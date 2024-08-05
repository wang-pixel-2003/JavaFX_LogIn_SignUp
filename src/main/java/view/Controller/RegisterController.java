package view.Controller;

import Controller.CSignUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.Application;

import java.io.IOException;


public class RegisterController {
    @FXML private TextField TFname;
    @FXML private TextField TFuser;
    @FXML private TextField TFpassword;
    @FXML private TextField TFid;
    @FXML private Button btnRegistrar;

    /**
     * función para obtener los datos del formulario de registro y enviarlos a SignUp
     */
    public void crearUser () {
        CSignUp.registerUser(TFname.getText(), TFuser.getText(), TFpassword.getText(), Integer.parseInt(TFid.getText()));
    }

    public static void alertFieldsEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Por favor, rellene todos los campos.");
        alert.showAndWait();
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
        alert.setContentText("La contraseña debe ser de 6 caracteres mínimo y 8 máximo. Además, debe estar conformada por una minúscula, una mayúscula y un carácter especial.");

        // Establecer el tamaño mínimo de la alerta
        alert.getDialogPane().setPrefWidth(400);
        alert.getDialogPane().setPrefHeight(200); // Puedes ajustar estos valores según sea necesario

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
     * funcion muestra una alerta de que tiene problemas con el id de usuario
     */
    public static void alertIdTaken() {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Usualmente se usa ERROR para mensajes de error
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("El ID está en uso.");
        alert.showAndWait();
    }

    /**
     * función para cerrar la ventana de registro y volver al iniciar sesion
     */
    public void closeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("LogIn.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
        myStage.close();
    }
}