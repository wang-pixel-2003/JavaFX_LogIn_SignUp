package view.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Task;
import AccessData.AccessData;

import java.sql.Date;
import java.time.LocalDate;

public class FormularioTareaController {

    @FXML private Label labelTitulo;
    @FXML private TextField txtTitulo;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtPrioridad;
    @FXML private DatePicker dpFechaVencimiento;
    @FXML private TextField txtEstado;
    @FXML private TextField txtTags;
    @FXML private DatePicker dpFechaCreacion;
    @FXML private DatePicker dpFechaModificacion;
    @FXML private TextField txtUserId;

    private Task tarea;

    public void setTarea(Task tarea) {
        this.tarea = tarea;
        if (tarea != null) {
            labelTitulo.setText("Actualizar Tarea");
            txtTitulo.setText(tarea.getTitle());
            txtDescripcion.setText(tarea.getDescription());
            txtPrioridad.setText(tarea.getPriority());
            dpFechaVencimiento.setValue(tarea.getDueDate().toLocalDate());
            txtEstado.setText(tarea.getStatus());
            txtTags.setText(tarea.getTags());
            dpFechaCreacion.setValue(tarea.getCreationDate().toLocalDate());
            dpFechaModificacion.setValue(tarea.getModificationDate().toLocalDate());
            txtUserId.setText(String.valueOf(tarea.getUserId()));
        } else {
            labelTitulo.setText("Agregar Tarea");
        }
    }

    @FXML
    public void guardarTarea() {
        String titulo = txtTitulo.getText();
        String descripcion = txtDescripcion.getText();
        String prioridad = txtPrioridad.getText();
        LocalDate fechaVencimiento = dpFechaVencimiento.getValue();
        String estado = txtEstado.getText();
        String tags = txtTags.getText().isEmpty() ? null : txtTags.getText();
        LocalDate fechaCreacion = dpFechaCreacion.getValue();
        LocalDate fechaModificacion = dpFechaModificacion.getValue();
        int userId = Integer.parseInt(txtUserId.getText());

        if (titulo.isEmpty() || descripcion.isEmpty() || prioridad.isEmpty() || fechaVencimiento == null || estado.isEmpty() || fechaCreacion == null || fechaModificacion == null || txtUserId.getText().isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        if (tarea == null) {
            // Agregar nueva tarea
            Task nuevaTarea = new Task(0, titulo, descripcion, prioridad, Date.valueOf(fechaVencimiento), estado, tags, Date.valueOf(fechaCreacion), Date.valueOf(fechaModificacion), userId);
            AccessData.insertTask(nuevaTarea);
        } else {
            // Actualizar tarea existente
            tarea.setTitle(titulo);
            tarea.setDescription(descripcion);
            tarea.setPriority(prioridad);
            tarea.setDueDate(Date.valueOf(fechaVencimiento));
            tarea.setStatus(estado);
            tarea.setTags(tags);
            tarea.setCreationDate(Date.valueOf(fechaCreacion));
            tarea.setModificationDate(Date.valueOf(fechaModificacion));
            tarea.setUserId(userId);
            AccessData.updateTask(tarea);
        }

        cerrarVentana();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    public void cerrarVentana() {
        Stage stage = (Stage) txtTitulo.getScene().getWindow();
        stage.close();
    }
}
