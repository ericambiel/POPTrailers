package ericambiel.com.br.poptrailers.ui.filmespopulares

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ericambiel.com.br.poptrailers.R
import ericambiel.com.br.poptrailers.domain.Media
import ericambiel.com.br.poptrailers.ui.common.CommonMediaAdapter
import ericambiel.com.br.poptrailers.ui.util.youtubeplayer.YoutubeFragment
import java.io.Serializable

class FilmesPopularesFragment :
        Fragment(),
        FilmesPopularesContrato.View,
        CommonMediaAdapter.ItemMediaClickListener {
    private lateinit var adapter: CommonMediaAdapter
    private lateinit var presenter: FilmesPopularesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = FilmesPopularesPresenter(this)

        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        presenter.getMedia()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destruirView()
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
        presenter.getVideos(media)
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

    companion object {
        const val TAG = "FILMES_POPULARES"
    }
}