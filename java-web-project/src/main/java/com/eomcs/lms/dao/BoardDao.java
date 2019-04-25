package com.eomcs.lms.dao;

import java.util.HashMap;
import java.util.List;
import com.eomcs.lms.domain.Board;

public interface BoardDao {
  int insert(Board board);
  List<Board> findAll(HashMap<String, Object> params);
  Board findByNo(int no);
  int increaseCount(int no);
  int update(Board board);
  int delete(int no);
  int countAll();
}







