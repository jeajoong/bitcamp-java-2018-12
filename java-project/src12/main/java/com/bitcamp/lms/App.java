//11번 -> 12번
package com.bitcamp.lms;

import java.util.Scanner;
import com.bitcamp.lms.Handler.*;

public class App {

    public static Scanner keyboard = new Scanner(System.in);
    public final static int LENGTH = 10;
    
    public static void main(String[] args) {

    while (true) { 
      System.out.print("명령> ");
      String command = keyboard.nextLine().toLowerCase();

      if (command.equals("/lesson/add")) {
        LessonHandler.addlesson();
      } else if (command.equals("/lesson/list")) {
        LessonHandler.listlesson();
      } else if (command.equals("/member/add")) {
        MemberHandler.addmember();
      } else if (command.equals("/member/list")) {
        MemberHandler.listmamber();
      } else if (command.equals("/board/add")) {
        BoardHandler.addboard();
      } else if (command.equals("/board/list")) {
        BoardHandler.listborad();
      } else if (command.equals("/board2/add")) {
        BoardHandler2.addboard();
      } else if (command.equals("/board2/list")) {
        BoardHandler2.listborad();
      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println(); // 결과 출력 후 빈 줄 출력
    }
    keyboard.close();
  }
    
}
