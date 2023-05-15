package com.example.masterapp;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import static com.example.masterapp.Main.in;
import static com.example.masterapp.Main.out;

public class MainController {

    @FXML
    TextField inicioUsuario;
    @FXML
    TextField inicioContra;
    @FXML
    Button validarButton;
    @FXML
    Label inicioError;
    private ArbolBinarioBusqueda arbolUsuarios = new ArbolBinarioBusqueda();
    private ArbolBinarioBusqueda arbolClientes = new ArbolBinarioBusqueda();
    private ArbolAVL arbolPlatillos = new ArbolAVL();




    @FXML
    public void initialize() {

    }







    @FXML
    private void iniciarSesion() throws IOException, ClassNotFoundException {
        String username = inicioUsuario.getText();
        String password = inicioContra.getText();

        if (username.isEmpty() || password.isEmpty()) {
            inicioError.setText("Ingrese ambos datos");
        } else {
            String usuarioIngresado = username;
            String contraIngresada = password;
            out.writeObject("validarLogin");
            out.writeObject(usuarioIngresado);
            out.writeObject(contraIngresada);

            String respuesta = (String) in.readObject();
            System.out.println(respuesta);

            if (respuesta.equals("Administrador")){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MasterView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

            }if (respuesta.equals("Cliente")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

            }else{
                inicioError.setText("Usuario o contraseña incorrectos");
                inicioUsuario.clear();
                inicioContra.clear();
            }

        }
    }
}