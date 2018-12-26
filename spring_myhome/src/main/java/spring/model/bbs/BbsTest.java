package spring.model.bbs;

import java.util.*;

public class BbsTest {

	public static void main(String[] args) {
		BbsDAO dao = new BbsDAO();

		//create(dao);
		//read(dao);
		//update(dao);
		//delete(dao);
		//list(dao);
		//upviewcnt(dao);
		passCheck(dao);
	}

	private static void passCheck(BbsDAO dao) {
		Map map = new HashMap();
		
		map.put("bbsno", 2);
		map.put("passwd", "1234");

		if (dao.passCheck(map)) {
			p("성공");
		} else {
			p("실패");
		}
	}

	private static void upviewcnt(BbsDAO dao) {
		int bbsno = 2;
		dao.upviewcnt(bbsno);
	}

	private static void list(BbsDAO dao) {
		List<BbsDTO> list = dao.list();

		for (int i = 0; i < list.size(); i++) {
			BbsDTO dto = list.get(i);

			p(dto);
			p("------------------");
		}
	}

	private static void delete(BbsDAO dao) {
		int bbsno = 2;

		if (dao.delete(bbsno)) {
			p("성공");
		} else {
			p("실패");
		}
	}

	private static void update(BbsDAO dao) {
		int bbsno = 2;
		BbsDTO dto = dao.read(bbsno);

		dto.setWname("re이름변경");
		dto.setTitle("re제목변경");
		dto.setContent("re내용변경");

		if (dao.update(dto)) {
			p("성공");
		} else {
			p("실패");
		}
	}

	private static void read(BbsDAO dao) {
		int bbsno = 1;
		BbsDTO dto = dao.read(bbsno);

		p(dto);
	}

	private static void create(BbsDAO dao) {
		BbsDTO dto = new BbsDTO();

		dto.setWname("이름임");
		dto.setTitle("제목임");
		dto.setContent("내용임");
		dto.setPasswd("1234");

		if (dao.create(dto)) {
			p("성공");
		} else {
			p("실패");
		}
	}

	private static void p(BbsDTO dto) {
		if (dto.getContent() != null) {
			p("번호 : " + dto.getBbsno());
			p("글쓴이 : " + dto.getWname());
			p("제목 : " + dto.getTitle());
			p("내용 : " + dto.getContent());
			p("조회수 : " + dto.getViewcnt());
			p("날짜 : " + dto.getWdate());
		} 
		else {
			p("");
			p("번호 : " + dto.getBbsno());
			p("글쓴이 : " + dto.getWname());
			p("제목 : " + dto.getTitle());
			p("조회수 : " + dto.getViewcnt());
			p("날짜 : " + dto.getWdate());
			p("grpno : " + dto.getGrpno());
			p("indent : " + dto.getIndent());
			p("ansnum : " + dto.getAnsnum());
		}
	}

	private static void p(String string) {
		System.out.println(string);
	}

}
