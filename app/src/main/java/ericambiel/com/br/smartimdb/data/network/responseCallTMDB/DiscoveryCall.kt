package ericambiel.com.br.smartimdb.data.network.responseCallTMDB

import android.annotation.SuppressLint
import ericambiel.com.br.smartimdb.config.Keys
import java.text.SimpleDateFormat
import java.util.*

data class DiscoveryCall (val API_KEY: String = Keys.KEY_TMDB,
                          var page: Int = 1,
                          var language: String = "pt-BR",
                          var region: String = "BR",
                          var releaseDataGte: String = "",
                          var releaseDataLte: String = "",
                          var withReleaseType: String = "2|3",
                          var includeAdult: Boolean = true,
                          var sortBy: String = "release_date.cres"){
    //Inicializa variaveis
    init {
        releaseDataGte = getAtualData()
    }

    private fun getAtualData() : String{
        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }
}