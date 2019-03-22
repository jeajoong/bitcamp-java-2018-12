package com.eomcs.lms.handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import service.PhotoBoardService;

@Component
public class PhotoBoardCommand {

  PhotoBoardService photoBoardService;
  
  public PhotoBoardCommand(PhotoBoardService boardService) {
    this.photoBoardService = boardService;
  }

  @RequestMapping("/photoboard/list")
  public void list(Response response) {
    List<PhotoBoard> boards = photoBoardService.list();
    
    for (PhotoBoard board : boards) {
      response.println(
          String.format("%3d, %-20s, %s, %d, %d", 
            board.getNo(), 
            board.getTitle(), 
            board.getCreatedDate(), 
            board.getViewCount(),
            board.getLessonNo()));
    }
  }
  
  @RequestMapping("/photoboard/add")
  public void add(Response response) throws Exception {
    try {
      PhotoBoard board = new PhotoBoard();
      board.setTitle(response.requestString("사진 제목?"));
      board.setLessonNo(response.requestInt("수업?"));
      
      response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      response.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
      
      ArrayList<PhotoFile> files = new ArrayList<>();
      while (true) {
        String filePath = response.requestString("사진 파일?");
        if (filePath.length() == 0) {
          if (files.size() == 0) {
            response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
            continue;
          } else {
            break;
          }
        }
        PhotoFile file = new PhotoFile();
        file.setFilePath(filePath);
//        file.setPhotoBoardNo(board.getNo());
//        이건 필요 없다 왜냐 file을 세팅 하는건 Service에서 처리했음
        files.add(file);
      }

      board.setFiles(files); // 새로 적음
      // 왜냐면 기존엔 Dao와 통신해서 바로 파일을 set 해서 넣었지만
      // 지금은 Service를 대행해서 사용하기 때문에 PhotoBoard에 담아서 넘겨줘야 하니까..
      
      photoBoardService.add(board);
      response.println("저장하였습니다.");
      
    } catch (Exception e) {
      e.printStackTrace();
      response.println("저장 중 오류가 발생.");
    }
  }
  
  
  
  
  // 오호라 detail은 그대로!
  @RequestMapping("/photoboard/detail")
  public void detail(Response response) throws Exception {
    int no = response.requestInt("번호?");
    
    PhotoBoard board = photoBoardService.get(no);
    if (board == null) {
      response.println("해당 사진을 찾을 수 없습니다.");
      return;
    }
    
    response.println(String.format("제목: %s", board.getTitle()));
    response.println(String.format("작성일: %s", board.getCreatedDate()));
    response.println(String.format("조회수: %d", board.getViewCount()));
    response.println(String.format("수업: %s(%s ~ %s)", 
        board.getLesson().getTitle(),
        board.getLesson().getStartDate(),
        board.getLesson().getEndDate()));
    
    response.println("사진파일:");
    List<PhotoFile> files = board.getFiles();
    for (PhotoFile file : files) {
      response.println(String.format("> %s", file.getFilePath()));
    }
    
  }
  
  @RequestMapping("/photoboard/update")
  public void update(Response response) throws Exception {
    try {
      PhotoBoard board = new PhotoBoard();
      board.setNo(response.requestInt("번호?"));
      
      PhotoBoard origin = photoBoardService.get(board.getNo());
      if (origin == null) {
        response.println("해당 번호의 사진이 없습니다.");
        return;
      }
      
      String input = response.requestString(
          String.format("제목(%s)?", origin.getTitle()));
      
      if (input.length() > 0) {
        board.setTitle(input);
//        photoBoardDao.update(board); // 사진 게시물 제목 변경
      }
      
      // 변경하려면 사진 게시물의 첨부 파일을 출력한다.
      response.println("사진 파일:");
      List<PhotoFile> files = origin.getFiles();
      for (PhotoFile file : files) {
        response.println("> " + file.getFilePath());
      }
      response.println("");
      
      response.println("사진은 일부만 변경할 수 없습니다.");
      response.println("전체를 새로 등록해야 합니다.");
      input = response.requestString("사진을 변경하시겠습니까?(y/N)");
      if (input.equalsIgnoreCase("y")) {
        // 그리고 새 첨부 파일을 추가한다.
        response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
        response.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
        
        ArrayList<PhotoFile> photoFiles = new ArrayList<>();
        while (true) {
          String filePath = response.requestString("사진 파일?");
          if (filePath.length() == 0) {
            if (photoFiles.size() == 0) {
              response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
              continue;
            } else {
              break;
            }
          }
          PhotoFile file = new PhotoFile();
          file.setFilePath(filePath);
          file.setPhotoBoardNo(board.getNo());// 사진 게시물을 입력한 후 자동 생성된 PK 값을 꺼낸다.
          
          photoFiles.add(file);
        }
        // 한 번에 파일 정보를 insert 한다.
//        photoFileDao.insert(photoFiles);
        board.setFiles(photoFiles);
      }
      
      photoBoardService.update(board);
      response.println("변경했습니다.");
      
    } catch (Exception e) {
      response.println("변경 중 오류 발생.");
    }
  }

  
  
  
  
  
  
  
  
  @RequestMapping("/photoboard/delete")
  public void delete(Response response) throws Exception {
    
    // 트랜잭션 동작 방식을 설정한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    
    // 트랜잭션을 준비한다.
    TransactionStatus status = txManager.getTransaction(def);
    
    try {
      int no = response.requestInt("번호?");
  
      // 데이터를 지울 때는 자식 테이블의 데이터부터 지워야 한다.
      photoFileDao.deleteByPhotoBoardNo(no);
  
      if (photoBoardDao.delete(no) == 0) {
        response.println("해당 번호의 사진이 없습니다.");
        return;
      }
      response.println("삭제했습니다.");
      txManager.commit(status);
      
    } catch (Exception e) {
      txManager.rollback(status);
      response.println("삭제 중 오류 발생.");
    }
  }
  
  @RequestMapping("/photoboard/search")
  public void search(Response response) {
    HashMap<String,Object> params = new HashMap<>();
    try {
      int lessonNo = response.requestInt("수업 번호?");
      params.put("lessonNo", lessonNo);
    } catch (Exception e) {
    }
    
    try {
      String keyword = response.requestString("검색어?");
      if (keyword.length() > 0)
        //SQL에서 검색할 때 사용할 문자열 패턴을 다음과 같이 자바에서 만들어 전달할 수 있다. 
        //params.put("keyword", "%" + keyword + "%");
        
        // 또는 다음과 같이 키워드를 전달한 후 mybatis 쪽에서 패턴을 정의할 수 있다.
        params.put("keyword", keyword);
    } catch (Exception e) {
    }
    
    List<PhotoBoard> boards = photoBoardDao.findAll(params);
    
    response.println("[검색 결과]");
    for (PhotoBoard board : boards) {
      response.println(
          String.format("%3d, %-20s, %s, %d, %d", 
            board.getNo(), 
            board.getTitle(), 
            board.getCreatedDate(), 
            board.getViewCount(),
            board.getLessonNo()));
    }
  }
}
