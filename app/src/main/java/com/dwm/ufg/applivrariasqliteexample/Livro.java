package com.dwm.ufg.applivrariasqliteexample;

import java.io.Serializable;

public class Livro implements Serializable {

    String _id;
    String titulo;
    String autor;
    String editora;
    String imagem;

    public Livro() {
    }

    public Livro(String _id, String titulo, String autor, String editora) {
        this._id = _id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
