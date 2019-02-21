package com.dwm.ufg.applivrariasqliteexample;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_livros);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("App Livraria");
        }
        carregaLista();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar(this);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    protected void cadastrar (View.OnClickListener view){
        Intent intent = new Intent(this, ItemLivroActivity.class);
        startActivity(intent);
    }

    protected void editar(View view, int position, ArrayList<Livro> listaDelivros){
        Intent intent = new Intent(this, ItemLivroActivity.class);
        intent.putExtra("livro", listaDelivros.get(position));
        startActivity(intent);
    }

    protected void carregaLista(){
        LivrariaDAO livrariaDAO = new LivrariaDAO(this);

        final ArrayList<Livro> listaDelivros = livrariaDAO.carregarDados();
        final LivroAdapter adapter = new LivroAdapter(listaDelivros, this);
        final ListView listView = (ListView) findViewById(R.id.id_lista);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editar(view, position, listaDelivros);
            }
        });
    }
}
