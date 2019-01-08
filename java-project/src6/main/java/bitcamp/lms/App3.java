//project 06
package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int[] no = new int[100];
    String[] content = new String[100];
    Date[] date = new Date[100];
    int views = 0;
    String answer;
    
    int i;
    for(i=0; i < 100; i++) {
      System.out.print("번호?");
      no[i] = sc.nextInt();
      
      System.out.print("내용?");
      content[i] = sc.next();
      
      date[i] = new Date(System.currentTimeMillis());
      
      System.out.print("계속 입력하시겠습니까?(y/n)");
      answer = sc.next();
      
      if(!(answer.equals("y"))) {
        break;
      }
      System.out.println();
    }
    
    sc.close();
    
      for (int j = 0; j <= i; j++) {  
      System.out.printf("%d, %s, %s, %d\n", no[j], content[j], date[j], views);
      }
    
  }

}
