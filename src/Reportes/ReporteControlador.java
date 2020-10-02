/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import corralesternero.Rutinas;
import java.awt.event.*
;
/**
 *
 * @author adria
 */
public class ReporteControlador implements ActionListener, ItemListener{
    
    Reporte vista;
    ReporteModelo modelo;
    
    public ReporteControlador(Reporte vista, ReporteModelo modelo){
        this.vista = vista;
        this.modelo = modelo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==vista.getImprimir()){
            modelo.imprime(vista.getCrias());
            return;
        }

        if(e.getSource()==vista.getCorral()){
            if(vista.getCorral().getSelectedItem() == "Seleccione"){
                return;
            }
            vista.llenaTabla();
            return;
        }
        
        if(e.getSource()==vista.getCiudad()){
            if(vista.getCiudad().getSelectedItem() == "Seleccione"){
                return;
            }
            vista.llenaTabla();
            return;
        }
        if(e.getSource()==vista.getGrasa()){
            if(vista.getGrasa().getSelectedItem() == "Seleccione"){
                return;
            }
            vista.llenaTabla();
            return;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==vista.getCorral()){
            System.out.println("getCorral");
            if(vista.getCorral().getSelectedItem() == "Seleccione"){
                return;
            }
            vista.llenaTabla();
            return;
        }
        if(e.getSource()==vista.getEstado()){
            
            if (vista.getEstado().getSelectedItem() == "Seleccione") {
                Rutinas.limpiaCombo(vista.getCiudad());
                return;
            }
            vista.setCiudades(vista.getEstado().getSelectedIndex());
            //vista.llenaTabla();
            return;
        }
        if(e.getSource()==vista.getCiudad()){
            System.out.println("getCiudad");
            if(vista.getCiudad().getSelectedItem() == "Seleccione"){
                return;
            }
            vista.llenaTabla();
            return;
        }
        if(e.getSource()==vista.getGrasa()){
            System.out.println("getGrasa");
            if(vista.getGrasa().getSelectedItem() == "Seleccione"){
                return;
            }
            vista.llenaTabla();
            return;
        }
    }

}
