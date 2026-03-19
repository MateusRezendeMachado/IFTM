class GeometriaPlana {

    private Figuras2D fig;

    public void mudaParaCirculo() {
        fig = new Circulo();
    }

    public void mudaParaTriangulo() {
        fig = new Triangulo();
    }

    public void mudaParaQuadrado() {
        fig = new Quadrado();
    }

    public String exibe() {
        return fig.pegaTexto();
    }
}