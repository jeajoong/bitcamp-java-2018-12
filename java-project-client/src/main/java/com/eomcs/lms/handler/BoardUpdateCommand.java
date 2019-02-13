package com.eomcs.lms.handler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand implements Command {
  
  Scanner keyboard;
  
  public BoardUpdateCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute(ObjectInputStream in, ObjectOutputStream out) {
    
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    try {
      out.writeUTF("/board/update");
      out.flush();
      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
      
      ///////
      if (!(in.readObject().equals(""))) {
      Board board = (Board) in.readObject();
      
      out.writeObject(board);
      out.flush();
      } else {
        System.out.println("서버에서 데이터 불러오기 실패");
      }
        //////
      String status = in.readUTF();
      
      if (! status.equals("OK"))
        throw new Exception("서버에서 데이터 변경 실패!");
      
        System.out.println("데이터 변경 실패!");
      
    } catch (Exception e) {
      
      
    }
  }
  
}
