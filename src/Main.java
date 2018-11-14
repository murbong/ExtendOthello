
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;




public class Main extends JFrame{

   public static final int WHITE = -1;
   public static final int BLACK = 1;
   public static final int MaxXY = 16;
   Offline Offl = new Offline(this);
   Title title = new Title(this);

   Container c = this.getContentPane();


   Main() {
      this.setTitle("Othello Alpha 4.0");
      this.setSize(650, 500);
      this.setResizable(false);
      this.setVisible(true);
      this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      c.setBackground(Color.WHITE);
      c.add(title);

   }

   public static void main(String[] args) {

      new Main();

   }

}
