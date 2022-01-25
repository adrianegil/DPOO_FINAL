package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import logica.Facultad;

import java.awt.Toolkit;

public class EliminarGuardia extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField textFieldNombre;
   private JTextField textField_CI;
   private JDateChooser dateChooserFecha;


   public EliminarGuardia() {

      setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarGuardia.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
      setResizable(false);
      setTitle("Eliminar Guardia de una Persona");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 352, 263);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblIntroduzcaLosDatos = new JLabel("Introduzca los datos de la persona");
      lblIntroduzcaLosDatos.setFont(new Font("Arial", Font.BOLD, 16));
      lblIntroduzcaLosDatos.setBounds(40, 27, 265, 27);
      contentPane.add(lblIntroduzcaLosDatos);

      JLabel lblNombre = new JLabel("Nombre:");
      lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
      lblNombre.setBounds(10, 70, 68, 21);
      contentPane.add(lblNombre);

      textFieldNombre = new JTextField();
      textFieldNombre.setBounds(165, 71, 174, 21);
      contentPane.add(textFieldNombre);
      textFieldNombre.setColumns(10);

      JLabel lblCarnetDeIdentidad = new JLabel("Carnet de Identidad:");
      lblCarnetDeIdentidad.setFont(new Font("Arial", Font.BOLD, 13));
      lblCarnetDeIdentidad.setBounds(10, 108, 151, 21);
      contentPane.add(lblCarnetDeIdentidad);

      textField_CI = new JTextField();
      textField_CI.setBounds(165, 108, 174, 21);
      contentPane.add(textField_CI);
      textField_CI.setColumns(10);

      JLabel lblFechaDeLa = new JLabel("Fecha de la Guardia:");
      lblFechaDeLa.setFont(new Font("Arial", Font.BOLD, 13));
      lblFechaDeLa.setBounds(10, 155, 145, 21);
      contentPane.add(lblFechaDeLa);

      JButton btnNewButton = new JButton("Cancelar");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            EliminarGuardia.this.dispose();
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
         }
      });
      btnNewButton.setBounds(211, 199, 128, 23);
      contentPane.add(btnNewButton);

      JButton btnEliminarGuardia = new JButton("Eliminar Guardia");
      btnEliminarGuardia.addActionListener(new ActionListener() {
         @SuppressWarnings("deprecation")
         public void actionPerformed(ActionEvent e) {

            String nombre = textFieldNombre.getText();
            String ci = textField_CI.getText();
            boolean nombrecorrecto = Facultad.validarNombre(nombre);
            boolean idcorrecto = Facultad.validarCI(ci);
            boolean seguir = true;

            if (nombre.isEmpty() || ci.isEmpty() || dateChooserFecha.getDate() == null) {
               JOptionPane.showMessageDialog(EliminarGuardia.this, "Los datos resaltos deben estar rellenados");

               if (nombre.isEmpty())
                  textFieldNombre.setBackground(Color.RED);

               if (ci.isEmpty())
                  textField_CI.setBackground(Color.RED);

               if (dateChooserFecha.getDate() == null)
                  dateChooserFecha.setBackground(Color.RED);
            } else {
               if (!nombrecorrecto) {
                  JOptionPane.showMessageDialog(EliminarGuardia.this, "(Datos erroneos) El nombre del estudiante no puede tener números");
                  seguir = false;
               }
               if (!idcorrecto) {
                  JOptionPane.showMessageDialog(EliminarGuardia.this, "(Datos erroneos) El carnet de identidad del estudiante no puede tener letras");
                  seguir = false;
               }
               if (ci.length() != 11) {
                  JOptionPane.showMessageDialog(EliminarGuardia.this, "(Datos erroneos) El carnet de identidad del estudiante debe tener 11 díjitos");
                  seguir = false;
               }
               if (seguir) {

                  Date fecha = dateChooserFecha.getDate();
                  int año = fecha.getYear() + 1900;
                  int mes = fecha.getMonth();
                  int dia = fecha.getDate();

                  Facultad fac = Facultad.getFacultad();
                  GregorianCalendar fechaGuardia = new GregorianCalendar();
                  fechaGuardia.set(año, mes, dia);

                  boolean eliminar = fac.eliminarGuardia(fechaGuardia, ci, nombre);

                  if (eliminar) {
                     JOptionPane.showMessageDialog(EliminarGuardia.this, "Se ha eliminado correctamente");
                     EliminarGuardia.this.dispose();
                     MenuPrincipal menu = new MenuPrincipal();
                     menu.setVisible(true);
                  } else {
                     JOptionPane.showMessageDialog(EliminarGuardia.this, "No se ha podido eliminar la guardia");
                     EliminarGuardia.this.dispose();
                     MenuPrincipal menu = new MenuPrincipal();
                     menu.setVisible(true);
                  }
               }
            }
         }
      });
      btnEliminarGuardia.setBounds(73, 199, 128, 23);
      contentPane.add(btnEliminarGuardia);
      dateChooserFecha = new JDateChooser();
      dateChooserFecha.setBounds(165, 155, 95, 20);
      contentPane.add(dateChooserFecha);
   }
}
