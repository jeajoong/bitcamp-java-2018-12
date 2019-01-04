//키보드로 입력한 값을 받기 - Int와 문자열을 섞어 읽기
package ch03;

import java.util.Scanner;

public class Test9 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.print("나이? ");
    int age = sc.nextInt();
    //nextInt()는 한 개의 토큰을 읽은 후에 줄 바꿈 코드는 읽기 전 상태로 내비둔다.
    //따라서 다음에 nextLine()을 호출하면 의도치 않게 빈 문자열을 가진 한 줄을 
    //읽는 상황이 된다.
    //nextLine() 다음에 nextLine()을 호출할 때 이런 상황이 발생한다.
    //해결방법? nextInt()를 호출한 후 남아있는 엔터코드를 읽어서 제거하라.
    sc.nextLine(); // 남아있는 빈 문자열의 한 줄(LF코드)을 읽어서 버린다.
    
    System.out.print("이름? ");
    String name = sc.nextLine();
    
    System.out.printf("%d %s \n", age, name);
    
  }
}
