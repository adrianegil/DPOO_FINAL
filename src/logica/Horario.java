package logica;

public enum Horario {

   MADRUGADA("1:00am-7:00am"),
   MANNANA("7:00am-1:00pm"),
   TARDE("1:00pm-7:00pm"),
   NOCHE("7:00pm-1:00am");

   private final String tpoHorario;

   private Horario(String tpoHorario) {
      this.tpoHorario = tpoHorario;
   }

   public String getTpoHorario() {
      return tpoHorario;
   }
}
