package ericambiel.com.br.smartimdb.ui.filmespopulares;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ericambiel.com.br.smartimdb.config.Keys;
import ericambiel.com.br.smartimdb.data.mapper.FilmesMapper;
import ericambiel.com.br.smartimdb.data.mapper.VideosFilmeMapper;
import ericambiel.com.br.smartimdb.data.network.RetrofitConfig;
import ericambiel.com.br.smartimdb.data.network.responseTMDB.FilmesPopularesResult;
import ericambiel.com.br.smartimdb.data.network.responseTMDB.VideosResult;
import ericambiel.com.br.smartimdb.domain.Filme;
import ericambiel.com.br.smartimdb.domain.Video;
import ericambiel.com.br.smartimdb.ui.FilmeContrato;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmesPopularesPresenter implements FilmeContrato.PresenterFilmesPopulares {
    private FilmeContrato.ViewFilmesPopulares viewFilmesPopulares;

    private List<Filme> filmesList = new ArrayList<>();

    FilmesPopularesPresenter(FilmeContrato.ViewFilmesPopulares viewFilmesPopulares) {
        this.viewFilmesPopulares = viewFilmesPopulares;
    }

    @Override
    public void obtemFilmesPopulares() {
        //Chama endpoint atravéz do Retrofit
        RetrofitConfig
                .getInstanceTMDB()
                .ObterFilmesPopulares(Keys.KEY_TMDB) //Classe estática com de acesso Keys
                .enqueue(new Callback<FilmesPopularesResult>() {
                    @Override
                    public void onResponse(@NotNull Call<FilmesPopularesResult> call, @NotNull Response<FilmesPopularesResult> response) {
                        // status code >= 200 e <300
                        if(response.isSuccessful()) {
                            filmesList = FilmesMapper.responseToDomain(Objects.requireNonNull(response.body()).getResultadosFilmes());
                            //Desacopla camada de domínio da camada de rede
                            viewFilmesPopulares.mostraFilmesPopulares(filmesList);

                        } else {
                            viewFilmesPopulares.mostraErro(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<FilmesPopularesResult> call, @NotNull Throwable t) {
                        viewFilmesPopulares.mostraErro(t.getMessage());
                    }
                });
    }

    @Override
    public void obtemVideos(Filme filme) {
            RetrofitConfig
                    .getInstanceTMDB()
                    .ObterVideosFilmes(Integer.toString(filme.getIdFilme()), Keys.KEY_TMDB)
                    .enqueue(new Callback<VideosResult>() {
                        @Override
                        public void onResponse(@NotNull Call<VideosResult> call, @NotNull Response<VideosResult> response) {
                            // status code >= 200 e <300
                            if (response.isSuccessful()) {
                                final List<Video> videoList = VideosFilmeMapper
                                        .responseToDomain(Objects
                                                .requireNonNull(response.body()).getResultadosVideos());

                                //Lista com Videos do filme selecionado
                                List<String> videoKey = new ArrayList<>();
                                for (int i = 0; i < videoList.size(); i++ )
                                    videoKey.add(videoList.get(i).getKeyVideo());

                                //Desacopla camada de domínio da camada de rede
                                viewFilmesPopulares.iniciaYoutubePlayer(videoKey);
                            } else {
                                viewFilmesPopulares.mostraErro(response.message());
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<VideosResult> call, @NotNull Throwable t) {
                            viewFilmesPopulares.mostraErro(t.getMessage());
                        }
                    });
    }

    @Override
    public void destruirView() {
        this.viewFilmesPopulares = null;
    }
}
