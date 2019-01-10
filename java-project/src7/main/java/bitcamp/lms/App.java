//project 07
package bitcamp.lms;

import java.util.Scanner;

public class App {
    
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Lesson lesson = new Lesson();
    
    int i;
    for (i = 0; i < 100; i++) {
      
    System.out.print("번호?");
    lesson.no[i] = sc.nextInt();
    System.out.print("수업명?");
    lesson.title[i] = sc.next();
    System.out.print("수업내용?");
    lesson.content[i] = sc.next();
    System.out.print("시작일?");
    lesson.sdate[i] = sc.nextInt();
    System.out.print("종료일?");
    lesson.edate[i] = sc.nextInt();
    System.out.print("총수업시간?");
    lesson.totime[i] = sc.nextInt();
    System.out.print("일수업시간?");
    lesson.datime[i] = sc.nextInt();
    
    System.out.println("계속 입력하시겠습니까?(y/n)");
    String answer = sc.next();
                  
    if(!(answer.equals("y"))) {
    break; 
    }
    
    }
    sc.close();
    System.out.println();
    
    for (int j = 0; j <= i; j++) {
      System.out.printf("%d, %s, %d ~ %d, %d\n", lesson.no[j], lesson.title[j], lesson.sdate[j], lesson.edate[j],lesson.totime[j]);
      
      }
    }
  }