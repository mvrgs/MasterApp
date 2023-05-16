package com.example.masterapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private Label muchoTexto;
    Boolean respuesta ;

    /**
     * Función que busca el usuario a editar o eliminar mediante sockets
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * Función que toma los parámetros y los edita mediante sockets
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    private void edit() throws IOException, ClassNotFoundException{
        out.writeObject("editUser");
        out.writeObject(usuario.getText());
        out.writeObject(contra.getText());
        out.writeObject(roll.getText());
        boolean ready = (boolean) in.readObject();
        if(ready){
            muchoTexto.setText("Editado con éxito");
        }else{
            muchoTexto.setText("Usuario no encontrado");
        }
    }

    /**
     * Función para eliminar usuarios mediante sockets
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    private void delete() throws IOException, ClassNotFoundException {
        out.writeObject("deleteUser");
        //out.writeObject(findUsuario.getText());
        boolean ready = (boolean) in.readObject();
        if(ready){
            muchoTexto.setText("Eliminado con éxito");
        }else{
            muchoTexto.setText("Usuario no encontrado");
        }
    }

    /**
     * Función para regresar a la ventana principal
     * @throws IOException
     */
    @FXML
    private void atras() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MasterView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}

