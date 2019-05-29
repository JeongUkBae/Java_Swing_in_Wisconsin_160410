package member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import global.Constants;
import global.Database;
import global.DatabaseFactory;
import global.DatabaseFactory;
import global.Vendor;

public class MemberTest {
	public static void main(String[] args) {
		Connection con = DatabaseFactory
				.getDatabase(Vendor.ORACLE,
						Constants.ORACLE_ID,
						Constants.ORACLE_PASS)
				.getConnection();
		MemberVO member;
		List<MemberVO> list = new ArrayList<MemberVO>();
		Statement stmt = null;
	    ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from member");
			while (rs.next()) {
	               member = new MemberVO();
	               member.setUserid(rs.getString("userid"));
	               member.setPassword(rs.getString("password"));
	               list.add(member);
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
}
