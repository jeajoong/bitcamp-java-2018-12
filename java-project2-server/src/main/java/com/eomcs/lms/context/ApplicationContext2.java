package com.eomcs.lms.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.io.Resources;
import com.eomcs.lms.handler.Command;

public class ApplicationContext2 {
  
  ArrayList<Class<?>> classes = new ArrayList<>();
  
  HashMap<String,Object> beanContainer = new HashMap<>();
  
  public ApplicationContext2(String packageName, Map<String,Object> beans) throws Exception {
    
    if (beans != null && beans.size() > 0) {
      Set<String> names = beans.keySet();
      for (String name : names) {
        addBean(name, beans.get(name));
      }
    }
    
    File packageDir = Resources.getResourceAsFile(packageName.replace(".", "/"));
   
    findClasses(packageDir, packageName);
    
    prepareCommand();
    
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
  private void prepareCommand() throws Exception{
    for(Class<?> clazz : classes) {
        List<Class<?>> interfaces = getAllInterfaces(clazz);
        
        for(Class<?> i : interfaces) {
          if (i == Command.class) {
            Object obj = createInstance(clazz);
          
            if(obj != null) {
              Method getName = clazz.getMethod("getName");
              addBean(
                  (String) getName.invoke(obj), obj);
            }
            break;
          }
        }
    }
  }

  private List<Class<?>> getAllInterfaces(Class<?> clazz) {
    ArrayList<Class<?>> list = new ArrayList<>();
    
    while(clazz != Object.class) {
      Class<?>[] interfaces = clazz.getInterfaces();
      for (Class<?> i : interfaces)
        list.add(i);
      clazz = clazz.getSuperclass();
    } 
    return null;
  }


  private Object createInstance(Class<?> clazz) throws Exception {
    try {
      Constructor<?> defaultConstructor = clazz.getConstructor();
      return defaultConstructor.newInstance();
    } catch (Exception e) {
    }
    Constructor<?>[] constructors = clazz.getConstructors();
    for(Constructor<?> c : constructors) {
      Class<?> [] paramTypes = c.getParameterTypes();
      Object[] paramValues = getParameterValues(paramTypes); 
      if(paramValues != null) {
      return c.newInstance(paramValues);
    }
    }
    return null;
  }


  private Object[] getParameterValues(Class<?>[] paramTypes) {
    ArrayList<Object> values = new ArrayList<>();
    
    for(Class<?> type : paramTypes) {
      Object value = findBean(type);
      if(value != null) {
        values.add(value);
      }
    }
    if(values.size() == paramTypes.length)
      return values.toArray();
    else
    return null;
  }
  
  private Object findBean(Class<?> type) {
    Collection<Object> beans = beanContainer.values();
    for(Object bean : beans) {
      if(type.isInstance(bean))
        return bean;
    }
    return null;
    }
  }
  
  


