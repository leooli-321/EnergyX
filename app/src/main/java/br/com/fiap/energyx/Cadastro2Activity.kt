package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

class Cadastro2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro2)

        // Inicializando os campos
        val inputLor: EditText = findViewById(R.id.input_lor)
        val inputSenha: EditText = findViewById(R.id.input_senha)
        val inputRepetirSenha: EditText = findViewById(R.id.input_repetir_senha)
        val checkboxTerms: CheckBox = findViewById(R.id.checkbox_terms)
        val buttonConcluir: Button = findViewById(R.id.button_concluir)

        // Ação para o botão de concluir cadastro
        buttonConcluir.setOnClickListener {
            val lor = inputLor.text.toString()
            val senha = inputSenha.text.toString()
            val repetirSenha = inputRepetirSenha.text.toString()

            // Verificando se os campos estão preenchidos e se as senhas coincidem
            if (lor.isEmpty() || senha.isEmpty() || repetirSenha.isEmpty()) {
                // Exibir mensagem de erro (como um Toast, por exemplo)
                showMessage("Preencha todos os campos.")
            } else if (senha != repetirSenha) {
                // Exibir mensagem de erro (como um Toast)
                showMessage("As senhas não coincidem.")
            } else if (!checkboxTerms.isChecked) {
                // Verifica se o usuário aceitou os termos
                showMessage("Você precisa aceitar os termos.")
            } else {
                // Chama a função para enviar os dados para o banco de dados
                if (insertUserData(lor, senha)) {
                    // Cadastro válido, redirecionar para a MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()  // Fecha a Cadastro2Activity
                } else {
                    // Caso haja um erro ao salvar no banco
                    showMessage("Erro ao salvar os dados.")
                }
            }
        }

        // Ação para o botão de voltar
        val backButton: ImageView = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    // Função para exibir uma mensagem
    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Função para inserir os dados no banco de dados
    private fun insertUserData(lor: String, senha: String): Boolean {
        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var result: Boolean = false

        try {
            // Estabelecendo a conexão com o banco de dados
            val url = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"
            val user = "rm554024@fiap.com.br"  // Credenciais do banco
            val password = "180995"
            connection = DriverManager.getConnection(url, user, password)

            // Preparando a query SQL para inserir os dados
            val sql = "INSERT INTO tabela_nome (LOR, SENHA_OPERADOR) VALUES (?, ?)"
            statement = connection.prepareStatement(sql)
            statement.setString(1, lor)
            statement.setString(2, senha)

            // Executando a inserção no banco de dados
            val rowsAffected = statement.executeUpdate()
            result = rowsAffected > 0  // Se mais de 0 linhas foram afetadas, a inserção foi bem-sucedida

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                statement?.close()
                connection?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return result
    }
}
