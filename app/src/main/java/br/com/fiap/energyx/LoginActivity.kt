package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var licencaInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var signupLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializando os elementos da UI
        licencaInput = findViewById(R.id.licenca_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        signupLink = findViewById(R.id.signup_link)

        // Ação de clique no botão de login
        loginButton.setOnClickListener {
            val licenca = licencaInput.text.toString()
            val senha = passwordInput.text.toString()

            // Validar os campos (simples validação de exemplo)
            if (licenca.isNotEmpty() && senha.isNotEmpty()) {
                // Aqui você pode adicionar a lógica de autenticação, por exemplo, verificação de credenciais
                // Caso o login seja bem-sucedido
                Toast.makeText(this, "Login bem-sucedido", Toast.LENGTH_SHORT).show()

                // Redirecionar para a MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finaliza a LoginActivity para que o usuário não consiga voltar para ela
            } else {
                // Caso os campos estejam vazios
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Ação de clique no link de cadastro
        signupLink.setOnClickListener {
            // Navegar para a tela de cadastro
            val intent = Intent(this, Cadastro1Activity::class.java)
            startActivity(intent)
        }
    }
}
