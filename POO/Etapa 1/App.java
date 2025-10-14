import java.util.Scanner;

public class App {

    
    public static boolean valida(double x1, double y1, double x2, double y2) {
        return (x1 >= 0 && y1 >= 0 && x2 >= 0 && y2 >= 0);
    }

    
    public static double leCoordenada(int num) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor da coordenada " + num + ": ");
        return sc.nextDouble();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contador = 0;
        char repetir;

        do {
            System.out.println("\n--- Nova Reta ---");
            double x1 = leCoordenada(1);
            double y1 = leCoordenada(2);
            double x2 = leCoordenada(3);
            double y2 = leCoordenada(4);

            if (valida(x1, y1, x2, y2)) {
                Retas reta = new Retas(x1, y1, x2, y2);
                contador++;
                System.out.println(reta.exibe());
                System.out.println("Total de retas criadas: " + contador);
            } else {
                System.out.println("Coordenadas inválidas! Só são aceitas no 1º quadrante.");
            }

            System.out.print("\nDeseja repetir? (S/N): ");
            repetir = sc.next().toUpperCase().charAt(0);

        } while (repetir == 'S');

        System.out.println("\nPrograma encerrado.");
        sc.close();
    }
}
