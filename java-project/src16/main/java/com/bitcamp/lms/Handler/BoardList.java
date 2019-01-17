package com.bitcamp.lms.Handler;

import java.util.Arrays;
import com.bitcamp.lms.domain.Board;

public class BoardList {
  static final int LENGTH = 3;
  Board[] arr = new Board[LENGTH]; //
  int size = 0;


  public Board[] toArray() { //데이터 배열을 리턴하는 toArray() 메서드를 정의한다.
    Board[] a = new Board[size];
    for (int i = 0; i < size; i++) {
      a[i] = this.arr[i];
    }
    return a;
  }

  public void add(Board board) { //데이터를 저장하는 add() 메서드를 정의한다.
    if (size == arr.length) {
      arr = Arrays.copyOf(arr, arr.length + (arr.length >> 1));
      System.out.printf("배열 증가 함: %d\n", arr.length);
    }

    arr[size++] = board;
  }
}