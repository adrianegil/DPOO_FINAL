package interfaz;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import auxiliar.GestorRecursos;
import auxiliar.Utiles;
import logica.Estudiante;
import logica.Facultad;
import logica.Persona;
import logica.Planificacion;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;

public class MenuPrincipal extends JFrame {

   private boolean diaVis = true;
   private boolean idVis = true;
   private boolean horVis = true;
   private boolean catVis = true;
   private boolean nombVis = true;
   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JMenuItem mntmVersinDelSoftware;
   private JMenuItem mntmSalir;
   private JMenu mnNuevo;
   private JMenuItem mntmNewMenuItem;
   private JMenuItem mntmDocente;
   private JMenu mnAadir;
   private JMenuItem mntmPriorizados;
   private JMenuItem mntmAyuda;
   private JMenuItem mntmDesarrolladoresDelSoftware;
   private JMenu mnAcercaDe;
   private JMenuItem mntmPersonalNoActivo;
   private JMenuItem mntmPersonalActivo;
   private JMenuItem mntmPersonasPri;
   private JTable table;
   private DefaultTableModel defaultTableModel = new DefaultTableModel();
   private JMenuItem mntmPlanificarGuardiaDel;
   private JCheckBoxMenuItem chckbxmntmDetMus;
   private JMenu mnVista;
   private JCheckBoxMenuItem chckbxmntmDias;
   private JCheckBoxMenuItem chckbxmntmNombres;
   private JCheckBoxMenuItem chckbxmntmCarnet;
   private JCheckBoxMenuItem chckbxmntmHorario;
   private JCheckBoxMenuItem chckbxmntmCategoria;
   private JCheckBoxMenuItem chckbxmntmPantallaCompleta;

   public MenuPrincipal() {

      GestorRecursos.T1.loop();

      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            Utiles.confirmarCerrar(MenuPrincipal.this);
         }
      });

      setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/recursos/Informatica.PNG")));
      setResizable(true);
      setExtendedState(MAXIMIZED_BOTH);
      setLocationRelativeTo(null);
      setTitle("Men\u00FA Principal");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 732, 601);

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);

      JMenu mnArchivo = new JMenu("Archivo");
      mnArchivo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
      menuBar.add(mnArchivo);

      mnNuevo = new JMenu("Nuevo");
      mnArchivo.add(mnNuevo);

      mntmNewMenuItem = new JMenuItem("Estudiante");
      mntmNewMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            AgregarEstudiante dialog = new AgregarEstudiante();
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
      mnNuevo.add(mntmNewMenuItem);

      mntmDocente = new JMenuItem("Docente");
      mntmDocente.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            AgregarDocente dialog = new AgregarDocente();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      mntmDocente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
      mnNuevo.add(mntmDocente);

      mnAadir = new JMenu("A\u00F1adir");
      mnArchivo.add(mnAadir);

      mntmPriorizados = new JMenuItem("Priorizado");
      mntmPriorizados.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            AñadirPriorizado dialog = new AñadirPriorizado();
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      mntmPriorizados.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
      mnAadir.add(mntmPriorizados);

      mntmSalir = new JMenuItem("Salir");
      mntmSalir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Utiles.confirmarCerrar(MenuPrincipal.this);
         }
      });
      mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
      mnArchivo.add(mntmSalir);

      JMenu mnMostrar = new JMenu("Mostrar");
      mnMostrar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/UpFolder.gif")));
      menuBar.add(mnMostrar);

      JMenuItem mntmGuardiasDelProximo = new JMenuItem("Guardias del pr\u00F3ximo mes");
      mntmGuardiasDelProximo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            GuardiasProximoMes frame = new GuardiasProximoMes();
            MenuPrincipal.this.setVisible(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
         }
      });
      mnMostrar.add(mntmGuardiasDelProximo);

      JMenu mnListados = new JMenu("Listados");
      mnMostrar.add(mnListados);

      mntmPersonalNoActivo = new JMenuItem("Personal no Activo");
      mntmPersonalNoActivo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ListadoPersonalnoActivo dialog = new ListadoPersonalnoActivo();
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
         }
      });
      mnListados.add(mntmPersonalNoActivo);

      mntmPersonalActivo = new JMenuItem("Personal Activo");
      mntmPersonalActivo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ListadoPersonalActivo dialog = new ListadoPersonalActivo();
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
         }
      });
      mnListados.add(mntmPersonalActivo);

      mntmPersonasPri = new JMenuItem("Personas Priorizadas");
      mntmPersonasPri.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ListadoPersonasPriorizadas dialog = new ListadoPersonasPriorizadas();
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
         }
      });
      mnListados.add(mntmPersonasPri);

      JMenu mnFuncionalidades = new JMenu("Funcionalidades");
      mnFuncionalidades.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
      menuBar.add(mnFuncionalidades);

      JMenuItem mntmEliminarGuardiaDe = new JMenuItem("Eliminar Guardia ");
      mntmEliminarGuardiaDe.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            EliminarGuardia frame = new EliminarGuardia();
            MenuPrincipal.this.dispose();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
         }
      });

      mntmPlanificarGuardiaDel = new JMenuItem("Planificar Guardia(Pr\u00F3ximo Mes)");
      mntmPlanificarGuardiaDel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            GregorianCalendar fecha = new GregorianCalendar();
            int posMes = fecha.get(Calendar.MONTH);
            posMes++;
            if (posMes == 11) posMes = 0;

            if (posMes == 6 || posMes == 7)
               Facultad.getFacultad().planificarGuardiasVacaciones2();
            else
               Facultad.getFacultad().planificarGuardiaMesSgt();
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Se ha planificado correctamente la guardia del próximo mes", "EXITO", JOptionPane.PLAIN_MESSAGE);
         }
      });
      mnFuncionalidades.add(mntmPlanificarGuardiaDel);
      mnFuncionalidades.add(mntmEliminarGuardiaDe);

      JMenuItem mntmModificarGuardiaDe = new JMenuItem("Modificar Guardia ");
      mntmModificarGuardiaDe.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ModificarGuardia frame = new ModificarGuardia();
            MenuPrincipal.this.dispose();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
         }
      });
      mnFuncionalidades.add(mntmModificarGuardiaDe);

      JMenu mnOpciones = new JMenu("Opciones");
      mnOpciones.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
      menuBar.add(mnOpciones);

      chckbxmntmDetMus = new JCheckBoxMenuItem("Detener M\u00FAsicas");
      chckbxmntmDetMus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (chckbxmntmDetMus.isSelected()) {
               GestorRecursos.T1.stop();
            } else {
               GestorRecursos.T1.loop();
            }
         }
      });
      mnOpciones.add(chckbxmntmDetMus);

      chckbxmntmPantallaCompleta = new JCheckBoxMenuItem("Pantalla Completa");
      chckbxmntmPantallaCompleta.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (chckbxmntmPantallaCompleta.isSelected()) {
               Utiles.activarSimuladorPantallaCompleta(MenuPrincipal.this);
            } else {
               Utiles.desactivarSimuladorPantallaCompleta(MenuPrincipal.this);
            }
         }
      });
      mnOpciones.add(chckbxmntmPantallaCompleta);

      mnVista = new JMenu("Vista");
      mnOpciones.add(mnVista);

      chckbxmntmNombres = new JCheckBoxMenuItem("Nombre");
      chckbxmntmNombres.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (chckbxmntmNombres.isSelected()) {
               nombVis = true;
               cargarTablaGuardiaEsteMes();
            } else {
               nombVis = false;
               cargarTablaGuardiaEsteMes();
            }
         }
      });

      chckbxmntmHorario = new JCheckBoxMenuItem("Horario");
      chckbxmntmHorario.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (chckbxmntmHorario.isSelected()) {
               horVis = true;
               cargarTablaGuardiaEsteMes();
            } else {
               horVis = false;
               cargarTablaGuardiaEsteMes();
            }
         }
      });
      chckbxmntmHorario.setSelected(true);
      mnVista.add(chckbxmntmHorario);

      chckbxmntmDias = new JCheckBoxMenuItem("Dias");
      chckbxmntmDias.setSelected(true);
      chckbxmntmDias.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (chckbxmntmDias.isSelected()) {
               diaVis = true;
               cargarTablaGuardiaEsteMes();
            } else {
               diaVis = false;
               cargarTablaGuardiaEsteMes();
            }
         }
      });
      mnVista.add(chckbxmntmDias);
      chckbxmntmNombres.setSelected(true);
      mnVista.add(chckbxmntmNombres);

      chckbxmntmCarnet = new JCheckBoxMenuItem("Carnet");
      chckbxmntmCarnet.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (chckbxmntmCarnet.isSelected()) {
               idVis = true;
               cargarTablaGuardiaEsteMes();
            } else {
               idVis = false;
               cargarTablaGuardiaEsteMes();
            }
         }
      });
      chckbxmntmCarnet.setSelected(true);
      mnVista.add(chckbxmntmCarnet);

      chckbxmntmCategoria = new JCheckBoxMenuItem("Categoria");
      chckbxmntmCategoria.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            if (chckbxmntmCategoria.isSelected()) {
               catVis = true;
               cargarTablaGuardiaEsteMes();
            } else {
               catVis = false;
               cargarTablaGuardiaEsteMes();
            }
         }
      });
      chckbxmntmCategoria.setSelected(true);
      mnVista.add(chckbxmntmCategoria);

      mnAcercaDe = new JMenu("Acerca de ");
      mnAcercaDe.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
      menuBar.add(mnAcercaDe);

      mntmAyuda = new JMenuItem("Ayuda");
      mntmAyuda.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Esta aplicación se encarga de planificar de forma automatizada las \n"
                    + "guardias de los estudiantes y docentes de la Facultad de Informatica.\n"
                    + "Cualquier sugerencia o recomendaciones contactenos a : \n " +
                    "(53)58435620 o (53)54026466 'Gracias por utilizar el software'", "GilSoft", JOptionPane.INFORMATION_MESSAGE);
         }
      });
      mnAcercaDe.add(mntmAyuda);

      mntmDesarrolladoresDelSoftware = new JMenuItem("Desarrolladores del Software");
      mntmDesarrolladoresDelSoftware.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Est. Abelardo Chaviano Fajardo\n" +
                    "Est. Adrian E Gil", "GilSoft", JOptionPane.INFORMATION_MESSAGE);
         }
      });
      mnAcercaDe.add(mntmDesarrolladoresDelSoftware);

      mntmVersinDelSoftware = new JMenuItem("Versi\u00F3n del Software");
      mntmVersinDelSoftware.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MenuPrincipal.this, "Versión Final", "GilSoft", JOptionPane.INFORMATION_MESSAGE);
         }
      });
      mnAcercaDe.add(mntmVersinDelSoftware);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 35, 1342, 637);
      contentPane.add(scrollPane);

      table = new JTable();
      table.setEnabled(false);
      scrollPane.setViewportView(table);

      JLabel lblGuardiaDeEste = new JLabel("Guardia de este Mes");
      lblGuardiaDeEste.setForeground(Color.BLACK);
      lblGuardiaDeEste.setHorizontalAlignment(SwingConstants.CENTER);
      lblGuardiaDeEste.setFont(new Font("Arial", Font.BOLD, 18));
      lblGuardiaDeEste.setBounds(583, 0, 195, 32);
      contentPane.add(lblGuardiaDeEste);

      cargarTablaGuardiaEsteMes();
   }

   public void cargarTablaGuardiaEsteMes() {

      ArrayList<Object> horario = new ArrayList<>();
      ArrayList<Object> dia = new ArrayList<>();
      ArrayList<Object> nombre = new ArrayList<>();
      ArrayList<Object> id = new ArrayList<>();
      ArrayList<Object> categoria = new ArrayList<>();

      Facultad fac = Facultad.getFacultad();
      GregorianCalendar fecha = new GregorianCalendar();
      int posMes = fecha.get(Calendar.MONTH);

      for (int i = 0; i < fac.getPlanificaciones().get(posMes).size(); i++) {

         Planificacion plan = fac.getPlanificaciones().get(posMes).get(i);

         for (int j = 0; j < plan.getTurnos().size(); j++) {

            for (int j2 = 0; j2 < plan.getTurnos().get(j).getPersonasTuno().size(); j2++) {

               Persona p = plan.getTurnos().get(j).getPersonasTuno().get(j2);
               //dia.add(i+1);
               int año = plan.getFecha().get(Calendar.YEAR);
               int mes = plan.getFecha().get(Calendar.MONTH);
               int diam = plan.getFecha().get(Calendar.DAY_OF_MONTH);
               String fech = diam + "/" + (mes + 1) + "/" + año;
               dia.add(fech);
               horario.add(plan.getTurnos().get(j).getTpoHorario().getTpoHorario());
               nombre.add(p.getNombre());
               id.add(p.getId());
               String categ = "";

               if (p instanceof Estudiante) {
                  categ = "Estudiante";
                  categoria.add(categ);
               } else {
                  categ = "Docente";
                  categoria.add(categ);
               }
            }
         }

      }

      defaultTableModel = new DefaultTableModel();
      if (horVis)
         defaultTableModel.addColumn("Horario", horario.toArray());
      if (diaVis)
         defaultTableModel.addColumn("Día", dia.toArray());
      if (nombVis)
         defaultTableModel.addColumn("Nombre", nombre.toArray());
      if (idVis)
         defaultTableModel.addColumn("CI", id.toArray());
      if (catVis)
         defaultTableModel.addColumn("Categoría", categoria.toArray());

      table.setModel(defaultTableModel);

   }
}
