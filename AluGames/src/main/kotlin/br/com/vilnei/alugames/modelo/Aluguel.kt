package br.com.vilnei.alugames.modelo


data class Aluguel(val gamer: Gamer, val jogo: Jogo, val periodo: Periodo) {

    private val valorDoAluguel:Double = gamer.plano.obterValor(this)

    override fun toString(): String {
        return "Aluguel do ${jogo.titulo} por ${gamer.nome} pelo valor R$=${valorDoAluguel}"
    }
}
