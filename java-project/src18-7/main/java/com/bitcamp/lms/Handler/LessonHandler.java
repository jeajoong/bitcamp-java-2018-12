package com.bitcamp.lms.Handler;

import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.domain.Lesson;
import com.bitcamp.util.ArrayList;

public class LessonHandler {

  Scanner keyboard;
  ArrayList<Lesson> list;
  
  public LessonHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.list = new ArrayList<>();
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
    int no = promptLessonNo();
    int index = indexL(no); 
    if(!validate(index))
      return;
    
    Lesson lesson = list.get(index);
    
    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("설명: %s\n", lesson.getContents());
    System.out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %d\n", lesson.getDayHours());
    
  }
  
  public void updateLesson() {
    int no = promptLessonNo();
    int index = indexL(no); 
    if(!validate(index))
      return;
    
    Lesson lesson = list.get(index);
    
    try {
      Lesson temp = new Lesson();
      temp.setNo(lesson.getNo());
      
      System.out.printf("수업명(%s)? ", lesson.getTitle());
      String input= keyboard.next();
      temp.setContents(input.length() > 0? input : lesson.getContents()); // 모든 핸들러 이런식으로 바꿔보기
      
      System.out.printf("설명(%s)? ", lesson.getContents());
      input= keyboard.next();
      temp.setTitle(input.length() > 0? input : lesson.getTitle());
      
      System.out.printf("시작일(%s)? ", lesson.getStartDate());
      input= keyboard.next();
      temp.setStartDate(input.length() > 0? Date.valueOf(input) : lesson.getStartDate()); // Date형일때
      
      System.out.printf("종료일(%s)? ", lesson.getEndDate());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setEndDate(Date.valueOf(input));
      
      System.out.printf("총수업시간(%d)? ", lesson.getTotalHours());
      input= keyboard.nextLine();
      temp.setTotalHours(input.length() > 0? 
          Integer.parseInt(input) : lesson.getTotalHours());  // int형일때 
      
      System.out.printf("일수업시간(%d)? ", lesson.getDayHours());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setDayHours(Integer.parseInt(input));
      
      list.set(index, temp);
      System.out.println("수업을 변경했습니다.");
      
    } catch (Exception e) {
      System.out.println("변경 중 오류 발생!");
    }
    
  }
  public void deleteLesson() {
    int no = promptLessonNo();
    int index = indexL(no); 
    if(!validate(index))
      return;
    
    list.remove(index);
    System.out.println("수업을 삭제했습니다.");
    
  }
  
  private int indexL(int no) {
    for (int i = 0; i < list.size; i++) {
      Lesson l = list.get(i);
      if (l.getNo()==no)
        return i;
    }
    return -1;
  }
  
  ///////////////////////////////////////////////////////////
  private int promptLessonNo() {
    System.out.println("번호? ");
    return Integer.parseInt(keyboard.nextLine());
  }
    
  private boolean validate(int index) {
    if ( index == -1) {
      System.out.println("글을 찾을 수 없습니다.");
      return false;
    }
    return true;
  }
  
}
  

