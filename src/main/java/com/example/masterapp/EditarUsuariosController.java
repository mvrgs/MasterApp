package com.example.masterapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.masterapp.Main.in;
import static com.example.masterapp.Main.out;

public class EditarUsuariosController {

    @FXML
    private TextField contra;

    @FXML
    private TextField findUsuario;

    @FXML
    private TextField roll;

    @FXML
    private TextField usuario;
    Boolean respuesta ;
    @FXML
    private void user() throws IOException, ClassNotFoundException {
        String nombre = findUsuario.getText();
        out.writeObject("validarUsuario");
        out.writeObject(nombre);
        respuesta = (Boolean) in.readObject();
        System.out.println(respuesta);
        if (respuesta){
            usuario.setText((String) in.readObject());
            contra.setText((String) in.readObject());
            roll.setText((String) in.readObject());
        }
        else{
            usuario.setText("Usuario no existe");
        }
    }
    @FXML
    private void edit() throws IOException, ClassNotFoundException{
        out.writeObject("editUser");
        out.writeObject(usuario.getText());
        out.writeObject(contra.getText());
        out.writeObject(roll.getText());
    }
}

