package com.eomcs.lms.handler;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonAddCommand extends AbstractCommand {
//서버쪽에서 스캐너 객체가 필요없다.. 단순히 클라이언트로 값을 받아오면 되기때문에.
  LessonDao lessonDao;
  
  public LessonAddCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
    Lesson lesson = new Lesson();

    lesson.setTitle(response.requestString("수업명? "));
    lesson.setContents(response.requestString("설명? "));
    lesson.setStartDate(response.requestDate("시작일? "));
    lesson.setEndDate(response.requestDate("종료일?"));
    lesson.setTotalHours(response.requestInt("총 수업 시간? "));
    lesson.setDayHours(response.requestInt("일 수업 시간? "));

    lessonDao.insert(lesson);
    response.println("저장하였습니다.");
    
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
  
}
