package dipviolacao;

public class DipViolacao {
    
    public static void main(String[] args) {
        ServicoDeNotificacao notificacao = new ServicoDeNotificacao();

        notificacao.notificaPorEmail("Sua fatura chegou!", "cliente@email.com");
        notificacao.notificaPorSMS("Seu código é 1234", "11999999999");    
    }
    
}