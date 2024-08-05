package view.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TareaFormularioController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField priorityField;

    @FXML
    private TextField dueDateField;

    @FXML
    private TextField statusField;

    private Task task;

    public void setTask(Task task) {
        this.task = task;
        if (task != null) {
            titleField.setText(task.getTitle());
            descriptionField.setText(task.getDescription());
            priorityField.setText(task.getPriority());
            if (task.getDueDate() != null) {
                dueDateField.setText(task.getDueDate().toString());
            }
            statusField.setText(task.getStatus());
        }
    }

    @FXML
    private void saveTask() {
        System.out.println("saveTask method called");

        if (task != null) {
            System.out.println("Saving task with the following data:");
            task.setTitle(titleField.getText());
            System.out.println("Title: " + titleField.getText());

            task.setDescription(descriptionField.getText());
            System.out.println("Description: " + descriptionField.getText());

            task.setPriority(priorityField.getText());
            System.out.println("Priority: " + priorityField.getText());

            if (!dueDateField.getText().isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                try {
                    LocalDate dueDate = LocalDate.parse(dueDateField.getText(), formatter);
                    task.setDueDate(dueDate);
                    System.out.println("Due Date: " + dueDate);
                } catch (DateTimeParseException e) {
                    System.out.println("Incorrect date format: " + e.getMessage());
                }
            }

            task.setStatus(statusField.getText());
            System.out.println("Status: " + statusField.getText());
        } else {
            System.out.println("The task is null");
        }
    }
}