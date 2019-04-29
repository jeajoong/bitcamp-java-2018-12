package com.eomcs.lms.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@Controller
@RequestMapping("/photoboard")
public class PhotoBoardController {

  @Autowired LessonService lessonService;
  @Autowired PhotoBoardService photoBoardService;
  @Autowired ServletContext servletContext;
  
  @GetMapping("form")
  public void form(Model model) {
    List<Lesson> lessons = lessonService.list(0, lessonService.size());
    model.addAttribute("lessons", lessons);
  }
  
  @PostMapping("add")
  public String add(PhotoBoard board, Part[] photo) throws Exception {

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
      return "redirect:.";
    }
  }
  
  @GetMapping("delete/{no}")
  public String delete(@PathVariable int no) {
    if (photoBoardService.delete(no) == 0)
      throw new RuntimeException("해당 번호의 사진이 없습니다.");
    return "redirect:../";
  }
  
  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) { 

    PhotoBoard board = photoBoardService.get(no); 
    // 수업 데이터를 가져오려면 size()로 DB에 있는 수업 정보 숫자를 돌려 받고 넘겨준다!!!
    List<Lesson> lessons = lessonService.list(0, lessonService.size());
    model.addAttribute("board", board);
    model.addAttribute("lessons", lessons);
    
    return "photoboard/detail";
  }
  
  @GetMapping
  public String list(
      @RequestParam(defaultValue="1") int pageNo, // 페이지의 대한 번호나 사이즈가 넘어오지 않는다면
      @RequestParam(defaultValue="3") int pageSize, // 기본적으로 사이즈를 지정한다.
      Model model) {
    
    if(pageSize < 3 || pageSize >8)
      pageSize = 3;
    
    int rowCount = lessonService.size();
    int totalPage = rowCount / pageSize;
    if (rowCount % pageSize > 0)
      totalPage++;
    
    if(pageNo < 1)
      pageNo=1;
    else if (pageNo > totalPage)
      pageNo = totalPage;
    
    List<PhotoBoard> boards = photoBoardService.list(0, null, pageNo, pageSize);
    model.addAttribute("list", boards);
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("pageSize",pageSize);
    model.addAttribute("totalPage",totalPage);
    
    return "photoboard/list";
  }
  
  
  @GetMapping("search")
  public void search(
      @RequestParam(defaultValue="0") int lessonNo,
      @RequestParam(defaultValue="1") int pageNo, // 페이지의 대한 번호나 사이즈가 넘어오지 않는다면
      @RequestParam(defaultValue="3") int pageSize, // 기본적으로 사이즈를 지정한다.
      String keyword,
      Model model) {
    
    if(pageSize < 3 || pageSize >8)
      pageSize = 3;
    
    int rowCount = lessonService.size(); // rowCount에 수업데이터 총 개수를 담고
    int totalPage = rowCount / pageSize; // 수업데이터 수를 3으로 나누고 totalPage에 담는다.
    if (rowCount % pageSize > 0)  // 만약 수업데이터를 pagesize로 나눴을때 0이상이라면
      totalPage++; // 페이지 수를 하나 더 늘려준다 (1,2개 남은 게시물을 위해서.)
    
    if(pageNo < 1) // pageNo가 0이라면
      pageNo = 1; // pageNo는 1로 고정시켜준다.
    else if (pageNo > totalPage) // pageNo가 전체페이지 크기보다 크다면
      pageNo = totalPage; // 전체페이지 크기를 pageNo에 담는다.
    
    String searchWord = null;
    if (keyword.length() > 0) 
      searchWord = keyword;
    List<PhotoBoard> boards = photoBoardService.list(lessonNo, searchWord, pageNo, pageSize);
    model.addAttribute("list", boards);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("keyword", searchWord);
    model.addAttribute("lessonNo", lessonNo);    
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("totalPage", totalPage);
    
    // 문제점. 처음 1페이지는 나오지만 2페이지를 누르게 되면 널포인트에러,
    // 해결법은 뭘까 ....
    //select photo_id, titl, cdt, vw_cnt, lesson_id from lms_photo where lesson_id = '101' limit 1,3;
    //DB SQL문
    
  }
  
  @PostMapping("update")
  public String update(PhotoBoard board, Part[] photo) throws Exception {

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
    return "redirect:.";
  }
}