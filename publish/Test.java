package publish;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Test {
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
		TempDAO dao = new TempDAO();
		dao.makeConnection();
		dao.selectAll();
		// addBook("C++ Programming", "Freelec", "2011", 50000);
		dao.selectAll();
	}

	
}
class TempDAO{
	static Connection con = null;
	static MyFrame frame;
	static JTable table;

	static void makeConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다");
		}
		String url = "jdbc:mysql://localhost/book_db";
		String user = "root";
		String password = "green";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void selectAll() {
		Statement stmt;
		String[] header = { "Book ID", "Book Name" };
		String[][] context = null;
		int i = 0;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books");
			rs.last();
			int nRecord = rs.getRow();
			context = new String[nRecord + 1][2];
			rs.beforeFirst();
			while (rs.next()) {
				int id = rs.getInt("book_id");
				String title = rs.getString("title");
				context[i][0] = id + "";
				context[i][1] = title;
				System.out.println(id + " " + title);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table = new JTable(context, header);
		JScrollPane scrollPane = new JScrollPane(table); // Make scrollpane with
		// Add scrollpane with table to the frame and show the frame to user
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}

	public static void addBook(String title, String publisher, String year, int price) {
		try {
			Statement stmt = con.createStatement();
			String s = "INSERT INTO books (title, publisher, year, price) VALUES ";
			s += "('" + title + "','" + publisher + "','" + year + "','" + price + "')";
			System.out.println(s);
			int i = stmt.executeUpdate(s);
			if (i == 1)
				System.out.println("레코드 추가 성공");
			else
				System.out.println("레코드 추가 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
class TempFrame extends JFrame implements ActionListener {
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JTextField textField1;
	JTextField textField2;
	JTextField textField3;
	JTextField textField4;
	JButton button;

	TempFrame() {
		setLayout(new GridLayout(3, 0));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(0, 4));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 4));
		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		textField4 = new JTextField();
		String[] header = { "Book Name", "Publisher", "Year", "Price" };
		label1 = new JLabel(header[0]);
		label2 = new JLabel(header[1]);
		label3 = new JLabel(header[2]);
		label4 = new JLabel(header[3]);
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel1.add(label4);
		panel2.add(textField1);
		panel2.add(textField2);
		panel2.add(textField3);
		panel2.add(textField4);

		button = new JButton("제출");
		button.addActionListener(this);
		add(panel1);
		add(panel2);
		add(button);
		// Add scrollpane with table to the frame and show the frame to user
		setSize(300, 300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String s1 = textField1.getText();
		String s2 = textField2.getText();
		String s3 = textField3.getText();
		int s4 = Integer.parseInt(textField4.getText());
	//	Test.addBook(s1, s2, s3, s4);
		setVisible(false);
	}
}

class MyFrame extends JFrame implements ActionListener {
	JButton addbutton;
	JButton searchbutton;

	MyFrame() {

		addbutton = new JButton("추가");
		searchbutton = new JButton("조회");
		JPanel panel = new JPanel();
		panel.add(addbutton);
		panel.add(searchbutton);
		addbutton.addActionListener(this);
		searchbutton.addActionListener(this);
		add(panel, BorderLayout.SOUTH);
		setSize(600, 400);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addbutton) {
			TempFrame tf = new TempFrame();
		} else if (e.getSource() == searchbutton) {
		//	Test.selectAll();
		}
	}
}
