package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import global.Constants;
import global.DAO;
import global.DatabaseFactory;
import global.Vendor;

public class MemberDAO extends DAO{
     
     private Connection con;
     private Statement stmt;
     private PreparedStatement pstmt;
     private ResultSet rs;
     private List<MemberVO> list = new ArrayList<MemberVO>();
     private MemberVO member = new MemberVO();
     
     private static MemberDAO dao = new MemberDAO();
     public static MemberDAO getInstance(){
    	 return dao;
     }
	public MemberDAO() {
		con = DatabaseFactory
				.getDatabase(Vendor.ORACLE,
						Constants.ORACLE_ID,
						Constants.ORACLE_PASS)
				.getConnection();
	}
    
     /**
      * executeUpdate
      */
     public int insert(MemberVO o ) {
    	 int result = 0;
    	 try {
    		result = con.createStatement().executeUpdate(o.insert()); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
           return result;
     }
     public int update(MemberVO o ) {
           return 0;
     }
     public int delete(MemberVO o ) {
           return 0;
     }
     /**
      *  executeQuery
      */
     @Override
     public List<MemberVO> selectAll() {
           try {
               rs = con.createStatement().executeQuery(member.selectAll());
               while (rs .next()) {
                   MemberVO member = new MemberVO(); // 이게 없으면 덮어씌움
                    member.setUserid( rs.getString( "USERID"));
                    member.setPassword( rs.getString( "PASSWORD"));
                    member.setUserid( rs.getString( "USERID"));
                    member.setBirth( rs.getString( "BIRTH"));
                    member.setBirth( rs.getString( "GENDER"));
                    member.setPhone( rs.getString( "PHONE"));
                    member.setEmail( rs.getString( "EMAIL"));
                    member.setEmail( rs.getString( "ADDR"));
                    member.setRegdate( rs.getString( "REGDATE"));
                    member.setProfile( rs.getString( "PROFILE"));
                    list.add( member);
              }
          } catch (SQLException e ) {
               e.printStackTrace();
          }
           return list ;
     }
     @Override
     public MemberVO selectOneBy(String s) {
           try {
               rs = con.createStatement().executeQuery(member .selectOneBy(s));
               while(rs .next()){
                    member.setUserid( rs.getString( "USERID"));
                    member.setPassword( rs.getString( "PASSWORD"));
                    member.setUserid( rs.getString( "USERID"));
                    member.setBirth( rs.getString( "BIRTH"));
                    member.setBirth( rs.getString( "GENDER"));
                    member.setPhone( rs.getString( "PHONE"));
                    member.setEmail( rs.getString( "EMAIL"));
                    member.setEmail( rs.getString( "ADDR"));
                    member.setRegdate( rs.getString( "REGDATE"));
                    member.setProfile( rs.getString( "PROFILE"));
              }
          } catch (SQLException e ) {
               e.printStackTrace();
          }
           return member ;
     }
    
     @Override
     public int count() {
           int result = 0;
           try {
               rs = con.createStatement().executeQuery(member.count());
               while (rs .next()) {
                    result = rs.getInt( "count");
              }
             
          } catch (SQLException e ) {
               e.printStackTrace();
          }
           return result ;
     }
     
     public List<MemberVO> selectSomeBy(String s1, String s2) {
    	 String sql = "select * from member where ? = ?";
           try {
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, s1);
               pstmt.setString(2, s2);
        	   rs = pstmt.executeQuery();
               while (rs .next()) {
                   MemberVO member = new MemberVO(); // 이게 없으면 덮어씌움
                    member.setUserid( rs.getString( "USERID"));
                    member.setPassword( rs.getString( "PASSWORD"));
                    member.setUserid( rs.getString( "USERID"));
                    member.setBirth( rs.getString( "BIRTH"));
                    member.setBirth( rs.getString( "GENDER"));
                    member.setPhone( rs.getString( "PHONE"));
                    member.setEmail( rs.getString( "EMAIL"));
                    member.setEmail( rs.getString( "ADDR"));
                    member.setRegdate( rs.getString( "REGDATE"));
                    member.setProfile( rs.getString( "PROFILE"));
                    list.add( member);
              }
          } catch (SQLException e ) {
               e.printStackTrace();
          }
           return list ;
     }
     /**
      *  추가된 메소드
      */
	public int countBy(String s1, String s2) {
		int count = 0;
		 String sql = "select count(*) as count from member where ? = ?";
		 try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, s1);
	        pstmt.setString(2, s2);
	  	    rs = pstmt.executeQuery();
	  	    count = rs.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public List<MemberVO> selectAllOrderBy(String s) {
		return list;
	}
}
