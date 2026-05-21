package atividadeviolacao.ocp.lsp;

public class ProcessadorPagamento {

    public void processar(
            EstrategiaPagamento estrategia,
            double valor) {

        estrategia.executar(valor);
    }
}