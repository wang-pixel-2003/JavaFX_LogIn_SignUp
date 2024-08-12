package AccessData;

import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccessData {

    private static final String URLconection = "jdbc:mysql://localhost:3306/newdb";
    private static final String userDataBase = "root";
    private static final String passwordDataBase = "Amse_1701!";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar el controlador JDBC
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URLconection, userDataBase, passwordDataBase);
    }

    /**
     * Agrega la informacion a la tabla de usuarios.
     *
     * @param name     Nombre Completo
     * @param userName Nombre de usuario
     * @param password Contraseña
     * @param ide      Identificacion
     */
    public static void insertUser(String userName,String ide, String name, String password){
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO users (name, userName, password, ide) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, userName);
                ps.setString(3, password);
                ps.setString(4, ide);
                ps.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Registro con esta clave primaria ya existe.");
            // Aquí puedes agregar lógica adicional, como notificar al usuario

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve todos los registros de la tabla usuarios
     * @return Datos de la tabla usuarios.
     */
    public static ResultSet getAllUsers(){
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isIdTaken(int ide) {
        String query = "SELECT COUNT(*) FROM users WHERE ide = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, ide);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Retorna true si el ID ya está en uso, false de lo contrario
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Por defecto, si hay algún error, asumimos que el ID no está en uso
    }

    public static boolean checkUserPassword(String userName, String password) {
        String query = "SELECT 1 FROM users WHERE userName = ? AND password = ?";
        try (Connection connection = getConnection(); // Usar el método getConnection para manejar la conexión
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, userName);
            ps.setString(2, password);

            try (ResultSet resultSet = ps.executeQuery()) {
                return resultSet.next(); // Retorna verdadero si hay al menos un registro que coincide
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna falso si ocurre una excepción o no se encuentra el usuario
    }

    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM task"; // Asegúrate de que el nombre de la tabla sea correcto
        try (Connection conn = getConnection(); // Usar el método getConnection para manejar la conexión
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("priority"),
                        rs.getDate("due_date"),
                        rs.getString("status"),
                        rs.getString("tags"),
                        rs.getDate("creation_date"),
                        rs.getDate("modification_date"),
                        rs.getInt("user_id")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tasks: " + e.getMessage());
            e.printStackTrace();
        }
        return tasks;
    }

    public static void insertTask(Task task) {
        String query = "INSERT INTO task (title, description, priority, due_date, status, tags, creation_date, modification_date, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getPriority());
            ps.setDate(4, task.getDueDate());
            ps.setString(5, task.getStatus());
            ps.setString(6, task.getTags());
            ps.setDate(7, task.getCreationDate());
            ps.setDate(8, task.getModificationDate());
            ps.setInt(9, task.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTask(Task task) {
        String query = "UPDATE task SET title = ?, description = ?, priority = ?, due_date = ?, status = ?, tags = ?, modification_date = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getPriority());
            ps.setDate(4, task.getDueDate());
            ps.setString(5, task.getStatus());
            ps.setString(6, task.getTags());
            ps.setDate(7, task.getModificationDate());
            ps.setInt(8, task.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTask(int taskId) {
        String query = "DELETE FROM task WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}