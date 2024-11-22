package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private lateinit var licencaInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: MaterialButton
    private lateinit var signupLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializando elementos da UI
        licencaInput = findViewById(R.id.licenca_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        signupLink = findViewById(R.id.signup_link)

        // Ação de login
        loginButton.setOnClickListener {
            val licenca = licencaInput.text.toString().trim()
            val senha = passwordInput.text.toString().trim()

            if (licenca.isNotEmpty() && senha.isNotEmpty()) {
                autenticarUsuario(licenca, senha)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Link para a tela de cadastro
        signupLink.setOnClickListener {
            val intent = Intent(this, Cadastro1Activity::class.java)
            startActivity(intent)
        }
    }

    // Função de autenticação do usuário
    private fun autenticarUsuario(lor: String, senhaOperador: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // URL do endpoint de login
                val url = "http://10.0.2.2:8080/api/operadores/login"

                // Criando o corpo da requisição em JSON
                val jsonBody = JSONObject().apply {
                    put("lor", lor)
                    put("senhaOperador", senhaOperador)
                }

                val body = jsonBody.toString().toRequestBody("application/json".toMediaTypeOrNull())

                // Criando a requisição HTTP POST
                val request = Request.Builder()
                    .url(url)
                    .post(body)
                    .build()

                // Cliente HTTP
                val client = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)  // Timeout de conexão
                    .writeTimeout(10, TimeUnit.SECONDS)    // Timeout de escrita
                    .readTimeout(10, TimeUnit.SECONDS)     // Timeout de leitura
                    .build()

                // Enviando a requisição e aguardando a resposta
                val response = client.newCall(request).execute()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Login bem-sucedido
                        val responseBody = response.body?.string()
                        Toast.makeText(applicationContext, responseBody ?: "Login bem-sucedido", Toast.LENGTH_SHORT).show()

                        // Redirecionando para a tela principal após login
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Credenciais inválidas
                        Toast.makeText(applicationContext, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                // Tratamento de erros (ex: sem internet)
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Erro ao realizar o login", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
