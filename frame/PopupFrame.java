package frame;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopupFrame extends JFrame implements ActionListener{
   private JFrame f = new JFrame("�˾�â");
   private JLabel trim = new JLabel(" ");
   private JLabel msg = new JLabel("�α׾ƿ��Ͻðڽ��ϱ�? ", JLabel.CENTER);
   private JButton yes = new JButton("Yes");
   private JButton no = new JButton("No");
   
   public PopupFrame(){
      
      f.setLayout(new GridLayout(3,1));
      
      JPanel la = new JPanel();
      la.add(trim);
      la.add(msg);
      
      JPanel button = new JPanel();
      button.add(yes);
      button.add(no);
      
      f.add(trim);
      f.add(la);
      f.add(button);
      //f.add(no, BorderLayout.WEST);
      
      f.setSize(250,150);
      
      yes.addActionListener(this);
      no.addActionListener(this);
      
      f.setResizable(false);
      f.setVisible(true);
      
   }
   
    public void actionPerformed(ActionEvent e) {
      
      if(e.getSource() == yes){
         f.setVisible(false);
      }else if(e.getSource() == no){
         f.setVisible(true);
         
      }
   }
}