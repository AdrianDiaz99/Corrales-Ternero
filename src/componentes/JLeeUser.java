
package componentes;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;


public class JLeeUser extends JTextField implements KeyListener{
    
    int max;
    
    public JLeeUser(){
        this(8);
    }
    
    public JLeeUser(int max){
        super(max);
        this.max=max;
        addKeyListener(this);
    }


    public void keyTyped(KeyEvent e) {
        if(getText().length()==max){
            e.consume();
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        char c=e.getKeyChar();
        if( ( (c<'a' || c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') ) && (e.getKeyCode()!=KeyEvent.VK_ENTER) ){
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
