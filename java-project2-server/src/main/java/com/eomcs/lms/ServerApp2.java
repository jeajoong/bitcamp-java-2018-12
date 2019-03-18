package com.eomcs.lms;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.eomcs.lms.context.ApplicationContext;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.context.RequestMappingHandlerMapping;

public class ServerApp2 {

  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();
  HashMap<String,Object> context = new HashMap<>();
  
  ApplicationContext beanContainer;
  RequestMappingHandlerMapping handlerMapping;
  
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }
  
  public void service() throws Exception {
    
    try(ServerSocket ss = new ServerSocket(8888)) {
      
      for(ApplicationContextListener listener : listeners) {
        listener.contextInitialized(context);
      }
      beanContainer = (ApplicationContext) context.get("applicationContext");
      handlerMapping = (RequestMappingHandlerMapping) beanContainer.getBean("handlerMapping");
      
      System.out.println("서버 실행 중...");
      
      while(true) {
       new RequestHandlerThread(ss.accept()).start();
      }
       
    }catch (Exception e) {
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
  
  public RequestHandlerThread(Socket accept) {
    this.socket = socket;
  }
  
  @Override
  public void run() {
    //여기서부터
    //try로 소켓 열고 in out 설정하기 
  }
  
}
  
  
  
  
  
  
  
  
  
  
}
