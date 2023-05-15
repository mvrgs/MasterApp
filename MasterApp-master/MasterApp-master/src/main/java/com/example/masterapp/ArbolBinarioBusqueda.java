package com.example.masterapp;

/**
 * Clase para el árbol binario de búsqueda
 */
public class ArbolBinarioBusqueda {
    private NodoArbol raiz;

    /**
     * Constructor
     */
    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    /**
     * Inserta un usuario
     * @param usuario
     */
    public void insertar(Usuario usuario) {
        if (raiz == null) {
            raiz = new NodoArbol(usuario);
        } else {
            insertar(raiz, usuario);
        }
    }
    /**
     * Inserta un usuario y un nodo
     * @param usuario
     */
    private void insertar(NodoArbol nodo, Usuario usuario) {
        if (nodo.usuario.getUsername().compareTo(usuario.getUsername()) > 0) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoArbol(usuario);
            } else {
                insertar(nodo.izquierdo, usuario);
            }
        } else if (nodo.usuario.getUsername().compareTo(usuario.getUsername()) < 0) {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoArbol(usuario);
            } else {
                insertar(nodo.derecho, usuario);
            }
        }
    }

    /**
     * Función para buscar un usuario en el árbol
     * @param username
     * @param password
     * @return Usuario
     */
    public Usuario buscar(String username, String password) {
        return buscar(raiz, new Usuario(username, password, null));
    }

    /**
     * Función auxiliar para la función buscar
     * @param nodo
     * @param usuario
     * @return un usuario
     */
    private Usuario buscar(NodoArbol nodo, Usuario usuario) {
        if (nodo == null) {
            return null;
        }

        if (nodo.usuario.getUsername().equals(usuario.getUsername()) &&
                nodo.usuario.getPassword().equals(usuario.getPassword())) {
            return nodo.usuario;
        }

        if (nodo.usuario.getUsername().compareTo(usuario.getUsername()) > 0) {
            return buscar(nodo.izquierdo, usuario);
        } else {
            return buscar(nodo.derecho, usuario);
        }
    }

    /**
     * Clase nodo para el árbol binario de búsqueda
     */
    private class NodoArbol {
        private Usuario usuario;
        private NodoArbol izquierdo;
        private NodoArbol derecho;

        public NodoArbol(Usuario usuario) {
            this.usuario = usuario;
            this.izquierdo = null;
            this.derecho = null;
        }
    }
}