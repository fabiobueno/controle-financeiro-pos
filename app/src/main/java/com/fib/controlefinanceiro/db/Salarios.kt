package com.fib.controlefinanceiro.db

import java.io.Serializable

data class Salarios(
    var id: Long = 0,
    var valor: Long = 0
    ) : Serializable {

    override fun toString(): String {
        return valor.toString()
    }
}