
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 


<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
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
 
<script type="text/javascript">

function iupdate() {
	var url = "./update"
	url += "?no=${dto.no}";
	location.href = url;
}

function idelete() {
	var url = "./delete"
	url += "?no=${dto.no}";
	url += "&fname=${dto.fname}";
	
	location.href = url;
}

function ilist() {
	var url = "./list"
	location.href = url;
}

function iread(no) {
	var url = "./read"
	url += "?no=" + no;
	location.href = url;
}

function ireply() {
	var url = "./reply"
		url += "?no=${dto.no}";
	location.href = url;
}
</script>
 
 
 
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
  <div class="w3-container w3-border w3-bar w3-center" style="width:100%">
<DIV class="w3-container w3-border w3-bar" style="width:100%">이미지 정보</DIV>

<div class="">
 <img style="width:300px; height:300px;" src="./storage/${dto.fname}">
</div>

<br>

  <TABLE class="w3-table-center"style="width:30%; height:20%;">
    <tr>
	
<c:forEach var="r" begin="0" end ="4" >
<c:choose>	
	<c:when test="${empty fA[r]}">
			
		  <td class="img">
		   <img src="./storage/null.png" width="100px" height="100px">
		  </td>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${noA[r] == dto.no}">

			  <td class="img">
			   <a href="javascript:iread('${noA[r]}')">
		  		<img class="now" src="./storage/${fA[r] }">
		  	   </a>
			  </td>				
			</c:when>
			<c:otherwise>
		
			  <td class="img">
			   <a href="javascript:iread('${noA[r]}')">
		  		<img src="./storage/${fA[r] }" width="100px" height="100px">
		  	   </a>
			  </td>				
			</c:otherwise>
		</c:choose>
</c:otherwise>
</c:choose>
</c:forEach>
   
    </tr>
 </table>
 <br>
   
 <table class="w3-table-centered">
    <TR>
      <TH class="">제 &nbsp;목</TH>
      <TD colspan="4">${dto.title}</TD>
    </TR>
    <TR>
      <TH class="">내 &nbsp;용</TH>
      <TD colspan="4">${dto.content}</TD>
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
  
  <DIV class='bottom'>
    
    <input type='button' value='정보 수정' onclick="iupdate()">
    <input type='button' value='이미지 삭제' onclick="idelete()">
    <input type='button' value='목록' onclick="ilist()">
    
  </DIV>
 </div>
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 