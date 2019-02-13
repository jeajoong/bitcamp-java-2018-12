package com.bitcamp.lms;

public class LessonHandler {
  int no;
  String title;
  String contents;
  String startDate;
  String endDate;
  int totalHours;
  int dayHours;



static LessonHandler[] lessons = new LessonHandler[App.LENGTH];
static int lessonIdx = 0;

static void addLesson() {
LessonHandler lesson = new LessonHandler();

System.out.print("번호? ");
lesson.no = App.keyboard.nextInt();

System.out.print("수업명? ");
lesson.title = App.keyboard.next();

System.out.print("설명? ");
lesson.contents = App.keyboard.next();

System.out.print("시작일? ");
lesson.startDate = App.keyboard.next();

System.out.print("종료일? ");
lesson.endDate = App.keyboard.next();

System.out.print("총수업시간? ");
lesson.totalHours = App.keyboard.nextInt();

System.out.print("일수업시간? ");
lesson.dayHours = App.keyboard.nextInt();

System.out.println("저장하였습니다.");
lessons[lessonIdx] = lesson;
lessonIdx++;
}


static void listLesson() {
  for (int j = 0; j < lessonIdx; j++) {
    System.out.printf("%d, %s, %s ~ %s, %d\n", 
        lessons[j].no, lessons[j].title, lessons[j].startDate, 
        lessons[j].endDate, lessons[j].totalHours);
  }
  
}
}