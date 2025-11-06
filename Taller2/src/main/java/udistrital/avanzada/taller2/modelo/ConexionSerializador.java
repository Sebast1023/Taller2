/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Clase que serializa los equipos y los deserealiza desde un archivo
 *
 * NOTA: por la especificación del taller, el archivo de salida debe estar en la
 * carpeta:
 *
 * Specs/data
 *
 * @author Mauricio
 * @since 04/10/2025
 */
public class ConexionSerializador {

    //Canal de salida para escribir en el archivo de serializacion
    private FileOutputStream fileOut;
    private ObjectOutputStream salida;

    //Canal de salida para escribir en el archivo de serializacion
    private FileInputStream fileIn;
    private ObjectInputStream entrada;

    private String rutaPredeterminada;

    public ConexionSerializador() {
        // Ubicacion donde ira el archivo predeterminadamente
        rutaPredeterminada = "Specs/data/equipos.bin";
    }

    /**
     * Método para configurar los canales de serializacion local
     */
    public void config() {
        try {
            //Para escribir
            fileOut = new FileOutputStream(rutaPredeterminada);
            salida = new ObjectOutputStream(fileOut);

            //Para leer
            fileIn = new FileInputStream(rutaPredeterminada);
            entrada = new ObjectInputStream(fileIn);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

    }

    public boolean cerrarArchivoSerializadoOut() {
        if (salida != null) {
            try {
                salida.close();
            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    }

    public boolean cerrarArchivoSerializadoIn() {
        if (entrada != null) {
            try {
                entrada.close();
            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo para escribir archivos serizalidos
     *
     * @param equipos
     */
    public void escribirArchivoSerializado(ArrayList<Equipo> equipos) {
        if (salida != null) {
            try {
                salida.writeObject(equipos);
            } catch (IOException ex) {

            }
        }
    }

    /**
     *
     * Metodo para recosntruir el objeto ArrayList de equipos desde los bytes
     * del archivo
     *
     * @return equipos deserilizados si no null
     */
    public ArrayList<Equipo> leerArchivoSerializado() {
        ArrayList<Equipo> equipos = null;

        try {
            equipos = (ArrayList<Equipo>) entrada.readObject();
        } catch (Exception e) {

        }
        return equipos;
    }

    /**
     *
     * Metodo sobrecargado para recosntruir el objeto ArrayList de equipos desde
     * los bytes del archivo que se pase como parametro
     *
     * @param archivo
     * @return equipos deserilizados si no null
     */
    public ArrayList<Equipo> leerArchivoSerializado(File archivo) {
        ArrayList<Equipo> equipos = null;

        FileInputStream fileIn = null;
        ObjectInputStream entrada = null;

        try {
            fileIn = new FileInputStream(archivo);
            entrada = new ObjectInputStream(fileIn);
            equipos = (ArrayList<Equipo>) entrada.readObject();
        } catch (Exception e) {

        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException ex) {

                }
            }

        }
        return equipos;
    }

    /**
     * Saber si archivo serializado exite en data
     *
     * @return
     */
    public boolean existeBin() {
        File archivo = new File(rutaPredeterminada);
        return archivo.exists();
    }
}
