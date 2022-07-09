<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
var cnt=1
function addFile(){
	let input = "<input type='file' name='file"+cnt+"'>";
	let rmBtn = 
	"<input type='button' value='삭제' onclick='delFile()'>";
	$("#addfile").append(
		"<div id='"+cnt+"'>"+input + rmBtn +"</div>");
	cnt++;
}
function delFile(){
	cnt--;
	$("#"+cnt).remove()
}
</script>

</head>
<body>
	<form action="upload02" method="post"
					enctype="multipart/form-data">
		<input type="button" value="파일추가" onclick="addFile()"><br>
		
		<div id="addfile">
		
		</div>
		
		<input type="submit" value="전송">
	</form>
</body>
</html>




