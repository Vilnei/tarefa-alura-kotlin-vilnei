package br.com.vilnei.alugames.modelo

data class Jogo (val titulo:String,val capa:String){

    // lembrando que a ? diz q esse valor pode ser null e sempre q e ultilizado tem q ser dito o tipo
    var descricao:String? = null

    override fun toString(): String {
        return "Jogo: $titulo \n" +
                "Capa: $capa \n" +
                "Descrição: $descricao"
    }

}