from flask import Flask, jsonify
import datetime
import random
import threading
import time

app = Flask(__name__)

# Função que gera um horário aleatório a cada requisição
def generate_new_time():
    # Gera um horário aleatório (hora: minuto)
    hour = random.randint(0, 23)
    minute = random.randint(0, 59)
    return f"{hour:02d}h{minute:02d}"

# Lista para armazenar os horários gerados
horarios = []

# Função para atualizar o horário a cada 1 minuto
def update_horarios():
    while True:
        # Gera um novo horário
        new_time = generate_new_time()
        
        # Adiciona o horário à lista de horários
        horarios.append(new_time)

        # Aqui você pode postar esse novo horário na sua aplicação ou banco de dados
        print(f"Novo horário gerado: {new_time}")
        
        # Espera 1 minuto (60 segundos) antes de gerar o próximo horário
        time.sleep(60)

# Endpoint para retornar os horários
@app.route('/get-horarios', methods=['GET'])
def get_horarios_endpoint():
    # Se não houver horários gerados ainda, retorna uma mensagem de erro
    if not horarios:
        return jsonify({"error": "Nenhum horário gerado ainda."}), 400
    
    # Pega o último horário gerado
    current_time_label = "Horário Atual"
    current_time = horarios[-1]

    # Retorna a resposta em formato JSON
    return jsonify({
        "timeLabel": current_time_label,
        "time": current_time
    })

if __name__ == '__main__':
    # Inicia o thread de atualização de horários
    threading.Thread(target=update_horarios, daemon=True).start()
    
    # Inicia o servidor Flask
    app.run(debug=True)
