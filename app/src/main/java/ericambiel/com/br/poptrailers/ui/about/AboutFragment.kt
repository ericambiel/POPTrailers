package ericambiel.com.br.poptrailers.ui.about


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ericambiel.com.br.poptrailers.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment :
        Fragment(),
        View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    /*
         * Para que não tenhamos problemas de Views ainda não
         * carregadas em tela para possível acesso em código
         * dinâmico, as Views do layout de AboutFragment estão
         * sendo acessadas somente em método posterior ao
         * método onCreateView(), no caso o método onActivityCreated(),
         * digo, Views sendo acessadas via sintaxe
         * kotlin-android-extensions.
         * */
    override fun onActivityCreated( savedInstanceState: Bundle? ) {
        super.onActivityCreated( savedInstanceState )

        iv_instagram.setOnClickListener( this )
        tv_instagram.setOnClickListener( this )

        iv_facebook.setOnClickListener( this )
        tv_facebook.setOnClickListener( this )

        iv_youtube.setOnClickListener( this )
        tv_youtube.setOnClickListener( this )

    }

    override fun onClick( v: View ) {
        when( v.id ){
            R.id.tv_instagram,
            R.id.iv_instagram -> {
                openNetwork(
                        "com.instagram.android",
                        "http://instagram.com/_u/ericambiel",
                        "http://instagram.com/ericambiel"
                )
            }
            R.id.tv_facebook,
            R.id.iv_facebook -> {
                openNetwork(
                        "com.facebook.katana",
                        "fb://facewebmodal/f?href=https://www.facebook.com/ericambiel",
                        "https://www.facebook.com/ericambiel"
                )
            }
            else -> {
                openNetwork(
                        "com.google.android.youtube",
                        "https://www.youtube.com/user/ericambiel",
                        "https://www.youtube.com/user/ericambiel"
                )
            }
        }
    }

    private fun openNetwork(
            appPackage: String,
            appAddress: String,
            webAddress: String ){

        val uri = Uri.parse( appAddress )
        val intent = Intent( Intent.ACTION_VIEW, uri )

        intent.setPackage( appPackage )

        try{
            requireActivity().startActivity( intent )
        }
        catch( e: ActivityNotFoundException){
            /*
             * Se não houver o aplicativo da rede
             * social acionada, então abra a página
             * no navegador padrão do aparelho, Web.
             * */
            requireActivity().startActivity(
                    Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse( webAddress )
                    )
            )
        }
    }
}