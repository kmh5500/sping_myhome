<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/ssi/ssi.jsp" %> 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
 <style type="text/css">
* {
	font-family: gulim;
	font-size: 24px;
}

div {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 20px;
}

table {
	width: 60%;
	margin: 0 auto;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 10px
}
</style>

<script>
  var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
  };
</script>

<script type="text/javascript">
function inputCheck(f) {
	
	if (f.title.value == "") {
		alert("제목을 입력하세요");
		f.title.focus();
		
		return false;
	}
	if (f.content.value == "") {
		alert("내용을 입력하세요");
		f.content.focus();
		
		return false;
	}
	if (f.passwd.value == "") {
		alert("패스워드를 입력하세요");
		f.passwd.focus();
		
		return false;
	}
}
</script>
</head>
<body>



<DIV class="title"></DIV>
 <FORM name='frm' method='POST' action='./update'
 		 enctype="multipart/form-data"
	   onsubmit="return inputCheck(this)">
	<input type="hidden" name="no" value="${param.no}">
	<input type="hidden" name="oldfname" value="${dto.fname}">
  <div class="w3-container w3-border w3-bar w3-center" style="width:100%">
<DIV class="w3-container w3-border w3-bar" style="width:100%">이미지 정보</DIV>

<div class="">
 <img style="width:300px; height:300px;"  src="./storage/${dto.fname}">
    <div>
 <input type="file" name="fname" accept="image/*" onchange="loadFile(event)"></div>
 <div><img id="output" class="img_wrap"/></div>
      </td>
</div>

<br>

   
 <table class="w3-table-centered">
    <TR>
      <TH class="">제 &nbsp;목</TH>
      <TD colspan="4">
        <input type = "text" name = "title" size = "40" value = "${dto.title}">
      </TD>
    </TR>
    <TR>
      <TH class="">내 &nbsp;용</TH>
      <TD colspan="4">
      <textarea name = "content" cols = "40" rows = "8" >${dto.content}</textarea>
      </TD>
    </TR>
    <TR>
      <TH class="">암호</TH>
      <TD colspan="4">
      <input type = "password" name = "passwd" size = "10" value = "">
      
      </TD>
    </TR>
    <TR>
      <TH class="">작성일</TH>
      <TD colspan="4">${dto.wdate}</TD>
    </TR>
    <TR>
      <TH class="">조회수</TH>
      <TD colspan="4">${dto.viewcnt}</TD>
    </TR>
    
    
  </TABLE>
  

 </div>
	 <input type = "submit" value = "등록">

</FORM>


</body>
</html>