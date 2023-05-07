package com.example.masterapp;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    /*
    public static List<Usuario> parse(String xmlFilePath) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();

        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("usuario");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String username = eElement.getElementsByTagName("username").item(0).getTextContent();
                String password = eElement.getElementsByTagName("password").item(0).getTextContent();
                String rol = eElement.getElementsByTagName("rol").item(0).getTextContent();

                Usuario usuario = new Usuario(username, password, rol);
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

     */
}
