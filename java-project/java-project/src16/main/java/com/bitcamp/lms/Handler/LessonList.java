package com.bitcamp.lms.Handler;

import java.util.Arrays;
import com.bitcamp.lms.domain.Lesson;

public class LessonList {

  public final int LENGTH = 3;
  Lesson[] arr = new Lesson[LENGTH];
  int size = 0;
  
  
  public Lesson[] toArray() { //배열작업 하는곳 // a 레슨 배열에, LENGTH의 길이를 가지고 있는 arr 주소를 넣어준다 size를 늘려주면서
    Lesson[] a = new Lesson[size];
    for (int i = 0; i < size; i++) {
      a[i] = this.arr[i];
    }
    return a;
  }
  
  public void add(Lesson lesson) { // 그리고 여기서 사이즈가 arr배열 크기와 같아지면 배열을 늘려주고 저장한다
    if (size == arr.length) {
      arr = Arrays.copyOf(arr, arr.length + (arr.length >> 1));
      System.out.printf("배열 증가 함: %d\n", arr.length);
    }
    arr[size++] = lesson;
  }
  
}
