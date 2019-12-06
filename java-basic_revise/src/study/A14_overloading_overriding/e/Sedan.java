package study.A14_overloading_overriding.e;

public class Sedan extends Car {
  
  int capacity; // 승차인원
  
  @Override
  public void run() {
    System.out.println("씽씽~~ 달린다!");
  }

}
