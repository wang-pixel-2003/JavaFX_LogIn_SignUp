package AccessData;

import java.sql.*;

public class AccessData {

    private static final String URL = "jdbc:mysql://45.88.196.5:3306/u484426513_diseno224";
    private static final String USER = "u484426513_diseno224";
    private static final String PASSWORD = "#7cYr646u@*Rp.P";

    // Singleton instance
    private static AccessData instance;
    private static Connection connection;

    // Private constructor to prevent instantiation
    private AccessData() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado al server remoto");
        } catch (SQLException e) {
            System.err.println("Fallo la conexion: " + e.getMessage());
        }
    }

    // Public method to provide access to the singleton instance
    public static synchronized AccessData getInstance() {
        if (instance == null) {
            instance = new AccessData();
        }
        return instance;
    }

    // Method to get the connection
    public Connection getConnection() {
        return connection;
    }

    /**
     * Agrega la informacion a la tabla de usuarios.
     * @param userName Nombre de usuario
     * @param ide Identificacion
     * @param name Nombre Completo
     * @param password Contraseña
     */
    public static void insertUser(String userName, String ide, String name, String password){
        String sql = "INSERT INTO user (userName, ide, name, password) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, userName);
            ps.setString(2, ide);
            ps.setString(3, name);
            ps.setString(4, password);

            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Devuelve todos los registros de la tabla usuarios
     * @return Datos de la tabla usuarios.
     */
    public static ResultSet getAllUsers(){
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
