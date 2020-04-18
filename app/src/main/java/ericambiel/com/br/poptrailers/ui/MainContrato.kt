package ericambiel.com.br.poptrailers.ui

import ericambiel.com.br.poptrailers.domain.Media

/**
 * Interface responsavel por designar Presenters e Views as classes (MVP).
 */
interface MainContrato {
    /**
     * Interface a ser extendia para todas as Views.
     * Comum a todas as Views.
     */
    interface View {
        /**
         * Confgura adapter e inicializa ele em um RV
         */
        fun setupAdapter()

        /**
         * Comtem lista de filmes populares a serem exibidos.
         * @param mediaList lista com filmes a serem exibidos.
         */
        fun showMedia(mediaList: List<Media?>?)

        /**
         * Exibe erro caso haja problemas de comunicação entre app e endpoints TMDB.
         */
        fun showErrorToast(erro: String?)

        /**
         * Exibe player do YouTube em novo Fragmento
         * @param keyVideoList lista de videos a serem executados
         */
        fun iniciaYoutubePlayer(keyVideoList: List<String?>?)
    }

    /**
     * Interface a ser extendia para todas os Presenters.
     * Comum a todos os Presenters.
     */
    interface Presenter {
        /**
         * Get medias (movies, series, shows, etc) from API
         */
        fun getMedia()

        /**
         * Get videos (trailes and teasers) from API
         */
        fun getVideos(media: Media?)

        /**
         * Evita que um presenter fique orfão de uma View.
         */
        fun destruirView()
    }
}