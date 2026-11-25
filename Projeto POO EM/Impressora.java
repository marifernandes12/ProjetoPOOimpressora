import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Impressora {

    private int tipo = 1;
    private String modelo = "i9";
    private String conexao = "USB";
    private int parametro = 0;
    private boolean conexaoAberta = false;

    public void configurarConexao(int tipo, String modelo, String conexao, int parametro) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.conexao = conexao;
        this.parametro = parametro;
        System.out.println("Configuracoes salvas!");
    }

    public boolean verificarConexao() {
        if (!conexaoAberta) {
            System.out.println("E necessario conectar a impressora primeiro! Digite 2 para conectar.");
            return false;
        }
        return true;
    }

    public void abrirConexao() {
        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(
                    tipo, modelo, conexao, parametro
            );

            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Impressora conectada com sucesso!");
            } else {
                conexaoAberta = false;
                System.out.println("Erro ao conectar. Codigo: " + retorno);
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao carregar a DLL.");
        }
    }

    public void fecharConexao() {
        if (!conexaoAberta) {
            System.out.println("A impressora ja esta desconectada.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();

            if (retorno == 0) {
                conexaoAberta = false;
                System.out.println("Impressora desconectada!");
            } else {
                System.out.println("Erro ao desconectar. Codigo: " + retorno);
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
        }
    }

    public void imprimirTexto(String texto) {
        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(texto, 1, 4, 0);

            if (retorno >= 0) {
                System.out.println("Texto enviado para impressao!");
                finalizarImpressao();
            } else {
                System.out.println("Erro ao imprimir texto. Codigo: " + retorno);
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
        }
    }

    public void imprimirQRCode() {
        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode("TESTE", 6, 4);

            if (retorno == 0) {
                System.out.println("QR Code enviado para impressao!");
                finalizarImpressao();
            } else {
                System.out.println("Erro ao imprimir QR Code. Codigo: " + retorno);
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
        }
    }

    public void imprimirCodigoBarras() {
        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(
                    8, "A12345678912", 100, 2, 3
            );

            if (retorno == 0) {
                System.out.println("Codigo de barras enviado para impressao!");
                finalizarImpressao();
            } else {
                System.out.println("Erro ao imprimir codigo de barras. Codigo: " + retorno);
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
        }
    }

    public void imprimirXML() {
        if (!verificarConexao()) return;

        try {
            String xml = Files.readString(Path.of("XMLSAT.xml"));
            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(xml, 0);

            if (retorno == 0) {
                System.out.println("XML enviado para impressao!");
                finalizarImpressao();
            } else {
                System.out.println("Erro ao imprimir XML. Codigo: " + retorno);
            }
        } catch (IOException e) {
            System.out.println("Nao foi possivel encontrar o arquivo XMLSAT.xml.");
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
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
                System.out.println("XML de cancelamento enviado!");
                finalizarImpressao();
            } else {
                System.out.println("Erro ao imprimir cancelamento. Codigo: " + retorno);
            }
        } catch (IOException e) {
            System.out.println("Nao foi possivel ler o arquivo.");
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
        }
    }

    public void abrirGaveta() {
        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(0, 5, 10);

            if (retorno == 0) {
                System.out.println("Comando para abrir a gaveta enviado!");
            } else {
                System.out.println("Erro ao abrir gaveta. Codigo: " + retorno);
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
        }
    }

    public void sinalSonoro(int quantidade) {
        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(quantidade, 50, 5);

            if (retorno == 0) {
                System.out.println("Sinal sonoro enviado!");
            } else {
                System.out.println("Erro no sinal sonoro. Codigo: " + retorno);
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
        }
    }

    public void avancarPapel(int linhas) {
        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);

            if (retorno == 0) {
                System.out.println("Papel avancado!");
            } else {
                System.out.println("Erro ao avancar papel. Codigo: " + retorno);
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao acessar a DLL.");
        }
    }

    public void consultarStatus() {
        if (!verificarConexao()) return;

        try {
            int retorno = ImpressoraDLL.INSTANCE.StatusImpressora(3);

            switch (retorno) {
                case 5:
                    System.out.println("Papel presente.");
                    break;
                case 6:
                    System.out.println("Pouco papel.");
                    break;
                case 7:
                    System.out.println("Sem papel.");
                    break;
                default:
                    System.out.println("Status retornado: " + retorno);
                    break;
            }
        } catch (LinkageError e) {
            System.out.println("Erro ao consultar a impressora.");
        }
    }

    private void finalizarImpressao() {
        try {
            ImpressoraDLL.INSTANCE.AvancaPapel(3);
            ImpressoraDLL.INSTANCE.Corte(0);
        } catch (LinkageError e) {
            System.out.println("Erro ao finalizar impressao.");
        }
    }
}
//