package br.com.vilnei.alugames.principal

import br.com.vilnei.alugames.modelo.Jogo
import br.com.vilnei.alugames.servicos.ConsumoApi
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para busca:")
    val busca = leitura.nextLine()

    val buscaApi = ConsumoApi()
    val informacaoJogo = buscaApi.buscaJogo(busca)

    // a ? quer dizer que esse jogo pode ser nulo, apenas pra deixar eu tratar ele antes de criar
    var meuJogo: Jogo? = null

    val resultado = runCatching {
         meuJogo = Jogo(
             informacaoJogo.info.title,
             informacaoJogo.info.thumb
        )
    }

    resultado.onFailure {
        println("Jogo Inexistente, tente outro ID.")
    }
    resultado.onSuccess {
        println("Deseja inserir uma descrição personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)){
            println("insira a descrição personalizada para o jogo")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada

        } else {
            meuJogo?.descricao = meuJogo?.titulo
        }
        println(meuJogo)
    }

}