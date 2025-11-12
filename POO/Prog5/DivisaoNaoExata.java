package prog5tratamento;

public class DivisaoNaoExata extends Exception {
    private int num, den;
    
    public DivisaoNaoExata(int num, int den) {
        super();
        this.num = num;
        this.den = den;
    }
    
    @Override
    public String toString() {
        return "Resultado de " + num + " / " + den + " não é inteiro!";
    }
}
