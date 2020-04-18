package ericambiel.com.br.poptrailers.ui.common

import ericambiel.com.br.poptrailers.config.Keys
import ericambiel.com.br.poptrailers.data.mapper.VideosFilmeMapper.Companion.responseToDomain
import ericambiel.com.br.poptrailers.data.network.RetrofitConfig
import ericambiel.com.br.poptrailers.data.network.responseCallTMDB.VideosResult
import ericambiel.com.br.poptrailers.domain.Media
import ericambiel.com.br.poptrailers.ui.MainContrato
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

object CommonPresenter: CommonContrato.Presenter {
    override fun getVideos(media: Media?, view: MainContrato.View) {
        RetrofitConfig
            .getInstanceTMDB()
            .getMediaVideos(
                    media!!.id,
                    Keys.KEY_TMDB)
            .enqueue(object : Callback<VideosResult?> {
                override fun onResponse(call: Call<VideosResult?>,
                                        response: Response<VideosResult?>) { // status code >= 200 e <300
                    if (response.isSuccessful) {
                        val videoList = responseToDomain(response.body()!!.resultadosVideos)
                        if (videoList.isNotEmpty()){
                            //Lista com Videos da mídia selecionada
                            val keyVideo: ArrayList<String?> = ArrayList()
                            for (i in videoList.indices) keyVideo.add(videoList[i].keyVideo)
                            //Desacopla camada de domínio da camada de rede
                            view.iniciaYoutubePlayer(keyVideo)
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