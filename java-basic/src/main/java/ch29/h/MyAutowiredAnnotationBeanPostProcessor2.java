package ch29.h;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyAutowiredAnnotationBeanPostProcessor2 implements BeanPostProcessor {
  
  HashMap<Class<?>,Object> beans = new HashMap<>();
  HashMap<Class<?>,List<AutowiredMethod>> autowiredMethodMap = new HashMap<>();
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
      beans.put(bean.getClass(), bean);
      callAutowiredMethod(bean);
      
      System.out.println("@Autowired 애노테이션 처리 : ");
      Method[] methods = bean.getClass().getMethods();
      for(Method m : methods) {
        Autowired anno = m.getAnnotation(Autowired.class);
        if(anno == null) 
          continue;
        Class<?> paramType = m.getParameters()[0].getType();
    
        if(paramType != null ? true : false) {
          
          try {
            m.invoke(bean, beans.get(paramType));
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
          addAutowiredMethod(paramType, new AutowiredMethod(bean, m));
        }
      }
    return bean;
  }
  
  
  
  private void addAutowiredMethod(Class<?> paramType, AutowiredMethod autowiredMethod) {
    List<AutowiredMethod> methods = autowiredMethodMap.get(paramType);
    if(methods == null) {
      methods = new ArrayList<>();
      autowiredMethodMap.put(paramType, methods);
    }
    methods.add(autowiredMethod);
    
  }
  
  
  
  private void callAutowiredMethod(Object paramValue) {
    List<AutowiredMethod> setters = autowiredMethodMap.get(paramValue.getClass());
    
    if (setters == null)
      return;
    
    for (AutowiredMethod setter : setters) {
      try {
        setter.method.invoke(setter.object, paramValue);
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }

  
  class AutowiredMethod {
    Object object;
    Method method;
    
    public AutowiredMethod(Object object, Method method) {
      this.object = object;
      this.method = method;
    }
  }
  
  
  
}





