/*En esta clase se representara la parte grafica del programa
Alumno: Diaz Orozco Jesus Adrian
Maestro: Clemente Garcia Gerardo
Materia: Taller de base de datos*/
package corralesternero;

import Reportes.*;
import InsertarCria.*;
import Reportes.ReporteModelo;
import corralesternero.ventanas.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class CorralesTerneroVista extends JFrame {

    private JMenuBar barra;
    private JMenu opciones;
    private JMenuItem agregaCria, itmReportes,itemSalida, desconectar,
            regCuarentena,itemEnfermas,itemSensores;
    private DatosCria datosCria;
    private Reporte vReporte;
    private Salida vSalida;
    private RegistrarCuarentena vRegCuarentena;
    private Login login;
    private CriasEnfermas vCriasEnfermas;
    private JButton simularSensores;
    private Sensores vSensores;
    
    CorralesTerneroVista() {
        super("Corrales Ternero");
        login=new Login();
        hazInterfazPrincipal();
        
        datosCria = new DatosCria();
        DatosCriaModelo DatosCriaModelo = new DatosCriaModelo();
        DatosCriaControlador DatosCriaControlador = new DatosCriaControlador(datosCria, DatosCriaModelo);
        datosCria.setControlador(DatosCriaControlador);
        
        vReporte = new Reporte();
        ReporteModelo reporteModelo = new ReporteModelo();
        ReporteControlador reporteControlador = new ReporteControlador(vReporte, reporteModelo);
        vReporte.setControlador(reporteControlador);
        
        vSalida=new Salida();
        vRegCuarentena=new RegistrarCuarentena();
        vCriasEnfermas=new CriasEnfermas();
        vSensores=new Sensores();
    }

    public void hazInterfazPrincipal() {
        setIconImage(new ImageIcon("IconoCorrales.JPG").getImage());
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        barra = new JMenuBar();
        opciones = new JMenu("Opciones");
        agregaCria = new JMenuItem("Agregar Nueva Cria");
        desconectar= new JMenuItem("Desconectar");
        itmReportes=new JMenuItem("Reportes");
        itemSalida=new JMenuItem("Gestionar salidas");
        regCuarentena=new JMenuItem("Registrar Cuarentena");
        itemEnfermas = new JMenuItem("Crias Enfermas");
        itemSensores = new JMenuItem("Sensores");
        
        
        itemEnfermas.setToolTipText("Muestra la cuarentena actual");
        regCuarentena.setToolTipText("Muestra crias que puede que enfermen(Cobertura 2)");
        itemSalida.setToolTipText("Muestra crias listas para el siguiente proceso");
        desconectar.setToolTipText("Ingresar con otro usuario/contraseña");
        agregaCria.setToolTipText("Registrar crias nuevas");
        itmReportes.setToolTipText("Crias que permanecerion mas de 5 meses por");
        itemSensores.setToolTipText("Capturar Sensores");
        
        simularSensores=new JButton("Simular Sensores");
        simularSensores.setBounds(350, 10, 140, 35);
        
        opciones.add(agregaCria);
        opciones.add(itmReportes);
        opciones.add(itemSalida);
        opciones.add(regCuarentena);
        opciones.add(itemEnfermas);
        opciones.add(itemSensores);
        opciones.add(desconectar);
        barra.add(opciones);
        setJMenuBar(barra);
        add(simularSensores);
    }
    
    
    public JMenuItem getItemEnfermas(){
        return itemEnfermas;
    }
    
    public CriasEnfermas getVCriasEnfermas(){
        return vCriasEnfermas;
    }
    
    public JMenuItem getRegCuarentena(){
        return regCuarentena;
    }
    
    public JMenuItem getAgregaCria(){
        return agregaCria;
    }
    public JMenuItem getDesconectar(){
        return desconectar;
    }
    public DatosCria getDatosCria(){
        return datosCria;
    }
    public Login getLogin(){
        return login;
    }
    public JMenuItem getItemReportes(){
        return itmReportes;
    }
    public Reporte getVClasificar(){
        return vReporte;
    }
    public Salida getVSalida(){
        return vSalida;
    }
    public JMenuItem getItemSalida(){
        return itemSalida;
    }
    public RegistrarCuarentena getVRegCuarentena(){
        return vRegCuarentena;
    }
    
    public Sensores getVSensores(){
        return vSensores;
    }
    
    public JMenuItem getItemSensores(){
        return itemSensores;
    }
    
    public JButton getSimularSensores(){
        return simularSensores;
    }

    public void desconectar(){
        setVisible(false);
        login.setVisible(true);
    }
    
    public void setControlador(CorralesTerneroControlador C) {
        
        agregaCria.addActionListener(C);
        desconectar.addActionListener(C);
        itmReportes.addActionListener(C);
        
        simularSensores.addActionListener(C);
        vSensores.getEstados().addItemListener(C);
        vSensores.getCiudades().addItemListener(C);
        vSensores.getGrabar().addActionListener(C);
        vSensores.getTablaSensores().addMouseListener(C);
        vSensores.getBorrar().addActionListener(C);
        vSensores.getImprimir().addActionListener(C);
        itemSensores.addActionListener(C);
        
        vCriasEnfermas.getCiudades().addItemListener(C);
        vCriasEnfermas.getEstados().addItemListener(C);
        vCriasEnfermas.getTablaCrias().addKeyListener(C);
        vCriasEnfermas.getConsultar().addActionListener(C);
        vCriasEnfermas.getLimpiar().addActionListener(C);
        vCriasEnfermas.getSacrificar().addActionListener(C);
        vCriasEnfermas.getLiberar().addActionListener(C);
        vCriasEnfermas.getGuardar().addActionListener(C);
        itemEnfermas.addActionListener(C);
        
        
        login.getUsuario().addKeyListener(C);
        login.getPass().addKeyListener(C);
        login.getBorrar().addActionListener(C);
        login.getEntrar().addActionListener(C);
        login.getEntrar().addKeyListener(C);
        login.getBorrar().addKeyListener(C);
        
        vSalida.getBtnSalida().addActionListener(C);
        vSalida.getEstados().addItemListener(C);
        vSalida.getCiudades().addItemListener(C);
        itemSalida.addActionListener(C);
        
        vRegCuarentena.getCrias().addMouseListener(C);
        vRegCuarentena.getMostrar().addActionListener(C);
        vRegCuarentena.getMostrar().addKeyListener(C);
        vRegCuarentena.getCuarentena().addActionListener(C);
        vRegCuarentena.getCuarentena().addKeyListener(C);
        vRegCuarentena.getEnviarSeleccionDieta().addActionListener(C);
        regCuarentena.addActionListener(C);
    }
    
    public void entrar(){
        setVisible(true);
    }
    

    public void muestra() {
        login.setVisible(true);
    }
}
