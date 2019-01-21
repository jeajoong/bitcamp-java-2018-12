package com.bitcamp.lms.handler;
import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.domain.Board;
import com.bitcamp.util.ArrayList;

public class BoardHandler {
  
  Scanner keyboard;
  ArrayList<Board> list;
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.list = new ArrayList<>(20);
  }
  
  public void listBoard() {
    Board[] boards = list.toArray(new Board[] {});
    for (Board board : boards) {
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
    
    list.add(board);
    
    System.out.println("저장하였습니다.");
  }
  
  public void detailBoard() {
    System.out.println("번호? ");
    int no = keyboard.nextInt();
    
    int index = indexB(no);
    
    
    Board board = list.get(index);
    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("작성일: %s\n", board.getCreatedDate());
  }
  
  private int indexB(int no) {
    for (int i = 0; i < list.size; i++) {
      Board b = list.get(i);
      if (b.getNo() == no) 
        return i;
    }
    return -1;
  }
}
