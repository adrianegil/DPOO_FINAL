package interfaz;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import auxiliar.Seguridad;
import auxiliar.Utiles;

import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.Color;

public class Autenticar extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField textNombreUsuario;
   private JPasswordField passwordContraseña;
   private JButton btnAceptar;
   private JButton btnCerrar;

   public Autenticar() {

      setIconImage(Toolkit.getDefaultToolkit().getImage(Autenticar.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
      setResizable(false);
      setTitle("Informaci\u00F3n de Usuario");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 483, 331);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel labelImg = new JLabel("");
      labelImg.setLocation(181, 113);
      labelImg.setSize(119, 81);
      labelImg.setIcon(Utiles.imagenAjustadaDefin(119, 81, "/recursos/Informatica.PNG", 110));
      getContentPane().add(labelImg);

      Panel panel = new Panel();
      panel.setBounds(5, 274, 468, 23);
      contentPane.add(panel);
      panel.setLayout(new GridLayout(0, 2, 4, 0));

      btnAceptar = new JButton("Aceptar");
      btnAceptar.setForeground(Color.BLACK);
      btnAceptar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            String nombre = textNombreUsuario.getText();
            textNombreUsuario.setText("");

            String contrasenna = new String(passwordContraseña.getPassword());
            passwordContraseña.setText("");

            Seguridad aux = new Seguridad();
            if (aux.acceso(nombre, contrasenna)) {
               Autenticar.this.setVisible(false);
               MenuPrincipal menu = new MenuPrincipal();
               menu.cargarTablaGuardiaEsteMes();
               menu.setVisible(true);
            } else
               JOptionPane.showMessageDialog(Autenticar.this, "Usted no tiene acceso al Sidtema", "ERROR", JOptionPane.ERROR_MESSAGE);
         }
      });
      panel.add(btnAceptar);
      getRootPane().setDefaultButton(btnAceptar);

      btnCerrar = new JButton("Cerrar");
      btnCerrar.addMouseListener(new MouseAdapter() {
      });
      btnCerrar.setForeground(Color.BLACK);
      btnCerrar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Autenticar.this.dispose();
         }
      });
      panel.add(btnCerrar);
      btnCerrar.setMnemonic('c');

      JLabel lblSistemaParaAutomatizacion = new JLabel("Sistema para Automatizaci\u00F3n de Guardias");
      lblSistemaParaAutomatizacion.setFont(new Font("Arial", Font.BOLD, 22));
      lblSistemaParaAutomatizacion.setBounds(26, 11, 425, 69);
      contentPane.add(lblSistemaParaAutomatizacion);

      JLabel lblIntroduzcaLasCredenciales = new JLabel("Introduzca las credenciales de acceso");
      lblIntroduzcaLasCredenciales.setFont(new Font("Arial", Font.PLAIN, 16));
      lblIntroduzcaLasCredenciales.setBounds(114, 197, 276, 23);
      contentPane.add(lblIntroduzcaLasCredenciales);

      JLabel lblNewLabel = new JLabel("Jefe de Guardia:");
      lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
      lblNewLabel.setBounds(10, 231, 119, 23);
      contentPane.add(lblNewLabel);

      textNombreUsuario = new JTextField();
      textNombreUsuario.setBounds(139, 233, 107, 20);
      contentPane.add(textNombreUsuario);
      textNombreUsuario.setColumns(10);

      JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
      lblContrasea.setFont(new Font("Arial", Font.BOLD, 13));
      lblContrasea.setBounds(264, 231, 86, 23);
      contentPane.add(lblContrasea);

      passwordContraseña = new JPasswordField();
      passwordContraseña.setBounds(347, 233, 107, 20);
      contentPane.add(passwordContraseña);

      JLabel lblInformtica = new JLabel("INFORM\u00C1TICA");
      lblInformtica.setVerticalAlignment(SwingConstants.BOTTOM);
      lblInformtica.setHorizontalAlignment(SwingConstants.CENTER);
      lblInformtica.setFont(new Font("Arial", Font.BOLD, 18));
      lblInformtica.setBounds(171, 79, 135, 23);
      contentPane.add(lblInformtica);
   }
}
