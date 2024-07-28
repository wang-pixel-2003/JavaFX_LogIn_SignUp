package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Task {

    private Long taskId;
    private int userId;
    private String title;
    private String description;
    private Priority priority;
    private Date dueDate;
    private Status status;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public Task() {
    }

    public Task(Long taskId, int userId, String title, String description, Priority priority, Date dueDate, Status status, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.taskId = taskId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
