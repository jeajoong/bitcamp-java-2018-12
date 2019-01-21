package com.bitcamp.util;

import java.util.Arrays;

public class ArrayList<E> {
  static final int DEFAULT_CAPACITY = 10;
  Object[] list;
  public int size = 0;

  public ArrayList() {
    list  = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY)
      list = new Object[initialCapacity];
    else
      list = new Object[DEFAULT_CAPACITY];
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if (a.length < size) {
      return (E[]) Arrays.copyOf(list, size, a.getClass());
    }
    System.arraycopy(list, 0, a, 0, size);
    if (a.length > size)
      a[size] = null;
    return a;
  }

  public void add(E obj) {
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }

    list[size++] = obj;
  }
  
  public E get(int index) {
    return (E) list[index];
    }
  
  public E set(int index, E value) {
    E old = (E) list[index];
    list[index] = value;
    return old;
  }
  
  public E remove(int index) {
    E obj = (E) list[index];
    
    for (int i = index; i < size - 1; i++) // i 는 배열부터 배열 사이즈 -1까지 범위고
      list[i] = list[i + 1]; // 한칸씩 배열 위치를 땡겨준다

    size--; // 배열을 땡겼으니 빈 배열이 생겨서 그걸 매꾸기 위해 -1 

    return obj;
  
  }
}
