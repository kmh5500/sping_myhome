
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">

<script type="text/javascript">
function bcheck(f) {
	if (f.wname.value == "") {
		alert("성명을 입력하세요");
		f.wname.focus();
		
		return;
	}
	if (f.title.value == "") {
		alert("제목을 입력하세요");
		f.title.focus();
		
		return;
	}
	if (f.content.value == "") {
		alert("내용을 입력하세요");
		f.content.focus();
		
		return;
	}
	if (f.passwd.value == "") {
		alert("비밀번호를 입력하세요");
		f.passwd.focus();
		
		return;
	}
	
	f.submit();
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
 
 <FORM name='frm' method='POST' action='./update'
		enctype = "multipart/form-data">
		<input type="hidden" name="bbsno" value="${dto.bbsno}">
<input type="hidden" name="col" value="${param.col}">
<input type="hidden" name="word" value="${param.word}">
<input type="hidden" name="nowPage" value="${param.nowPage}">
<input type="hidden" name="oldfile" value="${dto.filename}">
<div class="w3-container" style="width:100%">
	<div class=" w3-bar w3-panel w3-pale-red w3-leftbar w3-border-red">
	  <p>글 수정</p>
	  <div class="w3-right-align">
		작성자:${dto.wname }
		<input type="hidden" name="wname" value=${dto.wname }>
	  </div>
	</div>
	<div class=" w3-border w3-bar w3-light-grey">
	<div class="w3-left"><p>제목:
	   <input type="text" name="title"  style="width: 300px" value="${dto.title}">
		 </p>
	 </div>
	<div class="w3-right">첨부 파일:
	<input type="file" name="filenameMF">
	<div>
	<c:choose>
		<c:when test="${not empty dto.filename }">
		${dto.filename }(${dto.filesize }btye)
			<a href="javascript:fileDown()">
  	<span class="glyphicon glyphicon-download-alt"></span>
  	</a>
		</c:when>
		<c:otherwise>
		파일없음
		</c:otherwise>
	</c:choose>
 
 
 
 	</div>
   </div>
	</div>
	
	<div class="w3-container w3-border w3-large">
	
    <div class="w3-left-align" style="width:100%; height:400px">
        <textarea style="width:100%; height:400px;" name="content">${dto.content}</textarea>
    
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