package interfaz;

import model.Autor;
import model.Campus;
import model.Libro;
import model.Sede;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LibreriaApp {
    private JFrame frame;
    private JTextField tituloField;
    private JTextField isbnField;
    private JTextField volumenField;
    private JTextField editorialField;
    private JComboBox<String> sedeComboBox;
    private JComboBox<String> campusComboBox;
    private JTextField nombreAutorField;
    private JTextField apellidoAutorField;
    private JTextArea biografiaAutorArea;
    private JTextArea resultadoArea;
    private List<Libro> libros;

    public LibreriaApp() {
        libros = new ArrayList<>();

        frame = new JFrame("Librería App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLayout(new BorderLayout());

        // Panel para ingresar datos del libro
        JPanel datosPanel = new JPanel(new GridLayout(8, 2));
        datosPanel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        datosPanel.add(tituloField);
        datosPanel.add(new JLabel("Código ISBN (13 dígitos):"));
        isbnField = new JTextField();
        datosPanel.add(isbnField);
        datosPanel.add(new JLabel("Volumen:"));
        volumenField = new JTextField();
        datosPanel.add(volumenField);
        datosPanel.add(new JLabel("Editorial:"));
        editorialField = new JTextField();
        datosPanel.add(editorialField);
        datosPanel.add(new JLabel("Sede:"));
        sedeComboBox = new JComboBox<>(new String[]{"Tunja", "Duitama", "Sogamoso", "Chiquinquira"});
        datosPanel.add(sedeComboBox);
        datosPanel.add(new JLabel("Campus:"));
        campusComboBox = new JComboBox<>(new String[]{"Campus Central Tunja", "Facultad de Medicina", "Edificio Central - FESAD", "Campus Central Duitama", "Campus Central Sogamoso", "Campus Central Chiquinquira"});
        datosPanel.add(campusComboBox);
        datosPanel.add(new JLabel("Nombre del Autor:"));
        nombreAutorField = new JTextField();
        datosPanel.add(nombreAutorField);
        datosPanel.add(new JLabel("Apellido del Autor:"));
        apellidoAutorField = new JTextField();
        datosPanel.add(apellidoAutorField);

        // Panel para ingresar biografía del autor
        JPanel biografiaPanel = new JPanel(new BorderLayout());
        biografiaPanel.add(new JLabel("Biografía del Autor:"), BorderLayout.NORTH);
        biografiaAutorArea = new JTextArea();
        JScrollPane biografiaScrollPane = new JScrollPane(biografiaAutorArea);
        biografiaPanel.add(biografiaScrollPane, BorderLayout.CENTER);

        // Botones para agregar, buscar y eliminar libros
        JPanel botonesPanel = new JPanel();
        JButton agregarButton = new JButton("Agregar Libro");
        JButton buscarButton = new JButton("Buscar Libro");
        JButton eliminarButton = new JButton("Eliminar Libro");
        botonesPanel.add(agregarButton);
        botonesPanel.add(buscarButton);
        botonesPanel.add(eliminarButton);

        // Panel para mostrar resultados
        JPanel resultadosPanel = new JPanel(new BorderLayout());
        resultadosPanel.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane resultadoScrollPane = new JScrollPane(resultadoArea);
        resultadosPanel.add(resultadoScrollPane, BorderLayout.CENTER);

        // Agregar componentes al frame
        frame.add(datosPanel, BorderLayout.NORTH);
        frame.add(biografiaPanel, BorderLayout.CENTER);
        frame.add(botonesPanel, BorderLayout.SOUTH);
        frame.add(resultadosPanel, BorderLayout.EAST);

        // Manejo de eventos
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarLibro();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });

        frame.setVisible(true);
    }

    private void agregarLibro() {
        // Obtener datos ingresados por el usuario y crear una instancia de Libro
        String titulo = tituloField.getText();
        String isbn = isbnField.getText();
        String volumen = volumenField.getText();
        String editorial = editorialField.getText();
        String sede = (String) sedeComboBox.getSelectedItem();
        String campus = (String) campusComboBox.getSelectedItem();
        String nombreAutor = nombreAutorField.getText();
        String apellidoAutor = apellidoAutorField.getText();
        String biografiaAutor = biografiaAutorArea.getText();

        // Validar y crear objetos Libro, Sede, Campus y Autor
        try {
            // Validaciones y creación de objetos

            // Crear instancia de Libro y agregarla a la lista de libros
            Libro nuevoLibro = new Libro(titulo, isbn, Integer.parseInt(volumen), editorial, new Sede(sede), new Campus(campus), new Autor(nombreAutor, apellidoAutor, biografiaAutor));
            libros.add(nuevoLibro);

            // Actualizar el área de visualización
            resultadoArea.setText("Libro agregado:\n" + nuevoLibro.toString());
        } catch (Exception ex) {
            // Manejar errores de validación o creación de objetos
            resultadoArea.setText("Error al agregar el libro:\n" + ex.getMessage());
        }
    }

    private void buscarLibro() {
        // Implementar lógica de búsqueda y actualizar el área de visualización con los resultados
    }

    private void eliminarLibro() {
        // Implementar lógica de eliminación y actualizar el área de visualización con los resultados
    }
}