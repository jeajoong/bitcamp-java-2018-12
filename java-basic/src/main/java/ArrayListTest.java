import algorithm.date_structure.array.ArrayList;

public class ArrayListTest {
  ArrayList list = new ArrayList();
  list.add(100); // list.add(Integer.valueOf(100)) : auto - boxing
  list.add(200);
  list.add(300);
  list.add(400);
  list.add(500);
  list.add(600);
  
  System.out.println(list.size);
}
