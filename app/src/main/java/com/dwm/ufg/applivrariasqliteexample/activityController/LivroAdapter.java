package com.dwm.ufg.applivrariasqliteexample.activityController;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwm.ufg.applivrariasqliteexample.R;
import com.dwm.ufg.applivrariasqliteexample.model.Livro;

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
        TextView editora = (TextView) rowView.findViewById(R.id.id_editora_item);
        TextView valor = (TextView) rowView.findViewById(R.id.id_preco_livro);
        ImageView imagem = (ImageView) rowView.findViewById(R.id.id_img_livro);

        titulo.setText(livro.getTitulo());
        autor.setText(livro.getAutor());
        editora.setText(livro.getEditora());
        valor.setText(String.valueOf(livro.getValor()));

        if (livro.getImagem() != null) {
            int idImagem = activity.getResources().getIdentifier(livro.getImagem(), "drawable", activity.getPackageName());
            imagem.setImageResource(idImagem);
        }else{
            imagem.setImageResource(R.drawable.icon_livro_capa);
        }

        return rowView;
    }
}
