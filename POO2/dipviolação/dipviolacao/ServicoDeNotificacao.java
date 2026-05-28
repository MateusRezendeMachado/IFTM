package dipviolacao;

public class ServicoDeNotificacao {
    private final ServicoDeEmail email;
    private final ServicoDeSMS sms;

    public ServicoDeNotificacao() {
        // Instanciando diretamente as classes concretas (violando o DIP)
        this.email = new ServicoDeEmail();
        this.sms = new ServicoDeSMS();
    }

    public void notificaPorEmail(String mensagem, String destinatario) {
        email.sendEmail(mensagem, destinatario);
    }

    public void notificaPorSMS(String mensagem, String destinatario) {
        sms.sendSMS(mensagem, destinatario);
    }
}