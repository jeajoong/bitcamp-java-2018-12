package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.ServerApp;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;


@SuppressWarnings("serial")
@WebServlet("/photoboard/update")
public class PhotoBoardUpdateServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  
    PhotoBoardService photoBoardService = ServerApp.iocContainer.getBean(PhotoBoardService.class);
    
    int no = Integer.parseInt(request.getParameter("no"));
    
    PhotoBoard board = photoBoardService.get(no);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    if (board == null) {
      out.println("<p>해당 사진을 찾을 수 없습니다.</p>");
      
    } else {
      out.println("<form action='update' method='post'>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>번호</th>");
      out.printf("  <td><input name='no' value='%d' readonly></td>\n", 
          board.getNo());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>제목</th>");
      out.printf("  <td><input name='title' value='%s'></td>\n", 
          board.getTitle());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>등록일</th>");
      out.printf("  <td>%s</td>\n", board.getCreatedDate());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>조회수</th>");
      out.printf("  <td>%d</td>\n", board.getViewCount());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>수업</th>");
      out.printf("  <td>%s(%s ~ %s)</td>\n", 
          board.getLesson().getTitle(),
          board.getLesson().getStartDate(),
          board.getLesson().getEndDate());
      out.printf("<input type='hidden' name='lessonNo' value='%d'>", 
          board.getLesson().getNo());
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td>");
      out.println("</tr>");
      out.println("<tr>");
      out.println("  <th>사진 파일</th>");
      out.println("  <td>");
      
      List<PhotoFile> files = board.getFiles();
      for (int i = 0; i < 5; i++) {
        if (i < files.size()) {
          out.printf("<input type='text' name='photo%d' value='%s'><br>\n", 
              i, files.get(i).getFilePath());
        } else {
          out.printf("<input type='text' name='photo%d'><br>\n", i);
        }
      }
      out.println("</td></tr>");
      out.println("</table>");
      
      out.println("<p><a href='list'>목록</a>"
          + " <a href='delete?no=" + board.getNo() + "'>삭제</a>"
          + " <button type='submit'>변경</button>"
          + "<p>");
      out.println("</form>");
    }
    out.println("</body></html>");
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  
    PhotoBoardService photoBoardService = ServerApp.iocContainer.getBean(PhotoBoardService.class);
    
    PhotoBoard board = new PhotoBoard();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setTitle(request.getParameter("title"));
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));
    
    ArrayList<PhotoFile> files = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      String filename = request.getParameter("photo" + i);
      if (filename.length() == 0)
        continue;
      
      PhotoFile file = new PhotoFile();
      file.setFilePath(filename);
      file.setPhotoBoardNo(board.getNo());
      files.add(file);
    }
    board.setFiles(files);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>사진 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>사진 변경</h1>");
    
    if (files.size() == 0) {
      out.println("<p>최소 한 개의 사진 파일을 등록해야 합니다.</p>");
      
    } else {
      photoBoardService.update(board);
      out.println("<p>변경하였습니다.</p>");
    }
    out.println("</body></html>");
  }

}
