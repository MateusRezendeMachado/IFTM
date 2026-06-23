import { useState } from 'react';
import styles from './Header.module.css';

function Header() {
  const [menuAberto, setMenuAberto] = useState(false);

  function fecharMenu() {
    setMenuAberto(false);
  }

  return (
    <header className={styles.header}>
      <div className={styles.container}>
        <a href="#inicio" className={styles.logoArea} onClick={fecharMenu}>
          

          <div className={styles.logoTexto}>
            <strong>STIQUIFAR</strong>
            <span>Defesa do trabalhador</span>
          </div>
        </a>

        <nav className={`${styles.menu} ${menuAberto ? styles.menuAberto : ''}`}>
          <a href="#inicio" onClick={fecharMenu}>Início</a>
          <a href="#dores" onClick={fecharMenu}>Dores</a>
          <a href="#solucoes" onClick={fecharMenu}>Soluções</a>
          <a href="#beneficios" onClick={fecharMenu}>Benefícios</a>
          <a href="#relatos" onClick={fecharMenu}>Relatos</a>
          <a href="#faq" onClick={fecharMenu}>FAQ</a>
          <a href="#contato" onClick={fecharMenu}>Contato</a>
        </nav>

        <div className={styles.acoes}>
          <a href="#filiacao" className={styles.botaoFiliacao}>
            Filiar-se
          </a>

          <button
            type="button"
            className={`${styles.botaoMenu} ${menuAberto ? styles.ativo : ''}`}
            onClick={() => setMenuAberto(!menuAberto)}
            aria-label="Abrir menu"
          >
            <span></span>
            <span></span>
            <span></span>
          </button>
        </div>
      </div>
    </header>
  );
}

export default Header;