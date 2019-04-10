package com.eomcs.lms.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

//4-10
// 페이지 컨트롤러는 Spring IoC 컨테이너에서 관리해야 한다.
// 클래스에 @Controller 애노테이션을 붙여라!
// @Component 중에서 page를 다루는 클래스이면 Controller를 사용
@Controller
public class BoardListController {
  
//@Autowired하게 되면 생성자를 만들 필요가 없다  
  @Autowired BoardService boardService;
  
  // Controller 클래스를 찾는것이 아니라 RequestMapping이 붙은 메소드를 찾을 것이여서
  // Controller에 이름을 지정하지 않아도 된다.
  @RequestMapping("/board/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    List<Board> boards = boardService.list();
    request.setAttribute("list", boards);
    
    // 뷰 컴포넌트의 URL을 이 메서드를 호출한 프론트 컨트롤러에게 리턴한다.
    return "/board/list.jsp";
  }
}










