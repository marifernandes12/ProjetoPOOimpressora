import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Usuario usuario = new Usuario("admin", "1234");
        Impressora impressora = new Impressora();

        int tentativas = 0;
        boolean logado = false;

        while (tentativas < 3 && !logado) {
            System.out.println("\n===== LOGIN =====");

            System.out.print("Usuário: ");
            String nome = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            if (usuario.verificarLogin(nome, senha)) {
                logado = true;
                System.out.println("\nLogin realizado com sucesso!");
            } else {
                tentativas++;
                System.out.println("Usuário ou senha incorretos.");
                System.out.println("Tentativas restantes: " + (3 - tentativas));
            }
        }

        if (!logado) {
            System.out.println("Número máximo de tentativas atingido.");
            scanner.close();
            return;
        }

        int opcao;

        do {
            System.out.println("\n========== MENU IMPRESSORA ==========");
            System.out.println("1 - Configurar impressora");
            System.out.println("2 - Conectar impressora");
            System.out.println("3 - Desconectar impressora");
            System.out.println("4 - Imprimir texto");
            System.out.println("5 - Imprimir QR Code");
            System.out.println("6 - Imprimir código de barras");
            System.out.println("7 - Imprimir XML SAT");
            System.out.println("8 - Imprimir XML de cancelamento");
            System.out.println("9 - Abrir gaveta");
            System.out.println("10 - Emitir sinal sonoro");
            System.out.println("11 - Avançar papel");
            System.out.println("12 - Consultar status do papel");
            System.out.println("0 - Sair");
            System.out.println("=====================================");

            opcao = lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    int tipo = lerInteiro(scanner, "Tipo da impressora: ");

                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();

                    System.out.print("Conexão: ");
                    String conexao = scanner.nextLine();

                    int parametro = lerInteiro(scanner, "Parâmetro: ");

                    impressora.configurarConexao(tipo, modelo, conexao, parametro);
                    break;

                case 2:
                    impressora.abrirConexao();
                    break;

                case 3:
                    impressora.fecharConexao();
                    break;

                case 4:
                    if (impressora.verificarConexao()) {
                        System.out.print("Digite o texto para imprimir: ");
                        String texto = scanner.nextLine();
                        impressora.imprimirTexto(texto);
                    }
                    break;

                case 5:
                    impressora.imprimirQRCode();
                    break;

                case 6:
                    impressora.imprimirCodigoBarras();
                    break;

                case 7:
                    impressora.imprimirXML();
                    break;

                case 8:
                    if (impressora.verificarConexao()) {
                        System.out.print("Caminho do XML de cancelamento: ");
                        String caminho = scanner.nextLine();

                        System.out.print("Assinatura do QR Code: ");
                        String assinatura = scanner.nextLine();

                        impressora.imprimirCancelamento(caminho, assinatura);
                    }
                    break;

                case 9:
                    impressora.abrirGaveta();
                    break;

                case 10:
                    if (impressora.verificarConexao()) {
                        int quantidade = lerInteiro(scanner, "Quantidade de bipes: ");
                        impressora.sinalSonoro(quantidade);
                    }
                    break;

                case 11:
                    if (impressora.verificarConexao()) {
                        int linhas = lerInteiro(scanner, "Quantidade de linhas: ");
                        impressora.avancarPapel(linhas);
                    }
                    break;

                case 12:
                    impressora.consultarStatus();
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    impressora.fecharConexao();
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            }
        }
    }
}