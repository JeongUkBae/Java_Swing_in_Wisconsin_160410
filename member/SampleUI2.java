package member;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class SampleUI2 {

	public static void main(String[] args) {
		new SampleUI2();
	}

	JTextField name;
	JPasswordField tel;
	JTextField addr;

	public SampleUI2() {
		init();
	}
	public void init() {
		JFrame f = new JFrame();
		Container cp = f.getContentPane();
		cp.setLayout(new FlowLayout());
		name = new JTextField("", 10);
		name.setCaretColor(Color.blue);
		tel = new JPasswordField("", 10);

		tel.setEditable(false);
		addr = new JTextField("", 10);
		addr.setEditable(false);
		cp.add(new JLabel("성명 : "));
		cp.add(name);
		cp.add(new JLabel("전화번호 : "));
		cp.add(tel);
		cp.add(new JLabel("주소 : "));
		cp.add(addr);

		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dataGet();
			}
		});
		f.setSize(600, 100);
		f.setVisible(true);
	}
	public void dataGet() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@***.***.***.***:1521:WINK", "test", "test");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select tel, addr from emp where name = " + "'" + name.getText().trim() + "'");
			if (rs != null) {

				rs.next();
				tel.setText(rs.getString("tel"));
				addr.setText(rs.getString("addr"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
			}
		}
	}

}
