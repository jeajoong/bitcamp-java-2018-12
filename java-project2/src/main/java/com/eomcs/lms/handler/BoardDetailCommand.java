package com.eomcs.lms.handler;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailCommand extends AbstractCommand {
  
  BoardDao boardDao;
  
  public BoardDetailCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(Response response) {
    try {
    int no = response.requestInt("번호?");
      Board board = boardDao.findByNo(no);
      if (board == null) {
        response.println("해당 번호의 게시물이 없습니다.");
        return;
      }
    
      //response에 println형밖에 정의하지 않아서 String.format 써야함
      response.println(String.format("내용: %s\n", board.getContents()));
      response.println(String.format("작성일: %s\n", board.getCreatedDate()));
      response.println(String.format("조회수: %d\n", board.getViewCount()));
    
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
