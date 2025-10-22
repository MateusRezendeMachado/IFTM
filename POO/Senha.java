import java.util.Scanner;

class SenhaInvalidaException extends Exception {
    public SenhaInvalidaException(String mensagem) {
        super(mensagem);
    }
}

public class App {
    
    public static void verificarSenha(String senha) throws SenhaInvalidaException {
        if (senha.length() < 8 || !senha.matches(".*\\d.*")) {
            throw new SenhaInvalidaException("Senha inválida! Ela deve ter ao menos 8 caracteres e conter números.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Digite sua senha: ");
            String senha = sc.nextLine();
            verificarSenha(senha);
            System.out.println("Bem Vindo ao Sistema Financeiro!");
        } catch (SenhaInvalidaException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
