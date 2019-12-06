package study.A14_overloading_overriding.e;

public class PickupTruck extends Truck {
  
  boolean dump; 
  
  @Override
  public void run() {
    System.out.println("꽈당당~ 달린다!");
  }

}
