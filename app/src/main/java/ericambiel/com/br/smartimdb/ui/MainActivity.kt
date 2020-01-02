package ericambiel.com.br.smartimdb.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import ericambiel.com.br.smartimdb.R
import ericambiel.com.br.smartimdb.data.NavMenuItemsDataBase
import ericambiel.com.br.smartimdb.domain.NavMenuItem
import ericambiel.com.br.smartimdb.domain.User
import ericambiel.com.br.smartimdb.util.NavMenuItemDetailsLookup
import ericambiel.com.br.smartimdb.util.NavMenuItemKeyProvider
import ericambiel.com.br.smartimdb.util.NavMenuItemPredicate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_user_logged.*
import kotlinx.android.synthetic.main.nav_header_user_not_logged.*
import kotlinx.android.synthetic.main.nav_menu.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navMenuItems : List<NavMenuItem>
    lateinit var navMenuItemsLogged : List<NavMenuItem>
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

        //Adiciona botão para abrir menu gaveta
        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener( toggle )
        toggle.syncState()

        initNavMenu( savedInstanceState )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        /*
         * Para manter o item de menu gaveta selecionado caso
         * haja reconstrução de atividade.
         * */
        selectNavMenuItems.onSaveInstanceState(outState)
        selectNavMenuItemsLogged.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Método que inicializa a lista de itens de menu gaveta
     * que estará presente quando o usuário estiver ou não
     * conectado ao aplicativo.
     * */
    private fun initNavMenuItems(){
        rv_menu_items.setHasFixedSize( false )
        rv_menu_items.layoutManager = LinearLayoutManager( this )
        rv_menu_items.adapter = NavMenuItemsAdapter( navMenuItems )

        initNavMenuItemsSelection()
    }

    /**
     * Método responsável por inicializar o objeto de seleção
     * de itens de menu gaveta, seleção dos itens que aparecem
     * para usuário conectado ou não.
     * */
    private fun initNavMenuItemsSelection(){

        selectNavMenuItems = SelectionTracker.Builder<Long>(
                "id-selected-item",
                rv_menu_items,
                NavMenuItemKeyProvider( navMenuItems ),
                NavMenuItemDetailsLookup( rv_menu_items ),
                StorageStrategy.createLongStorage()
        )
                .withSelectionPredicate( NavMenuItemPredicate( this ) )
                .build()

        //Verifica click no item dentro do RV
        selectNavMenuItems.addObserver(
                SelectObserverNavMenuItems {
                    selectNavMenuItemsLogged.selection.filter {
                        selectNavMenuItemsLogged.deselect( it )
                    }
                }
        )

        (rv_menu_items.adapter as NavMenuItemsAdapter).selectionTracker = selectNavMenuItems
    }

    /**
     * Método que inicializa a parte de lista de itens de menu
     * gaveta que estará presente somente quando o usuário
     * estiver conectado ao aplicativo.
     * */
    private fun initNavMenuItemsLogged(){
        rv_menu_items_logged.setHasFixedSize( true )
        rv_menu_items_logged.layoutManager = LinearLayoutManager( this )
  //      rv_menu_items_logged.adapter = NavMenuItemsAdapter( NavMenuItemsDataBase( this ).itemsLogged )
        rv_menu_items_logged.adapter = NavMenuItemsAdapter( navMenuItemsLogged )

        initNavMenuItemsLoggedSelection()
    }

    /**
     * Método responsável por inicializar o objeto de seleção
     * de itens de menu gaveta, seleção dos itens que aparecem
     * somente quando o usuário está conectado ao app.
     * */
    private fun initNavMenuItemsLoggedSelection(){

        selectNavMenuItemsLogged = SelectionTracker.Builder<Long>(
                "id-selected-item-logged",
                rv_menu_items_logged,
                NavMenuItemKeyProvider( navMenuItemsLogged ),
                NavMenuItemDetailsLookup( rv_menu_items_logged ),
                StorageStrategy.createLongStorage()
        )
                .withSelectionPredicate( NavMenuItemPredicate( this ) )
                .build()

        //Verifica click no item dentro do RV
        selectNavMenuItemsLogged.addObserver(
                SelectObserverNavMenuItems {
                    selectNavMenuItems.selection.filter {
                        selectNavMenuItems.deselect( it )
                    }
                }
        )

        (rv_menu_items_logged.adapter as NavMenuItemsAdapter).selectionTracker = selectNavMenuItemsLogged
    }

    private fun fillUserHeaderNavMenu(){
        if( user.status ) { /* Conectado */
            iv_user.setImageResource(user.image)
            tv_user.text = user.name
        }
    }

    /**
    * Responsável por esconder itens do menu gaveta de
    * acordo com o status do usuário (conectado ou não).
    * */
    private fun showHideNavMenuViews(){
        if( user.status ){ /* Conectado */
            rl_header_user_not_logged.visibility = View.GONE
            fillUserHeaderNavMenu()
        }
        else{  /* Não conectado */
            rl_header_user_logged.visibility = View.GONE
            v_nav_vertical_line.visibility = View.GONE
            rv_menu_items_logged.visibility = View.GONE
        }
    }

    /**
     * Inicializa menu gaveta, responsável por
     * apresentar o cabeçalho e itens de menu de acordo com o
     * status do usuário (logado ou não).
     * */
    private fun initNavMenu( savedInstanceState: Bundle? ){

        val navMenu = NavMenuItemsDataBase( this )
        navMenuItems = navMenu.items
        navMenuItemsLogged = navMenu.itemsLogged

        showHideNavMenuViews()

        initNavMenuItems()
        initNavMenuItemsLogged()

        if( savedInstanceState != null ){
            selectNavMenuItems.onRestoreInstanceState( savedInstanceState )
            selectNavMenuItemsLogged.onRestoreInstanceState( savedInstanceState )
        }
        else{
            /*
             * O primeiro item do menu gaveta deve estar selecionado
             * caso não seja uma reinicialização de tela / atividade.
             * O primeiro item aqui é o de ID 1.
             * */
            selectNavMenuItems.select( R.id.item_filmes_populares.toLong() )
        }
    }

    inner class SelectObserverNavMenuItems(
            val callbackRemoveSelection: ()->Unit
    ) : SelectionTracker.SelectionObserver<Long>(){

        /**
         * Responsável por permitir que seja possível disparar alguma ação de acordo com a mudança
         * de status de algum item em algum dos objetos de seleção de itens de menu gaveta.
         */
        /*
          Aqui vamos proceder com alguma ação somente em caso de item obtendo seleção, para item
          perdendo seleção não haverá processamento, pois este status não importa na lógica de
          negócio deste método.
         * */
        override fun onItemStateChanged(
                key: Long,
                selected: Boolean ) {
            super.onItemStateChanged( key, selected )

            /*
             * Padrão Cláusula de Guarda para não seguirmos
             * com o processamento em caso de item perdendo
             * seleção. O processamento posterior ao condicional
             * abaixo é somente para itens obtendo a seleção,
             * selected = true.
             * */
            if( !selected ){
                return
            }

            /*
             * Para garantir que somente um item de lista se
             * manterá selecionado, é preciso acessar o objeto
             * de seleção da lista de itens de usuário conectado
             * para então remover qualquer possível seleção
             * ainda presente nela. Sempre haverá somente um
             * item selecionado, mas infelizmente o método
             * clearSelection() não estava respondendo como
             * esperado, por isso a estratégia a seguir.
             * */
            callbackRemoveSelection()

            /*
             * TODO: Mudança de Fragment
             * */

            /*
             * Fechando o menu gaveta.
             * */
            drawer_layout.closeDrawer( GravityCompat.START )
        }
    }
}
