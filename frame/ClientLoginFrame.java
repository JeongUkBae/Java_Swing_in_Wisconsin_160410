package frame;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientLoginFrame extends JFrame implements ActionListener{
	public static void main(String[] args){
		new ClientLoginFrame();
	}
	private JFrame f = new JFrame("����Talk");
	private Label lid = new Label("���̵� : ", Label.RIGHT);
	private Label lpwd = new Label("��й�ȣ : ", Label.RIGHT);
	private TextField tfId = new TextField(10);
	private TextField tfPwd =new TextField(10);
	private JButton ok = new JButton("�α���");
	private JButton join = new JButton("ȸ����");
	private ImageIcon image = new ImageIcon("talk.jpg");
	private JLabel label = new JLabel(image);
	private Socket sock;
	private OutputStream os = null;
	private PrintWriter pw = null; 
	private InputStream is = null;
	private BufferedReader read = null;
	final String serverIp = "203.236.209.127";
	
	public ClientLoginFrame(){
	
		tfPwd.setEchoChar('*');

		f.setLayout(new BorderLayout());
		
		JPanel id = new JPanel();
		id.add(lid);
		id.add(tfId);
		JPanel pw = new JPanel();
		pw.add(lpwd);
		pw.add(tfPwd);
		JPanel button = new JPanel();
		button.add(ok);
		button.add(join);
		JPanel south = new JPanel();
		south.add(id);
		south.add(pw);
		south.add(button);
		
		f.add("Center", label);
		f.add("South", south);
		
		
		f.setSize(600, 500);
		//f.setResizable(false);��
		f.setVisible(true);
		connect();
		ok.addActionListener(this);
		join.addActionListener(this);
		f.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	public void connect(){//User.txt�� Ȧ���� ���̵� ¦���� ������� ����
		
		try{
			sock = new Socket(serverIp, 7777);
		}catch(Exception e){}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ok){
			try {
				os = sock.getOutputStream();
				pw = new PrintWriter(new OutputStreamWriter(os));
		        pw.println("ok");            
		        pw.flush();
		        login();
		        is = sock.getInputStream();
				read = new BufferedReader(new InputStreamReader(is));
				String check = read.readLine();
				if(check.equals("true")){
					Runnable run = new UserFrame(tfId.getText(), sock, is, read, pw);
					Thread th = new Thread(run);
					th.start();
					f.dispose();
				}else if(check.equals("false")){
					System.out.println("false");
				}
			} catch (Exception e1) {			}		
		}else if(e.getSource() == join){
			try {
				os = sock.getOutputStream();
				pw = new PrintWriter(new OutputStreamWriter(os));
		        pw.println("join");            
		        pw.flush();
		        new JoinFrame(sock);
			} catch (Exception e1) {			}
		}
	}
	
	public void login(){
		
		try{
			os = sock.getOutputStream();
			pw = new PrintWriter(new OutputStreamWriter(os));
	        pw.println(tfId.getText() + "/" + tfPwd.getText());            
	        pw.flush();
		}catch(Exception e){	}
	}
	
	
}