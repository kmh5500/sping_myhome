package spring.model.guest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import spring.db.webtest.DBClose;
import spring.db.webtest.DBOpen;


@Repository
public class GuestDAO
{
    private Connection conn;
    
  
    
    //작성된 내용 DB에 저장하기
    public int insertData(GuestDTO dto)
    {
        int result = 0;
        Connection con = DBOpen.open();
        String sql;
        PreparedStatement pstmt = null;
        
        try {
            sql = "INSERT INTO guest(num, name, content, ipAddr, created) " +
                    "VALUES(?, ?, ?, ?, SYSDATE)";
            pstmt = con.prepareStatement(sql);
            
            pstmt.setInt(1, dto.getNum());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getContent());
            pstmt.setString(4, dto.getIpAddr());
            
            result = pstmt.executeUpdate();
            
          
        } catch(Exception e) {
            System.out.println(e.toString());
        }finally {
			DBClose.close(pstmt, con);
		}
        
        return result;
    }
    
    //게시물의 최대값 구하기
    public int getMaxNum()
    {
        int result = 0;
        Connection con = DBOpen.open();
        String sql;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            sql = "SELECT NVL(MAX(num), 0) FROM guest";    //게시물의 최대값 구하기
            pstmt = con.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            if(rs.next())
                result = rs.getInt(1);
            
            
        } catch(Exception e) {
            System.out.println(e.toString());
        }finally {
			DBClose.close(rs, pstmt, con);
		}
        
        return result;
    }
    
    //작성한 글 DB에서 읽어오기
    public List<GuestDTO> getListData()
    {
        List<GuestDTO> lists = new ArrayList<GuestDTO>();
        Connection con = DBOpen.open();
        String sql;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            sql = "SELECT num, name, content, TO_CHAR(created, 'YYYY-MM-DD') created, ipAddr " +
                    "From guest ORDER BY num DESC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                GuestDTO dto = new GuestDTO();
                
                dto.setNum(rs.getInt("num"));
                dto.setName(rs.getString("name"));
                dto.setContent(rs.getString("content"));
                dto.setCreated(rs.getString("created"));
                dto.setIpAddr(rs.getString("ipaddr"));
                
                lists.add(dto);
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }finally {
			DBClose.close(rs, pstmt, con);
		}
        
        return lists;
    }
    
    //삭제 기능 구현
    public int deleteData(int num)
    {
        int result = 0;
        Connection con = DBOpen.open();
        PreparedStatement pstmt = null;
        String sql;
        
        try {
            sql = "DELETE FROM guest WHERE num = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, num);
            result = pstmt.executeUpdate();
           
        } catch(Exception e) {
            System.out.println(e.toString());
        }finally {
			DBClose.close(pstmt, con);
		}
        
        
        return result;
    }
}
