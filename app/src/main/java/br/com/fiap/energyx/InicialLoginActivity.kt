package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InicialLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial_login)

        // Referenciando os botões
        val buttonLogin = findViewById<Button>(R.id.button)
        val buttonCadastro = findViewById<Button>(R.id.cadastro)

        // Navegação para Login
        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Navegação para Cadastro
        buttonCadastro.setOnClickListener {
            val intent = Intent(this, Cadastro1Activity::class.java)
            startActivity(intent)
        }
    }
}
