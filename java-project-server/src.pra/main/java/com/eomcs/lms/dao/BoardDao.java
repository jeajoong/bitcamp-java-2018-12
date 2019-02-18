// 11단계: AbstractService 상속 받기
package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardDao extends AbstractDao<Board> {

  public BoardDao(String filepath) {
    this.filepath = filepath;
  }

  private void insert(Board board) {
    list.add(board);
  }

  private List<Board> findAll() {
    return list;
  }

  private Board findByNo(int no) {
    for (Board obj : list) {
      if (obj.getNo() == no) {
        return obj;
      }
    }
    return null;
  }

  private int update(Board board) {
    int index = 0;
    for (Board obj : list) {
      if (obj.getNo() == board.getNo()) {
        list.set(index, board);
        return 1;
      }
      index++;
    }   
    return 0;
  }

  private int delete(int no) {
    int index = 0;
    for (Board obj : list) {
      if (obj.getNo() == no) {
        list.remove(index);
        return 1;
      }
      index++;
    }
    return 0;
  }

}







