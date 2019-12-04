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


private const val TAG = "MAIN_ACTIVITY"

class YoutubeFragment : Fragment() {

    var mOnInitializerListerner: YouTubePlayer.OnInitializedListener? = null
    private lateinit var youTubePlayerFragmentX: YouTubePlayerFragmentX

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_youtube, container, false)

        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.youtube_fragment_layout, youTubePlayerFragmentX).commit()

        mOnInitializerListerner = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, wasRestored: Boolean) {
                if (wasRestored) {
                    youTubePlayer.play()
                } else {
                    //youTubePlayer.cueVideo("pRj8x8M2iAI") //Espera dar Play.
                    youTubePlayer.loadVideo("pRj8x8M2iAI") //Chama video direto.
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
                }
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                Log.d(TAG, "Failed to initialize")
            }
        }

        youTubePlayerFragmentX.initialize("AIzaSyBwSvhhXHE_UBMHiI0kODGE5g5A6jG8jew", mOnInitializerListerner)


        // Inflate the layout for this fragment
        return view
    }
}