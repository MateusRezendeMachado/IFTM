import { useEffect, useRef, useState } from 'react';
import styles from './Beneficios.module.css';

function Beneficios() {
  const [beneficios, setBeneficios] = useState([]);
  const [beneficiosAbertos, setBeneficiosAbertos] = useState([]);
  const [visivel, setVisivel] = useState(false);

  const sectionRef = useRef(null);

  useEffect(() => {
    async function buscarBeneficios() {
      try {
        const resposta = await fetch('/dados/beneficios.json');

        if (!resposta.ok) {
          throw new Error('Arquivo beneficios.json não encontrado');
        }

        const dados = await resposta.json();
        setBeneficios(dados);
      } catch (erro) {
        console.error('Erro ao buscar benefícios:', erro);
      }
    }

    buscarBeneficios();
  }, []);

  useEffect(() => {
    if (beneficios.length === 0 || !sectionRef.current) {
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
        threshold: 0.2
      }
    );

    observer.observe(elemento);

    return () => {
      observer.unobserve(elemento);
    };
  }, [beneficios.length]);

  function alternarBeneficio(id) {
    setBeneficiosAbertos((beneficiosAtuais) => {
      if (beneficiosAtuais.includes(id)) {
        return beneficiosAtuais.filter((beneficioId) => beneficioId !== id);
      }

      return [...beneficiosAtuais, id];
    });
  }

  if (beneficios.length === 0) {
    return (
      <section className={styles.beneficios} id="beneficios">
        <p className={styles.carregando}>Carregando benefícios...</p>
      </section>
    );
  }

  return (
    <section
      ref={sectionRef}
      className={`${styles.beneficios} ${visivel ? styles.visivel : ''}`}
      id="beneficios"
    >
      <div className={styles.container}>
        <div className={styles.cabecalho}>
          <h2>Ser STIQUIFAR vale mais</h2>

          <p>
            Mais do que benefícios, uma rede de apoio, oportunidades e
            representação para você e sua família.
          </p>
        </div>

        <div className={styles.lista}>
          {beneficios.map((beneficio) => {
            const estaAberto = beneficiosAbertos.includes(beneficio.id);

            return (
              <article
                key={beneficio.id}
                className={`${styles.card} ${estaAberto ? styles.aberto : ''}`}
                style={{
                  '--imagem-beneficio': `url(${beneficio.imagem})`
                }}
              >
                <button
                  type="button"
                  className={styles.topo}
                  onClick={() => alternarBeneficio(beneficio.id)}
                  aria-expanded={estaAberto}
                >
                  <div>
                    <h3>{beneficio.titulo}</h3>
                    <p>{beneficio.resumo}</p>
                  </div>

                  <span className={styles.icone}>
                    {estaAberto ? '−' : '+'}
                  </span>
                </button>

                <div className={styles.conteudo}>
                  <div className={styles.imagemArea}>
                    <img
                      src={beneficio.imagem}
                      alt={beneficio.titulo}
                    />
                  </div>

                  <div className={styles.textoArea}>
                    <h4>{beneficio.titulo}</h4>
                    <p>{beneficio.texto}</p>
                  </div>
                </div>
              </article>
            );
          })}
        </div>
      </div>
    </section>
  );
}

export default Beneficios;