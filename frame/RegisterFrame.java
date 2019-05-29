package frame;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RegisterFrame extends JFrame implements ActionListener{

	private JFrame register = new JFrame("ģ�����");
	private Label lid = new Label("���̵� : ", Label.RIGHT);
	private TextField tfId = new TextField(10);
	private JButton btAdd = new JButton("���");
	private JPanel jp = new JPanel();
	private String id;
	private Socket sock;
	private InputStream is = null;
	private BufferedReader read = null;
	private PrintWriter pw = null;
	
	public RegisterFrame(String id, Socket sock, InputStream is, BufferedReader read, PrintWriter pw){
		
		this.sock = sock;
		this.id=id;
		this.is = is;
		this.read = read;
		this.pw = pw;
		
		jp.add(lid);
		jp.add(tfId);
		jp.add(btAdd);
	
		btAdd.addActionListener(this);
		register.add(jp);
		register.setSize(300, 100);
		register.setResizable(false);
		register.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btAdd){
			register.dispose();
			pw.println("/a" + tfId.getText());
			pw.flush();
		}
	}
}