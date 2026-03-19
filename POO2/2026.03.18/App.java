public class App {
    public static void main(String[] args) {

        GeometriaPlana geo = new GeometriaPlana();

        geo.mudaParaCirculo();
        System.out.println(geo.exibe());

        geo.mudaParaTriangulo();
        System.out.println(geo.exibe());

        geo.mudaParaQuadrado();
        System.out.println(geo.exibe());
    }
}