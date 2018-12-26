<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ include file="/ssi/ssi.jsp" %>

	
    <%
   	String tempDir = "/myhome/myimg/temp";
   	String upDir = "/myhome/myimg/storage"; 
   	
   	tempDir = application.getRealPath(tempDir);
   	upDir = application.getRealPath(upDir);
   	
   	UploadSave upload = new UploadSave(request,-1,-1,tempDir);
   	
   	
 
   	int no = Integer.parseInt(upload.getParameter("no"));
   	String title = UploadSave.encode(upload.getParameter("title"));
   	
   	String content =UploadSave.encode(upload.getParameter("content"));
   	String passwd = upload.getParameter("passwd");
   	String oldfname =UploadSave.encode(upload.getParameter("oldfname"));
   	//업로드 실패시 파일 저장 되는 오류 해결해야됨
   	FileItem fileItem = upload.getFileItem("fname");
   	String fname = oldfname;
   	
   	int filesize = (int)fileItem.getSize();

   	if (filesize > 0) {
   		fname = UploadSave.saveFile(fileItem, upDir);
   	}
   	
   	
   	
   	ImgDAO dao = new ImgDAO();
   	ImgDTO dto = new ImgDTO();
   	Map map = new HashMap();
   	map.put("no", no);
   	map.put("passwd",passwd);
   	dto.setNo(no);
   	dto.setTitle(title);
   	dto.setContent(content);
   	dto.setPasswd(passwd);
   	dto.setFname(fname);
   	System.out.println(dto.getNo());
   	System.out.println(dto.getTitle());
   	System.out.println(dto.getContent());
   	System.out.println(dto.getPasswd());
   	System.out.println(dto.getFname());
   	boolean pflag = dao.passCheck(map);
   	boolean flag = false;
   	if(pflag){
   	flag = dao.update(dto);
   	}
   	if(flag){
   		UploadSave.deleteFile(upDir, oldfname);
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



<DIV class="title">결과 처리</DIV>

<div class="content">
<%
	if (!pflag) {
		out.print("비밀번호가 틀렸습니다.");%>
		<input type='button' value='다시 시도' onclick="history.back()">
		<% 
	}else if(pflag==true&&flag==true){
		out.print("글 수정이 완료 되었습니다.");
		
		%>
		<input type='button' value='목록' onclick="location.href='./list.jsp'">
	<%}else {
		out.print("글 수정을 실패했습니다."); %>
	<% }%>
	  <input type='button' value='다시 시도' onclick="history.back()">
    <input type='button' value='목록' onclick="location.href='./list.jsp'">

</div>


 
<!-- *********************************************** -->
<jsp:include page="/myhome/bottom.jsp" flush="false"/>
</body>
</html>