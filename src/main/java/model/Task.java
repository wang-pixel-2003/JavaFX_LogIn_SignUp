package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Task {

    private IntegerProperty id;
    private StringProperty title;
    private StringProperty description;
    private StringProperty priority;
    private ObjectProperty<LocalDate> dueDate;
    private StringProperty status;
    private final ObjectProperty<LocalDate> creationDate;
    private ObjectProperty<LocalDate> modificationDate;

    // Constructor sin parámetros
    public Task() {
        this.id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.priority = new SimpleStringProperty();
        this.dueDate = new SimpleObjectProperty<>();
        this.status = new SimpleStringProperty();
        this.creationDate = new SimpleObjectProperty<>(LocalDate.now());
        this.modificationDate = new SimpleObjectProperty<>(LocalDate.now());
    }

    // Constructor con parámetros
    public Task(int id, String title, String description, String priority, LocalDate dueDate, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.priority = new SimpleStringProperty(priority);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
        this.status = new SimpleStringProperty(status);
        this.creationDate = new SimpleObjectProperty<>(LocalDate.now());
        this.modificationDate = new SimpleObjectProperty<>(LocalDate.now());
    }

    // Getters y setters para id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Getters y setters para los demás campos
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title.set(title);
            this.modificationDate.set(LocalDate.now());
        }
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        if (description != null && !description.trim().isEmpty()) {
            this.description.set(description);
            this.modificationDate.set(LocalDate.now());
        }
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String priority) {
        if (priority != null && !priority.trim().isEmpty()) {
            this.priority.set(priority);
            this.modificationDate.set(LocalDate.now());
        }
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate != null) {
            this.dueDate.set(dueDate);
            this.modificationDate.set(LocalDate.now());
        }
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status.set(status);
            this.modificationDate.set(LocalDate.now());
        }
    }

    public StringProperty statusProperty() {
        return status;
    }

    public LocalDate getCreationDate() {
        return creationDate.get();
    }

    public ObjectProperty<LocalDate> creationDateProperty() {
        return creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate.get();
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate.set(modificationDate);
    }

    public ObjectProperty<LocalDate> modificationDateProperty() {
        return modificationDate;
    }
}
