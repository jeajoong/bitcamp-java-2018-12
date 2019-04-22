package com.eomcs.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.domain.Member;

@WebFilter("/app/*")
public class AuthFilter2 implements Filter {

  FilterConfig filterConfig;
  String contextRootPath;
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    contextRootPath = filterConfig.getServletContext().getContextPath();
  }
  
  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain)
      throws IOException, ServletException {
    
    HttpServletRequest httpReq = (HttpServletRequest) request;
    HttpServletResponse httpResp= (HttpServletResponse) response;
    
    String pathInfo = httpReq.getPathInfo();
    
    if (pathInfo.endsWith("add")
        || pathInfo.endsWith("update")
        || pathInfo.endsWith("delete")
        || (!pathInfo.endsWith("/auth/form") && 
            pathInfo.endsWith("form"))) {
      //로그인 되어 있어야 한다.
      Member loginUser = (Member) httpReq.getSession().getAttribute("loginUser");
      
      if (loginUser == null) {
        
        httpResp.sendRedirect(contextRootPath );
      }
    }
    
  }
  
}






