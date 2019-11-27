package ericambiel.com.br.smartimdb.ui.listafilmes;

import java.util.List;

import ericambiel.com.br.smartimdb.data.mapper.FilmesMapper;
import ericambiel.com.br.smartimdb.data.model.Filme;
import ericambiel.com.br.smartimdb.data.network.RetrofitApiService;
import ericambiel.com.br.smartimdb.data.network.response.FilmesPopularesResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterFilmesPopulares implements ContratoFilme.PresenterFilmesPopulares {
    private ContratoFilme.ViewFilmesPopulares viewFilmesPopulares;

    PresenterFilmesPopulares(ContratoFilme.ViewFilmesPopulares viewFilmesPopulares) {
        this.viewFilmesPopulares = viewFilmesPopulares;
    }

    @Override
    public void setViewFilmesPopulares(ContratoFilme.ViewFilmesPopulares viewFilmesPopulares) {
        this.viewFilmesPopulares = viewFilmesPopulares;
    }

    @Override
    public void obtemFilmes() {
        //Chama endpoint atravéz do Retrofit
        RetrofitApiService.getInstance()
                .ObterFilmesPopulares("313f36e207809621639e4fe85151294a")
                .enqueue(new Callback<FilmesPopularesResult>() {
                    @Override
                    public void onResponse(Call<FilmesPopularesResult> call, Response<FilmesPopularesResult> response) {
                        // status code >= 200 e <300
                        if(response.isSuccessful()) {
                            final List<Filme> filmesList = FilmesMapper
                                    .responseToDomain(response.body().getResultadosFilmes());

                            //Desacopla camada de domínio da camada de rede
                             viewFilmesPopulares.mostraFilmesPopulares(filmesList);
                        } else {
                            viewFilmesPopulares.mostraErro();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmesPopularesResult> call, Throwable t) {
                        viewFilmesPopulares.mostraErro();
                    }
                });
    }

    /**
     * Evita que um presenter fique orfão de uma View
     */
    @Override
    public void destruirView() {
        this.viewFilmesPopulares = null;
    }
}
