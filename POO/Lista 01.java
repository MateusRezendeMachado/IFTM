import java.util.Scanner;
import java.util.ArrayList;

public class Opcoes {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int opcao;

        do {
            mostrarMenu();
            opcao = s.nextInt();

            if (opcao < 1 || opcao > 18) {
                System.out.println("Opção inválida, tente novamente!\n");
                continue;
            }
            if (opcao == 18) {
                System.out.println("Programa finalizado!");
                break;
            }

            executarOpcao(opcao);

        } while (true);
    }

    public static void mostrarMenu() {
        System.out.println("============== MENU DE OPÇÕES ==============");
        System.out.println(" 1 - Comparador de Números");
        System.out.println(" 2 - Equação do 2º Grau");
        System.out.println(" 3 - Média Aritmética");
        System.out.println(" 4 - Identificador de Triângulos");
        System.out.println(" 5 - Contador de Somas");
        System.out.println(" 6 - Demonstração do Contador");
        System.out.println(" 7 - Descobridor de Fatores");
        System.out.println(" 8 - Analisador de Números");
        System.out.println(" 9 - Verificador de Números Primos");
        System.out.println("10 - Fatorial");
        System.out.println("11 - MMC");
        System.out.println("12 - MDC");
        System.out.println("13 - Fibonacci");
        System.out.println("14 - Progressão Aritmética (PA)");
        System.out.println("15 - Soma de PG");
        System.out.println("16 - Tabuada");
        System.out.println("17 - Número Perfeito");
        System.out.println("18 - Sair");
        System.out.println("============================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1: comparador(); break;
            case 2: equacao2grau(); break;
            case 3: mediaAritmetica(); break;
            case 4: identificadorTriangulos(); break;
            case 5: contadorSomas(); break;
            case 6: demonstracaoContador(); break;
            case 7: descobridorFatores(); break;
            case 8: analisadorNumeros(); break;
            case 9: verificadorPrimo(); break;
            case 10: fatorial(); break;
            case 11: mmc(); break;
            case 12: mdc(); break;
            case 13: fibonacci(); break;
            case 14: progressaoAritmetica(); break;
            case 15: somaPG(); break;
            case 16: tabuada(); break;
            case 17: numeroPerfeito(); break;
        }
    }

    // ex 1
    public static String compararNumeros(int a, int b) {
        if (a < b) return "menor";
        if (a > b) return "maior";
        return "igual";
    }
    public static void exibirComparacao(int a, int b) {
        System.out.println("O primeiro número é " + compararNumeros(a, b) + " que o segundo.");
    }
    public static void comparador() {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite o primeiro número: ");
        int a = s.nextInt();
        System.out.print("Digite o segundo número: ");
        int b = s.nextInt();
        exibirComparacao(a, b);
    }

    // ex 2
    public static double calcularDelta(double a, double b, double c) {
        return b*b - 4*a*c;
    }
    public static double[] resolverEquacao(double a, double b, double c) {
        double d = calcularDelta(a,b,c);
        if (d < 0) return new double[0];
        if (d == 0) return new double[]{ -b/(2*a) };
        return new double[]{ (-b + Math.sqrt(d))/(2*a), (-b - Math.sqrt(d))/(2*a) };
    }
    public static void equacao2grau() {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite a: "); double a = s.nextDouble();
        System.out.print("Digite b: "); double b = s.nextDouble();
        System.out.print("Digite c: "); double c = s.nextDouble();
        double[] r = resolverEquacao(a,b,c);
        if (r.length == 0) System.out.println("Sem raízes reais.");
        else if (r.length == 1) System.out.println("Raiz única: " + r[0]);
        else System.out.println("Duas raízes: " + r[0] + " e " + r[1]);
    }

    // ex 3
    public static double calcularMedia(int a, int b) { return (a+b)/2.0; }
    public static void exibirMedia(double media) { System.out.println("A média é: " + media); }
    public static void mediaAritmetica() {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite o primeiro número: ");
        int a = s.nextInt();
        System.out.print("Digite o segundo número: ");
        int b = s.nextInt();
        exibirMedia(calcularMedia(a,b));
    }

    // ex 4
    public static boolean formaTriangulo(int a, int b, int c) {
        return a+b>c && a+c>b && b+c>a;
    }
    public static String classificarTriangulo(int a, int b, int c) {
        if (!formaTriangulo(a,b,c)) return "Não forma triângulo";
        if (a==b && b==c) return "Equilátero";
        if (a==b || a==c || b==c) return "Isósceles";
        return "Escaleno";
    }
    public static void identificadorTriangulos() {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite lado A: ");
        int a = s.nextInt();
        System.out.print("Digite lado B: ");
        int b = s.nextInt();
        System.out.print("Digite lado C: ");
        int c = s.nextInt();
        System.out.println("Resultado: " + classificarTriangulo(a,b,c));
    }

    // ex 5
    public static int[] lerNumeros() {
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> lista = new ArrayList<>();
        int num;
        do {
            System.out.print("Digite número (0 p/ sair): ");
            num = s.nextInt();
            if (num != 0) lista.add(num);
        } while(num != 0);
        return lista.stream().mapToInt(i->i).toArray();
    }
    public static int[] contarSomas(int[] numeros) {
        int pos = 0, neg = 0;
        for (int n: numeros) {
            if (n >= 0) pos++; else neg++;
        }
        return new int[]{pos, neg};
    }
    public static void contadorSomas() {
        int[] nums = lerNumeros();
        int[] c = contarSomas(nums);
        System.out.println("Somas positivas = " + c[0]);
        System.out.println("Somas negativas = " + c[1]);
    }

    // ex 6
    public static int[] exemploNumeros() {
        return new int[]{-1, -6, 3, 4, -2, 7, 9};
    }
    public static void demonstrarContador(int[] numeros) {
        int[] c = contarSomas(numeros);
        System.out.println("Somas positivas = " + c[0]);
        System.out.println("Somas negativas = " + c[1]);
    }
    public static void demonstracaoContador() {
        int[] nums = exemploNumeros();
        demonstrarContador(nums);
    }

    // ex 7
    public static int[] fatores(int n) {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i=1;i<=n;i++) if (n%i==0) lista.add(i);
        return lista.stream().mapToInt(i->i).toArray();
    }
    public static void exibirFatores(int n) {
        int[] f = fatores(n);
        System.out.print("Fatores de " + n + ": ");
        for (int x: f) System.out.print(x+" ");
        System.out.println();
    }
    public static void descobridorFatores() {
        Scanner s = new Scanner(System.in);
        String resp;
        do {
            System.out.print("Digite número: ");
            int n = s.nextInt();
            exibirFatores(n);
            System.out.print("Quer outro? (S/N): ");
            resp = s.next();
        } while(resp.equalsIgnoreCase("s"));
    }

    // ex 8
    public static int[][] analisarNumeros(int[] numeros) {
        int pares=0, impares=0, pos=0, neg=0;
        int somaP=0, somaI=0, somaPos=0, somaNeg=0;
        for (int n: numeros) {
            if (n%2==0) { pares++; somaP+=n; } else { impares++; somaI+=n; }
            if (n>0) { pos++; somaPos+=n; } else if (n<0) { neg++; somaNeg+=n; }
        }
        return new int[][]{
            {pares,somaP}, {impares,somaI}, {pos,somaPos}, {neg,somaNeg}
        };
    }
    public static void exibirAnalise(int[][] r) {
        System.out.println("Pares: qtd="+r[0][0]+" soma="+r[0][1]);
        System.out.println("Ímpares: qtd="+r[1][0]+" soma="+r[1][1]);
        System.out.println("Positivos: qtd="+r[2][0]+" soma="+r[2][1]);
        System.out.println("Negativos: qtd="+r[3][0]+" soma="+r[3][1]);
    }
    public static void analisadorNumeros() {
        int[] nums = lerNumeros();
        exibirAnalise(analisarNumeros(nums));
    }

    // ex 9
    public static boolean ehPrimo(int n) {
        if (n<2) return false;
        for (int i=2;i<=Math.sqrt(n);i++) if (n%i==0) return false;
        return true;
    }
    public static void exibirPrimo(int n) {
        if (ehPrimo(n)) System.out.println(n+" é primo.");
        else System.out.println(n+" não é primo.");
    }
    public static void verificadorPrimo() {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite número: ");
        int n = s.nextInt();
        exibirPrimo(n);
    }

    // ex 10
    public static long calcularFatorial(int n) {
        long f=1; for (int i=2;i<=n;i++) f*=i; return f;
    }
    public static void exibirFatorial(int n) {
        System.out.println("Fatorial de "+n+" = "+calcularFatorial(n));
    }
    public static void fatorial() {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite número: ");
        int n = s.nextInt();
        exibirFatorial(n);
    }

    // ex 11
    public static int calcularMMC(int a, int b) {
        int m = Math.max(a,b);
        while(true){ if(m%a==0 && m%b==0) return m; m++; }
    }
    public static void exibirMMC(int a, int b) {
        System.out.println("MMC("+a+","+b+")="+calcularMMC(a,b));
    }
    public static void mmc() {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite a: "); int a=s.nextInt();
        System.out.print("Digite b: "); int b=s.nextInt();
        exibirMMC(a,b);
    }

    // ex 12
    public static int calcularMDC(int a, int b) {
        while(b!=0){ int r=a%b; a=b; b=r; }
        return a;
    }
    public static void exibirMDC(int a, int b) {
        System.out.println("MDC("+a+","+b+")="+calcularMDC(a,b));
    }
    public static void mdc() {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite a: "); int a=s.nextInt();
        System.out.print("Digite b: "); int b=s.nextInt();
        exibirMDC(a,b);
    }

    // ex 13
    public static int[] gerarFibonacci(int n) {
        int[] f = new int[n];
        if(n>0) f[0]=0;
        if(n>1) f[1]=1;
        for(int i=2;i<n;i++) f[i]=f[i-1]+f[i-2];
        return f;
    }
    public static void exibirFibonacci(int[] f) {
        for(int x:f) System.out.print(x+" ");
        System.out.println();
    }
    public static void fibonacci() {
        Scanner s = new Scanner(System.in);
        System.out.print("Qtd termos: ");
        int n=s.nextInt();
        exibirFibonacci(gerarFibonacci(n));
    }

    // ex 14
    public static int[] gerarPA(int primeiro,int razao,int n) {
        int[] pa=new int[n];
        for(int i=0;i<n;i++) pa[i]=primeiro+i*razao;
        return pa;
    }
    public static void exibirPA(int[] pa) {
        for(int x:pa) System.out.print(x+" ");
        System.out.println();
    }
    public static void progressaoAritmetica() {
        Scanner s=new Scanner(System.in);
        System.out.print("Primeiro termo: ");int p=s.nextInt();
        System.out.print("Razão: ");int r=s.nextInt();
        System.out.print("Qtd termos: ");int n=s.nextInt();
        exibirPA(gerarPA(p,r,n));
    }

    // ex 15
    public static double calcularSomaPG(double p,double q,int n) {
        if(q==1) return p*n;
        return p*(1-Math.pow(q,n))/(1-q);
    }
    public static void exibirSomaPG(double soma) {
        System.out.println("Soma da PG = "+soma);
    }
    public static void somaPG() {
        Scanner s=new Scanner(System.in);
        System.out.print("Primeiro termo: ");double p=s.nextDouble();
        System.out.print("Razão: ");double q=s.nextDouble();
        System.out.print("Qtd termos: ");int n=s.nextInt();
        exibirSomaPG(calcularSomaPG(p,q,n));
    }

    // ex 16
    public static int[][] gerarTabuada() {
        int[][] tab=new int[10][10];
        for(int i=0;i<10;i++) for(int j=0;j<10;j++) tab[i][j]=(i+1)*(j+1);
        return tab;
    }
    public static void exibirTabuada(int[][] tab) {
        for(int i=0;i<10;i++){ for(int j=0;j<10;j++) System.out.print(tab[i][j]+"\t"); System.out.println(); }
    }
    public static void tabuada() { exibirTabuada(gerarTabuada()); }

    // ex 17
    public static int somaDivisores(int n) {
        int soma=0; for(int i=1;i<n;i++) if(n%i==0) soma+=i; return soma;
    }
    public static void exibirNumeroPerfeito(int n) {
        if(somaDivisores(n)==n) System.out.println(n+" é perfeito.");
        else System.out.println(n+" não é perfeito.");
    }
    public static void numeroPerfeito() {
        Scanner s=new Scanner(System.in);
        System.out.print("Digite número: ");
        int n=s.nextInt();
        exibirNumeroPerfeito(n);
    }
}
