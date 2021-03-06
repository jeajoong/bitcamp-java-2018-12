// 정수 리터럴(literal)의 자릿수 표기
package study.A02_literal_console;

public class Test03 {
  public static void main(String[] args) {
    // 12,3456,7890 정수 값을 자릿수를 포함하여 표현해보라.
    // => 3자리 마다 자릿수 표기할 것
    System.out.println(1_234_567_890);
    
    // => 4자리 마다 자릿수 표기할 것
    System.out.println(12_3456_7890);
    
    // 0b01100001 2진수 자릿수를 포함하여 포현하라.
    // => 4비트 마다 자릿수 표기할 것
    System.out.println(0b0110_0001);
    
  }
}

/*
# 정수 리터럴의 자릿수
- 긴 숫자를 읽기 쉽도록 _ 기호를 이용하여 자릿수를 표기할 수 있다.

12345678 = 12_345_678 = 1_2_3_4_5678
0x12_3f_87_42 = 0x123f_8742
0b0110_0001


 */















