package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 사진 파일 데이터 설정
@WebServlet("/photoboard/update") // 웹 서블릿 객체 주소
@SuppressWarnings("serial")
public class PhotoBoardUpdateServlet extends HttpServlet {
  
  String uploadDir; // 사진 파일 관리목적..
  
  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath("/upload/photoboard");
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    PhotoBoardService photoBoardService = InitServlet.iocContainer.getBean(PhotoBoardService.class);

    response.setContentType("text/html;charset=UTF-8");

    PhotoBoard board = new PhotoBoard();
    //새로만든 PhotoBoard 객체에 요청이 들어온 값들을 넣어준다.
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setTitle(request.getParameter("title"));
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    // 그리고 사진 처리를 해줘야 하는데 업데이트를 하려면 처음부터 파일을 올려야 한다.
    ArrayList<PhotoFile> files = new ArrayList<>();
    Collection<Part> photos = request.getParts(); 
    
    for (Part photo : photos) {
      if (photo.getSize() == 0 || !photo.getName().equals("photo")) // photo의 이름이 "photo"가 아니라면? as사진이 as사진과 같지 않다면
        continue;
      
      String filename = UUID.randomUUID().toString(); // 사진 이름 랜덤으로 설정하기
      photo.write(uploadDir + "/" + filename);
      
      PhotoFile file = new PhotoFile();
      file.setFilePath(filename);
      file.setPhotoBoardNo(board.getNo());
      files.add(file);
    }
    board.setFiles(files);

    PrintWriter out = response.getWriter();
    out.println("<html><head>" + "<title>사진 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>" + "</head>");
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