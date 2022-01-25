package logica;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Planificacion {

   private GregorianCalendar fecha = new GregorianCalendar();
   ArrayList<Turno> turnos = new ArrayList<>();

   public Planificacion(GregorianCalendar calendario, ArrayList<Turno> turnos) {
      super();
      this.fecha = calendario;
      this.turnos = turnos;
   }

   public GregorianCalendar getFecha() {
      return fecha;
   }

   public void setFecha(GregorianCalendar fecha) {
      this.fecha = fecha;
   }

   public ArrayList<Turno> getTurnos() {
      return turnos;
   }

   public void setTurnos(ArrayList<Turno> turnos) {
      this.turnos = turnos;
   }

}
