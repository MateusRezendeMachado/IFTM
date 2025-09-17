import java.util.Scanner;

class JogadorDeFutebol {
    private String nome, posicao, nacionalidade;
    private int anoNascimento;
    private double altura, peso;

    public JogadorDeFutebol(String nome, String posicao, int anoNascimento,
                            String nacionalidade, double altura, double peso) {
        this.nome = nome;
        this.posicao = posicao;
        this.anoNascimento = anoNascimento;
        this.nacionalidade = nacionalidade;
        this.altura = altura;
        this.peso = peso;
    }

    public int calculaIdade() {
        int anoAtual = java.time.Year.now().getValue();
        return anoAtual - anoNascimento;
    }

    public void exibeDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Ano de Nascimento: " + anoNascimento);
        System.out.println("Altura: " + altura + " m");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Nacionalidade: " + nacionalidade);
        System.out.println("Posição: " + posicao);
    }

    // encapsulamento
    public String getNome() {
        return nome;
    }

    public String getPosicao() {
        return posicao;
    }
}

public class App {
    public static void main(String[] args) {
        JogadorDeFutebol jogador = leDados();
        jogador.exibeDados();
        System.out.println(calculaAposentadoria(jogador));
    }

    public static JogadorDeFutebol leDados() {
        Scanner s = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = s.nextLine();
        System.out.print("Posição (atacante/defesa/meio de campo): ");
        String posicao = s.nextLine();
        System.out.print("Ano de nascimento: ");
        int ano = s.nextInt();
        s.nextLine(); 
        System.out.print("Nacionalidade: ");
        String nac = s.nextLine();
        System.out.print("Altura (m): ");
        double altura = s.nextDouble();
        System.out.print("Peso (kg): ");
        double peso = s.nextDouble();

        return new JogadorDeFutebol(nome, posicao, ano, nac, altura, peso);
    }

    public static String calculaAposentadoria(JogadorDeFutebol j) {
        int idade = j.calculaIdade();
        int idadeAposentar;

        switch (j.getPosicao().toLowerCase()) {
            case "atacante":
                idadeAposentar = 35;
                break;
            case "defesa":
                idadeAposentar = 40;
                break;
            case "meio de campo":
                idadeAposentar = 38;
                break;
            default:
                return "Posição inválida!";
        }

        int anosFaltando = idadeAposentar - idade;
        return "Faltam " + anosFaltando + " anos para o jogador " + j.getNome() + " se aposentar.";
    }
}
