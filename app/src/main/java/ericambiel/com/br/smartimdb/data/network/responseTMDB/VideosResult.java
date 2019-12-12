package ericambiel.com.br.smartimdb.data.network.responseTMDB;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * 1º
 * Classe responsavel por trazer os objetos de um Movie do JSON
 * e gravar em uma lista
 *
 * verificar: https://developers.themoviedb.org/3/movies/get-movie-videos
 */
public class VideosResult {
    /**
     * Id do video no TMDB, mesmo usado no endPoint para buscar video
     */
    @Json(name = "id")
    private final int id; // Se nome da variavel igual ao do Json não é necessário @Json

    /**
     * Lista com resultados da pesquisa
     */
    @Json(name = "results")
    private final List<VideoResponse> resultadosVideos;

    public VideosResult(int id,
                        List<VideoResponse> resultadosVideos) {
        this.id = id;
        this.resultadosVideos = resultadosVideos;
    }

    public int getId() {
        return id;
    }

    public List<VideoResponse> getResultadosVideos() {
        return resultadosVideos;
    }
}
