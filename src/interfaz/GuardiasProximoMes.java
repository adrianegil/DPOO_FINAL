package interfaz;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import logica.Estudiante;
import logica.Facultad;
import logica.Persona;
import logica.Planificacion;

import java.awt.Color;
import java.awt.Toolkit;

public class GuardiasProximoMes extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTable table;
   private DefaultTableModel defaultTableModel = new DefaultTableModel();

   public GuardiasProximoMes() {
      setIconImage(Toolkit.getDefaultToolkit().getImage(GuardiasProximoMes.class.getResource("/recursos/Informatica.PNG")));
      setResizable(false);
      setExtendedState(MAXIMIZED_BOTH);
      setLocationRelativeTo(null);
      setTitle("Guardias del Mes pr\u00F3ximo");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 743, 594);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 11, 1578, 783);
      contentPane.add(scrollPane);

      table = new JTable();
      table.setEnabled(false);
      scrollPane.setViewportView(table);

      JButton btnNewButton = new JButton("Cancelar");
      btnNewButton.setForeground(new Color(0, 0, 0));
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            GuardiasProximoMes.this.dispose();
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
         }
      });
      btnNewButton.setBounds(1453, 803, 125, 23);
      contentPane.add(btnNewButton);

      JButton btnNewButton_1 = new JButton("Cargar Tablas");
      btnNewButton_1.setForeground(new Color(0, 0, 0));
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            cargarTablaGuardiaMesSgte();
            @SuppressWarnings("unused")
            ArrayList<ArrayList<Planificacion>> plan = Facultad.getFacultad().getPlanificaciones();
         }
      });
      btnNewButton_1.setBounds(1318, 803, 125, 23);
      contentPane.add(btnNewButton_1);
   }

   public void cargarTablaGuardiaMesSgte() {

      ArrayList<Object> horario = new ArrayList<>();
      ArrayList<Object> dia = new ArrayList<>();
      ArrayList<Object> nombre = new ArrayList<>();
      ArrayList<Object> id = new ArrayList<>();
      ArrayList<Object> categoria = new ArrayList<>();

      Facultad fac = Facultad.getFacultad();
      GregorianCalendar fecha = new GregorianCalendar();

      int posMes = fecha.get(Calendar.MONTH);
      if (posMes == 11)
         posMes = 0;
      else
         posMes++;

      for (int i = 0; i < fac.getPlanificaciones().get(posMes).size(); i++) {

         Planificacion plan = fac.getPlanificaciones().get(posMes).get(i);

         for (int j = 0; j < plan.getTurnos().size(); j++) {

            for (int j2 = 0; j2 < plan.getTurnos().get(j).getPersonasTuno().size(); j2++) {

               Persona p = plan.getTurnos().get(j).getPersonasTuno().get(j2);
               //dia.add(i+1);
					/*
					int diames = plan.getFecha().get(Calendar.DAY_OF_MONTH);					
				    dia.add(diames);
				    */
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
      defaultTableModel.addColumn("Horario", horario.toArray());
      defaultTableModel.addColumn("Día", dia.toArray());
      defaultTableModel.addColumn("Nombre", nombre.toArray());
      defaultTableModel.addColumn("CI", id.toArray());
      defaultTableModel.addColumn("Categoría", categoria.toArray());
      table.setModel(defaultTableModel);
   }
}
