package com.bitcamp.lms.Handler;

import java.util.Arrays;

public class ArrayList<E> {
  
  static final int DEFAULT_CAPACITY = 10;
  Object[] list = new Object[DEFAULT_CAPACITY];
  int size = 0;

  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY)
      list = new Object[initialCapacity];
    else
      list = new Object[DEFAULT_CAPACITY];
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] sampleArr) {
    return (E[]) Arrays.copyOf(list, size, sampleArr.getClass()); //리스트의 크기배열에서 size만큼의 sampleArr배열을 돌려준다
    /*
    E[] arr = Arrays.copyOf(sampleArr, size);
    for (int i = 0; i < size; i++) {
      arr[i] = (E) list[i];
    }
    return arr;
    */
  }

  public void add(E obj) {
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }

    list[size++] = obj;
  }
  
  
  public E get(int index) { // 값을 꺼낼 배열의 항목 위치
    return (E) list[index];
  }
  
  
  
  
  public E set(int index, E value) { // index 값을 변경할 배열의 항목 위치
    E old = (E) list[index]; // value 해당 위치에 있는 값을 대체할 값
    list[index] = value;  
    return old; // 대체되기 전의 기존 값
  }
  
  
  
  public E remove(int index) {//삭제할 배열의 항목 위치
    E old=(E) list[index]; 
    
    
    
    
    // 리턴값: 삭제된 이전 값
    // 힌트: System.arraycopy() 참고! 
    return null;
  }
}



