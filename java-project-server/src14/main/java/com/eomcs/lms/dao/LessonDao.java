// 데이터 처리 관련 코드를 별도의 클래스로 분리
package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Lesson;

public interface LessonDao {
  public void insert(Lesson lesson);
  public List<Lesson> findAll();
  public Lesson findByNo(int no);
  public int update(Lesson lesson);
  public int delete(int no);
}







