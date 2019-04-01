package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.ServerApp;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    BoardService boardService = ServerApp.iocContainer.getBean(BoardService.class);
    
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardService.get(no);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    if (board == null) {
      out.println("<p>해당 번호의 게시물이 없습니다.</p>");
    } else {
      out.println("<form action='update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr>"
          + "<th>번호</th>"
          + "<td><input type='text' name='no' value='%d' readonly></td>"
          + "</tr>\n", no);
      out.println(String.format(
          "<tr> <th>내용</th> "
          + "<td><textarea name='contents' rows='3' cols='50'>%s</textarea></td> "
          + "</tr>", board.getContents()));
      out.println(String.format(
          "<tr> <th>작성일</th> <td>%s</td> </tr>", board.getCreatedDate()));
      out.println(String.format(
          "<tr> <th>조회수</th> <td>%d</td> </tr>", board.getViewCount()));
      
      out.println("</table>");
      out.println("<p><button type='submit'>변경</button>"
          + "</p>");
      out.println("</form>");
    }
    out.println("</body></html>");
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    BoardService boardService = ServerApp.iocContainer.getBean(BoardService.class);
    
    Board board = new Board();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setContents(request.getParameter("contents"));
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html><head>"
        + "<title>게시물 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>게시물 변경</h1>");
    
    if (boardService.update(board) == 0) {
      out.println("<p>해당 번호의 게시물이 없습니다.</p>");
    } else { 
      out.println("<p>변경했습니다.</p>");
    }
    out.println("</body></html>");
  }
  

}










