package study.design_patterns.decorator.after;

public class Truck extends Car {
  int weight;
  
  @Override
  public void run() {
    System.out.println("덜컬덜컹 달린다!");
  }
}
