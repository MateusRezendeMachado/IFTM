import { useEffect, useRef, useState } from 'react';
import styles from './Solucoes.module.css';

function Solucoes() {
  const [solucoes, setSolucoes] = useState([]);
  const [visivel, setVisivel] = useState(false);
  const sectionRef = useRef(null);

  useEffect(() => {
    async function buscarSolucoes() {
      try {
        const resposta = await fetch('/dados/solucoes.json');

        if (!resposta.ok) {
          throw new Error('Arquivo solucoes.json não encontrado');
        }

        const dados = await resposta.json();
        setSolucoes(dados);
      } catch (erro) {
        console.error('Erro ao buscar soluções:', erro);
      }
    }

    buscarSolucoes();
  }, []);

  useEffect(() => {
    if (solucoes.length === 0 || !sectionRef.current) {
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
  }, [solucoes.length]);

  if (solucoes.length === 0) {
    return (
      <section className={styles.solucoes} id="solucoes">
        <p className={styles.carregando}>Carregando soluções...</p>
      </section>
    );
  }

  return (
    <section
      ref={sectionRef}
      className={`${styles.solucoes} ${visivel ? styles.visivel : ''}`}
      id="solucoes"
    >
      <div className={styles.container}>
        <div className={styles.cabecalho}>
          <h2>Soluções que fortalecem você todos os dias</h2>

          <p>
            Com o STIQUIFAR, o trabalhador encontra apoio, orientação e
            representatividade para enfrentar os desafios da profissão.
          </p>
        </div>

        <div className={styles.conteudo}>
          <div className={styles.imagemBox}>
            <img
              src="/imagens/trabalhadores.png"
              alt="Trabalhador e trabalhadora representando a categoria"
            />
          </div>

          <div className={styles.grid}>
            {solucoes.map((solucao) => (
              <article className={styles.card} key={solucao.id}>
                <h3>{solucao.titulo}</h3>
                <p>{solucao.texto}</p>
              </article>
            ))}
          </div>
        </div>
      </div>
    </section>
  );
}

export default Solucoes;