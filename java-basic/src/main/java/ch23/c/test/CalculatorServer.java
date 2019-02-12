package ch23.c.test; //5까지. 주말에 기능, 클래스 분리 해보기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
  public static void main(String[] args) {
    
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행중...");
      
      while (true) {
        Socket socket = null;
        BufferedReader in = null;
        PrintStream out = null;
        
      try {  socket = serverSocket.accept();
          out = new PrintStream(socket.getOutputStream());
           in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
           
           while (true) { // 클라이언트에서 quit하기 전까지 서버 유지하기위해서
             String request = in.readLine();  // 클라이언트 또한 requset객체 만들어서 보내줘야 함 
           if (request.equalsIgnoreCase("quit")) {
             out.println("안녕하 가세요!");
             out.flush();
             break;
           }
           
        String[] input = in.readLine().split(" ");
        
        int a = Integer.parseInt(input[0]);
        String op = input[1];
        int b = Integer.parseInt(input[2]);
        int result = 0;
        
        switch (op) {
          case "+" : result = a + b; break;
          case "-" : result = a - b; break;
          case "*" : result = a* b; break;
          case "/" : result = a / b; break;
          case "%" : result = a % b; break;
          default :
            out.printf("%s 연산자를 지원하지 않습니다.\n", op);
            out.flush();
            continue;
        }
        
        out.printf("결과는 %d 입니다.\n", result);
        out.flush();
      
           } // 안쪽 while
      } catch (Exception e) { //연산자를 잘못 입력했을때 이쪽으로 예외처리 
        try {  // 아무것도 안적어 줬다가 처리를 하긴 하는데 또? 왜 try로 처리 하는건지...
          out.println("식의 형식이 잘못되었습니다.");
          out.flush();
        } catch (Exception e2) {}
        
      } finally {
        try {in.close();} catch (Exception e) {}
        try {out.close();} catch (Exception e) {}
        try {socket.close();} catch (Exception e) {}
      }
      } // while
    } catch (Exception e) {}
  }
}
