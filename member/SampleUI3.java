package member;

import java.sql.*;

import javax.swing.*;

import java.awt.*;
import java.util.*;

import javax.swing.table.*;

import java.awt.event.*;

public class SampleUI3 extends JFrame implements ActionListener {
	public static void main(String[] args) {
		SampleUI3 f = new SampleUI3();
		f.pack();
		f.setVisible(true);
	}
	JLabel label, id, name, phone, address, company;
	JTextField fid, fname, fphone, faddress, fcompany, search;
	JButton add, delete, update, clear, all, sbutton;
	String items[] = { "이름", "주소", "회사" };
	JComboBox combo;
	JPanel bottompanel, leftpanel, center, pid, pname, pphone, padd, pcom;

	JScrollPane sp;
	Vector outer, title, noresult, msg; // noresult :검색결과없을때 테이블때문에
	JTable table;
	DefaultTableModel model;

	String connect = new String("jdbc:oracle:thin:@localhost:1521:xe");
	String user = new String("scott");
	String passwd = new String("tiger");

	Connection conn;
	Statement stat;
	PreparedStatement prestat1, prestat2, prestat3;
	ResultSet rs;

	public SampleUI3(){
		makeGui(); //화면구성
		prepareDB(); //db 준비작업
		select(null); //첫화면에서 테이블의 모든 내용보여주기 위해 select하는 함수
		model.setDataVector(outer, title);
		
	}

	public void makeGui() {

		label = new JLabel("Address Book", JLabel.CENTER);
		id = new JLabel("   I D :  ", JLabel.CENTER);

		fid = new JTextField(15);
		pid = new JPanel();
		pid.add(id);
		pid.add(fid);

		name = new JLabel("이름 : ");
		fname = new JTextField(15);
		pname = new JPanel();
		pname.add(name);
		pname.add(fname);

		phone = new JLabel("전화 : ");
		fphone = new JTextField(15);
		pphone = new JPanel();
		pphone.add(phone);
		pphone.add(fphone);

		address = new JLabel("주소 : ");
		faddress = new JTextField(15);
		padd = new JPanel();
		padd.add(address);
		padd.add(faddress);

		company = new JLabel("회사 : ");
		fcompany = new JTextField(15);
		pcom = new JPanel();
		pcom.add(company);
		pcom.add(fcompany);

		leftpanel = new JPanel();
		leftpanel.setLayout(new GridLayout(5, 1));
		leftpanel.add(pid);
		leftpanel.add(pname);
		leftpanel.add(pphone);
		leftpanel.add(padd);
		leftpanel.add(pcom);

		add = new JButton("추가");
		add.addActionListener(this);
		delete = new JButton("삭제");
		delete.addActionListener(this);
		update = new JButton("수정");
		update.addActionListener(this);

		combo = new JComboBox(items);

		search = new JTextField(15);
		search.addActionListener(this);

		sbutton = new JButton("검색");
		sbutton.addActionListener(this);

		clear = new JButton("지우기");
		clear.addActionListener(this);
		all = new JButton("전체보기");
		all.addActionListener(this);

		bottompanel = new JPanel();
		bottompanel.add(add);
		bottompanel.add(delete);
		bottompanel.add(update);
		bottompanel.add(combo);
		bottompanel.add(search);
		bottompanel.add(sbutton);
		bottompanel.add(clear);
		bottompanel.add(all);

		title = new Vector();
		outer = new Vector();
		noresult = new Vector();
		msg = new Vector();

		title.add("ID");
		title.add("Name");
		title.add("Phone");
		title.add("Address");
		title.add("Company");

		noresult.add("실행 결과");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent w) {

				try {
					rs.close();
					stat.close();
					conn.close();

					setVisible(false);
					dispose();
					System.exit(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				super.windowClosing(w);
			}
		});

		Container c = getContentPane();

		// DefaultTableModel 생성
		model = new DefaultTableModel();

		// model 사용해서 JTable 생성
		table = new JTable(model);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				// 클릭한 행의 인덱스 알아내기-->JTable의 메소드
				int index = table.getSelectedRow();

				// 인덱스 이용해서 out안의 작은 벡터 in 꺼내기
				Vector msg = (Vector) outer.get(index);

				// in 안에 들어 있는 번호,이름,주소 알아내서 텍스트 필드에 넣어주기
				String id = (String) msg.get(0);
				String name = (String) msg.get(1);
				String phone = (String) msg.get(2);
				String address = (String) msg.get(2);
				String company = (String) msg.get(2);

				fid.setText(id);
				fname.setText(name);
				fphone.setText(phone);
				faddress.setText(address);
				fcompany.setText(company);

				// 번호가 들어가는 텍스트 필드는 편집 불가 상태로 변경
				fid.setEditable(false);
			}
		});

		sp = new JScrollPane(table);

		center = new JPanel();
		center.add(leftpanel);
		center.add(sp);

		c.add(label, "North");
		c.add(center, "Center");
		c.add(bottompanel, "South");
	}

	public void prepareDB() {

		// Statement
		// PreparedStatement

		try {
			// driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 객체 생성

			// connection 얻기
			conn = DriverManager.getConnection(connect, user, passwd);

			// statement 얻기
			stat = conn.createStatement();

			prestat1 = conn.prepareStatement("insert into addressBook values(?,?,?,?,?)");
			prestat2 = conn.prepareStatement("delete from addressBook where id = ?");
			prestat3 = conn
					.prepareStatement("update addressBook set name=?,phone=?,address=?,company=?" + " where id = ?");

			/*
			 * pin = conn.prepareStatement("insert into customer values(?,?,?)"
			 * ); pdel = conn.prepareStatement(
			 * "delete from customer where num= ?"); pup =
			 * conn.prepareStatement(
			 * "update customer set name=?,address=? where num=?");
			 */

		} catch (Exception e) {

		}

	}

	public void add() {
		String id = fid.getText();
		String name = fname.getText();
		String phone = fphone.getText();
		String address = faddress.getText();
		String company = fcompany.getText();

		try {
			prestat1.setString(1, id);
			prestat1.setString(2, name);
			prestat1.setString(3, phone);
			prestat1.setString(4, address);
			prestat1.setString(5, company);

			prestat1.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete() {
		String id = fid.getText();

		try {
			prestat2.setString(1, id);

			prestat2.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {

		String id = fid.getText();
		String name = fname.getText();
		String phone = fphone.getText();
		String address = faddress.getText();
		String company = fcompany.getText();

		try {
			prestat3.setString(1, name);
			prestat3.setString(2, phone);
			prestat3.setString(3, address);
			prestat3.setString(4, company);
			prestat3.setString(5, id);

			prestat3.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search() {

		String keyword = search.getText();

		int i = combo.getSelectedIndex();

		String index;
		if (i == 0)
			index = "name";
		else if (i == 1)
			index = "address";
		else
			index = "company";

		String q = "select * from addressBook where " + index + "='" + keyword + "' order by id";
		select(q);
		search.setText("");
	}

	public void clear() {
		fid.setText("");
		fname.setText("");
		fphone.setText("");
		faddress.setText("");
		fcompany.setText("");
		fid.setEditable(true);
	}

	public void select(String query) {
		try {
			if (query == null)
				query = "select * from addressBook order by id";

			rs = stat.executeQuery(query);
			// rs안에 데이터가 여부 체크 후 작업
			outer.clear();
			while (rs.next()) {
				msg = new Vector<String>();

				msg.add(rs.getString(1));
				msg.add(rs.getString(2));
				msg.add(rs.getString(3));
				msg.add(rs.getString(4));
				msg.add(rs.getString(5));
				outer.add(msg);
			}

		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent w) {
		Object o = w.getSource();

		try {
			if (o == add) { // 추가
				if (fid.getText().length() > 0)
					add();
				select(null);

			} else if (o == delete) { // 삭제
				if (fid.getText().length() > 0)
					delete();
				select(null);

			} else if (o == clear) { // 지우기
				clear();

			} else if (o == all) { // 전체보기
				select(null);

			} else if (o == update) { // 수정
				if (fid.getText().length() > 0)
					update();
				select(null);

			} else if (o == search || o == sbutton) { // 검색:버튼을 누르거나 엔터를 치거나
				search();

			}

			// model로 값 설정

			if (outer.isEmpty()) {
				outer.clear();

				msg.clear();

				msg.add("찾은 데이터가 없습니다...");
				outer.add(msg);
				model.setDataVector(outer, noresult);
			} else {
				model.setDataVector(outer, title);
			}

			// 초기화
			clear();

		} catch (Exception ew) {
			ew.printStackTrace();
		}
	}

	
}
