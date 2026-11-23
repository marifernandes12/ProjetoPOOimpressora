# 🖨️ Sistema de Controle de Impressora Elgin
### *Projeto Impressora 3000*
---

## 📘 Sobre o Projeto

Este projeto foi desenvolvido pelos alunos para controlar uma impressora térmica 
Elgin i8/i9, utilizando a biblioteca E1_Impressora01.dll. O sistema possui login
e um menu com opções para conectar a impressora, imprimir textos, QR Codes, códigos de barras e realizar outras funções.
O projeto foi desenvolvido na disciplina de Programação Orientada a Objetos (POO).
---

## 🧰 Tecnologias Utilizadas

Java (OpenJDK 26) – linguagem utilizada no desenvolvimento do sistema.

IntelliJ IDEA 2025.3.5 – ambiente de desenvolvimento utilizado.

JNA 5.15.0 – biblioteca utilizada para realizar a comunicação entre Java e a DLL.

DLL E1_Impressora01 – biblioteca responsável pela comunicação com a impressora Elgin.

Impressora Elgin i8/i9 – modelos compatíveis com o sistema (testes físicos pendentes).

---

## 📁 Estrutura do Projeto

/Projeto POO EM
├── /Bibliotecas E1 Impressora   -> DLLs da Elgin necessárias para comunicação com a impressora
│   ├── /x64 / E1_Impressora01.dll
│   └── /x86 / E1_Impressora01.dll
├── /libs Java
│   └── jna-5.15.0.jar           -> Biblioteca JNA para integração do código Java com a DLL nativa
├── CANC_SAT.xml                 -> Arquivo de teste utilizado para requisições de cancelamento SAT
├── Impressora.java              -> Gerencia os comandos da impressora (textos, QR Code, código de barras, XML SAT, gaveta, etc.)
├── ImpressoraDLL.java           -> Interface JNA que mapeia os métodos e funções da DLL da impressora Elgin
├── Main.java                    -> Ponto de entrada do sistema (inicia o programa)
├── README_MODELO.md             -> Documentação e instruções do projeto
└── Usuario.java                 -> Modela a entidade do usuário e realiza a validação de credenciais de login
---

## ⚙️ Instalação e Execução

### 1. Baixar o projeto
1. Acesse o repositório no GitHub.
2. Clique em **Code -> Download ZIP**.
3. Extraia o arquivo.

### 2. Abrir no IntelliJ IDEA
1. Vá a **File -> Open** e escolha a pasta extraída.
2. Em **File -> Project Structure -> Libraries**, confirme que o `jna-5.15.0.jar` está adicionado.
3. Conecte e ligue a impressora.
4. Abra o ficheiro `Main.java` e clique em ▶ **Run**[cite: 3].

---

## 🔐 Login

Para acessar o sistema de testes da impressora, utilize as seguintes credenciais:

Utilizador: admin (substitua pelo utilizador definido no seu código)

Palavra-passe: admin (substitua pela palavra-passe definida)

Tentativas permitidas: 3 tentativas consecutivas antes de bloquear o acesso.

## 📋 Funcionalidades do Menu

Opção	O que faz
1	Configurar Conexão: Define os parâmetros iniciais (tipo, modelo, conexão e parâmetro).

2	Abrir Conexão: Inicia efetivamente a comunicação com a impressora através da DLL.

3	Imprimir Texto: Envia um texto para impressão, permitindo definir posição, estilo e tamanho.

4	Imprimir QR Code: Gera e imprime um QR Code a partir de dados fornecidos e nível de correção.

5	Imprimir Código de Barras: Imprime um código de barras configurável (tipo, dados, altura, largura e HRI).

6	Imprimir XML SAT: Lê e imprime um talão a partir de um ficheiro XML do SAT.

7	Imprimir Cancelamento SAT: Imprime o comprovativo de cancelamento utilizando um ficheiro XML e a assinatura.

8	Abrir Gaveta: Envia o comando para abrir a gaveta de dinheiro conectada.

9	Sinal Sonoro: Emite um aviso sonoro (beep) na impressora.

10	Avançar Papel: Avança o rolo de papel o número de linhas especificado.

11	Consultar Status: Verifica o estado atual da impressora (com papel, pouco papel ou sem papel).

0	Fechar Conexão e Sair: Desconecta a impressora de forma segura e encerra a aplicação.

## 🧪 Testes Realizados

Autenticação (Login):

Validar se o sistema permite o acesso apenas com as credenciais corretas.

Confirmar o bloqueio e encerramento do sistema após 3 tentativas de login falhadas.

Comunicação com a Impressora:

Testar a configuração e abertura de conexão com a impressora ligada (resultado esperado: sucesso).

Testar o comportamento do sistema caso a impressora esteja desligada ou o cabo desconectado (resultado esperado: exibição do código de erro sem quebrar o programa).

Confirmar o fecho seguro da conexão ao encerrar a aplicação.

## 📚 Documentação Adicional

Manual da impressora Elgin
Documentação do SDK E1_Impressora

---

## 👥 Autores

- Manuela Antunes Munhoz
- Mariana Fernandes Silva
- Pedro Henrique Lima Gaspar
