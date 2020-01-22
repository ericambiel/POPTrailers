package ericambiel.com.br.smartimdb.ui.common

import ericambiel.com.br.smartimdb.config.Keys
import ericambiel.com.br.smartimdb.data.mapper.VideosFilmeMapper.Companion.responseToDomain
import ericambiel.com.br.smartimdb.data.network.RetrofitConfig
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.VideosResult
import ericambiel.com.br.smartimdb.domain.Media
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


object CommonVideos {
    @JvmStatic
    fun getVideos(media: Media, view: CommonContrato.View) {
        RetrofitConfig
            .getInstanceTMDB()
            .getMediaVideos(
                    media.id,
                    Keys.KEY_TMDB)
            .enqueue(object : Callback<VideosResult?> {
                override fun onResponse(call: Call<VideosResult?>,
                                        response: Response<VideosResult?>) { // status code >= 200 e <300
                    if (response.isSuccessful) {
                        val videoList = responseToDomain(response.body()!!.resultadosVideos)
                        if (videoList.isNotEmpty()){
                            //Lista com Videos da mídia selecionada
                            val videoKey: ArrayList<String?> = ArrayList()
                            for (i in videoList.indices) videoKey.add(videoList[i].keyVideo)
                            //Desacopla camada de domínio da camada de rede
                            view.iniciaYoutubePlayer(videoKey)
                        } else
                            view.showErrorToast("Não existe Videos para este Poster")
                    } else
                        view.showErrorToast(response.message())
                }

                override fun onFailure(call: Call<VideosResult?>, t: Throwable) {
                    view.showErrorToast(t.message!!)
                }
            })
    }
}