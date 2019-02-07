package com.bitcamp.lms;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
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
  static ArrayList<Lesson> lessonList;
  static ArrayList<Member> memberList;
  static ArrayList<Board> boardList;
  
  public static void main(String[] args) {
    
    loadLessonData();
    loadMemberData();
    loadBoardData();

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
          System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
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
    
    saveLessonData();
    saveMemberData();
    saveBoardData();
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
  
  @SuppressWarnings("unchecked")
  private static void loadLessonData() {
    try (ObjectInputStream in = new ObjectInputStream(
          new BufferedInputStream(
              new FileInputStream("lesson3.data")))) {
      
      lessonList = (ArrayList<Lesson>) in.readObject();
      
    } catch (Exception e) {
      System.out.println("수업 데이터를 읽는 중 오류 발생: " + e.toString());
      lessonList = new ArrayList<>();
    }
  }
  
  private static void saveLessonData() {
    try (ObjectOutputStream out = new ObjectOutputStream(
            new BufferedOutputStream(
                new FileOutputStream("lesson3.data")))) {
      
      out.writeObject(lessonList);
      
    } catch (Exception e) {
      System.out.println("수업 데이터를 쓰는 중 오류 발생: " + e.toString());
    }
  }
  
  private static void loadMemberData() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream("member3.data")))) {
      
      memberList = (ArrayList<Member>) in.readObject();
      
      
    } catch (Exception e) {
      System.out.println("회원 데이터를 읽는 중 오류 발생: " + e.toString());
      memberList = new ArrayList<>();
    }
  }
  
  private static void saveMemberData() {
    try (ObjectOutputStream out = new ObjectOutputStream(
          new BufferedOutputStream(
              new FileOutputStream("member.data")))) {
      
      out.writeObject(memberList);      
      
    } catch (Exception e) {
      System.out.println("회원 데이터를 쓰는 중 오류 발생: " + e.toString());
    }
  }
  
  private static void loadBoardData() {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream("board.data")))) {
      
      boardList = (ArrayList<Board>) in.readObject();
      
    } catch (Exception e) {
      System.out.println("게시글 데이터를 읽는 중 오류 발생: " + e.toString());
      boardList = new ArrayList<>();
    }
  }
  
  private static void saveBoardData() {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream("board.data")))) {
    
      out.writeObject(boardList);
      
    } catch (Exception e) {
      System.out.println("게시글 데이터를 쓰는 중 오류 발생: " + e.toString());
    }
  }
}
