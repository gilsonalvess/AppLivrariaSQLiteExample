package com.dwm.ufg.applivrariasqliteexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ItemLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_livro);

        final EditText titulo = (EditText) findViewById(R.id.id_titulo);
        final EditText autor = (EditText) findViewById(R.id.id_autor);
        final EditText editora = (EditText) findViewById(R.id.id_editora_item);
        final TextView tituloTela = (TextView) findViewById(R.id.id_title_item_livro);
        final Button btnCadastro = (Button) findViewById(R.id.id_button);

        final Livro livro = (Livro) getIntent().getSerializableExtra("livro");

        if(livro != null){
            titulo.setText(livro.getTitulo());
            autor.setText(livro.getAutor());
            editora.setText(livro.getEditora());
            btnCadastro.setText("Editar");
            tituloTela.setText("Editar Livro");
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Cadastro");
        }

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivrariaDAO crud = new LivrariaDAO(getBaseContext());

                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String editoraString = editora.getText().toString();

                if(tituloString.equals("") || autorString.equals("") || editoraString.equals("")){
                    Toast.makeText(ItemLivroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                String resultado;

                if(btnCadastro.getText().equals("Editar") && livro != null){
                    livro.setTitulo(tituloString);
                    livro.setAutor(autorString);
                    livro.setEditora(editoraString);
                    resultado = crud.atualizaDados(livro);
                }else{
                    resultado = crud.insereDados(tituloString, autorString, editoraString);
                }

                Toast.makeText(ItemLivroActivity.this, resultado, Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
