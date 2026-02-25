package mateusRezende_TipoGenerico;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ler dados das pessoas
        System.out.println("=== CADASTRO DE PESSOAS ===");
        Vetor<Pessoa> pessoas = lerPessoas(scanner);

        // Ler dados das cidades
        System.out.println("\n=== CADASTRO DE CIDADES ===");
        Vetor<Cidade> cidades = lerCidades(scanner);

        // Buscar pessoa
        System.out.print("\nDigite o nome da pessoa para buscar: ");
        String nomeBusca = scanner.nextLine();

        Pessoa pessoaEncontrada = buscarPessoa(pessoas, nomeBusca);
        String saida = gerarSaida(pessoaEncontrada, cidades);
        System.out.println(saida);

        scanner.close();
    }

    private static Vetor<Pessoa> lerPessoas(Scanner scanner) {
        System.out.print("Quantas pessoas deseja cadastrar? ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        Vetor<Pessoa> vetorPessoas = new Vetor<>(quantidade);

        for (int i = 0; i < quantidade; i++) {
            System.out.println("\nPessoa " + (i + 1) + ":");
            
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Sexo (M/F): ");
            char sexo = scanner.nextLine().toUpperCase().charAt(0);
            
            System.out.print("Naturalidade (cidade): ");
            String naturalidade = scanner.nextLine();

            Pessoa pessoa = new Pessoa(nome, sexo, naturalidade);
            vetorPessoas.adicionar(pessoa);
        }

        return vetorPessoas;
    }

    private static Vetor<Cidade> lerCidades(Scanner scanner) {
        System.out.print("Quantas cidades deseja cadastrar? ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        Vetor<Cidade> vetorCidades = new Vetor<>(quantidade);

        for (int i = 0; i < quantidade; i++) {
            System.out.println("\nCidade " + (i + 1) + ":");
            
            System.out.print("Nome da cidade: ");
            String nome = scanner.nextLine();
            
            System.out.print("Adjetivo pátrio: ");
            String adjetivo = scanner.nextLine();
            
            System.out.print("Estado: ");
            String estado = scanner.nextLine();

            Cidade cidade = new Cidade(nome, adjetivo, estado);
            vetorCidades.adicionar(cidade);
        }

        return vetorCidades;
    }

    private static Pessoa buscarPessoa(Vetor<Pessoa> pessoas, String nome) {
        return pessoas.buscarPorNome(nome);
    }

    private static Cidade buscarCidade(Vetor<Cidade> cidades, String nomeCidade) {
        return cidades.buscarPorNome(nomeCidade);
    }

    private static String gerarSaida(Pessoa pessoa, Vetor<Cidade> cidades) {
        if (pessoa == null) {
            return "Pessoa não encontrada.";
        }

        Cidade cidade = buscarCidade(cidades, pessoa.getNaturalidade());
        
        if (cidade == null) {
            return pessoa.getNome() + " nasceu em cidade desconhecida.";
        }

        String artigo = (pessoa.getSexo() == 'M' || pessoa.getSexo() == 'm') ? "O" : "A";
        String adjetivo = cidade.getAdjetivo();
        
        
        if (adjetivo.contains("/")) {
            String[] partes = adjetivo.split("/");
            adjetivo = (pessoa.getSexo() == 'M' || pessoa.getSexo() == 'm') ? partes[0] : partes[1];
        }

        return String.format("%s %s %s nasceu em %s - %s.",
            artigo, adjetivo, pessoa.getNome(), cidade.getNome(), cidade.getEstado());
    }
}