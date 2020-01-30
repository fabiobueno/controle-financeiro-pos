package com.fib.controlefinanceiro.db

import java.io.Serializable

data class Contas(
    var id: Long = 0,
    var nome: String? = null,
    var valor: Long = 0
    ) : Serializable {

    override fun toString(): String {
        return nome.toString()
    }
}