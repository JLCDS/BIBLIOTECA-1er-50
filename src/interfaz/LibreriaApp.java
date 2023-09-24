package interfaz;
import model.Autor;
import model.Campus;
import model.Libro;
import model.Sede;
import model.ArbolAVL;
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
    CustomDialog dialog;
    ArbolAVL arbolAVL = new ArbolAVL();

    public LibreriaApp() {
        libros = new ArrayList<>();

        frame = new JFrame("Librería App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 600);
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
                abrirVentanaBusqueda();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
                abrirVentanaEliminacion();
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
            // Verificar que los campos no estén vacíos
            if (titulo.isEmpty() || editorial.isEmpty() || nombreAutor.isEmpty() || apellidoAutor.isEmpty() || biografiaAutor.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }
            // Realizar la validación del código ISBN como una cadena de 13 dígitos
            if (!isbn.matches("\\d{13}")) {
                throw new IllegalArgumentException("El código ISBN debe contener exactamente 13 dígitos numericos.");
            }

            // Crear objetos Libro, Sede, Campus y Autor
            Sede sedeObj = new Sede(sede);
            Campus campusObj = new Campus(campus);
            Autor autor = new Autor(nombreAutor, apellidoAutor, biografiaAutor);
            Libro nuevoLibro = new Libro(titulo, isbn, Integer.parseInt(volumen), editorial, sedeObj, campusObj, autor);

            // Agregar el libro a la lista de libros
            arbolAVL.insertar(nuevoLibro);

            // Limpiar campos de entrada
           limpiarCampos();

            // Actualizar el área de visualización
            resultadoArea.setText("Libro agregado:\n" + nuevoLibro.toString());
            dialog = new CustomDialog("Libro agregado", "Se agregó el libro correctamente:\n" + nuevoLibro.toString());
            dialog.showDialog();
        } catch (NumberFormatException ex) {
            resultadoArea.setText("El volumen debe ser un número válido.");
            dialog = new CustomDialog("Error", "Se ha producido un error al digitar la información:\nVolumen debe ser un número entero");
            dialog.showDialog();
        } catch (IllegalArgumentException ex) {
            resultadoArea.setText("Error: " + ex.getMessage());
            dialog = new CustomDialog("Error: ", "Se ha producido un error al digitar la informacion: \n" + ex.getMessage());
            dialog.showDialog();
        }
    }

    private void buscarLibro() {
        arbolAVL.buscar(String.valueOf(tituloField));

    }

    private void eliminarLibro() {
      arbolAVL.eliminar(String.valueOf(tituloField));

    }
    // implementacion del metodo para limpiar los campos despues de agregar un libro
    private void limpiarCampos() {
        tituloField.setText("");
        isbnField.setText("");
        volumenField.setText("");
        editorialField.setText("");
        nombreAutorField.setText("");
        apellidoAutorField.setText("");
        biografiaAutorArea.setText("");
        sedeComboBox.setSelectedIndex(0); // Establecer el primer elemento como seleccionado en el JComboBox de sedes
        campusComboBox.setSelectedIndex(0); // Establecer el primer elemento como seleccionado en el JComboBox de campus
    }
    private void abrirVentanaBusqueda() {
        JFrame ventanaBusqueda = new JFrame("Búsqueda de Libro");
        ventanaBusqueda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaBusqueda.setSize(800, 700);
        ventanaBusqueda.setLayout(new FlowLayout());

        JTextField buscarTituloField = new JTextField(20);
        JButton buscarButton = new JButton("Buscar");

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tituloBusqueda = buscarTituloField.getText();
                Libro libroEncontrado = arbolAVL.buscar(tituloBusqueda);

                if (libroEncontrado != null) {
                    // Crear una instancia de la ventana personalizada y mostrarla
                    CustomDialog dialog = new CustomDialog("Libro encontrado", libroEncontrado.toString());
                    dialog.showDialog();
                } else {
                    // Crear una instancia de la ventana personalizada para mostrar un mensaje de "Libro no encontrado"
                    CustomDialog dialog = new CustomDialog("Libro no encontrado", "El libro no se encontró en la base de datos.");
                    dialog.showDialog();
                }
            }
        });

        ventanaBusqueda.add(new JLabel("Título del Libro:"));
        ventanaBusqueda.add(buscarTituloField);
        ventanaBusqueda.add(buscarButton);

        ventanaBusqueda.setVisible(true);
    }

    private void abrirVentanaEliminacion() {
        JFrame ventanaEliminar= new JFrame("Eliminar libro");
        ventanaEliminar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEliminar.setSize(800, 700);
        ventanaEliminar.setLayout(new FlowLayout());

        JTextField eliminarTituloField = new JTextField(20);
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tituloEliminar = eliminarTituloField.getText();
                boolean libroEncontrado = arbolAVL.eliminar(tituloEliminar);



                if (tituloEliminar != null) {
                    // Crear una instancia de la ventana personalizada y mostrarla
                    CustomDialog dialog = new CustomDialog("Libro encontrado y eliminado: ", tituloEliminar);
                    dialog.showDialog();
                } else {
                    // Crear una instancia de la ventana personalizada para mostrar un mensaje de "Libro no encontrado"
                    CustomDialog dialog = new CustomDialog("Libro no encontrado", "El libro no se encontró en la base de datos.");
                    dialog.showDialog();
                }
            }
        });
        ventanaEliminar.add(new JLabel("Título del Libro:"));
        ventanaEliminar.add(eliminarTituloField);
        ventanaEliminar.add(eliminarButton);

        ventanaEliminar.setVisible(true);
            }

        }
