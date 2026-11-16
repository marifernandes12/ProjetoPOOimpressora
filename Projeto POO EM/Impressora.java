
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Impressora {

    private int tipo = 1;
    private String modelo = "i9";
    private String conexao = "USB";
    private int parametro = 0;

    private boolean conexaoAberta = false;

    public boolean estaConectada() {
        return conexaoAberta;
    }

    public void configurarConexao(int tipo, String modelo,
                                  String conexao, int parametro) {

        if (conexaoAberta) {
            System.out.println("Feche a conexão antes de configurar novamente.");
            return;
        }

        if (tipo < 1 || tipo > 4) {
            System.out.println("Tipo de conexão inválido!");
            return;
        }

        if (!modelo.equalsIgnoreCase("i8")
                && !modelo.equalsIgnoreCase("i9")) {
            System.out.println("Modelo inválido! Use i8 ou i9.");
            return;
        }

        this.tipo = tipo;
        this.modelo = modelo;
        this.conexao = conexao;
        this.parametro = parametro;

        System.out.println("Configuração realizada!");
    }

    public void abrirConexao() {

        if (conexaoAberta) {
            System.out.println("A impressora já está conectada.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(
                    tipo, modelo, conexao, parametro
            );

            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Impressora conectada!");
            } else {
                conexaoAberta = false;
                System.out.println("Erro ao conectar: " + retorno);
            }

        } catch (LinkageError e) {
            conexaoAberta = false;
            System.out.println("Erro ao carregar a DLL: " + e.getMessage());
        }
    }

    public void fecharConexao() {

        if (!conexaoAberta) {
            System.out.println("Impressora já desconectada.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();

            if (retorno == 0) {
                conexaoAberta = false;
                System.out.println("Conexão encerrada!");
            } else {
                System.out.println("Erro ao desconectar: " + retorno);
            }

        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    private boolean verificarConexao() {

        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes.");
            return false;
        }

        return true;
    }

    private void finalizarImpressao() {

        int retornoAvanco = ImpressoraDLL.INSTANCE.AvancaPapel(3);

        if (retornoAvanco == 0) {
            System.out.println("Papel avançado em 3 linhas!");
        } else {
            System.out.println("Erro ao avançar papel: " + retornoAvanco);
        }

        int retornoCorte = ImpressoraDLL.INSTANCE.Corte(0);

        if (retornoCorte == 0) {
            System.out.println("Papel cortado!");
        } else {
            System.out.println("Erro ao cortar papel: " + retornoCorte);
        }
    }

    public void imprimirTexto(String texto, int posicao,
                              int estilo, int tamanho) {

        if (!verificarConexao()) return;

        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("Digite um texto válido!");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(
                    texto, posicao, estilo, tamanho
            );

            if (retorno >= 0) {
                System.out.println("Texto enviado para impressão!");
                finalizarImpressao();
            } else {
                System.out.println("Erro ao imprimir texto: " + retorno);
            }

        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    public void imprimirQRCode(String dados, int tamanho,
                               int nivelCorrecao) {

        if (!verificarConexao()) return;

        if (dados == null || dados.trim().isEmpty()) {
            System.out.println("Digite os dados do QR Code!");
            return;
        }

        if (tamanho < 1 || tamanho > 6) {
            System.out.println("Tamanho inválido! Use valores de 1 a 6.");
            return;
        }

        if (nivelCorrecao < 1 || nivelCorrecao > 4) {
            System.out.println("Nível de correção inválido! Use valores de 1 a 4.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(
                    dados, tamanho, nivelCorrecao
            );

            if (retorno == 0) {
                System.out.println("QR Code impresso com sucesso!");
                finalizarImpressao();
            } else {
                System.out.println("Erro ao imprimir QR Code: " + retorno);

                if (retorno == -51) {
                    System.out.println("Verifique o tamanho do QR Code.");
                } else if (retorno == -52) {
                    System.out.println("Verifique o nível de correção.");
                } else if (retorno == -53) {
                    System.out.println("Os dados informados são inválidos.");
                }
            }

        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    public void imprimirCodigoBarras(int tipo, String dados,
                                     int altura, int largura, int hri) {

        if (!verificarConexao()) return;

        if (dados == null || dados.trim().isEmpty()) {
            System.out.println("Digite os dados do código de barras!");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(
                    tipo, dados, altura, largura, hri
            );

            if (retorno == 0) {
                System.out.println("Código de barras impresso!");
                finalizarImpressao();
            } else {
                System.out.println("Erro no código de barras: " + retorno);
            }

        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    public void imprimirXML(String caminho) {

        if (!verificarConexao()) return;

        try {
            String xml = Files.readString(Path.of(caminho));

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(xml, 0);

            if (retorno == 0) {
                System.out.println("XML SAT impresso com sucesso!");
                finalizarImpressao();
            } else {
                System.out.println("Erro ao imprimir XML SAT: " + retorno);
            }

        } catch (IOException e) {
            System.out.println("Arquivo XML não encontrado!");
        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    public void imprimirCancelamento(String caminho, String assinatura) {

        if (!verificarConexao()) return;

        try {
            String xml = Files.readString(Path.of(caminho));

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(
                    xml, assinatura, 0
            );

            if (retorno == 0) {
                System.out.println("Cancelamento SAT impresso!");
                finalizarImpressao();
            } else {
                System.out.println("Erro no cancelamento SAT: " + retorno);
            }

        } catch (IOException e) {
            System.out.println("Arquivo XML não encontrado!");
        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    public void abrirGaveta() {

        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(0, 5, 10);
            verificarRetorno(retorno, "Abertura da gaveta");

        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    public void sinalSonoro(int quantidade) {

        if (!verificarConexao()) return;

        if (quantidade < 1 || quantidade > 63) {
            System.out.println("Quantidade de bipes inválida! Use de 1 a 63.");
            return;
        }

        try {
            // 1 equivale a 100 milissegundos.
            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(
                    quantidade, 1, 1
            );

            verificarRetorno(retorno, "Sinal sonoro");

        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    public void avancarPapel(int linhas) {

        if (!verificarConexao()) return;

        if (linhas < 1) {
            System.out.println("Informe uma quantidade válida de linhas!");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);
            verificarRetorno(retorno, "Avanço de papel");

        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    public void consultarStatus(int parametro) {

        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.StatusImpressora(parametro);

            if (parametro == 3 && retorno == 5) {
                System.out.println("A impressora possui papel.");
            } else if (parametro == 3 && retorno == 6) {
                System.out.println("O papel está acabando.");
            } else if (parametro == 3 && retorno == 7) {
                System.out.println("A impressora está sem papel.");
            } else {
                System.out.println("Código de status: " + retorno);
            }

        } catch (LinkageError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }


    private void verificarRetorno(int retorno, String operacao) {

        if (retorno == 0) {
            System.out.println(operacao + " realizado com sucesso!");
        } else {
            System.out.println("Erro em " + operacao + ". Código: " + retorno);
        }
    }
}
