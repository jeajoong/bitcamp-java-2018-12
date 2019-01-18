//13번 -> 14번 각 Handler에 기본 생성자 대신에 파라미터로 keyboard를 받는 생성자를 추가한다
package com.bitcamp.lms;

import java.util.Scanner;
import com.bitcamp.lms.Handler.*;

public class App {

    public static Scanner keyboard = new Scanner(System.in);
    public final static int LENGTH = 10;
    
    public static void main(String[] args) {
      LessonHandler lH = new LessonHandler(keyboard);
      MemberHandler mH = new MemberHandler(keyboard);
      BoardHandler bH1 = new BoardHandler(keyboard);
      BoardHandler bH2 = new BoardHandler(keyboard);
      
    while (true) {
      System.out.print("명령> ");
      String command = keyboard.nextLine().toLowerCase();

      if (command.equals("/lesson/add")) {
        lH.addlesson();
      } else if (command.equals("/lesson/list")) {
        lH.listlesson();
      } else if (command.equals("/member/add")) {
        mH.addmember();
      } else if (command.equals("/member/list")) {
        mH.listmamber();
      } else if (command.equals("/board/add")) {
        bH1.addboard();
      } else if (command.equals("/board/list")) {
        bH1.listborad();
      } else if (command.equals("/board2/add")) {
        bH2.addboard();
      } else if (command.equals("/board2/list")) {
        bH2.listborad();
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
