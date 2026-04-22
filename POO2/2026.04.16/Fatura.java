public class Fatura implements Pagavel {

    private final String numeroPeca;
    private final String descricaoPeca;
    private int quantidade;
    private double precoItem;

    public Fatura(String numeroPeca, String descricaoPeca, int quantidade, double precoItem) {
        this.numeroPeca = numeroPeca;
        this.descricaoPeca = descricaoPeca;
        setQuantidade(quantidade);
        setPrecoItem(precoItem);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade > 0 ? quantidade : 0;
    }

    public double getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(double precoItem) {
        this.precoItem = precoItem > 0 ? precoItem : 0.0;
    }

    @Override
    public double calculaPagamento() {
        return quantidade * precoItem;
    }

    @Override
    public String toString() {
        return "Fatura:\nNúmero da peça: " + numeroPeca + " (" + descricaoPeca + ")" +
               "\nQuantidade: " + quantidade +
               "\nPreço por item: $" + precoItem;
    }
}