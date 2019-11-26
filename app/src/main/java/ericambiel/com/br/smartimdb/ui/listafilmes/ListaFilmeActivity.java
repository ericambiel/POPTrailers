package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.mapper.FilmesMapper;
import ericambiel.com.br.smartimdb.data.model.Filme;
import ericambiel.com.br.smartimdb.data.network.RetrofitApiService;
import ericambiel.com.br.smartimdb.data.network.response.FilmesPopularesResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Diferente da Activity a AppCompatActivity cria um mesmo
// padrão de layout para diferentes versões do android
public class ListaFilmeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaFilmesAdapter filmesAdapter;

    //Primeira vez que a Activity for criada passara por aqui
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filme);

        configuraToolBar();

        configuraAdapter();

        obtemFilmes();

    }

    private void configuraToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void configuraAdapter(){
        recyclerView = findViewById(R.id.recycler_filmes);

        filmesAdapter = new ListaFilmesAdapter();

        //Constroi LayoutManager
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //Seta LayoutManager
        recyclerView.setLayoutManager(gridLayoutManager);
        //Seta Adapter
        recyclerView.setAdapter(filmesAdapter);
    }

    private void obtemFilmes() {
        //Chama endpoint atravéz do Retrofit
        RetrofitApiService.getInstance()
                .ObterFilmesPopulares("313f36e207809621639e4fe85151294a")
                .enqueue(new Callback<FilmesPopularesResult>() {
                    @Override
                    public void onResponse(Call<FilmesPopularesResult> call, Response<FilmesPopularesResult> response) {
                        // status code >= 200 e <300
                        if(response.isSuccessful()) {
                            final List<Filme> filmeList = FilmesMapper
                                    .responseToDomain(response.body().getResultadosFilmes());

                            //Desacopla camada de domínio da camada de rede
                            filmesAdapter.setFilmes(filmeList);
                        } else {
                            mostraErro();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmesPopularesResult> call, Throwable t) {
                        mostraErro();
                    }
                });
    }

    private void mostraErro(){
        Toast.makeText(this, "Erro ao obter lista de filmes.", Toast.LENGTH_LONG).show();
    }
}
