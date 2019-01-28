package com.bitcamp.lms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

  // 사용자가 입력한 명령을 보관할 스택 준비
  static Stack<String> commandHistory = new Stack<>();
  static ArrayDeque<String> commandHistory2 = new ArrayDeque<>();

  public static void main(String[] args) {
    
    ArrayList<Board> boardList = new ArrayList<>();
    ArrayList<Lesson> lessonList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    HashMap<String,Command> commandMap = new HashMap<>();
    
    commandMap.put("/board/add", new BoardAddCommand(keyboard, boardList));
    commandMap.put("/board/list", new BoardListCommand(keyboard, boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(keyboard, boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(keyboard, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(keyboard, boardList));
    
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

    while (true) {
      String command = prompt(); // command 인터페이스 객체에 prompt를 넣고 기록하기위함

      // 사용자가 입력한 명령을 스택에 보관한다.
      commandHistory.push(command);
      
      // 사용자가 입력한 명령을 큐에 보관한다.
      commandHistory2.offer(command);
      
      if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
        
      } else if (command.equals("history")) { //// history 111 스택에서 읽어오기
        printCommandHistory(new Iterator<String>() { // 메서드 사용
          int index = commandHistory.size() - 1;
          
          @Override
          public boolean hasNext() {
            return index >= 0; 
          }
          @Override
          public String next() {
            return commandHistory.get(index--);
          }
        });
        
      } else if (command.equals("history2")) { //// history 222 큐에서 읽어오기
        printCommandHistory(commandHistory2.iterator());
        
      } else {
        Command commandHandler = commandMap.get(command); // 커맨드 핸들러라는 커맨드 인터페이스 객체에 명령어를 넣는다
        
        if (commandHandler == null) //
          System.out.println("실행할 수 없는 명령입니다.");
        else 
          commandHandler.execute();
      }
      
      System.out.println(); // 결과 출력 후 빈 줄 출력
    }

    keyboard.close();
  }

  private static void printCommandHistory(Iterator<String> iterator) { 
    //iterator 무엇인가 많이 모여있는 것들을 순서대로 지정하면서 전체를 검색하는 처리를 실행하기 위한 것
    //Iterator<String> 은 String 형태만 받겠다는 것이고 iterator 이름으로 정한다는 의미
    try {
      int count = 0;
      while (iterator.hasNext()) {
        System.out.println(iterator.next());
        if (++count % 5 == 0) {
          System.out.print(":");
          String input = keyboard.nextLine();
          if (input.equalsIgnoreCase("q"))
            break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private static String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }
}

//HashMap<String,Command> commandMap = new HashMap<>(); 커맨드맵은 해시맵 형태로 선언했고 그것은 String과 Command형태를 받는다

//Command commandHandler = commandMap.get(command); 이 라인에서
//commandMap.put(~~~~) put을 사용해 커맨드맵 커맨드에 값을 지정해 줬고
// 커맨드핸들러는 이 값을 보유하게 된다!
// 보유한 상태에서 if 문으로 (commandHandler == null) 이게 되면 실행하지 않고 
// commandHandler.execute(); 이면 실행하게 된다?

