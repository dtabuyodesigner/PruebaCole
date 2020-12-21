package general;

import controlers.ControlPanelColegio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.R;

// Clase App para iniciar la aplicación
public class App extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("panelColegio.fxml"));
        ControlPanelColegio controlador = new ControlPanelColegio();
        loader.setController(controlador);
        VBox vbox = loader.load();
        controlador.cargarLista();
        controlador.cargarListado();
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}