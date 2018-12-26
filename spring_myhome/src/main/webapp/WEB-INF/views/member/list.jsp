
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 



<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
search{
text-align center;
margin: 3px auto;
}
</style> 
<script type="text/javascript">
function read(id) {
	var url="${root}/member/read";
	url = url+"?id="+id;
	url = url+"&col=${col}";
	url = url+"&word=${word}";
	url = url+"&nowPage=${nowPage}";
	location.href=url;
}

</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">회원목록</DIV>
 <div class="search">
<FORM name='frm' method='POST' action='./list'>
  <select name="col">
  	<option value="id">아이디
  	<c:if test="${col=='id'}">selected</c:if>
  	</option>
  	<option value="email">이메일
  	<c:if test="${col=='email' }">selected</c:if>
  	</option>
  	<option value="mname">성명
  	<c:if test="${col=='mname' }">selected</c:if>
  </option>
  	<option value="total">전체출력</option>
  </select>
  <input type="text" name ="word" value= "${word}">
  <button>검색</button>
  <button onclick="location.href='./create.do'">회원가입</button>
  </FORM>
  </div>
  
  <div class="container">
  <h2><span class="glyphicon glyhicon-th-list"></span>
  회원 목록
  </h2>
 
 <c:forEach var="dto" items="${list }">
 
 

  <TABLE class="table table-hover">
    <TR>
    	<td rowspan="5" style="width: 20%"><img src='${root }/member/storage/${dto.fname}' width="200px" heigh="200px"> </td>
      <TH style="width: 20%">아이디</TH>
      <TD style="width: 50%">
      <a href="javascript:read('${dto.id}')">
      ${dto.id}</a></TD>
    </TR>
    <TR>
      <TH>성명</TH>
      <TD>${dto.mname}</TD>
    </TR>
     <TR>
      <TH>전화번호</TH>
      <TD>${dto.tel}</TD>
    </TR>
     <TR>
      <TH>이메일</TH>
      <TD>${dto.email}</TD>
    </TR>
     <TR>
      <TH>주소</TH>
      <TD><${dto.address1}<br>
     ${dto.address2}</TD>
    </TR>
     
    
  </TABLE>
   </c:forEach>

  <DIV class='bottom'>
  ${paging}
    <input type='submit' value=''>
    <input type='button' value='' onclick="location.href=''">
  </DIV>
</div>

 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 