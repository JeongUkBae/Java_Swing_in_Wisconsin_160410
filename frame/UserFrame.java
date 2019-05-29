package frame;

import java.awt.List;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;


public class UserFrame extends JFrame implements MouseListener, ActionListener, Runnable{

	private PopupMenu pMenu = new PopupMenu("Edit");
	private MenuItem friendDelete = new MenuItem("ģ������");
	private MenuItem chatt = new MenuItem("ä���ϱ�");
	private MenuItem friendAdd = new MenuItem("ģ�����߰�");
	private List user = new List();
	private String id;
	private Socket sock;
	private InputStream is = null;
	private BufferedReader read = null;
	private PrintWriter pw = null;
	private int count = 0;
	private final int friend = 10000;//ģ���߰� �� �ο� ��
	
	public UserFrame(String id, Socket sock, InputStream is, BufferedReader read, PrintWriter pw){
	
		this.sock = sock;
		this.id=id;
		this.is = is;
		this.read = read;
		this.pw = pw;
		
		JFrame userf = new JFrame(this.id + "���� ģ�����");
		
		for(int i = 0; i < friend; i++){
			user.add("");
		}
		user.setLocation(100, 300);
		user.setSize(50,150);
	
		friendAdd.addActionListener(this);
		friendDelete.addActionListener(this);
		chatt.addActionListener(this);

		user.addMouseListener(this);
		userf.add(user);
		userf.add(pMenu);
		userf.setSize(200,300);
		userf.setVisible(true);
		userf.setDefaultCloseOperation(userf.EXIT_ON_CLOSE);
		addWindowListener( new WindowAdapter( ){
			public void windowClosing(WindowEvent e) {
				 new PopupFrame();
			}
		}) ; 
	}
	 
	public void mouseClicked(MouseEvent me) {
			
		if(me.getClickCount() == 1){
			pMenu.add(friendAdd);
			pMenu.add(chatt);
			pMenu.add(friendDelete);
			pMenu.show(user, me.getX(), me.getY());
		}
	}
	
	public void mousePressed(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {		}

	public void actionPerformed(ActionEvent e) {
		
		try{
			if(e.getSource() == friendAdd){
				RegisterFrame rf = new RegisterFrame(id, sock, is, read, pw);
			}else if(e.getSource() == friendDelete){
				pw.println("/d" + user.getSelectedItem());
				pw.flush();
				user.remove(user.getSelectedIndex());
			}else if(e.getSource() == chatt){
				pw.println("/c" + user.getSelectedItem());
				pw.flush();
				new ChattingFrame(id, sock, is, read, pw);
			}
		}catch(Exception ex){ }
	}
	
	public void run() {
		
		String userId = null;
		int size = 0;
		
		while(true){
			try {
				count = 0;
				while(true){
					userId = read.readLine();
					if(userId.equals("reset")){
						break;
					}
					user.replaceItem(userId, count);
					count++;
				}
				if(count < size){
					user.remove(size-1);
					count--;
				}
				size = Integer.parseInt(read.readLine());
				pw.println("connect");
				pw.flush();	
			} catch (Exception e) {
				System.out.println("error");
			}
		}
	}
}