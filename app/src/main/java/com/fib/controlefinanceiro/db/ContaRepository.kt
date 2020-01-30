package com.fib.controlefinanceiro.db

import android.content.Context
import org.jetbrains.anko.db.*

class ContaRepository(val context: Context) {

    fun findAll() : ArrayList<Contas> = context.database.use {
        val contas = ArrayList<Contas>()

        select("conta", "id", "valor", "nome")
            .parseList(object: MapRowParser<List<Contas>> {
                override fun parseRow(columns: Map<String, Any?>): List<Contas> {
                    val id = columns.getValue("id")
                    val nome = columns.getValue("nome")
                    val valor = columns.getValue("valor")

                    val contaFind = Contas(
                        id.toString()?.toLong(),
                        nome?.toString(),
                        valor.toString()?.toLong())
                    contas.add(contaFind)
                    return contas
                }
            })

        contas
    }

    fun create(conta: Contas) = context.database.use {
        insert("conta",
            "nome" to conta.nome,
            "valor" to conta.valor)
    }

    fun update(conta: Contas) = context.database.use {
        val updateResult = update("conta",
            "valor" to conta.valor,
            "nome" to conta.nome)
            .whereArgs("id = {id}","id" to conta.id).exec()
    }

    fun delete(id: Long) = context.database.use {
        delete("conta", "id = {contaId}", args = *arrayOf("contaId" to id))
    }

}