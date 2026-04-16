public class GeradorExtratos {

    public String exibeExtrato(Conta conta) {
        return "Saldo atual: R$ " + conta.getSaldo();
    }
}