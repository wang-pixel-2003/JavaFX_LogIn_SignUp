package view.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Task;

public class MenuContextualTareaController {

    private TareaController tareaControlador;
    private Task tareaSeleccionada;

    public void setControlador(TareaController controlador) {
        this.tareaControlador = controlador;
    }

    public void setTareaSeleccionada(Task tarea) {
        this.tareaSeleccionada = tarea;
    }

    @FXML
    public void actualizarTarea(ActionEvent event) {
//         tareaControlador.abrirFormularioTarea(tareaSeleccionada);
        cerrarMenuContextual(event);
    }

    @FXML
    public void completarTarea(ActionEvent event) {
//         tareaControlador.completarTarea(tareaSeleccionada);
        cerrarMenuContextual(event);
    }

    @FXML
    public void eliminarTarea(ActionEvent event) {
//        tareaControlador.eliminarTarea(tareaSeleccionada);
        cerrarMenuContextual(event);
    }

    @FXML
    public void cerrarMenuContextual(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
