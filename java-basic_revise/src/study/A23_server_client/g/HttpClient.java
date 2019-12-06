// HTTP 클라이언트 만들기
package study.A23_server_client.g;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class HttpClient {
  public static void main(String[] args) {
    //HTTP 프로토콜
    // => 웹 서버와 웹브라우저 사이의 통신 규칙이다.
    // => Stateless 방식으로 동작한다.
    
    // HTTP 요청 규칙
    // ----------------------------------------------------------
    // GET [url] [프로토콜/버전] (CRLF)
    // Host: [서버주소] (CRLF)
    // (CRLF)
    // ----------------------------------------------------------
    try (Socket socket = new Socket("www.khnp.co.kr", 80);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {
      
      out.println("GET / HTTP/1.1");
      out.println("Host: www.khnp.com");
      out.println();
      out.flush();
      
      while (true) {
      System.out.println(in.nextLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
