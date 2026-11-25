# 🖨️ Sistema de Controle de Impressora Elgin
### Projeto Impressora 3000

---

## 📘 Sobre o Projeto

Este projeto foi desenvolvido por alunos para controlar uma impressora térmica **Elgin i8/i9**, utilizando a biblioteca `E1_Impressora01.dll`.

O sistema possui autenticação por login e um menu interativo com opções para conectar a impressora, imprimir textos, QR Codes, códigos de barras e realizar outras funções.

O projeto foi desenvolvido na disciplina de **Programação Orientada a Objetos (POO)**.

---

## 🧰 Tecnologias Utilizadas

- **Java (OpenJDK 26):** linguagem utilizada no desenvolvimento.
- **IntelliJ IDEA 2025.3.5:** ambiente de desenvolvimento.
- **JNA 5.15.0:** biblioteca utilizada para comunicação entre Java e DLL.
- **E1_Impressora01.dll:** biblioteca responsável pela comunicação com a impressora Elgin.
- **Elgin i8/i9:** modelos de impressoras compatíveis com o sistema.

---

## 📁 Estrutura do Projeto

```text
Projeto POO EM/
│
├── Bibliotecas E1 Impressora/
│   ├── x64/
│   │   └── E1_Impressora01.dll
│   └── x86/
│       └── E1_Impressora01.dll
│
├── libs Java/
│   └── jna-5.15.0.jar
│
├── CANC_SAT.xml
├── Impressora.java
├── ImpressoraDLL.java
├── Main.java
├── README_MODELO.md
└── Usuario.java
```

### Descrição dos arquivos

| Arquivo | Descrição |
|---|---|
| `Impressora.java` | Gerencia os comandos da impressora. |
| `ImpressoraDLL.java` | Interface JNA que mapeia as funções da DLL. |
| `Main.java` | Ponto de entrada do sistema. |
| `Usuario.java` | Realiza a validação das credenciais de login. |
| `CANC_SAT.xml` | Arquivo utilizado para testes de cancelamento SAT. |
| `jna-5.15.0.jar` | Biblioteca de integração entre Java e DLL. |

---

## ⚙️ Instalação e Execução

### 1. Baixar o projeto

1. Acesse o repositório no GitHub.
2. Clique em **Code → Download ZIP**.
3. Extraia os arquivos em seu computador.

### 2. Abrir no IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Acesse **File → Open** e selecione a pasta do projeto.
3. Entre em **File → Project Structure → Libraries**.
4. Confirme se a biblioteca `jna-5.15.0.jar` está adicionada.
5. Conecte e ligue a impressora.
6. Abra o arquivo `Main.java`.
7. Clique em ▶ **Run** para executar o sistema.

---

## 🔐 Login

Para acessar o sistema, utilize as seguintes credenciais:

| Campo | Credencial |
|---|---|
| Usuário | `admin` |
| Senha | `admin` |

> **Importante:** As credenciais podem ser alteradas diretamente no código.

**Tentativas de acesso:** 3 tentativas consecutivas antes do bloqueio do sistema.

---

## 📋 Funcionalidades do Menu

| Opção | Funcionalidade | Descrição |
|:---:|---|---|
| 1 | Configurar Conexão | Define os parâmetros iniciais da impressora. |
| 2 | Abrir Conexão | Inicia a comunicação com a impressora através da DLL. |
| 3 | Imprimir Texto | Permite imprimir textos personalizados. |
| 4 | Imprimir QR Code | Gera e imprime QR Codes. |
| 5 | Imprimir Código de Barras | Imprime códigos de barras configuráveis. |
| 6 | Imprimir XML SAT | Lê e imprime um talão a partir de um arquivo XML. |
| 7 | Imprimir Cancelamento SAT | Imprime o comprovante de cancelamento utilizando XML e assinatura. |
| 8 | Abrir Gaveta | Envia o comando para abrir a gaveta de dinheiro. |
| 9 | Sinal Sonoro | Emite um aviso sonoro pela impressora. |
| 10 | Avançar Papel | Avança o papel pela quantidade de linhas especificada. |
| 11 | Consultar Status | Verifica o estado atual da impressora. |
| 0 | Sair | Fecha a conexão e encerra o sistema. |

---

## 🧪 Testes Realizados

### 🔑 Autenticação (Login)

- [ ] Validar o acesso com as credenciais corretas.
- [ ] Verificar se o sistema rejeita credenciais incorretas.
- [ ] Confirmar o bloqueio após 3 tentativas de login malsucedidas.

### 🖨️ Comunicação com a Impressora

- [ ] Testar a configuração e abertura da conexão com a impressora ligada.
- [ ] Verificar o comportamento do sistema com a impressora desligada.
- [ ] Testar a desconexão do cabo durante a execução.
- [ ] Confirmar a exibição dos códigos de erro sem interromper inesperadamente o programa.
- [ ] Verificar o fechamento seguro da conexão ao encerrar a aplicação.

---

## 📚 Documentação Adicional

- Manual da impressora Elgin.
- Documentação do SDK E1_Impressora.

---

## 👥 Autores

- Manuela Antunes Munhoz
- Mariana Fernandes Silva
- Pedro Henrique Lima Gaspar

---

<p align="center">
  <strong>Projeto desenvolvido para a disciplina de Programação Orientada a Objetos (POO).</strong>
</p>
