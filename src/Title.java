import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Title extends Container {
	   JLabel j = new JLabel(new ImageIcon("./�ѹ���.png"));
	  // JButton b1 = new JButton(new ImageIcon("./�¶���.png"));
	   JButton b2 = new JButton(new ImageIcon("./��������.png"));
	   JButton b3 = new JButton(new ImageIcon("./ũ����.png"));
	   JButton b4 = new JButton(new ImageIcon("./������.png"));


	   
	   Title(Main ani) {
	    /*  b1.setBorderPainted(false);
	      b1.setContentAreaFilled(false);
	      b1.setCursor(new Cursor(Cursor.HAND_CURSOR));*/
	      b2.setBorderPainted(false);
	      b2.setContentAreaFilled(false);
	      b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
	      b3.setBorderPainted(false);
	      b3.setContentAreaFilled(false);
	      b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
	      b4.setBorderPainted(false);
	      b4.setContentAreaFilled(false);
	      b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
	      this.add(j);
	     // this.add(b1);
	      this.add(b2);
	      this.add(b3);
	      this.add(b4);
	      this.setLayout(new FlowLayout());

	      b2.addActionListener(new ActionListener() {

	         public void actionPerformed(ActionEvent arg0) {
	            JOptionPane.showMessageDialog(null, "�ѹ��� ���������� �����մϴ�.", "����", JOptionPane.PLAIN_MESSAGE);
	            ani.c.removeAll();
	            ani.c.repaint();
	            ani.c.revalidate();
	            ani.c.setLayout(new BorderLayout());
	            ani.c.add(ani.Offl);
	            ani.setSize(ani.Offl.maxSize * 16 + 300, ani.Offl.maxSize * 16 + 48);

	         }

	      });

	      /*b1.addActionListener(new ActionListener() {

	         public void actionPerformed(ActionEvent arg0) {

	            JOptionPane.showMessageDialog(null, "�غ�����", "�غ���", JOptionPane.QUESTION_MESSAGE);
	         }

	      });*/

	      b3.addActionListener(new ActionListener() {

	         public void actionPerformed(ActionEvent arg0) {

	            JOptionPane.showMessageDialog(null, "GUI : �Ӻ���\n���� �� �˰��� : ���ѱ�",
	                  "�����ڵ�", JOptionPane.QUESTION_MESSAGE);
	         }

	      });
	      
	      b4.addActionListener(new ActionListener() {

		         public void actionPerformed(ActionEvent arg0) {

		            System.exit(0);
		         }

		      });

	   }
	}
