package com.bitcamp.lms.Handler;

import java.sql.Date;
import com.bitcamp.lms.App;
import com.bitcamp.lms.domain.*;

public class LessonHandler {
  
  Lesson[] lessons = new Lesson[App.LENGTH];
  int lessonIdx = 0;
  
  public void addlesson() {

    Lesson lesson = new Lesson(); 
  
  System.out.print("번호? ");
  lesson.no = Integer.parseInt(App.keyboard.nextLine());

  System.out.print("수업명? ");
  lesson.title = App.keyboard.nextLine();

  System.out.print("설명? ");
  lesson.contents = App.keyboard.nextLine();

  System.out.print("시작일? ");
  lesson.startDate = Date.valueOf(App.keyboard.nextLine());

  System.out.print("종료일? ");
  lesson.endDate = Date.valueOf(App.keyboard.nextLine());

  System.out.print("총수업시간? ");
  lesson.totalHours = Integer.parseInt(App.keyboard.nextLine());

  System.out.print("일수업시간? ");
  lesson.dayHours = Integer.parseInt(App.keyboard.nextLine());

  // i 번째 배열에 수업 정보를 담고 있는 Lesson 객체(의 주소)를 보관한다.
  lessons[lessonIdx] = lesson;
  lessonIdx++;

  System.out.println("저장하였습니다.");
  
}

public void listlesson() {
  for (int j = 0; j < lessonIdx; j++) {
    System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
        lessons[j].no, lessons[j].title, lessons[j].startDate, 
        lessons[j].endDate, lessons[j].totalHours);
  }
}

}
