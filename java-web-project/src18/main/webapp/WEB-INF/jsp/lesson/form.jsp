<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <title>새 수업</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container">
<h1>새 수업</h1>
<form action='add' method='post'>

  <div class="form-group row">
    <label for="title" class="col-sm-2 col-form-label">수업</label>
    <div class="col-sm-7">
      <textarea class="form-control" id="title" name='title' rows='1'></textarea>
    </div>
  </div>

  <div class="form-group row">
    <label for="contents" class="col-sm-2 col-form-label">내용</label>
    <div class="col-sm-7">
      <textarea class="form-control" id="contents" name='contents' rows='3'></textarea>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="startDate" class="col-sm-2 col-form-label">시작일</label>
    <div class="col-sm-7">
      <input type='date' class="form-control-date" id='startDate' name='startDate'>
    </div>
  </div>

  <div class="form-group row">
    <label for="endDate" class="col-sm-2 col-form-label">종료일</label>
    <div class="col-sm-7">
      <input type='date' class="form-control-date" id='endDate' name='endDate'>
    </div>
  </div>

  <div class="form-group row">
    <label for="totalHours" class="col-sm-2 col-form-label">총 교육시간</label>
    <div class="col-sm-7">
      <textarea class="form-control" id="totalHours" name='totalHours' rows='1'></textarea>
    </div>
  </div>

  <div class="form-group row">
    <label for="dayHours" class="col-sm-2 col-form-label">일 교육시간</label>
    <div class="col-sm-7">
      <textarea class="form-control" id="dayHours" name='dayHours' rows='1'></textarea>
    </div>
  </div>


<div class="form-group row">
    <div class="col-sm-10">
      <a class="btn btn-primary" href='.'>목록</a> 
      <button class="btn btn-primary">등록</button>
    </div>
  </div>
</form>
  </div><!-- .container -->

<jsp:include page="../javascript.jsp"/>
</body>
</html>
