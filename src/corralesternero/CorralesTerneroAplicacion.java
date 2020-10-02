/*En el cuerpo de esta clase se declararan el modelo, vista, controlador y 
la clase que utilizaremos para conectarnos a la base de datos
Alumno: Diaz Orozco Jesus Adrian
Maestro: Clemente Garcia Gerardo
Materia: Taller de base de datos*/

package corralesternero;
import InsertarCria.*;

public class CorralesTerneroAplicacion {

    public static void main(String[] args) throws InterruptedException {
        CorralesTerneroVista vista=new CorralesTerneroVista();
        CorralesTerneroModelo modelo=new CorralesTerneroModelo();
        CorralesTerneroControlador controlador=new CorralesTerneroControlador(vista,modelo);
        
        
        vista.muestra();
        vista.setControlador(controlador);
    }
    
}
