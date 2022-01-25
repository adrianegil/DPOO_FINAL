package logica;

import java.util.Date;

public class Docente extends Persona {

   private Date fechainc;

   public Date getFechainc() {
      return fechainc;
   }

   public void setFechainc(Date fechainc) {
      this.fechainc = fechainc;
   }

   public Docente(String nombre, String id, boolean activo) {
      this.nombre = nombre;
      this.id = id;
      this.activo = activo;
   }

   public Docente(String nombre, String id, boolean activo, Date fecha) {
      this.nombre = nombre;
      this.id = id;
      this.activo = activo;
      this.fechainc = fecha;
   }

}