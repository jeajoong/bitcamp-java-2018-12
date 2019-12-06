package study.algorithm.stack2;

public class Stack<E> {
  
  public static final int DEFAULT_SIZE = 5;
  
  Object[] list;
  int size;
  
  public Stack() { // Stack을 호출하면 DEFAULT_SIZE 크기 list에 담음
    list = new Object[DEFAULT_SIZE];
  }
  
  public void push(E value) {
    if (size == list.length) {
      Object[] arr = new Object[list.length + (list.length >> 1)];
      for (int i = 0; i < list.length; i++) {
        arr[i] = list[i];
      }
      list = arr;
    }
    
    list[size++] = value;
  }
  
  @SuppressWarnings("unchecked")
  public E pop() {
    if (size == 0)
      return null;
    
    return (E) list[--size];
  }
  
  public boolean empty() {
    return size == 0;
  }
  
  public int size() {
    return this.size;
  }
}
