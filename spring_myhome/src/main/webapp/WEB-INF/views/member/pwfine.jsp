
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
<link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js">
</script>
<script type="text/javascript">

$.ajaxSetup({
    error: function(jqXHR, exception) {
        if (jqXHR.status === 0) {
            alert('Not connect.\n Verify Network.');
        }
        else if (jqXHR.status == 400) {
            alert('Server understood the request, but request content was invalid. [400]');
        }
        else if (jqXHR.status == 401) {
            alert('Unauthorized access. [401]');
        }
        else if (jqXHR.status == 403) {
            alert('Forbidden resource can not be accessed. [403]');
        }
        else if (jqXHR.status == 404) {
            alert('Requested page not found. [404]');
        }
        else if (jqXHR.status == 500) {
            alert('Internal server error. [500]');
        }
        else if (jqXHR.status == 503) {
            alert('Service unavailable. [503]');
        }
        else if (exception === 'parsererror') {
            alert('Requested JSON parse failed. [Failed]');
        }
        else if (exception === 'timeout') {
            alert('Time out error. [Timeout]');
        }
        else if (exception === 'abort') {
            alert('Ajax request aborted. [Aborted]');
        }
        else {
            alert('Uncaught Error.n' + jqXHR.responseText);
        }
    }
});
function pwfine(){
	var f=document.frm;
	var mname=f.mname.value;
	var id=f.id.value;
	var url ="<%=root%>/myhome/idpwfine.jsp";
	
	$.ajax({url:url,dataType:"text",data:{"mname":mname,"id":id},
	success:function(data){$("#pwfine").text(data.trim()).css("color","red");}});
	 
}
</script>
</head> 
<!-- *********************************************** -->
<body>
<jsp:include page="/myhome/top.jsp"/><br>
<!-- *********************************************** -->
 <div>
<DIV class="title">비밀번호 찾기</DIV>
 
<FORM name='frm' method='POST' >
  <TABLE>
    <TR>
      <TH>이름</TH>
      <TD><input type="text" name="mname" required="required"></TD>
    </TR>
    <TR>
      <TH>ID</TH>
      <TD><input type="text" name="id" required="required"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    
    <input type='button' value='찾기' onclick="pwfine()">
  </DIV>
</FORM>
  <div id="pwfine" style="text-align: conter"></div>
 
 
<!-- *********************************************** -->
<jsp:include page="/myhome/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->
</html> 