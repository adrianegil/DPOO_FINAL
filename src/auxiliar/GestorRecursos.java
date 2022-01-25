package auxiliar;

import java.applet.Applet;
import java.applet.AudioClip;

public class GestorRecursos {

   public static AudioClip T1 = Applet.newAudioClip(GestorRecursos.class.getResource("/recursos/T1.wav"));
   public static AudioClip T2 = Applet.newAudioClip(GestorRecursos.class.getResource("/recursos/Curve.wav"));
   public static AudioClip T3 = Applet.newAudioClip(GestorRecursos.class.getResource("/recursos/GilSoft.wav"));

   public static void reproducirMusicas() {

      Thread nt = new Thread(new Runnable() {
         public void run() {
            while (true) {
               T1.play();
               try {
                  Thread.sleep(24195);

               } catch (Exception e) {
               }
               T1.stop();
            }
         }
      });
      nt.start();
   }

   public static void detenerMusicas() {
      T1.stop();
   }
}
