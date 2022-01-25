package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Estudiante;
import logica.Facultad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarEstudiante extends JDialog {

   private static final long serialVersionUID = 1L;
   private final JPanel contentPanel = new JPanel();
   private JTextField textNombre;
   private JTextField textID;
   private JButton btnAceptar;
   private JButton btnCancelar;
   private JCheckBox chckbxActivo;
   private JComboBox comboBoxSexo;


   public AgregarEstudiante() {

      setResizable(false);
      setTitle("Datos del Estudiante");
      setBounds(100, 100, 355, 238);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);
      {
         JLabel lblNombre = new JLabel("Nombre:");
         lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
         lblNombre.setBounds(10, 28, 63, 20);
         contentPanel.add(lblNombre);
      }
      {
         JLabel lblCarnetDeIdentidad = new JLabel("Carnet de Identidad:");
         lblCarnetDeIdentidad.setFont(new Font("Arial", Font.BOLD, 13));
         lblCarnetDeIdentidad.setBounds(10, 68, 133, 20);
         contentPanel.add(lblCarnetDeIdentidad);
      }
      {
         JLabel lblEstadoDelEstudiante = new JLabel("Estado del Estudiante:");
         lblEstadoDelEstudiante.setFont(new Font("Arial", Font.BOLD, 13));
         lblEstadoDelEstudiante.setBounds(10, 103, 146, 20);
         contentPanel.add(lblEstadoDelEstudiante);
      }
      {
         JLabel lblSexo = new JLabel("Sexo:");
         lblSexo.setFont(new Font("Arial", Font.BOLD, 13));
         lblSexo.setBounds(10, 142, 46, 14);
         contentPanel.add(lblSexo);
      }
      {
         textNombre = new JTextField();
         textNombre.setBounds(169, 29, 136, 20);
         contentPanel.add(textNombre);
         textNombre.setColumns(10);
      }
      {
         textID = new JTextField();
         textID.setBounds(169, 69, 136, 20);
         contentPanel.add(textID);
         textID.setColumns(10);
      }
      {
         chckbxActivo = new JCheckBox("Activo");
         chckbxActivo.setSelected(true);
         chckbxActivo.setFont(new Font("Arial", Font.PLAIN, 13));
         chckbxActivo.setBounds(169, 103, 97, 23);
         contentPanel.add(chckbxActivo);
      }
      {
         comboBoxSexo = new JComboBox();
         comboBoxSexo.setModel(new DefaultComboBoxModel(new String[]{"Masculino", "Femenino"}));
         comboBoxSexo.setBounds(54, 140, 91, 20);
         contentPanel.add(comboBoxSexo);
      }
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
                  String sexo = (String) comboBoxSexo.getSelectedItem();
                  boolean nombrecorrecto = Facultad.validarNombre(nombre);
                  boolean idcorrecto = Facultad.validarCI(ID);
                  boolean seguir = true;

                  if (nombre.isEmpty() || ID.isEmpty()) {
                     JOptionPane.showMessageDialog(AgregarEstudiante.this, "Los datos resaltos deben estar rellenados");
                     if (nombre.isEmpty())
                        textNombre.setBackground(Color.RED);
                     if (ID.isEmpty())
                        textID.setBackground(Color.RED);
                  } else {

                     if (!nombrecorrecto) {
                        JOptionPane.showMessageDialog(AgregarEstudiante.this, "(Datos erroneos) El nombre del estudiante no puede tener números");
                        seguir = false;
                     }

                     if (!idcorrecto) {
                        JOptionPane.showMessageDialog(AgregarEstudiante.this, "(Datos erroneos) El carnet de identidad del estudiante no puede tener letras");
                        seguir = false;
                     }
                     if (ID.length() != 11) {
                        JOptionPane.showMessageDialog(AgregarEstudiante.this, "(Datos erroneos) El carnet de identidad del estudiante debe tener 11 díjitos");
                        seguir = false;
                     }

                     if (seguir) {
                        char sexo1;

                        if (sexo.equalsIgnoreCase("Masculino")) {
                           sexo1 = 'M';
                        } else {
                           sexo1 = 'F';
                        }
                        JOptionPane.showMessageDialog(AgregarEstudiante.this, "Se ha introducido un nuevo estudiante");
                        Facultad.getFacultad().getPersonas().add(new Estudiante(nombre, ID, chckbxActivo.isSelected(), sexo1));
                        AgregarEstudiante.this.dispose();
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
                  AgregarEstudiante.this.dispose();
               }
            });
            buttonPane.add(btnCancelar);
         }
      }
   }
}
