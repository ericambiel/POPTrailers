package ericambiel.com.br.smartimdb.domain

class NavMenuItem(
        val id: Long, //Id do item dentro do RV para marcação ao clicar
        val label : String,
        val iconId : Int = DEFAULT_ICON_ID ){

    companion object {
        const val DEFAULT_ICON_ID = -1
    }
}