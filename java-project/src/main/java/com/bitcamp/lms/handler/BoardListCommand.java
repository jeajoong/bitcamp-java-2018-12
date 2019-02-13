package com.bitcamp.lms.handler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import com.bitcamp.lms.domain.Board;

public class BoardListCommand implements Command {
  
  Scanner keyboard;
  List<Board> list;
  
  public BoardListCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute(ObjectInputStream in, ObjectOutputStream out) {
    Board[] boards = list.toArray(new Board[] {});
    for (Board board : boards) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          board.getNo(), board.getContents(), 
          board.getCreatedDate(), board.getViewCount());
    }
  }

}
