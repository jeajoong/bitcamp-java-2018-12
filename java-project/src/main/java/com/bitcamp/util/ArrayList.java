package com.bitcamp.util;

import java.util.Arrays;

public class ArrayList<E> {
  
  static final int DEFAULT_CAPACITY = 10;
  Object[] list;
  public int size = 0;
  
  public ArrayList() {
    list = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY)
      list = new Object[initialCapacity];
    else
      list = new Object[DEFAULT_CAPACITY];
  }

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
  
  
  public E get(int index) {  // 배열의 위치 값을 돌려줌 (한 배열의 값을?)
    if (index < 0 || index >= size)
    return null;
  
    return (E) list[index];
  }
  
  // set(이전 값, 새로운 값)  이전값을 새로운값으로 바꿔줌
  public E set(int index, E value) {
    if (index < 0 || index >= size)
      return null;
    
    E old =  (E) list[index];
    list[index] = value;
    return old;
  }
  
  
  public E remove(int index) {//삭제할 배열의 항목 위치
    if (index < 0 || index >= size)
    return null;
    
    E obj = (E) list[index];
    
    for (int i = index; i < size -1; i++) {
      list[i] = list[i + 1];
      
      size--;
    }
    return obj;
  }
}



