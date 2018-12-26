
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="../ssi/ssi.jsp" %> 
 <%
 String mname = request.getParameter("mname");
 String email = request.getParameter("email");
 String id = request.getParameter("id");
//String id =null;
 
 MemberDAO dao = new MemberDAO();
 
 if(mname!=null&&email!=null){
	 Map mapid = new HashMap();
	 mapid.put("mname", mname);
	 mapid.put("email",email);
	 String fineid = dao.getFineId(mapid);
	 
	 if(fineid!=null){
		 out.print("id는 "+fineid+"입니다.");	
	 }else{
		 out.print("잘못된 정보입니다.");
	 }
 }else if(mname!=null&&id!=null){
	  Map mappw= new HashMap();
	  mappw.put("mname", mname);
	  mappw.put("id",id);
	  String pw = dao.getFinePw(mappw); 
	  if(pw!=null){
			 out.print("passwd는 "+pw+"입니다.");	
	}else{
			 out.print("잘못된 정보입니다.");
		 }
 }
 
 
 
 
 

 

//  }else if(id==null&&email!=null){
// 	 out.print("잘못된 정보입니다.");
	 
//  }else if(pw!=null&&id!=null){
// 	 out.print("pw는 "+pw+"입니다.");
//  }else if(pw==null&&id!=null){
// 	 out.print("잘못된 정보입니다");
//  }else{
	 
// 	 out.print("오류입니다");
//  }
 
 %>
 