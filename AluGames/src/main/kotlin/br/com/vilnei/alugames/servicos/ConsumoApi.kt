package br.com.vilnei.alugames.servicos

import br.com.vilnei.alugames.modelo.*
import br.com.vilnei.alugames.ultilitario.criaJogo
import br.com.vilnei.alugames.ultilitario.criarGamer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {

    private fun consomeDados (endereco: String) : String {
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder().uri(URI.create(endereco)).build()
        val response = client.send(request, BodyHandlers.ofString())
        return response.body()
    }

    fun buscaJogo(id: String):InfoJogo {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val json = consomeDados(endereco)

        val gson = Gson()
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

        return meuInfoJogo
    }

    fun buscaGamer(): List<Gamer> {
        val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"

        val json = consomeDados(endereco)

        val gson = Gson()
        val meuGamerTipo = object : TypeToken<List<InfoGamerJson>>() {}.type
        val listaGamer: List<InfoGamerJson> = gson.fromJson(json, meuGamerTipo)

        // transformando um tipo de objeto para outro tipo de objeto
        val listaGamerConvertida = listaGamer.map { infoGamerJson -> infoGamerJson.criarGamer() }

        return listaGamerConvertida
    }

    fun buscaJogoJson(): List<Jogo> {
        val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/jogos.json"

        val json = consomeDados(endereco)

        val gson = Gson()
        val meuJogoTipo = object : TypeToken<List<InfoJogoJson>>() {}.type
        val listaJogo: List<InfoJogoJson> = gson.fromJson(json, meuJogoTipo)

        // transformando um tipo de objeto para outro tipo de objeto
        val listaJogoConvertida = listaJogo.map { infoJogoJson -> infoJogoJson.criaJogo() }

        return listaJogoConvertida
    }
}