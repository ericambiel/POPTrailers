package ericambiel.com.br.smartimdb.ui.youtubeplayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragmentX
import ericambiel.com.br.smartimdb.R
import ericambiel.com.br.smartimdb.config.Keys

class YoutubeFragment(private var video: String) : Fragment() {

    private lateinit var mOnInitializerListerner: YouTubePlayer.OnInitializedListener
    private lateinit var youTubePlayerFragmentX: YouTubePlayerFragmentX

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()

        youTubePlayerFragmentX = YouTubePlayerFragmentX()
        mOnInitializerListerner = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, wasRestored: Boolean) {
                if (wasRestored) {
                    youTubePlayer.play()
                } else {
                    //youTubePlayer.cueVideo("pRj8x8M2iAI") //Espera dar Play.
                    youTubePlayer.loadVideo(video) //Chama video direto.
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
                }
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                Log.d(TAG, "Failed to initialize")
            }
        }

        youTubePlayerFragmentX.initialize(Keys.key_YouTube, mOnInitializerListerner)

        // Inicia transação entre Activity e Fragment
        transaction.add(R.id.youtube_fragment_layout, youTubePlayerFragmentX).commit()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    //Constantes
    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }
}