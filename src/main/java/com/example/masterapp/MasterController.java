package com.example.masterapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MasterController {

    private List<Users> usuarios = new ArrayList<Users>();
    private JSONArray lista = new JSONController().getLista();
    @FXML
    TextField agregarUsuario;
    @FXML
    TextField agregarContra;
    @FXML
    Button agregarButton;
    @FXML
    Label agregarError;
    private JSONController jsonController = new JSONController();

    @FXML
    public void agregarAdmi(){
        if (agregarUsuario.getText().equals("")||agregarContra.getText().equals("")|| agregarUsuario.getText().equals("") && agregarContra.getText().equals("")){
            agregarError.setText("Debe completar ambos espacios");
        }else {
            agregarError.setText("Administrador agregado con exito");
            Users user = new Users();
            user.setUsuario(agregarUsuario.getText());
            user.setPassword(agregarContra.getText());
            user.setRol("Administrador");

            crearCarpetaUsuario(user.getUsuario());
            this.jsonController.saveJson(user.getUsuario(), user.getPassword(), user.getRol());

            agregarUsuario.clear();
            agregarContra.clear();
        }
    }

    private void crearCarpetaUsuario(String user){
        File directorio = new File("Users\\" + user);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }



}
