package com.fib.controlefinanceiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fib.controlefinanceiro.db.SalarioRepository
import com.fib.controlefinanceiro.db.Salarios
import kotlinx.android.synthetic.main.activity_salario.*

class Salario : AppCompatActivity() {

    private var salarios: Salarios? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)

        val salarioAtual = SalarioRepository(this).getOne(1)

        txtSalario.setText(salarioAtual?.valor.toString())

        btnCadastro?.setOnClickListener {
            salarios?.valor = txtSalario?.text.toString().toLong()
            SalarioRepository(this).update(salarios!!)
            finish()
        }
    }
}
