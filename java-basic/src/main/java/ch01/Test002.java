package ch01;

import java.util.Scanner;

//입력한 문자열중에 가운데 문자열만 출력하기
//ex) "abcde" 라면 c 출력
//       "abcd" 라면 bc 출력

public class Test002 {

  public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          String s = sc.next();
            if (s.length() > 0 && s.length() <= 100) {

              if (s.length()%2 == 1) {
                int center1 = s.length()/2;
               String c =  s.substring(center1, center1+1);
                System.out.println(c);
                
              } else {
                int center2 = s.length()/2;
                String e = s.substring(center2-1, center2+1);
                System.out.println(e);
              }
            
            } else {
              System.out.println("1이상 100이하의 숫자를 입력해주세요");
            }
          sc.close();
      }
}
