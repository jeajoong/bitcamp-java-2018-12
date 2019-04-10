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

    String pageControllerPath = request.getPathInfo();

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer");
    RequestMappingHandlerMapping handlerMapping = 
        (RequestMappingHandlerMapping) iocContainer.getBean(RequestMappingHandlerMapping.class);
    try {
      RequestMappingHandler requestHandler = handlerMapping.get(pageControllerPath);

      if (requestHandler == null)
        throw new Exception("해당 URL의 요청을 처리할 수 있습니다.");
      
      HashMap<String,Object> model = new HashMap<>();
      
      Object[] paramValues = prepareParamValues(
          requestHandler.method,request,response,model);
      
      String viewUrl = (String) requestHandler.method.invoke(
          requestHandler.bean, paramValues);
      
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9)); // ex) redirect:list
        
      } else {
        Set<Entry<String,Object>> entrySet = model.entrySet();
        for (Entry<String,Object> entry : entrySet) {
          request.setAttribute(entry.getKey(), entry.getValue());
        }
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(viewUrl).include(request, response);
      }
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.setAttribute("error.title", "요청 처리 오류!");
      request.setAttribute("error.content", e.getCause().getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }

  private Object[] prepareParamValues(
      Method method, 
      HttpServletRequest request,
      HttpServletResponse response,
      Map<String,Object> model) throws Exception {
    
    ArrayList<Object> paramValues = new ArrayList<>();
    Parameter[] params = method.getParameters();
    
    for (Parameter param : params) {
      Class<?> paramType = param.getType();
      if (paramType == ServletRequest.class || paramType == HttpServletRequest.class) {
        paramValues.add(request);
        
      } else if (paramType == ServletResponse.class || paramType == HttpServletResponse.class) {
        paramValues.add(response);
        
      } else if (paramType == Map.class) {
        paramValues.add(model);
        
      } else if (paramType == int.class) {
        RequestParam requestParam = param.getAnnotation(RequestParam.class);
        String paramName = requestParam.value();
        try {
          int value = Integer.parseInt(request.getParameter(paramName));
          paramValues.add(value);
        } catch (Exception e) {
          paramValues.add(0);
        }
        
      } else if (paramType == String.class) {
        RequestParam rq = param.getAnnotation(RequestParam.class);
        paramValues.add(request.getParameter(rq.value()));
        
      } else if (paramType == Part.class) {
        RequestParam rq = param.getAnnotation(RequestParam.class);
        paramValues.add(request.getPart(rq.value()));
          
      }  else if (paramType.getComponentType() == Part.class) {
        RequestParam rq = param.getAnnotation(RequestParam.class);
        ArrayList<Part> list = new ArrayList<>(); 
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
          if (!part.getName().equals(rq.value())) 
            continue;
          list.add(part);
        }
        paramValues.add(list.toArray(new Part[] {}));
        
      } else if (paramType == HttpSession.class) {
        paramValues.add(request.getSession());
        
      } else if (paramType == java.util.Date.class || paramType == java.sql.Date.class) {
        RequestParam rq = param.getAnnotation(RequestParam.class);
        paramValues.add(Date.valueOf(request.getParameter(rq.value())));
        
      } else {
        paramValues.add(null);
      }
    }
    
    return paramValues.toArray();
  }
}








