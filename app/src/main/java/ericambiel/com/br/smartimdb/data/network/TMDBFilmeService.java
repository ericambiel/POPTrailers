package ericambiel.com.br.smartimdb.data.network;

import ericambiel.com.br.smartimdb.data.network.response.FilmesPopularesResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBFilmeService {
    @GET("movie/popular")
    Call<FilmesPopularesResult> ObterFilmesPopulares(@Query("api_key") String apiKey);
}
