package study.A14_overloading_overriding.e;

public class Car {
  String model;
  int cc;
  
  public void run() {
    System.out.println("달린다!");
  }
  
  public void stop() {
    System.out.println("멈춘다!");
  }
}
