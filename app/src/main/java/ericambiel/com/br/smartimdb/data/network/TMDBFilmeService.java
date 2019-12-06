package ericambiel.com.br.smartimdb.data.network;

import ericambiel.com.br.smartimdb.data.network.responseTMDB.FilmesPopularesResult;
import ericambiel.com.br.smartimdb.data.network.responseTMDB.VideosFilmesResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBFilmeService {
    @GET("movie/popular")
    Call<FilmesPopularesResult> ObterFilmesPopulares(@Query("api_key") String apiKey);

    @GET("/movie/{movie_id}/videos")
    Call<VideosFilmesResult> ObterVideosFilmes(@Query("api_key") String apiKey, @Path("movie_id") String movieId);
}
