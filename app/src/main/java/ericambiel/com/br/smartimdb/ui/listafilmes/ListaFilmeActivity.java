package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.model.Filme;

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

        RecyclerView.LayoutManager liLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(liLayoutManager);
        recyclerView.setAdapter(new ListaFilmesAdapter(criaFilmes()));
    }

    private List<Filme> criaFilmes() {
        return Arrays.asList(
                new Filme("Coração de Ferro"),
                new Filme("Coração de Ferro"),
                new Filme("Coração de Ferro"),
                new Filme("Coração de Ferro")
        );
    }
}
