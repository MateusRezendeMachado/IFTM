import java.util.Scanner;

public class Main {

    static int[][] tabuleiro = new int[3][3];

    public static int inter(int jog) {
        Scanner posi = new Scanner(System.in);
        System.out.println("\nTabuleiro Atual:\n");

        int pos = 1; 

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == 1)
                    System.out.print(" X ");
                else if (tabuleiro[i][j] == 2)
                    System.out.print(" O ");
                else
                    System.out.print(" " + pos + " ");

                if (j < 2) System.out.print("|");
                pos++;
            }
            System.out.println();
            if (i < 2) System.out.println("---+---+---");
        }

        System.out.println();
        if (jog == 1)
            System.out.println("Jogador X, é sua vez!");
        else
            System.out.println("Jogador O, é sua vez!");

        System.out.print("Escolha uma posição: ");
        return posi.nextInt();
    }

    public static boolean validacao(int pos) {
        if (pos < 1 || pos > 9)
            return false;

        int linha = (pos - 1) / 3;
        int coluna = (pos - 1) % 3;

        return tabuleiro[linha][coluna] == 0;
    }

    public static void restricao() {
        System.out.println("Jogada inválida! Tente novamente.");
    }

    public static boolean empate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == 0) {
                    return false;
                }
            }
        }
        return true; 
    }

    public static boolean vitoria() {
        
        // Linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] != 0 && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2])
                return true;
            if (tabuleiro[0][i] != 0 && tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i])
                return true;
        }

        // Diagonais
        if (tabuleiro[0][0] != 0 && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2])
            return true;
        if (tabuleiro[0][2] != 0 && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0])
            return true;

        return false;
    }

    static void exibeFim(int vencedor) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == 1)
                    System.out.print(" X ");
                else if (tabuleiro[i][j] == 2)
                    System.out.print(" O ");
                else
                    System.out.print(" - ");

                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("---+---+---");
        }

        if (vencedor == 0)
            System.out.println("Deu velha!");
        else
            System.out.println("Jogador " + (vencedor == 1 ? "X" : "O") + " venceu!");
    }

    public static void main(String[] args) {
        int jogador = 1;
        boolean fim = false;

        while (!fim) {
            int pos = inter(jogador);

            if (validacao(pos)) {
                int linha = (pos - 1) / 3;
                int coluna = (pos - 1) % 3;
                tabuleiro[linha][coluna] = jogador;

                if (vitoria()) {
                    exibeFim(jogador);
                    fim = true;
                } else if (empate()) {
                    exibeFim(0);
                    fim = true;
                } else {
                    jogador = (jogador == 1) ? 2 : 1;
                }

            } else {
                restricao();
            }
        }
    }
}

