package spring.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;
@Repository
public class MemberDAO {
	
	
	public String getGrade(String id){
		String grade = null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select grade from member ");
		sql.append(" where id = ? ");
		 
		try {
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, id);
		 
		rs = pstmt.executeQuery();
		if (rs.next()){
		grade = rs.getString("grade");
		}
		 
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} finally{
		DBClose.close(rs, pstmt, con);
		}
		 
		return grade;
		}

	
	public boolean loginCheck(String id, String pw){
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from member ");
		sql.append(" where id= ? ");
		sql.append(" and passwd= ? ");
		 
		try {
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		 
		rs = pstmt.executeQuery();
		if(rs.next()){
		int cnt = rs.getInt(1);
		if(cnt>0) flag =true;
		}
		 
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} finally{
		DBClose.close(rs, pstmt, con);
		}
		 
		return flag;
		}
	
	
	public boolean OldpassCheck(Map map) {
		boolean flag= false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs= null;
		
		
		
		
		sql.append("  select count(id)  ");
		sql.append("  from member  ");
		sql.append("  where id = ?  and passwd = ? ");
		
		String id= (String)map.get("id");
		String oldpass=(String)map.get("oldpasswd");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, oldpass);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt(1)>0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, con);
		}

		return flag;
	}
	
	public Map Oldpass(String id) {
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs= null;
		
		sql.append("  select passwd  ");
		sql.append("  from member  ");
		sql.append("  where id = ?  ");
		Map map= new HashMap();
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
			
				map.put("pass", rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, con);
		}

		return map;
		
	}
	
	public boolean updatePw(Map map) {
		boolean flag =false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		String passwd= (String)map.get("passwd");
		String id= (String)map.get("id");
		sql.append(" update member ");
		sql.append(" set passwd = ? ");
		sql.append(" where id = ? ");
		
		
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, passwd);
			pstmt.setString(2, id);
			int cnt = pstmt.executeUpdate();
			if(cnt>0)flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, con);
		}
		return flag ;
		
		
	}
	
	public boolean updateFile(Map map) {
		boolean flag= false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" update member ");
		sql.append(" set fname = ? ");
		sql.append(" where id = ? ");
		String id =(String)map.get("id");
		String fname =(String)map.get("fname");
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1,fname);
			pstmt.setString(2,id);
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;	
	}
	
	public boolean duplicateEmail(String email) {
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		StringBuffer sql = new StringBuffer();
		sql.append("  select count(email) ");
		sql.append("  from member ");
		sql.append("  where email = ? ");
		boolean flag=false;
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				if(cnt>0)flag = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(null, pstmt, con);
		}
		
		return flag;
		
	}
	
	
	public boolean duplicateId(String id) {
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		StringBuffer sql = new StringBuffer();
		sql.append("  select count(id) ");
		sql.append("  from member ");
		sql.append("  where id = ? ");
		boolean flag=false;
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				if(cnt>0)flag = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(null, pstmt, con);
		}
		
		return flag;
		
	}
	
	public int total(Map map) {
		int total=0;
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs= null;
		String col = (String)map.get("col");
		String word = (String)map.get("word");
		sql.append(" select count (*) from member ");
		if(word.trim().length()>0) {
			sql.append(" where "+ col + " like '%'||?||'%'");
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
	
	
	public List<MemberDTO> list(Map map){
		List<MemberDTO> list= new ArrayList<MemberDTO>();
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs= null;
		String col = (String)map.get("col");
		String word =(String)map.get("word"); 
		int sno= (Integer)map.get("sno");
		int eno= (Integer)map.get("eno");
		sql.append(" SELECT id, mname, tel, email, zipcode, address1, address2, ");
		sql.append(" fname,  r ");
		sql.append(" from( ");
		sql.append(" SELECT id, mname, tel, email, zipcode, address1, address2, ");
		sql.append(" fname, rownum r ");
		sql.append(" from( ");
		sql.append(" SELECT id, mname, tel, email, zipcode, address1,  ");
		sql.append(" address2, fname ");
		sql.append(" FROM member ");
		if(word.trim().length()>0)
		sql.append(" where "+col+" like '%'||?||'%' ");
		sql.append(" ORDER BY mdate DESC  ");
		sql.append(" ) ");
		sql.append(" ) " );
		sql.append(" where r >= ? and r <= ?  ");
		int r=0;
		try {
			pstmt= con.prepareStatement(sql.toString());
			if(word.trim().length()>0) {
				
				pstmt.setString(++r, word);
				
			}
			pstmt.setInt(++r, sno);
			pstmt.setInt(++r, eno);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto= new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setMname(rs.getString("mname"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setFname(rs.getString("fname"));
			
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, con);
		}
		return list;
		
	}
	
	
	public MemberDTO read(String id) {
		MemberDTO dto = null;
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs= null;
		sql.append(" select * from member where id= ? ");
		
		try {
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setMname(rs.getString("mname"));
			dto.setTel(rs.getString("tel"));
			dto.setEmail(rs.getString("email"));
			dto.setZipcode(rs.getString("zipcode"));
			dto.setAddress1(rs.getString("address1"));
			dto.setAddress2(rs.getString("address2"));
			dto.setJob(rs.getString("job"));
			dto.setMdate(rs.getString("mdate"));
			dto.setFname(rs.getString("fname"));
			dto.setGrade(rs.getString("grade"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, con);
		}
		return dto;
	}
	
	public boolean create(MemberDTO dto) {
		
		boolean flag= false;
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into MEMBER(id, passwd, mname, tel, email, zipcode, address1, address2,  ");
		sql.append("					job, mdate, fname, grade) ");
		sql.append("			values (?,?,?,?,?,?,?,?,?,sysdate,?,'H')");
		try {
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getMname());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getZipcode());
			pstmt.setString(7, dto.getAddress1());
			pstmt.setString(8, dto.getAddress2());
			pstmt.setString(9, dto.getJob());
			pstmt.setString(10, dto.getFname());
			int cnt= pstmt.executeUpdate();
			if(cnt>0)flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	public boolean update(MemberDTO dto) {
		boolean flag= false;
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		StringBuffer sql = new StringBuffer();
		sql.append("  update member set ");
		sql.append("  tel=?, ");
		sql.append("  email=?, ");
		sql.append("  zipcode=?, ");
		sql.append("  	address1=?, ");
		sql.append("  	address2=?, ");
		sql.append("  	job=? ");
		sql.append("  where id =?");
		try {
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setString(1,dto.getTel());
			pstmt.setString(2,dto.getEmail());
			pstmt.setString(3,dto.getZipcode());
			pstmt.setString(4,dto.getAddress1());
			pstmt.setString(5,dto.getAddress2());
			pstmt.setString(6,dto.getJob());
			pstmt.setString(7,dto.getId());
		
			
			int cnt= pstmt.executeUpdate();
			if(cnt>0)flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	public boolean delete(String id) {
		boolean flag= false;
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from member where id = ?");
		
		try {
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			int cnt=pstmt.executeUpdate();
			if(cnt>0)flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, con);
		}
		
		return flag;
	}
	
	public String getFineId(Map mapid) {
		String id=null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		
		String mname = (String)mapid.get("mname");
		String email = (String)mapid.get("email");
		
		sql.append(" select id from member ");
		sql.append(" where mname = ? " );
		sql.append(" and email = ? " );
		
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, mname);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id= rs.getString("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return id;
	}
	public String getFinePw(Map mappw) {
		String pw=null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		
		String mname = (String)mappw.get("mname");
		String id = (String)mappw.get("id");
		
		sql.append(" select passwd from member ");
		sql.append(" where mname = ? " );
		sql.append(" and id = ? " );
		
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, mname);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pw= rs.getString("passwd");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, con);
		}
		
		
		return pw;
	}


	public String getpwFind(Map map) {
		// TODO Auto-generated method stub
		return null;
	}


	public String getIdFind(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
