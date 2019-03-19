// 애플리케이션의 상태 변경을 보고 받는 옵저버가 구현해야 할 규칙
package com.eomcs.lms.context;

import java.util.Map;
import javax.annotation.Nullable;

public interface ApplicationContextListener {
  // 애플리케이션이 시작될 때 호출된다.
  void contextInitialized(Map<String,Object> context) throws ApplicationContextException;
  
  
  void contextDestroyed(Map<String,Object> context) throws ApplicationContextException;

}
