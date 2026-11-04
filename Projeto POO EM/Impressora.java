
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

        this.tipo = tipo;
        this.modelo = modelo;
        this.conexao = conexao;
        this.parametro = parametro;

        System.out.println("Configuracao realizada!");
    }

    public void abrirConexao() {
        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(
                    tipo, modelo, conexao, parametro);

            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Impressora conectada!");
            } else {
                conexaoAberta = false;
                System.out.println("Erro ao conectar: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            conexaoAberta = false;
            System.out.println("Erro ao carregar DLL: " + e.getMessage());
        }
    }

    public void fecharConexao() {
        if (!conexaoAberta) {
            System.out.println("Impressora ja desconectada.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();

            if (retorno == 0) {
                conexaoAberta = false;
                System.out.println("Conexao encerrada!");
            } else {
                System.out.println("Erro ao desconectar: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void imprimirTexto(String texto, int posicao,
                              int estilo, int tamanho) {
        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(
                    texto, posicao, estilo, tamanho);

            if (retorno >= 0) {
                System.out.println("Texto enviado para impressao!");
            } else {
                System.out.println("Erro ao imprimir: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void imprimirQRCode(String dados, int tamanho,
                               int nivelCorrecao) {
        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(
                    dados, tamanho, nivelCorrecao);

            verificarRetorno(retorno, "QR Code");

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void imprimirCodigoBarras(int tipo, String dados,
                                     int altura, int largura, int hri) {
        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(
                    tipo, dados, altura, largura, hri);

            verificarRetorno(retorno, "Codigo de barras");

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void imprimirXML(String caminho) {
        try {
            String xml = Files.readString(Path.of(caminho));

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(xml, 0);

            verificarRetorno(retorno, "XML SAT");

        } catch (IOException e) {
            System.out.println("Arquivo XML nao encontrado!");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void imprimirCancelamento(String caminho, String assinatura) {
        try {
            String xml = Files.readString(Path.of(caminho));

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(
                    xml, assinatura, 0);

            verificarRetorno(retorno, "Cancelamento SAT");

        } catch (IOException e) {
            System.out.println("Arquivo XML nao encontrado!");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void abrirGaveta() {
        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(0, 5, 10);
            verificarRetorno(retorno, "Abertura da gaveta");

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void sinalSonoro(int quantidade) {
        try {
            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(
                    quantidade, 50, 5);

            verificarRetorno(retorno, "Sinal sonoro");

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void avancarPapel(int linhas) {
        try {
            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);
            verificarRetorno(retorno, "Avanco de papel");

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    public void consultarStatus(int parametro) {
        try {
            int retorno = ImpressoraDLL.INSTANCE.StatusImpressora(parametro);

            if (parametro == 3 && retorno == 5) {
                System.out.println("A impressora possui papel.");
            } else if (parametro == 3 && retorno == 6) {
                System.out.println("O papel esta acabando.");
            } else if (parametro == 3 && retorno == 7) {
                System.out.println("A impressora esta sem papel.");
            } else {
                System.out.println("Codigo de status: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Erro na DLL: " + e.getMessage());
        }
    }

    private void verificarRetorno(int retorno, String operacao) {
        if (retorno == 0) {
            System.out.println(operacao + " realizado com sucesso!");
        } else {
            System.out.println("Erro em " + operacao
                    + ". Codigo: " + retorno);
        }
    }
}
