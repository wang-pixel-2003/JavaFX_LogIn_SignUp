package view.Controller;

import Controller.CLogIn;
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

public class LoginController {

    @FXML private TextField TFuser;

    @FXML private TextField TFpassword;

    @FXML private Button btnCrear;

    @FXML private Button btnLogin;


    /**
     * función para obtener los valores ingresados en el formularion de iniciar sesion y cambiar de ventana o alertar si los datos son incorrectos
     */
    public void login () throws IOException {
        if (CLogIn.checkUserPassword(TFuser.getText(),TFpassword.getText())){
            changeHomepage();
        } else {
            alert();
        }
    }


    /**
     * función para imprimir una alerta en pantalla informando que los datos no coinciden
     */
    public static void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("No se pudo iniciar sesión");
        alert.setContentText("Los datos ingresados no coinciden");
        alert.showAndWait();
    }


    /**
     * función para cambiar de ventana por la de Registro
     */
    public void changeRegistro() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("SignUp.fxml"));
        Parent root = fxmlLoader.load();
        RegisterController controlador = fxmlLoader.getController();
        Scene scene = new Scene(root,  450, 400);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e-> {
            try {
                controlador.closeWindow();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Stage myStage = (Stage) this.btnCrear.getScene().getWindow();
        myStage.close();

    }


    /**
     * función para cambiar de ventana por la de Home page
     */
    public void changeHomepage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("CrearTarea.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
        myStage.close();

    }

}
