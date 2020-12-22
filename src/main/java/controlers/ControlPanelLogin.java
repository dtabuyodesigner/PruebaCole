package controlers;

import basesDAO.LoginDAO;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.R;

import java.sql.SQLException;

public class ControlPanelLogin {
    public TextField tvNomUsu;
    public PasswordField tvPass;
    public Label lbLogin;
    public Button btRegistro, btAceptar;


    private LoginDAO loginDAO;
    private ControlPanelColegio appController = new ControlPanelColegio();

    public ControlPanelLogin() throws Exception {
        try {
            appController = new ControlPanelColegio();
            loginDAO = new LoginDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void aceptar(Event event) {
        String usuario = tvNomUsu.getText();
        String pass = tvPass.getText();
        try {
            boolean respuesta = loginDAO.verificarLogin(usuario, pass);
            if (respuesta) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(R.getUI("panelColegio.fxml"));
                loader.setController(appController);
                VBox vbox = loader.load();
                Scene scene = new Scene(vbox);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de verificación");
                alerta.setContentText("Usuario o contraseña incorrectas");
                alerta.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    public void registrar(Event event) {
        String usuario = tvNomUsu.getText().toString();
        String pass = tvPass.getText().toString();
        try {
            boolean result = loginDAO.registrar(usuario, pass);
            avisoRegistro(result);
        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cierra la pantalla al pulsar en salir
    @FXML
    public void salirLogin(Event event) throws SQLException {
        Platform.exit();
    }

    // Avisa si el registro ha ido bien o mal
    public void avisoRegistro(boolean result) {
        if (result) {
            lbLogin.setText("Usuario registrado con exito");
            limpiar();
        } else {
            lbLogin.setText("Error al registrar");
        }
    }


    public void limpiar() {
        tvNomUsu.setText("");
        tvPass.setText("");
    }

}

