package br.com.vilnei.alugames.principal

import br.com.vilnei.alugames.modelo.Gamer
import br.com.vilnei.alugames.modelo.Jogo
import br.com.vilnei.alugames.servicos.ConsumoApi
import transformarEmIdade
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)

//pacote para transforma a data de nascimento em Idade
    println(gamer.dataNascimento?.transformarEmIdade())

    //do while ele faz um loot enquanto o caso do while for aceita
    do {
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
        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos buscados: ")
    println(gamer.jogosBuscados)
    println("\n Jogos ordenados por titulo: ")

    //aqui busca pegando o titulo como ordem
    gamer.jogosBuscados.sortBy { it?.titulo }

    //aqui busca apenas o titulo
    gamer.jogosBuscados.forEach {
        println("Título: " + it?.titulo)
    }

    //aqui busca apenas os especificos
    //os ?: e pra dizer que caso n ache ele retorna um false
    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true)?: false
    }
    println("\n Jogos filtrados: ")
    println(jogosFiltrados)

    //excluindo item da lista original
    println("Deseja excluir alum jogo da lista? S/N")
    val opcao = leitura.nextLine()

    if (opcao.equals("s", true)) {
        println("Qual a posição do jogo que deseja excluir?")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("Busca Finalizada com SUCESSO.")

}