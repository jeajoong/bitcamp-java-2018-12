//키보드로 입력한 값을 받기 - 토큰 단위로 문자열 읽기
package ch03;

import java.util.Scanner;

public class Test10 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.print("나이? ");
    int age = sc.nextInt();
    
    System.out.print("이름? ");
    String name = sc.next();
    //next()는 토큰 단위로 문자열을 읽는다.
    //nextLine()을 호출한 후 엔터코드가 남아있다 하더라도
    //next()는 공백을 버리기 때문에 영향을 받지 않는다.
    
    System.out.printf("%d %s \n", age, name);
    
  }
}
