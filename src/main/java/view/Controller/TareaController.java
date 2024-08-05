package view.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    @FXML private TableColumn<Task, String> columnaFechaVencimiento;
    @FXML private TableColumn<Task, String> columnaEstado;

    @FXML
    public void initialize() {
        // Configurar las columnas para que usen las propiedades de Task
        columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnaPrioridad.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnaFechaVencimiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDueDate().toString()));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Cargar los datos desde la base de datos
        cargarTareasDesdeBaseDeDatos();
    }

    private void cargarTareasDesdeBaseDeDatos() {
        List<Task> listaTareas = AccessData.getAllTasks();
        ObservableList<Task> tareasObservableList = FXCollections.observableArrayList(listaTareas);
        tablaTareas.setItems(tareasObservableList);
    }

    @FXML
    private void crearTarea() {
        mostrarFormularioTarea(new Task());
    }

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
