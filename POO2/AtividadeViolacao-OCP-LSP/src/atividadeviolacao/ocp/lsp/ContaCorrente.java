package atividadeviolacao.ocp.lsp;

public class ContaCorrente implements ContaPagadora {

    protected double saldo;

    public ContaCorrente(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void pagarBoleto(double valor) {
        saldo -= valor;
        System.out.println(
            "Boleto pago. Saldo restante: " + saldo
        );
    }
}