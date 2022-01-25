package logica;

public class Persona {

   protected String nombre;
   protected String id;
   protected boolean activo;

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public boolean isActivo() {
      return activo;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

}
