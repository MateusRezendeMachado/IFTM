import java.util.Scanner;

public class App {

    
    public static double leCoordenada(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor da coordenada " + num + ": ");
        return sc.nextDouble();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char repetir;

        do {
            System.out.println("\n--- Nova Reta ---");
            double x1 = leCoordenada(1);
            double y1 = leCoordenada(2);
            double x2 = leCoordenada(3);
            double y2 = leCoordenada(4);

            
            if (Validacao.valida(x1, y1, x2, y2)) {
                if (Validacao.isQuadOne(x1, y1, x2, y2)) {
                    Retas reta = new Retas(x1, y1, x2, y2);
                    System.out.println(reta.exibe());
                    System.out.println("Total de retas criadas: " + Retas.cont);
                } else {
                    System.out.println("As coordenadas não estão no 1º quadrante!");
                }
            } else {
                System.out.println("Coordenadas inválidas (valores negativos não são aceitos).");
            }

            System.out.print("\nDeseja repetir? (S/N): ");
            repetir = sc.next().toUpperCase().charAt(0);

        } while (repetir == 'S');

        System.out.println("\nPrograma encerrado.");
        sc.close();
    }
}
