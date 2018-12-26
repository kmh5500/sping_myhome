
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
function idfine(){
	var f=document.frm;
	var mname=f.mname.value;
	var email=f.email.value;
	var url ="<%=root%>/myhome/idpwfine.jsp";
	
	$.ajax({url:url,dataType:"text",data:{"mname":mname,"email":email},
	success:function(data){$("#idfine").text(data.trim()).css("color","red");}});
	 
}
</script>
</head> 
<!-- *********************************************** -->
<body>
<jsp:include page="/myhome/top.jsp"/><br>
<!-- *********************************************** -->
 <div>
<DIV class="title">아이디 찾기</DIV>
 
<FORM name='frm' method='POST' >
  <TABLE>
    <TR>
      <TH>이름</TH>
      <TD><input type="text" name="mname" required="required"></TD>
    </TR>
    <TR>
      <TH>Email</TH>
      <TD><input type="email" name="email" required="required"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    
    <input type='button' value='찾기' onclick="idfine()">
  </DIV>
</FORM>
  <div id="idfine" style="text-align: conter"></div>
 
 
<!-- *********************************************** -->
<jsp:include page="/myhome/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->
</html> 