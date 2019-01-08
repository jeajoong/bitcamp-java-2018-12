//project 06
package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int[] no = new int[100];
    String[] name = new String[100];
    String[] email = new String[100];
    int[] pw = new int[100];
    String[] file = new String[100];
    String[] call = new String[100];
    Date[] registeredDate = new Date[100]; // Date형 변수 배열 선언
    
    int i;
    
    for( i = 0; i < 100; i++) {
    
    System.out.print("번호?");
    no[i] = sc.nextInt();
    System.out.print("이름?");
    name[i] = sc.next();
    System.out.print("이메일?");
    email[i] = sc.next();
    System.out.print("암호?");
    pw[i] = sc.nextInt();
    System.out.print("사진?");
    file[i] = sc.next();
    System.out.print("전화?");
    call[i] = sc.next();
    
    registeredDate[i] = new Date(System.currentTimeMillis());
    //Date형 변수에 시스템 시간 넣어줌
    
    System.out.print("계속 입력하시겠습니까?(y/n)");
    String answer = sc.next();
    
    if(!(answer.equals("y"))) {
      break;
    }
    
    }
    System.out.println();
    
    for (int j = 0; j <= i; j++) {
      System.out.printf("%d, %s , %s, %s, %s\n", no[j],name[j], email[j], call[j],registeredDate[j]);  
    }
  }
}
