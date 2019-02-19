// 데이터 처리 관련 코드를 별도의 클래스로 분리
package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl extends AbstractDao<Lesson> implements LessonDao {

  public LessonDaoImpl(String filepath) {
    this.filepath = filepath;
  }
  
  public void insert(Lesson lesson){
    list.add(lesson);
    try {
      this.saveData();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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

  public int update(Lesson lesson){
    int index = 0;
    for (Lesson obj : list) {
      if (obj.getNo() == lesson.getNo()) {
        list.set(index, lesson);
        try {
          this.saveData();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
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
        try {
          this.saveData();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        return 1;
      }
      index++;
    }
    return 0;
  }

}







