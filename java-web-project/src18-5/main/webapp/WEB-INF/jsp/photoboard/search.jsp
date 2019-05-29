<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>사진 검색</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>
  <jsp:include page="../header.jsp" />
  
  <div class="container">
  <h1>사진 검색</h1>
  
<div class="bit-list">
<table class="table table-hover">
<thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">등록일</th>
      <th scope="col">조회수</th>
      <th scope="col">수업</th>
    </tr>
    </thead>
<tbody>
  <c:forEach items="${list}" var="board">
    <tr>
      <td scope="row">${board.no}</td>
      <td><a href='${board.no}'>${board.title}</a></td>
      <td>${board.createdDate}</td>
      <td>${board.viewCount}</td>
      <td>${board.lessonNo}</td>
    </tr>
  </c:forEach>
  <tbody>
  </table>
  </div><!-- .bit-list  -->
  
  
  <nav aria-label="목록 페이지 이동">
  <ul class="pagination justify-content-center">
  
    <li class="page-item ${pageNo <= 1 ? 'disabled' : ''}">
    <a class="page-link" href="?lessonNo=${lessonNo}&keyword=${keyword}&pageNo=${pageNo -1}&pageSize=${pageSize}">이전</a></li>
    
    <li class="page-item ${pageNo <= 1 ? 'disabled' : ''}">
    <a class="page-link" href="?lessonNo=${lessonNo}&keyword=${keyword}&pageNo=${pageNo -1}&pageSize=${pageSize}">${pageNo <= 1 ? "-" : pageNo - 1}</a></li>

    <li class="page-item active"><span class="page-link">${pageNo}</span></li>

    <li class="page-item ${pageNo >= totalPage ? 'disabled' : ''}">
    <a class="page-link" href="?lessonNo=${lessonNo}&keyword=${keyword}&pageNo=${pageNo + 1}&pageSize=${pageSize}">${pageNo >= totalPage ? "-" : pageNo + 1}</a></li>

    <li class="page-item ${pageNo >= totalPage ? 'disabled' : ''}">
      <a class="page-link" href="?lessonNo=${lessonNo}&keyword=${keyword}&pageNo=${pageNo +1}&pageSize=${pageSize}">다음</a></li>
  </ul>
</nav>

  
 <div class="form-group row">
    <div class="col-sm-10">
      <a class="btn btn-primary" href='.'>목록</a> 
    </div>
  </div>
  
  </div><!-- .container -->

<jsp:include page="../javascript.jsp"/>
</body>
</html>







