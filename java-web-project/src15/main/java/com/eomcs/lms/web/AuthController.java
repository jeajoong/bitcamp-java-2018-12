package com.eomcs.lms.web;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
@RequestMapping("/auth")
public class AuthController {

  final static Logger logger = LogManager.getLogger(AuthController.class);
  // 로그 출력하려고
  
  static final String REFERER_URL = "refererUrl";
  
  @Autowired MemberService memberService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("form")
  public void form(
      @RequestHeader(value="Referer",required=false) String refererUrl,
      HttpSession session) {
    
    logger.debug("refererUrl: " + refererUrl);
    
    if (refererUrl != null && !refererUrl.endsWith("/auth/login")) {
    session.setAttribute(REFERER_URL, refererUrl);
  } else {
    session.removeAttribute(REFERER_URL);
  }
  }
  @RequestMapping("login")
  public String login(String email, String password, String saveEmail,
      HttpSession session, HttpServletResponse response) throws Exception {

    Cookie cookie;
    if (saveEmail != null) {
      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 15); // 15일간 쿠키를 보관한다.
      
    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 기존의 쿠키를 제거한다.
    }

    // 이제 이 클래스는 including 서블릿이 아니기 때문에 
    // 프론트 컨트롤러에서 받은 response 객체를 사용하여 
    // 바로 쿠키를 추가할 수 있다.
    response.addCookie(cookie); 

    Member member = memberService.get(email, password);

    if (member == null) {
      return "auth/fail.jsp";
    }

    session.setAttribute("loginUser", member);

    String refererUrl = (String) session.getAttribute(REFERER_URL);
    if (refererUrl == null) {      
      return "redirect:" + servletContext.getContextPath();
      
    } else {
      response.sendRedirect(refererUrl);
      return null;
      // return "redirect:" + refererUrl;
    }
  }
  
  @RequestMapping("logout")
  public String logout(HttpSession session) throws Exception {
    logger.debug("세션 무효화시킴");

    session.invalidate();
    return "redirect:/";
  }
}










