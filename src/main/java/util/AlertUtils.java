package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;



public class AlertUtils {
    public static void mostarError(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mensaje);
        alerta.show();
    }
    //Hecho el 15-11-2020 Mensaje paa pedir confirmación
    //todo Revisar este método porque elimina los registros aunque canceles
    public static boolean pedirConfirmacion(String mensaje, String titulo){
        boolean respuestaeliminar;
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle(titulo);
        confirmacion.setContentText(mensaje);
        Optional<ButtonType> respuesta = confirmacion.showAndWait();
       if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
            respuestaeliminar=false;
        else respuestaeliminar=true;
        return respuestaeliminar;
    }
}
