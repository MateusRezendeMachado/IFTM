import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        int[] lados = le(); 
        
        
        if (ValidaTriangulo.verifica(lados[0], lados[1], lados[2])) {
            Triangulo t = new Triangulo(lados[0], lados[1], lados[2]);
            exibe(t);
        } else {
            System.out.println("Os valores informados NÃO formam um triângulo!");
        }
    }

  
    public static int[] le() {
        Scanner sc = new Scanner(System.in);
        int[] lados = new int[3];

        System.out.print("Informe o primeiro lado: ");
        lados[0] = sc.nextInt();
        System.out.print("Informe o segundo lado: ");
        lados[1] = sc.nextInt();
        System.out.print("Informe o terceiro lado: ");
        lados[2] = sc.nextInt();

        return lados;
    }

    
    public static void exibe(Triangulo t) {
        System.out.println("O triângulo formado é: " + t.tipoTriangulo());
    }
}
