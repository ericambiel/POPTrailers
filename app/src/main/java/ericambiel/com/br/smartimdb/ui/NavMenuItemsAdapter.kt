package ericambiel.com.br.smartimdb.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import ericambiel.com.br.smartimdb.R
import ericambiel.com.br.smartimdb.domain.NavMenuItem
import ericambiel.com.br.smartimdb.util.NavMenuItemDetails

class NavMenuItemsAdapter (private val items: List<NavMenuItem> ) :
        RecyclerView.Adapter<NavMenuItemsAdapter.ViewHolder>() {

    //Responsavel pela selçao dentro do RV
    lateinit var selectionTracker: SelectionTracker<Long>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        type: Int ): ViewHolder {
            val layout = LayoutInflater
                    .from( parent.context )
                    .inflate(
                            R.layout.nav_menu_item,
                            parent,
                            false
                    )
            return ViewHolder( layout )
    }

    override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int ) {

        holder.setData( items[ position ] )
    }

    override fun getItemCount() = items.size

    inner class ViewHolder( itemView: View) :
            RecyclerView.ViewHolder( itemView ){

        private val ivIcon : ImageView = itemView.findViewById( R.id.iv_icon )
        private val tvLabel : TextView = itemView.findViewById( R.id.tv_label )

        val itemDetails: NavMenuItemDetails = NavMenuItemDetails()

        fun setData( item: NavMenuItem ){

            tvLabel.text = item.label
            itemDetails.item = item
            itemDetails.adapterPosition = adapterPosition

            if( item.iconId != NavMenuItem.DEFAULT_ICON_ID ){
                ivIcon.setImageResource( item.iconId )
                ivIcon.visibility = View.VISIBLE
            }
            else{
                ivIcon.visibility = View.GONE
            }

            /*
            * São nos blocos condicionais a seguir que devem vir os
            * algoritmos de atualização de UI, isso para indicar o
            * item selecionado e os itens não selecionados.
            * */

            if( selectionTracker.isSelected( itemDetails.selectionKey) ){
                itemView.setBackgroundColor(
                        ContextCompat.getColor(
                                itemView.context,
                                R.color.navItemSelectedColor
                        )
                )
            }
            else{
                itemView.setBackgroundColor( Color.TRANSPARENT )
            }
        }
    }
}