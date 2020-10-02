package corralesternero.ventanas;

/**
 * Esta clase se utilizara para capturar/consultar/borrar sensores
 *
 * @Autor: Jesus Adrian Diaz Orozco
 * @Fecha: 04/12/2019
 * @Materia: Taller de base de datos
 * @Maestro: Clemente Garcia Gerardo
 *
 */

import corralesternero.Rutinas;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Sensores extends JDialog {

    JPanel panCaptura, panConsulta;
    JLabel etiEstado, etiCiudad, etiSensor, sensor;
    JComboBox estados, ciudades;
    JButton grabar, borrar, imprimir;
    JTable tablaSensores;
    JScrollPane panTablaSensores;
    DefaultTableModel modelTablaSensores;

    public Sensores() {
        setTitle("Sensores");
        setSize(400, 300);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);

        panCaptura = new JPanel();
        panCaptura.setLayout(null);
        panConsulta = new JPanel();
        panConsulta.setLayout(null);
        etiEstado = new JLabel("Estado", SwingConstants.RIGHT);
        etiCiudad = new JLabel("Ciudad", SwingConstants.RIGHT);
        etiSensor = new JLabel("ID Sensor", SwingConstants.CENTER);
        sensor = new JLabel("***",SwingConstants.CENTER);
        estados = new JComboBox();
        ciudades = new JComboBox();
        grabar = new JButton("Grabar");
        borrar = new JButton("Borrar");
        imprimir = new JButton("Imprimir");
        tablaSensores = new JTable();
        panTablaSensores = new JScrollPane(tablaSensores);
        modelTablaSensores = new DefaultTableModel();
        JTabbedPane p = new JTabbedPane();

        modelTablaSensores.addColumn("ID Sensor");

        etiEstado.setBounds(20, 20, 50, 30);
        etiCiudad.setBounds(20, 70, 50, 30);
        estados.setBounds(80, 20, 100, 30);
        ciudades.setBounds(80, 70, 100, 30);
        grabar.setBounds(70, 150, 80, 30);
        panTablaSensores.setBounds(205, 20, 70, 210);
        etiSensor.setBounds(300, 20, 60, 30);
        sensor.setFont(new Font("Arial",Font.BOLD,16));
        sensor.setBounds(300, 50, 60, 30);
        borrar.setBounds(295, 90, 80, 30);
        imprimir.setBounds(295, 200, 85, 30);
        
        tablaSensores.setModel(modelTablaSensores);

        panCaptura.add(etiEstado);
        panCaptura.add(etiCiudad);
        panCaptura.add(estados);
        panCaptura.add(ciudades);
        panCaptura.add(grabar);
        panCaptura.add(borrar);
        panCaptura.add(panTablaSensores);
        panCaptura.add(etiSensor);
        panCaptura.add(sensor);
        panCaptura.add(imprimir);
        
        p.addTab("Capturar",null,panCaptura,"Registrar sensores nuevos");
        add(p);

        //setVisible(true);

    }
/*
    public static void main(String[] args) {
        new Sensores();
    }
    */
    
    public void agregaSensor(int senID){
        Vector<Integer>v = new Vector();
        v.add(senID);
        modelTablaSensores.addRow(v);
        sensor.setText(senID+"");
    }
    
    public void setSensor(String sensor) {
        this.sensor.setText(sensor);
    }

    public int getSensor() {
        return Integer.parseInt(sensor.getText());
    }

    public JComboBox getEstados() {
        return estados;
    }

    public JComboBox getCiudades() {
        return ciudades;
    }

    public JButton getGrabar() {
        return grabar;
    }

    public JButton getBorrar() {
        return borrar;
    }
    
    public JButton getImprimir(){
        return imprimir;
    }

    public JTable getTablaSensores() {
        return tablaSensores;
    }
    
    public void limpiaCombos(){
        ciudades.removeAllItems();
        ciudades.addItem("Seleccione");
    }

    public void quitaSensor() {

        int row = tablaSensores.getSelectedRow();
        modelTablaSensores.removeRow(row);

    }

    public void llenaCombo(JComboBox combo) {

        if (combo.getItemCount() > 0) {
            combo.removeAllItems();
        }
        if (combo == estados) {
            Rutinas.insertaCombo("EdoNombre", "Estados", combo);
        } else {
            Rutinas.insertaCombo("CiuNombre", "Ciudades", "EdoId=" + estados.getSelectedIndex(), combo);
        }

    }

    public void muestra() {
        llenaCombo(estados);
        setVisible(true);
    }

    public void ocultar() {
        setVisible(false);
    }

}
