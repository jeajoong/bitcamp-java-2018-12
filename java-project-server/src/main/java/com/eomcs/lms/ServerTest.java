package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.eomcs.lms.domain.Member;

public class ServerTest {
  static Socket socket;
  static ObjectOutputStream out;
  static ObjectInputStream in;
  
  public static void main(String[] args) {
    
    try {socket = new Socket("localhost", 8888);
        out = new ObjectOutputStream(socket.getOutputStream());
         in = new ObjectInputStream(socket.getInputStream());
             
      
      System.out.println("서버와 연결되었음.");
      
      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPassword("1111");
      member.setPassword("hong.gif");
      member.setTel("1111-1111");
      member.setRegisteredDate(null);
      
      add(member);
      
      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("leem@test.com");
      member.setPassword("1111");
      member.setPhoto("leem.jpeg");
      member.setTel("1111-3333");
      member.setRegisteredDate(null);
      
      add(member);
      
      
      
      System.out.println(in.readObject());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {in.close();} catch (Exception e) {}
    try {out.close();} catch (Exception e) {}
    try {socket.close();} catch (Exception e) {}
    System.out.println("서버와의 연결을 끊었음.");
  }

  private static void add(Object obj) throws Exception {
    out.writeObject(obj);
    out.flush();
  }

}
