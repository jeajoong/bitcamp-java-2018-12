package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;
import sun.tools.serialver.resources.serialver;

// 스프링 IoC 컨테이너가 관리하는 객체 중에서 
// 비즈니스 로직을 담당하는 객체는 
// 특별히 그 역할을 표시하기 위해 @Component 대신에 @Service 애노테이션을 붙인다.
// 이렇게 애노테이션으로 구분해두면 나중에 애노테이션으로 객체를 찾을 수 있다.
@Service
public class PhotoBoardServiceImpl2 implements PhotoBoardService {
  
  PhotoBoardDao boardDao;
  PhotoFileDao fileDao;
  
  public PhotoBoardServiceImpl2(
      PhotoBoardDao boardDao, 
      PhotoFileDao fileDao) {
    
    this.boardDao = boardDao;
    this.fileDao = fileDao;
  }
  
  // 비지니스 객체에서 메서드 이름은 가능한 업무 용어를 사용한다.
  @Override
  public List<PhotoBoard> list(int lessonNo, String searchWord) {
    if (lessonNo <= 0 && searchWord == null) {
      return boardDao.findAll(null);
    }
    HashMap<String,Object> params = new HashMap<>(); 
    if (lessonNo > 0 ) {
      params.put("lessonNo", lessonNo);
    }
    if (searchWord != null) {
      params.put("searchWord", searchWord);
    }
    return boardDao.findAll(params);
  }
  
  @Override
  public int add(PhotoBoard board) {
    int count = boardDao.insert(board);
    
    List<PhotoFile> files = board.getFiles();
    for (PhotoFile file : files) {
      file.setPhotoBoardNo(board.getNo());
    }
    fileDao.insert(board.getFiles());
    
    return count;
  }
  

  @Override
  public PhotoBoard get(int no) { // detail기능, 조회수 늘리
  PhotoBoard board = boardDao.findByNoWithFile(no);
  
  if (board != null) {
    boardDao.increaseCount(no);
  }
  return board;
  }
  
  
  @Override
  public int update(PhotoBoard board) {
    if (board.getTitle() != null) {
      boardDao.update(board);
    }
    
    
  
  }
  
  @Override
  public int delete(int no) {
  }
}







