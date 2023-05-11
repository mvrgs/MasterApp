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
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase controller principal
 */
public class MainController {
    /**
     * Declaración de objetos de scene builder
     */
    @FXML
    TextField inicioUsuario;
    @FXML
    TextField inicioContra;
    @FXML
    Button validarButton;
    @FXML
    Label inicioError;
    /**
     * Creación de parboles
     */
    private ArbolBinarioBusqueda arbolUsuarios = new ArbolBinarioBusqueda();
    private ArbolBinarioBusqueda arbolClientes = new ArbolBinarioBusqueda();
    private ArbolAVL arbolPlatillos = new ArbolAVL();


    /**
     * Función initialize (Da argumentos cada vez que la aplicación se inicialice
     */
    @FXML
    public void initialize() {
        cargarPlatillos("platillos.json");
        cargarUsuarios("usuarios.xml");
        cargarClientes("clientes.xml");
    }

    /**
     * Función para cargar los platillos del menú
     * @param nombreArchivo
     */
    private void cargarPlatillos(String nombreArchivo) {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(nombreArchivo);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Platillos platillo = new Platillos();
                platillo.setNombre((String) jsonObject.get("nombre"));
                platillo.setCalorias((Integer) jsonObject.get("calorias"));
                platillo.setTiempo((Integer) jsonObject.get("tiempo"));
                platillo.setPrecio((Integer) jsonObject.get("precio"));
                arbolPlatillos.insert(platillo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Función para cargar los usuarios existentes
     * @param nombreArchivo
     */
    private void cargarUsuarios (String nombreArchivo) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(nombreArchivo));
            doc.getDocumentElement().normalize();
            NodeList listaUsuarios = doc.getElementsByTagName("usuario");

            for (int i = 0; i < listaUsuarios.getLength(); i++) {
                Node nodoUsuario = listaUsuarios.item(i);

                if (nodoUsuario.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoUsuario = (Element) nodoUsuario;
                    String username = elementoUsuario.getElementsByTagName("username").item(0).getTextContent();
                    String password = elementoUsuario.getElementsByTagName("password").item(0).getTextContent();
                    String rol = elementoUsuario.getElementsByTagName("rol").item(0).getTextContent();
                    Usuario usuario = new Usuario(username, password, rol);
                    arbolUsuarios.insertar(usuario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Función para cargar los clientes existentes
     * @param nombreArchivo
     */
    private void cargarClientes (String nombreArchivo) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(nombreArchivo));
            doc.getDocumentElement().normalize();
            NodeList listaUsuarios = doc.getElementsByTagName("usuario");

            for (int i = 0; i < listaUsuarios.getLength(); i++) {
                Node nodoUsuario = listaUsuarios.item(i);

                if (nodoUsuario.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoUsuario = (Element) nodoUsuario;
                    String username = elementoUsuario.getElementsByTagName("username").item(0).getTextContent();
                    String password = elementoUsuario.getElementsByTagName("password").item(0).getTextContent();
                    String rol = elementoUsuario.getElementsByTagName("rol").item(0).getTextContent();
                    Usuario usuario = new Usuario(username, password, rol);
                    arbolClientes.insertar(usuario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Función para iniciar sesión de una cuenta
     * @throws Exception
     */
    @FXML
    private void iniciarSesion() throws Exception {
        String username = inicioUsuario.getText();
        String password = inicioContra.getText();

        if (username.isEmpty() || password.isEmpty()) {
            inicioError.setText("Ingrese ambos datos");
        } else {
            // Buscar el usuario en ambos árboles
            Usuario usuario = arbolUsuarios.buscar(username, password);
            if (usuario == null) {
                usuario = arbolClientes.buscar(username, password);
            }

            // Validar en qué árbol se encuentra el usuario y abrir la ventana correspondiente
            if (usuario != null) {
                inicioError.setText("Bienvenid@ " + usuario.getUsername());
                if (arbolUsuarios.buscar(username,password) != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MasterView.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();

                } else if (arbolClientes.buscar(username,password) != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                }
            } else {
                inicioError.setText("Usuario o contraseña incorrectos");
                inicioUsuario.clear();
                inicioContra.clear();
            }
        }
    }
}