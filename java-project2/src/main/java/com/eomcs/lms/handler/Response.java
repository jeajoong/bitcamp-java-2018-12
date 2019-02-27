package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Response {
  BufferedReader in;
  PrintWriter out;
  
  public Response(BufferedReader in, PrintWriter out) {
    this.in = in;
    this.out = out;
  }

}
