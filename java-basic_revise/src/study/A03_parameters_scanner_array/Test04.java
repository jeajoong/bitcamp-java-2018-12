//l-value 와 r-value
package study.A03_parameters_scanner_array;

public class Test04 {
  public static void main(String[] args) {
    int a = 100;
    int b = a;
    System.out.printf("%d, %d\n", a, b);
    
    a = 200; // a값을 다시 선언하였고 b는 그 이전에 a값으로 선언했기에 b는 100
    System.out.printf("%d, %d\n", a, b);
  }
}
/*
 # l-value와 r-value
 l-value(left value) : = 연산자 왼쪽 편을 가리키는 용어
 r-value(Right value) : = 연산자 오른쪽 편을 가리키는 용어
 
 l-value는 반드시 메모리여야 한다.
 r-value는 리터럴,변수 가능하다. 의미? 변수의 값을 왼쪽 변수에 저장하라는 의미다.
*/
