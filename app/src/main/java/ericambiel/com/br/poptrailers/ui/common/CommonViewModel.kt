package ericambiel.com.br.poptrailers.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ericambiel.com.br.poptrailers.config.Keys
import ericambiel.com.br.poptrailers.data.mapper.VideosFilmeMapper.Companion.responseToDomain
import ericambiel.com.br.poptrailers.data.network.RetrofitConfig
import ericambiel.com.br.poptrailers.data.network.responseCallTMDB.VideosResult
import ericambiel.com.br.poptrailers.domain.Media
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommonViewModel (): ViewModel() {

    val keyVideoList: MutableLiveData<ArrayList<String?>> by lazy { MutableLiveData<ArrayList<String?>>() }
    val message: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun loadVideos(media: Media?) {
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

                            for (i in videoList.indices)
                                keyVideoList.value?.add(videoList[i].keyVideo)
                            //Desacopla camada de domínio da camada de rede
                            //view.iniciaYoutubePlayer(videoKey)
                        } else
                            message.value = "Não existe Videos para este Poster"
                    } else
                       message.value = response.message()
                }

                override fun onFailure(call: Call<VideosResult?>, t: Throwable) {
                    message.value = t.message!!
                }
            })
    }
}