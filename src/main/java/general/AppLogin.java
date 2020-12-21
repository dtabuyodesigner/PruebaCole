package general;

import controlers.ControlPanelLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.R;

public class AppLogin extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }


    @Override
    public void start(Stage stage) throws Exception {

        ControlPanelLogin controlador = new ControlPanelLogin();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("login.fxml"));
        loader.setController(controlador);
        VBox vbox = loader.load();
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

