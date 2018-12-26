<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ include file="/ssi/ssi.jsp" %>



    <%
	String tempDir = "/myhome/myimg/temp";
	String upDir = "/myhome/myimg/storage"; 
	
	tempDir = application.getRealPath(tempDir);
	upDir = application.getRealPath(upDir);
	
	
	UploadSave upload = new UploadSave(request,-1,-1,tempDir);
	String title = UploadSave.encode(upload.getParameter("title"));
	
	String content =UploadSave.encode(upload.getParameter("content"));
	String passwd = upload.getParameter("passwd");
	//업로드 실패시 파일 저장 되는 오류 해결해야됨
	FileItem fileItem = upload.getFileItem("fname");
	String fname = null;
	
	int filesize = (int)fileItem.getSize();

	if (filesize > 0) {
		fname = UploadSave.saveFile(fileItem, upDir);				
	}
	
	
	
	ImgDAO dao = new ImgDAO();
	ImgDTO dto = new ImgDTO();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setPasswd(passwd);
	dto.setFname(fname);
	System.out.println(dto.getTitle());
	System.out.println(dto.getContent());
	System.out.println(dto.getPasswd());
	System.out.println(dto.getFname());
	boolean flag = dao.create(dto);
	
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/myhome/top.jsp"/><br>



<DIV class="title">결과 처리</DIV>

<div class="content">
<%
	if (flag) {
		out.print("글 작성이 완료 되었습니다.");
	}
	else {
		out.print("글 작성을 실패했습니다.");
	}
%>
</div>

<%
	if (flag) {
%>  
  <DIV class='bottom'>
    <input type='button' value='목록' onclick="location.href='./list.jsp'">
  </DIV>
<%
	}
	else {
%>
  <DIV class='bottom'>
    <input type='button' value='다시 시도' onclick="history.back()">
    <input type='button' value='목록' onclick="location.href='./list.jsp'">
  </DIV>
<%
	}
%>
 
<!-- *********************************************** -->
<jsp:include page="/myhome/bottom.jsp" flush="false"/>
</body>
</html>