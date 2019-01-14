//project 06
package bitcamp.lms;

import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      int[] no = new int[100];
      String[] title = new String[100];
      String[] content = new String[100];
      int[] sdate = new int[100];
      int[] edate = new int[100];
      int[] totime = new int[100];
      int[] datime = new int[100];
      
      int i;
      for (i = 0; i < 100; i++) {
        
      System.out.print("번호?");
      no[i] = sc.nextInt();
      System.out.print("수업명?");
      title[i] = sc.next();
      System.out.print("수업내용?");
      content[i] = sc.next();
      System.out.print("시작일?");
      sdate[i] = sc.nextInt();
      System.out.print("종료일?");
      edate[i] = sc.nextInt();
      System.out.print("총수업시간?");
      totime[i] = sc.nextInt();
      System.out.print("일수업시간?");
      datime[i] = sc.nextInt();
      
      System.out.println("계속 입력하시겠습니까?(y/n)");
      String answer = sc.next();
                    
      if(!(answer.equals("y"))) {
      break; 
      }        
      
      }
      sc.close();
      System.out.println();
      
      
      for (int j = 0; j <= i; j++) {
        System.out.printf("%d, %s, %d ~ %d, %d\n", no[j], title[j], sdate[j], edate[j],totime[j]);
        
        }
      }
    }

