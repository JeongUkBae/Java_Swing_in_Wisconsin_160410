package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChattingFrame extends  JFrame  implements ActionListener, Runnable  {       
   
   private JPanel cardPane, connectPane, chatPane;
   private JPanel  pn = new JPanel();
   private JLabel  msg;
   private JButton   btn_send, btn_exit;
   private JTextField  txt_input;
   private TextArea txt_list;
   private CardLayout card;
   private JComboBox emo;
   private  JComboBox cb ;
   private  String [] emoticon = {"_(�áԡ�)�Ϋߡ� ����~����~~","��(���䪴*)/ �ų���","(*�����)/ )) ��~~",
		   "s(���ԣ�)/`  ȦȦȦ~","(���ԣ�)v   ���̺���~ ", "(������)a  ������ ","( `�Ԣ�)��))) ��������",
		   "(�� �� �� ;;) �Ĵ��..", "��(�����;) �����̰�!","(~���⨬)~  ����","(�� ������) ����","(�� , .����) ����","(�騬 o ��)!��"};
   private  String [] id = {"��ü","ģ��"}; 

   //442page �޴��߰�
   private  MenuBar mb = new MenuBar( ); //442������ 7����
   private  Menu  mnufile = new Menu("����");
   private  Menu  mnufriend = new Menu("ģ��");
   private  Menu  mnubackcolor = new Menu("������");
   private  Menu  mnutextcolor = new Menu("���ڻ���");
   private  MenuItem  mnusave= new MenuItem("����"); 
   private  MenuItem  mnusend= new MenuItem("�������"); 
   private  MenuItem  mnuexit= new MenuItem("����"); 
   private  MenuItem  mnulist= new MenuItem("��ȭ��ģ�����");
   private  MenuItem  mnuinvite= new MenuItem("ģ���ʴ�");
   private  MenuItem  mnuye= new MenuItem("�����");
   private  MenuItem  mnupink= new MenuItem("��ȫ��");
   private  MenuItem  mnugreen= new MenuItem("�ʷϻ�");
   private  MenuItem  mnugray= new MenuItem("ȸ��");
   private  MenuItem  mnutor= new MenuItem("��Ȳ��");
   private  MenuItem  mnutpink= new MenuItem("��ȫ��");
   private  MenuItem  mnutgreen= new MenuItem("�ʷϻ�");
   private  MenuItem  mnutgray= new MenuItem("ȸ��");
   private String user;
  
   String ip_txt;                            //ip�� ������ ����
   Socket sock;
   final int PORT=7777;
   PrintWriter pw=null;                 //��۽�Ʈ�� 
   BufferedReader br=null;            //���Ž�Ʈ��   
   OutputStream os=null;
   
   public ChattingFrame(String user, Socket sock, InputStream is, BufferedReader read, PrintWriter pw)
   {
      super("������");
      
      ChatPane();
      this.sock = sock;
      this.user = user;
      //card-----------------------------
      cardPane = new JPanel();
      card = new CardLayout();
      cardPane.setLayout(card);
   
      cardPane.add(chatPane,"ä��â");
      card.show(cardPane, "����â");
      //----------------------------------
      add(cardPane);
      setSize(700,600);
      
      setVisible(true);
      
      super.setDefaultCloseOperation(EXIT_ON_CLOSE);
      //�̺�Ʈó��-----------------------
      
      btn_exit.addActionListener(this);
      btn_send.addActionListener(this);
      txt_input.addActionListener(this);
      mnusave.addActionListener(this) ; //����
      mnusend.addActionListener(this) ;
      mnuexit.addActionListener(this) ; //����
      mnulist.addActionListener(this) ; 
      mnuye.addActionListener(this) ;
      mnupink.addActionListener(this) ;
      mnugreen.addActionListener(this) ;
      mnugray.addActionListener(this) ;
      mnutor.addActionListener(this) ;
      mnutpink.addActionListener(this) ;
      mnutgreen.addActionListener(this) ;
      mnutgray.addActionListener(this) ;
      emo.addActionListener(this);
      //----------------------------------
   }//ChattingFrame ���� end
   
   public void ChatPane(){
	   
      chatPane = new JPanel();
      
      txt_list = new TextArea();
      txt_input = new JTextField("",20);
      btn_send = new JButton("������");
      btn_exit = new JButton("����");
      
      emo = new JComboBox(emoticon);
      cb = new JComboBox(id);
      cb.setBounds(100,200,80,20);
      chatPane.setBackground(Color.ORANGE);
      pn.setBackground( Color.ORANGE );
 
      mnufile.add(mnusave); 
      mnufile.addSeparator(); //����ִ� �и���
      mnufile.add(mnusend);
      mnufile.addSeparator(); //����ִ� �и���
      mnufile.add(mnuexit);
      
      mnufriend.add(mnulist);
      mnufriend.addSeparator();
      mnufriend.add(mnuinvite);
      
      mnubackcolor.add(mnuye);
      mnubackcolor.addSeparator();
      mnubackcolor.add(mnupink);
      mnubackcolor.addSeparator();
      mnubackcolor.add(mnugreen);
      mnubackcolor.addSeparator();
      mnubackcolor.add(mnugray);
      
      mnutextcolor.add(mnutor);
      mnutextcolor.addSeparator();
      mnutextcolor.add(mnutpink);
      mnutextcolor.addSeparator();
      mnutextcolor.add(mnutgreen);
      mnutextcolor.addSeparator();
      mnutextcolor.add(mnutgray);
      
      mb.add(mnufile);   mb.add(mnufriend);  mb.add(mnubackcolor); mb.add(mnutextcolor);
      this.setMenuBar(mb); //�̰� ���ϸ� �޴� �Ⱥ��� 443������ 39����
      
      pn.setBorder(BorderFactory.createTitledBorder("��ȭ�ϱ�"));
      chatPane.setBorder(BorderFactory.createTitledBorder("ä�ó���"));
      pn.add(emo); pn.add(cb); pn.add(txt_input);   pn.add(btn_send);  pn.add(btn_exit);
      
      chatPane.setLayout(new BorderLayout());
      chatPane.add(txt_list, "Center");
      chatPane.add(pn, "South");
      
      Thread th = new Thread(this);
      th.start();
   }//chatPane end

   public void actionPerformed(ActionEvent e) {
      Object ob = e.getSource();
      
      if(ob == emo){// ���� �޺��ڽ��� ������ 
          JComboBox cb=(JComboBox<?>)e.getSource();
          // cb.getSelectedIndex() �̰��� 0,1,2.. ���� �����մϴ�.
          String data=null;
          switch(cb.getSelectedIndex()){
           case 0:  data = "_(�áԡ�)�Ϋߡ� ����~����~~";  break;
           case 1:  data = "��(���䪴*)/ �ų���";    break;
           case 2:  data = "(*�����)/ )) ��~~";   break;
           case 3:  data = "s(���ԣ�)/`  ȦȦȦ~";  break;
           case 4:  data = "(���ԣ�)v   ���̺���~ "; break;
           case 5:  data = "(������)a  ������ ";     break;
           case 6:  data = "( `�Ԣ�)��))) ��������";  break;
           case 7:  data = "(�� �� �� ;;) �Ĵ��..";  break;
           case 8:  data = "��(�����;) �����̰�!";    break;
           case 9:  data = "(~���⨬)~  ����";      break;
           case 10: data = "(�� ������) ����";        break;
           case 11: data = "(�� , .����) ����";      break;
           case 12: data = "(�騬 o ��)!��";         break;
          }
          
          txt_input.setText(data);
          //pw.println(data);
          pw.flush();
      }
      //�׽�Ʈ4-------------------------------------
      if(ob == btn_send || ob == txt_input)
      {
         String text=txt_input.getText();
         if(text.trim().equals(""))   //�׳� ����ġ�ų� ����� �ְ� ���͸� ģ ���
         {
            txt_input.setText("");
            return;
         }
         pw.println(text);
         pw.flush();
         txt_input.setText("");
         txt_input.requestFocus();  //Ŀ���� txt_input���� �Űܳ���
      }
      //�׽�Ʈ4-------------------------------------
      if(ob ==btn_exit){
         try {
            
            this.sock.close();
         } catch (IOException e1) {
            
           // e1.printStackTrace();
         }
         System.exit(0);
      }
      if(ob==mnusave){ fileSave( ); }
      /*if(ob==mnusend){ try {
         new FileSendClient( );
      } catch (Exception e1) {
         e1.printStackTrace();
      } }*/
      
      if(ob==mnuexit){ System.exit(1); } // ����
      
      //������
      if(ob==mnuye){ chatPane.setBackground(Color.yellow); pn.setBackground(Color.yellow);}
      if(ob==mnupink){ chatPane.setBackground(Color.pink); pn.setBackground(Color.pink);}
      if(ob==mnugreen){ chatPane.setBackground(Color.green); pn.setBackground(Color.green);}
      if(ob==mnugray){ chatPane.setBackground(Color.gray); pn.setBackground(Color.gray);}
      //���ڻ���
      if(ob==mnutor){ txt_list.setForeground(Color.orange);}
      if(ob==mnutpink){ txt_list.setForeground(Color.pink); }
      if(ob==mnutgreen){ txt_list.setForeground(Color.green); }
      if(ob==mnutgray){ txt_list.setForeground(Color.gray); }
   } //actionPerformed end  
   
   public void fileSave( ){ //��������
        //FileDialog(Dialog parent, String title, int mode)
         FileDialog fd = new FileDialog(this, "��������", FileDialog.SAVE) ;
         fd.show(); //setVisible(true) ;
         try {
            File file = new File("/C:/Mtest/ok/" + fd.getFile());
            PrintWriter pw = new PrintWriter(file);
            pw.println(txt_list.getText());
            pw.close();
         } catch (Exception e) { }
      } //flieSave end
   
   public void run() {
      try{
         
         //�׽�Ʈ2(���)----------------------------------------
         String tf = txt_input.getText();
         os = sock.getOutputStream();
         pw = new PrintWriter(new OutputStreamWriter(os));
         pw.println(tf);            //����)pw.print(); ---X
         pw.flush();
         //�׽�Ʈ2(���)----------------------------------------
         //�׽�Ʈ3(����)----------------------------------------
         InputStream is=this.sock.getInputStream();
         br=new BufferedReader(new InputStreamReader(is));
         
         String str;
         while(true)
         {
            str=br.readLine();
            txt_list.append(str + "\n");
         }
         //�׽�Ʈ3(����)----------------------------------------
         
      }catch(IOException e){ }      
   } //run end
} //class END


