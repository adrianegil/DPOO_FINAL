package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Docente;
import logica.Estudiante;
import logica.Facultad;
import logica.Persona;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AñadirPriorizado extends JDialog {

   private static final long serialVersionUID = 1L;
   private final JPanel contentPanel = new JPanel();
   private JTextField textFieldNombre;
   private JTextField textField_CI;
   private JButton btnAceptar;
   private JButton btnCancelar;
   private JComboBox comboBoxCatg;


   public AñadirPriorizado() {

      setResizable(false);
      setTitle("A\u00F1adir a Lista de Priorizados");
      setBounds(100, 100, 344, 210);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);

      JLabel lblNombre = new JLabel("Nombre:");
      lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
      lblNombre.setBounds(10, 26, 75, 21);
      contentPanel.add(lblNombre);
      {
         JLabel lblCarnetDeIdentidad = new JLabel("Carnet de Identidad:");
         lblCarnetDeIdentidad.setFont(new Font("Arial", Font.BOLD, 14));
         lblCarnetDeIdentidad.setBounds(10, 68, 149, 21);
         contentPanel.add(lblCarnetDeIdentidad);
      }

      comboBoxCatg = new JComboBox();
      comboBoxCatg.setModel(new DefaultComboBoxModel(new String[]{"Estudiante", "Docente"}));
      comboBoxCatg.setBounds(94, 117, 88, 20);
      contentPanel.add(comboBoxCatg);

      textFieldNombre = new JTextField();
      textFieldNombre.setBounds(160, 27, 149, 20);
      contentPanel.add(textFieldNombre);
      textFieldNombre.setColumns(10);

      textField_CI = new JTextField();
      textField_CI.setBounds(160, 69, 149, 20);
      contentPanel.add(textField_CI);
      textField_CI.setColumns(10);

      JLabel lblCategora = new JLabel("Categor\u00EDa:");
      lblCategora.setFont(new Font("Arial", Font.BOLD, 14));
      lblCategora.setBounds(10, 116, 75, 21);
      contentPanel.add(lblCategora);
      {
         JPanel buttonPane = new JPanel();
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            btnAceptar = new JButton("Aceptar");
            btnAceptar.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  String nombre = textFieldNombre.getText();
                  String ci = textField_CI.getText();
                  String catg = (String) comboBoxCatg.getSelectedItem();
                  int i = 0;
                  boolean encontrado = false;
                  ArrayList<Estudiante> est = Facultad.getFacultad().estudiantesActivos();
                  ArrayList<Docente> doc = Facultad.getFacultad().docentesActivos();
                  boolean nombrecorrecto = Facultad.validarNombre(nombre);
                  boolean idcorrecto = Facultad.validarCI(ci);
                  boolean seguir = true;

                  if (nombre.isEmpty() | ci.isEmpty()) {
                     JOptionPane.showMessageDialog(AñadirPriorizado.this, "Los datos resaltos deben estar rellenados");
                     if (nombre.isEmpty())
                        textFieldNombre.setBackground(Color.RED);
                     if (ci.isEmpty())
                        textField_CI.setBackground(Color.RED);
                  } else {
                     if (!nombrecorrecto) {
                        JOptionPane.showMessageDialog(AñadirPriorizado.this, "(Datos erroneos) El nombre del estudiante no puede tener úmeros");
                        seguir = false;
                     }
                     if (!idcorrecto) {
                        JOptionPane.showMessageDialog(AñadirPriorizado.this, "(Datos erroneos) El carnet de identidad del estudiante no puede tener letras");
                        seguir = false;
                     }
                     if (ci.length() != 11) {
                        JOptionPane.showMessageDialog(AñadirPriorizado.this, "(Datos erroneos) El carnet de identidad del estudiante debe tener 11 díjitos");
                        seguir = false;
                     }
                     if (seguir) {
                        if (catg.equalsIgnoreCase("Estudiante")) {
                           while (i < est.size() && !encontrado) {
                              Persona p = est.get(i);
                              if (p.getNombre().equalsIgnoreCase(nombre) && p.getId().equalsIgnoreCase(ci)) {
                                 Facultad.getFacultad().getPriorizados().add(p);
                                 encontrado = true;
                              }
                              i++;
                           }
                        }
                        if (catg.equalsIgnoreCase("Docente")) {
                           while (i < doc.size() && !encontrado) {
                              Persona p = doc.get(i);
                              if (p.getNombre().equalsIgnoreCase(nombre) && p.getId().equalsIgnoreCase(ci)) {
                                 Facultad.getFacultad().getPriorizados().add(p);
                                 encontrado = true;
                              }
                              i++;
                           }
                        }

                        if (encontrado) {
                           JOptionPane.showMessageDialog(AñadirPriorizado.this, "Se añadió esta persona a la lista de priorizados");
                           AñadirPriorizado.this.dispose();
                        } else {
                           JOptionPane.showMessageDialog(AñadirPriorizado.this, " No se ha encontrado esta persona en la lista del personal activo ");
                           AñadirPriorizado.this.dispose();
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
                  AñadirPriorizado.this.dispose();
               }
            });
            buttonPane.add(btnCancelar);
         }
      }
   }
}
