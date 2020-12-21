package util;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class R {
    public TextField tfCodalu;
    public TextField tfNombre;
    public TextField tfApellido1;
    public TextField tfApellido2;
    public TextField tfObservaciones;
    public ComboBox<String> cbCodcurso;

    public static InputStream getImage(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("images" + File.separator + name);
    }

    public static URL getUI(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }


}