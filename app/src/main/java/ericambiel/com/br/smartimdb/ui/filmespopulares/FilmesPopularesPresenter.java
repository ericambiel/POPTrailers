package ericambiel.com.br.smartimdb.ui.filmespopulares;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ericambiel.com.br.smartimdb.config.Keys;
import ericambiel.com.br.smartimdb.data.mapper.MediaMapper;
import ericambiel.com.br.smartimdb.data.mapper.VideosFilmeMapper;
import ericambiel.com.br.smartimdb.data.network.RetrofitConfig;
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.MediaResult;
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.VideosResult;
import ericambiel.com.br.smartimdb.domain.Media;
import ericambiel.com.br.smartimdb.domain.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmesPopularesPresenter
        implements FilmesPopularesContrato.Presenter {
    private FilmesPopularesContrato.View view;

    private List<Media> mediaList = new ArrayList<>();

    FilmesPopularesPresenter(FilmesPopularesContrato.View view) {
        this.view = view;
    }

    @Override
    public void getMedia() {
        //Chama endpoint atravéz do Retrofit
        RetrofitConfig
                .getInstanceTMDB()
                .getPopularMedia(Keys.KEY_TMDB) //Classe estática com de acesso Keys
                .enqueue(new Callback<MediaResult>() {
                    @Override
                    public void onResponse(@NotNull Call<MediaResult> call,
                                           @NotNull Response<MediaResult> response) {
                        // status code >= 200 e <300
                        if(response.isSuccessful()) {
                            mediaList = MediaMapper.responseToDomain(Objects
                                    .requireNonNull(response.body())
                                    .getMediaResult());
                            //Desacopla camada de domínio da camada de rede
                            view.showMedia(mediaList);

                        } else {
                            view.showErrorToast(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<MediaResult> call, @NotNull Throwable t) {
                        view.showErrorToast(t.getMessage());
                    }
                });
    }

    @Override
    public void getVideos(Media media) {
//        Chamada via common
//        CommonVideos videos = new CommonVideos();
//        List<Video> videoList = videos.getVideos(media);
//        List<String> videoKey = new ArrayList<>();
//
//        //Lista com Videos da mídia selecionada
//        for (int i = 0; i < videoList.size(); i++ )
//            videoKey.add(videoList.get(i).getKeyVideo());
//
//        //Desacopla camada de domínio da camada de rede
//        view.iniciaYoutubePlayer(videoKey);

        RetrofitConfig
                .getInstanceTMDB()
                .getMediaVideos(
                        media.getId(),
                        Keys.KEY_TMDB)
                .enqueue(new Callback<VideosResult>() {
                    @Override
                    public void onResponse(@NotNull Call<VideosResult> call,
                                           @NotNull Response<VideosResult> response) {
                        // status code >= 200 e <300
                        if (response.isSuccessful()) {
                            final List<Video> videoList = VideosFilmeMapper
                                    .responseToDomain(Objects
                                            .requireNonNull(response.body())
                                            .getResultadosVideos());

                            //Lista com Videos da mídia selecionada
                            List<String> videoKey = new ArrayList<>();
                            for (int i = 0; i < videoList.size(); i++ )
                                videoKey.add(videoList.get(i).getKeyVideo());

                            //Desacopla camada de domínio da camada de rede
                            view.iniciaYoutubePlayer(videoKey);
                        } else {
                            view.showErrorToast(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<VideosResult> call,
                                          @NotNull Throwable t) {
                        view.showErrorToast(t.getMessage());
                    }
                });
    }

    @Override
    public void destruirView() {
        this.view = null;
    }
}
