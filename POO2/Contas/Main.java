import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Conta contaPoupanca = new ContaPoupanca();
        Conta contaCorrente = new ContaCorrente();
        GeradorExtratos gerador = new GeradorExtratos();

        int opcaoPrincipal;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Conta Poupança");
            System.out.println("2 - Conta Corrente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = sc.nextInt();

            switch (opcaoPrincipal) {
                case 1:
                    menuConta(sc, contaPoupanca, gerador, "POUPANÇA");
                    break;
                case 2:
                    menuConta(sc, contaCorrente, gerador, "CORRENTE");
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcaoPrincipal != 0);

        sc.close();
    }

    public static void menuConta(Scanner sc, Conta conta, GeradorExtratos gerador, String tipoConta) {
        int opcao;
        double valor;

        do {
            System.out.println("\n=== MENU CONTA " + tipoConta + " ===");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Exibir extrato");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor do depósito: ");
                    valor = sc.nextDouble();
                    conta.depositar(valor);
                    break;
                case 2:
                    System.out.print("Digite o valor do saque: ");
                    valor = sc.nextDouble();
                    conta.sacar(valor);
                    break;
                case 3:
                    System.out.println(gerador.exibeExtrato(conta));
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}