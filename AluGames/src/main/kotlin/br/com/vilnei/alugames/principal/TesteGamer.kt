package br.com.vilnei.alugames.principal

import br.com.vilnei.alugames.modelo.Gamer
import br.com.vilnei.alugames.modelo.Periodo
import br.com.vilnei.alugames.servicos.ConsumoApi
import java.time.LocalDate

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscaGamer()
    val listaJogoJson = consumo.buscaJogoJson()

    val gamerCaroline = listaGamers.get(3)
    val jogoResidentVillage = listaJogoJson.get(10)


//    println(gamerCaroline)
//    println(jogoResidentVillage)

//    val periodo = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))
//    val aluguel = gamerCaroline.alugaJogo(jogoResidentVillage, periodo)
//
//    println(aluguel)


}