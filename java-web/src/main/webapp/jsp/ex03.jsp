<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% // _jspService() 메서드 안에 넣을 코드를 작성하라!
int i = 100; 
//public void m() {   // _jspService() 메서드 안이여서 생성불가
//}
//<!-- -->   // JSP 주석 또한 바로 JAVA 파일로 복사되기 때문에 작성불가

class My {} // 중첩 클래스 가능
%>

<html>
<head>
<meta charset="UTF-8">
<title>ex03</title>
</head>
<body>
<h1>스크립트릿(scriptlet)과 JSP 주석<%out.print(i);%></h1>
<%-- JSP 주석
- 이 태그 안에 작성된 것은 자바 클래스를 생성할 때 제거된다.
	
스크립트릿
- JSP 파일 안에 실행할 자바 코드를 넣는 태그
- 예)
	<% 자바 코드 %>
- 스크립트릿 안에 작성한 코드는 자바 클래스를 생성할 때
	_jspService() 메서드 안에 작성한 순서 그대로 복사된다.(메서드를 작성할 수 없다 -> 메서드 안이라서)
 --%>
<%
if(i < 10) {
  for(int x = 0; x < i; x++) {
    out.println("<p>"+ x + "</p>");
  }
}
%>



</body>
</html>



