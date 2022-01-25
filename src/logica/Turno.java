package logica;

import java.util.ArrayList;

public class Turno {

   private ArrayList<Persona> personasTuno = new ArrayList<>();

   public ArrayList<Persona> getPersonasTuno() {
      return personasTuno;
   }

   public void setPersonasTuno(ArrayList<Persona> personasTuno) {
      this.personasTuno = personasTuno;
   }

   private Horario tpoHorario;
   private boolean asignado;

   public Turno(Horario tpoHorario, boolean asignado, ArrayList<Persona> personas) {

      this.tpoHorario = tpoHorario;
      this.asignado = asignado;
      this.personasTuno = personas;
   }

   public Horario getTpoHorario() {
      return tpoHorario;
   }

   public void setTpoHorario(Horario tpoHorario) {
      this.tpoHorario = tpoHorario;
   }

   public boolean isAsignado() {
      return asignado;
   }

   public void setAsignado(boolean asignado) {
      this.asignado = asignado;
   }

}
