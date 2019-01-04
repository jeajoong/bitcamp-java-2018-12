//키보드로 입력한 값을 받기
package ch03;

import java.util.Scanner;

public class Test7 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("이름을 입력하세요. :");
    String name = sc.next();
    
    System.out.println("나이를 입력하세요. :");
    String age = sc.next();
   
    System.out.printf("%s(%s) \n", name, age);
  }
}
