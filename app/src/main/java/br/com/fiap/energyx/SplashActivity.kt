package br.com.fiap.energyx


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Usando um Handler para aguardar 2 segundos antes de redirecionar para a InicialActivity
        Handler().postDelayed({
            // Intent para a InicialActivity
            val intent = Intent(this, InicialActivity::class.java)
            startActivity(intent)
            // Finaliza a SplashActivity para que o usuário não consiga voltar a ela
            finish()
        }, 2000) // 2000ms = 2 segundos
    }
}
