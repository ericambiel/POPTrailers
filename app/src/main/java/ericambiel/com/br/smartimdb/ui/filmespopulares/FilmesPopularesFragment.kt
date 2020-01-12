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
import ericambiel.com.br.smartimdb.ui.util.CommonMediaAdapter
import ericambiel.com.br.smartimdb.ui.youtubeplayer.YoutubeFragment
import java.io.Serializable

class FilmesPopularesFragment :
        Fragment(),
        FilmePopularesContrato.ViewFilmesPopulares,
        CommonMediaAdapter.ItemMediaClickListener {
    private lateinit var filmesAdapter: CommonMediaAdapter
    private lateinit var presenterFilmesPopulares: FilmesPopularesPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenterFilmesPopulares = FilmesPopularesPresenter(this)
        presenterFilmesPopulares.getMedia()
        configuraAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_videos_populares, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterFilmesPopulares.destruirView()
    }

    override fun showMedia(mediaList: MutableList<Media>?) {
        filmesAdapter.setFilmes(mediaList)
    }

    override fun mostraErro(erro: String) {
        Toast.makeText(context, "Erro: $erro", Toast.LENGTH_LONG).show()
    }

    override fun onClickItemFilme(media: Media) {
        presenterFilmesPopulares.getVideos(media)
    }

    private fun configuraAdapter() {
        val recyclerView: RecyclerView? = activity?.findViewById(R.id.recycler_videos)
        filmesAdapter = CommonMediaAdapter(this)
        //Constroi LayoutManager
        val gridLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        //Seta LayoutManager
        recyclerView?.layoutManager = gridLayoutManager
        //Seta Adapter
        recyclerView?.adapter = filmesAdapter
    }

    override fun iniciaYoutubePlayer(keyVideoList: List<String>) {
        val youtubePlayer: Fragment = YoutubeFragment()
        val bundle = Bundle()
        bundle.putSerializable("keyVideo", keyVideoList as Serializable)
        youtubePlayer.arguments = bundle
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.fl_filmes_populares, youtubePlayer)
                ?.addToBackStack(YoutubeFragment.getTag())
                ?.commit()
    }

    companion object {
        const val TAG = "FILMES_POPULARES"
    }
}