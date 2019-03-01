package com.eomcs.lms.handler;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;


//서버이기 때문에 스캐너 객체 필요없어지고, 값을 읽어옴(Response 객체)
public class BoardAddCommand extends AbstractCommand {
  
  BoardDao boardDao; // 서버의 BoardDaoImpl 객체를 대행하는 프록시 객체이다.
  
  public BoardAddCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public void execute(Response response) {
    Board board = new Board();

    try {
    board.setContents(response.requestString("내용?"));
      boardDao.insert(board);
      response.println("저장하였습니다.");
    } catch(Exception e) {
    }
    
  }
}
