package Controller;

import AccessData.AccessData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controlador del log-in
 */
public class CLogIn {
    //esta variable guarda el nombre del usuario que logro inciar sesion
    public static String userConnected;

    /**
     *Verifica si el Usuario y la contraseña ingresadas existen en la base de datos, si es asi el usuario ingresa a la pagina principal, de lo contrario se da una alerta
     * @param userName Nombre de usuario ingresado
     * @param password Contraseña ingresada
     * @return retorna un valor booleano para saber si este entró o no.
     */
    public static int userIdConnected;

    public static boolean checkUserPassword(String userName, String password) {
        boolean isAuthenticated = AccessData.checkUserPassword(userName, password);
        if (isAuthenticated) {
            userConnected = userName;
            userIdConnected = getUserId(userName);
        }
        return isAuthenticated;
    }

    public static int getUserId(String userName) {
        int userId = -1;
        String query = "SELECT ide FROM users WHERE userName = ?";
        try (Connection connection = AccessData.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("ide");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
