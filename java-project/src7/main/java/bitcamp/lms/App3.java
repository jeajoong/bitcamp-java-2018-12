//project 06
package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    Board board = new Board();
    int i;
    for(i=0; i < 100; i++) {
      System.out.print("번호?");
      board.no[i] = sc.nextInt();
      
      System.out.print("내용?");
      board.content[i] = sc.next();
      
      board.date[i] = new Date(System.currentTimeMillis());
      
      System.out.print("계속 입력하시겠습니까?(y/n)");
      board.answer = sc.next();
      
      if(!(board.answer.equals("y"))) {
        break;
      }
      System.out.println();
    }
    
    sc.close();
    
      for (int j = 0; j <= i; j++) {  
      System.out.printf("%d, %s, %s, %d\n", board.no[j], board.content[j], board.date[j], board.views);
      }
    
  }

}
