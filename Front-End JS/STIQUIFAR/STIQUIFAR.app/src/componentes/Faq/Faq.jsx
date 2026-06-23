import { useEffect, useState } from 'react';
import styles from './Faq.module.css';

function Faq() {
  const url = 'https://samuca698.github.io/java_script/json';

  const [faqs, setFaqs] = useState([]);

  useEffect(() => {
    async function lerFaq() {
      try {
        const resposta = await fetch(url);
        const dados = await resposta.json();

        if (dados.faqs) {
          setFaqs(dados.faqs);
        } else {
          setFaqs(dados);
        }
      } catch (erro) {
        console.error('Erro ao carregar FAQ:', erro);
      }
    }

    lerFaq();
  }, []);

  return (
    <section className={styles.faq} id="faq">
      <div className={styles.container}>
        <div className={styles.cabecalho}>
          <h2>Perguntas Frequentes</h2>

        
        </div>

        {faqs.length === 0 ? (
          <p className={styles.carregando}>Carregando perguntas...</p>
        ) : (
          <div className={styles.listaFaq}>
            {faqs.map((faq, index) => (
              <details className={styles.itemFaq} key={index}>
                <summary className={styles.pergunta}>
                  <div>
                    <h3>{faq.pergunta}</h3>
                  </div>

                  <span className={styles.icone}>+</span>
                </summary>

                <div className={styles.respostaContainer}>
                  <p>{faq.resposta}</p>
                </div>
              </details>
            ))}
          </div>
        )}
      </div>
    </section>
  );
}

export default Faq;