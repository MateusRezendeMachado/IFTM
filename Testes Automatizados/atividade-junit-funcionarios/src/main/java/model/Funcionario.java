package model;

public class Funcionario {

    private String nome;
    private int horasTrabalhadas;
    private double valorHora;

    public Funcionario() {
    }

    public Funcionario(String nome, int horasTrabalhadas, double valorHora) {
        this.nome = nome;
        setHorasTrabalhadas(horasTrabalhadas);
        setValorHora(valorHora);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas < 20 || horasTrabalhadas > 160) {
            throw new IllegalArgumentException("Horas inválidas.");
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        if (valorHora < 15.18 || valorHora > 151.80) {
            throw new IllegalArgumentException("Valor hora inválido.");
        }
        this.valorHora = valorHora;
    }

    public double calcularPagamento() {
        double pagamento = horasTrabalhadas * valorHora;

        if (pagamento < 1518.00 || pagamento > 10000.00) {
            throw new IllegalArgumentException("Pagamento fora dos limites.");
        }

        return pagamento;
    }
}