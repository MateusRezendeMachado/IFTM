import { useEffect, useRef, useState } from 'react';
import styles from './Dores.module.css';

function Dores() {
  const [dores, setDores] = useState([]);
  const [indiceAtual, setIndiceAtual] = useState(0);
  const [animando, setAnimando] = useState(false);
  const [direcao, setDirecao] = useState('proximo');

  const sectionRef = useRef(null);
  const [visivel, setVisivel] = useState(false);

  useEffect(() => {
    async function buscarDores() {
      try {
        const resposta = await fetch('/dados/dores.json');

        if (!resposta.ok) {
          throw new Error('Arquivo dores.json não encontrado');
        }

        const dados = await resposta.json();
        setDores(dados);
      } catch (erro) {
        console.error('Erro ao buscar as dores:', erro);
      }
    }

    buscarDores();
  }, []);

  useEffect(() => {
    if (dores.length === 0 || !sectionRef.current) {
      return;
    }

    const elemento = sectionRef.current;

    const observer = new IntersectionObserver(
      (entradas) => {
        const entrada = entradas[0];

        if (entrada.isIntersecting) {
          setVisivel(true);
          observer.unobserve(elemento);
        }
      },
      {
        threshold: 0.25
      }
    );

    observer.observe(elemento);

    return () => {
      observer.unobserve(elemento);
    };
  }, [dores.length]);

  function buscarCard(posicao) {
    if (dores.length === 0) {
      return null;
    }

    const novoIndice = (indiceAtual + posicao + dores.length) % dores.length;
    return dores[novoIndice];
  }

  function voltarCard() {
    if (animando) {
      return;
    }

    setDirecao('anterior');
    setAnimando(true);

    // ALTERADO: tempo maior para transição suave
    setTimeout(() => {
      setIndiceAtual((indice) =>
        indice === 0 ? dores.length - 1 : indice - 1
      );

      setTimeout(() => {
        setAnimando(false);
      }, 250); // antes 60ms
    }, 400); // antes 260ms
  }

  function avancarCard() {
    if (animando) {
      return;
    }

    setDirecao('proximo');
    setAnimando(true);

    // ALTERADO: tempo maior para transição suave
    setTimeout(() => {
      setIndiceAtual((indice) =>
        indice === dores.length - 1 ? 0 : indice + 1
      );

      setTimeout(() => {
        setAnimando(false);
      }, 250); // antes 60ms
    }, 400); // antes 260ms
  }

  if (dores.length === 0) {
    return (
      <section className={styles.dores} id="dores">
        <p className={styles.carregando}>Carregando informações...</p>
      </section>
    );
  }

  const cardEsquerda = buscarCard(-1);
  const cardCentro = buscarCard(0);
  const cardDireita = buscarCard(1);

  return (
    <section
      ref={sectionRef}
      className={`${styles.dores} ${visivel ? styles.visivel : ''}`}
      id="dores"
    >
      <div className={styles.container}>
        <div className={styles.cabecalho}>
          <h2>Onde o STIQUIFAR faz a diferença</h2>

        </div>

        <div className={styles.carrossel}>
          <button
            type="button"
            className={styles.seta}
            onClick={voltarCard}
            disabled={animando}
            aria-label="Voltar card"
          >
            ‹
          </button>

          <div
            className={`
              ${styles.cards}
              ${animando ? styles.trocando : ''}
              ${direcao === 'proximo' ? styles.proximo : styles.anterior}
            `}
          >
            <article className={`${styles.card} ${styles.lateral}`}>
              <h3>{cardEsquerda.titulo}</h3>
              <p>{cardEsquerda.texto}</p>
            </article>

            <article className={`${styles.card} ${styles.principal}`}>
              <h3>{cardCentro.titulo}</h3>
              <p>{cardCentro.texto}</p>
            </article>

            <article className={`${styles.card} ${styles.lateral}`}>
              <h3>{cardDireita.titulo}</h3>
              <p>{cardDireita.texto}</p>
            </article>
          </div>

          <button
            type="button"
            className={styles.seta}
            onClick={avancarCard}
            disabled={animando}
            aria-label="Avançar card"
          >
            ›
          </button>
        </div>
      </div>
    </section>
  );
}

export default Dores;