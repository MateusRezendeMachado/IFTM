public class EmpregadoComissionado extends Empregado {

    private double vendasBrutasSemanais;
    private double taxaComissao;

    public EmpregadoComissionado(String nome, String sobrenome, String nss, double vendas, double taxa) {
        super(nome, sobrenome, nss);
        setVendasBrutasSemanais(vendas);
        setTaxaComissao(taxa);
    }

    public void setVendasBrutasSemanais(double vendas) {
        this.vendasBrutasSemanais = vendas > 0 ? vendas : 0.0;
    }

    public void setTaxaComissao(double taxa) {
        this.taxaComissao = (taxa > 0 && taxa <= 1) ? taxa : 0.0;
    }

    @Override
    public double calculaPagamento() {
        return vendasBrutasSemanais * taxaComissao;
    }

    @Override
    public String toString() {
        return "Empregado Comissionado: " + getNome() + " " + getSobrenome() +
               "\n" + super.toString() +
               "\nVendas Brutas Semanais: $" + vendasBrutasSemanais +
               "; Taxa da Comissão: " + taxaComissao;
    }
}