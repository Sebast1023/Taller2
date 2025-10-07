package udistrital.avanzada.taller2.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase para conectar al archivo properties que sea especificado por
 * usuario
 * 
 * @author mauri
 * @since 03/10/2025
 */

public class ConexionProperties {
    /**
     * Metodo para cargar el archivo properties
     * @param archivo
     * @return Properties, si hubo algun error null 
     */
    public Properties cargar(File archivo) {
        Properties props = new Properties();
        InputStream entrada = null;
        try {
            entrada = new FileInputStream(archivo);
            props.load(entrada);
            return props;
        } catch (IOException ex) {
            return null;
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }

    }
}
