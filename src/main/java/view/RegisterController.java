package view;

import Controller.Register;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class RegisterController {
    @FXML private TextField TFname;
    @FXML private TextField TFuser;
    @FXML private TextField TFpassword;
    @FXML private TextField TFid;
    @FXML private Button btnRegistrar;
    private String imagen = "Sin foto";

    /**
     * función para obtener los datos del formulario de registro y enviarlos a Register
     */
    public void crearUser () {
        Register.registerUser(TFname.getText(), TFuser.getText(), TFpassword.getText(),imagen, Integer.parseInt(TFid.getText()));
    }

    /**
     * función para crear una alerta en pantalla si el nombre de usuario ya se encuentra registrado
     */
    public static void alertUserName(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("El nombre de usuario ya se encuentra registrado");
        alert.showAndWait();
    }

    /**
     * función para crear una alerta en pantalla si la contraseña no cumple con el formato solicitado
     */
    public static void alertPasswordInvalid(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("La contraseña debe ser de 6 caracteres minimo y 8 maximo. Ademas debe estar conformada por una minuscula, una mayucula y un caracter especial");
        alert.showAndWait();
    }

    /**
     * funcion muestra una alerta de que se registro el usuario exitosamente
     */
    public static void alertSuccessfullyRegistered() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Usted se a registrado existosamente");
        alert.showAndWait();
    }

    /**
     * función para cerrar la ventana de registro y volver al iniciar sesion
     */
    public void closeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
        myStage.close();
    }
}