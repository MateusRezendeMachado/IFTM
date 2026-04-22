public class EmpregadoHorista extends Empregado {

    private double salarioHora;
    private double horasTrabalhadas;

    public EmpregadoHorista(String nome, String sobrenome, String nss, double salarioHora, double horasTrabalhadas) {
        super(nome, sobrenome, nss);
        setSalarioHora(salarioHora);
        setHorasTrabalhadas(horasTrabalhadas);
    }

    public void setSalarioHora(double salarioHora) {
        this.salarioHora = salarioHora > 0 ? salarioHora : 0.0;
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas > 0 ? horasTrabalhadas : 0.0;
    }

    @Override
    public double calculaPagamento() {
        if (horasTrabalhadas <= 40)
            return salarioHora * horasTrabalhadas;
        else
            return (40 * salarioHora) + ((horasTrabalhadas - 40) * salarioHora * 1.5);
    }

    @Override
    public String toString() {
        return "Empregado Horista: " + getNome() + " " + getSobrenome() +
               "\n" + super.toString() +
               "\nValor por Hora: $" + salarioHora + "; Horas Trabalhadas: " + horasTrabalhadas;
    }
}