package Controller;

import AccessData.AccessData;
import java.sql.ResultSet;

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
    public static boolean checkUserPassword(String userName, String password) {
        boolean isAuthenticated = AccessData.checkUserPassword(userName, password);
        if (isAuthenticated) {
            userConnected = userName;
        }
        return isAuthenticated;
    }
}
