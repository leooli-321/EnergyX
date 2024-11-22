package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputEditText
import android.widget.ImageView
import com.google.android.material.button.MaterialButton
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

class Cadastro1Activity : AppCompatActivity() {

    private lateinit var nomeInput: TextInputEditText
    private lateinit var senhaInput: TextInputEditText
    private lateinit var cargoInput: TextInputEditText
    private lateinit var lorInput: TextInputEditText
    private lateinit var turnoManha: MaterialRadioButton
    private lateinit var turnoTarde: MaterialRadioButton
    private lateinit var turnoNoite: MaterialRadioButton
    private lateinit var nextButton: MaterialButton // Use MaterialButton ao invés de ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro1)

        // Inicializa os campos de entrada
        nomeInput = findViewById(R.id.input_nome)
        senhaInput = findViewById(R.id.input_senha)
        cargoInput = findViewById(R.id.input_cargo)
        lorInput = findViewById(R.id.input_lor)
        turnoManha = findViewById(R.id.turno_manha)
        turnoTarde = findViewById(R.id.turno_tarde)
        turnoNoite = findViewById(R.id.turno_noite)
        nextButton = findViewById(R.id.button_concluir)

        // Ação do botão "Próximo"
        nextButton.setOnClickListener {
            // Validar campos e enviar os dados
            val nomeOperador = nomeInput.text.toString().trim()
            val senhaOperador = senhaInput.text.toString().trim()
            val cargo = cargoInput.text.toString().trim()
            val lor = lorInput.text.toString().trim() //12345-CNEN

            // Validação dos campos obrigatórios
            if (nomeOperador.isEmpty() || senhaOperador.isEmpty() || cargo.isEmpty() || lor.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar se um turno foi selecionado
            val turnoId = when {
                turnoManha.isChecked -> 1
                turnoTarde.isChecked -> 2
                turnoNoite.isChecked -> 3
                else -> {
                    Toast.makeText(this, "Por favor, selecione um turno.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Preparar os dados para enviar à API
            val jsonBody = """
                {
                    "nomeOperador": "$nomeOperador",
                    "senhaOperador": "$senhaOperador",
                    "cargo": "$cargo",
                    "turnoId": $turnoId,
                    "lor": "$lor"
                }
            """.trimIndent()

            // Enviar os dados para a API
            sendDataToApi(jsonBody)
        }
    }

    private fun sendDataToApi(jsonBody: String) {
        val url = "http://10.0.2.2:8080/api/operadores/cadastro"

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val requestBody = jsonBody
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                runOnUiThread {
                    when {
                        response.isSuccessful -> {
                            // Exibe o primeiro toast "Cadastro bem sucedido"
                            Toast.makeText(this@Cadastro1Activity, "Cadastro bem sucedido", Toast.LENGTH_SHORT).show()

                            // Exibe o segundo toast "Agora faça Login"
                            Toast.makeText(this@Cadastro1Activity, "Agora faça Login", Toast.LENGTH_SHORT).show()

                            // Atraso para que o segundo toast seja visível antes de redirecionar
                            android.os.Handler().postDelayed({
                                val intent = Intent(this@Cadastro1Activity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()  // Finaliza a atividade atual
                            }, 2000) // Delay de 2 segundos para garantir que o Toast apareça
                        }
                        response.code == 400 -> {
                            Toast.makeText(this@Cadastro1Activity, "Erro de validação", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this@Cadastro1Activity, "Erro ao cadastrar", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@Cadastro1Activity, "Falha na conexão", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        })
    }
}
