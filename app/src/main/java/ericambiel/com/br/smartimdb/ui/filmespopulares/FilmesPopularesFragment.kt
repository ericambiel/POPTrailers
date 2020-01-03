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
import ericambiel.com.br.smartimdb.domain.Filme
import ericambiel.com.br.smartimdb.ui.FilmeContrato
import ericambiel.com.br.smartimdb.ui.FilmeContrato.ViewFilmesPopulares
import ericambiel.com.br.smartimdb.ui.filmespopulares.FilmesPopularesAdapter.ItemFilmeClickListener
import ericambiel.com.br.smartimdb.ui.youtubeplayer.YoutubeFragment
import java.io.Serializable

class FilmesPopularesFragment : Fragment(), ViewFilmesPopulares, ItemFilmeClickListener {
    private var filmesAdapter: FilmesPopularesAdapter? = null
    private var presenterFilmesPopulares: FilmeContrato.PresenterFilmesPopulares? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenterFilmesPopulares = FilmesPopularesPresenter(this)
        (presenterFilmesPopulares as FilmesPopularesPresenter).obtemFilmesPopulares()
        configuraAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_videos_populares, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterFilmesPopulares!!.destruirView()
    }

    override fun mostraFilmesPopulares(filmesList: List<Filme>) {
        filmesAdapter!!.setFilmes(filmesList)
    }

    override fun mostraErro(erro: String) {
        Toast.makeText(context, "Erro: $erro", Toast.LENGTH_LONG).show()
    }

    override fun onClickItemFilme(filme: Filme) {
        presenterFilmesPopulares!!.obtemVideos(filme)
    }

//    private void configuraToolBar(){
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//    }

    private fun configuraAdapter() {
        val recyclerView: RecyclerView? = activity?.findViewById(R.id.recycler_filmes)
        filmesAdapter = FilmesPopularesAdapter(this)
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
        private const val TAG = "FILMES_POPULARES"
    }
}