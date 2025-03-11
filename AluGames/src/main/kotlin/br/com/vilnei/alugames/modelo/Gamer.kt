package br.com.vilnei.alugames.modelo

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


    //esse e um bloco de codigo que sera executado antes de inicializa a classe
    init {
        if (nome.isNullOrBlank()) {
            throw IllegalArgumentException("Nome esta em branco")
        }
        this.email = validarEmail()
    }

}
