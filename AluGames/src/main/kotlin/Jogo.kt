package br.com.vilnei

data class Jogo (val titulo:String,val capa:String){
    val descricao = ""

    override fun toString(): String {
        return "Jogo: $titulo \n" +
                "Capa: $capa \n" +
                "Descrição: $descricao"
    }

}