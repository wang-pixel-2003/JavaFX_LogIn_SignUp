package view.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.MUser;
import model.Task;
import AccessData.AccessData;

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
    @FXML private TableColumn<Task, Date> columnaFechaCreacion;
    @FXML private TableColumn<Task, Date> columnaFechaModificacion;
    @FXML private Label labelUsuario;

    @FXML
    public void initialize() {
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnaPrioridad.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnaFechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnaFechaCreacion.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        columnaFechaModificacion.setCellValueFactory(new PropertyValueFactory<>("modificationDate"));
        cargarTareasDesdeBaseDeDatos(); // Cargar tareas al iniciar
    }

    private void cargarTareasDesdeBaseDeDatos() {
        List<Task> listaTareas = AccessData.getAllTasks(); // Cambia a getAllTasks()
        ObservableList<Task> tareasObservableList = FXCollections.observableArrayList(listaTareas);
        tablaTareas.setItems(tareasObservableList);
    }

    /*@FXML
    private void crearTarea() {
        mostrarFormularioTarea(new Task());
    }*/

    private void mostrarFormularioTarea(Task tarea) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TareaFormulario.fxml"));
            Parent root = loader.load();

            TareaFormularioController controller = loader.getController();
            controller.setTask(tarea);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void salir() {
        Stage stage = (Stage) tablaTareas.getScene().getWindow();
        stage.close();
    }
}
