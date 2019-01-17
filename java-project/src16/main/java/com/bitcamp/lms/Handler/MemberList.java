package com.bitcamp.lms.Handler;

import java.util.Arrays;
import com.bitcamp.lms.domain.Member;

public class MemberList {
  
  public final int LENGTH = 3;
  Member[] arr = new Member[LENGTH];
  int size = 0;
  
  //배열작업 하는곳 // a 멤버 배열에, LENGTH의 길이를 가지고 있는 arr 주소를 넣어준다 size를 늘려주면서
  public Member[] toArray() { // toArray는 멤버 배열값을 돌려받는다
    Member[] a = new Member[size]; // a라는 멤버주소 변수에 size배열 크기의 멤버배열주소를 넣어준다
    for (int i = 0; i < size; i++) { // 그리고 사이즈를 늘려가며
      a[i] = this.arr[i]; // arr 배열에
    }
    return a;
  }
  
  public void add(Member member) {
  if (size == arr.length) {
    arr = Arrays.copyOf(arr, arr.length + (arr.length >> 1));
    
  }
  arr[size++] = member;
  
  }
}
