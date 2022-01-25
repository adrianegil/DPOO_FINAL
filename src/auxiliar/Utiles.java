package auxiliar;

import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utiles {

   public static void confirmarCerrar(JFrame frame) {
      int confirm = JOptionPane.showOptionDialog(frame,
              "¿Desea realmente cerrar esta aplicación?", "Confirmar cerrar",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null, new String[]{"Sí, cerrar", "No, cancelar"}, 1);
      if (confirm == 0) {
         frame.dispose();
         System.exit(0);
      }
   }

   public static void activarSimuladorPantallaCompleta(JFrame frame) {
      frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      frame.dispose();
      frame.setUndecorated(true);
      frame.setVisible(true);
   }

   public static void desactivarSimuladorPantallaCompleta(JFrame frame) {
      frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      frame.dispose();
      frame.setUndecorated(false);
      frame.setVisible(true);
   }

   public static ImageIcon imagenAjustadaDefin(int compoWidth, int compoHeight, String imgURL, int scaleImgAlgorit) {
      return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Utiles.class.getClass().getResource(imgURL)).getScaledInstance(compoWidth, compoHeight, scaleImgAlgorit));
   }
}
