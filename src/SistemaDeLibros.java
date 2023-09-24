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
            }
        });


            // Crear instancias de libros, autores, sedes y campus
        Autor autor1 = new Autor("NombreAutor1", "ApellidoAutor1", "Biografía del autor 1");
        List<Sede> listaSedes = new ArrayList<>();
        List<Campus> listaCampus = new ArrayList<>();

        // Agregar sede a la lista
        Sede sedeTunja = new Sede("Tunja");
        listaSedes.add(sedeTunja);
        Sede sedeDuitama = new Sede("Duitama");
        listaSedes.add(sedeDuitama);
        Sede sedeSogamoso = new Sede("Sogamoso");
        listaSedes.add(sedeSogamoso);
        Sede sedeChiquinquira = new Sede("Chiquinquira");
        listaSedes.add(sedeChiquinquira);

        //agregar campus a la lista
        Campus campusCTunja = new Campus("Campus central Tunja");
        listaCampus.add(campusCTunja);
        Campus campusMedicina = new Campus("Facultad de Medicina");
        listaCampus.add(campusMedicina);
        Campus campusFESAD = new Campus("Edificio Central - FESAD");
        listaCampus.add(campusFESAD);
        Campus campusCDuitama = new Campus("Campus central Duitama");
        listaCampus.add(campusCDuitama);
        Campus camspusCSogamos = new Campus("Campus central Sogamoso");
        listaCampus.add(camspusCSogamos);
        Campus campusCChiquinquira = new Campus("Campus centra Chiquinquira");
        listaCampus.add(campusCChiquinquira);


            ArbolAVL arbolLibros = new ArbolAVL();

        Libro libro1 = new Libro("Título1", "5463215795458", 5, "Editorial1", sedeTunja, campusCTunja, autor1);
        Libro libro2 = new Libro("Título2", "5469874563210", 2, "Editorial2", sedeDuitama,campusCDuitama, autor1);
        Libro libro3 = new Libro("Título3", "1236549852314", 3, "Editorial3", sedeSogamoso, camspusCSogamos, autor1);


// Insertar los libros en el árbol AVL
        arbolLibros.insertar(libro1);
        arbolLibros.insertar(libro2);
        arbolLibros.insertar(libro3);
        arbolLibros.recorridoInorden();

    }



}
