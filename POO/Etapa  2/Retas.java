public class Retas {
    private double x1, y1, x2, y2;
    public static int cont = 0; 

   
    public Retas(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        cont++; 
    }

    
    public static boolean valida(double x1, double y1, double x2, double y2) {
        return (x1 >= 0 && y1 >= 0 && x2 >= 0 && y2 >= 0);
    }


    public double comprimento() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    
    public String exibe() {
        return String.format("Reta: (%.2f, %.2f) até (%.2f, %.2f) | Comprimento: %.2f",
                x1, y1, x2, y2, comprimento());
    }
}
