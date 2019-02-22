package com.dwm.ufg.applivrariasqliteexample.activityController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dwm.ufg.applivrariasqliteexample.R;

public class ListViewLivrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_livros);

        ListView lista = (ListView) findViewById(R.id.id_lista);

    }

}
