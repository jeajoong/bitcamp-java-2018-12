package com.eomcs.lms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.service.LessonService;

@Controller("/lesson/delete")
public class LessonDeleteController implements PageController {
  
//@Autowired하게 되면 생성자를 만들 필요가 없다  
  @Autowired LessonService lessonService;
  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

    if (lessonService.delete(no) == 0) 
      throw new Exception("해당 번호의 수업이 없습니다.");
      return "redirect:list";
      
  }
}
