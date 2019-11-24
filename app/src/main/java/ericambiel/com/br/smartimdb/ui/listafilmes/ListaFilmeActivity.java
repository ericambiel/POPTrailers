package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.network.RetrofitApiService;
import ericambiel.com.br.smartimdb.data.network.response.FilmesPopularesResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Diferente da Activity a AppCompatActivity cria um mesmo
// padrão de layout para diferentes versões do android
public class ListaFilmeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    //Primeira vez que a Activity for criada passara por aqui
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filme);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_filmes);

        //Chama endpoint atravéz do Retrofit
        RetrofitApiService.getInstance()
                .ObterFilmesPopulares("313f36e207809621639e4fe85151294a")
                .enqueue(new Callback<FilmesPopularesResult>() {
                    @Override
                    public void onResponse(Call<FilmesPopularesResult> call, Response<FilmesPopularesResult>response) {
                        // status code >= 200 e <300
                        if(response.isSuccessful()) {
                            //Constroi LayoutManager
                            RecyclerView.LayoutManager liLayoutManager = new LinearLayoutManager(ListaFilmeActivity.this);
                            //Seta LayoutManager
                            recyclerView.setLayoutManager(liLayoutManager);
                            //Seta Adapter
                            recyclerView.setAdapter(new ListaFilmesAdapter(response.body().getResultadosFilmes()));
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmesPopularesResult> call, Throwable t) {

                    }
                });
    }
}
