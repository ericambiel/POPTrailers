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
public class FilmesPopularesResult {
    @Json(name = "page")
    private final int pagina; // Se nome da variavel igual ao do Json não é necessário @Json

    @Json(name = "results")
    private final List<FilmePopularesResponse> resultadosFilmes;

    @Json(name = "total_results")
    private final int totalResultados;

    @Json(name = "total_pages")
    private final int toralPaginas;

    public FilmesPopularesResult(int pagina, List<FilmePopularesResponse> resultadosFilmes, int totalResultados, int toralPaginas) {
        this.pagina = pagina;
        this.resultadosFilmes = resultadosFilmes;
        this.totalResultados = totalResultados;
        this.toralPaginas = toralPaginas;
    }

    public int getPagina() {
        return pagina;
    }

    public List<FilmePopularesResponse> getResultadosFilmes() {
        return resultadosFilmes;
    }

    public int getTotalResultados() {
        return totalResultados;
    }

    public int getToralPaginas() {
        return toralPaginas;
    }
}
