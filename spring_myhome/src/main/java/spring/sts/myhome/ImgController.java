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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.model.img.ImgDAO;
import spring.model.img.ImgDTO;
import spring.utility.webtest.Utility;



@Controller
public class ImgController {
	
	@Autowired
	public ImgDAO dao;
	
	
	
	
	@RequestMapping(value="/img/delete",method=RequestMethod.POST)
	public String delete(HttpServletRequest request,
			Model model) {
		
		String upDir = "/img/storage";
	
		upDir = request.getRealPath(upDir);
		int no = Integer.parseInt(request.getParameter("no"));
		String passwd = request.getParameter("passwd");
		
		
		boolean pflag = dao.pwChek(no, passwd);
		boolean flag =false; 
		if(pflag) {
	
			ImgDTO dto = dao.read(no);
			String oldfile = dto.getFname();
			flag=dao.delete(no);
			if(flag==true) {
				Utility.deleteFile(upDir,oldfile);
				return "redirect:/img/list";
			}else {
				return "/error/error";
			}
		}else {
			return "/error/error";
		}
	}
	@RequestMapping(value="/img/delete",method=RequestMethod.GET)
	public String delete() {
		
		return "/img/delete";
	}
	
	@RequestMapping(value="/img/update",method=RequestMethod.POST)
	public String update(ImgDTO dto,Model model) {
		
		Map map = new HashMap();
		map.put("no", dto.getNo());
		map.put("passwd", dto.getPasswd());
		
		
		boolean pflag =dao.passCheck(map);
		boolean flag=false;
		if(pflag) {
		
		if(flag=dao.update(dto)) {
			return "redirect:/img/list";
		}else {
			return "/error/error";
		}
		
		}else {
		
		return "/error/error";
		}
		
	}
	@RequestMapping(value="/img/update",method=RequestMethod.GET)
	public String update(String no,Model model) {
		
		ImgDTO dto = dao.read(Integer.parseInt(no));
		model.addAttribute("dto", dto);
		
		return "/img/update";
	}
	@RequestMapping(value="/img/upFile",method=RequestMethod.POST,
			produces="text/plain;charset=UTF-8")
	public String updateFile(MultipartFile fnameMF,String oldfile,HttpServletRequest request,Model model) {
		String upDir ="/img/storage";
		
		upDir = request.getRealPath(upDir);
	
		int no =Integer.parseInt(request.getParameter("no"));
		
		String fname = request.getParameter("fnameMF");
		
		String filename=oldfile;
		if(fnameMF.getSize()>0) {
			
			
			Utility.deleteFile(upDir, oldfile);
			
			fname =Utility.saveFileSpring(fnameMF,upDir);			
			
		}
		
		
		Map map = new HashMap();
		map.put("no", no);
		map.put("fname", fname);
		boolean flag = dao.updateFile(map);
		if(!flag) {
			Utility.deleteFile(upDir, fname);
			return "/error/error";
			}
		else {
				return "redirect:/img/list";
		}
		
	}
	
	@RequestMapping(value="/img/upFile",method=RequestMethod.GET,
			produces="text/plain;charset=UTF-8")
	public String updateFile() {
		
		return "/img/upFile";
	}
	
	
	
	@RequestMapping(value="/img/create",method=RequestMethod.POST)
	public String create(HttpServletRequest request,ImgDTO dto,Model model) {
		
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
		System.out.println(dto.getFname());
		System.out.println(dto.getFnameMF());
		int filesize = (int) dto.getFnameMF().getSize();
		System.out.println(filesize);
		String fname="";
		String upDir = "/img/storage"; 
		upDir = request.getRealPath(upDir);		
		//업로드 실패시 파일 저장 되는 오류 해결해야됨		
		if (filesize > 0)
			fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);	
		System.out.println(fname);
		System.out.println(filesize);
		dto.setFname(fname);		
		if(dao.create(dto)) {
			model.addAttribute("dto", dto);
			return "redirect:/img/list";
		}else {
			Utility.deleteFile(upDir,fname);
		return "/error/error";
		}
	}
	@RequestMapping(value="/img/create",method=RequestMethod.GET)
	public String create() {
		
		return "/img/create";
	}
	
	
	@RequestMapping("/img/read")
	public String read(int no,Model model) {
		ImgDTO dto = dao.read(no);
		
		dao.upViewcnt(no);
		List list = dao.readImg(no);
		int[] noA = (int[]) list.get(0);
		String[] fA = (String[]) list.get(1);
		String content = dto.getContent();	   
		content = content.replaceAll("\r\n", "<br>");
		dto.setContent(content);
		
		model.addAttribute("list", list);
		model.addAttribute("fA", fA);
		model.addAttribute("noA", noA);
		model.addAttribute("dto", dto);
		return "/img/read";
	}
	
	@RequestMapping("/img/list")
	public String list(HttpServletRequest request) {
		
		String col=Utility.checkNull(request.getParameter("col"));
		String word=Utility.checkNull(request.getParameter("word")); 
		
		if(col.equals("total")) word="";
		//paging관련
		int nowPage =1;
		int recordPerPage=4;
		if(request.getParameter("nowPage")!=null){
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		//db에서 가져올 레코드의 순번
		int sno =((nowPage-1)*recordPerPage)+1;
		int eno =nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		List<ImgDTO> list = dao .list(map);
		int totalRecord =dao.total(map);
		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		
		request.setAttribute("list", list);
		request.setAttribute("paging",paging);
		request.setAttribute("col",col);
		request.setAttribute("word",word);
		return "/img/list";
	}

}
