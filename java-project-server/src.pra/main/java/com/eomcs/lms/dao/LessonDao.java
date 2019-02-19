// 11단계: AbstractService 상속 받기
package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonDao extends AbstractDao<Lesson> {

  public LessonDao(String filepath) {
    this.filepath = filepath;
  }
  
  public void insert(Lesson lesson) {
    list.add(lesson);
  }

  public List<Lesson> findAll() {
    return list;
  }

  public Lesson findByNo(int no) {
    for (Lesson obj : list) {
      if (obj.getNo() == no) {
        return obj;
      }
    }
    return null;
  }

  public int update(Lesson lesson) {
    int index = 0;
    for (Lesson obj : list) {
      if (obj.getNo() == lesson.getNo()) {
        list.set(index, lesson);
        return 1;
      }
      index++;
    }
    return 0;
  }

  public int delete(int no) {
    int index = 0;
    for (Lesson obj : list) {
      if (obj.getNo() == no) {
        list.remove(index);
        return 1;
      }
      index++;
    }
    return 0;
  }

}






