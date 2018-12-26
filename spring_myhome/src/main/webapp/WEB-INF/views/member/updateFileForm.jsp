
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="../ssi/ssi.jsp" %> 
 
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
</style> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">

</head> 
<!-- *********************************************** -->
<body>
<jsp:include page="/myhome/top.jsp"/><br>
<!-- *********************************************** -->
 
<DIV class="w3-center">파일수정</DIV>
 
<FORM name='frm' method='POST' action='./updateFileProc.jsp'
		enctype="multipart/form-data">
		<input type="hidden" name="id" value="<%=request.getParameter("id") %>">
		<input type="hidden" name="oldfile" value="<%=request.getParameter("oldfile") %>">
  <TABLE class='w3-table w3-center'>
   <TR>
      <TD colspan="2"  ><img src="./storage/<%=request.getParameter("oldfile") %>">
      </TD>
    </TR>
    <TR>
      <TH>파일</TH>
      <TD><input type="file" name="fname"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='수정'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 
<!-- *********************************************** -->
<jsp:include page="/myhome/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->
</html> 