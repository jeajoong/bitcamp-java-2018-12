package com.eomcs.lms.context;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;

public class ApplicationContext2 {
ArrayList<Class<?>> classes = new ArrayList<>();
HashMap<String,Object> beanContainer = new HashMap<>();
  
  public ApplicationContext2(Class<?> configClass) throws Exception {
    Constructor<?> c = configClass.getConstructor();
    Object config = c.newInstance();
    ArrayList<Method> factoryMethods = new ArrayList<>();
    
    Method[] methods = configClass.getMethods();
    for(Method m : methods) {
      if(m.getAnnotation(Bean.class) != null)
        factoryMethods.add(m);
    }
    while(factoryMethods.size() > 0) {
      Method m = factoryMethods.get(0);
      callFactoryMethod(config, m, factoryMethods, "");
      factoryMethods.remove(m);
    }
      
      ComponentScan componentScan = configClass.getAnnotation(ComponentScan.class);
      if(componentScan != null) {
        String[] packages = componentScan.basePackages();
        for(String packageName : packages) {
          prepareComponent(packageName);
      }
    }
  }
  public void prepareComponent(String packageName) throws Exception {
    File packageDir = Resources.getResourceAsFile(packageName.replace(".", "/"));
    findClasses(packageDir,packageName);
    createComponent();
    postProcess();
  }
  private void addBean(String name, Object bean) {
    if(name == null || name.length() == 0 || bean == null)
      return;
    beanContainer.put(name, bean);
  }
  
  public Object getBean(String name) { // ServerApp에서 활용
    return beanContainer.get(name);
  }
  
  
  
  
  private void findClasses(File dir, String packageName) throws Exception {
    File[] files = dir.listFiles((File pathname) -> {
        return false;
    });
    
  }
  
  
  
  
  private void createComponent() throws Exception {
  for(Class<?> clazz : classes) {
    Component compAnno = clazz.getAnnotation(Component.class);
    
    if(compAnno == null)
      continue;
    
    Object obj = createInstance(clazz);
    
    if(obj != null) {
      addBean(
          compAnno.value().length() > 0 ? compAnno.value() : clazz.getName(),
              obj);
    }
  }
  }
  
  
  // 요것도 다시 또 해보기
  private Object createInstance(Class<?> clazz) throws Exception {

    return null;
    }
  
  
  
  
  private Object[] getParameterValues(Class<?>[] paramTypes) {
    return paramTypes;
  }
  
  
  public void postProcess() {
  }
  
  
  private Object callFactoryMethod(
      Object obj,
      Method factoryMethod,
      List<Method> factoryMethods,
      String indent) throws Exception {
    
    return beanContainer;
  }
  
  
  private Method findMethodByReturnType() {
    return null;
  }
  
}










