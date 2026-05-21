package atividadeviolacao.ocp.lsp;

public class PagamentoBoleto implements EstrategiaPagamento {

    private ContaPagadora conta;

    public PagamentoBoleto(ContaPagadora conta) {
        this.conta = conta;
    }

    @Override
    public void executar(double valor) {
        conta.pagarBoleto(valor);
    }
}