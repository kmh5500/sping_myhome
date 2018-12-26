package spring.sts.myhome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.bbs.BbsDAO;
import spring.model.bbs.BbsDTO;
import spring.utility.webtest.Utility;

@Controller
public class BbsController {

	@Autowired
	private BbsDAO dao;

	@Autowired
	private BbsService mgr;

	@RequestMapping(value = "/bbs/delete", method = RequestMethod.POST)
	public String delete(int bbsno, String passwd, String oldfile, HttpServletRequest request,Model model) {

		Map map = new HashMap();
		map.put("passwd", passwd);
		String upDir = request.getRealPath("/bbs/storage");
		map.put("bbsno", bbsno);
		boolean pflag = dao.passCheck(map);
		
		if (pflag) {
			if (dao.delete(bbsno)) {
				Utility.deleteFile(upDir, oldfile);
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				
				return "redirect:/bbs/list";
			} else {
				return "/error/passwdError";
			}
		} else {
			
			return "/error/passwdError";
		}

	}

	@RequestMapping(value = "/bbs/delete", method = RequestMethod.GET)
	public String delete(int bbsno, HttpServletRequest request) {

		boolean flag = dao.checkRefnum(bbsno);
		request.setAttribute("flag", flag);
		return "/bbs/delete";
	}

	@RequestMapping(value = "/bbs/reply", method = RequestMethod.POST)
	public String reply(BbsDTO dto, HttpServletRequest request) {

		String upDir = request.getRealPath("/view/storage");

		int filesize = (int) dto.getFilenameMF().getSize();
		String filename = "";
		if (filesize > 0)
			filename = Utility.saveFileSpring(dto.getFilenameMF(), upDir);

		dto.setFilename(filename);
		dto.setFilesize(filesize);

		boolean flag = mgr.reply(dto);

		String str = "redirect:/bbs/list";
		if (flag) {

			return str;
		} else {
			if (!filename.equals("")) {
				Utility.deleteFile(upDir, filename);
				str = "/error/error";
			}
			return str;

		}
	}

	@RequestMapping(value = "/bbs/reply", method = RequestMethod.GET)
	public String reply(int bbsno, Model model) {
		BbsDTO dto = dao.replyRead(bbsno);

		model.addAttribute("dto", dto);

		return "/bbs/reply";

	}

	@RequestMapping(value = "/bbs/update", method = RequestMethod.POST)
	public String update(BbsDTO dto, HttpServletRequest request, String oldfile ,Model model) {

		String upDir = request.getRealPath("/view/storage");

		Map map = new HashMap();
		map.put("bbsno", dto.getBbsno());
		map.put("passwd", dto.getPasswd());

		int filesize = (int) dto.getFilenameMF().getSize();
		String filename = "";
		boolean pflag = dao.passCheck(map);

		if (pflag) {
			if (filesize > 0) {
				if (oldfile != null) {
					Utility.deleteFile(upDir, oldfile);
				}
				filename = Utility.saveFileSpring(dto.getFilenameMF(), upDir);

			}
			dto.setFilename(filename);
			dto.setFilesize(filesize);

			if (dao.update(dto)) {
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				return "redirect:/bbs/list";
			} else {
				return "/error/error";
			}

		} else {
			return "/error/passwdError";

		}

	}

	@RequestMapping(value = "/bbs/update", method = RequestMethod.GET)
	public String update(int bbsno, Model model) {

		BbsDTO dto = dao.read(bbsno);
		model.addAttribute("dto", dto);

		return "/bbs/update";
	}

	@RequestMapping(value = "/bbs/create", method = RequestMethod.POST)
	public String create(BbsDTO dto, HttpServletRequest request,Model model) {

		int filesize = (int) dto.getFilenameMF().getSize();
		String filename="";
		String basePath = request.getRealPath("/bbs/storage");
		if (filesize > 0)
			filename= (Utility.saveFileSpring(dto.getFilenameMF(), basePath));
		dto.setFilesize(filesize);
		dto.setFilename(filename);
		
		boolean flag = dao.create(dto);
		
		if (flag) {

			return "redirect:/bbs/list";
		} else {
			if (!filename.equals("")) {
				Utility.deleteFile(basePath, dto.getFilename());
			}	return "/error/error";
		}
	
	
	}

	@RequestMapping(value = "/bbs/create", method = RequestMethod.GET)
	public String create() {

		return "/bbs/create";
	}

	@RequestMapping("/bbs/read")
	public String read(int bbsno, Model model) {

		dao.upviewcnt(bbsno);

		BbsDTO dto = dao.read(bbsno);

		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");
		dto.setContent(content);
		model.addAttribute("dto", dto);

		return "/bbs/read";
	}

	@RequestMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {

		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		// 검색 관련 처리

		if (col.equals("total")) {
			word = "";
		}

		int nowPage = 1;
		int recordPerPage = 5;
		// paging 관련 처리

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		// 처음 가져온 nowPage의 값이 null일 경우 형변환을 시도하면 Exception에러가 날 수 있으므로

		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;
		// DB에서 가져올 레코드의 첫번호, 끝번호 설정

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<BbsDTO> list = dao.list(map);

		int totalRecord = dao.total(map);

		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		// 전체 레코드 개수는 col, word 필요
		// 검색 시에도 페이징을 해줘야 하기 때문에

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("col", col);
		model.addAttribute("word", word);

		return "/bbs/list";
	}

}
