package com.bitcamp.lms.Handler;

import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.domain.*;

//static에는 this를 사용할수 없다
public class BoardHandler {
  
  Scanner keyboard;
  ArrayList arraylist = new ArrayList(); 
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  public void listBoard() {
    Object[] objs = arraylist.toArray(); 
    
    for (Object obj : objs) {
      Board board = (Board) obj;
      System.out.printf("%3d, %-20s, %s, %d\n", 
          board.getNo(), board.getContents(), 
          board.getCreatedDate(), board.getViewCount());
    }
  }

  public void addBoard() {
    Board board = new Board(); 
    
    System.out.print("번호? ");
    board.setNo(Integer.parseInt(keyboard.nextLine()));
    
    System.out.print("내용? ");
    board.setContents(keyboard.nextLine());
    
    board.setCreatedDate(new Date(System.currentTimeMillis())); 
    
    board.setViewCount(0);
    
    arraylist.add(board);//  
    
    System.out.println("저장하였습니다.");
  }

}