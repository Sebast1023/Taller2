/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.taller2.modelo;

import java.io.File;

/**
 * Clase para guardar momentaneamente los archivos de precarga
 *
 * @author Mauricio
 * @since 04/10/2025
 */
public class ArchivoSeleccionado {

    private File archivoProperties;
    private File archivoBin; 
    
    public File getArchivoProperties() {
        return archivoProperties;
    }

    public void setArchivoProperties(File archivoProperties) {
        if (archivoProperties.getName().toLowerCase().endsWith(".properties")) {
            this.archivoProperties = archivoProperties;
        }        
    }

    public File getArchivoBin() {
        return archivoBin;
    }

    public void setArchivoBin(File archivoBin) {
        if (archivoBin.getName().toLowerCase().endsWith(".bin")) {
            this.archivoBin = archivoBin;    
        }        
    }

    public boolean archivosCompletos() {
        return archivoProperties != null && archivoBin != null;
    }
}
