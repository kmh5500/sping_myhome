
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 

<!DOCTYPE html> 
<html> 

<meta charset="UTF-8"> 
<title></title> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
<head> 
<link href="/webtest/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
<DIV class="title">로그인</DIV>
 
<FORM name='frm' method='POST' action='./login'>
  <TABLE>
    <TR>
      <TH class="w3-bar-item w3-red w3-mobile">아이디</TH>
      <TD><input  type="text" name="id"  value='${c_id_val}'>
     <c:choose>
     		<c:when test="${c_id=='Y' }">
     		
         <input type='checkbox' name='c_id' value='Y' checked='checked'> ID 저장 
     		</c:when>
     		<c:otherwise>
         <input type='checkbox' name='c_id' value='Y' > ID 저장 
     		
     		</c:otherwise>
     	</c:choose>
      </TD>
    </TR>
    <TR>
      <TH class="w3-bar-item w3-red w3-mobile">비밀번호</TH>
      <TD><input type="password" name="passwd"></TD>
    </TR>
    <TR>
      <TD colspan="2" style="text-align: center">  
      <A href="./idfine">아이디</A> 
      /
     <A href="./pwfine"> 비밀번호 찾기</A>
     </TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='로그인'>
    <input type='button' value='회원가입' 
    onclick="location.href='./agree'">
  </DIV>
</FORM>
 
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 