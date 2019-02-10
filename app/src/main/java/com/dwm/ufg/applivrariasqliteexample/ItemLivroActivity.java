package com.dwm.ufg.applivrariasqliteexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.function.ToDoubleBiFunction;

public class ItemLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_livro);

        final EditText titulo = (EditText) findViewById(R.id.id_titulo);
        final EditText autor = (EditText) findViewById(R.id.id_autor);
        final EditText editora = (EditText) findViewById(R.id.id_editora);
        final Button btnCadastro = (Button) findViewById(R.id.id_button);

        final Livro livro = (Livro) getIntent().getSerializableExtra("livro");

        if(livro != null){
            titulo.setText(livro.getTitulo());
            autor.setText(livro.getAutor());
            editora.setText(livro.getEditora());
            btnCadastro.setText("Editar");
        }

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivrariaDAO crud = new LivrariaDAO(getBaseContext());

                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String editoraoString = editora.getText().toString();

                if(tituloString.equals("") || autorString.equals("") || editoraoString.equals("")){
                    Toast.makeText(ItemLivroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                String resultado;

                if(btnCadastro.getText().equals("Editar") && livro != null){
                    livro.setTitulo(tituloString);
                    livro.setAutor(autorString);
                    livro.setEditora(editoraoString);
                    resultado = crud.atualizaDados(livro);
                }else{
                    resultado = crud.insereDados(tituloString, autorString, editoraoString);
                }

                Toast.makeText(ItemLivroActivity.this, resultado, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
