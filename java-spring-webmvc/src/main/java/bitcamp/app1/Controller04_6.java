// 요청 핸들러의 아규먼트 - @RequestHnadler
package bitcamp.app1;

import java.io.PrintWriter;
import java.util.regex.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/c04_6")
public class Controller04_6 {

  //클라이언트의 HTTP 요청 헤더를 받고 싶드면
  // request handler의 아규먼트 앞에 @RequestHeader(헤더명) 애노테이션을 붙여라!
  
  // 테스트:
  //    http://.../c04_6/h1
  @GetMapping("h1") 
  @ResponseBody 
  public void handler1(
      PrintWriter out,
      @RequestHeader("Accept")String accept,
      @RequestHeader("User-Agent")String userAgent
      ) {
    
    out.printf("Accept=%s\n", accept);
    out.printf("User-Agent=%s\n", userAgent);
    
    if(userAgent.matches("Chrome")) {
      out.println("chrome.");
    } else if (userAgent.matches("Safari")) {
      out.println("Safari.");
    } else if (userAgent.matches("Firefox")) {
      out.println("Firefox");
    } else {
      out.println("other");
    }
    
    System.out.println(userAgent.matches("Chrome"));
  }
  
  public static void main(String[] args) {
    String str = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
//    String str = "AA BB Aa Ba $12,000";
    
    // 정규 표현식으로 패턴을 정의한다.
    
//    String regex = "Chrome.*Safari"; // .은 문자를 찾으라는 의미인데 3자리 ./g
    String regex = "^(Chrome).*Safari";
    
    Pattern pattern = Pattern.compile(regex);

    // 주어진 문자열에서 패턴과 일치하는 정보를 찾는다.
    java.util.regex.Matcher matcher = pattern.matcher(str);
    
    //일치 여부를 확인한다.
    if (matcher.find()) {
      System.out.println("OK!");
//      for (int i = 0; i< matcher.groupCount(); i++) {
        System.out.println(matcher.group(1));
      }
    }
//    System.out.println(str.matches("Chrome"));
  
  
  
  
}


