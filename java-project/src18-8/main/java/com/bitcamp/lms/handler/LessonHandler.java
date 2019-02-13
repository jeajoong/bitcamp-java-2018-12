package com.bitcamp.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.domain.Lesson;
import com.bitcamp.util.ArrayList;

public class LessonHandler {

  Scanner keyboard;
  ArrayList<Lesson> list;
  
  public LessonHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    list = new ArrayList<>();
  }
  
  public void listLesson() {
    Lesson[] lessons = list.toArray(new Lesson[0]);
    for (Lesson lesson : lessons) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          lesson.getNo(), lesson.getTitle(), 
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
    }
  }

  public void addLesson() {
    Lesson lesson = new Lesson();

    System.out.print("번호? ");
    lesson.setNo(Integer.parseInt(keyboard.nextLine()));

    System.out.print("수업명? ");
    lesson.setTitle(keyboard.nextLine());

    System.out.print("설명? ");
    lesson.setContents(keyboard.nextLine());

    System.out.print("시작일? ");
    lesson.setStartDate(Date.valueOf(keyboard.nextLine()));

    System.out.print("종료일? ");
    lesson.setEndDate(Date.valueOf(keyboard.nextLine()));

    System.out.print("총수업시간? ");
    lesson.setTotalHours(Integer.parseInt(keyboard.nextLine()));

    System.out.print("일수업시간? ");
    lesson.setDayHours(Integer.parseInt(keyboard.nextLine()));

    list.add(lesson);

    System.out.println("저장하였습니다.");
  }

  public void detailLesson() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    
    int index = indexL(no);
    if(index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }
    Lesson lesson = list.get(index);
    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("수업내용: %s\n", lesson.getContents());
    System.out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %d\n", lesson.getDayHours());
    }
  
  public int indexL(int no) {
    for (int i = 0; i < list.size; i++) {
      Lesson l = list.get(i);
      if (l.getNo() == no) 
        return i;
    }
    return -1;
  }
}