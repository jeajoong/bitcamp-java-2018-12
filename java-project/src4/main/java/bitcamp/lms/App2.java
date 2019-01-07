package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("번호? ");
    int no = sc.nextInt();
    
    sc.nextLine();
    System.out.print("이름? ");
    String name = sc.nextLine();
    
    System.out.print("이메일? ");
    String email = sc.next();
    
    System.out.print("암호? ");
    int pw = sc.nextInt();
    
    System.out.print("사진? ");
    String picture = sc.next();
    
    System.out.print("전화? ");
    String call = sc.next();
    
    Date registerDate = new Date(System.currentTimeMillis());
    
    sc.close();
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("이메일: %s\n", email);
    System.out.printf("암호: %d\n", pw);
    System.out.printf("사진: %s\n", picture);
    System.out.printf("전화: %s\n", call);
    System.out.printf("가입일: %s\n", registerDate);
  }
}
