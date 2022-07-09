<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="dto" items="${list }">
	아이디 : ${dto.id }<br>
	이름 : ${dto.name }<br>
	파일명 : ${dto.imgName }
	<img alt="이미지없음" width="100px" height="100px" 
			src="download?imgName=${dto.imgName }">
	<a href="download?imgName=${dto.imgName }">
		${dto.imgName }
	</a>
	<hr>
</c:forEach>
<a href="form">업로드 이동</a>

</body>
</html>







