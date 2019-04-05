<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% // _jspService() 메서드 안에 넣을 코드를 작성하라!
String[] names = {"홍길동", "임꺽정", "유관순"};
%>

<html>
<head>
<meta charset="UTF-8">
<title>ex04</title>
</head>
<body>
<h1>스크립트릿(scriptlet) 응용</h1>
<%
for (String name : names) {
  out.println("<li>" + name + "</li>");
}
%>



</body>
</html>



