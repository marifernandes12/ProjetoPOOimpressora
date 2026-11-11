
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Usuario usuario = new Usuario("admin", "1234");
        Impressora impressora = new Impressora();

        System.out.println("==================================");
        System.out.println("      SISTEMA IMPRESSORA ELGIN" );
        System.out.println("==================================");



        boolean loginRealizado = false;

        for (int i = 1; i <= 3; i++) {

            System.out.print("\nDigite o usuario: ");
            String nome = scanner.nextLine();

            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            if (usuario.verificarLogin(nome, senha)) {

                System.out.println("\nLogin realizado com sucesso!");
                loginRealizado = true;
                break;

            } else {

                int restantes = 3 - i;

                System.out.println("Login incorreto!");

                if (restantes > 0) {
                    System.out.println("Tentativas restantes: " + restantes);
                } else {
                    System.out.println("Limite de tentativas atingido!");
                }
            }
        }

        if (!loginRealizado) {
            System.out.println("Programa encerrado.");
            scanner.close();
            return;
        }


        int opcao;

        do {

            System.out.println("\n========== MENU ELGIN ==========");
            System.out.println("1 - Configurar conexao");
            System.out.println("2 - Conectar impressora");
            System.out.println("3 - Desconectar impressora");
            System.out.println("4 - Imprimir texto");
            System.out.println("5 - Imprimir QR Code");
            System.out.println("6 - Imprimir codigo de barras");
            System.out.println("7 - Imprimir XML SAT");
            System.out.println("8 - Imprimir XML de cancelamento");
            System.out.println("9 - Abrir gaveta");
            System.out.println("10 - Emitir sinal sonoro");
            System.out.println("11 - Avancar papel");
            System.out.println("12 - Consultar status do papel");
            System.out.println("0 - Sair");
            System.out.println("================================");

            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {

                case 1:

                    int tipo = lerInteiro(
                            "Tipo (1 USB, 2 Serial, 3 Rede, 4 Bluetooth): ");

                    System.out.print("Modelo (i8 ou i9): ");
                    String modelo = scanner.nextLine();

                    System.out.print("Conexao (USB, COM2 ou IP): ");
                    String conexao = scanner.nextLine();

                    int parametro = lerInteiro("Parametro: ");

                    impressora.configurarConexao(
                            tipo, modelo, conexao, parametro);
                    break;

                case 2:

                    System.out.println("Conectando impressora...");
                    impressora.abrirConexao();
                    break;

                case 3:

                    impressora.fecharConexao();
                    break;

                case 4:

                    if (!verificarConexao(impressora)) break;

                    System.out.print("Digite o texto: ");
                    String texto = scanner.nextLine();

                    int posicao = lerInteiro(
                            "Posicao (0 esquerda, 1 centro, 2 direita): ");

                    int estilo = lerInteiro(
                            "Estilo (0 normal, 8 negrito): ");

                    int tamanho = lerInteiro("Tamanho (0 a 7): ");

                    impressora.imprimirTexto(
                            texto, posicao, estilo, tamanho);
                    break;

                case 5:

                    if (!verificarConexao(impressora)) break;

                    System.out.print("Conteudo do QR Code: ");
                    String qr = scanner.nextLine();

                    int tamanhoQR = lerInteiro("Tamanho (1 a 6): ");
                    int nivel = lerInteiro("Nivel de correcao (1 a 4): ");

                    impressora.imprimirQRCode(qr, tamanhoQR, nivel);
                    break;

                case 6:

                    if (!verificarConexao(impressora)) break;

                    int tipoCodigo = lerInteiro(
                            "Tipo (0 UPC-A, 2 EAN13, 3 EAN8, 4 CODE39, 8 CODE128): ");

                    System.out.print("Digite os dados: ");
                    String codigo = scanner.nextLine();

                    int altura = lerInteiro("Altura: ");
                    int largura = lerInteiro("Largura: ");
                    int hri = lerInteiro("HRI (1, 2, 3 ou 4): ");

                    impressora.imprimirCodigoBarras(
                            tipoCodigo, codigo, altura, largura, hri);
                    break;

                case 7:

                    if (!verificarConexao(impressora)) break;

                    System.out.print("Caminho do XML SAT: ");
                    String caminhoXML = scanner.nextLine();

                    impressora.imprimirXML(caminhoXML);
                    break;

                case 8:

                    if (!verificarConexao(impressora)) break;

                    System.out.print("Caminho do XML de cancelamento: ");
                    String caminhoCancelamento = scanner.nextLine();

                    System.out.print("Assinatura do QR Code: ");
                    String assinatura = scanner.nextLine();

                    impressora.imprimirCancelamento(
                            caminhoCancelamento, assinatura);
                    break;

                case 9:

                    if (!verificarConexao(impressora)) break;

                    impressora.abrirGaveta();
                    break;

                case 10:

                    if (!verificarConexao(impressora)) break;

                    int quantidade = lerInteiro("Quantidade de bips: ");

                    impressora.sinalSonoro(quantidade);
                    break;

                case 11:

                    if (!verificarConexao(impressora)) break;

                    int linhas = lerInteiro("Quantidade de linhas: ");

                    impressora.avancarPapel(linhas);
                    break;

                case 12:

                    if (!verificarConexao(impressora)) break;

                    impressora.consultarStatus(3);
                    break;

                case 0:

                    System.out.println("Encerrando o sistema...");
                    impressora.fecharConexao();
                    break;

                default:

                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // VERIFICA SE A IMPRESSORA ESTA CONECTADA

    public static boolean verificarConexao(Impressora impressora) {

        if (!impressora.estaConectada()) {

            System.out.println(
                    "E necessario conectar a impressora primeiro! Digite 2 para conectar.");

            return false;
        }

        return true;
    }

    public static int lerInteiro(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Digite apenas numeros!");
            }
        }
    }
}
