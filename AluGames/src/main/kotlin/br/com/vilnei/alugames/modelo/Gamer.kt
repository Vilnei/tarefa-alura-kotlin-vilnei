package br.com.vilnei.alugames.modelo

import java.util.Scanner
import kotlin.random.Random

data class Gamer(var nome: String, var email: String) {
    var dataNascimento: String? = null

    //alguma modificaçoes que da pra fazer com variaveis, como por as condiçoes ligadas sem precisar criar a função
    var usuario:String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }

    //colocando o private set vc deixa o get publico porem modficaçoes travadas.
    var idInterno:String? = null
        private set

    var plano: PlanoAvulso = PlanoAvulso("BRONZE")

    //isntancia uma lista mutavel do tipo Jogo
    val jogosBuscados = mutableListOf<Jogo?>()

    val jogosAlugados = mutableListOf<Aluguel>()

    constructor(nome:String, email:String, dataNascimento:String, usuario:String): this (nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }


    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        this.idInterno = "$usuario#$tag"
    }

    fun validarEmail():String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        }else {
            throw IllegalArgumentException("Email invalido")
        }
    }

    fun alugaJogo(jogo: Jogo, periodo: Periodo) : Aluguel {

        val aluguel = Aluguel(this, jogo, periodo)
        jogosAlugados.add(aluguel)

        return aluguel
    }

    fun jogosDoMes (mes: Int) : List<Jogo> {
        //filtra nos jogos alugados o mes de acordo com a data incial do aluguel e o jogo q foi alugado naquele mes
        return jogosAlugados.filter {
            aluguel -> aluguel.periodo.dataInicial.monthValue == mes }
            .map { aluguel -> aluguel.jogo }
    }

    //esse e um bloco de codigo que sera executado antes de inicializa a classe
    init {
        if (nome.isNullOrBlank()) {
            throw IllegalArgumentException("Nome esta em branco")
        }
        this.email = validarEmail()
    }

    //é um recurso que permite criar elementos estáticos dentro de uma classe, para que vc possa cria sem instanciar
    companion object {

        fun criarGamer (leitura: Scanner):Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome: ")
            val nome = leitura.nextLine()
            println("Digite seu email: ")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? S/N")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento DD/MM/AAAA: ")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário: ")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)

            } else {

                return Gamer(nome, email)

            }

        }

    }

 
}
