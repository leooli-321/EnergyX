package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pegando os cards pelo ID
        val cardTemperatura = findViewById<LinearLayout>(R.id.cardTemperatura)
        val cardPressao = findViewById<LinearLayout>(R.id.cardPressao)
        val cardRadiacao = findViewById<LinearLayout>(R.id.cardRadiacao)
        val cardRefrigeracao = findViewById<LinearLayout>(R.id.cardRefrigeracao)

        // Navegação ao clicar no Card de Temperatura
        cardTemperatura.setOnClickListener {
            val intent = Intent(this, TemperaturaNucleoActivity::class.java)
            startActivity(intent)
        }

        // Navegação ao clicar no Card de Pressão
        cardPressao.setOnClickListener {
            val intent = Intent(this, PressaoReatorActivity::class.java)
            startActivity(intent)
        }

        // Navegação ao clicar no Card de Níveis de Radiação
        cardRadiacao.setOnClickListener {
            val intent = Intent(this, NiveisRadiacaoActivity::class.java)
            startActivity(intent)
        }

        // Navegação ao clicar no Card de Fluxo de Refrigeração
        cardRefrigeracao.setOnClickListener {
            val intent = Intent(this, FluxoRefrigeracaoActivity::class.java)
            startActivity(intent)
        }
    }
}
