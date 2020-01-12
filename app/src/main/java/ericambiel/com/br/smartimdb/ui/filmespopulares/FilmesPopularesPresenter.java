package ericambiel.com.br.smartimdb.ui.filmespopulares;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ericambiel.com.br.smartimdb.config.Keys;
import ericambiel.com.br.smartimdb.data.mapper.FilmesMapper;
import ericambiel.com.br.smartimdb.data.mapper.VideosFilmeMapper;
import ericambiel.com.br.smartimdb.data.network.RetrofitConfig;
import ericambiel.com.br.smartimdb.data.network.responseTMDB.MediaResult;
import ericambiel.com.br.smartimdb.data.network.responseTMDB.VideosResult;
import ericambiel.com.br.smartimdb.domain.Media;
import ericambiel.com.br.smartimdb.domain.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmesPopularesPresenter
        implements FilmePopularesContrato.PresenterFilmesPopulares {
    private FilmePopularesContrato.ViewFilmesPopulares viewFilmesPopulares;

    private List<Media> filmesList = new ArrayList<>();

    FilmesPopularesPresenter(FilmePopularesContrato.ViewFilmesPopulares viewFilmesPopulares) {
        this.viewFilmesPopulares = viewFilmesPopulares;
    }

    @Override
    public void getMedia() {
        //Chama endpoint atravéz do Retrofit
        RetrofitConfig
                .getInstanceTMDB()
                .getPopularMedia(Keys.KEY_TMDB) //Classe estática com de acesso Keys
                .enqueue(new Callback<MediaResult>() {
                    @Override
                    public void onResponse(@NotNull Call<MediaResult> call, @NotNull Response<MediaResult> response) {
                        // status code >= 200 e <300
                        if(response.isSuccessful()) {
                            filmesList = FilmesMapper.responseToDomain(Objects.requireNonNull(response.body()).getResultadosFilmes());
                            //Desacopla camada de domínio da camada de rede
                            viewFilmesPopulares.showMedia(filmesList);

                        } else {
                            viewFilmesPopulares.mostraErro(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<MediaResult> call, @NotNull Throwable t) {
                        viewFilmesPopulares.mostraErro(t.getMessage());
                    }
                });
    }

    @Override
    public void getVideos(Media media) {
            RetrofitConfig
                    .getInstanceTMDB()
                    .getMediaVideos(Integer.toString(media.getId()), Keys.KEY_TMDB)
                    .enqueue(new Callback<VideosResult>() {
                        @Override
                        public void onResponse(@NotNull Call<VideosResult> call, @NotNull Response<VideosResult> response) {
                            // status code >= 200 e <300
                            if (response.isSuccessful()) {
                                final List<Video> videoList = VideosFilmeMapper
                                        .responseToDomain(Objects
                                                .requireNonNull(response.body()).getResultadosVideos());

                                //Lista com Videos do media selecionado
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
