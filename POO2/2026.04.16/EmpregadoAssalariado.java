public class EmpregadoAssalariado extends Empregado {

    private double salarioSemanal;

    public EmpregadoAssalariado(String nome, String sobrenome, String nss, double salarioSemanal) {
        super(nome, sobrenome, nss);
        setSalarioSemanal(salarioSemanal);
    }

    public double getSalarioSemanal() {
        return salarioSemanal;
    }

    public void setSalarioSemanal(double salarioSemanal) {
        this.salarioSemanal = salarioSemanal > 0 ? salarioSemanal : 0.0;
    }

    @Override
    public double calculaPagamento() {
        return salarioSemanal;
    }

    @Override
    public String toString() {
        return "Empregado Assalariado: " + getNome() + " " + getSobrenome() +
               "\n" + super.toString() +
               "\nSalário Semanal: $" + salarioSemanal;
    }
}