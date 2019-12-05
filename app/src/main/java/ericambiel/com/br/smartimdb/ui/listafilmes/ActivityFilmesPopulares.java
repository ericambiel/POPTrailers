package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragmentX;

import java.util.List;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.model.Filme;
import ericambiel.com.br.smartimdb.ui.youtubeplayer.YoutubeFragment;

// Diferente da Activity a AppCompatActivity cria um mesmo
// padrão de layout para diferentes versões do android
public class ActivityFilmesPopulares extends AppCompatActivity
        implements ContratoFilme.ViewFilmesPopulares,
            AdapterFilmesPopulares.ItemFilmeClickListener {

    private static final String TAG = "MAIN_ACTIVITY";

    private AdapterFilmesPopulares filmesAdapter;
    private ContratoFilme.PresenterFilmesPopulares presenterFilmesPopulares;

    YouTubePlayer.OnInitializedListener mOnInitializerListerner;
    YouTubePlayerFragmentX youTubePlayerFragmentX;

    //Primeira vez que a Activity for criada passara por aqui
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.fragment_youtube);
//
//        youTubePlayerFragmentX = (YouTubePlayerFragmentX) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
//
//        mOnInitializerListerner = new YouTubePlayer.OnInitializedListener(){
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
//                if(wasRestored) {
//                    youTubePlayer.play();
//                } else {
//                    //youTubePlayer.cueVideo("pRj8x8M2iAI"); //Espera dar Play.
//                    youTubePlayer.loadVideo("pRj8x8M2iAI"); //Chama video direto.
//                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Log.d(TAG, "Failed to initialize");
//            }
//        };
//
//        youTubePlayerFragmentX.initialize("AIzaSyBwSvhhXHE_UBMHiI0kODGE5g5A6jG8jew", mOnInitializerListerner);

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
//        //Objeto necessário para iniciar outra activity.
//        Intent intent = new Intent(this, DetalhesFilmeActivity.class);
//        /* Passa dados para proxima tela pelo nome da Activity informado, no caso Filme implementa
//           classe Serializable para que possa ser enviado diretamente. */
//        intent.putExtra(DetalhesFilmeActivity.TAG, filme);
//        //Inicia outra Activity com o Intent criado.
//        startActivity(intent);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.youtube_fragment_layout, new YoutubeFragment())
                .addToBackStack("YOUTUBE_PLAYER")
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

