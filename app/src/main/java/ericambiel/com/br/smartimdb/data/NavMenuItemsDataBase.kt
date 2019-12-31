package ericambiel.com.br.smartimdb.data

import android.content.Context
import ericambiel.com.br.smartimdb.R
import ericambiel.com.br.smartimdb.domain.NavMenuItem

class NavMenuItemsDataBase( context: Context) {

    /**
     * Itens de menu gaveta que sempre estarão presentes,
     * independente do status do usuário (conectado ou não).
     * */
    val items = listOf(
            NavMenuItem(
                    R.id.item_filmes_populares.toLong(),
                    context.getString(R.string.item_filmes_populares),
                    R.drawable.ic_menu_videos_populares_24dp
            ),
            NavMenuItem(
                    R.id.item_compartilhar.toLong(),
                    context.getString(R.string.item_compartilhar),
                    R.drawable.ic_menu_share_24db
            ),
            NavMenuItem(
                    R.id.item_contato.toLong(),
                    context.getString(R.string.item_contato),
                    R.drawable.ic_email_black_24dp
            ),
            NavMenuItem(
                    R.id.item_politica_privacidade.toLong(),
                    context.getString(R.string.item_politica_privacidade),
                    R.drawable.ic_lock_black_24dp
            )
    )

    /**
     * Itens de menu gaveta que estarão presentes somente
     * quando o usuário estiver conectado.
     * */
    val itemsLogged = listOf(
            NavMenuItem(
                    R.id.item_fav_media.toLong(),
                    context.getString(R.string.item_fav_media),
                    R.drawable.ic_star_black_24dp
            ),
            NavMenuItem(
                    R.id.item_ferramentas.toLong(),
                    context.getString(R.string.item_ferramentas),
                    R.drawable.ic_settings_black_24dp
            )
    )

    fun getLastItemId() = items.last().id

    fun getFirstItemLoggedId() = itemsLogged.first().id
}