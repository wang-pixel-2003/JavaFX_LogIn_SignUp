package view.Controller;

import Controller.CSignUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.Application;

import java.io.File;
import java.io.IOException;

public class RegisterController {
    @FXML private TextField TFname;
    @FXML private TextField TFuser;
    @FXML private TextField TFpassword;
    @FXML private TextField TFid;
    @FXML private Button btnRegistrar;
    @FXML private Label lbImagen;
    private String imagen = "Sin foto";

    public void crearUser() {
        // Validar que todos los campos están llenos
        if (TFname.getText().isEmpty() || TFuser.getText().isEmpty() || TFpassword.getText().isEmpty() || TFid.getText().isEmpty()) {
            alertFieldsEmpty();
            return;
        }

        // Validar que el campo ID es un número válido
        Integer id;
        try {
            id = Integer.parseInt(TFid.getText());
        } catch (NumberFormatException e) {
            alertIdInvalid();
            return;
        }

        // Llamar al método de registro
        CSignUp.registerUser(TFname.getText(), TFuser.getText(), TFpassword.getText(), id, imagen);
    }

    public void buscarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la búsqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);
        if (imgFile != null) {
            try {
                Image ImgCamisa = new Image(new File(imgFile.toString()).toURI().toString());
                ImageView IVcamisa = new ImageView(ImgCamisa);
                IVcamisa.setFitWidth(100);
                IVcamisa.setFitHeight(105);
                lbImagen.setGraphic(IVcamisa);
                imagen = imgFile.toString();
            } catch (Exception e) {
                e.printStackTrace(); // Manejar error de carga de imagen
            }
        }
    }

    public static void alertFieldsEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Por favor, rellene todos los campos.");
        alert.showAndWait();
    }

    public static void alertUserName() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("El nombre de usuario ya se encuentra registrado.");
        alert.showAndWait();
    }

    public static void alertPasswordInvalid() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("La contraseña debe ser de 6 caracteres mínimo y 8 máximo. Además, debe estar conformada por una minúscula, una mayúscula y un carácter especial.");
        alert.getDialogPane().setPrefWidth(400);
        alert.getDialogPane().setPrefHeight(200);
        alert.showAndWait();
    }

    public static void alertSuccessfullyRegistered() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Usted se ha registrado exitosamente.");
        alert.showAndWait();
    }

    public static void alertIdTaken() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("El ID está en uso.");
        alert.showAndWait();
    }

    public static void alertIdInvalid() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("El ID debe ser un número válido.");
        alert.showAndWait();
    }

    public void closeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("LogIn.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 270, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
        myStage.close();
    }
}