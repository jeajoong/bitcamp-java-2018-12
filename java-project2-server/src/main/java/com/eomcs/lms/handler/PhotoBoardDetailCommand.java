package com.eomcs.lms.handler;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoBoardDetailCommand extends AbstractCommand {
  
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  
  public PhotoBoardDetailCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호?");
    
    PhotoBoard board = photoBoardDao.findByNo(no);
    if (board == null) {
      response.println("해당 번호의 게시물이 없습니다.");
      return;
    }
    response.println(String.format("내용: %s", board.getTitle()));
    response.println(String.format("작성일: %s", board.getCreatedDate()));
    response.println(String.format("조회수: %d", board.getViewCount()));
    response.println(String.format("수업번호: %d",board.getLessonNo()));
   
    //파일 정보를 추가로 출력하기
    response.println("사진 파일: ");
    List<PhotoFile> files = photoFileDao.findByPhotoBoardNo(no);
    // PhotoBoard의 번호를 받아서 files에 저장 
    for(PhotoFile file : files) {
      response.println(String.format("> %s", file.getFilePath()));
    }

  }
}



