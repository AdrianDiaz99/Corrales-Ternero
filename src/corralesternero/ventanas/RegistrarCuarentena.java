package corralesternero.ventanas;

/*
Esta clase se utilizara para mostrar al usuario las crias grasa cobertura 2
en las cuales se detecte esten apunto de enfermarse para mandarlas a cuarentena
Autor: Adrian Diaz
 */
import corralesternero.CorralesTerneroBD;
import corralesternero.Rutinas;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class RegistrarCuarentena extends JDialog {

    private JTable crias, reporteSensores;
    private DefaultTableModel mReporteSensores,mCrias;
    private JTextField criId, corral, ciudad;
    private JButton mostrar, cuarentena;
    private JLabel etiCriId, etiCorral, etiCiudad;
    private Font fEtis;
    private JScrollPane panCrias, panReporteSensores;
    private SeleccionDieta seleccionDieta;
    
    class SeleccionDieta extends JDialog{
    
        public JButton enviar;
        public JLabel etiComentario, etiMedicamento;
        public JTextField comentario;
        public JComboBox medicamento;
    
        public SeleccionDieta(){
            setTitle("Seleccionar Dieta");
            setSize(340,300);
            setResizable(false);
            setLocationRelativeTo(null);
            setLayout(null);
            setModal(true);
        
            etiComentario=new JLabel("Comentario",SwingConstants.RIGHT);
            etiMedicamento=new JLabel("Medicamento",SwingConstants.RIGHT);
            comentario=new JTextField();
            medicamento=new JComboBox();
            enviar=new JButton("Enviar");
        
            etiComentario.setBounds(30, 100, 77, 30);
            etiMedicamento.setBounds(30, 50, 77, 30);
            medicamento.setBounds(120, 50, 150, 30);
            comentario.setBounds(120, 100, 200, 30);
            enviar.setBounds(100,200,80,30);
        
        
            add(etiComentario);
            add(etiMedicamento);
            add(medicamento);
            add(comentario);
            add(enviar);
        }
    }


    public RegistrarCuarentena() {

        hazInterfaz();

    }
    
    public void llenaCajas(){
        criId.setText(crias.getValueAt(crias.getSelectedRow(), 0)+"");
        corral.setText(crias.getValueAt(crias.getSelectedRow(), 1)+"");
        ciudad.setText(crias.getValueAt(crias.getSelectedRow(), 2)+"");
    }

    
    public String getCriId(){
        return criId.getText();
    }
    
    public void vaciaTabla(DefaultTableModel t){
        for(int i=0;i<t.getRowCount();i++){
            t.removeRow(i);
        }
    }
    
    public JButton getEnviarSeleccionDieta(){
        return seleccionDieta.enviar;
    }
    
    public void muestraSeleccionDieta(){
        if(seleccionDieta.medicamento.getItemCount() == 0){
            Rutinas.insertaCombo("MedNombre", "Medicamentos", seleccionDieta.medicamento);
        }
        seleccionDieta.setVisible(true);
    }
    
    public void ocultarSeleccionDieta(){
        seleccionDieta.medicamento.setSelectedIndex(0);
        seleccionDieta.comentario.setText("");
        seleccionDieta.setVisible(false);
    }
    
    public String getComentario(){
        return "'"+seleccionDieta.comentario.getText()+"'";
    }
    
    public String getSelectedMed(){
        return seleccionDieta.medicamento.getSelectedIndex()+"";
    }
    
    public void hazTablaCrias(Vector<Vector>v) {
        
        mCrias=new DefaultTableModel();
        mCrias.addColumn("ID Cria");
        mCrias.addColumn("Corral");
        mCrias.addColumn("Ciudad");
        try{
            for (int i = 0; i < v.size(); i++) {
                mCrias.addRow(v.get(i));
            }
        } catch(NullPointerException e){
        }
        crias.setModel(mCrias);
    }
    
    public void eliminaCria(Object row){
        //Fila convertida a int
        int rowInt = Integer.parseInt(row.toString());
        //Obtenemos El ID 
        int idCria=Integer.parseInt(mCrias.getValueAt(rowInt, 0).toString());
        mCrias.removeRow(Integer.parseInt(row.toString()));
        JOptionPane.showMessageDialog(null, "Se mando a cuarentena a la cria con ID ("+idCria+")", 
                "AVISO", JOptionPane.INFORMATION_MESSAGE);
    }

    public void hazTablaReporte(Vector<Vector>v) {
        
        
        mReporteSensores=new DefaultTableModel();
        mReporteSensores.addColumn("ID Cria");
        mReporteSensores.addColumn("Fecha");
        mReporteSensores.addColumn("F. Cardiaca");
        mReporteSensores.addColumn("F. Respiratoria");
        try {
            for (int i = 0; i < v.size(); i++) {
                mReporteSensores.addRow(v.get(i));
            }
        } catch (NullPointerException e) {
        }
        reporteSensores.setModel(mReporteSensores);
    }
    
    public void hazInterfaz() {

        setTitle("Registro de cuarentenas");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(900, 670);
        setLayout(null);
        setModal(true);
        setLocationRelativeTo(null);

        crias = new JTable();
        panCrias = new JScrollPane(crias);
        reporteSensores = new JTable();
        panReporteSensores = new JScrollPane(reporteSensores);
        criId = new JTextField(); 
        corral = new JTextField(); 
        ciudad = new JTextField(); 
        criId.setEditable(false); 
        corral.setEditable(false); 
        ciudad.setEditable(false); 
        criId.setFont(new Font("Arial",Font.PLAIN,16));
        corral.setFont(new Font("Arial",Font.PLAIN,16));
        ciudad.setFont(new Font("Arial",Font.PLAIN,16));
        seleccionDieta=new SeleccionDieta();
                
        mostrar = new JButton("Mostrar");
        cuarentena = new JButton("CUARENTENA");
        fEtis = new Font("Arial", Font.BOLD, 20);
        etiCriId = new JLabel("ID Cria", SwingConstants.RIGHT);
        etiCorral = new JLabel("Corral", SwingConstants.RIGHT);
        etiCiudad = new JLabel("Ciudad", SwingConstants.RIGHT);
        mCrias=new DefaultTableModel();
        mReporteSensores=new DefaultTableModel();
        

        etiCriId.setFont(fEtis);
        etiCorral.setFont(fEtis);
        etiCiudad.setFont(fEtis);

        etiCriId.setBounds(110, 30, 80, 20);
        etiCorral.setBounds(110, 100, 80, 20);
        etiCiudad.setBounds(110, 170, 80, 20);
        criId.setBounds(200, 20, 100, 35);
        corral.setBounds(200, 90, 100, 35);
        ciudad.setBounds(200, 160, 100, 35);
        mostrar.setBounds(165, 230, 100, 35);
        panCrias.setBounds(20, 300, 400, 300);
        panReporteSensores.setBounds(470, 30, 400, 500);
        cuarentena.setBounds(620, 560, 120, 35);

        add(etiCriId);
        add(etiCorral);
        add(etiCiudad);
        add(criId);
        add(ciudad);
        add(corral);
        add(mostrar);
        add(panCrias);
        add(panReporteSensores);
        add(cuarentena);
        
        
        mCrias.addColumn("ID Cria");
        mCrias.addColumn("Corral");
        mCrias.addColumn("Ciudad");
        
        mReporteSensores.addColumn("ID Cria");
        mReporteSensores.addColumn("Fecha");
        mReporteSensores.addColumn("F. Cardiaca");
        mReporteSensores.addColumn("F. Respiratoria");
        
        crias.setModel(mCrias);
        reporteSensores.setModel(mReporteSensores);

    }

    public JTable getCrias() {
        return crias;
    }

    public JButton getMostrar() {
        return mostrar;
    }

    public JButton getCuarentena() {
        return cuarentena;
    }

    public void muestra() {
        setVisible(true);
    }

}
