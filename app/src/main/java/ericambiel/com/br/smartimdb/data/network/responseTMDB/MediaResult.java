package ericambiel.com.br.smartimdb.data.network.responseTMDB;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * 1º
 * Classe responsavel por trazer os objetos de Filmes Populares do JSON
 * e gravar em uma lista
 *
 * verificar: https://developers.themoviedb.org/3/movies/get-popular-movies
 */
public class MediaResult {
    @Json(name = "page")
    private final int pagina; // Se nome da variavel igual ao do Json não é necessário @Json

    @Json(name = "results")
    private final List<MediaResponse> resultadosFilmes;

    @Json(name = "total_results")
    private final int totalResultados;

    @Json(name = "total_pages")
    private final int totalPaginas;

    public MediaResult(int pagina,
                       List<MediaResponse> resultadosFilmes,
                       int totalResultados,
                       int totalPaginas) {
        this.pagina = pagina;
        this.resultadosFilmes = resultadosFilmes;
        this.totalResultados = totalResultados;
        this.totalPaginas = totalPaginas;
    }

    public int getPagina() {
        return pagina;
    }

    public List<MediaResponse> getResultadosFilmes() {
        return resultadosFilmes;
    }

    public int getTotalResultados() {
        return totalResultados;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }
}
