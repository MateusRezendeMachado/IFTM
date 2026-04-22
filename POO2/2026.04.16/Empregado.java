public abstract class Empregado implements Pagavel {

    private final String nome;
    private final String sobrenome;
    private final String numeroSeguroSocial;

    public Empregado(String nome, String sobrenome, String numeroSeguroSocial) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.numeroSeguroSocial = numeroSeguroSocial;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    @Override
    public String toString() {
        return "Número do Seguro Social: " + numeroSeguroSocial;
    }
}