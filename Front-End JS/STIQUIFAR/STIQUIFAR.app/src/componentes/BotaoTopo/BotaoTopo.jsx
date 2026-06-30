import { useEffect, useState } from 'react';
import styles from './BotaoTopo.module.css';

function BotaoTopo() {
  const [visivel, setVisivel] = useState(false);

  useEffect(() => {
    function verificarScroll() {
      if (window.scrollY > 420) {
        setVisivel(true);
      } else {
        setVisivel(false);
      }
    }

    window.addEventListener('scroll', verificarScroll);

    return () => {
      window.removeEventListener('scroll', verificarScroll);
    };
  }, []);

  function voltarAoTopo() {
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  }

  return (
    <button
      type="button"
      className={`${styles.botaoTopo} ${visivel ? styles.visivel : ''}`}
      onClick={voltarAoTopo}
      aria-label="Voltar ao topo"
      title="Voltar ao topo"
    >
      ↑
    </button>
  );
}

export default BotaoTopo;