// 프로젝트2 - 2 -> 3 리스너 작업완료
// App을 클라이언트와 서버로 나누고 작업
//현재 파일을 서버통신으로 만들기
// 스캐너 객체, 커맨드 저장은 서버에서 관리 x ,
// 
package com.eomcs.lms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.handler.Command;

public class ServerApp {

  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();
  
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }
 
  public void service() throws Exception {
    
    try(ServerSocket ss = new ServerSocket(8888)) {
    
    HashMap<String,Object> context = new HashMap<>();
    
    for(ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
    
    while (true) {
      
      try(Socket socket = ss.accept(); 
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(socket.getOutputStream())
          ) {
        
      String input = in.readLine();
      
      if (input.equals("stop")) {
        System.out.println("종료합니다.");
        break;
      } 
      
      Command commandHandler = (Command) context.get(input);
      
      if (commandHandler == null) {
        out.println("실행할 수 없는 명령입니다.");
        out.println("!end!");
        out.flush();
        continue;
      }
      
      try {
        commandHandler.execute(in, out);

        out.println("!end!");
        out.flush();
        
        
      } catch (Exception e) {
        System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
      }
      }
    }// while 끝
      for(ApplicationContextListener listener : listeners) {
        listener.contextDestroyed(context);
      }
  }
  }
  
  public static void main(String[] args) throws Exception {
    ServerApp serverApp = new ServerApp();

    serverApp.addApplicationContextListener(new ApplicationInitializer());
    
    serverApp.service();
  }
}
