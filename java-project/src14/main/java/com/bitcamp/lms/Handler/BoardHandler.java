package com.bitcamp.lms.Handler;

import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.App;
import com.bitcamp.lms.domain.*;


public class BoardHandler {
  Board[] boards = new Board[App.LENGTH];
  int boardIdx = 0;
  
    Scanner keyboard;
    public BoardHandler(Scanner keyboard){
      this.keyboard = keyboard;
    }

  public void addboard() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.no = Integer.parseInt(this.keyboard.nextLine());
    
    System.out.print("내용? ");
    board.contents = this.keyboard.nextLine();
    
    board.createdDate = new Date(System.currentTimeMillis()); 
    
    board.viewCount = 0;
    
    boards[boardIdx] = board;
    boardIdx++;
    
    System.out.println("저장하였습니다.");
    
  }
  public void listborad() {
    
  for (int j = 0; j < boardIdx; j++) {
    System.out.printf("%3d, %-20s, %s, %d\n", 
        boards[j].no, boards[j].contents, boards[j].createdDate, boards[j].viewCount);
  }
  }
}
