//7까지...
package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class ServerApp {

  static ArrayList<Member> members = new ArrayList<>();
  static ArrayList<Lesson> lessons = new ArrayList<>();
  static ArrayList<Board> boards = new ArrayList<>();
 
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  public static void main(String[] args) {
    
    
    try(ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작");

      while(true) {
      try (Socket socket = serverSocket.accept();
          ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          ) {
        System.out.println("클라이언트와 연결되었음");
        members.clear();
        lessons.clear();
        boards.clear();
        ServerApp.in = in;
        ServerApp.out = out;
        
        MemberCommand.in = in;
        MemberCommand.out = out;
        
        LessonCommand.in = in;
        LessonCommand.out = out;
        
        BoardCommand.in = in;
        BoardCommand.out = out;
        
        while(true) {
          String request = in.readUTF();
          System.out.println(request);
          
          if(request.startsWith("/member")) {
            MemberCommand.service(request);
          } else if (request.startsWith("/lesson/")) {
            LessonCommand.service(request);
          } else if (request.startsWith("/board/")) {
            BoardCommand.service(request);
          } else if (request.equals("quit")) {
            quit();
            break loop;
          }
        }
        
        
  } catch (Exception e) {
    e.printStackTrace();
  }
}
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
