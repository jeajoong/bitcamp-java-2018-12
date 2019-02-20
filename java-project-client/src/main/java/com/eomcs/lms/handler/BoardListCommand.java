package com.eomcs.lms.handler;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {

  Scanner keyboard;
  BoardDao boardDao;
  
  public BoardListCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    try {
      boardDao.findAll();
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }

}
