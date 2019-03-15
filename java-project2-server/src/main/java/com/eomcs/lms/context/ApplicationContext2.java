package com.eomcs.lms.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.io.Resources;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;

//연습용~
public class ApplicationContext2 {
  ArrayList<Class<?>> classes = new ArrayList<>();
  
  HashMap<String,Object> beanContainer = new HashMap<>();
  
  public ApplicationContext2(Class<?> configClass) throws Exception {
    
    Constructor<?> c = configClass.getConstructor();
    Object config = c.newInstance();
    
    ArrayList<Method> factoryMethods = new ArrayList<>();
    
    Method[] methods = configClass.getMethods();
    for (Method m : methods) {
      if (m.getAnnotation(Bean.class) != null) 
        factoryMethods.add(m);
    }
    while (factoryMethods.size() > 0) {
      Method m = factoryMethods.get(0); // 메서드 목록에서 메서드를 꺼내
      callFactoryMethod(config, m, factoryMethods, ""); // 호출한다.
      factoryMethods.remove(m); // 호출에 성공하든 실패하든 목록에서 제거한다.
    }
    
    ComponentScan componentScan = configClass.getAnnotation(ComponentScan.class);
    if (componentScan != null) {
      String[] packages = componentScan.basePackages();
      for (String packageName : packages) {
        prepareComponent(packageName);
      }
    }
  }
  
  public void prepareComponent(String packageName) throws Exception {
    File packageDir = Resources.getResourceAsFile(packageName.replace(".", "/"));
   
    findClasses(packageDir, packageName);
    
    createComponent();
    
    postProcess();
    
    System.out.println("-------------------------------");
    Set<String> names = beanContainer.keySet();
    for (String name : names) {
      System.out.printf("%s : %s\n", 
          name, beanContainer.get(name).getClass().getSimpleName());
    }
  }
  private void addBean(String name, Object bean) {
    if (name == null || name.length() == 0 || bean == null)
      return;
    beanContainer.put(name, bean);
  }
  
  public Object getBean(String name) {
    return beanContainer.get(name);
  }
  
  
  
  
  private void findClasses(File dir, String packageName) throws Exception {
    File[] files = dir.listFiles((File pathname) -> {
        if (pathname.isDirectory())
          return true;
        
        if (pathname.getName().endsWith(".class")/* 자바 클래스 파일 */ &&
            !pathname.getName().contains("$")/* 중첩 클래스가 아닌 경우 */)
          return true;
        
        return false;
      });
    
    for (File f : files) {
      if (f.isFile()) {
        String filename = f.getName();
        String className = packageName + "." + 
            filename.substring(0, filename.indexOf('.'));
        
        Class<?> clazz = Class.forName(className);
        
        if (clazz.isLocalClass() || clazz.isInterface() || clazz.isEnum())
          continue;
        
        if (Modifier.isAbstract(clazz.getModifiers()) ||
            !Modifier.isPublic(clazz.getModifiers()))
          continue;
        
        classes.add(clazz);
        
      } else {
        findClasses(f, packageName + "." + f.getName());
      }
    }
  }
  
  private void createComponent() throws Exception {
    for (Class<?> clazz : classes) {
      Component compAnno = clazz.getAnnotation(Component.class);
      
      if (compAnno == null) 
        continue;
      
      // Component 애노테이션이 붙은 클래스에 대해 인스턴스를 생성한다.
      Object obj = createInstance(clazz);
      
      if (obj != null) { // 제대로 생성했으면 빈컨테이너에 저장한다.
        // 빈컨테이너에 객체를 저장할 때 key 값은 Component 애노테이션의 value() 값으로 한다.
        // 만약 value 가 빈 문자열이라면 클래스 이름을 사용한다.
        // => 클래스에서 getName() 메서드를 알아낸다.
        addBean(
            compAnno.value().length() > 0 ? compAnno.value() : clazz.getName(),
            obj);
      }
    }
  }
  
  
  
  
  private Object createInstance(Class<?> clazz) throws Exception {
    try {
      Constructor<?> defaultConstructor = clazz.getConstructor();
      return defaultConstructor.newInstance();
    } catch (Exception e) {
    }
    Constructor<?>[] constructors = clazz.getConstructors();
    for (Constructor<?> c : constructors) {
      Class<?>[] paramTypes = c.getParameterTypes();
      Object[] paramValues = getParameterValues(paramTypes);
      if(paramValues != null) {
        return c.newInstance(paramValues);
      }
    }
    return null;
  }
  
  
  
  
  private Object[] getParameterValues(Class<?>[] paramTypes) {
    ArrayList<Object> values = new ArrayList<>();
    
    for (Class<?> type : paramTypes) {
      Object value = findBean(type);
      if (value != null) {
        values.add(value);
      }
    }
    if (values.size() == paramTypes.length) 
      return values.toArray();
    else
      return null;
  }
  
  
  
  
  
  
  private Object findBean(Class<?> type) {
    Collection<Object> beans = beanContainer.values();
    for (Object bean : beans) {
      if (type.isInstance(bean))
        return bean;
    }
    return null;
  }
  
  
  
  public void postProcess() {
    RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
    Collection<Object> beans = beanContainer.values();
    
    for (Object bean : beans) {
      Method[] methods = bean.getClass().getMethods();
      for (Method m : methods) {
        RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
        if (requestMapping == null) 
          continue;
        
        RequestMappingHandler handler = new RequestMappingHandler(bean, m);
        
        handlerMapping.add(requestMapping.value(), handler);
      }
    }
    beanContainer.put("handlerMapping", handlerMapping);
  }
  
  
  private Object callFactoryMethod(
      Object obj,
      Method factoryMethod,
      List<Method> factoryMethods,
      String indent) throws Exception {
    
    Class<?>[] paramTypes = factoryMethod.getParameterTypes(); // 파라미터 정보를 담을 배열
    Object[] paramValues = new Object[paramTypes.length];// 파라미터 값을 담을 배열
    
    for(int i = 0; i < paramTypes.length; i++) { // 파라미터 타입에 해당하는것을 꺼냄
      Object paramValue = findBean(paramTypes[i]);
      if (paramValue != null) {
        paramValues[i] = paramValue;
        continue;
      } // 빈컨테이너에 파라미터 타입에 해당하는 값이 없으면
      //팩토리 메서드에서 찾아야한다
      Method otherFactoryMethod = findMethodByReturnType(
          factoryMethods,paramTypes[i]);
      if (otherFactoryMethod == null) {
        return null; // 없으면 포기
      }
      // 찾았으면 공백과 함께 찾은메서드를 출력해준다
      paramValue = callFactoryMethod(obj, otherFactoryMethod, factoryMethods, indent+ "    ");
      if (paramValue == null) { // 찾았지만 그 메서드의 파라미터값이 없어서 호출에 실패하면
        return null;                      // 호출을 포기한다.
      }
      paramValues[i] = paramValue;
    }
    factoryMethods.remove(factoryMethod);
    
    Object bean = factoryMethod.invoke(obj, paramValues);
    Bean beanAnno = factoryMethod.getAnnotation(Bean.class);
    beanContainer.put(
        beanAnno.value().length() > 0 ? beanAnno.value() : factoryMethod.getName(),
            bean);
    
    return bean;
  }

  private Method findMethodByReturnType(List<Method> methods, Class<?> returnType) {
    for(Method m : methods) {
      if (m.getReturnType() == returnType) {
        return m;
      }
    }
    return null;
  }
  
  
}




