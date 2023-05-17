package com.example.masterapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.masterapp.Main.out;

public class MasterController implements Initializable {

    @FXML
    TextField agregarUsuario;
    @FXML
    TextField agregarContra;
    @FXML
    TextField platilloNombre;
    @FXML
    TextField platilloCalorias;
    @FXML
    TextField platilloTiempo;
    @FXML
    TextField platilloPrecio;
    @FXML
    Label platilloError;
    @FXML
    Button agregarButton;
    @FXML
    Label agregarError;
    @FXML
    static Label nombreUsuario;
    @FXML
    private ListView<String> myListView;

    String[] usuarios = {};

    @FXML
    public void agregarAdmi() throws IOException {
        if (agregarUsuario.getText().equals("")||agregarContra.getText().equals("")){
            agregarError.setText("Debe completar ambos espacios");
        }else {
            String newUsuario = agregarUsuario.getText();
            String newPassword = agregarContra.getText();

            out.writeObject("agregarAdmin");
            out.writeObject(newUsuario);
            out.writeObject(newPassword);


            agregarUsuario.clear();
            agregarContra.clear();
        }
    }
    public void editarAdmin() throws IOException, ClassNotFoundException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarUsuarios.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }


    /**
     * Metodo que envia la clave y los datos al server para ejecutar el metodo y agregar el nuevo platillo.
     * @throws IOException
     */
    @FXML
    private void agregarPlatillo() throws IOException {
        if (platilloNombre.getText().equals("")||platilloCalorias.getText().equals("")||platilloTiempo.getText().equals("")||platilloPrecio.getText().equals("")){
            platilloError.setText("Debe completar todos los espacio");
        }else {
            String newNombre = platilloNombre.getText();
            Integer newCalorias = Integer.valueOf(platilloCalorias.getText());
            Integer newTiempo = Integer.valueOf(platilloTiempo.getText());
            Integer newPrecio = Integer.valueOf(platilloPrecio.getText());

            platilloError.setText("Platillo agregado con exito");
            out.writeObject("agregarPlatillo");
            out.writeObject(newNombre);
            out.writeObject(newCalorias);
            out.writeObject(newTiempo);
            out.writeObject(newPrecio);


            platilloNombre.clear();
            platilloCalorias.clear();
            platilloTiempo.clear();
            platilloPrecio.clear();

        }
    }

    @FXML
    private void EditarPlatillos()  {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(usuarios);
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            }
        });
    }
    }




