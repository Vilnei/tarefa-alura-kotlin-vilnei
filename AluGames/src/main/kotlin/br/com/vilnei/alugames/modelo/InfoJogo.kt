package br.com.vilnei.alugames.modelo

data class InfoJogo (val info: InfoApiShark) {
    override fun toString(): String {
        return info.toString()
    }
}
