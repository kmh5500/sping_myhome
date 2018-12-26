<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/ssi/ssi.jsp" %> 
    
    <%@page import="guest.GuestDTO"%>
<%@page import="java.util.List"%>
<%@page import="guest.GuestDAO"%>

<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    
    
   GuestDAO dao = new GuestDAO();
    List<GuestDTO> lists = dao.getListData();    //DB 전체 자료 가져오기
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
<link rel="stylesheet" href="<%=root%>/data/css/style.css" type="text/css"/>
<script type="text/javascript">
    function sendIt()
    {
        var f = document.myForm;
       
        if(!f.name.value)
        {
            alert("이름을 입력하세요!!!");
            f.name.focus();
            return;
        }
       
        if(!f.content.value)
        {
            alert("내용을 입력하세요!!!");
            f.content.focus();
            return;
        }
       
        f.action = "<%=root%>/myhome/guest/guest_ok.jsp";
        f.submit();
    }
   
    function isDelete(num)  {
        if (confirm("위 자료를 삭제하시겠습니까 ?")) {
            location.href = "<%=root %>/myhome/guest/delete.jsp?num=" + num;
        }
    }
</script>
</head>
<body>


<jsp:include page="/myhome/top.jsp"/><br>


<center>
<br/>&nbsp;<br/>
<table width="600" border="2" cellpadding="0" cellspacing="0"
           bordercolor="#D6D4D6" align="center">
     <tr height="40">
         <td style="padding-left: 20px;" align="left"><b>방 명 록</b></td>
     </tr>
</table>

<br/>&nbsp;<br/>
<form name="myForm" method="post" action="">
<table width="600" border="0" cellpadding="0" cellspacing="0"  align="center">
<tr><td width="600" colspan="4" height="3" bgcolor="#E6D4A6"></td>
</tr>
<tr>
    <td width="60" height="30" bgcolor="#EEEEEE" align="center">작성자</td>
    <td width="240" height="30" style="padding-left: 10px;" align="left" colspan="3">
        <input type="text" name="name" size="35" maxlength="20" class="boxTF"/>
    </td>
</tr>
 
<tr><td width="600" colspan="4" height="1" bgcolor="#E6D4A6"></td></tr>
 <tr>
    <td width="60" height="30" bgcolor="#EEEEEE" align="center">내용</td>
    <td width="540" colspan="3" style="padding-left: 10px;" align="left">
        <textarea  name="content" rows="3" cols="84" class="boxTA" style="height: 50px;"></textarea>
    </td>
 </tr>
 
<tr><td width="600" colspan="4" height="3" bgcolor="#E6D4A6"></td></tr>
<tr>
    <td width="600" colspan="4" height="35" align="right" style="padding-right: 15px;">
        <input type="button" value="등록하기" class="btn2" onclick="sendIt()"/>
        <input type="reset"  value=" 다시입력 " onclick="document.myForm.name.focus();" class="btn2"/>
    </td>
</tr>
</table>
</form>

<br/>
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
<% if(lists.size() == 0) { %>
    <tr><td width="600" colspan="2" height="3" bgcolor="#E6D4A6"></td></tr>
    <tr><td width="600" colspan="2" height="30" bgcolor="#E6D4A6">등록된 게시물이 없습니다.</td></tr>
    <tr><td width="600" colspan="2" height="3" bgcolor="#E6D4A6"></td></tr>   

<%} else { %>
    <tr><td width="600" colspan="2" height="3" bgcolor="#E6D4A6"></td></tr>
    <% for(GuestDTO dto : lists) { %>
        <tr height="25">
            <td width="50%" align="left">No.<%=dto.getNum()%>&nbsp;&nbsp;이름:<%=dto.getName() %></td>
            <td width="50%" align="right"><%=dto.getCreated() %>&nbsp;&nbsp;<a href="javas-ript:isDelete('<%=dto.getNum()%>')">삭제</a></td>
        </tr>
        <tr><td width="600" colspan="2" height="1" bgcolor="#E6D4A6"></td></tr>
        <tr height="60">
            <td align="left" style="padding: 5px 5px 5px 5px;" valign="top">
                <pre><%=dto.getContent() %></pre>
            </td>
        </tr>
        <tr><td width="600" colspan="2" height="3" bgcolor="#E6D4A6"></td></tr>
    <%} %>

<%} %>
</table>
</center>


<jsp:include page="/myhome/bottom.jsp" flush="false"/>
</body>
</html>
