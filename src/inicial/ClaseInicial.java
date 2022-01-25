package inicial;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.UIManager;

import auxiliar.GestorRecursos;
import interfaz.Autenticar;
import logica.Docente;
import logica.Estudiante;
import logica.Facultad;
import logica.Persona;
import logica.Planificacion;
import logica.Turno;


public class ClaseInicial {

   public static void main(String[] args) {

      try {
         UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
      } catch (Throwable e) {
         e.printStackTrace();
      }

      llenarDatos();

      GregorianCalendar fecha = new GregorianCalendar();
      int posMes = fecha.get(Calendar.MONTH);

      if (posMes == 6 || posMes == 7)
         Facultad.getFacultad().planificarGuardiasVacaciones();
      else
         Facultad.getFacultad().planificarGuardiaEsteMes();

      Splash sp = new Splash();
      sp.setVisible(true);


      try {
         GestorRecursos.T3.play();
         Thread.sleep(1800);
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      sp.setVisible(false);

      Autenticar frame = new Autenticar();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      GestorRecursos.T2.play();
   }


   private static void llenarDatos() {

      //LISTADO DE ESTUDIANTES

      Persona juan = new Estudiante("Juan Carrasco Bermudez", "97111508122", true, 'M');
      Facultad.getFacultad().getPersonas().add(juan);

      Persona pedro = new Estudiante("Pedro Martinez", "98111518132", true, 'M');
      Facultad.getFacultad().getPersonas().add(pedro);

      Persona maria = new Estudiante("María Martinez", "88101518152", true, 'F');
      Facultad.getFacultad().getPersonas().add(maria);

      Persona alejandro = new Estudiante("Alejandro Chang", "96101518155", true, 'M');
      Facultad.getFacultad().getPersonas().add(alejandro);

      Persona amanda = new Estudiante("Amanda Guevara", "94103028155", true, 'F');
      Facultad.getFacultad().getPersonas().add(amanda);

      Persona camilo = new Estudiante("Camilo Hernandez", "92120528158", true, 'M');
      Facultad.getFacultad().getPersonas().add(camilo);
      Facultad.getFacultad().getPriorizados().add(camilo);

      Persona cesar = new Estudiante("Cesar Manuel", "91120818158", false, 'M');
      Facultad.getFacultad().getPersonas().add(cesar);

      Persona gabriela = new Estudiante("Gabriela Gonzalez", "99121818138", true, 'F');
      Facultad.getFacultad().getPersonas().add(gabriela);

      Persona inti = new Estudiante("Inti Martinez", "93101828138", true, 'M');
      Facultad.getFacultad().getPersonas().add(inti);

      Persona alex = new Estudiante("Alex Carrasco Bermudez", "96021828138", true, 'M');
      Facultad.getFacultad().getPersonas().add(alex);
      Facultad.getFacultad().getPriorizados().add(alex);

      Persona pedry = new Estudiante("Pedry Gonzalez", "95022828138", true, 'M');
      Facultad.getFacultad().getPersonas().add(pedry);

      Persona marian = new Estudiante("Marian Martinez", "95122628138", true, 'F');
      Facultad.getFacultad().getPersonas().add(marian);

      Persona alejandros = new Estudiante("Alejandro Rodriguez", "95102628144", true, 'M');
      Facultad.getFacultad().getPersonas().add(alejandros);

      Persona amandad = new Estudiante("Amanda Duran", "99102628132", true, 'F');
      Facultad.getFacultad().getPersonas().add(amandad);

      Persona camiloc = new Estudiante("Camilo Cienfuegos", "97102128132", true, 'M');
      Facultad.getFacultad().getPersonas().add(camiloc);

      Persona jcesar = new Estudiante("Julio Cesar Gil", "97112128232", false, 'M');
      Facultad.getFacultad().getPersonas().add(jcesar);

      Persona agabriela = new Estudiante(" Ana Gabriela Gonzalez", "94102128231", true, 'F');
      Facultad.getFacultad().getPersonas().add(agabriela);

      Persona yuni = new Estudiante("Yuni Martinez", "88072128231", true, 'M');
      Facultad.getFacultad().getPersonas().add(yuni);

      Persona juanito = new Estudiante("Juanito  Bermudez", "88092228131", true, 'M');
      Facultad.getFacultad().getPersonas().add(juanito);

      Persona pedrito = new Estudiante("Pedrito Perez", "98042228131", true, 'M');
      Facultad.getFacultad().getPersonas().add(pedrito);

      Persona mariaju = new Estudiante("Maria Julia Chu", "98041228121", true, 'F');
      Facultad.getFacultad().getPersonas().add(mariaju);

      Persona alexander = new Estudiante("Alexander Gren", "98052228121", true, 'M');
      Facultad.getFacultad().getPersonas().add(alexander);

      Persona migdalia = new Estudiante("Migdalia Mejias", "96052228121", true, 'F');
      Facultad.getFacultad().getPersonas().add(migdalia);

      Persona ernesto = new Estudiante("Ernesto Hernandez", "96021228121", true, 'M');
      Facultad.getFacultad().getPersonas().add(ernesto);

      Persona fidel = new Estudiante("Fidel Ruz", "96051228122", false, 'M');
      Facultad.getFacultad().getPersonas().add(fidel);

      Persona celia = new Estudiante("Celia Sanchez", "95071228122", true, 'F');
      Facultad.getFacultad().getPersonas().add(celia);

      Persona raul = new Estudiante("Raul Martinez", "97071228132", true, 'M');
      Facultad.getFacultad().getPersonas().add(raul);

      Persona ruben = new Estudiante("Ruben Carrasco Nuñez", "97062228132", true, 'M');
      Facultad.getFacultad().getPersonas().add(ruben);

      Persona jorje = new Estudiante("Jorje Gonzalez", "99062228142", true, 'M');
      Facultad.getFacultad().getPersonas().add(jorje);

      Persona mayory = new Estudiante("Mayory Gil", "96062228143", true, 'F');
      Facultad.getFacultad().getPersonas().add(mayory);

      Persona gender = new Estudiante("Gender Duran", "96012228154", true, 'M');
      Facultad.getFacultad().getPersonas().add(gender);

      Persona maylen = new Estudiante("Maylen Jardinez", "96011228124", true, 'F');
      Facultad.getFacultad().getPersonas().add(maylen);

      Persona pelayo = new Estudiante("Pelayo Gomez", "92111227124", true, 'M');
      Facultad.getFacultad().getPersonas().add(pelayo);
      Facultad.getFacultad().getPriorizados().add(pelayo);

      Persona lazaro = new Estudiante("Lazaro Rey Gil", "92011228124", false, 'M');
      Facultad.getFacultad().getPersonas().add(lazaro);

      Persona marbelis = new Estudiante(" Marbelis Gabriela Gonzalez", "92081228124", true, 'F');
      Facultad.getFacultad().getPersonas().add(marbelis);

      Persona cristiano = new Estudiante("Cristiano Martinez", "95082228178", true, 'M');
      Facultad.getFacultad().getPersonas().add(cristiano);

      //LISTADO DE DOCENTES

      Persona sonia = new Docente("Sonia Hernandez", "96081228177", true);
      Facultad.getFacultad().getPersonas().add(sonia);

      Persona ana = new Docente("Maykel Blanco Salsa", "91082228177", true);
      Facultad.getFacultad().getPersonas().add(ana);

      Persona juank = new Docente("Juan Carlos Bermudez", "81082228177", true);
      Facultad.getFacultad().getPersonas().add(juank);

      Persona pedron = new Docente("Pedron Romay", "81057228177", true);
      Facultad.getFacultad().getPersonas().add(pedron);

      Persona mariana = new Docente("Mariana Grajales", "81052228177", true);
      Facultad.getFacultad().getPersonas().add(mariana);

      Persona alejandromag = new Docente("Alejandro Magno Cheng", "81022228177", true);
      Facultad.getFacultad().getPersonas().add(alejandromag);

      Persona amar = new Docente("Amarily Guevara", "83021228177", true);
      Facultad.getFacultad().getPersonas().add(amar);

      Persona yomil = new Docente("Yomil Hernandez", "83051228177", true);
      Facultad.getFacultad().getPersonas().add(yomil);
      Facultad.getFacultad().getPriorizados().add(yomil);

      Persona cesarmu = new Docente("Cesar Miu", "87051228177", true);
      Facultad.getFacultad().getPersonas().add(cesarmu);

      Persona gaby = new Docente("Gaby Reyes", "87051228197", true);
      Facultad.getFacultad().getPersonas().add(gaby);

      Persona yisel = new Docente("Yisel Perez", "88051228197", true);
      Facultad.getFacultad().getPersonas().add(yisel);

      Persona merman = new Docente("Merman Carrasco Bermudez", "88051228165", true);
      Facultad.getFacultad().getPersonas().add(merman);

      Persona pier = new Docente("Pier Emery", "88051228161", true);
      Facultad.getFacultad().getPersonas().add(pier);

      Persona mariano = new Docente("Mariano Rodriguez", "84051228161", true);
      Facultad.getFacultad().getPersonas().add(mariano);

      Persona yansel = new Docente("Yansel Urrutia", "86051228161", true);
      Facultad.getFacultad().getPersonas().add(yansel);

      Persona juniel = new Docente("Juniel Gil", "86051228122", true);
      Facultad.getFacultad().getPersonas().add(juniel);

      Persona yamil = new Docente("Yamil Gonzalez", "86051228123", true);
      Facultad.getFacultad().getPersonas().add(yamil);

      Persona leonis = new Docente("Leoni Cesar Torrez", "77051228123", true);
      Facultad.getFacultad().getPersonas().add(leonis);

      Persona beyonce = new Docente(" Beyonce Rey Gonzalez", "77051228122", true);
      Facultad.getFacultad().getPersonas().add(beyonce);

      Persona yunior = new Docente("Yunior Paumier", "79051228122", true);
      Facultad.getFacultad().getPersonas().add(yunior);

      Persona sony = new Docente("Sony Diaz", "76051228122", true);
      Facultad.getFacultad().getPersonas().add(sony);

      Persona phil = new Docente("Phil Perz", "78051728122", true);
      Facultad.getFacultad().getPersonas().add(phil);

      Persona yeniel = new Docente(" Yeniel Bermudez", "78052728122", true);
      Facultad.getFacultad().getPersonas().add(yeniel);

      Persona samuel = new Docente("Samuel Lombard", "78052728123", true);
      Facultad.getFacultad().getPersonas().add(samuel);

      Persona gretel = new Docente("Gretel Grajal", "78012728123", true);
      Facultad.getFacultad().getPersonas().add(gretel);

      Persona hitler = new Docente("Hitler Magno ", "78012728127", true);
      Facultad.getFacultad().getPersonas().add(hitler);

      Persona yenifer = new Docente("Yenifer Machado", "79012728127", true);
      Facultad.getFacultad().getPersonas().add(yenifer);

      Persona gerandy = new Docente("Gerandy Hernandez", "79092728127", true);
      Facultad.getFacultad().getPersonas().add(gerandy);
      Facultad.getFacultad().getPriorizados().add(gerandy);

      Persona messi = new Docente("Cesar Messi", "79092728432", true);
      Facultad.getFacultad().getPersonas().add(messi);

      Persona neymar = new Docente("Neymar Da Silva", "78092728432", true);
      Facultad.getFacultad().getPersonas().add(neymar);

      Persona bruno = new Docente("Bruno Rodriguez", "73092728432", true);
      Facultad.getFacultad().getPersonas().add(bruno);

      Persona jbaster = new Docente("Javier Baster","78092728452",false,new Date(2018 - 1900 ,8,20));
      Facultad.getFacultad().getPersonas().add(jbaster);
      Facultad.getFacultad().getPriorizados().add(jbaster);

      GregorianCalendar fecha = new GregorianCalendar();

      int posMes = 0;
      fecha.set(posMes, 1);

      ArrayList<Turno> turnos = new ArrayList<>();

      ArrayList<Planificacion> planificaciones;
      Planificacion plan;

      for (int i = 0; i < 12; i++) {
         planificaciones = new ArrayList<>();
         fecha.set(fecha.getTime().getYear() + 1900, i, 1);
         int cantDias = fecha.getActualMaximum(fecha.DAY_OF_MONTH);

         plan = new Planificacion(fecha, turnos);
         planificaciones.add(plan);
         Facultad.getFacultad().getPlanificaciones().add(planificaciones);
         ArrayList<ArrayList<Planificacion>> p = Facultad.getFacultad().getPlanificaciones();
					/*int cont = 1;
					for(int j = 0; j < cantDias; j++) {
						fecha.set(fecha.getTime().getYear()+1900,i, cont++);
						Planificacion plan1 = new Planificacion(fecha, turnos);
						Facultad.getFacultad().getPlanificaciones().get(i).add(plan1);
					}*/
      }
   }
}
	

