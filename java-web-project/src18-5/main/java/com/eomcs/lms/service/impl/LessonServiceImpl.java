package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.LessonService;

// 스프링 IoC 컨테이너가 관리하는 객체 중에서 
// 비즈니스 로직을 담당하는 객체는 
// 특별히 그 역할을 표시하기 위해 @Component 대신에 @Service 애노테이션을 붙인다.
// 이렇게 애노테이션으로 구분해두면 나중에 애노테이션으로 객체를 찾을 수 있다.
@Service
public class LessonServiceImpl implements LessonService {
  
  LessonDao lessonDao;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  
  public LessonServiceImpl(
      LessonDao lessonDao,
      PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao) {
    
    this.lessonDao = lessonDao;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }
  
  // 비지니스 객체에서 메서드 이름은 가능한 업무 용어를 사용한다.
  @Override
  public List<Lesson> list(int pageNo, int pageSize) {
    HashMap<String,Object> params = new HashMap<>();
    params.put("size", pageSize);
    
 // 0이 pageNo으로 들어오면 rowNo가 -값이 되기 때문에 오류가 난다.
 // 0을 받았을때 처리 해주어야 한다.
    if ((pageNo - 1) < 0) {   
      params.put("rowNo", 0);
    } else {
    params.put("rowNo", (pageNo-1) * pageSize);
    }
    
    return lessonDao.findAll(params);
  }
  
  @Override
  public int add(Lesson lesson) {
    return lessonDao.insert(lesson);
  }
  
  @Override
  public Lesson get(int no) {
    return lessonDao.findByNo(no);
  }
  
  @Override
  public int update(Lesson lesson) {
    return lessonDao.update(lesson);
  }
  
  @Override
  public int delete(int no) {
    HashMap<String,Object> params = new HashMap<>();
    params.put("lessonNo", no);
    
    List<PhotoBoard> boards = photoBoardDao.findAll(params);
    for (PhotoBoard board : boards) {
      photoFileDao.deleteByPhotoBoardNo(board.getNo());
      photoBoardDao.delete(board.getNo());
    }
    
    int count = lessonDao.delete(no);
    
    
    return count;
  }

  @Override
  public int size() {
    return lessonDao.countAll();
  }

}







