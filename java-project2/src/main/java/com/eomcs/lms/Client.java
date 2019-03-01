// 클라이언트 만들기
// 차근차근 다른거 신경안쓰고 서버와 연결부터
// 2 서버와 통신을 하기위해 문자열을 정하고 통신하기
//
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Client {
  
  Scanner keyboard = new Scanner(System.in);
  Stack<String> commandHistory = new Stack<>();
  Queue<String> commandHistory2 = new LinkedList<>();
  // 조심!! Queue는 인터페이스 인터페이스로 배열 x 그걸 상속받은 클래스인 List로 만듬
  
    public void service() throws Exception {
      
      while (true) {
        String command = prompt();
        if(command.equals("quit")) {
          System.out.println("클라이언트를 종료합니다.");
          return;
        }
        
        commandHistory.push(command);
        commandHistory2.offer(command);
        
        if(command.equals("history")) {
          printCommandHistory();
          continue;
        }else if(command.equals("history2")) {
            printCommandHistory2();
            continue;
          }

        try(Socket socket = new Socket("localhost", 8888);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(
           new  InputStreamReader(socket.getInputStream())) ) {
      
          out.println(command);
          out.flush();
          
          if(command.equals("stop")) {
            System.out.println("서버를 종료합니다.");
            break;
          }
          // 서버응답
          while (true) {
            String response = in.readLine();
            if(response.equals("!end!")) { 
              break;
            
          } else if (response.equals("!{}!")) { // 서버에서 이 값을 보내왔다면
            String input = keyboard.nextLine(); // 새로운 값 입력후 서버로 보내서 서버에서 데이터 처리
            out.println(input);
            out.flush();
            
          } else {
          System.out.println(response);
          }
        }
    } catch (Exception e) {
      System.out.println("서버에 요청하는 중 오류 발생!");
      e.printStackTrace();
    }
    }
      
  }
  private void printCommandHistory() {
  Stack<String> temp = (Stack<String>) commandHistory.clone();  
  while (temp.size() > 0) {
    System.out.println(temp.pop());
  }
  }
  private void printCommandHistory2() {
Queue<String> temp = (Queue<String>) ((LinkedList<String>) commandHistory2).clone();
  while (temp.size() > 0) {
    System.out.println(temp.poll());
  }
  }
  private String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }

  public static void main(String[] args) throws Exception {
    Client app = new Client();
    app.service();
  }
  
  }

