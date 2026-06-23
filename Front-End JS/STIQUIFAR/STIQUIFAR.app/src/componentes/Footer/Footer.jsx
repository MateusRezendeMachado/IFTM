import styles from './Footer.module.css';

function Footer() {
  const anoAtual = new Date().getFullYear();

  const redesSociais = [
    {
      nome: 'Facebook',
      link: 'https://www.facebook.com/share/1EKKMarUoc/?mibextid=wwXIfr',
      icone: (
        <svg viewBox="0 0 24 24" aria-hidden="true">
          <path d="M14 8.5V6.9c0-.7.2-1.1 1.2-1.1h1.6V3.1c-.8-.1-1.7-.1-2.5-.1-2.5 0-4.2 1.5-4.2 4.3v1.2H7.3v3h2.8V21h3.4v-9.5h2.8l.4-3H14z" />
        </svg>
      )
    },
    {
      nome: 'Instagram',
      link: 'https://www.instagram.com/stiquifar.uberaba?igsh=MzBxaDFndmRlanM%3D&utm_source=qr',
      icone: (
        <svg viewBox="0 0 24 24" aria-hidden="true">
          <path d="M7.8 2.5h8.4c2.9 0 5.3 2.4 5.3 5.3v8.4c0 2.9-2.4 5.3-5.3 5.3H7.8c-2.9 0-5.3-2.4-5.3-5.3V7.8c0-2.9 2.4-5.3 5.3-5.3zm0 2C6 4.5 4.5 6 4.5 7.8v8.4C4.5 18 6 19.5 7.8 19.5h8.4c1.8 0 3.3-1.5 3.3-3.3V7.8c0-1.8-1.5-3.3-3.3-3.3H7.8zm4.2 3.2a4.3 4.3 0 1 1 0 8.6 4.3 4.3 0 0 1 0-8.6zm0 2a2.3 2.3 0 1 0 0 4.6 2.3 2.3 0 0 0 0-4.6zm4.6-2.5a1 1 0 1 1 0 2.1 1 1 0 0 1 0-2.1z" />
        </svg>
      )
    },
    {
      nome: 'Blog',
      link: ' https://stiquifar.com.br/blog/',
      icone: (
        <svg viewBox="0 0 24 24" aria-hidden="true">
          <path d="M5 3.5h14c1.1 0 2 .9 2 2v13c0 1.1-.9 2-2 2H5c-1.1 0-2-.9-2-2v-13c0-1.1.9-2 2-2zm0 2v13h14v-13H5zm2 2h10v2H7v-2zm0 4h10v2H7v-2zm0 4h6v2H7v-2z" />
        </svg>
      )
    }
  ];

  return (
    <footer className={styles.footer}>
      <div className={styles.container}>
        <div className={styles.logoArea}>
          <img src="/imagens/logo-stiquifar.png" alt="Logo STIQUIFAR" />

          <div>
            <strong>STIQUIFAR</strong>
            <span>Defesa do trabalhador</span>
          </div>
        </div>

        <div className={styles.redes}>
          {redesSociais.map((rede) => (
            <a
              key={rede.nome}
              href={rede.link}
              target="_blank"
              rel="noreferrer"
              aria-label={rede.nome}
              title={rede.nome}
            >
              {rede.icone}
            </a>
          ))}
        </div>

        <p>
          © {anoAtual} STIQUIFAR. Todos os direitos reservados.
        </p>
      </div>
    </footer>
  );
}

export default Footer;