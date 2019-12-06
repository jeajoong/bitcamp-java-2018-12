package study.A14_overloading_overriding.e;

public class Convertible extends Sedan {
  
  boolean open;
  
  @Override
  public void run() {
    System.out.println("쎙쎙~~ 달린다!");
  }

}
