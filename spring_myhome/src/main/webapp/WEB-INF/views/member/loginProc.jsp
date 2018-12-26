
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 

<!DOCTYPE html> 
<html> 
<meta charset="UTF-8"> 
<title></title> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
<head> 
<link href="/webtest/css/style.css" rel="Stylesheet" type="text/css">
</head> 

<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">로그인처리</DIV>
 
<div class="w3-center">
<c:choose>
	<c:when test="${flag=='true' }">
	"로그인 되었습니다."
	<input type='button' value='홈' 
  onclick="location.href='${root}/'">
	</c:when>
	<c:otherwise>
		"아이디/비밀번호를 잘못 입력하셨거나" <br>
		"회원이 아닙니다. 회원가입을 하세요"
		<input type='button' value='홈' 
  onclick="location.href='${root}/'">
  <input type='button' value='다시시도' 
  onclick="history.back()">
	</c:otherwise>
</c:choose>
</div>

 <DIV class='w3-center'>
  <input type='button' value='홈' 
  onclick="location.href='../'">
  <input type='button' value='다시시도' 
  onclick="history.back()">
</DIV>
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 