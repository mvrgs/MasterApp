package com.example.masterapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.masterapp.Main.in;
import static com.example.masterapp.Main.out;

public class EditarPlatillosController {

    @FXML
    private TextField ID;

    @FXML
    private TextField calorias;

    @FXML
    private TextField findPlate;

    @FXML
    private TextField nombre;

    @FXML
    private TextField precio;

    @FXML
    private TextField tiempo;
    @FXML
    private void buscar() throws IOException, ClassNotFoundException {
        out.writeObject("buscarPlatillo");
        out.writeObject(findPlate.getText());
        ID.setText((String) in.readObject());
        calorias.setText((String) in.readObject());
        nombre.setText((String) in.readObject());
        precio.setText((String) in.readObject());
        tiempo.setText((String) in.readObject());
    }
    @FXML
    private void edit() throws IOException, ClassNotFoundException{
        out.writeObject("editUser");
        out.writeObject(calorias.getText());
        out.writeObject(precio.getText());
        out.writeObject(nombre.getText());
        boolean ready = (boolean) in.readObject();
        if(ready){
            findPlate.setText("Editado con éxito");
        }else{
            findPlate.setText("Usuario no encontrado");
        }
    }
    @FXML
    private void regresar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MasterView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}