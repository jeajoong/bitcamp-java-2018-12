package com.bitcamp.lms.Handler;

import java.util.Arrays;
import com.bitcamp.lms.domain.Board;

public class BoardList {
  static final int DEFAULT_CAPACITY = 10;
  Board[] list; // 
  int size = 0;

  public BoardList() { 
    list  = new Board[DEFAULT_CAPACITY];
  }

  public BoardList(int initialCapacity) { // this.list = new BoardList(20);
    if (initialCapacity > DEFAULT_CAPACITY) // initialCapacity = 20
      list = new Board[initialCapacity]; 
    else
      list = new Board[DEFAULT_CAPACITY];
  }

  public Board[] toArray() { //데이터 배열을 리턴하는 toArray() 메서드를 정의한다.
    return Arrays.copyOf(list, size); //  toArray에 배열의 리스트와 크기를 돌려준다
  }

  public void add(Board board) { //데이터를 저장하는 add() 메서드를 정의한다.
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }

    list[size++] = board;
  }
}