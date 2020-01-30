package com.fib.controlefinanceiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.fib.controlefinanceiro.db.ContaRepository
import com.fib.controlefinanceiro.db.Contas
import com.fib.controlefinanceiro.db.SalarioRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var contas:ArrayList<Contas>? = null
    private var contasSelecionado:Contas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val contas = arrayOf(
//            "Carro - R$500,00",
//            "Água - R$35,00",
//            "Luz - R$55,00",
//            "Aluguel - R$600,00",
//            "Luz - R$55,00"
//        )
//        var salarioLiquido = 1800.00
//        var contaMes = 1190.00
//        var saldoMes = salarioLiquido - contaMes
//        val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, contas)
//
//        val textView: TextView = findViewById(R.id.saldoMes) as TextView
//        textView.text = "R$" + saldoMes.toBigDecimal().toString()
//
//        var listaContas = listaContas
//        listaContas.setAdapter(adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.novo -> {
                Toast.makeText(this, "Cadastrar Conta", Toast.LENGTH_LONG).show()
                val intent = Intent(this, Conta::class.java)
                startActivity(intent)
                return false
            }

            R.id.Salario -> {
                Toast.makeText(this, "Configurar Salario", Toast.LENGTH_LONG).show()
                val intent = Intent(this, Salario::class.java)
                startActivity(intent)
                return false
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        contas = ContaRepository(this).findAll()

        //Não consegui rodar um forEach com contas
        var listaContasCalc = arrayOf(500,600,100)
        var contaMes = 0
        listaContasCalc.forEach {
            contaMes = (contaMes + it).toInt()
        }

        var salarioLiquido = SalarioRepository(this).getOne(1)

        var saldoMes = salarioLiquido.valor - contaMes

        val textView: TextView = findViewById(R.id.saldoMes) as TextView
        textView.text = "R$" + saldoMes.toBigDecimal().toString()

        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, contas)
        listaContas?.adapter = adapter
        adapter.notifyDataSetChanged()

        listaContas.setOnItemClickListener { _, _, position, id ->
            val intent = Intent(this, Conta::class.java)
            intent.putExtra("conta", contas?.get(position))
            startActivity(intent)
        }

    }

}
