package br.com.vilnei.alugames.ultilitario

import br.com.vilnei.alugames.modelo.Gamer
import br.com.vilnei.alugames.modelo.InfoGamerJson

// apenas transforma um infoGamerJson em um tipo Gamer
fun InfoGamerJson.criarGamer(): Gamer {
    return Gamer(this.nome, this.email, this.dataNascimento, this.usuario)
}