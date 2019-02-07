package com.bitcamp.lms.context;

import java.util.Map;

public interface ApplicationListener {

  void startApplication(Map<String,Object> context);
  
  void endApplication(Map<String,Object> context);
}
