package com.bitcamp.lms.Handler;

import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.App;
import com.bitcamp.lms.domain.*;

//static에는 this를 사용할수 없다
public class BoardHandler {
  Board[] boards = new Board[App.LENGTH];
  int boardIdx = 0;
  
    Scanner keyboard;
    public BoardHandler(Scanner keyboard){ // 생성자는 클래스 명과 똑같아야 한다 , 파라미터로 Scanner요구
      this.keyboard = keyboard; // keyboard 파라미터값 
    }   //생성자는 리턴값이 없어야 한다.

  public void addboard() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.setNo(Integer.parseInt(this.keyboard.nextLine()));
    
    System.out.print("내용? ");
    board.setContents(this.keyboard.nextLine());
    
    board.setCreatedDate(new Date(System.currentTimeMillis())); 
    
    board.setViewCount(0);
    
    boards[boardIdx] = board;
    boardIdx++;
    
    System.out.println("저장하였습니다.");
    
  }
  public void listborad() {
    
  for (int j = 0; j < boardIdx; j++) {
    System.out.printf("%3d, %-20s, %s, %d\n", 
        boards[j].getNo(), boards[j].getContents(), boards[j].getCreatedDate(), boards[j].getViewCount());
  }
  }
}
