public class ValidaTriangulo {


    public static boolean verifica(int a, int b, int c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
}
