//부동소수점 리터럴의 크기
package ch01;

public class test04 {
  static final float f = 12.375f;
  public static void main(String[] args) {
    System.out.println(0.1f * 0.1f);
    //부동소수점을 2진수로 바꿀때 딱 떨어지지않아서 0.01값이 나오지않음..
    System.out.println((0.1f * 0.1f - 0.01f) <= Float.POSITIVE_INFINITY);
    //해결방안
    // => 예상한 값과의 오차가 부동소수점에서 인정하는 오차 이하의 값이라면 같은 것으로 취급하라!
    
    
    
    //3.14
    //4바이트 부동소수점
    //3.14F
    //3.14f (주로사용)
    //31.4e-1f
  }

}
