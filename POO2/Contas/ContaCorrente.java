public class ContaCorrente implements Conta {
    private double saldo;
    private double taxa;

    public ContaCorrente() {
        this.saldo = 0;
        this.taxa = 2.5;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0) {
            double totalSaque = valor + taxa;

            if (saldo >= totalSaque) {
                saldo -= totalSaque;
                System.out.println("Saque realizado com sucesso.");
                System.out.println("Taxa cobrada: R$ " + taxa);
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Valor inválido para saque.");
        }
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
}