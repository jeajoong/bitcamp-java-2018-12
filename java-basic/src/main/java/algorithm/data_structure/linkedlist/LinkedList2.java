package algorithm.data_structure.linkedlist;

public class LinkedList2 {
  protected Node head;
  protected Node tail;
  protected int size;

  public LinkedList2() {
    head = new Node();
    tail = head;
    size = 0;
  }
  public void add(Object value) {
    tail.value = value;
    Node node = new Node();
    tail.next = node;
    node.prev = tail;
    tail = node;
    size++;
  }
  public Object get(int index) {
    if (index < 0 || index >= size)
      return null;
    
    Node cursor = head;
    for (int i = 0 ; i < index ; i++) {
      cursor = cursor.next;
    }
    
    return cursor.value;
  }
  
  public Object[] toArray() {
    Object[] arr = new Object[size];
    
    Node cursor = head;
    for (int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }
  
  public Object set(int index, Object value) {
    if ( index < 0 || index >= size)
      return null;
    Node cursor = head;
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    Object old = cursor.value;
    cursor.value = value;
    return old;
  }
  
  public int insert(int index, Object value) {
    if ( index < 0 || index >= size)
      return -1;
    Node node = new Node(value);
    Node cursor = head;
    
    for (int i = 0; i < index; i++) {
      cursor = cursor.next; 
    }
    node.next = cursor; 
    
    
  } 
  
  public Object remove(int index) {

  
  }
  public int size() {
    return size;
  }
}
