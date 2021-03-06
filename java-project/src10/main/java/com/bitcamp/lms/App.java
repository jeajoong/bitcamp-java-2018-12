//9번 -> 10번 도전 //클래스로 메서드를 분류하기
package com.bitcamp.lms;

import java.util.Scanner;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  
  static final int LENGTH = 10;
  
  public static void main(String[] args) {
    
    while (true) {
      System.out.print("명령> ");
      String order = keyboard.next();
      
      if (order.equals("/lesson/add")) {
        LessonHandler.addLesson();
      } else if (order.equals("/lesson/list")) {
        LessonHandler.listLesson();
      } else if (order.equals("/member/add")) {
        MemberHandler.addMember();
      } else if (order.equals("/member/list")) {
        MemberHandler.listMember();
      } else if (order.equals("/board/add")) {
        BoardHandler.addboard();
      } else if (order.equals("/board/list")) {
        BoardHandler.listboard();
      } else if (order.equals("quit")) {
        System.out.println("안녕!");
        keyboard.close();
        break;
      } else
        System.out.println("실행할 수 없는 명령입니다.");
    }//while 끝
  }//메인 끝
}//클래스 끝
  
