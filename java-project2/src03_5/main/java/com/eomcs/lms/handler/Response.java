package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;

// in,out뿐만 아니라 커맨드에 적용시키기 편하게 메서드를 만듦
public class Response {
  BufferedReader in;
  PrintWriter out;
  
  public Response(BufferedReader in, PrintWriter out) {
    this.in = in;
    this.out = out;
  }
  
  public void println(String message) {
    out.println(message);
    out.flush();
  }

  public String requestString(String title) throws Exception {
    out.println(title); // 서버에서 물어보는거
    out.println("!{}!"); // 클라이언트로 문자열을 보냄(클라이언트에서 조건문)
    out.flush(); // 출력
    return in.readLine(); // 클라이언트에서 보내온 값을 읽어들여옴
  }
  
  public int requestInt(String title) throws Exception {
    return Integer.parseInt(this.requestString(title));
  }
  
  public Date requestDate(String title) throws Exception {
    return Date.valueOf(this.requestString(title));
  }
  
}
