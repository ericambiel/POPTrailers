package ericambiel.com.br.smartimdb.ui.filmespopulares

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ericambiel.com.br.smartimdb.R
import ericambiel.com.br.smartimdb.domain.Media
import ericambiel.com.br.smartimdb.ui.common.CommonMediaAdapter
import ericambiel.com.br.smartimdb.ui.util.youtubeplayer.YoutubeFragment
import java.io.Serializable

class FilmesPopularFragment :
        Fragment(),
        FilmesPopularesContrato.View,
        CommonMediaAdapter.ItemMediaClickListener {
    private lateinit var adapter: CommonMediaAdapter
    private lateinit var presenter: FilmesPopularesPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FilmesPopularesPresenter(this)
        presenter.getMedia()

        setupAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destruirView()
    }

    override fun setupAdapter() {
        val recyclerView: RecyclerView? = activity?.findViewById(R.id.recycler_media)
        adapter = CommonMediaAdapter(this)
        //Constroi LayoutManager
        val gridLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        //Seta LayoutManager
        recyclerView?.layoutManager = gridLayoutManager
        //Seta Adapter
        recyclerView?.adapter = adapter
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