package dipviolacao;

public class ServicoDeSMS {
    public void sendSMS(String mensagem, String destinatario) {
        System.out.println("Enviando SMS para " + destinatario + " : " + mensagem);
    }
}