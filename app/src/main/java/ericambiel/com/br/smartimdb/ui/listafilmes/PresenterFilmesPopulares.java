package ericambiel.com.br.smartimdb.ui.listafilmes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ericambiel.com.br.smartimdb.config.Keys;
import ericambiel.com.br.smartimdb.data.mapper.FilmesMapper;
import ericambiel.com.br.smartimdb.data.mapper.VideosFilmeMapper;
import ericambiel.com.br.smartimdb.data.model.Filme;
import ericambiel.com.br.smartimdb.data.model.Video;
import ericambiel.com.br.smartimdb.data.network.RetrofitConfig;
import ericambiel.com.br.smartimdb.data.network.responseTMDB.FilmesPopularesResult;
import ericambiel.com.br.smartimdb.data.network.responseTMDB.VideosResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterFilmesPopulares implements ContratoFilme.PresenterFilmesPopulares {
    private ContratoFilme.ViewFilmesPopulares viewFilmesPopulares;

    private List<Filme> filmesList = new ArrayList<>();

    PresenterFilmesPopulares(ContratoFilme.ViewFilmesPopulares viewFilmesPopulares) {
        this.viewFilmesPopulares = viewFilmesPopulares;
    }

    @Override
    public void obtemFilmesPopulares() {
        //Chama endpoint atravéz do Retrofit
        RetrofitConfig
                .getInstanceTMDB()
                .ObterFilmesPopulares(Keys.key_TMDB) //Classe estática com de acesso Keys
                .enqueue(new Callback<FilmesPopularesResult>() {
                    @Override
                    public void onResponse(@NotNull Call<FilmesPopularesResult> call, @NotNull Response<FilmesPopularesResult> response) {
                        // status code >= 200 e <300
                        if(response.isSuccessful()) {
                            filmesList = FilmesMapper.responseToDomain(Objects.requireNonNull(response.body()).getResultadosFilmes());
                            //Desacopla camada de domínio da camada de rede
                            viewFilmesPopulares.mostraFilmesPopulares(filmesList);

                        } else {
                            viewFilmesPopulares.mostraErro();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<FilmesPopularesResult> call, @NotNull Throwable t) {
                        viewFilmesPopulares.mostraErro();
                    }
                });
    }

    @Override
    public void obtemVideos(Filme filme) {
        //for(int i = 1; i <= filmesList.size(); i++)
            RetrofitConfig
                    .getInstanceTMDB()
                    .ObterVideosFilmes(Integer.toString(filmesList.get(1).getIdFilme()), Keys.key_TMDB)
                    .enqueue(new Callback<VideosResult>() {
                        @Override
                        public void onResponse(@NotNull Call<VideosResult> call, @NotNull Response<VideosResult> response) {
                            // status code >= 200 e <300
                            if (response.isSuccessful()) {
                                final List<Video> videoListList = VideosFilmeMapper
                                        .responseToDomain(Objects.requireNonNull(response.body()).getResultadosVideos());

                                //Desacopla camada de domínio da camada de rede
                                viewFilmesPopulares.iniciaYoutubePlayer(videoListList);
                            } else {
                                viewFilmesPopulares.mostraErro();
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<VideosResult> call, @NotNull Throwable t) {
                            viewFilmesPopulares.mostraErro();
                        }
                    });
    }

    @Override
    public void destruirView() {
        this.viewFilmesPopulares = null;
    }
}
