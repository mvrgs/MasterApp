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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class MainController {

    private JSONArray lista = new JSONController().getLista();
    @FXML
    TextField inicioUsuario;
    @FXML
    TextField inicioContra;
    @FXML
    Button validarButton;
    @FXML
    Label inicioError;

    @FXML
    private void iniciarSesion() throws IOException {
        Boolean exist = false;
        for(int i = 0; i < lista.size(); i++){
            JSONObject userInfo = (JSONObject) lista.get(i);
            if(inicioUsuario.getText().equals(userInfo.get("usuario"))){
                if(inicioContra.getText().equals(userInfo.get("password"))){
                    inicioError.setText("Bienvenid@ "+ inicioUsuario.getText());
                    exist = true;
                    if (userInfo.get("rol").equals("Administrador")){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MasterView.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();
                    }else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();
                    }
                    break;
                }
            }
        }
        if(!exist){
            inicioError.setText("Usuario o contraseña incorrecto.");
            inicioUsuario.clear();
            inicioContra.clear();
        }
    }



}