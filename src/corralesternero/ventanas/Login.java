
package corralesternero.ventanas;

import java.awt.*;
import javax.swing.*;
import componentes.*;

public class Login extends JFrame{
    
    private JFrame login;
    private JLeeUser usuario;
    private JLeePass password;
    private JButton borrar, entrar;
    private JLabel etiUser,etiPass;
    private Font f;
    
    public Login(){
        super("Login");
        setIconImage(new ImageIcon("IconoCorrales.JPG").getImage());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(350,300);
        
        usuario=new JLeeUser();
        password=new JLeePass();
        borrar=new JButton("Limpiar");
        entrar=new JButton("Entrar");
        etiUser=new JLabel("Usuario",SwingConstants.RIGHT);
        etiPass=new JLabel("Contraseña",SwingConstants.RIGHT);
        f=new Font("Arial",Font.BOLD,18);
        etiUser.setFont(f); etiPass.setFont(f);
        
        etiUser.setBounds(50, 50, 100, 35);
        usuario.setBounds(160, 50, 100, 30);
        etiPass.setBounds(30, 110, 120, 35);
        password.setBounds(160, 110 , 100, 30);
        borrar.setBounds(50, 170, 100, 35);
        entrar.setBounds(180, 170, 100, 35);
        
        
        
        add(etiUser);
        add(usuario);
        add(etiPass);
        add(password);
        add(borrar);
        add(entrar);
    }
    
    public void accionLogin(boolean band){
        if(band){
            setVisible(false);
            getUsuario().setText("");
            getPass().setText("");
            getUsuario().requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "Conexion no realizada",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            limpiaLogin();
            getUsuario().requestFocus();
        }
    }
    
    public void limpiaLogin(){
        usuario.setText("");
        password.setText("");
    }
    
    public JLeeUser getUsuario(){
        return usuario;
    }
    public JLeePass getPass(){
        return password;
    }
    public JButton getBorrar(){
        return borrar;
    }
    public JButton getEntrar(){
        return entrar;
    }
}
