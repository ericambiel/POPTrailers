package ericambiel.com.br.poptrailers.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.selection.SelectionTracker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import ericambiel.com.br.poptrailers.R
import ericambiel.com.br.poptrailers.domain.User
import kotlinx.android.synthetic.main.nav_header_user_logged.*
import kotlinx.android.synthetic.main.nav_header_user_not_logged.*
import kotlinx.android.synthetic.main.nav_menu.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var selectNavMenuItems: SelectionTracker<Long>
    lateinit var selectNavMenuItemsLogged: SelectionTracker<Long>

    //Usuário de Teste
    val user = User(
            "Eric Ambiel",
            R.drawable.ic_user_circle_24dp,
            true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        //Variavel que faz referência ao menu gaveta
        val navView: NavigationView = findViewById(R.id.nav_view)

        //Usando a helper class Navigation podemos encontrar o componente do NavController
        //Baseado no NavHostFragment que está hospedado em nosso Layout Principal
        val navController = findNavController(R.id.nav_host_fragment)


        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)

        //Instanciamos o AppBarConfiguration
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_videos_populares,
                R.id.nav_playing_now,
                R.id.nav_about
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //Configura o navView com um navControler
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        showHideNavMenuViews()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Coloca o usuário e sua foto no Menu Gaveta
     */
    private fun fillUserHeaderNavMenu() {
        if (user.status) { /* Conectado */
            iv_user.setImageResource(user.image)
            tv_user.text = user.name
        }
    }

    /**
     * Responsável por esconder itens do menu gaveta de
     * acordo com o status do usuário (conectado ou não).
     * */
    private fun showHideNavMenuViews() {
        if (user.status) { /* Conectado */
            rl_header_user_not_logged.visibility = View.GONE
            fillUserHeaderNavMenu()
        } else {  /* Não conectado */
            rl_header_user_logged.visibility = View.GONE
            v_nav_vertical_line.visibility = View.GONE
            rv_menu_items_logged.visibility = View.GONE
        }
    }
}
