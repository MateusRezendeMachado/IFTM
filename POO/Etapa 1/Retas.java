public class Retas {
    private double x1, y1, x2, y2;

    // Construtor
    public Retas(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    // Método que calcula o comprimento da reta
    public double comprimento() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // Método que retorna uma String com as informações da reta
    public String exibe() {
        return String.format("Reta: (%.2f, %.2f) até (%.2f, %.2f) | Comprimento: %.2f",
                x1, y1, x2, y2, comprimento());
    }
}
