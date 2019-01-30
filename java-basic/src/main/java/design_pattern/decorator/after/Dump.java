package design_pattern.decorator.after;

public class Dump extends Decorator{

  public Dump(Car car) {
    super(car);
  }
  
  @Override
  public void run() {
    // 생성자에서 받은 자동차에 덧붙인 짐내리기 기능을 실행한다.
      this.car.stop();
      this.dump();
      
      
  }
  public void dump() {
    System.out.println("짐을 내린다.");
  }
}
