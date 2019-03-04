package com.eomcs.lms.handler;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardDetailCommand extends AbstractCommand {
  
  PhotoBoardDao photoBoardDao;
  
  public PhotoBoardDetailCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
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
  }
}
