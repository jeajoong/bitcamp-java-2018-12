package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public abstract class AbstractCommand implements Command{

  @Override
  public void execute(BufferedReader in, PrintWriter out) {
    try {
      execute(new Response(in,out));
    } catch (Exception e) {
    }
  }

  public void execute(Response response) {
    
  }
}
