package controlers;

import basesDAO.DAOmysql;
import domain.Alumno;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.AlertUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ControlPanelColegio {

    public TextField tfCodalu;
    public TextField tfNombre;
    public TextField tfApellido1;
    public TextField tfApellido2;
    public TextField tfObservaciones;
    public Button btCrear, btModificar, btEliminar, btConfirmar, btGuardar,btBuscar;
    public ComboBox<String> cbCodcurso;
    public Label lbEstado;
    public ListView<Alumno> lvListado;
    private final String MENSAJEBARRABAJA = "Esperando nueva acción";

    private DAOmysql daOmysql;
    private Alumno alumnoseleccionado;

    public void cargarLista() {
        lbEstado.setText(MENSAJEBARRABAJA);
        daOmysql = new DAOmysql();
        try {
            daOmysql.conectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Documentar métodos
     * <p>
     * Describe método
     * Describes parametros
     * Describes retorno
     **/
//TODO
    public void cargarListado() {
        cargarLista();//___________________
        List<Alumno> listadoalumnos = null;
        try {
            listadoalumnos = daOmysql.listaAlumnos();

        } catch (Exception e) {
            System.out.println("Ha habido un problema al cargar las lista");
        }

        lvListado.setItems(FXCollections.observableList(listadoalumnos));
    }

    private void cargarAlumno(Alumno alumno) {
        tfCodalu.setText(alumno.getCodCurso());
        tfNombre.setText(alumno.getNombre());
        tfApellido1.setText(alumno.getApellido1());
        tfApellido2.setText(alumno.getApellido2());
        tfObservaciones.setText(alumno.getObservaciones());
    }

    @FXML
    public void seleccionarAlumno(Event event) {
        alumnoseleccionado = lvListado.getSelectionModel().getSelectedItem();
        cargarAlumno(alumnoseleccionado);
    }

    @FXML
    public void crearAlumno(Event event) {
        cargarListado();//-----------
        modoCrear(true);
        mostarinfobarra("Creando un nuevo registro", "Creando un nuevo registro", 1000);

    }


    @FXML
    public void modificarAlumno(Event event) {
        mostarinfobarra("Modificando un registro", "Modificando un registro", 1000);
        modoModificar();


    }

    @FXML
    public void cancelar(Event event) {
        mostarinfobarra("Cancelando", MENSAJEBARRABAJA, 500);
        modoCrear(false);//llamo a este método en false para que habilite de nuevo los botones
        //Esto es nuevo 14-11
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Edición");
        confirmacion.setContentText("¿Estás seguro?");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
            return;

        modoCrear(false);
        cargarAlumno(alumnoseleccionado);

    }

    @FXML
    public void eliminarAlumno(Event event) {
        if (lvListado.getSelectionModel().isEmpty()) {
            lbEstado.setText("No se ha seleccionado ningún alumno");
            return;
        }

        if (!AlertUtils.pedirConfirmacion("¿Seguro que quieres eliminar?", "Eliminar alumno")) {
            return;
        }

        Alumno alumno = lvListado.getSelectionModel().getSelectedItem();
        try {
            daOmysql.eliminaAlumno(alumno);
            lbEstado.setText("Registro eliminado con éxito");
            cargarListado();

        } catch (SQLException sqle) {
            AlertUtils.mostarError("No se ha podido eliminar el alumno");
        } catch (Exception e) {
            e.printStackTrace();
        }

        limpiarcajas();
        mostarinfobarra("Elimando registro", MENSAJEBARRABAJA, 250);
    }
    //Boton CONFIMAR
    @FXML
    public void guardaModifi(Event event) {

    }
    //todo //Botón BUSCAR
    @FXML
    public void buscar(Event event) throws SQLException {
        modobuscar();
        String ap1 = tfApellido1.getText();
        String ap2 = tfApellido2.getText();
        DAOmysql objbuscar = new DAOmysql();
        objbuscar.buscar(ap1,ap2);




    }
    //Este es el botón CONFIRMAR
    @FXML
    public void guardarAlumno(Event event) {
        Alumno alumno = new Alumno(
                tfNombre.getText(),
                tfApellido1.getText(),
                tfApellido2.getText(),
                //cbCodcurso.getItems().get(1).toString(),
                "",
                tfObservaciones.getText()
        );

        try {
            daOmysql.crearAlumno(alumno);
            System.out.println("Crea bien el registro de alumno");


        } catch (Exception e) {
            System.out.println("no se registra usuario metido");
            e.printStackTrace();
        }
        cargarListado();
        mostarinfobarra("Confirmando", MENSAJEBARRABAJA, 500);
        modoCrear(false);
    }

    //boolean para controlar activar-desactivar botones en crear.
    public void modoCrear(boolean modocrear) {
        // Limpia los campos tf...
        limpiarcajas();
        // Lleva el foco al tfNombre
        tfNombre.requestFocus();
        // Deshabilita botones y tfCodalu
        tfCodalu.setDisable(modocrear);
        btCrear.setDisable(modocrear);
        btModificar.setDisable(modocrear);
        btEliminar.setDisable(modocrear);
    }

    //Creamos un método para el modomodificar. Desactiva botonesre
    public void modoModificar() {
        tfNombre.requestFocus();
        tfCodalu.setDisable(true);
        tfNombre.setEditable(true);
        tfApellido1.getSelectedText();
        tfApellido2.getSelectedText();
        tfObservaciones.getSelectedText();
        btGuardar.setDisable(true);
        btCrear.setDisable(true);
        btEliminar.setDisable(true);
        btBuscar.setDisable(true);


    }
    //todo
    public void modobuscar(){
        tfNombre.setDisable(true);
        tfCodalu.setDisable(true);
        btCrear.setDisable(true);
        btEliminar.setDisable(true);
        btModificar.setDisable(true);
        btGuardar.setDisable(true);
        btConfirmar.setDisable(false);
        tfApellido1.requestFocus();



    }

    //Creamos un método para las info

    private void mostarinfobarra(String mensaje1, String mensaje2, long tiempo) {

        //creamos un hilo
        Thread mensajesinfo = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                try {
                    int valoripaso = i;
                    Platform.runLater(new Runnable() {//El objeto Platform contola los hilos en JavaFX
                        @Override
                        public void run() {//Ejecuta elementos JFX
                            if (valoripaso < 3) {
                                lbEstado.setText(mensaje1);
                                lbEstado.setStyle("-fx-font-weight:bold");
                            } else {
                                lbEstado.setText(mensaje2);
                                lbEstado.setStyle("-fx-font-weight:regular");
                            }
                        }
                    });
                    //ponemos tiempo que va a dormir entre pasos del for
                    Thread.sleep(tiempo);
                } catch (Exception e) {
                    System.out.println("Hubo un error");
                }

            }

        });
        mensajesinfo.start();


    }

    @FXML
    public void seleccionardelalista(Event event) {
        if (lvListado.getItems().isEmpty()) {
            AlertUtils.mostarError("No hay elementos en la lista");
            return;

        }
        tfCodalu.setText(String.valueOf(lvListado.getSelectionModel().getSelectedItem().getCodAlum()));
        tfNombre.setText(lvListado.getSelectionModel().getSelectedItem().getNombre());
        tfApellido1.setText(lvListado.getSelectionModel().getSelectedItem().getApellido1());
        tfApellido2.setText(lvListado.getSelectionModel().getSelectedItem().getApellido2());
        cbCodcurso.setAccessibleText(lvListado.getSelectionModel().getSelectedItem().getCodCurso());
        tfObservaciones.setText(lvListado.getSelectionModel().getSelectedItem().getObservaciones());
    }


    public void limpiarcajas() {
        tfCodalu.clear();
        tfNombre.clear();
        tfApellido1.clear();
        tfApellido2.clear();
        cbCodcurso.setValue("");
        tfObservaciones.clear();
    }

}


