<%@page import="guest.GuestDAO"%>

<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    String cp = request.getContextPath();
%>

<jsp:useBean id="dto" class="guest.GuestDTO">
    <jsp:setProperty name="dto" property="*"/>
</jsp:useBean>

<%
    

    GuestDAO dao = new GuestDAO();
   
    //게시물의 최대값 가져오기
    int maxNum = dao.getMaxNum();
   
    dto.setNum(maxNum + 1);
    dto.setIpAddr(request.getRemoteAddr());
   
    //자료추가
    dao.insertData(dto);
   
    //방명록 페이지로 리다이렉트(추가, 수정, 삭제, 로그인, 로그아웃후에는 리다이렉트)
    response.sendRedirect(cp + "/myhome/guest/guest.jsp");
%>