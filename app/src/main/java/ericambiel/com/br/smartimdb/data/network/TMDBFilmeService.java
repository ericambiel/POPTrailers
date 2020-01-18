package ericambiel.com.br.smartimdb.data.network;

import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.MediaResult;
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.VideosResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBFilmeService {
    /**
     * Get list of popular media in TMDB.
     * @param apiKey API Key from TMDB.
     * @return List of discovered media.
     */
    @GET("movie/popular")
    Call<MediaResult> getPopularMedia(@Query("api_key") String apiKey);

    /**
     * Get media videos.
     * @param movieId Media ID within the TMDB platform.
     * @param apiKey API Key from TMDB.
     * @return List of discovered media videos.
     */
    @GET("movie/{movie_id}/videos")
    Call<VideosResult> getMediaVideos(@Path("movie_id") int movieId,
                                      @Query("api_key") String apiKey);

    /**
     * Get media that are playing now.
     * @param apiKey API Key from TMDB
     * @param page Specify the page of results to query. Min 1, Max 1000, Default 1
     * @param language Specify a language to query translatable fields with. minLength: 2, pattern: ([a-z]{2})-([A-Z]{2}), default: en-US
     * @param region Specify a ISO 3166-1 code to filter release dates. Must be uppercase. pattern: ^[A-Z]{2}$
     * @param releaseDataGte Filter and only include movies that have a release date (looking at all release dates) that is greater or equal to the specified value. format: date
     * @param releaseDataLte Filter and only include movies that have a release date (looking at all release dates) that is less than or equal to the specified value. format: date
     * @param withReleaseType Specify a comma (AND) or pipe (OR) separated value to filter release types by. These release types map to the same values found on the movie release date method. minimum: 1, maximum: 6
     * @param includeAdult A filter and include or exclude adult movies.
     * @return List of discovered movies.
     */
    @GET("discover/movie")
    Call<MediaResult> getMediaPlayingNow(@Query("api_key") String apiKey,
                                         @Query("page") int page,
                                         @Query("language") String language,
                                         @Query("region") String region,
                                         @Query("release_date.gte") String releaseDataGte,
                                         @Query("release_date.lte") String releaseDataLte,
                                         @Query("with_release_type") String withReleaseType,
                                         @Query("include_adult") boolean includeAdult,
                                         @Query("sort_by") String sortBy);
}
