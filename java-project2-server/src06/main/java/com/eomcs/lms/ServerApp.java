// 6단계: 사진 게시판 추가 
// 작업
// 1) PhotoBoard 도메인 클래스 정의
// 2) PhotoBoardDao와 PhotoBoardDaoImpl 정의
//      - findAll() 메서드 추가
// 3) PhotoBoardListCommand 추가
// 4) ApplicationInitializer에 PhotoBoardListCommand를 등록한다.
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.handler.Command;

public class ServerApp {

  // ApplicationContextListener(옵저버) 목록을 보관할 객체
  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void service() throws Exception {

    try (ServerSocket ss = new ServerSocket(8888)) {
      
      // App에서 사용할 객체를 보관하는 저장소
      HashMap<String,Object> context = new HashMap<>();

      // 애플리케이션을 시작할 때, 등록된 리스너에게 알려준다.
      for (ApplicationContextListener listener : listeners) {
        listener.contextInitialized(context);
      }

      System.out.println("서버 실행 중...");
      
      while (true) {

        try (Socket socket = ss.accept();
            BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream())) {

          // 클라이언트의 요청 읽기
          String request = in.readLine();
          
          if (request.equalsIgnoreCase("stop")) {
            System.out.println("종료합니다.");
            break;
          }
          
          // 클라이언트에게 응답하기
          //사용자가 입력한 값이 키로 적용되어 키에 맞는 속성값을 얻어오는 뜻임..
          // Command의 객체로 넘겨받는건 xxxCommand명령어들의 형태이기때문에
          Command commandHandler = (Command) context.get(request);
          
          if (commandHandler == null) {
            out.println("실행할 수 없는 명령입니다.");
            out.println("!end!");
            out.flush();
            continue;
          }
          
          commandHandler.execute(in, out);
          //AbstractCommand 에서 execute(BufferedReader in, PrintWriter out)을 재정의
          //new Response(in, out) -> 커맨드 명령어에서 in과 out을 주고  문자열 출력하는 것을 묶어 각 커맨드에서 편하게 처리한다
          
          out.println("!end!");
          out.flush();

        } catch (Exception e) {
          System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
          e.printStackTrace();
        } // try(Socket)
        
      } // while

      // 애플리케이션을 종료할 때, 등록된 리스너에게 알려준다.
      for (ApplicationContextListener listener : listeners) {
        listener.contextDestroyed(context);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } // try(ServerSocket)

  }
  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();

    // App이 실행되거나 종료될 때 보고를 받을 옵저버를 등록한다.
    app.addApplicationContextListener(new ApplicationInitializer());

    // App 을 실행한다.
    app.service();
  }
}





