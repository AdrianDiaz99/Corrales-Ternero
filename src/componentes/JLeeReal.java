package componentes;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class JLeeReal extends JTextField implements KeyListener {

    private int tamano;

    public JLeeReal() {
        this(5);
    }

    public JLeeReal(int Tam) {
        super(Tam);
        tamano = Tam;
        addKeyListener(this);
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
        if (getText().length() == tamano) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        char T = e.getKeyChar();
        if(getText().length()==0 && e.getKeyChar()=='0'){
            e.consume();
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (!(T >= '0' && T <= '9' || T == '.')) {
            e.consume();
            return;
        }
        if(T!='.' && (getText().length()>=4 && getText().indexOf('.')<=0)){
            e.consume();
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if(T=='.' && (getText().length()<2 || getText().length()>4)){
            e.consume();
            Toolkit.getDefaultToolkit().beep();
            return;
        }

        if (T == '.' && getText().indexOf(".")>= 0) {
            Toolkit.getDefaultToolkit().beep();
            e.consume();
            return;
        }
        if(e.isControlDown()){
            e.consume();
            Toolkit.getDefaultToolkit().beep();
            return;
        }

    }
}
