package ch23.c.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {

  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      try (Socket socket = new Socket();
          PrintWriter out = new PrintWriter(socket.getOutputStream());
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()))
          ) {
        
        String[] input = in.readLine().split(" ");  //.split()은 문자열을 분할하는 메서드
        
        int a = Integer.parseInt(input[0]);
        String op = input[1];
        int b = Integer.parseInt(input[2]);
        
        
      } catch (Exception e) {

      }

    } catch (Exception e) {
    }

  }

}
