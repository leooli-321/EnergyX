# Sistema de Monitoramento e Emissão de Alertas para Operadores de Usinas Nucleares

## Descrição Geral do Projeto
O projeto de **Mobile Application Development (MAD)** envolve o desenvolvimento de uma aplicação móvel, utilizando **Kotlin** com **API 33**, que faz parte de um sistema integrado para monitorar condições operacionais em usinas nucleares. 

A aplicação tem como foco:
- Monitoramento de dados em tempo real, como temperatura, pressão e níveis de radiação.
- Emissão de alertas para situações adversas ou críticas.
- Interface intuitiva para operadores acompanharem o status da usina de forma prática e eficiente.

## Funcionalidades da Aplicação Móvel
1. **Login e Cadastro do Operador**
   - Autenticação de operadores para garantir acesso seguro.

2. **Monitoramento de Dados Operacionais**
   - Exibição em tempo real de:
     - Temperatura
     - Pressão
     - Fluxo de Refrigeração
     - Níveis de Radiação

3. **Status Geral**
   - Indicadores do estado atual da usina:
     - **Normal**
     - **Alerta**
     - **Crítico**

4. **Integração Backend**
   - Comunicação com uma API REST desenvolvida em **Java Spring Boot** para envio e recebimento de dados.
   - Utilização do banco de dados **Oracle** para armazenamento de informações críticas.

## Tecnologias Utilizadas
- **Linguagem:** Kotlin (API 33)
- **Ferramentas de Design:** Figma
- **Backend:** Java Spring Boot, Oracle Database
- **Infraestrutura:** Docker

  ## Vídeo Demonstração
  - https://youtu.be/ZLosk2b6LJI

## Como Rodar a Aplicação
Para rodar o aplicativo móvel, basta configurar o ambiente Android Studio com as dependências necessárias e importar o projeto, após isso, rode o arquivo (https://github.com/leooli-321/EnergyX/blob/master/energyx-backend-main/energyx-backend-main/energyx/src/main/java/com/example/energyx/EnergyxApplication.java) para executar a coneção com o Backend. Após isso você poderá enfim rodar a aplicação via emulador e executar a aplicação.

---

**Desenvolvedores:**
- Felipe Amador | RM 553528
- Leonardo Oliveira | RM 554024
- Sara Sousa | RM 552656
