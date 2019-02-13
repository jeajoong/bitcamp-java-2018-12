package bitcamp.lms;

import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("번호? ");
      int no = sc.nextInt();
      
      sc.nextLine();
      System.out.print("수업명? ");
      String title = sc.nextLine();
      
      System.out.print("수업내용? ");
      String speech = sc.nextLine();
      
      System.out.print("시작일? ");
      String sdate = sc.nextLine();
      
      System.out.print("종료일? ");
      String edate = sc.nextLine();
      
      System.out.print("총수업시간? ");
      int ttime = sc.nextInt();
      
      System.out.print("일수업시간? ");
      int dtime = sc.nextInt();
      
      System.out.printf("번호: %d\n", no);
      System.out.printf("수업명: %s\n", title);
      System.out.printf("수업내용 : %s\n", speech);
      System.out.printf("시작일: %s\n", sdate);
      System.out.printf("종료일: %s\n", edate);
      System.out.printf("총수업시간: %s\n", ttime);
      System.out.printf("일수업시간: %s\n", dtime);
    }
    }