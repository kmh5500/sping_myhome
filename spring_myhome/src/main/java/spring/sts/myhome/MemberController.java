 package spring.sts.myhome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import spring.model.member.MemberDAO;
import spring.model.member.MemberDTO;
import spring.utility.webtest.Utility;



@Controller
public class MemberController {
		
	@Autowired
	public MemberDAO dao;
	
	@RequestMapping("/admin/list")
	public String list(HttpServletRequest request) {
		
		String col= Utility.checkNull(request.getParameter("col"));
	 	String word= Utility.checkNull(request.getParameter("word"));
	 	if(col.equals("total")){
	 		word="";
	 	}
	 	//페이징
	 	int nowPage	=1;
	 	int recordPerPage=5;
	 	if(request.getParameter("nowPage")!=null){
	 		nowPage=Integer.parseInt(request.getParameter("nowPage"));
	 	}
	 	//db에서 가져올 순번
	 	int sno= ((nowPage-1)*recordPerPage)+1;
	 	int eno= nowPage* recordPerPage;
	 	
	 	Map map= new HashMap();
	 	map.put("col", col);
	 	map.put("word", word);
	 	map.put("sno", sno);
	 	map.put("eno", eno);
	 	
		int total= dao.total(map);
	 	List<MemberDTO> list =dao.list(map);
	 	
	 	String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
	 			
	 	request.setAttribute("list",list);
	 	
	 	request.setAttribute("paging",paging);
	 	request.setAttribute("col",col);
	 	request.setAttribute("word",word);
	 	request.setAttribute("nowPage", nowPage);
		
		return "/member/list";
				
	}
	
	
	@RequestMapping(value="/member/delete",method=RequestMethod.POST)
	public String delete(String id,HttpSession session ,HttpServletRequest request,
			Model model) {
		
		String upDir = 	request.getRealPath("/member/storage");
		 
		
		String sid = (String)request.getSession().getAttribute("id");
		String oldfile = request.getParameter("oldfile");
		String grade = (String)session.getAttribute("grade");
		
		
		boolean flag = dao.delete(id);
		if(flag){
			if(oldfile!=null && ! oldfile.equals("member.jpg"))
				Utility.deleteFile(upDir, oldfile);
			if(sid!=null && !grade.equals("A")){
				request.getSession().invalidate();
			
			return "redirect:/";
			}else {
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				return "redirect:/admin/list";
			}
		}else {
			return "/error/error";
		}
		
		
		
	}
	
	
	@RequestMapping(value="/member/delete",method=RequestMethod.GET)
	public String delete(String id,HttpSession session,Model model) {
		 if(id==null)id = (String)session.getAttribute("id");
		    model.addAttribute("id", id);
		return "/member/delete";
	}
	
	@RequestMapping(value="/member/updateFile",method=RequestMethod.POST)
	public String updateFile(String id, String oldfile, MultipartFile fnameMF,
			HttpServletRequest request,Model model) {
	

		String upDir = request.getRealPath("/member/storage");
		
		String fname ="member.jpg";
		if(fnameMF.getSize()>0){
			if(oldfile!=null && !oldfile.equals("member.jpg"))
				Utility.deleteFile(upDir,oldfile);
			fname = Utility.saveFileSpring(fnameMF, upDir);
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("fname", fname);

		boolean flag =dao.updateFile(map); 
		
		if(flag) {
			model.addAttribute("id", id);
			return "redirect:/member/read";
		}else {
			Utility.deleteFile(upDir,fname);
			return "/error/error";
		}
		
	}
	@RequestMapping(value="/member/updateFile",method=RequestMethod.GET)
	public String updateFile() {

		
		return "/member/updateFile";
				
	}

	@RequestMapping("/member/idpwfind")
	public String idpwfind() {
		
		return "/member/idpwfind";
	}
	
	@ResponseBody
	@RequestMapping(value="/member/pwfine",method=RequestMethod.GET,
			produces="text/plain;charset=UTF-8")
	public String pwfind(HttpServletRequest request) {
		
		String mname = request.getParameter("mname");
		String email = request.getParameter("id");
		Map map = new HashMap();
		map.put("mname", mname);
		map.put("id",email);
		
		String pw = dao.getpwFind(map);
		String str = null;
		if(pw!=null){
		str = "찾으시는 pw는 "+pw+"입니다.";
		}else{
			str = "잘못된 정보를 입력하였습니다.";
		}
		
		
		
		return  str;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/Idfine",method=RequestMethod.GET,
	produces="text/plain;charset=UTF-8")
	public String idfind(@RequestParam Map<String, String> map) {
//		String mname = request.getParameter("mname");
//		String email = request.getParameter("email");
//		Map map = new HashMap();
		
//		map.put("mname", mname);
//		map.put("email",email);
		
		
		String id = dao.getIdFind(map);
		String str = null;
		
		
		if(id!=null){
			str = "찾으시는 id는 "+id+"입니다.";
		}else{
			str = "잘못된 정보를 입력하였습니다.";
		}
		
		return str;
	}
	
	
	
	@RequestMapping(value="/member/updatePwProc",method=RequestMethod.POST)
	public String updatePw(HttpServletRequest request) {
		String oldpasswd = request.getParameter("oldpasswd");
	 	String passwd = request.getParameter("passwd");
	 	String id = request.getParameter("id");
	 	
	 	Map map = new HashMap();
	 	map.put("id", id);
	 	map.put("oldpasswd", oldpasswd);
	 	map.put("passwd", passwd);
	 	MemberDAO dao = new MemberDAO();
	 	boolean pflag=dao.OldpassCheck(map);
	 	boolean flag=false;
	 	if(pflag)
	 	flag=dao.updatePw(map);
	 	request.setAttribute("pflag", pflag);
	 	request.setAttribute("flag", flag);
	 	
		
		return "/member/updatePwProc";
	}
	
	@RequestMapping(value="/member/updatePw",method=RequestMethod.GET)
	public String updatePw() {
		
		return "/member/updatePw";
	}
	@RequestMapping(value="/member/update",method=RequestMethod.POST)
	public String update(MemberDTO dto,Model model,HttpSession session,HttpServletRequest request) {
		if(dao.update(dto)) {
			if(session.getAttribute("id")!=null &&
					session.getAttribute("grade").equals("A"))
				{
				model.addAttribute("col", request.getAttribute("col"));
				model.addAttribute("word", request.getAttribute("word"));
				model.addAttribute("nowPage", request.getAttribute("nowPage"));
				return "redirect:/admin/list";
				}
			else {
				model.addAttribute("id", dto.getId());
				model.addAttribute("dto", dao.read(dto.getId()));
				return "/member/read";
			}
	
			
		}else {
			return "/error/error";
		}
	}
	
	@RequestMapping("/member/emailProc")
	public String emailProc(String email) {
		return "/member/email_form";
	}
	

	@RequestMapping(value="/member/update",method=RequestMethod.GET)
	public String update(String id ,Model model) {
		MemberDTO dto = dao.read(id);
		model.addAttribute("dto", dto);
		return "/member/update";
	}
	
//	@RequestMapping("/member/read")
//	public String read(String id, HttpSession session, Model model) {
//	//	String id = session.getAttribute("id");
//		if(id==null) {
//			id=(String)session.getAttribute("id");
//		}
//		
//		MemberDTO dto= dao.read(id);
//		model.addAttribute("dto", dto);
//		
//		return "/member/read";
//	}
	@RequestMapping("/member/read")
	public String read(String id, HttpSession session, Model model) {
		//	String id = session.getAttribute("id");
		if(id==null) {
			id=(String)session.getAttribute("id");
		}
		
		MemberDTO dto= dao.read(id);
		model.addAttribute("dto", dto);
		
		return "/member/readu";
	}
	
	@RequestMapping("/member/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpSession session,Model model,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		boolean flag = dao.loginCheck(id, passwd);
		String grade = null;// 회원등급
		if (flag) {// 회원인경우
			grade = dao.getGrade(id);
			request.getSession().setAttribute("id", id);
			
			request.getSession().setAttribute("grade", grade);

			// ----------------------------------------------
			// Cookie 저장, Checkbox는 선택하지 않으면 null 임
			// ----------------------------------------------
			Cookie cookie = null;

			String c_id = request.getParameter("c_id"); // Y, 아이디 저장 여부

			if (c_id != null) { // 처음에는 값이 없음으로 null 체크로 처리
				cookie = new Cookie("c_id", "Y"); // 아이디 저장 여부 쿠키
				cookie.setMaxAge(120); // 2 분 유지
				response.addCookie(cookie); // 쿠키 기록

				cookie = new Cookie("c_id_val", id); // 아이디 값 저장 쿠키
				cookie.setMaxAge(120); // 2 분 유지
				response.addCookie(cookie); // 쿠키 기록

			} else {
				cookie = new Cookie("c_id", ""); // 쿠키 삭제
				cookie.setMaxAge(0);
				response.addCookie(cookie);

				cookie = new Cookie("c_id_val", ""); // 쿠키 삭제
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			// ---------------------------------------------
		}
		model.addAttribute("flag", flag);

		return "/member/loginProc";
	}
	@RequestMapping(value="/member/login" , method=RequestMethod.GET)
	public String login(HttpServletRequest request) {
		String c_id = "";     // ID 저장 여부를 저장하는 변수, Y
		String c_id_val = ""; // ID 값
		 
		Cookie[] cookies = request.getCookies(); 
		Cookie cookie=null; 
		 
		if (cookies != null){ 
		 for (int i = 0; i < cookies.length; i++) { 
		   cookie = cookies[i]; 
		 
		   if (cookie.getName().equals("c_id")){ 
		     c_id = cookie.getValue();     // Y 
		   }else if(cookie.getName().equals("c_id_val")){ 
		     c_id_val = cookie.getValue(); // user1... 
		   } 
		 } 
		} 

		/*----쿠키설정 내용 끝----------------------------*/
		 
		request.setAttribute("c_id", c_id);
		request.setAttribute("c_id_val", c_id_val);
		
		return "/member/login"; 
			
	}
	@ResponseBody
	@RequestMapping(value="/member/emailCheck", method=RequestMethod.GET,
	produces="text/plain;charset=UTF-8")
	public String emailCheck(String email) {
		boolean flag = dao.duplicateEmail(email);
		String str = null;
		
		if(flag) {
			str = "중복되어서 사용할 수 없습니다.";
		}else {
			str = "중복 아님 , 사용 가능합니다.";
		}
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/idCheck", method=RequestMethod.GET,
			produces="text/plain;charset=UTF-8")
	public String idCheck(String id) {
		boolean flag = dao.duplicateId(id);
		String str = null;
		
		if(flag) {
			str = "중복되어서 사용할 수 없습니다.";
		}else {
			str = "중복 아님 , 사용 가능합니다.";
		}
		return str;
	}

	
	
	@RequestMapping("/member/createProc")
	public String pcreate(MemberDTO dto, HttpServletRequest request, Model model,String grade) {
		String str = null;
		System.out.println(grade); 
		String url = "/member/pcreate";
		if(dao.duplicateId(dto.getId())) {
			str="존재하는 아이디입니다 .id중복 확인을 하세요";
			model.addAttribute("str", str);
		}else if(dao.duplicateEmail(dto.getEmail())) {
			str="존재하는 이메일입니다 .Email중복 확인을 하세요";
			model.addAttribute("str", str);
		}else {
			String upDir= request.getRealPath("/member/storage");
			
			int size = (int)dto.getFnameMF().getSize();
			String fname=null;
				if(size>0){
				fname=Utility.saveFileSpring(dto.getFnameMF(), upDir);
				}else{
					fname="member.jpg";
				}
			dto.setFname(fname);
			
			boolean flag= dao.create(dto);
			
			model.addAttribute("flag",flag);
			url="/member/createProc";
			
		}
		return url;
	}
	
	
	@RequestMapping("/member/create")
	public String create() {
		
		return "/member/create";
	}
	
//	회원 분류 create H, A, P
	@RequestMapping("/member/createH")
	public String Hcreate() {
		
		return "/member/createH";
	}
	
	@RequestMapping("/member/createA")
	public String Acreate() {
		
		return "/member/createA";
	}
	
	@RequestMapping("/member/createP")
	public String Pcreate() {
		
		return "/member/createP";
	}
	
	
	
//회원 분류 create 끝
	
	
	
	
	
	@RequestMapping("/member/agree")
	public String agree() {
		
		return "/member/agree";
	}
	@RequestMapping("/member/choice")
	public String choice() {
		
		return "/member/choice";
	}
	
	
		
		
		
		
		
	
	
		
	

}
