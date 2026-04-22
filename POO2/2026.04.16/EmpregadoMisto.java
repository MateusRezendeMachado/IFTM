public class EmpregadoMisto extends EmpregadoComissionado {

    private double salarioBase;

    public EmpregadoMisto(String nome, String sobrenome, String nss,
                          double vendas, double taxa, double salarioBase) {
        super(nome, sobrenome, nss, vendas, taxa);
        setSalarioBase(salarioBase);
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase > 0 ? salarioBase : 0.0;
    }

    @Override
    public double calculaPagamento() {
        return super.calculaPagamento() + salarioBase;
    }

    @Override
    public String toString() {
        return "Empregado Misto: " + getNome() + " " + getSobrenome() +
               "\n" + super.toString() +
               "; Salário-base: $" + salarioBase;
    }
}