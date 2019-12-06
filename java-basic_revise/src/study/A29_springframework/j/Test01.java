// Java config - 자바 클래스로 스프링 IoC 컨테이너 설정하기
package study.A29_springframework.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

public class Test01 {
  public static void main(String[] args) {
    
    // java config를 다룰 때는 AnnotationConfigApplicationContext 클래스를 사용한다.
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig1.class);
      // 위치를 직접 지정했으니 클래스에서 @Configuration 사용x
    
    System.out.println("---------------------------------------");
    
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", 
          name, iocContainer.getBean(name).getClass().getName());
    }
  }
}






