package Controller;

import AccessData.AccessData;
import model.MUser;
import view.Controller.RegisterController;
import java.sql.ResultSet;
import java.util.regex.Pattern;

/**
 * Controlador de registro
 */
public class CSignUp {
    private static final String passwordFormat = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,8}$";
    private static final Pattern passwordPattern = Pattern.compile(passwordFormat);

    /**
     * Crea e ingresa un usuario nuevo a la base de datos verificando que el username de este no exista, si el registro de este no funciona va provocar un alert
     * @param name Nombre completo
     * @param userName Nombre de usuario
     * @param password Contraseña
     * @param ide Identificacion
     */
    public static void registerUser(String name, String userName, String password, Integer ide, String profilePic) {
        // Verifica campos vacíos
        if (checkEmptyFields(name, userName, password, ide, profilePic)) {
            RegisterController.alertFieldsEmpty();
            return;
        }

        // Verifica el nombre de usuario, la contraseña y el ID
        if (!checkUsername(userName)) {
            if (checkPassword(password)) {
                if (!AccessData.isIdTaken(ide)) { // Verifica si el ID está en uso
                    MUser nuevoUser = new MUser(name, userName, password, ide, profilePic);
                    AccessData.insertUser(nuevoUser.getUserName(), String.valueOf(nuevoUser.getIde()), nuevoUser.getName(), nuevoUser.getPassword(), nuevoUser.getProfilePic());
                    RegisterController.alertSuccessfullyRegistered();
                } else {
                    RegisterController.alertIdTaken(); // Alerta si el ID ya está en uso
                }
            } else {
                RegisterController.alertPasswordInvalid();
            }
        } else {
            RegisterController.alertUserName();
        }
    }

    /**
     * Verifica si alguno de los campos está vacío
     * @param name Nombre completo
     * @param userName Nombre de usuario
     * @param password Contraseña
     * @param ide Identificación
     * @param profilePic URL o ruta de la imagen de perfil
     * @return boolean verdadero si algún campo está vacío, falso si todos están completos.
     */
    private static boolean checkEmptyFields(String name, String userName, String password, Integer ide, String profilePic) {
        return name == null || name.trim().isEmpty() ||
                userName == null || userName.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                ide == null ||
                profilePic == null || profilePic.trim().isEmpty();
    }

    /**
     * Funcion para verificar que el user name que se haya ingresado en el registro no exista en la base de datos, si este existe va retornar un valor true afirmando que es igual a otro
     * @param userName Nombre de usuario
     * @return boolean verdadero si es igual a otro o falso si no hay otro igual.
     */
    public static boolean checkUsername(String userName)  {
        boolean check = false;
        ResultSet users = AccessData.getAllUsers();
        try {
            while(users.next()){
                if (users.getString("userName").equals(userName)){
                    check = true;
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return check;
    }

    /**
     * Funcion para verificar que la contraseña posea el formato solicitado
     * @param password Contraseña por validar
     * @return boolean verdadero si cumple con el formato, falso si no.
     */
    public static boolean checkPassword(String password){
        if (passwordPattern.matcher(password).matches()) {
            return true;
        }
        else {
            return false;
        }
    }
}
