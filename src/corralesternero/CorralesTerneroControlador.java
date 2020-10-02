package corralesternero;

import java.awt.event.*;
import java.util.Vector;
import javax.swing.JComboBox;

public class CorralesTerneroControlador implements ActionListener, ItemListener, WindowListener, KeyListener, MouseListener {

    CorralesTerneroVista vista = new CorralesTerneroVista();
    CorralesTerneroModelo modelo = new CorralesTerneroModelo();

    public CorralesTerneroControlador(CorralesTerneroVista vista, CorralesTerneroModelo modelo) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void actionPerformed(ActionEvent e) {
        
        //**********SENSORES*************
        if(e.getSource() == vista.getItemSensores()){
            vista.getVSensores().muestra();
            return;
        }
        
        if(e.getSource() == vista.getVSensores().getGrabar()){
            vista.getVSensores().agregaSensor(CorralesTerneroModelo.grabaSensor( 
                    vista.getVSensores().getCiudades().getSelectedIndex() ) );
            return;
        }
        
        if(e.getSource() == vista.getVSensores().getBorrar()){
            if(vista.getVSensores().getTablaSensores().getSelectedRow() == -1 ){
                return;
            }
            CorralesTerneroModelo.deleteSensor(vista.getVSensores().getTablaSensores().getValueAt(
                    vista.getVSensores().getTablaSensores().getSelectedRow(),0 ));
            vista.getVSensores().quitaSensor();
            vista.getVSensores().requestFocus();
            return;
        }
        
        if(e.getSource() == vista.getVSensores().getImprimir()){
            modelo.imprime(vista.getVSensores().getTablaSensores());
            return;
        }
        
        if(e.getSource() == vista.getItemEnfermas()){
            vista.getVCriasEnfermas().muestra();
            return;
        }
        
        if(e.getSource() == vista.getVCriasEnfermas().getLimpiar()){
            vista.getVCriasEnfermas().limpiaCombos();
            return;
        }
        
        if(e.getSource() == vista.getVCriasEnfermas().getConsultar()){
            vista.getVCriasEnfermas().llenaTablaCrias();
            return;
        }
        
        if(e.getSource() == vista.getVCriasEnfermas().getLiberar()){
            vista.getVCriasEnfermas().mostrarIngresarCorral();
            return;
        }
        if(e.getSource() == vista.getVCriasEnfermas().getGuardar()){
            CorralesTerneroModelo.liberarCria(vista.getVCriasEnfermas().getSelectedCria(), vista.getVCriasEnfermas().getCorral());
            vista.getVCriasEnfermas().quitaCria();
            return;
        }
        
        if(e.getSource() == vista.getVCriasEnfermas().getSacrificar()){
            CorralesTerneroModelo.sacrificarCria(vista.getVCriasEnfermas().getSelectedCria());
            vista.getVCriasEnfermas().quitaCria();
            return;
        }
        
        if(e.getSource() == vista.getVRegCuarentena().getEnviarSeleccionDieta()){
            CorralesTerneroModelo.mandarCuarentena(
                    vista.getVRegCuarentena().getCrias().getValueAt(
                            vista.getVRegCuarentena().getCrias().getSelectedRow(), 0),
                    vista.getVRegCuarentena().getComentario(),
                    vista.getVRegCuarentena().getSelectedMed());     
            vista.getVRegCuarentena().eliminaCria(vista.getVRegCuarentena().getCrias().getSelectedRow());
            vista.getVRegCuarentena().hazTablaReporte(new Vector<Vector>());
            vista.getVRegCuarentena().ocultarSeleccionDieta();
            return;
        }
        
        if(e.getSource() == vista.getVRegCuarentena().getCuarentena()){
            vista.getVRegCuarentena().muestraSeleccionDieta();
            return;
        }
     
        if (e.getSource() == vista.getVRegCuarentena().getMostrar()) {
            vista.getVRegCuarentena().hazTablaReporte(CorralesTerneroModelo.traeVector("reportasensoresview",
                    "criid=" + vista.getVRegCuarentena().getCriId()));
            vista.getVRegCuarentena().getCuarentena().requestFocus();
            return;
        }

        if(e.getSource()==vista.getSimularSensores()){
            CorralesTerneroModelo.simularSensores();
            return;
        }
        
        if(e.getSource()==vista.getRegCuarentena()){
            vista.getVRegCuarentena().hazTablaCrias(
                    CorralesTerneroModelo.traeVector("CriasPorEnfermarseView","1=1"));
            vista.getVRegCuarentena().muestra();
            return;
        }
        
        if(e.getSource()==vista.getVSalida().getBtnSalida()){
           CorralesTerneroModelo.darSalida();
           modelo.imprime(vista.getVSalida().getTabla());
           vista.getVSalida().ocultar();
           return;
        }
        
        if(e.getSource()==vista.getItemSalida()){
            if(vista.getVSalida().getEstados().getSelectedObjects().length==0){
                vista.getVSalida().getEstados().removeItemListener(this);
                vista.getVSalida().llenaCombo();
                vista.getVSalida().getEstados().addItemListener(this);
            }
            vista.getVSalida().muestra();        
            vista.getVSalida().hazTabla();
            
            return;
        }
        
        if(e.getSource()==vista.getItemReportes()){/*
            if(vista.getVClasificar().getCorral().getSelectedObjects().length==0){
                vista.getVClasificar().getEstado().removeItemListener(this);
                vista.getVClasificar().llenaCombo();
                vista.getVClasificar().getEstado().addItemListener(this);
            }*/
            vista.getVClasificar().muestra();
            return;
        }
        
        if (e.getSource() == vista.getLogin().getBorrar()) {
            vista.getLogin().limpiaLogin();
            return;
        }
        if (e.getSource() == vista.getLogin().getEntrar()) {
            if (modelo.iniciarSesion(vista.getLogin().getUsuario().getText(),
                    vista.getLogin().getPass().getPassword())) {
                vista.getLogin().accionLogin(true);
                vista.entrar();
                return;
            }
            vista.getLogin().accionLogin(false);
            return;
        }
        //REVISAR ESTE CONTROLADOR
        if (e.getSource() == vista.getAgregaCria()) {
            vista.getDatosCria().muestra(modelo.consultaCriasHoy());
            return;
        }
        if (e.getSource() == vista.getDesconectar()) {
            CorralesTerneroBD.cierraConexion();
            vista.desconectar();
            return;
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == vista.getVSensores().getEstados()){
            if (vista.getVSensores().getEstados().getSelectedItem() == "Seleccione") {
                vista.getVSensores().getCiudades().removeItemListener(this);
                vista.getVSensores().limpiaCombos();
                vista.getVSensores().getCiudades().addItemListener(this);
                return;
            }
            vista.getVSensores().getCiudades().removeItemListener(this);
            vista.getVSensores().llenaCombo(vista.getVSensores().getCiudades());
            vista.getVSensores().getCiudades().addItemListener(this);
            return;
        }
        
        if(e.getSource() == vista.getVSensores().getCiudades()){
            vista.getVSensores().getGrabar().requestFocus();
        }
        
        if (e.getSource() == vista.getVCriasEnfermas().getEstados()) {

            if (vista.getVCriasEnfermas().getEstados().getSelectedItem() == "Seleccione") {
                vista.getVCriasEnfermas().limpiaCombos();
                return;
            }
            vista.getVCriasEnfermas().llenaCombo(vista.getVCriasEnfermas().getCiudades());
            return;
        }
        
        if(e.getSource()==vista.getVSalida().getEstados()){
            System.out.println("llego al escuchador 2");
            if(vista.getVSalida().getEstados().getSelectedItem() == "Seleccione"){
                return;
            }
            vista.getVSalida().llenaCiudades();
            vista.getVSalida().hazTabla();
            return;
        }
        if(e.getSource()==vista.getVSalida().getCiudades()){
            if(vista.getVSalida().getCiudades().getSelectedItem() == "Seleccione"){
                return;
            }
            vista.getVSalida().hazTabla();
            return;
        }
    }

    public void windowClosing(WindowEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if(e.getSource()==vista.getVCriasEnfermas().getTablaCrias()){
                vista.getVCriasEnfermas().llenaTablaSensores(vista.getVCriasEnfermas().getTablaCrias().getSelectedRow());
                return;
            }
            
            if(e.getSource()==vista.getVRegCuarentena().getMostrar()){
                vista.getVRegCuarentena().hazTablaReporte(CorralesTerneroModelo.traeVector("reportasensoresview",
                        "criid="+vista.getVRegCuarentena().getCriId()));
                vista.getVRegCuarentena().getCuarentena().requestFocus();
                return;
            }
            
            if(e.getSource()==vista.getVRegCuarentena().getCuarentena()){
            vista.getVRegCuarentena().muestraSeleccionDieta();
            return;
            }
            
            if (e.getSource() == vista.getLogin().getUsuario()) {
                vista.getLogin().getPass().requestFocus();
                return;
            }
            if (e.getSource() == vista.getLogin().getPass()) {
                if (modelo.iniciarSesion(vista.getLogin().getUsuario().getText(),
                        vista.getLogin().getPass().getPassword())) {
                    vista.getLogin().accionLogin(true);
                    vista.entrar();
                    return;
                }
                vista.getLogin().accionLogin(false);
                return;
            }
            if (e.getSource() == vista.getLogin().getEntrar()) {

                vista.getLogin().accionLogin(modelo.iniciarSesion(
                        vista.getLogin().getUsuario().getText(),
                        vista.getLogin().getPass().getPassword()));
                return;
            }
            if (e.getSource() == vista.getLogin().getBorrar()) {
                vista.getLogin().limpiaLogin();
                return;
            }
        }
        if (e.isControlDown()) { // cuando se presiona una combinacion de tecla ctrl
            e.consume();
            return;
        }
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
            if(e.getSource()==vista.getVRegCuarentena().getCrias()){
                vista.getVRegCuarentena().llenaCajas();
                vista.getVRegCuarentena().getMostrar().requestFocus();
                return;
            }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
