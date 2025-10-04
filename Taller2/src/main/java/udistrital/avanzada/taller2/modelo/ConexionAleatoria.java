package udistrital.avanzada.taller2.modelo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 *   Estructura de cada registro (longitudes fijas):
 *   Clave: entero (4 bytes)
 *   Nombre del equipo: 30 caracteres (60 bytes, cada char = 2 bytes)
 *   Nombre de 4 jugadores: 20 caracteres cada uno (4 * 40 = 160 bytes)
 *   Resultado: 10 caracteres (20 bytes)
 * 
 * @author sebas
 * @since 03/10/2025
 */
public class ConexionAleatoria {
    private RandomAccessFile archivo;

    /** Tamaños fijos en caracteres (cada char ocupa 2 bytes) */
    private static final int TAM_NOMBRE_EQUIPO = 30;
    private static final int TAM_NOMBRE_JUGADOR = 20;
    private static final int TAM_RESULTADO = 10;

    /**
     * Constructor: abre o crea un archivo de acceso aleatorio.
     * 
     * @param ruta Ruta del archivo en el sistema
     * @throws IOException si no se puede abrir o crear el archivo
     */
    public ConexionAleatoria(String ruta) throws IOException {
        archivo = new RandomAccessFile(ruta, "rw");
    }

    /**
     * Escribe un registro al final del archivo con campos de tamaño fijo.
     * 
     * @param clave Identificador del registro 
     * @param nombreEquipo Nombre del equipo (máximo 30 caracteres)
     * @param jugadores Arreglo con los 4 nombres de jugadores (máx. 20 caracteres cada uno)
     * @param resultado Estado final 
     * @throws IOException 
     */
    public void escribirRegistro(int clave, String nombreEquipo, String[] jugadores, String resultado) throws IOException {
        archivo.seek(archivo.length()); // ir al final

        // Escribir clave (int)
        archivo.writeInt(clave);

        // Escribir campos de tamaño fijo
        escribirCadenaFija(nombreEquipo, TAM_NOMBRE_EQUIPO);

        for (int i = 0; i < 4; i++) {
            escribirCadenaFija(jugadores[i], TAM_NOMBRE_JUGADOR);
        }

        escribirCadenaFija(resultado, TAM_RESULTADO);
    }

    /**
     * Lee todos los registros del archivo desde el inicio.
     * 
     * Imprime cada registro en consola con el siguiente formato:
     * 
     * clave - equipo - jugador1, jugador2, jugador3, jugador4 - resultado
     * 
     * 
     * @throws IOException si ocurre un error de lectura
     */
    /**
 * Lee todos los registros del archivo y los retorna como lista de Strings.
 * Cada registro tiene el formato:
 * clave - equipo - jugador1, jugador2, jugador3, jugador4 - resultado
 *
 * @return lista con todos los registros del archivo
 * @throws IOException si ocurre un error de lectura
 */
public java.util.List<String> leerRegistros() throws IOException {
    java.util.List<String> registros = new java.util.ArrayList<>();
    archivo.seek(0);
    while (archivo.getFilePointer() < archivo.length()) {
        int clave = archivo.readInt();
        String equipo = leerCadenaFija(TAM_NOMBRE_EQUIPO);
        String[] jugadores = new String[4];
        for (int i = 0; i < 4; i++) {
            jugadores[i] = leerCadenaFija(TAM_NOMBRE_JUGADOR);
        }
        String resultado = leerCadenaFija(TAM_RESULTADO);

        registros.add(clave + " - " + equipo + " - " +
                String.join(", ", jugadores) + " - " + resultado);
    }
    return registros;
}

    /**
     * Escribe una cadena en un campo de tamaño fijo.
     * Si es más corta, se rellena con caracteres nulos.
     * Si es más larga, se recorta.
     * 
     * @param texto Texto a escribir
     * @param longitud Longitud fija en caracteres
     * @throws IOException 
     */
    private void escribirCadenaFija(String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto);
        sb.setLength(longitud); // recorta o rellena con \0
        archivo.writeChars(sb.toString());
    }

    /**
     * Lee una cadena de longitud fija desde el archivo.
     * 
     * @param longitud Número de caracteres a leer
     * @return Cadena leída sin espacios extra
     * @throws 
     */
    private String leerCadenaFija(int longitud) throws IOException {
        char[] chars = new char[longitud];
        for (int i = 0; i < longitud; i++) {
            chars[i] = archivo.readChar();
        }
        return new String(chars).trim(); // quita el relleno
    }

    /**
     * Cierra el archivo de acceso aleatorio.
     * 
     * @throws IOException si ocurre un error al cerrar
     */
    public void cerrar() throws IOException {
        if (archivo != null) archivo.close();
    }
}

