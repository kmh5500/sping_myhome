
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css">
.img_wrap{
	width:300px;
	margin-top:50px; 
}
.img_wrap img{
	max-width: 100%;
}
</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
<script>
  var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
  };
</script>


<script type="text/javascript">
function inputCheck(f) {
	if (f.fname.value == "") {
		alert("사진을 첨부하세요");
		f.fname.focus();
		
		return false;
	}
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
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title"></DIV>
 <FORM name='frm' method='POST' action='./create'
	  enctype="multipart/form-data" onsubmit="return inputCheck(this)">

 	<h2><b>이미지 미리보기</b></h2>
 <p class="title">이미지 업로드</p>
	


<table width = "100%" border = "1" cellpadding = "1" cellspacing = "0">
<tr>
      <td>제목</td>
      <td>
            <input type = "text" name = "title" size = "40" value = "">
      </td>
</tr>

<tr>
      <td>암호</td>
      <td>
            <input type = "password" name = "passwd" size = "10" value = "">
      </td>
</tr>
<tr>
      <td>이미지</td>
      <td>
           <div>
 <input type="file" name="fnameMF" accept="image/*" onchange="loadFile(event)"></div>

<div><img id="output" class="img_wrap"/></div>
      </td>
</tr>
<tr>
      <td>내용</td>
      <td>
            <textarea name = "content" cols = "40" rows = "8"></textarea>
      </td>
</tr>
<tr>
      <td colspan = "2">
            <input type = "submit" value = "등록">
      </td>
</tr>
</table>

</FORM>
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 