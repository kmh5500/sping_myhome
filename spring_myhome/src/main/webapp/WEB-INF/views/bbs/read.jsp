
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">


</head> 

<script type="text/javascript">
function blist() {
	var url = "./list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function bupdate() {
	var url = "./update";
	url = url + "?bbsno=${param.bbsno}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function bdelete() {
	var url = "./delete";
	url = url + "?bbsno=${param.bbsno}";
	url = url + "&oldfile=${dto.filename}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function breply() {
	var url = "./reply";
	url = url + "?bbsno=${param.bbsno}";
	location.href = url;
}
function fileDown() {
	var url="${root}/download";
	url = url+"?filename=${dto.filename}";
	url= url+"&dir=/bbs/storage";
	
	location.href = url;
}
</script>



<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<div class="w3-container" style="width:100%">
	<div class=" w3-bar w3-panel w3-pale-red w3-leftbar w3-border-red">
	  <p>게시글 내용</p>
	  <div class="w3-right-align">
	  작성자: ${dto.wname} 
	  조회수: ${dto.viewcnt}
	  등록일: ${dto.wdate}
	  </div>
	</div>
	<div class=" w3-border w3-bar w3-light-grey">
	<div class="w3-left"><p>제목: ${dto.title}</p></div>
	<div class="w3-right">첨부 파일:
	
	<c:choose>
		<c:when test="${not empty dto.filename }">${dto.filename }(${dto.filesize }.byte)
		<a href="javascript:fileDown()">
  	<span class="glyphicon glyphicon-download-alt"></span>
  	</a>(${dto.filesize/1024 }KB)
		
		</c:when>
		<c:otherwise>
		파일없음
		</c:otherwise>
	</c:choose>
 </div>
	</div>
	
	<div class="w3-container w3-border w3-large">
	
    <div class="w3-left-align" style="width:100%; height:400px"><p>${dto.content}</p></div>
   
  </div>
	
</div>
 
  <DIV class='w3-center'>
    <input type='button' value='목록' onclick="blist()">
    <button onclick="bupdate()">수정</button>
    <button onclick="bdelete()">삭제</button>
    
  </DIV>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 