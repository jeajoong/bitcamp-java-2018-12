// 프로젝트2 - 2 -> 3 리스너 작업완료
// App을 클라이언트와 서버로 나누고 작업
//현재 파일을 서버통신으로 만들기
package com.eomcs.lms;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.handler.Command;

public class ServerApp {

  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();
  
  Scanner keyboard = new Scanner(System.in);
  Stack<String> commandHistory = new Stack<>();
  Queue<String> commandHistory2 = new LinkedList<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }
 
  public void service() throws Exception {
    
    try(ServerSocket ss = new ServerSocket(8888)) {
      /////서버는 여기까지만 했음 
    
    HashMap<String,Object> context = new HashMap<>();
    context.put("keyboard", keyboard);
    
    for(ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
    
    while (true) {
      String input = prompt();
      
      commandHistory.push(input);
      commandHistory2.offer(input);

      if (input.equals("quit")) {
        System.out.println("종료합니다.");
        break;
        
      } else if (input.equals("history")) {
        printCommandHistory();
        continue;
        
      } else if (input.equals("history2")) {
        printCommandHistory2();
        continue;
      } 
      
      Command commandHandler = (Command) context.get(input);
      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        continue;
      }
      
      try {
        commandHandler.execute();
        System.out.println(); 

      } catch (Exception e) {
        System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
      }
      
      
      for(ApplicationContextListener listener : listeners) {
        listener.contextDestroyed(context);
      }
      
    }
    keyboard.close();
  }
  }
  
  @SuppressWarnings("unchecked")
  private void printCommandHistory() {
    Stack<String> temp = (Stack<String>) commandHistory.clone();

    while (temp.size() > 0) {
      System.out.println(temp.pop());
    }
  }

  @SuppressWarnings("unchecked")
  private void printCommandHistory2() {
    Queue<String> temp = (Queue<String>) ((LinkedList<String>) commandHistory2).clone();

    while (temp.size() > 0) {
      System.out.println(temp.poll());
    }
  }

  private String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }

  public static void main(String[] args) throws Exception {
    ServerApp serverApp = new ServerApp();

    serverApp.addApplicationContextListener(new ApplicationInitializer());
    
    serverApp.service();
  }
}
