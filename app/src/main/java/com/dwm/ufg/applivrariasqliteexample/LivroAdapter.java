package com.dwm.ufg.applivrariasqliteexample;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LivroAdapter extends BaseAdapter {

    private final ArrayList<Livro> livros;
    private final Activity activity;

    public LivroAdapter(ArrayList<Livro> livros, Activity activity) {
        this.livros = livros;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Object getItem(int position) {
        return livros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = activity.getLayoutInflater()
                .inflate(R.layout.item_lista, parent, false);

        Livro livro = livros.get(position);
       // TODO adionar atributo imagem
        TextView titulo = (TextView) rowView.findViewById(R.id.id_titulo_item);
        TextView autor = (TextView) rowView.findViewById(R.id.id_autor_item);

        titulo.setText(livro.getTitulo());
        autor.setText(livro.getAutor());

        return rowView;
    }
}
