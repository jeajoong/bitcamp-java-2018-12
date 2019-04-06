<%@page import="com.eomcs.lms.service.PhotoBoardService"%>
<%@page import="com.eomcs.lms.domain.PhotoFile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
  <%
PhotoBoard photoBoard = (PhotoBoard)request.getAttribute("photoBoard");
ArrayList<PhotoFile> files = (ArrayList<PhotoFile>) request.getAttribute("photoFile");
PhotoBoardService photoBoardService = (PhotoBoardService) request.getAttribute("photoBoardService");
%>
<!DOCTYPE html>
<html>
<head>
<title>사진 등록 오류</title>
<meta http-equiv="Refresh" content="2;url=<%=request.getHeader("Referer")%>">
</head>
<body>
<h1>사진 등록 오류</h1>
  <%if (photoBoard.getLessonNo() == 0) {%>
  <p>사진 또는 파일을 등록할 수업을 선택하세요.</p>
  <%} else if (files.size() == 0) {%>
  <p>최소 한 개의 사진 파일을 등록해야 합니다.</p>
  <%} else {
     photoBoardService.add(photoBoard);
      response.sendRedirect("list");
      return;
    }%>
</body>
</html>
