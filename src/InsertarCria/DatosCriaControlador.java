package InsertarCria;

import corralesternero.CorralesTerneroModelo;
import corralesternero.Rutinas;
import java.awt.event.*;

public class DatosCriaControlador implements ActionListener, KeyListener, ItemListener, WindowListener, FocusListener {

    DatosCria vista;
    DatosCriaModelo modelo;

    public DatosCriaControlador(DatosCria vista, DatosCriaModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getLimpiar()) {
            vista.limpiaComponentes();
            return;
        }
        if (e.getSource() == vista.getGrabar()) {
            grabar();
            return;
        }

        if (e.getSource() == vista.getImprimir()) {
            modelo.imprime(vista.getTablaCrias());
            vista.limpiaTabla();
            return;
        }

        if (e.getSource() == vista.getCalGrasa()) {
            if (vista.ready()) {
                calculaGrasa();
            }
            vista.getEstado().requestFocus();
            return;
        }

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource() == vista.getLimpiar()) {
                vista.limpiaComponentes();
                return;
            }
            if (e.getSource() == vista.getGrabar()) {
                grabar();
                return;
            }
            if (e.getSource() == vista.getEstado()) {
                vista.getCiudad().requestFocus();
            }

            if (e.getSource() == vista.getCorral()) {
                vista.getGrabar().requestFocus();
                return;
            }

            if (e.getSource() == vista.getCiudad()) {
                vista.getCorral().requestFocus();
                return;
            }

            if (e.getSource() == vista.getGrabar()) {
                grabar();
                return;
            }

            if (e.getSource() == vista.getPeso()) {

                vista.getCantGrasa().requestFocus();
                return;

            }

            if (e.getSource() == vista.getCantGrasa()) {

                vista.getColorMusculo().requestFocus();
                return;
            }

            if (e.getSource() == vista.getColorMusculo()) {

                vista.getEstado().requestFocus();
                return;
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {

        if (e.getSource() == vista.getEstado()) {
            if (vista.getEstado().getSelectedItem() == "Seleccione") {
                Rutinas.limpiaCombo(vista.getCiudad());
                return;
            }
            vista.setCiudades(vista.getEstado().getSelectedIndex());
            return;
        }
    }

    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista) {
            vista.ocultar();
        }
    }
    
    public void focusLost(FocusEvent e) {
        
        if( e.getSource() == vista.getCantGrasa() && vista.ready() ){
            
            calculaGrasa();
            return;
            
        }
        
        if( e.getSource() == vista.getPeso() && vista.ready() ){
            
            calculaGrasa();
            return;
            
        }
        
        if( e.getSource() == vista.getColorMusculo() && vista.ready() ){
            
            calculaGrasa();
            return;
            
        }
        
    }
    
    public void focusGained(FocusEvent e) {
        
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void windowClosed(WindowEvent we) {

    }

    public void windowOpened(WindowEvent we) {

    }

    public void windowIconified(WindowEvent we) {

    }

    public void windowDeiconified(WindowEvent we) {

    }

    public void windowActivated(WindowEvent we) {

    }

    public void windowDeactivated(WindowEvent we) {

    }

    private void grabar() {
        String peso, cantGrasa;
        int idColMusc, ciuId, corId, graId, criId;

        peso = vista.getPeso().getText();
        cantGrasa = vista.getCantGrasa().getText();
        idColMusc = vista.getColorMusculo().getSelectedIndex();
        graId = vista.getGrasa();
        ciuId = vista.getCiudad().getSelectedIndex();
        corId = vista.getCorral().getSelectedIndex();
        graId = vista.getGrasa();
        System.out.println("graId: "+graId);
        criId = modelo.grabarDatosCria(peso, cantGrasa, idColMusc, ciuId, corId, graId);
        vista.recibeInsercionCria(criId);
        vista.getPeso().requestFocus();
    }

    public void calculaGrasa() {
        int graId, colMuscId;
        double peso, cantGrasa;

        peso = Double.parseDouble(vista.getPeso().getText());
        cantGrasa = Double.parseDouble(vista.getCantGrasa().getText());
        colMuscId = vista.getColorMusculo().getSelectedIndex();

        graId = modelo.calculaGrasa(peso, cantGrasa, colMuscId);

        vista.setGrasa(graId);
    }


}
