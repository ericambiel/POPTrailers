package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.model.Filme;
import ericambiel.com.br.smartimdb.ui.detalhesfilme.DetalhesFilmeActivity;

// Diferente da Activity a AppCompatActivity cria um mesmo
// padrão de layout para diferentes versões do android
public class ActivityFilmesPopulares extends AppCompatActivity
        implements ContratoFilme.ViewFilmesPopulares,
        AdapterFilmesPopulares.ItemFilmeClickListener{

    private AdapterFilmesPopulares filmesAdapter;
    private ContratoFilme.PresenterFilmesPopulares presenterFilmesPopulares;

    //Primeira vez que a Activity for criada passara por aqui
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filme);

        configuraToolBar();

        configuraAdapter();

        presenterFilmesPopulares = new PresenterFilmesPopulares(this);

        presenterFilmesPopulares.obtemFilmes();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenterFilmesPopulares.destruirView();
    }

    private void configuraToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void configuraAdapter(){
        final RecyclerView recyclerView = findViewById(R.id.recycler_filmes);

        filmesAdapter = new AdapterFilmesPopulares(this);

        //Constroi LayoutManager
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //Seta LayoutManager
        recyclerView.setLayoutManager(gridLayoutManager);
        //Seta Adapter
        recyclerView.setAdapter(filmesAdapter);
    }

    @Override
    public void mostraFilmesPopulares(List<Filme> filmesList) {
       filmesAdapter.setFilmes(filmesList);
    }

    @Override
    public void mostraErro(){
        Toast.makeText(this, "Erro ao obter lista de filmes.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickItemFilme(Filme filme) {
        //Objeto necessário para iniciar outra activity.
        Intent intent = new Intent(this, DetalhesFilmeActivity.class);
        /* Passa dados para proxima tela pelo nome da Activity informado, no caso Filme implementa
           classe Serializable para que possa ser enviado diretamente. */
        intent.putExtra(DetalhesFilmeActivity.TAG, filme);
        //Inicia outra Activity com o Intent criado.
        startActivity(intent);
    }
}
