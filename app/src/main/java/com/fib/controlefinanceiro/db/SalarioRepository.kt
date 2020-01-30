package com.fib.controlefinanceiro.db

import android.content.Context
import org.jetbrains.anko.db.*

class SalarioRepository(val context: Context) {

    fun getOne(id: Int) : Salarios = context.database.use {
        val salarios: Salarios

        select("salario", "id", "valor")
            .whereArgs( "id = {id}", "id" to id)
            .parseSingle(object: MapRowParser<Salarios> {
                override fun parseRow(columns: Map<String, Any?>): Salarios {
                    return Salarios(
                        id = columns.getValue("id").toString()?.toLong(),
                        valor = columns.getValue("valor").toString()?.toLong())
                }
            })

    }

    fun update(salario: Salarios) = context.database.use {
        val updateResult = update("salario",
            "valor" to salario.valor)
            .whereArgs("id = {id}","id" to salario.id).exec()
    }

}