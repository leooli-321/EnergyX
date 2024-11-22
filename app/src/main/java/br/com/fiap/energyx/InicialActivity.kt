package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ImageView

class InicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        // Referências para os elementos no layout
        val pularTextView = findViewById<TextView>(R.id.btn_skip)
        val proximoImageView = findViewById<ImageView>(R.id.btn_next)

        // Clique no "Pular"
        pularTextView.setOnClickListener {
            val intent = Intent(this, InicialLoginActivity::class.java)
            startActivity(intent)
        }

        // Clique no "Próximo"
        proximoImageView.setOnClickListener {
            val intent = Intent(this, InicialSegurancaActivity::class.java)
            startActivity(intent)
        }
    }
}
