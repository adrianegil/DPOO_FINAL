package interfaz;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import logica.Docente;
import logica.Estudiante;
import logica.Facultad;

import java.awt.Toolkit;

public class ListadoPersonalActivo extends JDialog {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTable table;
   private JTable table_1;
   private DefaultTableModel defaultTableModel = new DefaultTableModel();
   private DefaultTableModel defaultTableModel_1 = new DefaultTableModel();


   public ListadoPersonalActivo() {

      setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoPersonalActivo.class.getResource("/recursos/Informatica.PNG")));
      setResizable(false);
      setLocationRelativeTo(null);

      setTitle("Listado del Personal Activo");
      setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 789, 583);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 39, 378, 461);
      contentPane.add(scrollPane);

      table = new JTable();
      table.setEnabled(false);
      scrollPane.setViewportView(table);

      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(411, 39, 363, 461);
      contentPane.add(scrollPane_1);

      table_1 = new JTable();
      table_1.setEnabled(false);
      scrollPane_1.setViewportView(table_1);

      JLabel lblNewLabel = new JLabel("Estudiantes");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
      lblNewLabel.setBounds(10, 11, 91, 17);
      contentPane.add(lblNewLabel);

      JLabel lblDocente = new JLabel("Docentes");
      lblDocente.setFont(new Font("Arial", Font.BOLD, 13));
      lblDocente.setBounds(411, 12, 71, 15);
      contentPane.add(lblDocente);

      cargarTablaEstudiantesAct();
      cargarTablaDocentesAct();
   }


   public void cargarTablaDocentesAct() {

      ArrayList<Object> nombDoc = new ArrayList<>();
      ArrayList<Object> CI = new ArrayList<>();
      ArrayList<Docente> doc = Facultad.getFacultad().docentesActivos();

      for (int i = 0; i < doc.size(); i++) {
         Docente doce = doc.get(i);
         nombDoc.add(doce.getNombre());
         CI.add(doce.getId());
      }
      defaultTableModel_1 = new DefaultTableModel();
      defaultTableModel_1.addColumn("Nombre", nombDoc.toArray());
      defaultTableModel_1.addColumn("CI", CI.toArray());
      table_1.setModel(defaultTableModel_1);
      contentPane.repaint();
   }

   public void cargarTablaEstudiantesAct() {

      ArrayList<Object> nombEst = new ArrayList<>();
      ArrayList<Object> CI = new ArrayList<>();
      ArrayList<Object> sexo = new ArrayList<>();
      ArrayList<Estudiante> est = Facultad.getFacultad().estudiantesActivos();

      for (Estudiante estu : est) {
         nombEst.add(estu.getNombre());
         CI.add(estu.getId());
         sexo.add(estu.getSexo());
      }

      defaultTableModel = new DefaultTableModel();
      defaultTableModel.addColumn("Nombre", nombEst.toArray());
      defaultTableModel.addColumn("CI", CI.toArray());
      defaultTableModel.addColumn("Sexo", sexo.toArray());
      table.setModel(defaultTableModel);
      contentPane.repaint();
   }
}
