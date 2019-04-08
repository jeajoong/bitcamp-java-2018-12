<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<title> 검색</title>
</head>
<jsp:include page="/header.jsp"/>
<body>
<h1>검색 결과(JSP2)</h1>
<table border='1'>
<tr><th>번호</th><th>제목</th><th>등록일</th><th>조회수</th><th>수업</th></tr>
  <jsp:useBean scope="request" id="boards" type="java.util.List<PhotoBoard>"/>
<%for(PhotoBoard board : boards) { %>
<tr>
<td><%=board.getNo()%></td>
<td><a href='detail?no=<%=board.getNo()%>'><%=board.getTitle()%></a></td>
<td><%=board.getCreatedDate()%></td>
<td><%=board.getViewCount()%></td>
<td><%=board.getLessonNo()%></td>
</tr>
<%} %>
</table>
<p><a href='list'>목록</a></p>
</body>
</html>
