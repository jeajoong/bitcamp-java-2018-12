package com.bitcamp.lms.Handler;
import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.domain.Board;

public class BoardHandler {
  
  Scanner keyboard;
  ArrayList<Board> list;
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.list = new ArrayList<>();
  }
  
  public void listBoard() {
    Board[] boards = list.toArray(new Board[0]);
    
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
    
    int index = indexB(no); // index변수 만들어서 indexB의 i리턴값 넣어줌
    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }
    Board board = list.get(index); // board변수에 list배열변수에 i 값 넣어줌
                                                       // board변수에 값을 넣어주고 내용을 읽어야 하니까
    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("작성일: %s\n", board.getCreatedDate());
  }
  
  
  
  public void updateBoard() {
    
  }
  
  
  
  public void deleteBoard() {
    
  }
  
  
  //indexB의 역할 : 만들어져있는 게시판의 개수와 no값이 같으면 i반환
  public int indexB(int no) { // 번호를 입력했을때 값을 넘겨주고
    for (int i = 0; i < list.size; i++) { 
      Board b = list.get(i);  // 보드변수 b <= 배열리스트에 get 메소드 사용해 배열 항목 빼낸다
      if(b.getNo() == no) // 보드변수 b의 번호get메소드가 처음입력한 숫자와 같으면
        return i; // 숫자가 있다는 뜻이므로 i값을 돌려준다
    }
    return -1;  // 아니면 -1을 리턴
    
  }
}










