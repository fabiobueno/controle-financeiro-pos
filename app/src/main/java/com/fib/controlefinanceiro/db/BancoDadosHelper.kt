package com.fib.controlefinanceiro.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.*

class BancoDadosHelper(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context ,
        name = "financeiro.db",  version = 1) {

    private val scriptSQLCreate = arrayOf(
        "INSERT INTO salario VALUES(1, 1500);",
        "INSERT INTO conta VALUES(1, 'Carro', 500);",
        "INSERT INTO conta VALUES(2, 'Aluguel', 600);",
        "INSERT INTO conta VALUES(3, 'Energia', 100);"
    )

    companion object {
        private var instance: BancoDadosHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): BancoDadosHelper {
            if (instance == null) {
                instance = BancoDadosHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("salario", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "valor" to INTEGER
        )

        db.createTable("conta", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nome" to TEXT,
            "valor" to INTEGER
        )

        scriptSQLCreate.forEach {sql ->
            db.execSQL(sql)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("financeiro.db", true)
        onCreate(db)
    }

}

val Context.database: BancoDadosHelper get() = BancoDadosHelper.getInstance(getApplicationContext())