package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  public static void main(String[] args) {
    try (Socket socket = new Socket("localhost", 8888);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ) {
      System.out.println("서버와 연결");
      
      ServerTest.in = in;
      ServerTest.out = out;
      
      add(new Member(1, "홍길동"));
      add(new Member(2, "임꺽정"));
      
      detail(1);
      
      update(new Member(1, "홍길동x"));
      
      detail(1);
      
      delete(2);
      
      list();
      
      quit();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버와의 연결을 끊었음");
  }


  private static void add(Member member) throws Exception {
    out.writeUTF("/member/add");
    out.writeObject(member);
    out.flush();
 
    String status = in.readUTF();
    
    if(status.equals("OK"))
      System.out.println("데이터 추가 성공");
    else
      System.out.println("데이터 추가 실패");
    
  }

  private static void list() throws Exception {
    out.writeUTF("/member/list"); 
    out.flush();
    
    String status = in.readUTF();
    
    if(!status.equals("OK")) {
      System.out.println("데이터 목록 가져오기 실패!");
      return;
    }
    
    List<Member> members = (List<Member>) in.readObject();
    for(Member m : members) {
      System.out.println(m);
    }
  }
  
  private static void delete(int no) throws Exception {
    out.writeUTF("/member/delete");
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    
    if(status.equals("OK"))
      System.out.println("데이터 삭제 성공!");
    else
      System.out.println("데이터 삭제 실패!");
  }
  
  private static void update(Member member) throws Exception {
    out.writeUTF("/member/update");
    out.writeObject(member);
    out.flush();
    
    String status = in.readUTF();
    
    if(status.equals("OK"))
      System.out.println("데이터 변경 성공!");
    else
      System.out.println("데이터 변경 실패!");
  }
  
  private static void detail(int no) throws Exception {
    out.writeUTF("/member/detail");
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    
    if(! status.equals("OK")) {
      System.out.println("데이터 가져오기 실패!");
      return;
    }
    Member member = (Member) in.readObject();
    System.out.println(member);
  }

  private static void quit() throws Exception {
    out.writeUTF("quit"); out.flush();
    System.out.println(in.readUTF());
  }

}
