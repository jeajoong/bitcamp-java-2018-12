package com.bitcamp.lms;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
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
  
  static ArrayList<Lesson> lessonList = new ArrayList<>(); // 222-1
  static LinkedList<Member> memberList = new LinkedList<>();
  static ArrayList<Board> boardList = new ArrayList<>();
  
  public static void main(String[] args) {
    
    //데이터 로딩 
    loadLessonDate();
    loadMemberDate();
    loadBoardDate();
    

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
        
      } else if (command.equals("quit")) { //333
       quit();
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
  //////////////////////////////////////////////////////////////////////////////////////////////////////////
  private static void quit() { // 444 save 마무리
    saveLessonDate();
    saveMemberDate();
    saveBoardDate();
    System.out.println("안녕!");
  }
  
  private static void loadLessonDate() { // 111
    try (FileReader in =new FileReader("lesson.csv");   // 555 -> Lesson.java
        Scanner in2 = new Scanner(in);
        ) {
      while (true) {
        String line = in2.nextLine(); // 번호, 제목, 내용, 시작일, 종료일, 총강의시간,일강의시간 //666 내용 위치 밑으로
        lessonList.add(Lesson.valueOf(line)); // 666위치 내용 위로
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch(NoSuchElementException e) {
      System.out.println("수업 데이터 로딩 완료!");
    }
  }
  
  private static void saveLessonDate() { // 111
    try (FileWriter out = new FileWriter("lesson.csv");) { // 222-2
      for (Lesson lesson : lessonList) {
        out.write(String.format("%s,%s,%s,%s,%s,%d,%d\n",
            lesson.getNo(),
            lesson.getTitle(),
            lesson.getContents(),
            lesson.getStartDate(),
            lesson.getEndDate(),
            lesson.getTotalHours(),
            lesson.getDayHours()));
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  
  
  private static void loadMemberDate() {
    try(FileReader in = new FileReader("member.csv"); 
      Scanner in2 = new Scanner(in);
      ) {
      while (true) {
         // 번호, 제목, 내용, 시작일, 종료일, 총강의시간,일강의시간 //666 위치 밑으로
        memberList.add(Member.valueOf(in2.nextLine()));
      }
        
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }catch (NoSuchElementException e){
      System.out.println("수업 데이터 로딩 완료!");
    }
  }
  
  private static void saveMemberDate() {
    try {
      FileWriter out = new FileWriter("member.csv");
      for (Member member : memberList) {
        out.write(String.format("%d,%s,%s,%s,%s,%s,%s\n",
        member.getNo(),
        member.getName(),
        member.getEmail(),
        member.getPassword(),
        member.getPhoto(),
        member.getTel(),
        member.getRegisteredDate() ));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  private static void loadBoardDate() {
    try {
      FileReader in = new FileReader("board.csv");
      Scanner in2 = new Scanner(in);
      while (true) {
        boardList.add(Board.valueOf(in2.nextLine()));
      }
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }catch (NoSuchElementException e){
      System.out.println("수업 데이터 로딩 완료!");
    }
  }
  
  private static void saveBoardDate() {
    try {
      FileWriter out = new FileWriter("board.csv");
      for (Board board : boardList) {
        out.write(String.format("%d,%s,%s,%d\n",
            board.getNo(),
            board.getContents(),
            board.getCreatedDate(),
            board.getViewCount() ));
      }
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
}
