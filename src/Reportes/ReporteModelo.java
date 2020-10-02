
package Reportes;

import javax.swing.*;

public class ReporteModelo {
    
    public void imprime(JTable t) {
        try {
            t.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
