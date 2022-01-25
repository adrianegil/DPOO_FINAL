package logica;

public class Estudiante extends Persona {

   private char sexo;

   public char getSexo() {
      return sexo;
   }

   public void setSexo(char sexo) {
      this.sexo = sexo;
   }

   public Estudiante(String nombre, String id, boolean activo, char sexo) {

      this.nombre = nombre;
      this.id = id;
      this.activo = activo;
      this.sexo = sexo;
   }
}
