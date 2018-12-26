package spring.model.img;

import org.springframework.web.multipart.MultipartFile;

public class ImgDTO {

                             
		private int		no ;
		private String	fname;
		private String	title;
		private String	passwd;
		private String	content;
		private	String	wdate;
		private int		viewcnt;
		private int		grpno;
		private int		indent;
		private int		ansnum;
		private MultipartFile fnameMF;
		
		
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getWdate() {
			return wdate;
		}
		public void setWdate(String wdate) {
			this.wdate = wdate;
		}
		public int getViewcnt() {
			return viewcnt;
		}
		public void setViewcnt(int viewcnt) {
			this.viewcnt = viewcnt;
		}
		public int getGrpno() {
			return grpno;
		}
		public void setGrpno(int grpno) {
			this.grpno = grpno;
		}
		public int getIndent() {
			return indent;
		}
		public void setIndent(int indent) {
			this.indent = indent;
		}
		public int getAnsnum() {
			return ansnum;
		}
		public void setAnsnum(int ansnum) {
			this.ansnum = ansnum;
		}
		public MultipartFile getFnameMF() {
			// TODO Auto-generated method stub
			return fnameMF;
		}
		public void setFnameMF(MultipartFile fnameMF) {
			this.fnameMF = fnameMF;
		}
		
			
}