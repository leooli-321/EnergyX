package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ImageView

class InicialSegurancaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial_seguranca)

        // Referências para os elementos no layout
        val proximoImageView = findViewById<ImageView>(R.id.imageView2)

        // Clique no "Próximo"
        proximoImageView.setOnClickListener {
            val intent = Intent(this, InicialLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
