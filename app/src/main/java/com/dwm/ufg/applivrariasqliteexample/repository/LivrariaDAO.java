package com.dwm.ufg.applivrariasqliteexample.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dwm.ufg.applivrariasqliteexample.model.Livro;

import java.util.ArrayList;

public class LivrariaDAO {

    private SQLiteDatabase db;
    private CreateDB banco;

    public LivrariaDAO(Context context) {
        banco = new CreateDB(context);
    }

    //Insere dados
    public String insereDados(String titulo, String autor, String editora, double valor, String imagem) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CreateDB.TITULO, titulo);
        valores.put(CreateDB.AUTOR, autor);
        valores.put(CreateDB.EDITORA, editora);
        valores.put(CreateDB.VALOR, valor);
        valores.put(CreateDB.IMAGEM, imagem);

        resultado = db.insert(CreateDB.TABELA, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else
            return "Registro inserido com sucesso";
    }

    //Carrega dados
    public ArrayList<Livro> carregarDados() {

        db = banco.getWritableDatabase();

        ArrayList<Livro> listaDeLivros = new ArrayList<Livro>();

        String selectQuery = "SELECT * FROM " + CreateDB.TABELA;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Livro livro = new Livro();
                livro.set_id(String.valueOf(cursor.getInt(0)));
                livro.setTitulo(cursor.getString(1));
                livro.setAutor(cursor.getString(2));
                livro.setEditora(cursor.getString(3));
                livro.setValor(cursor.getDouble(4));
                livro.setImagem(cursor.getString(5));
                listaDeLivros.add(livro);

            } while (cursor.moveToNext());
        }
        db.close();
        return listaDeLivros;
    }

    //Recupera pelo id
    public Livro recuperaPeloId(int id) {

        db = banco.getWritableDatabase();

        Cursor cursor = db.query(CreateDB.TABELA, new String[]{CreateDB.ID,
                        CreateDB.TITULO, CreateDB.AUTOR, CreateDB.EDITORA},
                CreateDB.ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        Livro livro = null;

        db.close();

        if (cursor != null) {
            cursor.moveToFirst();
            livro = new Livro();
            livro.setTitulo(cursor.getString(1));
            livro.setAutor(cursor.getString(2));
            livro.setEditora(cursor.getString(3));

            return livro;

        } else {
            throw new RuntimeException("Não existe");
        }

    }

    //Atualiza dados
    public String atualizaDados(Livro livro) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CreateDB.ID, livro.get_id());
        valores.put(CreateDB.TITULO, livro.getTitulo());
        valores.put(CreateDB.AUTOR, livro.getAutor());
        valores.put(CreateDB.EDITORA, livro.getEditora());
        valores.put(CreateDB.VALOR, livro.getValor());
        valores.put(CreateDB.IMAGEM, livro.getImagem());

        // atualiza linha
        resultado = db.update(CreateDB.TABELA, valores,
                CreateDB.ID + " = ?",
                new String[]{String.valueOf(livro.get_id())});

        if (resultado == -1) {
            return "Erro ao atualizar cadastro";
        } else
            return "Cadastro atualizado com sucesso";

    }

    public void delete(Livro livro) {
        db = banco.getWritableDatabase();
        db.delete(CreateDB.TABELA, CreateDB.ID + " = ?",
                new String[]{String.valueOf(livro.get_id())});
        db.close();
    }

}
