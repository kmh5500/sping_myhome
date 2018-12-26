package spring.model.bbs;

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
public class BbsDAO {
	
	public boolean checkRefnum(int bbsno) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(refnum) ");
		sql.append(" from bbs ");
		sql.append(" where refnum = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			if (rs.getInt(1) > 0) {
				flag = true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}	
		
		return flag;
	}
	
	public boolean replyCreate(BbsDTO dto) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into bbs(bbsno, wname, title, content, ");
		sql.append(" passwd, wdate, grpno, indent, ansnum, refnum, filename, filesize) ");
		sql.append(" values( ");
		sql.append(" (select nvl(max(bbsno), 0) + 1 from bbs), ");
		sql.append(" ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ? ,? ) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setInt(5, dto.getGrpno());
				// 부모의 grpno 그대로
			pstmt.setInt(6, dto.getIndent()+1);
				// 부모의 indent + 1
			pstmt.setInt(7, dto.getAnsnum()+1);
				// 부모의 ansnum + 1
			pstmt.setInt(8, dto.getBbsno());
			pstmt.setString(9, dto.getFilename());
			pstmt.setInt(10, dto.getFilesize());
			
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
	
	public void upAnsnum(Map map) {
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		int grpno = (Integer) map.get("grpno");
		int ansnum = (Integer) map.get("ansnum");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update bbs ");
		sql.append(" set ansnum = ansnum + 1 ");
		sql.append(" where grpno = ? ");
		sql.append(" and ansnum > ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, grpno);
			pstmt.setInt(2, ansnum);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
	}
	
	public BbsDTO replyRead(int bbsno) {
		BbsDTO dto = null;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select bbsno, title, grpno, indent, ansnum ");
		sql.append(" from bbs ");
		sql.append(" where bbsno = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new BbsDTO();
				
				dto.setBbsno(rs.getInt("bbsno"));
				dto.setTitle(rs.getString("title"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return dto;
	}
	
	public boolean create(BbsDTO dto) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into bbs(bbsno, wname, title, content, ");
		sql.append(" 	 			 passwd, wdate, grpno, filename, filesize) ");
		sql.append(" values( ");
		sql.append(" (select nvl(max(bbsno), 0) + 1 from bbs), ");
		sql.append(" ?, ?, ?, ?, sysdate, ");
		sql.append(" (select nvl(max(grpno), 0) + 1 from bbs), ?, ?) ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setString(5, dto.getFilename());
			pstmt.setInt(6, dto.getFilesize());
			
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
	
	public BbsDTO read(int bbsno) {
		BbsDTO dto = null;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select bbsno, wname, title, content, viewcnt, ");
		sql.append(" 	 	to_char(wdate, 'yyyy-mm-dd') as wdate, filename ,filesize ");
		sql.append(" from bbs ");
		sql.append(" where bbsno = ? ");
				
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs = pstmt.executeQuery();
	
			if (rs.next()) {
				dto = new BbsDTO();
				
				dto.setBbsno(rs.getInt("bbsno"));
				dto.setWname(rs.getString("wname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));				
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				dto.setFilename(rs.getString("filename"));
				dto.setFilesize(rs.getInt("filesize"));
			
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return dto;
	}
	
	public boolean update(BbsDTO dto) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update bbs ");
		sql.append(" set wname = ?, ");
		sql.append(" 	 title = ?, ");
		sql.append(" 	 content = ? ");
		if(dto.getFilesize()>0) {
			sql.append(" ,filename= ?, ");
			sql.append(" filesize = ? ");
		}
		sql.append(" where bbsno = ? ");
		
		try {
			int i = 1;
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(i++, dto.getWname());
			pstmt.setString(i++, dto.getTitle());
			pstmt.setString(i++, dto.getContent());
			if(dto.getFilesize()>0) {
				pstmt.setString(i++, dto.getFilename());
				pstmt.setInt(i++, dto.getFilesize());
			}
			pstmt.setInt(i++, dto.getBbsno());
			
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
	
	public boolean delete(int bbsno) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from bbs ");
		sql.append(" where bbsno = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
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
	
	public int total(Map map) {
		int total = 0;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String col = (String) map.get("col");
		String word = (String) map.get("word");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) ");
		sql.append(" from bbs ");
		if (word.trim().length() > 0) {
			sql.append(" where " + col + " like '%'||?||'%' ");
		}
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			if (word.trim().length() > 0) {
				pstmt.setString(1, word);
			}
			
			rs = pstmt.executeQuery();
			rs.next();
			
			total = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return total;
	}
	
	public List<BbsDTO> list(Map map) {
		List<BbsDTO> list = new ArrayList<BbsDTO>();
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String col = (String) map.get("col");
		String word = (String) map.get("word");
		int sno = (Integer) map.get("sno");
		int eno = (Integer) map.get("eno");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select bbsno, wname, title, viewcnt, wdate,  ");
		sql.append("	    indent, filename, r ");
		sql.append(" from ( ");
		sql.append("	select bbsno, wname, title, viewcnt, wdate,  ");
		sql.append("	       indent, filename, rownum as r ");
		sql.append("	from ( ");		
		sql.append(" 		  select bbsno, wname, title, viewcnt, ");
		sql.append("				 to_char(wdate, 'yyyy-mm-dd') as wdate, ");
		sql.append(" 				 indent, filename ");
		sql.append("          from bbs ");
		if (word.trim().length() > 0) {
			sql.append(" where " + col + " like '%'||?||'%' ");
				// total은 list.jsp에서 빈 문자열로 만들어버렸기 때문에 여기에 쓰이지 못함
		}
		sql.append(" 		  order by grpno desc, ansnum ");
		sql.append(" 	) ");
		sql.append(" ) ");
		sql.append(" where r >= ? and r <= ? ");
		
		int i = 0;
				
		try {
			pstmt = con.prepareStatement(sql.toString());
			if (word.trim().length() > 0) {
				pstmt.setString(++i, word);
			}
			pstmt.setInt(++i, sno);
			pstmt.setInt(++i, eno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BbsDTO dto = new BbsDTO();
				
				dto.setBbsno(rs.getInt("bbsno"));
				dto.setWname(rs.getString("wname"));
				dto.setTitle(rs.getString("title"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				dto.setIndent(rs.getInt("indent"));
				dto.setFilename(rs.getString("filename"));
				
				list.add(dto);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return list;	
	}
	
	public void upviewcnt(int bbsno) {
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update bbs ");
		sql.append(" set viewcnt = viewcnt + 1 ");
		sql.append(" where bbsno = ? ");
				
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, con);
		}
	}
	
	public boolean passCheck(Map map) {
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int bbsno = (Integer) map.get("bbsno");
		String passwd = (String) map.get("passwd");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(bbsno) as cnt ");
		sql.append(" from bbs ");
		sql.append(" where bbsno = ? ");
		sql.append(" and passwd = ? ");
						
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			pstmt.setString(2, passwd);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			if (rs.getInt("cnt") > 0) {
				flag = true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, con);
		}
		
		return flag;
	}
	
}
