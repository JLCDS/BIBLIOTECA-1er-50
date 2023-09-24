package model;

public class ArbolAVL {
    private Nodo raiz;

    private class Nodo {
        Libro libro;
        Nodo izquierda;
        Nodo derecha;
        int altura;

        Nodo(Libro libro) {
            this.libro = libro;
            this.altura = 1;
        }
    }

    // Constructor del árbol AVL
    public ArbolAVL() {
        raiz = null;
    }

    // Método para obtener la altura de un nodo
    private int altura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    // Método para calcular el balance de un nodo
    private int balance(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.izquierda) - altura(nodo.derecha);
    }

    // Rotación simple a la derecha
    private Nodo rotacionDerecha(Nodo y) {
        Nodo x = y.izquierda;
        Nodo T2 = x.derecha;

        // Realizar la rotación
        x.derecha = y;
        y.izquierda = T2;

        // Actualizar alturas
        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }

    // Rotación simple a la izquierda
    private Nodo rotacionIzquierda(Nodo x) {
        Nodo y = x.derecha;
        Nodo T2 = y.izquierda;

        // Realizar la rotación
        y.izquierda = x;
        x.derecha = T2;

        // Actualizar alturas
        x.altura = Math.max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = Math.max(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }

    // Método para insertar un libro en el árbol AVL
    public Nodo insertar(Nodo nodo, Libro libro) {
        if (nodo == null) {
            return new Nodo(libro);
        }

        // Insertar el libro según titulo
        if (libro.getTitulo().compareTo(nodo.libro.getTitulo()) < 0) {
            nodo.izquierda = insertar(nodo.izquierda, libro);
        } else if (libro.getTitulo().compareTo(nodo.libro.getTitulo()) > 0) {
            nodo.derecha = insertar(nodo.derecha, libro);
        } else {
            return nodo;
        }

        // Actualizar la altura del nodo actual
        nodo.altura = 1 + Math.max(altura(nodo.izquierda), altura(nodo.derecha));

        // Calcular el balance del nodo
        int balance = balance(nodo);

        // Casos de desequilibrio
        // Rotación a la derecha
        if (balance > 1 && libro.getTitulo().compareTo(nodo.izquierda.libro.getTitulo()) < 0) {
            return rotacionDerecha(nodo);
        }
        // Rotación a la izquierda
        if (balance < -1 && libro.getTitulo().compareTo(nodo.derecha.libro.getTitulo()) > 0) {
            return rotacionIzquierda(nodo);
        }
        // Rotación doble a la derecha
        if (balance > 1 && libro.getTitulo().compareTo(nodo.izquierda.libro.getTitulo()) > 0) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }
        // Rotación doble a la izquierda
        if (balance < -1 && libro.getTitulo().compareTo(nodo.derecha.libro.getTitulo()) < 0) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Método público para insertar un libro en el árbol AVL
    public void insertar(Libro libro) {
        raiz = insertar(raiz, libro);
    }

    // Método para eliminar un libro por su código ISBN
    public boolean eliminar(String titulo) {
        raiz = eliminar(raiz, titulo);
        return false;
    }

    private Nodo eliminar(Nodo nodo, String titulo) {
        if (nodo == null) {
            return nodo;
        }

        if (titulo.compareTo(String.valueOf(nodo.libro.getTitulo())) < 0) {
            nodo.izquierda = eliminar(nodo.izquierda, titulo);
        } else if (titulo.compareTo(String.valueOf(nodo.libro.getTitulo())) > 0) {
            nodo.derecha = eliminar(nodo.derecha, titulo);
        } else {
            // Este es el nodo que debe ser eliminado
            if (nodo.izquierda == null || nodo.derecha == null) {
                Nodo temp = nodo.izquierda != null ? nodo.izquierda : nodo.derecha;

                if (temp == null) {
                    temp = nodo;
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                // Nodo con dos hijos: obtener el sucesor inorden (el nodo más pequeño en el subárbol derecho)
                Nodo temp = obtenerMinimo(nodo.derecha);

                // Copiar los datos del sucesor inorden al nodo actual
                nodo.libro = temp.libro;

                // Eliminar el sucesor inorden
                nodo.derecha = eliminar(nodo.derecha, String.valueOf(temp.libro.getTitulo()));
            }
        }

        if (nodo == null) {
            return nodo;
        }

        // Actualizar la altura del nodo actual
        nodo.altura = 1 + Math.max(altura(nodo.izquierda), altura(nodo.derecha));

        // Calcular el balance del nodo
        int balance = balance(nodo);

        // Casos de desequilibrio
        // Rotación a la derecha
        if (balance > 1 && balance(nodo.izquierda) >= 0) {
            return rotacionDerecha(nodo);
        }
        // Rotación a la izquierda
        if (balance < -1 && balance(nodo.derecha) <= 0) {
            return rotacionIzquierda(nodo);
        }
        // Rotación doble a la derecha
        if (balance > 1 && balance(nodo.izquierda) < 0) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }
        // Rotación doble a la izquierda
        if (balance < -1 && balance(nodo.derecha) > 0) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Método para buscar un libro por su titulo
    public Libro buscar(String titulo) {
        return buscar(raiz, titulo);
    }

    private Libro buscar(Nodo nodo, String titulo) {
        if (nodo == null) {
            return null;
        }

        if (titulo.equals(nodo.libro.getTitulo())) {
            return nodo.libro;
        } else if (titulo.compareTo(String.valueOf(nodo.libro.getCodigoISBN())) < 0) {
            return buscar(nodo.izquierda,titulo);
        } else {
            return buscar(nodo.derecha, titulo);
        }
    }

    // Método para obtener el libro con el código ISBN mínimo en el árbol
    private Nodo obtenerMinimo(Nodo nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }
    // Método para realizar un recorrido inorden del árbol y mostrar los libros en orden
    public void recorridoInorden() {
        recorridoInorden(raiz);
    }

    private void recorridoInorden(Nodo nodo) {
        if (nodo != null) {
            recorridoInorden(nodo.izquierda);
            System.out.println("Título: " + nodo.libro.getTitulo() +"\n"
                    + "Codigo ISBN: " + nodo.libro.getCodigoISBN() +"\n"
                    + "Volumen: " + nodo.libro.getVolumen() +"\n"
                    + "Editorial: " + nodo.libro.getEditorial() +"\n"
                    + nodo.libro.getSede()
                    + nodo.libro.getCampus()
                    + nodo.libro.getAutor());
            recorridoInorden(nodo.derecha);
        }
    }

    // Método para realizar un recorrido preorden del árbol y mostrar los libros
    public void recorridoPreorden() {
        recorridoPreorden(raiz);
    }

    private void recorridoPreorden(Nodo nodo) {
        if (nodo != null) {
            System.out.println("Título: " + nodo.libro.getTitulo() +"\n"
                    + "Codigo ISBN: " + nodo.libro.getCodigoISBN() +"\n"
                    + "Volumen: " + nodo.libro.getVolumen() +"\n"
                    + "Editorial: " + nodo.libro.getEditorial() +"\n"
                    + nodo.libro.getSede()
                    + nodo.libro.getCampus()
                    + nodo.libro.getAutor());
            recorridoPreorden(nodo.izquierda);
            recorridoPreorden(nodo.derecha);
        }
    }

    // Método para realizar un recorrido postorden del árbol y mostrar los libros
    public void recorridoPostorden() {
        recorridoPostorden(raiz);
    }

    private void recorridoPostorden(Nodo nodo) {
        if (nodo != null) {
            recorridoPostorden(nodo.izquierda);
            recorridoPostorden(nodo.derecha);
            System.out.println("Título: " + nodo.libro.getTitulo() +"\n"
                    + "Codigo ISBN: " + nodo.libro.getCodigoISBN() +"\n"
                    + "Volumen: " + nodo.libro.getVolumen() +"\n"
                    + "Editorial: " + nodo.libro.getEditorial() +"\n"
                    + nodo.libro.getSede()
                    + nodo.libro.getCampus()
                    + nodo.libro.getAutor());
        }
    }

    // Método para contar el número total de libros en el árbol
    public int contarLibros() {
        return contarLibros(raiz);
    }

    private int contarLibros(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarLibros(nodo.izquierda) + contarLibros(nodo.derecha);
    }
}