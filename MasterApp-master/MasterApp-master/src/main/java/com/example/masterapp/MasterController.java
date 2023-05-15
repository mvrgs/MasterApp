package com.example.masterapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
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

    @FXML
    public void agregarAdmi(){
        if (agregarUsuario.getText().equals("")||agregarContra.getText().equals("")){
            agregarError.setText("Debe completar ambos espacios");
        }else {
            Usuario usuario = new Usuario();
            usuario.setUsername(agregarUsuario.getText());
            usuario.setPassword(agregarContra.getText());
            usuario.setRol("Administrador");

            try {
                agregarUsuarioAXML("usuarios.xml", usuario);
                agregarError.setText("Administrador agregado con éxito");
            } catch (Exception e) {
                agregarError.setText("Error al agregar el administrador");
            }

            agregarUsuario.clear();
            agregarContra.clear();
        }
    }



    public void agregarUsuarioAXML(String nombreArchivo, Usuario usuario) throws Exception {
        // Crear una fábrica de constructores de documentos
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

        // Leer el archivo XML existente
        Document doc = docBuilder.parse(new File(nombreArchivo));

        // Obtener la raíz del documento
        Element raiz = doc.getDocumentElement();

        // Crear un nuevo elemento para el usuario
        Element nuevoUsuario = doc.createElement("usuario");
        raiz.appendChild(nuevoUsuario);

        Element username = doc.createElement("username");
        username.appendChild(doc.createTextNode(usuario.getUsername()));
        nuevoUsuario.appendChild(username);

        Element password = doc.createElement("password");
        password.appendChild(doc.createTextNode(usuario.getPassword()));
        nuevoUsuario.appendChild(password);

        Element rol = doc.createElement("rol");
        rol.appendChild(doc.createTextNode(usuario.getRol()));
        nuevoUsuario.appendChild(rol);

        // Guardar los cambios en el archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(nombreArchivo));
        transformer.transform(source, result);
    }

 /*


    @FXML
    public void agregarAdmi(){
        if (agregarUsuario.getText().equals("")||agregarContra.getText().equals("")){
            agregarError.setText("Debe completar ambos espacios");
        }else {
            Usuario usuario= new Usuario();
            usuario.setUsername(agregarUsuario.getText());
            usuario.setPassword(agregarContra.getText());
            usuario.setRol("Administrador");

            // Agregar el usuario a la lista de usuarios
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(usuario);

            // Crear el archivo XML
            try {
                crearArchivoXML(usuarios);
                agregarError.setText("Administrador agregado con exito");
            } catch (Exception e) {
                agregarError.setText("Error al crear archivo XML");
            }

            agregarUsuario.clear();
            agregarContra.clear();
        }
    }


    public static void crearArchivoXML(List<Usuario> usuarios) throws Exception {
        // Crear un nuevo documento XML
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
        StreamResult result = new StreamResult(new File("clientes.xml"));
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
