package ericambiel.com.br.poptrailers.ui.mediaplayingnow

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ericambiel.com.br.poptrailers.R
import ericambiel.com.br.poptrailers.domain.Media
import ericambiel.com.br.poptrailers.ui.MainContrato
import ericambiel.com.br.poptrailers.ui.common.CommonMediaAdapter
import ericambiel.com.br.poptrailers.ui.common.CommonViewModel
import ericambiel.com.br.poptrailers.ui.util.youtubeplayer.YoutubeFragment
import java.io.Serializable

class MediaPlayingNowFragment :
        Fragment(),
        MainContrato.View,
        CommonMediaAdapter.ItemMediaClickListener {

    companion object {
        const val TAG = "PLAYING_NOW"
    //    fun newInstance() = this
    }

    private lateinit var adapter: CommonMediaAdapter

    private val viewModel: MediaPlayingNowViewModel by viewModels( factoryProducer = {
        SavedStateViewModelFactory(Application(),this)
    })

    private val commonViewModel: CommonViewModel by viewModels( factoryProducer = {
        SavedStateViewModelFactory(Application(),this)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mediaList.observe(viewLifecycleOwner){
            showMedia(viewModel.mediaList.value)
        }

        viewModel.message.observe(viewLifecycleOwner) {
            showErrorToast(viewModel.message.value)
        }

        commonViewModel.keyVideoList.observe(viewLifecycleOwner) {
            iniciaYoutubePlayer(commonViewModel.keyVideoList.value)
        }

        commonViewModel.keyVideoList.observe(viewLifecycleOwner) {
            showErrorToast(commonViewModel.message.value)
        }

        setupAdapter()
        viewModel.loadMedia()
    }

    override fun setupAdapter() {
        Thread(Runnable { //Evita que RV não seja mostrado entre trocas de Fragmento
            activity?.runOnUiThread {
                val recyclerView: RecyclerView? = activity?.findViewById(R.id.recycler_media)
                adapter = CommonMediaAdapter(this)
                //Constroi LayoutManager
                val gridLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
                //Seta LayoutManager
                recyclerView?.layoutManager = gridLayoutManager
                //Seta Adapter
                recyclerView?.adapter = adapter
            }
        }).start()

    }

    override fun showMedia(mediaList: List<Media?>?) {
        adapter.setMedia(mediaList)
    }

    override fun showErrorToast(erro: String?) {
        Toast.makeText(context, "Erro: $erro", Toast.LENGTH_LONG).show()
    }

    override fun onClickItemMedia(media: Media) {
        commonViewModel.loadVideos(media)
    }

    override fun iniciaYoutubePlayer(keyVideoList: List<String?>?) {
        val youtubePlayer: Fragment = YoutubeFragment()
        val bundle = Bundle()
        bundle.putSerializable("keyVideo", keyVideoList as Serializable)
        youtubePlayer.arguments = bundle
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.fl_media, youtubePlayer)
                ?.addToBackStack(TAG)
                ?.commit()
    }
}