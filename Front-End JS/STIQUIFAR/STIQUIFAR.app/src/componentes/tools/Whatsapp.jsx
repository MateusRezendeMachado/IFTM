import React from "react";
import { FloatingWhatsApp } from "react-floating-whatsapp";

function Whatsapp() {
  return (
    <FloatingWhatsApp
      phoneNumber="+553433319400"
      accountName="STIQUIFAR"
      avatar="/imagens/logo-stiquifar.png"
      statusMessage="Atendimento STIQUIFAR"
      chatMessage="Olá! Seja bem-vindo ao atendimento do STIQUIFAR. Como podemos ajudar?"
      placeholder="Digite sua mensagem"
      allowClickAway={true}
      allowEsc={true}
      darkMode={true}
      chatboxHeight={360}
      notification={true}
      notificationDelay={20}
      notificationLoop={2}
      chatboxStyle={{
        borderRadius: "22px",
        overflow: "hidden",
        boxShadow: "0 24px 60px rgba(0, 0, 0, 0.45)"
      }}
    />
  );
}

export default Whatsapp;