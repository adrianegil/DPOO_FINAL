package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Facultad {

   private String nombre;

   ArrayList<Persona> pendientes;
   ArrayList<Persona> priorizados;
   ArrayList<ArrayList<Planificacion>> planificaciones;
   ArrayList<Persona> personas;

   public ArrayList<Persona> getPendientes() {
      return pendientes;
   }

   public void setPendientes(ArrayList<Persona> pendientes) {
      this.pendientes = pendientes;
   }

   public ArrayList<Persona> getPriorizados() {
      return priorizados;
   }

   public void setPriorizados(ArrayList<Persona> priorizados) {
      this.priorizados = priorizados;
   }

   public ArrayList<ArrayList<Planificacion>> getPlanificaciones() {
      return planificaciones;
   }

   public void setPlanificaciones(ArrayList<ArrayList<Planificacion>> planificaciones) {
      this.planificaciones = planificaciones;
   }

   public ArrayList<Persona> getPersonas() {
      return personas;
   }

   public void setPersonas(ArrayList<Persona> personas) {
      this.personas = personas;
   }

   private static Facultad facultad;

   public static Facultad getFacultad() {
      if (facultad == null) {
         facultad = new Facultad();
      }
      return facultad;
   }

   private Facultad() {
      priorizados = new ArrayList<Persona>();
      planificaciones = new ArrayList<ArrayList<Planificacion>>();
      personas = new ArrayList<Persona>();
      pendientes = new ArrayList<>();
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   //FUNCIONALIDADES

   public ArrayList<Estudiante> estnoActivos() {

      ArrayList<Estudiante> result = new ArrayList<>();

      for (int i = 0; i < personas.size(); i++) {

         Persona per = personas.get(i);
         if (per instanceof Estudiante && !per.isActivo())
            result.add((Estudiante) per);
      }
      return result;
   }

   public ArrayList<Docente> doctnoActivos() {

      ArrayList<Docente> result = new ArrayList<>();

      for (int i = 0; i < personas.size(); i++) {
         Persona per = personas.get(i);
         if (per instanceof Docente && !per.isActivo())
            result.add((Docente) per);
      }
      return result;
   }

   public ArrayList<Estudiante> estudiantesActivos() {

      ArrayList<Estudiante> result = new ArrayList<>();

      for (int i = 0; i < personas.size(); i++) {
         if (personas.get(i) instanceof Estudiante && personas.get(i).isActivo())
            result.add((Estudiante) personas.get(i));
      }
      return result;
   }

   public ArrayList<Docente> docentesActivos() {

      ArrayList<Docente> result = new ArrayList<>();

      for (int i = 0; i < personas.size(); i++) {
         if (personas.get(i) instanceof Docente && personas.get(i).isActivo())
            result.add((Docente) personas.get(i));
      }
      return result;
   }

   public void llenarPendientes() {

      for (int i = 0; i < personas.size(); i++) {
         if (personas.get(i).isActivo())
            pendientes.add(personas.get(i));
      }
   }

   public void limpiarListaPlanific(int pos) {
      Facultad.getFacultad().getPlanificaciones().get(pos).clear();
   }

   public void planificarGuardiaMesSgt() {

      llenarPendientes();
      GregorianCalendar fecha = new GregorianCalendar();
      int posMes = fecha.get(Calendar.MONTH);
      posMes++;

      if (posMes == 12)
         posMes = 0;

      GregorianCalendar fechaGuardia = new GregorianCalendar();
      limpiarListaPlanific(posMes);
      int anno = fecha.get(Calendar.YEAR);

      if (posMes == 0)
         anno = anno + 1; //fechaGuardia.set(anno+1, posMes, 1);
      else
         fechaGuardia.set(anno, posMes, 1);

      int cantDias = fechaGuardia.getActualMaximum(fechaGuardia.DAY_OF_MONTH); // CANTIDAD DE DIAS DEL MES A PLANIFICAR
      limpiarListaPlanific(posMes);

      for (int i = 0; i < cantDias; i++) {      // COMIENZA LA PLANIFICACION DE LA GUARDIA

         Facultad fac = Facultad.getFacultad();
         fechaGuardia.set(anno, posMes, 1 + i); // EMPIEZO EL PRIMER DIA DEL MES
         Horario horario = null;
         ArrayList<Turno> turnosAnadir = new ArrayList<Turno>();

         int date_of_week = fechaGuardia.get(Calendar.DAY_OF_WEEK);

         if (date_of_week >= 2 && date_of_week <= 6) { // TURNOS DE ENTRE SEMANA (LUNES A VIERNES)
            horario = Horario.MADRUGADA;// 1ER TURNO
            ArrayList<Persona> personasAnadir = new ArrayList<Persona>();

            int count = 0;

            while (fac.priorizados.size() > 0 && count < 2)// HAY PERSONAS PRIORIZADAS
            {
               boolean finding = false;

               for (int pos = 0; pos < fac.priorizados.size(); pos++) {
                  Persona p = fac.priorizados.get(pos);
                  if (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'M') {
                     count++;
                     personasAnadir.add(p);
                     fac.priorizados.remove(pos);
                     // SE ELIMINA DE LA LISTA DE PENDIENTES
                     finding = true;
                     break;
                  }
               }
               if (!finding) break;
            }

            while (count < 2) // SE ACABARON LOS PRIORIZADOS VOY PARA LOS PENDIENTES
            {
               for (int pos = 0; pos < fac.pendientes.size(); pos++) {
                  Persona p = fac.pendientes.get(pos);
                  if (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'M') {
                     count++;
                     personasAnadir.add(p);
                     fac.pendientes.remove(pos);
                     fac.pendientes.add(p);  // SE AÑADEN AL FINAL DE LA LISTA
                     break;
                  }
               }
            }

            Turno turno = new Turno(horario, true, personasAnadir);
            turnosAnadir.add(turno); // SE AÑADE EL 1ER TURNO
            horario = Horario.NOCHE; // 2DO TURNO
            ArrayList<Persona> personasAnadir_noche = new ArrayList<Persona>();
            count = 0;

            while (fac.priorizados.size() > 0 && count < 2)  // HAY PERSONAS PRIORIZADAS
            {
               boolean finding = false;
               for (int pos = 0; pos < fac.priorizados.size(); pos++) {
                  Persona p = fac.priorizados.get(pos);
                  if (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'M') {
                     count++;
                     personasAnadir_noche.add(p);
                     fac.priorizados.remove(pos);
                     finding = true;
                     break;
                  }
               }
               if (!finding) break;
            }

            while (count < 2) // // SE ACABARON LOS PRIORIZADOS VOY PARA LOS PENDIENTES
            {

               for (int pos = 0; pos < fac.pendientes.size(); pos++) {
                  Persona p = fac.pendientes.get(pos);
                  if (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'M') {
                     count++;
                     personasAnadir_noche.add(p);
                     fac.pendientes.remove(pos);
                     fac.pendientes.add(p);   // SE AÑADEN AL FINAL DE LA LISTA
                     break;
                  }
               }
            }

            Turno turno_noche = new Turno(horario, true, personasAnadir_noche);
            turnosAnadir.add(turno_noche); // SE AÑADE EL 2ER TURNO

         } else // TURNOS DE FIN DE SEMANA (SÁBADO O DOMINGO)
         {

            horario = Horario.MANNANA;  // 1ER TURNO
            ArrayList<Persona> personasAnadir_mannana = new ArrayList<Persona>();
            int count = 0;

            while (fac.priorizados.size() > 0 && count < 2) // HAY PERSONAS PRIORIZADAS
            {
               boolean finding = false;
               for (int pos = 0; pos < fac.priorizados.size(); pos++) {
                  Persona p = fac.priorizados.get(pos);
                  if ((p instanceof Estudiante && ((Estudiante) p).getSexo() == 'F') || p instanceof Docente) {
                     count++;
                     personasAnadir_mannana.add(p);
                     fac.priorizados.remove(pos);
                     finding = true;
                     break;
                  }
               }
               if (!finding) break;
            }


            while (count < 2) // SE ACABARON LOS PRIORIZADOS VOY PARA LOS PENDIENTES
            {

               for (int pos = 0; pos < fac.pendientes.size(); pos++) {
                  Persona p = fac.pendientes.get(pos);
                  if (p instanceof Docente || (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'F')) {
                     count++;
                     personasAnadir_mannana.add(p);
                     fac.pendientes.remove(pos);
                     fac.pendientes.add(p);
                     break;
                  }
               }
            }

            Turno turno_mannana = new Turno(horario, true, personasAnadir_mannana);
            turnosAnadir.add(turno_mannana); // SE AÑADE EL 1ER TURNO

            horario = Horario.TARDE; // 2DO TURNO
            ArrayList<Persona> personasAnadir_tarde = new ArrayList<Persona>();
            count = 0;

            while (fac.priorizados.size() > 0 && count < 2) // HAY PERSONAS PRIORIZADAS
            {
               boolean finding = false;
               for (int pos = 0; pos < fac.priorizados.size(); pos++) {
                  Persona p = fac.priorizados.get(pos);
                  if (p instanceof Docente || (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'F')) {
                     count++;
                     personasAnadir_tarde.add(p);
                     fac.priorizados.remove(pos);
                     finding = true;
                     break;
                  }
               }
               if (!finding) break;
            }


            while (count < 2)   // SE ACABARON LOS PRIORIZADOS VOY PARA LOS PENDIENTES
            {
               for (int pos = 0; pos < fac.pendientes.size(); pos++) {
                  Persona p = fac.pendientes.get(pos);
                  if ((p instanceof Estudiante && ((Estudiante) p).getSexo() == 'F') || p instanceof Docente) {
                     count++;
                     personasAnadir_tarde.add(p);
                     fac.pendientes.remove(pos);
                     fac.pendientes.add(p);
                     break;
                  }
               }
            }
            Turno turno_tarde = new Turno(horario, true, personasAnadir_tarde);
            turnosAnadir.add(turno_tarde); // SE AÑADE EL 2DO TURNO
         }

         int dia = i + 1;
         fechaGuardia.set(anno, posMes, dia);
         Planificacion planAnadirDia = new Planificacion((GregorianCalendar) fechaGuardia.clone(), turnosAnadir); // SE CREA LA PLANIFICACION DEL DIA
         fac.getPlanificaciones().get(posMes).add(planAnadirDia); // SE AÑADA AL ARREGLO MULTIDIMENSIONAL LA PLANIFICACION DEL DIA EN LA POSICION DEL MES PLANIFICAR
      }
   }

   @SuppressWarnings("unused")
   public void planificarGuardiaEsteMes() {

      llenarPendientes();
      GregorianCalendar fecha = new GregorianCalendar();
      int posMes = fecha.get(Calendar.MONTH);
      GregorianCalendar fechaGuardia = new GregorianCalendar();
      limpiarListaPlanific(posMes);
      int anno = fecha.get(Calendar.YEAR);
      fechaGuardia.set(anno, posMes, 1);
      int cantDias = fechaGuardia.getActualMaximum(fechaGuardia.DAY_OF_MONTH); // CANTIDAD DE DIAS DEL MES A PLANIFICAR
      Facultad fac = Facultad.getFacultad();


      limpiarListaPlanific(posMes);

      for (int i = 0; i < cantDias; i++) {   // COMIENZA LA PLANIFICACION DE LA GUARDIA

         fechaGuardia.set(anno, posMes, 1 + i); // EMPIEZO EL PRIMER DIA DEL MES
         Horario horario = null;
         ArrayList<Turno> turnosAnadir = new ArrayList<Turno>();

         int date_of_week = fechaGuardia.get(Calendar.DAY_OF_WEEK);

         if (date_of_week >= 2 && date_of_week <= 6) { // TURNOS DE ENTRE SEMANA (LUNES A VIERNES)

            horario = Horario.MADRUGADA;// 1ER TURNO
            ArrayList<Persona> personasAnadir = new ArrayList<Persona>();
            int count = 0;


            while (count < 2) //  PARA LOS PENDIENTES
            {

               for (int pos = 0; pos < fac.pendientes.size(); pos++) {
                  Persona p = fac.pendientes.get(pos);
                  if (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'M') {
                     count++;
                     personasAnadir.add(p);
                     fac.pendientes.remove(pos);
                     fac.pendientes.add(p);  // SE AÑADEN AL FINAL DE LA LISTA
                     break;
                  }
               }
            }

            Turno turno = new Turno(horario, true, personasAnadir);
            turnosAnadir.add(turno); // SE AÑADE EL 1ER TURNO


            horario = Horario.NOCHE; // 2DO TURNO
            ArrayList<Persona> personasAnadir_noche = new ArrayList<Persona>();
            count = 0;

            while (count < 2) // //  PARA LOS PENDIENTES
            {
               for (int pos = 0; pos < fac.pendientes.size(); pos++) {
                  Persona p = fac.pendientes.get(pos);
                  if (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'M') {
                     count++;
                     personasAnadir_noche.add(p);
                     fac.pendientes.remove(pos);
                     fac.pendientes.add(p);   // SE AÑADEN AL FINAL DE LA LISTA
                     break;
                  }
               }
            }

            Turno turno_noche = new Turno(horario, true, personasAnadir_noche);
            turnosAnadir.add(turno_noche); // SE AÑADE EL 2ER TURNO

         } else // TURNOS DE FIN DE SEMANA (SÁBADO O DOMINGO)
         {
            horario = Horario.MANNANA;  // 1ER TURNO
            ArrayList<Persona> personasAnadir_mannana = new ArrayList<Persona>();
            int count = 0;

            while (count < 2) //  PARA LOS PENDIENTES
            {
               for (int pos = 0; pos < fac.pendientes.size(); pos++) {
                  Persona p = fac.pendientes.get(pos);
                  if (p instanceof Docente || (p instanceof Estudiante && ((Estudiante) p).getSexo() == 'F')) {
                     count++;
                     personasAnadir_mannana.add(p);
                     fac.pendientes.remove(pos);
                     fac.pendientes.add(p);
                     break;
                  }
               }
            }

            Turno turno_mannana = new Turno(horario, true, personasAnadir_mannana);
            turnosAnadir.add(turno_mannana); // SE AÑADE EL 1ER TURNO
            horario = Horario.TARDE; // 2DO TURNO
            ArrayList<Persona> personasAnadir_tarde = new ArrayList<Persona>();
            count = 0;


            while (count < 2)   //  PARA LOS PENDIENTES
            {
               for (int pos = 0; pos < fac.pendientes.size(); pos++) {
                  Persona p = fac.pendientes.get(pos);
                  if ((p instanceof Estudiante && ((Estudiante) p).getSexo() == 'F') || p instanceof Docente) {
                     count++;
                     personasAnadir_tarde.add(p);
                     fac.pendientes.remove(pos);
                     fac.pendientes.add(p);
                     break;
                  }
               }
            }

            Turno turno_tarde = new Turno(horario, true, personasAnadir_tarde);
            turnosAnadir.add(turno_tarde); // SE AÑADE EL 2DO TURNO
         }

         int dia = i + 1;
         fechaGuardia.set(anno, posMes, dia);
         Planificacion planAnadirDia = new Planificacion((GregorianCalendar) fechaGuardia.clone(), turnosAnadir); // SE CREA LA PLANIFICACION DEL DIA
         Facultad.getFacultad().getPlanificaciones().get(posMes).add(planAnadirDia); // SE AÑADA AL ARREGLO MULTIDIMENSIONAL LA PLANIFICACION DEL DIA EN LA POSICION DEL MES PLANIFICAR
         ArrayList<ArrayList<Planificacion>> plan = Facultad.getFacultad().getPlanificaciones();
         int e = 0;
      }
   }

   public void planificarGuardiasVacaciones() {

      llenarPendientes();

      GregorianCalendar fecha = new GregorianCalendar();
      int posMes = fecha.get(Calendar.MONTH);
      GregorianCalendar fechaGuardia = new GregorianCalendar();
      limpiarListaPlanific(posMes);
      int anno = fecha.get(Calendar.YEAR);
      fechaGuardia.set(anno, posMes, 1);
      int cantDias = fechaGuardia.getActualMaximum(fechaGuardia.DAY_OF_MONTH); // CANTIDAD DE DIAS DEL MES A PLANIFICAR
      Facultad fac = Facultad.getFacultad();

      limpiarListaPlanific(posMes);

      for (int i = 0; i < cantDias; i++) {   // COMIENZA LA PLANIFICACION DE LA GUARDIA

         fechaGuardia.set(anno, posMes, 1 + i); // EMPIEZO EL PRIMER DIA DEL MES
         Horario horario = null;
         ArrayList<Turno> turnosAnadir = new ArrayList<Turno>();
         horario = Horario.MANNANA;  // 1ER TURNO
         ArrayList<Persona> personasAnadir_mannana = new ArrayList<Persona>();
         int count = 0;

         while (count < 2) //  PARA LOS PENDIENTES
         {
            for (int pos = 0; pos < fac.pendientes.size(); pos++) {
               Persona p = fac.pendientes.get(pos);
               if (p instanceof Docente) {
                  count++;
                  personasAnadir_mannana.add(p);
                  fac.pendientes.remove(pos);
                  fac.pendientes.add(p);
                  break;
               }
            }
         }

         Turno turno_mannana = new Turno(horario, true, personasAnadir_mannana);
         turnosAnadir.add(turno_mannana); // SE AÑADE EL 1ER TURNO
         horario = Horario.TARDE; // 2DO TURNO
         ArrayList<Persona> personasAnadir_tarde = new ArrayList<Persona>();
         count = 0;

         while (count < 2)   //  PARA LOS PENDIENTES
         {
            for (int pos = 0; pos < fac.pendientes.size(); pos++) {
               Persona p = fac.pendientes.get(pos);
               if (p instanceof Docente) {
                  count++;
                  personasAnadir_tarde.add(p);
                  fac.pendientes.remove(pos);
                  fac.pendientes.add(p);
                  break;
               }
            }
         }

         Turno turno_tarde = new Turno(horario, true, personasAnadir_tarde);
         turnosAnadir.add(turno_tarde); // SE AÑADE EL 2DO TURNO
         int dia = i + 1;
         fechaGuardia.set(anno, posMes, dia);
         Planificacion planAnadirDia = new Planificacion((GregorianCalendar) fechaGuardia.clone(), turnosAnadir); // SE CREA LA PLANIFICACION DEL DIA
         fac.getPlanificaciones().get(posMes).add(planAnadirDia); // SE AÑADA AL ARREGLO MULTIDIMENSIONAL LA PLANIFICACION DEL DIA EN LA POSICION DEL MES PLANIFICAR
      }


   }

   public void planificarGuardiasVacaciones2() {

      llenarPendientes();
      GregorianCalendar fecha = new GregorianCalendar();
      int posMes = fecha.get(Calendar.MONTH);
      posMes++;
      if (posMes == 12) posMes = 0;
      GregorianCalendar fechaGuardia = new GregorianCalendar();
      limpiarListaPlanific(posMes);

      int anno = fecha.get(Calendar.YEAR);

      if (posMes == 0)
         fechaGuardia.set(anno + 1, posMes, 1);
      else
         fechaGuardia.set(anno, posMes, 1);

      int cantDias = fechaGuardia.getActualMaximum(fechaGuardia.DAY_OF_MONTH); // CANTIDAD DE DIAS DEL MES A PLANIFICAR

      Facultad fac = Facultad.getFacultad();
      limpiarListaPlanific(posMes);

      for (int i = 0; i < cantDias; i++) {   // COMIENZA LA PLANIFICACION DE LA GUARDIA

         fechaGuardia.set(anno, posMes, 1 + i); // EMPIEZO EL PRIMER DIA DEL MES
         Horario horario = null;
         ArrayList<Turno> turnosAnadir = new ArrayList<Turno>();
         horario = Horario.MANNANA;  // 1ER TURNO
         ArrayList<Persona> personasAnadir_mannana = new ArrayList<Persona>();
         int count = 0;

         while (count < 2) //  PARA LOS PENDIENTES
         {
            for (int pos = 0; pos < fac.pendientes.size(); pos++) {
               Persona p = fac.pendientes.get(pos);
               if (p instanceof Docente) {
                  count++;
                  personasAnadir_mannana.add(p);
                  fac.pendientes.remove(pos);
                  fac.pendientes.add(p);
                  break;
               }
            }
         }

         Turno turno_mannana = new Turno(horario, true, personasAnadir_mannana);
         turnosAnadir.add(turno_mannana); // SE AÑADE EL 1ER TURNO
         horario = Horario.TARDE; // 2DO TURNO
         ArrayList<Persona> personasAnadir_tarde = new ArrayList<Persona>();
         count = 0;

         while (count < 2)   //  PARA LOS PENDIENTES
         {
            for (int pos = 0; pos < fac.pendientes.size(); pos++) {
               Persona p = fac.pendientes.get(pos);
               if (p instanceof Docente) {
                  count++;
                  personasAnadir_tarde.add(p);
                  fac.pendientes.remove(pos);
                  fac.pendientes.add(p);
                  break;
               }
            }
         }

         Turno turno_tarde = new Turno(horario, true, personasAnadir_tarde);
         turnosAnadir.add(turno_tarde); // SE AÑADE EL 2DO TURNO
         int dia = i + 1;
         fechaGuardia.set(anno, posMes, dia);
         Planificacion planAnadirDia = new Planificacion((GregorianCalendar) fechaGuardia.clone(), turnosAnadir); // SE CREA LA PLANIFICACION DEL DIA
         fac.getPlanificaciones().get(posMes).add(planAnadirDia); // SE AÑADA AL ARREGLO MULTIDIMENSIONAL LA PLANIFICACION DEL DIA EN LA POSICION DEL MES PLANIFICAR
      }
   }

   public boolean eliminarGuardia(GregorianCalendar fechaEli, String id, String nombre) {


      int pos = fechaEli.get(Calendar.MONTH);
      int dia = fechaEli.get(Calendar.DATE);
      boolean eliminado = false;
      Planificacion plan = planificaciones.get(pos).get(dia - 1);

      for (int j = 0; j < plan.getTurnos().size(); j++) {
         for (int j2 = 0; j2 < plan.getTurnos().get(j).getPersonasTuno().size(); j2++) {
            Persona p = plan.getTurnos().get(j).getPersonasTuno().get(j2);
            if (p.nombre.equalsIgnoreCase(nombre) && p.id.equalsIgnoreCase(id)) {
               plan.getTurnos().get(j).getPersonasTuno().remove(p);
               eliminado = true;
            }
         }
      }
      return eliminado;
   }

   public Persona buscarPersona(String nombre1, String id1, GregorianCalendar fecha) {

      Persona p = null;
      boolean encontrado = false;
      int posMes = fecha.get(Calendar.MONTH);
      int dia = fecha.get(Calendar.DATE);
      Planificacion plan = planificaciones.get(posMes).get(dia - 1);

      for (int k = 0; k < plan.getTurnos().size(); k++) {
         for (int k2 = 0; k2 < plan.getTurnos().get(k).getPersonasTuno().size(); k2++) {
            Persona p2 = plan.getTurnos().get(k).getPersonasTuno().get(k2);
            if (p2.getNombre().equalsIgnoreCase(nombre1) && p2.getId().equalsIgnoreCase(id1)) {
               p = p2;
               encontrado = true;
            }
         }
      }
      if (encontrado)
         return p;
      else
         return null;
   }

   public boolean modificarGuardia(String nombre1, String id1, GregorianCalendar fechaEli1, String nombre2, String id2, GregorianCalendar fechaEli2) {

      boolean modificado = false;
      Persona p1 = buscarPersona(nombre1, id1, fechaEli1);
      Persona p2 = buscarPersona(nombre2, id2, fechaEli2);
      int pos1 = fechaEli1.get(Calendar.MONTH);
      int pos2 = fechaEli2.get(Calendar.MONTH);
      int dia1 = fechaEli1.get(Calendar.DATE);
      int dia2 = fechaEli2.get(Calendar.DATE);

      if (p1 != null && p2 != null) {

         Planificacion plan = planificaciones.get(pos1).get(dia1 - 1);

         for (int j = 0; j < plan.getTurnos().size(); j++) {

            for (int k = 0; k < plan.getTurnos().get(j).getPersonasTuno().size(); k++) {
               Persona p = plan.getTurnos().get(j).getPersonasTuno().get(k);
               if (p == p1) {
                  plan.getTurnos().get(j).getPersonasTuno().remove(p);
                  plan.getTurnos().get(j).getPersonasTuno().add(p2);
                  modificado = true;
               }
            }
         }
         if (modificado) {
            modificado = false;
            Planificacion plan1 = planificaciones.get(pos2).get(dia2 - 1);

            for (int j = 0; j < plan1.getTurnos().size(); j++) {

               for (int k = 0; k < plan1.getTurnos().get(j).getPersonasTuno().size(); k++) {
                  Persona p = plan1.getTurnos().get(j).getPersonasTuno().get(k);
                  if (p == p2) {
                     plan1.getTurnos().get(j).getPersonasTuno().remove(p);
                     plan1.getTurnos().get(j).getPersonasTuno().add(p1);
                     modificado = true;
                  }
               }
            }
         }
      }
      return modificado;
   }


   public static boolean validarCI(String id) {

      boolean result = true;
      for (int i = 0; i < id.length(); i++) {

         if (id.charAt(i) != '1' && id.charAt(i) != '2' && id.charAt(i) != '3' && id.charAt(i) != '4' && id.charAt(i) != '5' && id.charAt(i) != '6' && id.charAt(i) != '7' && id.charAt(i) != '8' && id.charAt(i) != '9' && id.charAt(i) != '0') {
            result = false;
         }
      }
      return result;
   }

   public static boolean validarNombre(String nombre) {

      boolean result = true;

      for (int i = 0; i < nombre.length(); i++) {
         if (nombre.charAt(i) == '1' || nombre.charAt(i) == '2' || nombre.charAt(i) == '3' || nombre.charAt(i) == '4' || nombre.charAt(i) == '5' || nombre.charAt(i) == '6' || nombre.charAt(i) == '7' || nombre.charAt(i) == '8' || nombre.charAt(i) == '9' || nombre.charAt(i) == '0') {
            result = false;
         }
      }
      return result;
   }
}

