import interfaz.LibreriaApp;
import model.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class SistemaDeLibros {
    public static  void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibreriaApp();
                ArbolAVL arbolLibros = new ArbolAVL();
                arbolLibros.recorridoInorden();
            }
        });
            // Crear instancias de libros, autores, sedes y campus





    }



}
