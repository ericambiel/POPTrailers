package ericambiel.com.br.poptrailers.ui.util.youtubeplayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import ericambiel.com.br.poptrailers.R
import ericambiel.com.br.poptrailers.config.Keys


class YoutubeFragment : Fragment() {

    private lateinit var mOnInitializerListerner: YouTubePlayer.OnInitializedListener
    private lateinit var mYouTubePlayerSupportFragmentX: YouTubePlayerSupportFragmentX
    private lateinit var mVideoKey: List<String>
    private var mIsTablet: Boolean = false //Telas pequenas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Mantem instância mesmo quando tela é rotacionada
        //Necessario add na activity dentro do manisfesto:
        //android:configChanges="orientation|screenSize"
        retainInstance = true

        val bundle: Bundle? = this.arguments
        mVideoKey = bundle?.get("keyVideo") as List<String>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()

        mYouTubePlayerSupportFragmentX = YouTubePlayerSupportFragmentX()
        mOnInitializerListerner = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, wasRestored: Boolean) {

                configurarEstadoFullScreen(youTubePlayer)

                //youTubePlayer.cueVideo(mVideoKey[0]) //Espera dar Play.
                youTubePlayer.loadVideo(mVideoKey[0]) //Inicia video direto.
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)

            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                Log.d(TAG, "Falha ao inicializar Youtube Player!")
            }
        }

        mYouTubePlayerSupportFragmentX.initialize(Keys.KEY_YOUTUBE, mOnInitializerListerner)

        // Inicia transação entre Activity e Fragment
        transaction.add(R.id.fl_media, mYouTubePlayerSupportFragmentX).commit()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    /**
     * Parametros de configuração para quando tela estiver em FullScreen
     */
    private fun configurarEstadoFullScreen(youTubePlayer: YouTubePlayer) {
        // YouTube player flags: use a custom full screen layout; let the YouTube player control
        // the system UI (hiding navigation controls, ActionBar etc); and let the YouTube player
        // handle the orientation state of the activity.
        var mYouTubeFullscreenFlags: Int =
                YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT or
                YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI or
                YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION

        // On smaller screen devices always switch to full screen in landscape mode
        if (!mIsTablet) {
            mYouTubeFullscreenFlags = mYouTubeFullscreenFlags or YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
        }

        // Apply full screen flags
        youTubePlayer.fullscreenControlFlags = mYouTubeFullscreenFlags
    }

    //Constantes estáticas
    companion object {
        const val TAG = "YOUTUBE_PLAYER"
    }
}