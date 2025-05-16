package br.com.vilnei.alugames.ultilitario

import br.com.vilnei.alugames.modelo.InfoJogoJson
import br.com.vilnei.alugames.modelo.Jogo

fun InfoJogoJson.criaJogo(): Jogo {
    return Jogo(this.titulo, this.capa,this.preco, this.descricao)
}