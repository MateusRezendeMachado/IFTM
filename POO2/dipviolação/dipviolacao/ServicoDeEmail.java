package dipviolacao;

public class ServicoDeEmail {
    public void sendEmail(String mensagem, String destinatario) {
        System.out.println("Enviando e-mail para " + destinatario + " : " + mensagem);
    }
}