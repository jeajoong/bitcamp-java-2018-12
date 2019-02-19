// 데이터 처리 관련 코드를 별도의 클래스로 분리
package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl extends AbstractDao<Board> implements BoardDao {

  public BoardDaoImpl(String filepath) {
    this.filepath = filepath;
  }

  public void insert(Board board) { // BoardDao에서 insert는 오류를 던지지 않는다
    list.add(board);                                // 하지만 던져야 되는 상황이라면...
    try {                                                     // try를 만들어서 catch에 Runtime오류를 잡아준다
      this.saveData();
    } catch (Exception e) {
    throw new RuntimeException(e);
    }
  }

  public List<Board> findAll() {
    return list;
  }

  public Board findByNo(int no) {
    for (Board obj : list) {
      if (obj.getNo() == no) {
        return obj;
      }
    }
    return null;
  }

  public int update(Board board) {
    int index = 0;
    for (Board obj : list) {
      if (obj.getNo() == board.getNo()) {
        list.set(index, board);
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
    for (Board obj : list) {
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




