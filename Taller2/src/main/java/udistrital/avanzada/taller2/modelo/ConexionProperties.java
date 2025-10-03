
import java.io.*;
import java.util.Properties;

public class ConexionProperties {

    public Properties cargar(File archivo) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(archivo)) {
            props.load(fis);
        }
        return props;
    }
}
