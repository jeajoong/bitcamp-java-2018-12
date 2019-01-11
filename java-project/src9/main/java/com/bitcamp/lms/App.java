//8번 -> 9번 도전
package com.bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  
  static final int LENGTH = 10;
  
  static Lesson[] lessons = new Lesson[LENGTH];
  static int lessonIdx = 0;
  
  static Member[] members = new Member[LENGTH];
  static int memberIdx = 0;
  
  static Board[] boards = new Board[LENGTH];
  static int boardIdx = 0;
  
  public static void main(String[] args) {
    
    while (true) {
      System.out.print("명령> ");
      String order = keyboard.next();
      
      if (order.equals("/lesson/add")) {
        addLesson();
      } else if (order.equals("/lesson/list")) {
        listLesson();
      } else if (order.equals("/member/add")) {
        addMember();
      } else if (order.equals("/member/list")) {
        listMember();
      } else if (order.equals("/board/add")) {
        addboard();
      } else if (order.equals("/board/list")) {
        listboard();
      } else if (order.equals("quit")) {
        end();
        break;
      } else
        System.out.println("실행할 수 없는 명령입니다.");
    }//while 끝
  }//메인 끝
  //메소드 모음
  static void end() {
    System.out.println("안녕!");
    keyboard.close();
  }
  static void listboard() {
    for (int j = 0; j < boardIdx; j++) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          boards[j].no, boards[j].contents, boards[j].createdDate, boards[j].viewCount);
    }
  }
  static void addboard() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.no = keyboard.nextInt();
    
    System.out.print("내용? ");
    board.contents = keyboard.next();
    
    board.createdDate = new Date(System.currentTimeMillis()); 
    
    board.viewCount = 0;
    
    boards[boardIdx] = board;
    boardIdx++;
  }
  static void listMember() {
    for (int j = 0; j < memberIdx; j++) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          members[j].no, members[j].name, members[j].email, 
          members[j].tel, members[j].registeredDate);
  }
}
  static void addMember() {
    Member member = new Member();
    
    System.out.print("번호? ");
    member.no = keyboard.nextInt();
    
    System.out.print("이름? ");
    member.name = keyboard.next();
    
    System.out.print("이메일? ");
    member.email = keyboard.next();
    
    System.out.print("암호? ");
    member.password = keyboard.next();

    System.out.print("사진? ");
    member.photo = keyboard.next();

    System.out.print("전화? ");
    member.tel = keyboard.next();

    member.registeredDate = new Date(System.currentTimeMillis()); 
    
    System.out.println("저장 되었습니다");
    members[memberIdx] = member;
    memberIdx++;
  }
  static void listLesson() {
      for (int j = 0; j < lessonIdx; j++) {
        System.out.printf("%d, %s, %s ~ %s, %d\n", 
            lessons[j].no, lessons[j].title, lessons[j].startDate, 
            lessons[j].endDate, lessons[j].totalHours);
      }
  }
  static void addLesson() {
    Lesson lesson = new Lesson();
    
    System.out.print("번호? ");
    lesson.no = keyboard.nextInt();
    
    System.out.print("수업명? ");
    lesson.title = keyboard.next();
    
    System.out.print("설명? ");
    lesson.contents = keyboard.next();
    
    System.out.print("시작일? ");
    lesson.startDate = keyboard.next();
    
    System.out.print("종료일? ");
    lesson.endDate = keyboard.next();
    
    System.out.print("총수업시간? ");
    lesson.totalHours = keyboard.nextInt();
    
    System.out.print("일수업시간? ");
    lesson.dayHours = keyboard.nextInt();
    
    System.out.println("저장하였습니다.");
    lessons[lessonIdx] = lesson;
    lessonIdx++;
  }
  //메소드 모음 끝
}  //클래스 끝
 

