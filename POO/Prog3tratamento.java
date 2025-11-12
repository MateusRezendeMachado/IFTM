package prog3tratamento;

import java.util.Scanner;

public class Prog3Tratamento {

    // Função que lança exceção caso a divisão não seja inteira
    public static void verifica(int num, int den) throws Exception {
        if (den == 0)
            throw new Exception("Erro: Divisão por zero não é permitida!");
        
        if (num % den != 0)
            throw new Exception("Erro: A divisão não é inteira!");
    }

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Digite o numerador: ");
            int num = s.nextInt();
            System.out.print("Digite o denominador: ");
            int den = s.nextInt();
            
            // Chama a função 
            verifica(num, den);
            
            int resultado = num / den;
            System.out.println("Divisão válida! Resultado = " + resultado);
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
