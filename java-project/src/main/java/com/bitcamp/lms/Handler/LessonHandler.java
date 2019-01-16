package com.bitcamp.lms.Handler;

import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.App;
import com.bitcamp.lms.domain.*;

public class LessonHandler {
  
  Scanner keyboard;
  LessonList list;
  
  public LessonHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    list = new LessonList();
  }
  
  
  public void addLesson() {

    Lesson lesson = new Lesson(); 
  
  System.out.print("번호? ");
  lesson.setNo(Integer.parseInt(this.keyboard.nextLine()));

  System.out.print("수업명? ");
  lesson.setTitle(this.keyboard.nextLine());

  System.out.print("설명? ");
  lesson.setContents(this.keyboard.nextLine());

  System.out.print("시작일? ");
  lesson.setStartDate(Date.valueOf(this.keyboard.nextLine()));

  System.out.print("종료일? ");
  lesson.setEndDate(Date.valueOf(this.keyboard.nextLine()));

  System.out.print("총수업시간? ");
  lesson.setTotalHours(Integer.parseInt(this.keyboard.nextLine()));

  System.out.print("일수업시간? ");
  lesson.setDayHours(Integer.parseInt(this.keyboard.nextLine()));

  // i 번째 배열에 수업 정보를 담고 있는 Lesson 객체(의 주소)를 보관한다.
  list.add(lesson);

  System.out.println("저장하였습니다.");
  
}

  public void listLesson() {
    Lesson[] lessons = list.toArray();
    for (Lesson lesson : lessons) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          lesson.getNo(), lesson.getTitle(), 
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
    }
  }

}
