package com.eomcs.lms.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.context.RequestHeader;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;
import com.eomcs.lms.context.RequestParam;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@WebServlet("/app/*")
@SuppressWarnings("serial")
public class DispatcherServlet2 extends HttpServlet {

@Override
protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

  ServletContext sc = this.getServletContext();
  ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer");

  RequestMappingHandlerMapping handlerMapping =
      iocContainer.getBean(RequestMappingHandlerMapping.class);
  try {
    
  }catch (Exception e) {

  }

}




}








