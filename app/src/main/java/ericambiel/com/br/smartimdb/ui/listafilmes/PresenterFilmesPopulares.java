package ericambiel.com.br.smartimdb.ui.listafilmes;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import ericambiel.com.br.smartimdb.config.Keys;
import ericambiel.com.br.smartimdb.data.mapper.FilmesMapper;
import ericambiel.com.br.smartimdb.data.model.Filme;
import ericambiel.com.br.smartimdb.data.network.RetrofitApiService;
import ericambiel.com.br.smartimdb.data.network.responseTMDB.FilmesPopularesResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterFilmesPopulares implements ContratoFilme.PresenterFilmesPopulares {
    private ContratoFilme.ViewFilmesPopulares viewFilmesPopulares;

    PresenterFilmesPopulares(ContratoFilme.ViewFilmesPopulares viewFilmesPopulares) {
        this.viewFilmesPopulares = viewFilmesPopulares;
    }

    @Override
    public void obtemFilmes() {
        //Chama endpoint atravéz do Retrofit
        RetrofitApiService.getInstance()
                .ObterFilmesPopulares(Keys.key_TMDB) //Classe estática Keys
                .enqueue(new Callback<FilmesPopularesResult>() {
                    @Override
                    public void onResponse(@NotNull Call<FilmesPopularesResult> call, @NotNull Response<FilmesPopularesResult> response) {
                        // status code >= 200 e <300
                        if(response.isSuccessful()) {
                            final List<Filme> filmesList = FilmesMapper
                                    .responseToDomain(Objects.requireNonNull(response.body()).getResultadosFilmes());

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
    public void obterVideosFilmes() {

    }

    /**
     * Evita que um presenter fique orfão de uma View
     */
    @Override
    public void destruirView() {
        this.viewFilmesPopulares = null;
    }
}
