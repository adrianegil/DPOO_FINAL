package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Docente;
import logica.Facultad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

public class AgregarDocente extends JDialog {

   private static final long serialVersionUID = 1L;
   private final JPanel contentPanel = new JPanel();
   private JTextField textNombre;
   private JTextField textID;
   private JButton btnAceptar;
   private JButton btnCancelar;
   private JCheckBox chckbxActivo;
   private JLabel lblNewLabel;
   private JDateChooser dateChooser;
   private int cont = 2;



   public AgregarDocente() {

      setResizable(false);
      setTitle("Datos del Docente");
      setBounds(100, 100, 354, 241);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);
      {
         JLabel lblNombre = new JLabel("Nombre:");
         lblNombre.setBounds(10, 25, 67, 20);
         lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
         contentPanel.add(lblNombre);
      }
      {
         JLabel lblCarnetDeIdentidad = new JLabel("Carnet de identidad:");
         lblCarnetDeIdentidad.setBounds(10, 63, 150, 20);
         lblCarnetDeIdentidad.setFont(new Font("Arial", Font.BOLD, 13));
         contentPanel.add(lblCarnetDeIdentidad);
      }
      {
         JLabel lblEstadoDelDocente = new JLabel("Estado del Docente:");
         lblEstadoDelDocente.setBounds(10, 106, 131, 20);
         lblEstadoDelDocente.setFont(new Font("Arial", Font.BOLD, 13));
         contentPanel.add(lblEstadoDelDocente);
      }
      {
         textNombre = new JTextField();
         textNombre.setBounds(160, 24, 150, 20);
         contentPanel.add(textNombre);
         textNombre.setColumns(10);
      }
      {
         textID = new JTextField();
         textID.setBounds(160, 62, 150, 20);
         contentPanel.add(textID);
         textID.setColumns(10);
      }
      {
         chckbxActivo = new JCheckBox("Activo");
         chckbxActivo.setBounds(160, 106, 97, 20);
         chckbxActivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (cont % 2 == 0) {
                  JOptionPane.showMessageDialog(AgregarDocente.this, "Introduzca la fecha de incorporación del docente");
                  lblNewLabel.setVisible(true);
                  dateChooser.setVisible(true);
                  cont++;
               } else {
                  lblNewLabel.setVisible(false);
                  dateChooser.setVisible(false);
                  cont++;
               }
            }
         });
         chckbxActivo.setSelected(true);
         chckbxActivo.setFont(new Font("Arial", Font.PLAIN, 13));
         contentPanel.add(chckbxActivo);
      }

      dateChooser = new JDateChooser();
      dateChooser.setVisible(false);
      dateChooser.setBounds(59, 148, 95, 20);
      contentPanel.add(dateChooser);

      lblNewLabel = new JLabel("Fecha:");
      lblNewLabel.setVisible(false);
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
      lblNewLabel.setBounds(10, 154, 67, 14);
      contentPanel.add(lblNewLabel);
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            btnAceptar = new JButton("Aceptar");
            btnAceptar.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  String nombre = textNombre.getText();
                  String ID = textID.getText();
                  boolean nombrecorrecto = Facultad.validarNombre(nombre);
                  boolean idcorrecto = Facultad.validarCI(ID);
                  boolean seguir = true;

                  if (nombre.isEmpty() || ID.isEmpty()) {
                     JOptionPane.showMessageDialog(AgregarDocente.this, "Todos los  datos  deben estar rellenados");
                     if (nombre.isEmpty())
                        textNombre.setBackground(Color.RED);
                     if (ID.isEmpty())
                        textID.setBackground(Color.RED);
                  } else {

                     Date fechainc = dateChooser.getDate();

                     if (!nombrecorrecto) {
                        JOptionPane.showMessageDialog(AgregarDocente.this, "(Datos erroneos) El nombre del docente no puede tener números");
                        seguir = false;
                     }

                     if (!idcorrecto) {
                        JOptionPane.showMessageDialog(AgregarDocente.this, "(Datos erroneos) El carnet del docente no puede tener letras");
                        seguir = false;
                     }
                     if (ID.length() != 11) {
                        JOptionPane.showMessageDialog(AgregarDocente.this, "(Datos erroneos) El carnet del docente debe tener 11 díjitos");
                        seguir = false;
                     }

                     if (dateChooser.getDate() == null && !chckbxActivo.isSelected()) {
                        JOptionPane.showMessageDialog(AgregarDocente.this, "Todos los datos deben estar rellenados");

                     } else if (seguir) {
                        if (!chckbxActivo.isSelected()) {
                           Docente doc = new Docente(nombre, ID, chckbxActivo.isSelected(), fechainc);
                           Facultad.getFacultad().getPersonas().add(doc);
                           JOptionPane.showMessageDialog(AgregarDocente.this, "Se ha introducido un nuevo docente");
                           AgregarDocente.this.dispose();
                        } else {
                           //anyadir un nuevo estudiante a la coleccion
                           Docente doc = new Docente(nombre, ID, chckbxActivo.isSelected());
                           Facultad.getFacultad().getPersonas().add(doc);
                           JOptionPane.showMessageDialog(AgregarDocente.this, "Se ha introducido un nuevo docente");
                           AgregarDocente.this.dispose();
                        }
                     }
                  }

               }
            });
            buttonPane.add(btnAceptar);
         }
         {
            btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  AgregarDocente.this.dispose();
               }
            });
            buttonPane.add(btnCancelar);
         }
      }
   }
}
