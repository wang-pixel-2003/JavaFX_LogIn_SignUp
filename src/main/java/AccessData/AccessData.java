package AccessData;

import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccessData {

    private static final String URLconection = "jdbc:mysql://localhost:3306/newdb";
    private static final String userDataBase = "root";
    private static final String passwordDataBase = "1234";

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

    /* public static void insertTask(Task task) {
        String query = "INSERT INTO task (title, description, priority, due_date, status, creation_date, modification_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); // Usar el método getConnection para manejar la conexión
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getPriority());
            pstmt.setDate(4, Date.valueOf(task.getDueDate()));
            pstmt.setString(5, task.getStatus());
            pstmt.setDate(6, Date.valueOf(task.getCreationDate())); // Assuming Tarea has a getCreationDate() method
            pstmt.setDate(7, Date.valueOf(task.getModificationDate())); // Assuming Tarea has a getModificationDate() method
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting task: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateTask(Task task) {
        String query = "UPDATE task SET title = ?, description = ?, priority = ?, due_date = ?, status = ?, modification_date = ? WHERE id = ?";
        try (Connection conn = getConnection(); // Usar el método getConnection para manejar la conexión
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, task.getId()); // Assuming Task has a getId() method
            pstmt.setString(2, task.getTitle());
            pstmt.setString(3, task.getDescription());
            pstmt.setString(4, task.getPriority());
            pstmt.setDate(5, Date.valueOf(task.getDueDate()));
            pstmt.setString(6, task.getStatus());
            pstmt.setDate(7, Date.valueOf(task.getModificationDate())); // Assuming Task has a getModificationDate() method
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
            e.printStackTrace();
        }
    }*/
}