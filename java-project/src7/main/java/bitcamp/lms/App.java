package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);
    
    final int LENGTH = 10;
    
    Lesson[] lessons = new Lesson[LENGTH];
    
    
    int i = 0;
    while (true) { // true면 참으로 계속실행
      System.out.println("명령> ");
      String order = keyboard.nextLine();
      
      if (order.equals("/lesson/add")) {
      Lesson lesson = new Lesson();
      
      System.out.print("번호? ");
      lesson.no = Integer.parseInt(keyboard.nextLine());
      
      System.out.print("수업명? ");
      lesson.title = keyboard.nextLine();
      
      System.out.print("설명? ");
      lesson.contents = keyboard.nextLine();
      
      System.out.print("시작일? ");
      lesson.startDate = Date.valueOf(keyboard.nextLine());
      
      System.out.print("종료일? ");
      lesson.endDate = Date.valueOf(keyboard.nextLine());
      
      System.out.print("총수업시간? ");
      lesson.totalHours = Integer.parseInt(keyboard.nextLine());
      
      System.out.print("일수업시간? ");
      lesson.dayHours = Integer.parseInt(keyboard.nextLine());
      
      // i 번째 배열에 수업 정보를 담고 있는 Lesson 객체(의 주소)를 보관한다.
      lessons[i] = lesson;
      i++;
      
      System.out.println("저장하였습니다.");
      }
    } else if (order.equlas("/lesson/list")) {
      for (int j = 0; j < i; j++) {
        System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
            lessons[j].no, lessons[j].title, lessons[j].startDate, 
            lessons[j].endDate, lessons[j].totalHours);
      }
      
    }
    
  }
}
