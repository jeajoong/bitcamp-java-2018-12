// 21단계: 자바 설정 방식을 이용하여 IoC 컨테이너를 설정하기
// => IoC 컨테이너에게 필요한 것들을 자바 코드로 설정한다. 
// 
// 작업1 - 팩토리 메서드를 통해 객체 생성하기 
// 1) AppConfig 정의
//    => IoC 컨테이너가 보관할 객체를 생성하는 메서드 정의
//    => IoC 컨테이너가 자동으로 생성하지 않는 객체를 메서드에서 리턴한다.
// 2) Bean 애노테이션 정의 
//    => IoC 컨테이너가 보관해야 하는 객체를 만들어 주는 메서드를 표시할 때 사용한다.
//    => IoC 컨테이너는 이 애노테이션이 붙은 메서드를 호출하여 그 리턴 값을 보관할 것이다.
// 3) AppConfig 변경
//    => 객체를 생성하여 리턴하는 메서드에 Bean 애노테이션을 붙인다.
// 4) ApplicationContext 변경
//    => 생성자의 파라미터로 받은 클래스에 대해 설정 작업을 수행한다.
// 5) ComponentScan 애노테이션 정의
//    => IoC 컨테이너가 객체를 자동 생성할 때 뒤질 패키지 이름을 설정한다.
// 6) AppConfig 변경
//    => ComponentScan 애노테이션을 추가한다.
// 7) ApplicationContext 변경
//    => 생성자에서 ComponentScan 애노테이션을 처리한다.
//
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.eomcs.lms.context.ApplicationContext;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;
import com.eomcs.lms.handler.Response;

public class ServerApp {

  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();
  HashMap<String,Object> context = new HashMap<>();

  ApplicationContext beanContainer;
  RequestMappingHandlerMapping handlerMapping;
  
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void service() throws Exception {

    try (ServerSocket ss = new ServerSocket(8888)) {
      

      for (ApplicationContextListener listener : listeners) {
        listener.contextInitialized(context);
      }

      beanContainer = (ApplicationContext) context.get("applicationContext");
      
      handlerMapping = 
          (RequestMappingHandlerMapping) beanContainer.getBean("handlerMapping");
      
      System.out.println("서버 실행 중...");
      
      while (true) {
        new RequestHandlerThread(ss.accept()).start();
      } 
      
    } catch (Exception e) {
      e.printStackTrace();
    } 

  }
  
  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();

    app.addApplicationContextListener(new ApplicationInitializer());

    app.service();
  }
  
  class RequestHandlerThread extends Thread {
    
    Socket socket;
    
    public RequestHandlerThread(Socket socket) {
      this.socket = socket;
    }
    
    @Override
    public void run() {
      
      try (Socket socket = this.socket;
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(socket.getOutputStream())) {

        String request = in.readLine();
        
        RequestMappingHandler requestHandler = handlerMapping.get(request);
        
        if (requestHandler == null) {
          out.println("실행할 수 없는 명령입니다.");
          out.println("!end!");
          out.flush();
          return;
        }
        
        try {
          requestHandler.method.invoke(
              requestHandler.bean,
              new Response(in, out));
          
        } catch (Exception e) {
          out.printf("실행 오류! : %s\n", e.getMessage());
          e.printStackTrace();
        }
        
        out.println("!end!");
        out.flush();
        
      } catch (Exception e) {
        System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
        e.printStackTrace();
        
      }
    }
  }
}









