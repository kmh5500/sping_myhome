package spring.model.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MembetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberDAO dao= new MemberDAO();
		//create(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
	//	list(dao);
		total(dao);
	}

	private static void total(MemberDAO dao) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		String col="mname";
		String word="발";
		map.put("col", col);
		map.put("word", word);
		
		System.out.println(dao.total(map));
	}

	private static void list(MemberDAO dao) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		String col="mname";
		String word="개";
		int sno=1;
		int eno=5;
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		List<MemberDTO> list= dao.list(map);
		for(int i=0;i<list.size();i++) {
			MemberDTO dto= list.get(i);
			p(dto);
		}
	}

	private static void delete(MemberDAO dao) {
		// TODO Auto-generated method stub
		if(dao.delete("강명훈"))p("삭제 성공");
		else p("삭제 실패");
	}

	private static void update(MemberDAO dao) {
		// TODO Auto-generated method stub
		MemberDTO dto= dao.read("강명훈");
		if(dao.update(dto))p("수정 성공");
		else p("수정 실패");
	}

	private static void read(MemberDAO dao) {
		// TODO Auto-generated method stub
		MemberDTO dto= dao.read("강명훈");
		p(dto);
	}

	private static void p(MemberDTO dto) {
		// TODO Auto-generated method stub
		System.out.println("id: "+dto.getId());
		System.out.println("mname: "+dto.getMname());
		System.out.println("tel: "+dto.getTel());
		System.out.println("email: "+dto.getEmail());
		System.out.println("zipcode: "+dto.getZipcode());
		System.out.println("address1: "+dto.getAddress1());
		System.out.println("address2: "+dto.getAddress2());
		System.out.println("job: "+dto.getJob());
		System.out.println("mdate: "+dto.getMdate());
		System.out.println("fname: "+dto.getFname());
		System.out.println("grade: "+dto.getGrade());
		
	}

	private static void create(MemberDAO dao) {
		// TODO Auto-generated method stub
		MemberDTO dto= new MemberDTO();
		dto.setId("강명훈");
		dto.setPasswd("1234");
		dto.setMname("솔데스크");
		dto.setTel("010-7878-7777");
		dto.setEmail("kmh@email.com");
		dto.setZipcode("98765");
		dto.setAddress1("경기도 남양주시");
		dto.setAddress2("와부읍");
		dto.setJob("백수");
		dto.setFname("man.jsp");
		dto.setGrade("H");
		if(dao.create(dto))p("등록 성공");
		else p("등록실패");
	}

	private static void p(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}

}
