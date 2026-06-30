import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import styles from './Header.module.css';

function Header() {
  const [menuAberto, setMenuAberto] = useState(false);
  const location = useLocation();

  const paginaFiliacao = location.pathname === '/filiacao';
  const baseUrl = import.meta.env.BASE_URL;

  const [tema, setTema] = useState(() => {
    return localStorage.getItem('tema-stiquifar') || 'dark';
  });

  useEffect(() => {
    document.documentElement.setAttribute('data-theme', tema);
    localStorage.setItem('tema-stiquifar', tema);
  }, [tema]);

  function alternarTema() {
    setTema((temaAtual) => (temaAtual === 'dark' ? 'light' : 'dark'));
  }

  function fecharMenu() {
    setMenuAberto(false);
  }

  const iconeTema =
    tema === 'dark'
      ? `${baseUrl}imagens/light_mode.png`
      : `${baseUrl}imagens/dark_mode.png`;

  return (
    <header className={styles.header}>
      <div className={styles.container}>
        <a
          href={paginaFiliacao ? baseUrl : '#inicio'}
          className={styles.logoArea}
          onClick={fecharMenu}
        >
          <div className={styles.logoBox}>
            <img
              src={`${baseUrl}imagens/logo-stiquifar.png`}
              alt="Logo STIQUIFAR"
              className={styles.logoImagem}
            />
          </div>

          <div className={styles.logoTexto}>
            <strong>STIQUIFAR</strong>
            <span>Defesa do trabalhador</span>
          </div>
        </a>

        {!paginaFiliacao && (
          <nav className={`${styles.menu} ${menuAberto ? styles.menuAberto : ''}`}>
            <a href="#inicio" onClick={fecharMenu}>Início</a>
            <a href="#dores" onClick={fecharMenu}>Dores</a>
            <a href="#solucoes" onClick={fecharMenu}>Soluções</a>
            <a href="#beneficios" onClick={fecharMenu}>Benefícios</a>
            <a href="#relatos" onClick={fecharMenu}>Relatos</a>
            <a href="#faq" onClick={fecharMenu}>FAQ</a>
            <a href="#contato" onClick={fecharMenu}>Contato</a>
          </nav>
        )}

        <div className={`${styles.acoes} ${paginaFiliacao ? styles.acoesSoTema : ''}`}>
          <button
            type="button"
            className={styles.botaoTema}
            onClick={alternarTema}
            aria-label="Alternar tema claro e escuro"
            title={tema === 'dark' ? 'Ativar tema claro' : 'Ativar tema escuro'}
          >
            <img
              src={iconeTema}
              alt={tema === 'dark' ? 'Ativar tema claro' : 'Ativar tema escuro'}
              className={styles.iconeTema}
            />
          </button>

          {!paginaFiliacao && (
            <>
              <a href="#duvidas" className={styles.botaoFiliacao} onClick={fecharMenu}>
                Fale conosco
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
            </>
          )}
        </div>
      </div>
    </header>
  );
}

export default Header;