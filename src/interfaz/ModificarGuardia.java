package interfaz;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import java.util.Date;

import logica.Facultad;

import java.awt.Toolkit;

public class ModificarGuardia extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField textFieldPer1;
   private JTextField textField_CI1;
   private JTextField textField_Per2;
   private JTextField textField_CI2;
   private JDateChooser dateChooser1;
   private JDateChooser dateChooser2;

   public ModificarGuardia() {
      setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarGuardia.class.getResource("/recursos/Informatica.PNG")));
      setResizable(false);
      setTitle("Modificar Guardia ");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 658, 301);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel("Introduzca los datos de las dos personas");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
      lblNewLabel.setBounds(148, 11, 423, 25);
      contentPane.add(lblNewLabel);

      JLabel lblNewLabel_1 = new JLabel("Nombre:");
      lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
      lblNewLabel_1.setBounds(10, 110, 90, 14);
      contentPane.add(lblNewLabel_1);

      JLabel lblCarnetDeIdentidad = new JLabel("Carnet de Identidad:");
      lblCarnetDeIdentidad.setFont(new Font("Arial", Font.BOLD, 13));
      lblCarnetDeIdentidad.setBounds(10, 147, 132, 14);
      contentPane.add(lblCarnetDeIdentidad);

      JLabel lblFechaDeGuardia = new JLabel("Fecha de Guardia:");
      lblFechaDeGuardia.setFont(new Font("Arial", Font.BOLD, 13));
      lblFechaDeGuardia.setBounds(10, 186, 132, 14);
      contentPane.add(lblFechaDeGuardia);

      textFieldPer1 = new JTextField();
      textFieldPer1.setBounds(144, 108, 168, 20);
      contentPane.add(textFieldPer1);
      textFieldPer1.setColumns(10);

      textField_CI1 = new JTextField();
      textField_CI1.setBounds(144, 145, 168, 20);
      contentPane.add(textField_CI1);
      textField_CI1.setColumns(10);

      JLabel lblNombre = new JLabel("Nombre:");
      lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
      lblNombre.setBounds(334, 114, 90, 14);
      contentPane.add(lblNombre);

      JLabel lblNewLabel_2 = new JLabel("Carnet de Identidad:");
      lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
      lblNewLabel_2.setBounds(334, 147, 132, 14);
      contentPane.add(lblNewLabel_2);

      JLabel lblNewLabel_3 = new JLabel("Fecha de Guardia:");
      lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 13));
      lblNewLabel_3.setBounds(334, 186, 132, 14);
      contentPane.add(lblNewLabel_3);

      textField_Per2 = new JTextField();
      textField_Per2.setBounds(474, 108, 168, 20);
      contentPane.add(textField_Per2);
      textField_Per2.setColumns(10);

      textField_CI2 = new JTextField();
      textField_CI2.setBounds(474, 145, 168, 20);
      contentPane.add(textField_CI2);
      textField_CI2.setColumns(10);

      JButton btnCancelar = new JButton("Cancelar");
      btnCancelar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ModificarGuardia.this.dispose();
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
         }
      });
      btnCancelar.setBounds(506, 238, 138, 23);
      contentPane.add(btnCancelar);

      JButton btnModificarGuardia = new JButton("Modificar Guardia");
      btnModificarGuardia.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            String nombre1 = textFieldPer1.getText();
            String nombre2 = textField_Per2.getText();
            String ci1 = textField_CI1.getText();
            String ci2 = textField_CI2.getText();

            boolean nombrecorrecto = Facultad.validarNombre(nombre1);
            boolean idcorrecto = Facultad.validarCI(ci1);
            boolean seguir = true;

            if (textFieldPer1.getText().isEmpty() || textField_Per2.getText().isEmpty() || textField_CI1.getText().isEmpty() || textField_CI2.getText().isEmpty() ||
                    dateChooser1.getDate() == null || dateChooser2.getDate() == null) {
               JOptionPane.showMessageDialog(ModificarGuardia.this, "Todos los datos deben estar rellenados");
            } else {

               Date fech1 = dateChooser1.getDate();
               Date fech2 = dateChooser2.getDate();

               GregorianCalendar fecha1 = new GregorianCalendar();
               GregorianCalendar fecha2 = new GregorianCalendar();

               int año1 = fech1.getYear() + 1900;
               int mes1 = fech1.getMonth();
               int dia1 = fech1.getDate();

               int año2 = fech2.getYear() + 1900;
               int mes2 = fech2.getMonth();
               int dia2 = fech2.getDate();

               fecha1.set(año1, mes1, dia1);
               fecha2.set(año2, mes2, dia2);

               if (!nombrecorrecto) {
                  JOptionPane.showMessageDialog(ModificarGuardia.this, "(Datos erroneos) El nombre de la primera persona no puede tener numeros");
                  seguir = false;
               }
               if (!idcorrecto) {
                  JOptionPane.showMessageDialog(ModificarGuardia.this, "(Datos erroneos) El carnet de identidad de la primera persona no puede tener letras");
                  seguir = false;
               }
               if (ci1.length() != 11) {
                  JOptionPane.showMessageDialog(ModificarGuardia.this, "(Datos erroneos) El carnet de identidad de la primera persona debe tener 11 dijitos");
                  seguir = false;
               }
               boolean nombrecorrecto2 = Facultad.validarNombre(nombre2);
               boolean idcorrecto2 = Facultad.validarCI(ci2);

               if (!nombrecorrecto2) {
                  JOptionPane.showMessageDialog(ModificarGuardia.this, "(Datos erroneos) El nombre de la segunda persona no puede tener numeros");
                  seguir = false;
               }
               if (!idcorrecto2) {
                  JOptionPane.showMessageDialog(ModificarGuardia.this, "(Datos erroneos) El carnet de identidad de la segunda persona no puede tener letras");
                  seguir = false;
               }
               if (ci2.length() != 11) {
                  JOptionPane.showMessageDialog(ModificarGuardia.this, "(Datos erroneos) El carnet de identidad de la segunda persona debe tener 11 dijitos");
                  seguir = false;
               }
               if (seguir) {
                  boolean modificado = Facultad.getFacultad().modificarGuardia(nombre1, ci1, fecha1, nombre2, ci2, fecha2);

                  if (modificado) {
                     JOptionPane.showMessageDialog(ModificarGuardia.this, "Se ha modificado correctamente");
                     ModificarGuardia.this.dispose();
                     MenuPrincipal menu = new MenuPrincipal();
                     menu.setVisible(true);
                  } else {
                     JOptionPane.showMessageDialog(ModificarGuardia.this, "No se ha podido modificar la guardia");
                     ModificarGuardia.this.dispose();
                     MenuPrincipal menu = new MenuPrincipal();
                     menu.setVisible(true);
                  }
               }
            }
         }
      });
      btnModificarGuardia.setBounds(358, 238, 138, 23);
      contentPane.add(btnModificarGuardia);

      dateChooser1 = new JDateChooser();
      dateChooser1.setBounds(144, 180, 95, 20);
      contentPane.add(dateChooser1);

      dateChooser2 = new JDateChooser();
      dateChooser2.setBounds(474, 180, 95, 20);
      contentPane.add(dateChooser2);

      JLabel lblPrimeraPersona = new JLabel("Primera Persona");
      lblPrimeraPersona.setFont(new Font("Arial", Font.BOLD, 14));
      lblPrimeraPersona.setBounds(91, 61, 125, 25);
      contentPane.add(lblPrimeraPersona);

      JLabel lblNewLabel_4 = new JLabel("Segunda Persona");
      lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
      lblNewLabel_4.setBounds(416, 61, 132, 25);
      contentPane.add(lblNewLabel_4);
   }
}
