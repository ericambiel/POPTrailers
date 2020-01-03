package ericambiel.com.br.smartimdb.domain

import java.io.Serializable

/**
 * Para exemplos de Data Class:
 * https://kotlinlang.org/docs/reference/data-classes.html
 * https://blog.caelum.com.br/eu-preciso-mesmo-de-uma-data-class/
 */
data class Video(
        /**
         * Chave de acesso a fonte
         */
        val keyVideo: String?,/**
         * ex: "Fight Club | 20th Century FOX".
         */
        val tituloTrailer: String?,
        /**
         * ex: pt-BR.
         */
        val linguaTrailer: String?,
        /**
         * ex: YouTube, Vimeo.
         */
        val fonteTrailer: String?,
        /**
         * ex: 360, 480, 720, 1080...
         */
        val resolucaoTrailer: Int?) : Serializable