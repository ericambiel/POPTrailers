package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ericambiel.com.br.smartimdb.R;

//Diferente da Activity a AppCompatActivity cria um mesmo
// padrão de layout para diferentes versões do android
public class ListaFilmeActivity extends AppCompatActivity {
    //Primeira vez que a Activity for criada passara por aqui
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filme);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_filmes);

        recyclerView.LayoutManager liLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(liLayoutManager);
        recyclerView.setAdapter();
    }
}
