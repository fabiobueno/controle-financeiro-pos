package com.fib.controlefinanceiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fib.controlefinanceiro.db.ContaRepository
import com.fib.controlefinanceiro.db.Contas
import kotlinx.android.synthetic.main.activity_conta.*

class Conta : AppCompatActivity() {

    private var conta: Contas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conta)

        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)

        btnCadastro?.setOnClickListener {
            conta?.nome = txtNome?.text.toString()
            conta?.valor = txtValor?.text.toString().toLong()

            if(conta?.id == 0.toLong()){
                ContaRepository(this).create(conta!!)
            }else{
                ContaRepository(this).update(conta!!)
            }
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val intent = intent
        if(intent != null){
            if(intent.getSerializableExtra("conta") != null){
                conta = intent.getSerializableExtra("conta") as Contas
                txtNome?.setText(conta?.nome)
                txtValor.setText(conta?.valor.toString())
            }else{
                conta = Contas()
            }
        }
    }
}
