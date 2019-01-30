package design_pattern.decorator.composite;

import java.util.ArrayList;

public class Directory extends Node{

  ArrayList<Node> childs = new ArrayList<>();
  
  public Directory(String tilte) {
    this.title = tilte;
  }
  
  @Override
  public void getfileInfo() {
    System.out.printf("디렉토리명: %s\n", this.title);
  }
  
  public void add(Node node) {
    childs.add(node);
  }
  
  public Node remove(int index) {
    return childs.remove(index);
  }
  public Node get(int index) {
    return childs.get(index);
  }
}
