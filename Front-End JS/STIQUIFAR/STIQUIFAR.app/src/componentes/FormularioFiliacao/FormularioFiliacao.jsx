import { useState } from 'react';
import emailjs from '@emailjs/browser';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import styles from './FormularioFiliacao.module.css';

function FormularioFiliacao() {
  const SERVICE_ID = 'service_fwcs3cf';
  const TEMPLATE_ID = 'template_fkrgy5g';
  const PUBLIC_KEY = 'DQlRoRx3RgJhiQJw7';


  const estadoInicial = {
    nome: '',
    email: '',
    telefone: '',
    duvida: ''
  };

  const [form, setForm] = useState(estadoInicial);
  const [mensagem, setMensagem] = useState('');
  const [tipoMensagem, setTipoMensagem] = useState('');
  const [enviando, setEnviando] = useState(false);

  function somenteNumeros(valor) {
    return String(valor || '').replace(/\D/g, '');
  }

  function formatarTelefone(valor) {
    const numeros = somenteNumeros(valor).slice(0, 11);

    if (!numeros) {
      return '';
    }

    if (numeros.length <= 2) {
      return `(${numeros}`;
    }

    if (numeros.length <= 6) {
      return `(${numeros.slice(0, 2)}) ${numeros.slice(2)}`;
    }

    if (numeros.length <= 10) {
      return `(${numeros.slice(0, 2)}) ${numeros.slice(2, 6)}-${numeros.slice(6)}`;
    }

    return `(${numeros.slice(0, 2)}) ${numeros.slice(2, 7)}-${numeros.slice(7)}`;
  }

  function validarEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(String(email).trim());
  }

  function validarTelefone(telefone) {
    const numeros = somenteNumeros(telefone);

    if (numeros.length !== 10 && numeros.length !== 11) {
      return false;
    }

    const ddd = Number(numeros.slice(0, 2));

    if (ddd < 11 || ddd > 99) {
      return false;
    }

    if (/^(\d)\1+$/.test(numeros)) {
      return false;
    }

    return true;
  }

  function validarFormulario() {
    if (!form.nome.trim()) {
      return 'Informe seu nome.';
    }

    if (!validarEmail(form.email)) {
      return 'Informe um e-mail válido.';
    }

    if (!validarTelefone(form.telefone)) {
      return 'Informe um telefone válido com DDD.';
    }

    if (!form.duvida.trim()) {
      return 'Digite sua dúvida ou mensagem.';
    }

    return '';
  }

  function alterarForm(evento) {
    const { name, value } = evento.target;

    let valorFinal = value;

    if (name === 'telefone') {
      valorFinal = formatarTelefone(value);
    }

    setForm({
      ...form,
      [name]: valorFinal
    });

    setMensagem('');
    setTipoMensagem('');
  }

  function montarMensagemEmail() {
    return `
NOVA MENSAGEM RECEBIDA PELA LANDING PAGE STIQUIFAR

Nome: ${form.nome}
E-mail: ${form.email}
Telefone/WhatsApp: ${form.telefone}

Dúvida/Mensagem:
${form.duvida}
    `;
  }

  function configuracaoSwal() {
    return {
      customClass: {
        popup: 'swal-stiquifar',
        title: 'swal-titulo-stiquifar',
        htmlContainer: 'swal-texto-stiquifar',
        confirmButton: 'swal-botao-stiquifar',
        cancelButton: 'swal-botao-cancelar-stiquifar',
        actions: 'swal-acoes-stiquifar'
      },
      buttonsStyling: false
    };
  }

  async function enviarFormulario(evento) {
    evento.preventDefault();

    const erroValidacao = validarFormulario();

    if (erroValidacao) {
      setMensagem(erroValidacao);
      setTipoMensagem('erro');

      Swal.fire({
        ...configuracaoSwal(),
        icon: 'error',
        title: 'Atenção',
        text: erroValidacao,
        confirmButtonText: 'Corrigir'
      });

      return;
    }

    setEnviando(true);
    setMensagem('Enviando mensagem...');
    setTipoMensagem('info');

    const dadosEmail = {
      name: form.nome,
      email: form.email,
      phone: form.telefone,
      time: new Date().toLocaleString('pt-BR'),
      message: montarMensagemEmail()
    };

    try {
      await emailjs.send(SERVICE_ID, TEMPLATE_ID, dadosEmail, PUBLIC_KEY);

      setMensagem('Mensagem enviada com sucesso! Em breve entraremos em contato.');
      setTipoMensagem('sucesso');
      setForm(estadoInicial);

      await Swal.fire({
        ...configuracaoSwal(),
        icon: 'success',
        title: 'Mensagem enviada!',
        html: `
        <strong>Sua mensagem foi enviada com sucesso.</strong>
        <br />
        <span>Em breve o STIQUIFAR entrará em contato.</span>
      `,
        confirmButtonText: 'Fechar',
        draggable: true
      });

    } catch (erro) {
      console.error('Erro ao enviar mensagem:', erro);

      setMensagem('Não foi possível enviar a mensagem. Tente novamente em alguns instantes.');
      setTipoMensagem('erro');

      Swal.fire({
        ...configuracaoSwal(),
        icon: 'error',
        title: 'Erro ao enviar',
        text: 'Não foi possível enviar a mensagem. Tente novamente em alguns instantes.',
        confirmButtonText: 'Entendi'
      });

    } finally {
      setEnviando(false);
    }
  }

  return (
    <section className={styles.formularioSection} id="duvidas">
      <div className={styles.container}>
        <div className={styles.cabecalho}>
          <span>Fale com o sindicato</span>

          <h2>Tem alguma dúvida?</h2>

          <p>
            Envie uma mensagem rápida para o STIQUIFAR. Nossa equipe entrará em
            contato para orientar você da melhor forma.
          </p>
        </div>

        <form className={styles.formulario} onSubmit={enviarFormulario}>
          <div className={styles.grid}>
            <div className={styles.campo}>
              <label htmlFor="nome">Nome *</label>
              <input
                type="text"
                id="nome"
                name="nome"
                value={form.nome}
                onChange={alterarForm}
                placeholder="Digite seu nome"
                required
              />
            </div>

            <div className={styles.campo}>
              <label htmlFor="email">E-mail *</label>
              <input
                type="email"
                id="email"
                name="email"
                value={form.email}
                onChange={alterarForm}
                placeholder="seuemail@exemplo.com"
                required
              />
            </div>

            <div className={`${styles.campo} ${styles.completo}`}>
              <label htmlFor="telefone">Telefone / WhatsApp *</label>
              <input
                type="tel"
                id="telefone"
                name="telefone"
                value={form.telefone}
                onChange={alterarForm}
                inputMode="numeric"
                maxLength="15"
                placeholder="(00) 00000-0000"
                required
              />
            </div>

            <div className={`${styles.campo} ${styles.completo}`}>
              <label htmlFor="duvida">Sua dúvida *</label>
              <textarea
                id="duvida"
                name="duvida"
                value={form.duvida}
                onChange={alterarForm}
                rows="6"
                placeholder="Digite sua dúvida ou mensagem..."
                required
              ></textarea>
            </div>
          </div>

          <button type="submit" className={styles.botao} disabled={enviando}>
            {enviando ? 'Enviando...' : 'Enviar mensagem'}
          </button>

          {mensagem && (
            <p className={`${styles.mensagem} ${styles[tipoMensagem]}`}>
              {mensagem}
            </p>
          )}
        </form>
      </div>
    </section>
  );
}

export default FormularioFiliacao;