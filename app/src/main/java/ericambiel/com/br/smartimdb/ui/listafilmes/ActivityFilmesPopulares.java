package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;

import java.io.Serializable;
import java.util.List;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.model.Filme;
import ericambiel.com.br.smartimdb.ui.youtubeplayer.YoutubeFragment;

// Diferente da Activity a AppCompatActivity cria um mesmo
// padrão de layout para diferentes versões do android
public class ActivityFilmesPopulares extends AppCompatActivity
        implements ContratoFilme.ViewFilmesPopulares,
            AdapterFilmesPopulares.ItemFilmeClickListener {

    private static final String tAG = "MAIN_ACTIVITY";

    private AdapterFilmesPopulares filmesAdapter;

    private ContratoFilme.PresenterFilmesPopulares presenterFilmesPopulares;

    YouTubePlayer.OnInitializedListener mOnInitializerListerner;
    YouTubePlayerSupportFragmentX youTubePlayerSupportFragmentX;

    //Primeira vez que a Activity for criada passara por aqui
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_filme);
        configuraToolBar();
        configuraAdapter();
        presenterFilmesPopulares = new PresenterFilmesPopulares(this);
        presenterFilmesPopulares.obtemFilmesPopulares();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenterFilmesPopulares.destruirView();
    }

    @Override
    public void mostraFilmesPopulares(List<Filme> filmesList) {
       filmesAdapter.setFilmes(filmesList);
    }

    @Override
    public void mostraErro(String erro){
        Toast.makeText(this, "Erro: " + erro, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickItemFilme(Filme filme) {
        presenterFilmesPopulares.obtemVideos(filme);
    }

    @Override
    public void iniciaYoutubePlayer(List<String> keyVideoList) {
        Fragment youtubePlayer = new YoutubeFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("keyVideo", (Serializable) keyVideoList);
        youtubePlayer.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.youtube_fragment_layout, youtubePlayer)
                .addToBackStack(YoutubeFragment.Companion.getTag())
                .commit();
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
}

