<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ include file="/ssi/ssi.jsp" %>
     
     <%
     	int no= Integer.parseInt(request.getParameter("no"));
     	String passwd= request.getParameter("passwd");
     	String fname= request.getParameter("fname");
     	ImgDAO dao = new ImgDAO();
     
     	Map map = new HashMap();
     	map.put("no", no);
     	map.put("passwd", passwd);
     	boolean pflag=dao.passCheck(map);
     	boolean flag=false;
     	if(pflag){
     		flag=dao.delete(no);
     	}
     
    	String upDir = application.getRealPath("myhome/myimg/storage");
    	if(flag){
    		UploadSave.deleteFile(upDir,fname);
    	}
     	
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/myhome/top.jsp"/><br>


<DIV class="title">번호삭제 처리</DIV>
 
<div class="content">

<%

	if(flag){
		out.print("삭제되었습니다.");
	}else if(pflag==false){
		out.print("비밀번호가 틀렸습니다.");
	}else
		out.print("삭제 실패.");


%>



</div>
  
  <DIV class='bottom'>
    <input type='button' value='홈' onclick="location.href='../index.jsp'">
    <input type='button' value='목록' onclick="location.href='./list.jsp'">
    <input type='button' value='다시시도' onclick="history.back()">
  </DIV>
<!-- *********************************************** -->
<jsp:include page="/myhome/bottom.jsp" flush="false"/>
</body>
</html>