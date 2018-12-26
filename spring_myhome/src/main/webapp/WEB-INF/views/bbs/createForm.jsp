
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">

<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 

</style> 

<script type="text/javascript">
function bcheck(f) {
	if (f.wname.value == "") {
		alert("성명을 입력하세요");
		f.wname.focus();
		
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
		alert("비밀번호를 입력하세요");
		f.passwd.focus();
		
		return false;
	}
}

function blist() {
	var url = "./list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
</script>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<FORM name='frm' method='POST' action='./create' onsubmit="return bcheck(this)"
	  enctype="multipart/form-data">
<div class="w3-container" style="width:100%">
	<div class=" w3-bar w3-panel w3-pale-red w3-leftbar w3-border-red">
	  <p>글 작성</p>
	  <div class="w3-right-align"><p>작성자:
		<input type="text" name="wname">
	  </p></div>
	</div>
	<div class=" w3-border w3-bar w3-light-grey">
	<div class="w3-left"><p>제목:
	<input type="text" name="title" style="width: 300px">
	 </p></div>
	<div class="w3-right">첨부 파일:
	<input type="file" name="filenameMF">
 
   </div>
	</div>
	
	<div class="w3-container w3-border w3-large">
	
    <div class="w3-left-align" style="width:100%; height:400px">
    <textarea style="width:100%; height:400px" name="content"></textarea>
    </div>
   	<div>비밀번호:  <input type="password" name="passwd"></div>
  </div>
	
</div>

  <DIV class='w3-center'>
    <input type='submit' value='등록'>
    <input type='button' value='목록' onclick="blist()">
    <input type="reset" value="입력 취소">
  </DIV>
</FORM>
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 