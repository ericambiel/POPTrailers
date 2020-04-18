package ericambiel.com.br.poptrailers.ui.filmespopulares;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ericambiel.com.br.poptrailers.config.Keys;
import ericambiel.com.br.poptrailers.data.mapper.MediaMapper;
import ericambiel.com.br.poptrailers.data.network.RetrofitConfig;
import ericambiel.com.br.poptrailers.data.network.responseCallTMDB.MediaResult;
import ericambiel.com.br.poptrailers.domain.Media;
import ericambiel.com.br.poptrailers.ui.common.CommonPresenter;
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
        CommonPresenter.INSTANCE.getVideos(media, view);
    }

    @Override
    public void destruirView() {
        this.view = null;
    }
}
