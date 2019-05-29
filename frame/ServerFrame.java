package frame;

import java.awt.TextArea;

import javax.swing.JFrame;

public class ServerFrame extends JFrame{

	TextArea ta;
	JFrame jf;
	
	public ServerFrame(){
		
		ta = new TextArea();
		jf = new JFrame("����");
		jf.add(ta);
		jf.setSize(250, 250);
		jf.setVisible(true);
	}
	
	public void taAppend(String str){
		
		ta.append(str);
	}
}
