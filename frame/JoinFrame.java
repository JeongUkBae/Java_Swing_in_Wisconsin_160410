package frame;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JoinFrame extends JFrame implements ActionListener {

	private JFrame joinf = new JFrame("ȸ����");
	private Label lid = new Label("���̵� : ", Label.RIGHT);
	private Label lpwd = new Label("��й�ȣ : ", Label.RIGHT);
	private Label lpwdCheck = new Label("��й�ȣȮ�� :", Label.RIGHT);
	private TextField tfId = new TextField(10);
	private TextField tfPwd = new TextField(10);
	private TextField tfPwdCheck = new TextField(10);
	private Button ok = new Button("ȸ����");
	private Button cancel = new Button("���");
	private Socket sock;
	private OutputStream os=null;
	private PrintWriter pw=null; 

	public JoinFrame(Socket sock){

		this.sock = sock;
		
		tfPwd.setEchoChar('*');//��й�ȣ�� *�� ǥ��
		tfPwdCheck.setEchoChar('*');//��й�ȣ Ȯ���� *�� ǥ��

		joinf.setLayout(new GridLayout(4,1));
		
		JPanel id = new JPanel();
		id.add(lid);
		id.add(tfId);
		JPanel pw = new JPanel();
		pw.add(lpwd);
		pw.add(tfPwd);
		JPanel pwc = new JPanel();
		pwc.add(lpwdCheck);
		pwc.add(tfPwdCheck);
		JPanel button = new JPanel();
		button.add(ok);
		button.add(cancel);
		
		joinf.add(id);
		joinf.add(pw);
		joinf.add(pwc);
		joinf.add(button);
		
		joinf.setSize(300, 200);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		
		joinf.setResizable(false);
		joinf.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == ok){
			try {
				if(tfPwd.getText().equals(tfPwdCheck.getText())){
					os = sock.getOutputStream();
			        pw = new PrintWriter(new OutputStreamWriter(os));
			        pw.println(tfId.getText() + "/" + tfPwd.getText());            
			        pw.flush();
				}
			} catch (IOException e1) {			}
			joinf.dispose();
			return ;
		}else if(e.getSource() == cancel){
			joinf.dispose();
			return ;	
		}
	}
}