package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import logica.Docente;
import logica.Estudiante;
import logica.Facultad;
import logica.Persona;

import java.awt.Toolkit;

public class ListadoPersonasPriorizadas extends JDialog {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTable table;
   private JTable table_1;
   private DefaultTableModel defaultTableModel = new DefaultTableModel();
   private DefaultTableModel defaultTableModel_1 = new DefaultTableModel();

   public ListadoPersonasPriorizadas() {
      setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoPersonasPriorizadas.class.getResource("/recursos/Informatica.PNG")));
      setResizable(false);
      setTitle("Listado de Personas Priorizadas");
      setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 731, 582);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 34, 332, 470);
      contentPane.add(scrollPane);

      table = new JTable();
      table.setEnabled(false);
      scrollPane.setViewportView(table);

      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(382, 34, 332, 470);
      contentPane.add(scrollPane_1);

      table_1 = new JTable();
      table_1.setEnabled(false);
      scrollPane_1.setViewportView(table_1);

      JLabel lblEstudiantes = new JLabel("Estudiantes");
      lblEstudiantes.setFont(new Font("Arial", Font.BOLD, 13));
      lblEstudiantes.setBounds(10, 11, 87, 22);
      contentPane.add(lblEstudiantes);

      JLabel lblNewLabel = new JLabel("Docentes");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
      lblNewLabel.setBounds(382, 11, 87, 22);
      contentPane.add(lblNewLabel);

      cargarTablaDocPrio();
      cargarTablaEstPrio();
   }

   public void cargarTablaEstPrio() {

      ArrayList<Object> nombreEst = new ArrayList<>();
      ArrayList<Object> CI = new ArrayList<>();
      ArrayList<Object> sexo = new ArrayList<>();
      ArrayList<Persona> per = Facultad.getFacultad().getPriorizados();

      for (int i = 0; i < per.size(); i++) {

         Persona pes = per.get(i);

         if (pes instanceof Estudiante) {
            nombreEst.add(pes.getNombre());
            CI.add(pes.getId());
            sexo.add(((Estudiante) pes).getSexo());
         }

         defaultTableModel = new DefaultTableModel();
         defaultTableModel.addColumn("Nombre", nombreEst.toArray());
         defaultTableModel.addColumn("CI", CI.toArray());
         defaultTableModel.addColumn("Sexo", sexo.toArray());

         table.setModel(defaultTableModel);
         contentPane.repaint();
      }
   }

   public void cargarTablaDocPrio() {

      ArrayList<Object> nombDoc = new ArrayList<>();
      ArrayList<Object> CI = new ArrayList<>();

      ArrayList<Persona> per = Facultad.getFacultad().getPriorizados();

      for (int i = 0; i < per.size(); i++) {
         Persona pers = per.get(i);

         if (pers instanceof Docente) {
            nombDoc.add(pers.getNombre());
            CI.add(pers.getId());
         }
      }
      defaultTableModel_1 = new DefaultTableModel();
      defaultTableModel_1.addColumn("Nombre", nombDoc.toArray());
      defaultTableModel_1.addColumn("CI", CI.toArray());

      table_1.setModel(defaultTableModel_1);
      contentPane.repaint();
   }
}
