import styles from './Contato.module.css';

function Contato() {
  const endereco = 'Rua Marquês do Paraná, 156 - Uberaba/MG';

  const mapaUrl =
    'https://www.google.com/maps?q=Rua%20Marqu%C3%AAs%20do%20Paran%C3%A1%2C%20156%20-%20Uberaba%2C%20MG&output=embed';

  return (
    <section className={styles.contato} id="contato">
      <h1>Onde nos encontrar</h1>

      <div className={styles.container}>
        <div className={styles.cardInfo}>
          <div className={styles.lista}>
            <div className={styles.item}>
              <strong>Endereço</strong>
              <p>{endereco}</p>
            </div>

            <div className={styles.item}>
              <strong>Telefone</strong>
              <p>(34) 3331-9400</p>
            </div>

            <div className={styles.item}>
              <strong>WhatsApp</strong>
              <p>(34) 98407-0761</p>
            </div>

            <div className={styles.item}>
              <strong>Horário de funcionamento</strong>
              <p>09h às 12h | 13h às 18h</p>
            </div>
          </div>
        </div>

        <div className={styles.cardMapa}>
          <iframe
            src={mapaUrl}
            title="Mapa com endereço do STIQUIFAR"
            loading="lazy"
            referrerPolicy="no-referrer-when-downgrade"
            allowFullScreen
          ></iframe>
        </div>
      </div>
    </section>
  );
}

export default Contato;