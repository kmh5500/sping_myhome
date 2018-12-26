
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


<script type="text/javascript">
function iread(no){
	var url = "read";
	url = url + "?no="+no;
	location.href = url;
}

</script>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
  <div class="w3-center">
<form action="./list" method="post">
<select name="col">
 	<option value="No"
	<c:if test="${col =='name'}">selected</c:if>>No</option>
 	
 	<option value="title"
	<c:if test="${col=='title' }">selected</c:if>>
 	제목</option>
 	<option value="content"
	<c:if test="${col=='content' }">selected</c:if>>
 	제목</option>
 	<option value="total">전체</option>
  </select>
 
  <input type="text" name="word" value="${param.word }">
  <button>검색</button>
  <button type="button" onclick="location.href='./create'">등록</button>
  </form>
 </div>
  
<!-- !PAGE CONTENT! -->
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">
  <!-- First Photo Grid-->
  <h2>목록</h2>
  <div class="w3-row-padding w3-padding-16 w3-center" id="food">
  
  
   <%-- <% if(list.size()==0) {  %>
    <tr>
	    <td colspan="8">
		등록된 글이 없습니다.
		</td>
	</tr>
<%}else {for(int i = 0 ;i <list.size();i++){  ImgDTO dto = list.get(i);%> --%>
  
  
  <c:forEach var="dto" items="${list }">
   <div class="w3-quarter">
      <img src='./storage/${dto.fname}' alt="Sandwich" style="width:100%">
      <h3><a href="javascript:iread('${dto.no}')">${dto.title}</a></h3>
      <p>${dto.content}</p>
    </div>
  </c:forEach>

 </div>
  <DIV class='bottom'>
   
   ${paging}
   
  </DIV>
 </div>
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 