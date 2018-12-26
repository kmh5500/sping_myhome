<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    <c:set var="title" value="나의 여행 블로그"/>
    <c:if test="${not empty sessionScope.id && sessionScope.grade =='A' }">
    <c:set var="title" value="관리자 페이지"/>
    
    </c:if>
    <c:choose>
    	<c:when test="${empty sessionScope.id }">
    		<c:set var="str" value="메인페이지 입니다."></c:set>
    	</c:when>
    	<c:otherwise>
    	
    		<c:set var="str" value="안녕하세요 ${sessionScope.id}님"></c:set>
    	</c:otherwise>
    </c:choose>


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
</head> 
<body> 

<div class="title">${title}</div>
 
<div class="content">
<h1>${str}</h1>
<img src="${pageContext.request.contextPath }/images/kim.jpg"  width="50%"><br>
 <c:choose>
 	<c:when test="${empty sessionScope.id }">
 	
<input type="button" value="로그인" 
onclick="location.href='member/login'">
 	</c:when>
 	<c:otherwise>
<input type="button" value="로그아웃" 
onclick="location.href='member/logout'">
 	
 	</c:otherwise>
 </c:choose>

</div>


</body> 
</html> 