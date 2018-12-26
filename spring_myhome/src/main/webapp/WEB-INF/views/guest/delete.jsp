<%@page import="guest.GuestDAO"%>

<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    String cp = request.getContextPath();
   
    int num = Integer.parseInt(request.getParameter("num"));

    GuestDAO dao = new GuestDAO();
   
    dao.deleteData(num);
   
    response.sendRedirect(cp + "/myhome/guest/guest.jsp");   
%>