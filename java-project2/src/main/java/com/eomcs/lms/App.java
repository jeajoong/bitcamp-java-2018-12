// 2단계: Connection 객체 공유하기
// => DAO에서 SQL 을 실행할 때마다 Connection 객체를 생성하는데,
//    실행 속도도 높이고 커넥션 객체가 가비지가 되는 것도 줄이기 위해 
//    커넥션 객체를 생성하여 공유한다.
package com.eomcs.lms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.handler.Command;

public class App {

  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();
  
  Scanner keyboard = new Scanner(System.in);
  Stack<String> commandHistory = new Stack<>();
  Queue<String> commandHistory2 = new LinkedList<>();
  
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }
  
  public void service() throws Exception {

    HashMap<String, Object> context = new HashMap<>();
    context.put("keyboard", keyboard);
    
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
      
      // 사용자가 입력한 명령으로 Command 객체를 찾는다.
      Command commandHandler = (Command) context.get(input);
      
      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        continue;
      }
      
      // stateful을 stateless로 전환할 때 주의할 점!
      // => 가능한 서버에 요청하는 시점에 서버와 연결하라!
      // => 이 클래스에서 서버와 연결하지 않고 
      //    데이터를 요청하는 일을 하는 객체(*Agent)에 서버 연결을 맡긴다. 
      try {
        commandHandler.execute();
        System.out.println(); 

      } catch (Exception e) {
        System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
      }
    }
    
    keyboard.close();
    for(ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
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
    App app = new App();
    
    app.addApplicationContextListener(new ApplicationInitializer());

    // App 을 실행한다.
    app.service();
  }
}
