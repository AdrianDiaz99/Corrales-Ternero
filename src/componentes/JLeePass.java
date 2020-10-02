/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPasswordField;

/**
 *
 * @author adria
 */
public class JLeePass extends JPasswordField implements KeyListener{

    int max;
    
    public JLeePass(){
        this(8);
    }
    public JLeePass(int max){
        super(max);
        this.max=max;
        addKeyListener(this);
    }
    public void keyTyped(KeyEvent e) {
        if(this.getPassword().length==max){
            e.consume();
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        char c=e.getKeyChar();
        if( ( (c<'a' || c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') ) ){
            e.consume();
            return;
        }
        if(e.isControlDown()){
            e.consume();
            Toolkit.getDefaultToolkit().beep();
            return;
        }
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
}
