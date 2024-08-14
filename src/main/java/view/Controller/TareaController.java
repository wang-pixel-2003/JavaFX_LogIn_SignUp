package view.Controller;

import Controller.CLogIn;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MUser;
import model.Task;
import AccessData.AccessData;
import view.Application;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class TareaController {

    @FXML private TableView<Task> tablaTareas;
    @FXML private TableColumn<Task, String> columnaTitulo;
    @FXML private TableColumn<Task, String> columnaDescripcion;
    @FXML private TableColumn<Task, String> columnaPrioridad;
    @FXML private TableColumn<Task, Date> columnaFechaVencimiento;
    @FXML private TableColumn<Task, String> columnaEstado;
    @FXML private TableColumn<Task, Date> columnaTags;
    @FXML private TableColumn<Task, Date> columnaFechaCreacion;
    @FXML private TableColumn<Task, Date> columnaFechaModificacion;

    @FXML private Button btnCalendario;

    @FXML
    public void initialize() {
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnaPrioridad.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnaFechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnaTags.setCellValueFactory(new PropertyValueFactory<>("tags"));
        columnaFechaCreacion.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        columnaFechaModificacion.setCellValueFactory(new PropertyValueFactory<>("modificationDate"));
        cargarTareasDesdeBaseDeDatos(); // Cargar tareas al iniciar
    }

    public void changeToCalendario() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Calendario.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.btnCalendario.getScene().getWindow();
        myStage.close();

    }

    private void cargarTareasDesdeBaseDeDatos() {
        int userId = CLogIn.userIdConnected;
        List<Task> listaTareas = AccessData.getTasksByUserId(userId);
        ObservableList<Task> tareasObservableList = FXCollections.observableArrayList(listaTareas);
        tablaTareas.setItems(tareasObservableList);
    }

    @FXML
    public void mostrarFormularioAgregar() {
        mostrarFormularioTarea(null);
    }

    @FXML
    public void mostrarFormularioActualizar() {
        Task tareaSeleccionada = tablaTareas.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada != null) {
            mostrarFormularioTarea(tareaSeleccionada);
        } else {
            mostrarAlerta("Por favor, selecciona una tarea para actualizar.");
        }
    }

    @FXML
    public void mostrarFormularioEliminar() {
        Task tareaSeleccionada = tablaTareas.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada != null) {
            eliminarTarea(tareaSeleccionada);
        } else {
            mostrarAlerta("Por favor, selecciona una tarea para eliminar.");
        }
    }

    private void mostrarFormularioTarea(Task tarea) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FormularioTarea.fxml"));
            Parent root = loader.load();

            FormularioTareaController controller = loader.getController();
            controller.setTarea(tarea);

            Stage stage = new Stage();
            stage.setTitle(tarea == null ? "Agregar Tarea" : "Actualizar Tarea");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            cargarTareasDesdeBaseDeDatos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarTarea(Task tarea) {
        AccessData.deleteTask(tarea.getId());
        cargarTareasDesdeBaseDeDatos();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    public void salir() {
        Stage stage = (Stage) tablaTareas.getScene().getWindow();
        stage.close();
    }
}
