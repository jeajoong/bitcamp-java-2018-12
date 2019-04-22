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
public class AuthFilter implements Filter {
  
  FilterConfig filterConfig; // 서블릿에 사용되는 필터 구성 개체이고 초기화 중에 필터로 정보가 전달된다
  String contextRootPath; // 예) /java-web-project
  
  @Override 
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    contextRootPath = filterConfig.getServletContext().getContextPath();
  //filterConfig의 정보중에 ServletContext의 경로명을 객체에 담는다 
  //=>   로그인 하고나서 화면을 넘겨주기 위해
  }
  
  @Override
  public void doFilter( 
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) 
      throws IOException, ServletException {
  //FiltersChain을 사용하여 체인의 다음 필터를 호출하거나 
  //호출 필터가 체인의 마지막 필터인 경우 체인의 끝에 있는 리소스를 호출하십시오
  // 즉, 필터체인끼리 서로서로 연결해서 사용하다가 마지막 체인이라면 chain안에 있는 dofilter 호출
    
    
    // 로그인 여부를 검사한다.
    HttpServletRequest httpReq = (HttpServletRequest) request;
    HttpServletResponse httpResp = (HttpServletResponse) response;
    
    // /app/* URL에 대해서 적용하기 때문에 서블릿 경로를 검사해서는 안된다.
    //String servletPath = httpReq.getServletPath(); // "/app"
    String pathInfo = httpReq.getPathInfo(); // ex) "/board/list"
    
    if (pathInfo.endsWith("add")
        || pathInfo.endsWith("update")
        || pathInfo.endsWith("delete")
        || (!pathInfo.endsWith("/auth/form") && 
             pathInfo.endsWith("form"))) {
      // 로그인 되어 있어야 한다.
      Member loginUser = (Member) httpReq.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        // 클라이언트가 요청한 위치를 알 수 없기 때문에
        // 막연히 상대경로로 로그인 폼의 URL을 지정할 수 없다.
        // 절대 경로로 정확하게 지정하라.
        
        httpResp.sendRedirect(contextRootPath + "/app/auth/form");
        return;
      }
    }
    
    // 그런 후에 다음 필터나 또는 최종 목적지인 서블릿을 실행한다.
    chain.doFilter(request, response);
  }
}





