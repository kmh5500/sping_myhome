
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 
 
<!DOCTYPE html> 
<html> 
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<meta charset="UTF-8"> 
<title></title> 


<script type="text/javascript">
function bread(bbsno) {
	var url = "./read";
	url = url + "?bbsno=" + bbsno;
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function fileDown(filename) {
	var url="${root}/download";
	url = url+"?filename="+filename;
	url= url+"&dir=/bbs/storage";
	
	location.href = url;
	
	
}
function NoId(){
	alert("로그인 하시오.");
}

</script>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->

<div class="w3-center">

 <form name="frm" method="post" action="./list">
  <select name="col">
   <option value="wname" <c:if test="${col =='wname'}">selected</c:if>>성명</option>
  <option value="title" <c:if test="${col =='title'}">selected</c:if>>제목</option>
	<option value="content"
					<c:if test="${col =='content'}">selected</c:if>>내용</option>
  <option value="total"
					<c:if test="${col =='total'}">selected</c:if>>전체</option>
 
</select> 


  <input type="text" name="word" value="${param.word}">
  
  <button>검색</button>
  
  <button type="button" onclick="location.href='./create'">등록</button>
 	
 </form>

</div>

<div class="">

<h2>
게시판 목록</h2>

  <table class="w3-table w3-striped w3-border">
    <thead>
  <TR class="w3-red">
 
      <TH>번호</TH>
      <TH>제목</TH>
      <TH>작성자</TH>
      <TH>조회수</TH>
      <TH>등록일</TH>
      <TH>파일명</TH> 
    </TR>
   </thead> 
   <c:choose>
  <c:when test="${empty list }">
					<tbody>
						<tr>
							<td colspan="6">등록된 글이 없습니다.</td>
						</tr>
					</tbody>
				</c:when>
				
				<c:otherwise>
					<c:forEach var="dto" items="${list }"> 
					
					
					   <TR>
    	<td>${dto.bbsno}</td>
    	
    	<td>
    	
    	<a href="javascript:bread('${dto.bbsno}')">${dto.title}</a>
    	<%--  <%
    	 for (int r=0; r<dto.getIndent(); r++) {
    		 out.print("&nbsp;&nbsp;");
    	 }
    	 if (dto.getIndent() > 0) {
    		 out.print("<img src='../images/re.jpg'>");
    	 }
    	 %>
		 <a href="javascript:bread('<%=dto.getBbsno() %>')"><%=dto.getTitle() %></a>
		 <%
		 if (Utility.compareDay(dto.getWdate())) {
			 out.print("<img src='../images/new.gif'>");
		 }
		 %> --%>
    	</td>
    	<td>${dto.wname}</td>
    	<td>${dto.viewcnt}</td>
    	<td>${dto.wdate}</td>
    	<td>
    		<c:choose>
    			<c:when test="${not empty dto.filename }">
    			<a href="javascript:fileDown('<${dto.filename}')">
				 <i class="glyphicon glyphicon-file"></i>
				</a>${dto.filename }
    			</c:when>
    			<c:otherwise>
    			파일 없음
    			</c:otherwise>
    		</c:choose>
   	
    	</td>
    </TR> 
  <!--  </tbody>   -->  
				</c:forEach>
				
				</c:otherwise>
   
   </c:choose>

  </TABLE>
  
  <DIV class=''>
  ${paging }
  </DIV>

</div>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 