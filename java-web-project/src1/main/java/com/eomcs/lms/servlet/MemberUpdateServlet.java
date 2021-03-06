package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.ServerApp;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    MemberService memberService = ServerApp.iocContainer.getBean(MemberService.class);
    
    int no = Integer.parseInt(request.getParameter("no"));
    Member member = memberService.get(no);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    if (member == null) {
      out.println("<p>해당 번호의 회원이 없습니다.</p>");
    } else {
      out.println("<form action='update' method='post'>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>번호</th>");
      out.printf("  <td><input type='text' name='no' readonly value='%d'></td>\n", no);
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>이름</th>");
      out.printf("  <td><input type='text' name='name' value='%s'></td>\n",
          member.getName());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>이메일</th>");
      out.printf("  <td><input type='email' name='email' value='%s'></td>\n",
          member.getEmail());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>암호</th>");
      out.println("  <td><input type='password' name='password'></td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진</th>");
      out.printf("  <td><input type='text' name='photo' value='%s'></td>\n",
          member.getPhoto());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>전화</th>");
      out.printf("  <td><input type='text' name='tel' value='%s'></td>\n",
          member.getTel());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>가입일</th>");
      out.printf("  <td>%s</td>\n",
          member.getRegisteredDate());
      out.println("</tr>");
      out.println("</table>");
      out.println("<p><button type='submit'>변경</button>"
          + "</p>");
      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  
    MemberService memberService = ServerApp.iocContainer.getBean(MemberService.class);
    
    Member member = new Member();
    member.setNo(Integer.parseInt(request.getParameter("no")));
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setPhoto(request.getParameter("photo"));
    member.setTel(request.getParameter("tel"));
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html><head>"
        + "<title>회원 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>회원 변경</h1>");
    
    if (memberService.update(member) == 0) {
      out.println("<p>해당 번호의 회원이 없습니다.</p>");
    } else { 
      out.println("<p>변경했습니다.</p>");
    }
    out.println("</body></html>");
  }
}
