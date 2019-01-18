package com.bitcamp.lms;

import java.sql.Date;

public class BoardHandler {
  int no;
  String contents;
  Date createdDate;
  int viewCount;

  static BoardHandler[] boards = new BoardHandler[App.LENGTH];
  static int boardIdx = 0;
  
  static void addboard() {
    BoardHandler board = new BoardHandler();
    
    System.out.print("번호? ");
    board.no = App.keyboard.nextInt();
    
    System.out.print("내용? ");
    board.contents = App.keyboard.next();
    
    board.createdDate = new Date(System.currentTimeMillis()); 
    
    board.viewCount = 0;
    
    boards[boardIdx] = board;
    boardIdx++;
  }
  static void listboard() {
    for (int j = 0; j < boardIdx; j++) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          boards[j].no, boards[j].contents, boards[j].createdDate, boards[j].viewCount);
    }
    
  }
}
