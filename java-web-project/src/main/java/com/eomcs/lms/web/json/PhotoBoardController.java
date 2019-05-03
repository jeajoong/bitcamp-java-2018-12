package com.eomcs.lms.web.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@RestController("json/PhotoBoardController")
@RequestMapping("/json/photoboard")
public class PhotoBoardController {

  @Autowired LessonService lessonService;
  @Autowired PhotoBoardService photoBoardService;
  @Autowired ServletContext servletContext;
  
  @PostMapping("add")
  public Object add(PhotoBoard board, Part[] photo) throws Exception {
    HashMap<String,Object> content = new HashMap<>();
    try {
    
    ArrayList<PhotoFile> files = new ArrayList<>();
    
    String uploadDir = servletContext.getRealPath("/upload/photoboard");
    
    for (Part part : photo) {
      if (part.getSize() == 0) 
        continue;
      
      String filename = UUID.randomUUID().toString();
      part.write(uploadDir + "/" + filename);
      
      PhotoFile file = new PhotoFile();
      file.setFilePath(filename);
      files.add(file);
    }
    board.setFiles(files);

    if (board.getLessonNo() == 0) {
      throw new RuntimeException("사진 또는 파일을 등록할 수업을 선택하세요.");
      
    } else if (files.size() == 0) {
      throw new RuntimeException("최소 한 개의 사진 파일을 등록해야 합니다.");

    } else if (board.getTitle().length() == 0) {
      throw new RuntimeException("사진 게시물 제목을 입력하세요.");

    } else {
      photoBoardService.add(board);
      content.put("status", "success"); 
      }
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
    return content;
  
}
  
  
  @GetMapping("delete")
  public Object delete(int no) {
    HashMap<String,Object> content = new HashMap<>();
    try {
    if (photoBoardService.delete(no) == 0)
      throw new RuntimeException("해당 번호의 사진이 없습니다.");
    content.put("status", "success");
    
  } catch (Exception e) {
    content.put("status", "fail");
    content.put("message", e.getMessage());
  }
  return content;
}
  
  @GetMapping("detail")
  public Object detail(int no) { 
    
    HashMap<String,Object> map = new HashMap<>();
    PhotoBoard board = photoBoardService.get(no); 
    List<Lesson> lessons = lessonService.list(0, lessonService.size());
    
    map.put("board", board);
    map.put("lessons", lessons);
    
  return map;
}
  
  @GetMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize) {
    
    if(pageSize < 3 || pageSize >8)
      pageSize = 3;
    
    int rowCount = photoBoardService.size();
    int totalPage = rowCount / pageSize;
    if (rowCount % pageSize > 0)
      totalPage++;
    
    if(pageNo < 1)
      pageNo=1;
    else if (pageNo > totalPage)
      pageNo = totalPage;
    
    List<PhotoBoard> boards = photoBoardService.list(0, null, pageNo, pageSize);
    
    HashMap<String,Object> content = new HashMap<>();
    content.put("list", boards);
    content.put("pageNo", pageNo);
    content.put("pageSize",pageSize);
    content.put("totalPage",totalPage);
    
    return content;
  }
  
  
  
  @GetMapping("search")
  public Object search(
      @RequestParam(defaultValue="0") int lessonNo,
      @RequestParam(defaultValue="1") int pageNo, // 페이지의 대한 번호나 사이즈가 넘어오지 않는다면
      @RequestParam(defaultValue="3") int pageSize, // 기본적으로 사이즈를 지정한다.
      String keyword) {
    
    if(pageSize < 3 || pageSize >8)
      pageSize = 3;

    // 검색결과의 데이터 수에 맞춰 페이지를 딱 맞추려면
    // 검색결과의 데이터 개수가 필요함...... 
    
    int rowCount = photoBoardService.size();
    int totalPage = rowCount / pageSize; // 수업데이터 수를 3으로 나누고 totalPage에 담는다.
    if (rowCount % pageSize > 0)   // 만약 수업데이터를 pageSize로 나눴을때 0이상이라면
      totalPage++; // 페이지 수를 하나 더 늘려준다 (1,2개 남은 게시물을 위해서.)
    
    if(pageNo < 1) // pageNo가 0이라면
      pageNo = 1; // pageNo는 1로 고정시켜준다.
    else if (pageNo > totalPage) //
      totalPage= pageNo; 
    
    String searchWord = null;
    if (keyword.length() > 0) 
      searchWord = keyword;
    
    List<PhotoBoard> boards = photoBoardService.list(lessonNo, searchWord, pageNo, pageSize);
    HashMap<String,Object> content = new HashMap<>();
    try {
    content.put("list", boards);
    content.put("keyword", searchWord);
    content.put("lessonNo", lessonNo);
    content.put("pageSize", pageSize);
    content.put("pageNo", pageNo);
    
    List<PhotoBoard> searchBoards = photoBoardService.list(lessonNo, searchWord, 1, photoBoardService.size());
   
    int count = searchBoards.size() / pageSize;
    if (searchBoards.size() % pageSize > 0) 
      count ++;
    
      if (totalPage > count)
        totalPage = count;
    
      content.put("totalPage", totalPage);
      
      content.put("status", "success"); 
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
      return content;
  }
  
  
  
  
  @PostMapping("update")
  public Object update(PhotoBoard board, Part[] photo) throws Exception {
    HashMap<String,Object> content = new HashMap<>();
    try {
    ArrayList<PhotoFile> files = new ArrayList<>();
    String uploadDir = servletContext.getRealPath("/upload/photoboard");
    
    for (Part part : photo) {
      if (part.getSize() == 0)
        continue;

      String filename = UUID.randomUUID().toString();
      part.write(uploadDir + "/" + filename);

      PhotoFile file = new PhotoFile();
      file.setFilePath(filename);
      file.setPhotoBoardNo(board.getNo());
      files.add(file);
    }
    board.setFiles(files);

    if (files.size() == 0) 
      throw new RuntimeException("최소 한 개의 사진 파일을 등록해야 합니다.");
    
    photoBoardService.update(board);
    content.put("status", "success");
    
  } catch (Exception e) {
    content.put("status", "fail");
    content.put("message", e.getMessage());
  }
  return content;
}
}
  
  
  
  