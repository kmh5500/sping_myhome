
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %> 

 
<!DOCTYPE html> 
<html> 
<head> 

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

<script type="text/javascript">
function updateFile() {
	var url="updateFile";
	url =url+"?id=${dto.id}";
	url =url+"&oldfile=${dto.fname}";
	
	location.href=url;
}
function updatePwForm() {
	var url="updatePw";
	url =url+"?id=${dto.id}";
	
	location.href=url;
	
}
function Mupdate() {
	var url ="update";
	url=url+"?id=${dto.id}";
	 location.href=url;
	
}
</script>
<style type="text/css">
table td tr{
w3-center;
}
</style>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->

<DIV class="w3-theme w3-padding w3-center">${dto.mname}의 회원정보</DIV>
 
  <TABLE  class='w3-table-all tr{border-bottom:1px solid #ddd}.w3-striped tbody tr:nth-child(even){background-color:#f1f1f1}'> 

    <TR >
      <TD colspan="2"><img src="./storage/${dto.fname}">
      </TD>
    </TR>
     <TR >
      <TH >아이디</TH>
      <TD >${dto.id}</TD>
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
      <TH>우편번호</TH>
      <TD>${dto.zipcode}</TD>
    </TR>
      <TR>
      <TH>주소</TH>
      <TD>${dto.address1}<br>
      ${Utility.checkNull(dto.address2)}</TD>
    </TR>
      <TR>
      <TH>직업</TH>
      <TD>
      직업코드:${dto.job}
      (${Utility.getCodeValue(dto.job)})
      </TD>
    </TR>
    <TR>
      <TH>날짜</TH>
      <TD>${dto.mdate}</TD>
    </TR>

  </TABLE>
  
  <DIV class='w3-center'>
    
    <input type='button'   value='정보수정' onclick="Mupdate()">
    <input type='button'   value='사진수정' onclick="updateFile()">
    <input type='button'   value='패스워드 변경' onclick="updatePwForm()">
  </DIV>

 
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 