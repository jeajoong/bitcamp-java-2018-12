//1. 데이터 관리 객체를 사용하는 규칙을 정의하기 

//핸들러 수정하
package com.bitcamp.util;

public interface List<E> {
  Object[] toArray();
  <T> T[] toArray(T[] a);
  void add(E obj);
  E get(int index);
  E set(int index, E obj);기
  E remove(int index);
  int size();
}
