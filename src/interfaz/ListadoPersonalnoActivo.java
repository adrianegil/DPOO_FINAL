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

public class ListadoPersonalnoActivo extends JDialog {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTable table;
   private JTable table_1;
   private DefaultTableModel defaultTableModel = new DefaultTableModel();
   private DefaultTableModel defaultTableModel_1 = new DefaultTableModel();

   public ListadoPersonalnoActivo() {
      setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoPersonalnoActivo.class.getResource("/recursos/Informatica.PNG")));
      setResizable(false);
      setTitle("Listado del Personal no Activo");
      setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 758, 578);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 46, 358, 446);
      contentPane.add(scrollPane);

      table = new JTable();
      table.setEnabled(false);
      scrollPane.setViewportView(table);

      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(395, 46, 346, 446);
      contentPane.add(scrollPane_1);

      table_1 = new JTable();
      table_1.setEnabled(false);
      scrollPane_1.setViewportView(table_1);

      JLabel lblEstudiantes = new JLabel("Estudiantes");
      lblEstudiantes.setFont(new Font("Arial", Font.BOLD, 13));
      lblEstudiantes.setBounds(10, 21, 80, 14);
      contentPane.add(lblEstudiantes);

      JLabel lblDocntes = new JLabel("Docentes");
      lblDocntes.setFont(new Font("Arial", Font.BOLD, 13));
      lblDocntes.setBounds(395, 21, 80, 14);
      contentPane.add(lblDocntes);

      cargarTablaEstudiantenoAct();
      cargarTablaDocentesnoAct();
   }

   public void cargarTablaEstudiantenoAct() {

      ArrayList<Object> nombreEst = new ArrayList<>();
      ArrayList<Object> CI = new ArrayList<>();
      ArrayList<Object> sexo = new ArrayList<>();

      ArrayList<Estudiante> est = Facultad.getFacultad().estnoActivos();

      for (int i = 0; i < est.size(); i++) {
         Estudiante estu = est.get(i);
         nombreEst.add(estu.getNombre());
         CI.add(estu.getId());
         sexo.add(estu.getSexo());
      }

      defaultTableModel = new DefaultTableModel();
      defaultTableModel.addColumn("Nombre", nombreEst.toArray());
      defaultTableModel.addColumn("CI", CI.toArray());
      defaultTableModel.addColumn("Sexo", sexo.toArray());

      table.setModel(defaultTableModel);
      contentPane.repaint();
   }

   public void cargarTablaDocentesnoAct() {

      ArrayList<Object> nombDoc = new ArrayList<>();
      ArrayList<Object> CI = new ArrayList<>();
      ArrayList<Object> fecha = new ArrayList<>();
      ArrayList<Docente> doc = Facultad.getFacultad().doctnoActivos();

      for (int i = 0; i < doc.size(); i++) {
         Docente doce = doc.get(i);
         nombDoc.add(doce.getNombre());
         CI.add(doce.getId());
         int dia = doce.getFechainc().getDate();
         int mes = doce.getFechainc().getMonth();
         int anno = doce.getFechainc().getYear() + 1900;
         String fec = dia + "/" + (mes + 1) + "/" + anno;
         fecha.add(fec);
      }
      defaultTableModel_1 = new DefaultTableModel();
      defaultTableModel_1.addColumn("Nombre", nombDoc.toArray());
      defaultTableModel_1.addColumn("CI", CI.toArray());
      defaultTableModel_1.addColumn("Fecha de incorporación", fecha.toArray());
      table_1.setModel(defaultTableModel_1);
      contentPane.repaint();
   }
}
