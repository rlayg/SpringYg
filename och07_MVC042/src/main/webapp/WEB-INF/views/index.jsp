<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<%
		//	서버를 못찾거나 URL을 잊어버려 그럼 이거 넣어 스트립 싫으면 밑에 폼액션 옆에 익스프레션 넣어
		String context = request.getContextPath();  // 이렇게 넣으면 패스를 잃어버려도 다 된다
	%>
<body>
	context : <%=context %><p> <!-- 스트립틀릿 찝찝해서 쓰기 싫으면  =context 자리에request.getContextPath(); 여기에 넣어 바로밑에 식으로 -->
	<%-- <form action="<%=request.getContextPath()%>/studentView1" method="post"> --%>
	<form action="<%=request.getContextPath()%>/studentView2" method="post">
		이름 : <input type="text"		name="name"><br />
		나이 : <input type="text"		name="age"><br />
		학년 : <input type="text"		name="gradeNum"><br />
		반 : <input type="text"		name="classNum"><br />
		<input type="submit" value="전송">
	</form>
	
</body>
</html>