package spring.model.img;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;
@Repository
public class ImgDAO {
	
	public boolean checkRefnum(int no){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(refnum) ");
		sql.append(" from img ");
		sql.append(" where refnum = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			int cnt = rs.getInt(1);
			if(cnt>0)flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(rs, pstmt, con);
		}
		return flag;
	}
	
	public ImgDTO replyRead(int no){
		ImgDTO dto = null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select no, fname, title, grpno, indent, ansnum ");
		sql.append(" from img ");
		sql.append(" where no = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new ImgDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
				dto.setFname(rs.getString("fname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(rs, pstmt, con);
		}
		return dto;
	}
	
	public boolean replyCreate(ImgDTO dto){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into img(no, title, passwd, fname, content, wdate, grpno, indent, ansnum) ");
		sql.append(" values((select nvl(max(no),0)+1 as no from img), ");
		sql.append(" ?,?,?,?,sysdate,?,?,?) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getFname());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getGrpno());
			pstmt.setInt(6, dto.getIndent()+1);
			pstmt.setInt(7, dto.getAnsnum()+1);
			
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0)flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(pstmt, con);
		}
		return flag;
	}
	
	public void upAnsnum(Map map){
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		int grpno = (Integer)map.get("grpno");
		int ansnum = (Integer)map.get("ansnum");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update img ");
		sql.append(" set ansnum = ansnum + 1 ");
		sql.append(" where grpno = ? ");
		sql.append(" and ansnum > ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, grpno);
			pstmt.setInt(2, ansnum);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(pstmt, con);
		}
	}
	
	public void upViewcnt(int no){
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update img ");
		sql.append(" set viewcnt = viewcnt + 1 ");
		sql.append(" where no = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
	}
	

	
	public boolean pwChek(int no, String passwd) {
		boolean flag=false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(no) ");
		sql.append(" from img ");
		sql.append(" where no=? and passwd=? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.setString(2, passwd);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {

				int cnt = rs.getInt(1);
				if(cnt>0)flag= true; //중복된 비번
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return flag;
	}
	
	public boolean passCheck(Map map){

		boolean flag = false;

		Connection con = DBOpen.open();

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		int no = (Integer)map.get("no");

		String passwd = (String)map.get("passwd");

		

		StringBuffer sql = new StringBuffer();

		sql.append(" select count(*) from img ");

		sql.append(" where no = ? ");

		sql.append(" and passwd = ? ");

		

		try {

			pstmt = con.prepareStatement(sql.toString());

			pstmt.setInt(1, no);

			pstmt.setString(2, passwd);

			

			

			rs = pstmt.executeQuery();

			if(rs.next()) {

				int cnt = rs.getInt(1);

				if(cnt>0)flag = true;

			}

			

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}finally {

			DBClose.close(rs, pstmt, con);

		}

		

		return flag;

	}
	
	public int total(Map map){
		int total = 0;
		
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs= null;
		String col = (String)map.get("col");
		String word = (String)map.get("word");
		sql.append(" select count (*) from Img ");
		if(word.trim().length()>0) {
			sql.append(" where "+ col + " like '%'||?||'%' ");
		}
		

		try {
			pstmt= con.prepareStatement(sql.toString());
			if(word.trim().length()>0) {pstmt.setString(1, word);}
			rs=pstmt.executeQuery();
			rs.next();
			total=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstmt,con);
		}
		return total;
	}

public List<ImgDTO> list(Map map){
		List<ImgDTO> list = new ArrayList<ImgDTO>();
		Connection con=DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String col=(String)map.get("col");
		String word=(String)map.get("word");
		int sno=(Integer)map.get("sno");
		int eno=(Integer)map.get("eno");
		sql.append(" select no, fname, title,content, viewcnt, wdate,  ");
		sql.append(" 		grpno, indent, ansnum, r ");
		sql.append(" from( ");
		sql.append(" 	select no, fname, title,content, viewcnt, wdate,  ");
		sql.append(" 		grpno, indent, ansnum, rownum as r ");
		sql.append(" 	from( ");
		sql.append(" 		select no, fname, title,content, viewcnt, to_char(wdate,'yyyy-mm-dd') as wdate,  ");
		sql.append(" 		grpno, indent, ansnum ");
		sql.append(" 		from img ");
		if(word.trim().length()>0)
		sql.append("            where  "+col+ " like  '%'||?||'%'  ");
		sql.append(" 		order by wdate desc ");
		sql.append(" 	) ");
		sql.append(" )where r>=? and r<=? ");
		int r= 0;
		
		try {
			pstmt= con.prepareStatement(sql.toString());
			if(word.trim().length()>0) {
			pstmt.setString(++r, word);}
			pstmt.setInt(++r, sno);
			pstmt.setInt(++r, eno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ImgDTO dto = new ImgDTO();
				dto.setNo(rs.getInt("no"));
				dto.setFname(rs.getString("fname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
public boolean delete(int no){

	boolean flag = false;

	Connection con = DBOpen.open();

	PreparedStatement pstmt = null;

	

	StringBuffer sql = new StringBuffer();

	sql.append(" delete from img ");

	sql.append(" where no = ? ");

	

	try {

		pstmt = con.prepareStatement(sql.toString());

		pstmt.setInt(1, no);

		

		int cnt = pstmt.executeUpdate();

		if(cnt>0)flag = true;

	} catch (SQLException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

	}finally {

		DBClose.close(pstmt, con);

	}

	return flag;

}
	
public boolean updateFile(Map map) {
	boolean flag = false;
	Connection con = DBOpen.open();
	PreparedStatement pstmt=null;
	
	String fname = (String)map.get("fname");
	int no = (Integer)map.get("no");
	
	StringBuffer sql = new StringBuffer();
	sql.append(" update img ");
	sql.append(" set fname = ? ");
	sql.append(" where no = ? ");
	
	try {
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, fname);
		pstmt.setInt(2, no);
		
		int cnt =  pstmt.executeUpdate();
	
		if(cnt>0) flag = true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBClose.close(pstmt, con);
	}		
	return flag;
}

//update
public boolean update(ImgDTO dto){
	boolean flag = false;
	Connection con = DBOpen.open();
	PreparedStatement pstmt=null;
	
	
	StringBuffer sql = new StringBuffer();
	sql.append(" update img ");
	sql.append(" set title =?, ");
	sql.append(" 	 content =?, ");
	sql.append(" 	 passwd =?, ");
	sql.append(" 	 fname =? ");
	sql.append(" where no =? ");
	
	try {
		pstmt=con.prepareStatement(sql.toString());
		pstmt.setString(1, dto.getTitle());
		pstmt.setString(2, dto.getContent());
		pstmt.setString(3, dto.getPasswd());
		pstmt.setString(4, dto.getFname());
		pstmt.setInt(5, dto.getNo());
		
		int cnt=pstmt.executeUpdate();
		if(cnt>0) flag=true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBClose.close(pstmt, con);
	}
	
	
	return flag;
}
	
	public ImgDTO read(int no){
		ImgDTO dto = null;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select no, fname, title, content, ");
		sql.append(" to_char(wdate, 'yyyy-mm-dd') as wdate, viewcnt ");
		sql.append(" from img ");
		sql.append(" where no = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new ImgDTO();
				
				dto.setNo(rs.getInt("no"));
				dto.setFname(rs.getString("fname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWdate(rs.getString("wdate"));
				dto.setViewcnt(rs.getInt("viewcnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return dto;
	}
	
	public List readImg(int no) {
		List list = new ArrayList();
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ");
		sql.append(" 	( ");
		sql.append(" 	select ");
		sql.append(" 		lag(no,2)     over (order by no) pre_no2, ");
		sql.append(" 		lag(no,1)     over (order by no) pre_no1, ");
		sql.append(" 		no, ");
		sql.append(" 		lead(no,1)    over (order by no) nex_no1, ");
		sql.append(" 		lead(no,2)    over (order by no) nex_no2, ");
		sql.append(" 		lag(fname,2)  over (order by no) pre_fname2, ");
		sql.append(" 		lag(fname,1)  over (order by no) pre_fname1, ");
		sql.append(" 		fname, ");
		sql.append(" 		lead(fname,1) over (order by no) nex_fname1, ");
		sql.append(" 		lead(fname,2) over (order by no) nex_fname2 ");
		sql.append(" 	from (  ");
		sql.append(" 		SELECT no, fname   ");
		sql.append("  		FROM img ");
		sql.append(" 		ORDER BY no desc ");
		sql.append(" 	)  ");
		sql.append(" )  ");
		sql.append(" WHERE no = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int[] noArr = {
						rs.getInt("pre_no2"),
						rs.getInt("pre_no1"),
						rs.getInt("no"),
						rs.getInt("nex_no1"),
						rs.getInt("nex_no2"),						
				};				
				String[] fArr = {
						rs.getString("pre_fname2"),
						rs.getString("pre_fname1"),
						rs.getString("fname"),
						rs.getString("nex_fname1"),
						rs.getString("nex_fname2"),
				};
				
				list.add(noArr);
				list.add(fArr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return list;		
	}
	
	public boolean create(ImgDTO dto){
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into img(no, title, passwd, fname, content, wdate, grpno) ");
		sql.append(" values((select nvl(max(no),0)+1 as no from img), ");
		sql.append(" ?, ?, ?, ?, sysdate,(select nvl(max(grpno),0)+1 as grpno from img)) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getFname());
			pstmt.setString(4, dto.getContent());
			
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	}
