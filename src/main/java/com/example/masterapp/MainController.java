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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private List<Usuario> usuarios;

    private JSONArray lista = new JSONController().getLista();
    @FXML
    TextField inicioUsuario;
    @FXML
    TextField inicioContra;
    @FXML
    Button validarButton;
    @FXML
    Label inicioError;

    private static Usuario validarUsuario(String username, String password, List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    public static List<Usuario> leerArchivoXML(String rutaArchivo) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(archivo);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("usuario");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String username = eElement.getElementsByTagName("username").item(0).getTextContent();
                String password = eElement.getElementsByTagName("password").item(0).getTextContent();
                String rol = eElement.getElementsByTagName("rol").item(0).getTextContent();
                Usuario usuario = new Usuario();
                usuario.setUsername(username);
                usuario.setPassword(password);
                usuario.setRol(rol);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }



    @FXML
    private void iniciarSesion() throws Exception {
        List<Usuario> usuarios = leerArchivoXML("usuarios.xml");
        String username = inicioUsuario.getText();
        String password = inicioContra.getText();

        Usuario usuario = validarUsuario(username, password, usuarios);

        if (usuario != null) {
            inicioError.setText("Bienvenid@ " + username);
            if (usuario.getRol().equals("Administrador")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MasterView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            }
        } else {
            inicioError.setText("Usuario o contraseña incorrecto.");
            inicioUsuario.clear();
            inicioContra.clear();
        }
    }



}