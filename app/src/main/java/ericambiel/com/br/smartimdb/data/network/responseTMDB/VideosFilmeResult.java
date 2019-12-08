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
public class VideosFilmeResult {
    /**
     * Id do video no TMDB, mesmo usado no endPoint para buscar video
     */
    @Json(name = "id")
    private final int id; // Se nome da variavel igual ao do Json não é necessário @Json

    /**
     * Lista com resultados da pesquisa
     */
    @Json(name = "results")
    private final List<VideoFilmeResponse> resultadosVideos;

    public VideosFilmeResult(int id, List<VideoFilmeResponse> resultadosVideos) {
        this.id = id;
        this.resultadosVideos = resultadosVideos;
    }

    public int getId() {
        return id;
    }

    public List<VideoFilmeResponse> getResultadosVideos() {
        return resultadosVideos;
    }
}
