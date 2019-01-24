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
    node.prev = tail;
    tail.next = node;
    tail = node;
    return;
  }
  public Object get(int index) { // (index)번호를 주고 해당 노드의 값을 반환해줌
    if (index < 0 || index >= size)
      return null;
    
    Node cursor = head;

     for (int i = 0; i < index; i++) {
       cursor = cursor.next;
      
    }
     return cursor.value;
  }
  
  public Object[] toArray() {

  }
  
  public Object set(int index, Object value) {

  }
  
  public int insert(int index, Object value) {

  } 
  
  public Object remove(int index) {

  
  }
  public int size() {
    return size;
  }
}
