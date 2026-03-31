package model;

public class FuncionarioTerceirizado extends Funcionario {

    private double despesaAdicional;

    public FuncionarioTerceirizado() {
        super();
    }

    public FuncionarioTerceirizado(String nome, int horasTrabalhadas, double valorHora, double despesaAdicional) {
        super(nome, horasTrabalhadas, valorHora);
        setDespesaAdicional(despesaAdicional);
    }

    public double getDespesaAdicional() {
        return despesaAdicional;
    }

    public void setDespesaAdicional(double despesaAdicional) {
        if (despesaAdicional < 0 || despesaAdicional > 1000.00) {
            throw new IllegalArgumentException("Despesa acima do limite.");
        }
        this.despesaAdicional = despesaAdicional;
    }

    @Override
    public double calcularPagamento() {
        double pagamento = (getHorasTrabalhadas() * getValorHora()) + (despesaAdicional * 1.1);

        if (pagamento < 1518.00 || pagamento > 10000.00) {
            throw new IllegalArgumentException("Pagamento excede o teto.");
        }

        return pagamento;
    }
}