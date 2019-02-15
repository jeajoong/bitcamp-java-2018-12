// 9단계 : Service 패키지로 옮기기
package com.eomcs.lms.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.domain.Board;

public class BoardService {

  static ArrayList<Board> boards = new ArrayList<>();
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  public BoardService(ObjectInputStream in , ObjectOutputStream out) {
    this.in= in;
    this.out = out;
  }
  
    public void execute(String request) throws Exception {
          
          switch (request) {
            case "/board/add":
              add();
              break;
            case "/board/list":
              list();
              break;
            case "/board/detail":
              detail();
              break;
            case "/board/update":
              update();
              break;
            case "/board/delete":
              delete();
              break;
              default:
                out.writeUTF("FAIL");
          }
          out.flush();
        }
  
  static void add() throws Exception {
    out.writeUTF("OK");
    out.flush();
    boards.add((Board) in.readObject()); // add => List의 add임
    out.writeUTF("OK");
  }
  
   static void list() throws Exception {
   out.writeUTF("OK"); 
   out.flush();
   out.writeUTF("OK");
    out.writeObject(boards);
  }
  
   static void detail() throws IOException {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();
    
    for (Board m : boards) {
      if(m.getNo() == no) {
        out.writeUTF("OK");
        out.writeObject(m);
        return;
      }
    }
    out.writeUTF("fail");
  }
  
  static void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    
    Board board = (Board) in.readObject();
    
    int index = 0;
    for (Board m : boards) {
      if(m.getNo() == board.getNo()) { 
        boards.set(index, board); 
        out.writeUTF("OK");
        return;
      }
      index++; // 0번째 부터 훑음.. 조건식이 맞다면 결국엔 index의 값도 리스트.getno 값이다
    }
    out.writeUTF("fail");
  }
  
  static void delete() throws Exception {
    out.writeUTF("OK");
    out.flush();
    
    int no = in.readInt(); 
    
    int index = 0; 
    for(Board m : boards) {
      if(m.getNo() == no) {
        boards.remove(index); 
        out.writeUTF("OK");
        return;
      }
      index++;
    }
    out.writeUTF("Fail");
  }
}
