package ch13.d;

public class A {
  private int v1 = 100;
  
  public void m1() {
    System.out.printf("A.v1 = %d\n", this.v1); // !! 주의 this에 B 인스턴스 주소가 들어있음
  }
}
