package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.eomcs.lms.domain.Member;

public class ServerApp {

  static ArrayList<Member> members = new ArrayList<>();
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
        ServerApp.in = in;
        ServerApp.out = out;
        
        while (true) {
          String request = in.readUTF();
          System.out.println(request);
          
          loop: switch (request) {
            case "quit":
              quit();
              break loop;
            case "add":
              add();
              break;
            case "list":
              list();
              break;
              default:
                out.writeUTF("이 명령을 처리할 수 없음!");
          }
          out.flush();
        }
      } catch (Exception e) {
      }
      System.out.println("클라이언트와의 연결을 끊음");
      }
    } catch (Exception e) {
    }
  }

  private static void list() throws Exception {
    out.writeObject(members);
  }

  private static void add() throws Exception {
    members.add((Member) in.readObject());
    out.writeUTF("OK");
  }

  private static void quit() throws Exception {
    out.writeUTF("종료함!");
    out.flush();
  }

}
