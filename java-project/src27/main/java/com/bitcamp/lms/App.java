package com.bitcamp.lms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import com.bitcamp.lms.domain.Board;
import com.bitcamp.lms.domain.Lesson;
import com.bitcamp.lms.domain.Member;
import com.bitcamp.lms.handler.BoardAddCommand;
import com.bitcamp.lms.handler.BoardDeleteCommand;
import com.bitcamp.lms.handler.BoardDetailCommand;
import com.bitcamp.lms.handler.BoardListCommand;
import com.bitcamp.lms.handler.BoardUpdateCommand;
import com.bitcamp.lms.handler.Command;
import com.bitcamp.lms.handler.LessonAddCommand;
import com.bitcamp.lms.handler.LessonDeleteCommand;
import com.bitcamp.lms.handler.LessonDetailCommand;
import com.bitcamp.lms.handler.LessonListCommand;
import com.bitcamp.lms.handler.LessonUpdateCommand;
import com.bitcamp.lms.handler.MemberAddCommand;
import com.bitcamp.lms.handler.MemberDeleteCommand;
import com.bitcamp.lms.handler.MemberDetailCommand;
import com.bitcamp.lms.handler.MemberListCommand;
import com.bitcamp.lms.handler.MemberUpdateCommand;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  static Stack<String> commandHistory = new Stack<>();
  static Queue<String> commandHistory2 = new LinkedList<>();
  
  public static void main(String[] args) {
    ArrayList<Lesson> lessonList = new ArrayList<>();
    LinkedList<Member> memberList = new LinkedList<>();
    ArrayList<Board> boardList = new ArrayList<>();

    Map<String,Command> commandMap = new HashMap<>();
    commandMap.put("/lesson/add", new LessonAddCommand(keyboard, lessonList));
    commandMap.put("/lesson/list", new LessonListCommand(keyboard, lessonList));
    commandMap.put("/lesson/detail", new LessonDetailCommand(keyboard, lessonList));
    commandMap.put("/lesson/update", new LessonUpdateCommand(keyboard, lessonList));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(keyboard, lessonList));

    commandMap.put("/member/add", new MemberAddCommand(keyboard, memberList));
    commandMap.put("/member/list", new MemberListCommand(keyboard, memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(keyboard, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(keyboard, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(keyboard, memberList));
    
    commandMap.put("/board/add", new BoardAddCommand(keyboard, boardList));
    commandMap.put("/board/list", new BoardListCommand(keyboard, boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(keyboard, boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(keyboard, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(keyboard, boardList));
    
    while (true) {
      String command = prompt();

      // 사용자가 입력한 명령을 스택에 보관한다.
      commandHistory.push(command);
      
      // 사용자가 입력한 명령을 큐에 보관한다.
      commandHistory2.offer(command);
      
      // 사용자가 입력한 명령으로 Command 객체를 찾는다.
      Command commandHandler = commandMap.get(command);
      
      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch (Exception e) {
          //예외가 발생하면 다음 문장을 실행한다.
          //그리고 계속 프로그램을 실행한다.
          System.out.printf("작업 중 오류 발생 : %s\n",e.toString());
        }
        
      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
        
      } else if (command.equals("history")) {
        printCommandHistory();
        
      } else if (command.equals("history2")) {
        printCommandHistory2();
        
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      
      System.out.println(); 
    }

    keyboard.close();
  }

  @SuppressWarnings("unchecked")
  private static void printCommandHistory() {
    Stack<String> temp = (Stack<String>) commandHistory.clone();
    
    while (temp.size() > 0) {
      System.out.println(temp.pop());
    }
  }
  
  @SuppressWarnings("unchecked")
  private static void printCommandHistory2() {
    Queue<String> temp = (Queue<String>) ((LinkedList<String>) commandHistory2).clone();
    
    while (temp.size() > 0) {
      System.out.println(temp.poll());
    }
  }

  private static String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }
}
