package com.eomcs.lms.service.impl;

import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.PhotoBoardService;

public class PhotoBoardServiceImpl implements PhotoBoardService{

  PhotoBoardDao boardDao;

  public PhotoBoardServiceImpl(PhotoBoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public List<PhotoBoard> list() {
    return boardDao.findAll(null);
  }

  @Override
  public int add(PhotoBoard board) {
    return boardDao.insert(board);
  }

  @Override
  public PhotoBoard get(int no) {
    return null;
  }

  @Override
  public int update(PhotoBoard board) {
    return boardDao.update(board);
  }

  @Override
  public int delete(int no) {
    return boardDao.delete(no);
  }

}
