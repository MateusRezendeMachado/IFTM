package atividadeviolacao.ocp.lsp;

public class Saque implements EstrategiaPagamento {

    private Conta conta;

    public Saque(Conta conta) {
        this.conta = conta;
    }

    @Override
    public void executar(double valor) {
        conta.sacar(valor);
    }
}