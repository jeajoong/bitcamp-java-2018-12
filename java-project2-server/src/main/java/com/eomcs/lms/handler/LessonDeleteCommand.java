package com.eomcs.lms.handler;
import java.util.HashMap;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;

public class LessonDeleteCommand extends AbstractCommand {

  LessonDao lessonDao;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  
  public LessonDeleteCommand(
      LessonDao lessonDao, 
      PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao) {
    this.lessonDao = lessonDao;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }
  

  @Override
  public void execute(Response response) throws Exception {
      int no = response.requestInt("번호?");
      
      // 사진파일이 수업 데이터의 PK(수업번호)값을 공유하기에
      // 수업데이터를 삭제하려면 종속된 사진게시판과 사진파일을 지워야한다.
      // findAll의 형태를 바꾼것도 이와 같은 이유때문이다.
      // params에 lessonNo를 담아서 모든 정보를 찾아오고
      // 사진 게시판 관련 정보를 다 가져와서 객체에 담아서
      // 해당하는 lessonNo의 File부터 제거해 게시판을 삭제한다.
      HashMap<String,Object> params = new HashMap<>();
      params.put("lessonNo", no);
      
      List<PhotoBoard> boards = photoBoardDao.findAll(params);
      for (PhotoBoard board : boards) {
        photoFileDao.deleteByPhotoBoardNo(board.getNo());
        photoBoardDao.delete(board.getNo());
      }
      
      if (lessonDao.delete(no) == 0) {
        response.println("해당 번호의 수업이 없습니다.");
        return;
      }
      response.println("삭제했습니다.");
  }
}




