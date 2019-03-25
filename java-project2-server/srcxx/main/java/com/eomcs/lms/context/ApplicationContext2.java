package com.eomcs.lms.context;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.io.Resources;
import com.eomcs.lms.handler.Command;

public class ApplicationContext2 { // 연습용2

  ArrayList<Class<?>> classes = new ArrayList<>();
  
  public ApplicationContext2(String packageName) throws Exception {
    File packageDir = Resources.getResourceAsFile(packageName.replace("." , "/"));
    findClasses(packageDir, packageName);
    prepareCommand();
    
  }

  private void findClasses(File dir, String packageName) throws Exception{
    File[] files = dir.listFiles((File pathname) -> {
      if (pathname.isDirectory())
        return true;
      
      if(pathname.getName().endsWith(".class") &&
          ! pathname.getName().contains("$")) // 중첩 클래스
        return true;
        
      return false;
    });
    
    for(File f : files) {
      if(f.isFile()) {
        
        String filename = f.getName();
        String className = packageName + "." +
        filename.substring(0, filename.indexOf( '.' ) );
        
        Class<?> clazz = Class.forName(className);
        
        if (clazz.isLocalClass() || clazz.isInterface() || clazz.isEnum())
          continue;
        
        //추상클래스이거나 public이 아닐때
        if(Modifier.isAbstract(clazz.getModifiers()) || 
            !Modifier.isPublic(clazz.getModifiers()))
          continue;
        
        classes.add(clazz);
        
      } else {
        findClasses(f, packageName + "." + f.getName());
      }
    }
  }
  
  private void prepareCommand() throws Exception {
    for(Class<?> clazz : classes) {
      List<Class<?>> interfaces = getAllInterfaces(clazz);
      for(Class<?> i : interfaces) {
        
        if (i == Command.class) {
          System.out.println(clazz.getName());
          break;
        }
      }
    }
  }

  private List<Class<?>> getAllInterfaces(Class<?> clazz) {
    ArrayList<Class<?>> list = new ArrayList<>();
    
    while(clazz != Object.class) {
      Class<?> [] interfaces = clazz.getInterfaces();
      for(Class<?> i : interfaces)
        list.add(i);
      clazz = clazz.getSuperclass();
    }
    return list;
  }

}

