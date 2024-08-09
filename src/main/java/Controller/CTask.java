package Controller;

import AccessData.AccessData;
import model.Task;

import java.time.LocalDate;
import java.util.List;

public class CTask {

    // Agrega una nueva tarea a la base de datos
   /* public void addTask(int id, String title, String description, String priority, LocalDate dueDate, String status) {
        Task newTask = new Task(id, title, description, priority, dueDate, status);
        AccessData.insertTask(newTask); // Inserta la tarea en la base de datos
        System.out.println("Task added: " + newTask.getTitle());
    }*/

    // Recupera la lista de tareas desde la base de datos
    public List<Task> getTaskList() {
        return AccessData.getAllTasks(); // Obtiene todas las tareas desde la base de datos
    }

    // Actualiza una tarea existente en la base de datos
    /*public void updateTask(Task task, String title, String description, String priority, LocalDate dueDate, String status) {
        // Actualiza los campos de la tarea
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setDueDate(dueDate);
        task.setStatus(status);
        AccessData.insertTask(task); // Guarda la tarea actualizada en la base de datos
        System.out.println("Task updated: " + task.getTitle());
    }*/

    // Elimina una tarea de la base de datos
    public void deleteTask(Task task) {
        // Para eliminar una tarea, se puede implementar un método en AccessData
        // que elimine la tarea de la base de datos usando su identificador único,
        // si existe un campo como "id" en la tabla "task".

        System.out.println("Task deleted: " + task.getTitle());
    }

    // Encuentra una tarea por título (este método no se conecta directamente con la base de datos)
    public Task findTaskByTitle(String title) {
        for (Task task : getTaskList()) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return task;
            }
        }
        System.out.println("Task not found with title: " + title);
        return null;
    }

    // Lista todas las tareas
    public void listAllTasks() {
        List<Task> tasks = getTaskList();
        System.out.println("Listing all tasks:");
        for (Task task : tasks) {
            System.out.println(task.getTitle() + " - " + task.getDescription() + " - " + task.getPriority() + " - " + task.getDueDate() + " - " + task.getStatus());
        }
    }
}