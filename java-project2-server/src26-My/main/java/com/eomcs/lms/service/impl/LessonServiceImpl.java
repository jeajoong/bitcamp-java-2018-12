package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService{

  LessonDao lessonDao;
  
  PhotoBoardDao boardDao; // 사진 게시판 삭제?
  PhotoFileDao fileDao; // 사진 게시판
  
  public LessonServiceImpl(LessonDao lessonDao,
      PhotoBoardDao photoBoardDao,
      PhotoFileDao fileDao) {
    
  this.lessonDao = lessonDao;
  this.boardDao = photoBoardDao;
  this.fileDao = fileDao;
  }
  
  @Override
  public List<Lesson> list() {
    return lessonDao.findAll();
  }

  @Override
  public int add(Lesson lesson) {
    return lessonDao.insert(lesson);
  }

  @Override
  public Lesson get(int no) {
    Lesson lesson = lessonDao.findByNo(no);
    return lesson;
  }

  @Override
  public int update(Lesson lesson) {
    return lessonDao.update(lesson);
  }

  @Override
  @Transactional(propagation=Propagation.REQUIRED)
  // 트랜잭션을 관리해주는 Advice
  // 트랜잭션 관리를 얘가 처리해줌.... 커밋,롤백 까지 해주는데  try문이 상관없어졌다
  public int delete(int no) {
//    // 트랜잭션 동작 방식을 설정한다.
//    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//    def.setName("tx1");
//    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//    
//    // 트랜잭션을 준비한다.
//    TransactionStatus status = txManager.getTransaction(def);
//    try {
      HashMap<String,Object> params = new HashMap<>();
      params.put("lessonNo", no);
      
      List<PhotoBoard> boards = boardDao.findAll(params);
      for (PhotoBoard board : boards) {
        fileDao.deleteByPhotoBoardNo(board.getNo());
        boardDao.delete(board.getNo());
      }
      
      int count = lessonDao.delete(no);
//      txManager.commit(status);
      return count;
      
//    } catch (RuntimeException e) {
//      txManager.rollback(status);
//      throw e;
//    }
  }
}
