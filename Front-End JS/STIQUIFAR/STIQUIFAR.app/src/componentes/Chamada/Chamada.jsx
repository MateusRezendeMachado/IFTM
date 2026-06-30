import styles from './Chamada.module.css';

function Chamada() {
  return (
    <section className={styles.chamada} id="inicio">
      <div className={styles.overlay}></div>

      <div className={styles.container}>
        <div className={styles.conteudo}>
          <h1>
            Seus Direitos <br />
            são <span>Conquistas</span>
          </h1>

          <p>
            Fortaleça-se com o STIQUIFAR  
            
          </p>

          <a href="#duvidas" className={styles.botao}>
            Fale com o STIQUIFAR
          </a>
        </div>
      </div>
    </section>
  );
}

export default Chamada;