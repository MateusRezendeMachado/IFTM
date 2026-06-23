import { useEffect, useRef, useState } from 'react';
import styles from './Relatos.module.css';

function Relatos() {
  // Estado dos relatos vindos do JSON
  const [relatos, setRelatos] = useState([]);
  // Índice do relato em destaque
  const [indiceAtual, setIndiceAtual] = useState(0);
  // Controla animação de transição
  const [animando, setAnimando] = useState(false);
  // Direção da animação (cima/baixo)
  const [direcao, setDirecao] = useState('baixo');
  // Controla visibilidade ao rolar a página
  const [visivel, setVisivel] = useState(false);

  const sectionRef = useRef(null);

  // Busca os relatos ao montar o componente
  useEffect(() => {
    async function buscarRelatos() {
      try {
        const resposta = await fetch('/dados/relatos.json');
        if (!resposta.ok) {
          throw new Error('Arquivo relatos.json não encontrado');
        }
        const dados = await resposta.json();
        setRelatos(dados);
      } catch (erro) {
        console.error('Erro ao buscar relatos:', erro);
      }
    }
    buscarRelatos();
  }, []);

  // Ativa a animação de entrada quando a seção entra na viewport
  useEffect(() => {
    if (relatos.length === 0 || !sectionRef.current) return;

    const elemento = sectionRef.current;
    const observer = new IntersectionObserver(
      (entradas) => {
        const entrada = entradas[0];
        if (entrada.isIntersecting) {
          setVisivel(true);
          observer.unobserve(elemento);
        }
      },
      { threshold: 0.2 }
    );

    observer.observe(elemento);
    return () => observer.unobserve(elemento);
  }, [relatos.length]);

  // Retorna o relato deslocado por 'posicao' em relação ao atual (circular)
  function buscarRelato(posicao) {
    const novoIndice = (indiceAtual + posicao + relatos.length) % relatos.length;
    return relatos[novoIndice];
  }

  // Muda para o próximo/anterior com animação
  function mudarRelato(novaDirecao) {
    if (animando) return;

    setDirecao(novaDirecao);
    setAnimando(true);

    // Primeiro aplica a animação de saída
    setTimeout(() => {
      // Depois troca o índice
      setIndiceAtual((indice) => {
        if (novaDirecao === 'baixo') {
          return indice === relatos.length - 1 ? 0 : indice + 1;
        }
        return indice === 0 ? relatos.length - 1 : indice - 1;
      });

      // Ao final, remove o estado de animação
      setTimeout(() => {
        setAnimando(false);
      }, 80);
    }, 260);
  }

  // Enquanto não carrega, mostra mensagem
  if (relatos.length === 0) {
    return (
      <section className={styles.relatos} id="relatos">
        <p className={styles.carregando}>Carregando relatos...</p>
      </section>
    );
  }

  // Obtém os três relatos (acima, atual, abaixo) para o carrossel
  const relatoAcima = buscarRelato(-1);
  const relatoAtual = buscarRelato(0);
  const relatoAbaixo = buscarRelato(1);

  return (
    <section
      ref={sectionRef}
      className={`${styles.relatos} ${visivel ? styles.visivel : ''}`}
      id="relatos"
    >
      <div className={styles.container}>
        <div className={styles.cabecalho}>
          <h2>Histórias de quem não está mais sozinho</h2>
          <p>
            Ser STIQUIFAR vale mais quando o trabalhador encontra apoio,
            informação e representação nos momentos que importam.
          </p>
        </div>

        <div className={styles.layout}>
          {/* Carrossel com os três cards sobrepostos na vertical */}
          <div
            className={`
              ${styles.carrossel}
              ${animando ? styles.animando : ''}
              ${direcao === 'baixo' ? styles.baixo : styles.cima}
            `}
          >
            <article className={`${styles.card} ${styles.lateral}`}>
              <RelatoConteudo relato={relatoAcima} />
            </article>

            <article className={`${styles.card} ${styles.principal}`}>
              <RelatoConteudo relato={relatoAtual} />
            </article>

            <article className={`${styles.card} ${styles.lateral}`}>
              <RelatoConteudo relato={relatoAbaixo} />
            </article>
          </div>

          {/* Controles: setas e contador (opcional) */}
          <div className={styles.controles}>
            <button
              type="button"
              className={styles.controleBotao}
              onClick={() => mudarRelato('cima')}
              disabled={animando}
              aria-label="Relato anterior"
            >
              ↑
            </button>

            
            <button
              type="button"
              className={styles.controleBotao}
              onClick={() => mudarRelato('baixo')}
              disabled={animando}
              aria-label="Próximo relato"
            >
              ↓
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}

// Componente que renderiza o conteúdo de cada card (com ou sem imagem)
function RelatoConteudo({ relato }) {
  const temImagem = relato.imagem && relato.imagem.trim() !== '';

  if (!temImagem) {
    // Versão sem imagem: texto centralizado
    return (
      <div className={`${styles.conteudoRelato} ${styles.semImagem}`}>
        <p>“{relato.relato}”</p>
        <div className={styles.autorCentralizado}>
          <strong>{relato.nome}</strong>
          <span>{relato.cargo}</span>
        </div>
      </div>
    );
  }

  // Versão com imagem: foto à esquerda e texto à direita
  return (
    <div className={styles.conteudoRelato}>
      <div className={styles.perfil}>
        <img src={relato.imagem} alt={relato.nome} />
        <div>
          <strong>{relato.nome}</strong>
          <span>{relato.cargo}</span>
        </div>
      </div>
      <div className={styles.texto}>
        <p>“{relato.relato}”</p>
      </div>
    </div>
  );
}

export default Relatos;