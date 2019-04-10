package com.eomcs.lms.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@Controller
public class LessonListController {
  
//@Autowired하게 되면 생성자를 만들 필요가 없다  
  @Autowired LessonService lessonService;
  
  @RequestMapping("/lesson/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Lesson> lessons = lessonService.list();
    
    request.setAttribute("list", lessons);
    
    // 뷰 컴포넌트의 URL을 ServletRequest 보관소에 저장한다.
    return "/lesson/list.jsp";
  }
}
