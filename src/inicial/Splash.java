package inicial;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import java.awt.Color;

public class Splash extends JWindow {

   private static final long serialVersionUID = 1L;

   public Splash() {

      ImageIcon img = new ImageIcon(Splash.class.getResource("/recursos/infor.jpg"));

      JPanel panel = (JPanel) getContentPane();
      JLabel label = new JLabel(img);
      label.setBackground(Color.BLACK);

      panel.add(label);

      setSize(510, 341);
      setLocationRelativeTo(null);
   }
}
