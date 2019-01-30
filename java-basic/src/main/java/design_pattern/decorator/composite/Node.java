package design_pattern.decorator.composite;

public abstract class Node {
  protected String title;
  
  public String getTitle() {
    return this.title;
  }
  
  public abstract void getfileInfo();
}
