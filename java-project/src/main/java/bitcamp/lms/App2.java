//project 07
package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    Member members = new Member();
    int i;
    
    for( i = 0; i < 100; i++) {
    
    System.out.print("번호?");
    members.no[i] = sc.nextInt();
    System.out.print("이름?");
    members.name[i] = sc.next();
    System.out.print("이메일?");
    members.email[i] = sc.next();
    System.out.print("암호?");
    members.pw[i] = sc.nextInt();
    System.out.print("사진?");
    members.file[i] = sc.next();
    System.out.print("전화?");
    members.call[i] = sc.next();
    
    members.registeredDate[i] = new Date(System.currentTimeMillis());
    //Date형 변수에 시스템 시간 넣어줌
    
    System.out.print("계속 입력하시겠습니까?(y/n)");
    String answer = sc.next();
    
    if(!(answer.equals("y"))) {
      break;
    }
    
    }
    System.out.println();
    
    for (int j = 0; j <= i; j++) {
      System.out.printf("%d, %s , %s, %s, %s\n", members.no[j],members.name[j], members.email[j], members.call[j],members.registeredDate[j]);  
    }
  }
}
