package com.example.masterapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MasterController {

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
    private JSONController jsonController = new JSONController();



    /*
    PRUEBAS :)
    @FXML
    public void agregarAdmi(){
        if (agregarUsuario.getText().equals("")||agregarContra.getText().equals("")){
            agregarError.setText("Debe completar ambos espacios");
        }else {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new File("usuarios.xml"));

                Element root = doc.getDocumentElement();
                Element usuario = doc.createElement("Usuario");

                Element username = doc.createElement("Username");
                username.setTextContent(agregarUsuario.getText());
                usuario.appendChild(username);

                Element password = doc.createElement("Password");
                password.setTextContent(agregarContra.getText());
                usuario.appendChild(password);

                Element rol = doc.createElement("Rol");
                rol.setTextContent("Administrador");
                usuario.appendChild(rol);

                root.appendChild(usuario);

                agregarUsuario.clear();
                agregarContra.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void crearArchivoXML(List<Usuario> usuarios) throws Exception {
        Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        // Crear el elemento raíz
        Element raiz = documento.createElement("usuarios");
        documento.appendChild(raiz);

        // Agregar cada usuario al documento
        for (Usuario u : usuarios) {
            Element usuario = documento.createElement("usuario");
            raiz.appendChild(usuario);

            Element username = documento.createElement("username");
            username.appendChild(documento.createTextNode(u.getUsername()));
            usuario.appendChild(username);

            Element password = documento.createElement("password");
            password.appendChild(documento.createTextNode(u.getPassword()));
            usuario.appendChild(password);

            Element rol = documento.createElement("rol");
            rol.appendChild(documento.createTextNode(u.getRol()));
            usuario.appendChild(rol);
        }

        // Guardar el documento en un archivo XML
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(new File("usuarios.xml"));
        transformer.transform(source, result);
    }

     */

    @FXML
    private void agregarPlatillo(){
        if (platilloNombre.getText().equals("")||platilloCalorias.getText().equals("")||platilloTiempo.getText().equals("")||platilloPrecio.getText().equals("")){
            platilloError.setText("Debe completar todos los espacio");
        }else {
            platilloError.setText("Platillo agregado con exito");
            Platillos platillos = new Platillos();
            platillos.setNombre(platilloNombre.getText());
            platillos.setCalorias(Integer.valueOf(platilloCalorias.getText()));
            platillos.setTiempo(Integer.valueOf(platilloTiempo.getText()));
            platillos.setPrecio(Integer.valueOf(platilloPrecio.getText()));

            crearCarpetaPlatillo(platillos.getNombre());

            this.jsonController.saveJson(platillos.getNombre(), platillos.getCalorias(), platillos.getTiempo(), platillos.getPrecio());

        }
    }

    private void crearCarpetaPlatillo(String nombre){
        File directorio = new File("Platillos\\" + nombre);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }



}
