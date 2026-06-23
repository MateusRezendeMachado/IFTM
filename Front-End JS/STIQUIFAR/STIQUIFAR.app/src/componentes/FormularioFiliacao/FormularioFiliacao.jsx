import { useState } from 'react';
import emailjs from '@emailjs/browser';
import {
  validarFormularioFiliacao,
  verificarCepExistente,
  formatarCPF,
  formatarCEP,
  formatarTelefone,
  formatarSalario
} from '../../utils/validacoesFormulario';
import styles from './FormularioFiliacao.module.css';

function FormularioFiliacao() {
  const SERVICE_ID = 'service_fwcs3cf';
  const TEMPLATE_ID = 'template_fkrgy5g';
  const PUBLIC_KEY = 'DQlRoRx3RgJhiQJw7';

  const dataMaxima = new Date().toISOString().split('T')[0];

  const estadoInicial = {
    nomeCompleto: '',
    cpf: '',
    rg: '',
    dataNascimento: '',
    estadoCivil: '',
    endereco: '',
    bairro: '',
    cidade: '',
    cep: '',
    telefone: '',
    email: '',
    empresa: '',
    cargo: '',
    salario: '',
    clubeCepes: '',
    aceitouTermosCepes: false,
    dependentes: '',
    observacoes: ''
  };

  const [form, setForm] = useState(estadoInicial);
  const [mensagem, setMensagem] = useState('');
  const [tipoMensagem, setTipoMensagem] = useState('');
  const [enviando, setEnviando] = useState(false);

  function alterarForm(evento) {
    const { name, value, type, checked } = evento.target;

    if (name === 'clubeCepes' && value === 'Não') {
      setForm({
        ...form,
        clubeCepes: value,
        aceitouTermosCepes: false
      });

      setMensagem('');
      setTipoMensagem('');
      return;
    }

    let valorFinal = type === 'checkbox' ? checked : value;

    if (name === 'cpf') {
      valorFinal = formatarCPF(value);
    }

    if (name === 'cep') {
      valorFinal = formatarCEP(value);
    }

    if (name === 'telefone') {
      valorFinal = formatarTelefone(value);
    }

    if (name === 'salario') {
      valorFinal = formatarSalario(value);
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
DADOS PESSOAIS
Nome completo: ${form.nomeCompleto}
CPF: ${form.cpf}
RG: ${form.rg || 'Não informado'}
Data de nascimento: ${form.dataNascimento}
Estado civil: ${form.estadoCivil}

ENDEREÇO E CONTATO
Endereço completo: ${form.endereco}
Bairro: ${form.bairro}
Cidade: ${form.cidade}
CEP: ${form.cep}
Telefone/WhatsApp: ${form.telefone}
E-mail: ${form.email}

DADOS PROFISSIONAIS
Empresa: ${form.empresa}
Cargo/Função: ${form.cargo}
Salário: ${form.salario}

CLUBE RECREATIVO CEPES STIQUIFAR
Gostaria de também ser Sócio do Clube CEPES?: ${form.clubeCepes}
Aceitou os termos do CEPES?: ${
      form.clubeCepes === 'Sim'
        ? form.aceitouTermosCepes
          ? 'Sim'
          : 'Não'
        : 'Não se aplica'
    }

INFORMAÇÕES OPCIONAIS
Dependentes: ${form.dependentes || 'Não informado'}

Observações complementares:
${form.observacoes || 'Não informado'}
    `;
  }

  async function enviarFormulario(evento) {
    evento.preventDefault();

    const erroValidacao = validarFormularioFiliacao(form);

    if (erroValidacao) {
      setMensagem(erroValidacao);
      setTipoMensagem('erro');
      return;
    }

    setEnviando(true);
    setMensagem('Validando CEP...');
    setTipoMensagem('info');

    const resultadoCep = await verificarCepExistente(form.cep);

    if (!resultadoCep.valido) {
      setMensagem(resultadoCep.mensagem);
      setTipoMensagem('erro');
      setEnviando(false);
      return;
    }

    setMensagem('Enviando solicitação...');
    setTipoMensagem('info');

    const dadosEmail = {
      name: form.nomeCompleto,
      email: form.email,
      phone: form.telefone,
      time: new Date().toLocaleString('pt-BR'),
      message: montarMensagemEmail()
    };

    try {
      await emailjs.send(SERVICE_ID, TEMPLATE_ID, dadosEmail, PUBLIC_KEY);

      setMensagem(
        'Solicitação enviada com sucesso! Em breve entraremos em contato.'
      );
      setTipoMensagem('sucesso');

      setForm(estadoInicial);
    } catch (erro) {
      console.error('Erro ao enviar formulário:', erro);

      setMensagem(
        'Não foi possível enviar a solicitação. Tente novamente em alguns instantes.'
      );
      setTipoMensagem('erro');
    } finally {
      setEnviando(false);
    }
  }

  return (
    <section className={styles.formularioSection} id="filiacao">
      <div className={styles.container}>
        <div className={styles.cabecalho}>
          <span>Filiação</span>

          <h2>Formulário de filiação STIQUIFAR</h2>

          <p>
            Preencha seus dados para iniciar o processo de filiação. As
            informações serão utilizadas para contato e encaminhamento da
            solicitação.
          </p>
        </div>

        <form className={styles.formulario} onSubmit={enviarFormulario}>
          <div className={styles.bloco}>
            <h3>Dados pessoais</h3>

            <div className={styles.grid}>
              <div className={`${styles.campo} ${styles.completo}`}>
                <label htmlFor="nomeCompleto">Nome completo *</label>
                <input
                  type="text"
                  id="nomeCompleto"
                  name="nomeCompleto"
                  value={form.nomeCompleto}
                  onChange={alterarForm}
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="cpf">CPF *</label>
                <input
                  type="text"
                  id="cpf"
                  name="cpf"
                  value={form.cpf}
                  onChange={alterarForm}
                  inputMode="numeric"
                  maxLength="14"
                  placeholder="000.000.000-00"
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="rg">RG</label>
                <input
                  type="text"
                  id="rg"
                  name="rg"
                  value={form.rg}
                  onChange={alterarForm}
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="dataNascimento">Data de nascimento *</label>
                <input
                  type="date"
                  id="dataNascimento"
                  name="dataNascimento"
                  value={form.dataNascimento}
                  onChange={alterarForm}
                  min="1900-01-01"
                  max={dataMaxima}
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="estadoCivil">Estado civil *</label>
                <select
                  id="estadoCivil"
                  name="estadoCivil"
                  value={form.estadoCivil}
                  onChange={alterarForm}
                  required
                >
                  <option value="">Selecione</option>
                  <option value="Solteiro(a)">Solteiro(a)</option>
                  <option value="Casado(a)">Casado(a)</option>
                  <option value="Divorciado(a)">Divorciado(a)</option>
                  <option value="Viúvo(a)">Viúvo(a)</option>
                  <option value="União estável">União estável</option>
                </select>
              </div>
            </div>
          </div>

          <div className={styles.bloco}>
            <h3>Endereço e contato</h3>

            <div className={styles.grid}>
              <div className={`${styles.campo} ${styles.completo}`}>
                <label htmlFor="endereco">Endereço completo *</label>
                <input
                  type="text"
                  id="endereco"
                  name="endereco"
                  value={form.endereco}
                  onChange={alterarForm}
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="bairro">Bairro *</label>
                <input
                  type="text"
                  id="bairro"
                  name="bairro"
                  value={form.bairro}
                  onChange={alterarForm}
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="cidade">Cidade *</label>
                <input
                  type="text"
                  id="cidade"
                  name="cidade"
                  value={form.cidade}
                  onChange={alterarForm}
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="cep">CEP *</label>
                <input
                  type="text"
                  id="cep"
                  name="cep"
                  value={form.cep}
                  onChange={alterarForm}
                  inputMode="numeric"
                  maxLength="9"
                  placeholder="00000-000"
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="telefone">Telefone *</label>
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
                <label htmlFor="email">E-mail *</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={form.email}
                  onChange={alterarForm}
                  required
                />
              </div>
            </div>
          </div>

          <div className={styles.bloco}>
            <h3>Dados profissionais</h3>

            <div className={styles.grid}>
              <div className={styles.campo}>
                <label htmlFor="empresa">Empresa *</label>
                <input
                  type="text"
                  id="empresa"
                  name="empresa"
                  value={form.empresa}
                  onChange={alterarForm}
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="cargo">Cargo / Função *</label>
                <input
                  type="text"
                  id="cargo"
                  name="cargo"
                  value={form.cargo}
                  onChange={alterarForm}
                  required
                />
              </div>

              <div className={styles.campo}>
                <label htmlFor="salario">Salário *</label>
                <input
                  type="text"
                  id="salario"
                  name="salario"
                  value={form.salario}
                  onChange={alterarForm}
                  inputMode="numeric"
                  placeholder="R$ 0,00"
                  required
                />
              </div>
            </div>
          </div>

          <div className={styles.bloco}>
            <h3>Associação ao Clube Recreativo CEPES STIQUIFAR</h3>

            <div className={styles.perguntaClube}>
              <p>Gostaria de também ser Sócio do Clube CEPES? *</p>

              <div className={styles.opcoesRadio}>
                <label>
                  <input
                    type="radio"
                    name="clubeCepes"
                    value="Sim"
                    checked={form.clubeCepes === 'Sim'}
                    onChange={alterarForm}
                    required
                  />
                  <span>Sim</span>
                </label>

                <label>
                  <input
                    type="radio"
                    name="clubeCepes"
                    value="Não"
                    checked={form.clubeCepes === 'Não'}
                    onChange={alterarForm}
                    required
                  />
                  <span>Não</span>
                </label>
              </div>

              {form.clubeCepes === 'Sim' && (
                <div className={styles.termosCepes}>
                  <p>
                    A adesão ao Clube Recreativo CEPES está sujeita à cobrança
                    de mensalidade específica, acrescida ao valor da contribuição
                    correspondente ao plano escolhido.
                  </p>

                  <label className={styles.checkTermos}>
                    <input
                      type="checkbox"
                      name="aceitouTermosCepes"
                      checked={form.aceitouTermosCepes}
                      onChange={alterarForm}
                      required={form.clubeCepes === 'Sim'}
                    />

                    <span>
                      Li e concordo com os termos de associação ao Clube
                      Recreativo CEPES STIQUIFAR.
                    </span>
                  </label>
                </div>
              )}
            </div>
          </div>

          <div className={styles.bloco}>
            <h3>Informações opcionais</h3>

            <div className={styles.grid}>
              <div className={`${styles.campo} ${styles.completo}`}>
                <label htmlFor="dependentes">Dependentes</label>
                <textarea
                  id="dependentes"
                  name="dependentes"
                  value={form.dependentes}
                  onChange={alterarForm}
                  rows="4"
                  placeholder="Informe nome, parentesco e idade, se desejar."
                ></textarea>
              </div>

              <div className={`${styles.campo} ${styles.completo}`}>
                <label htmlFor="observacoes">Observações complementares</label>
                <textarea
                  id="observacoes"
                  name="observacoes"
                  value={form.observacoes}
                  onChange={alterarForm}
                  rows="4"
                  placeholder="Digite alguma informação adicional, se necessário."
                ></textarea>
              </div>
            </div>
          </div>

          <div className={styles.encerramento}>
            <p>
              Ao se filiar ao STIQUIFAR, você fortalece a representação da
              categoria e passa a ter acesso aos benefícios, convênios,
              oportunidades e serviços oferecidos pelo sindicato.
            </p>

            <strong>
              Juntos somos mais fortes na defesa dos direitos e na construção de
              novas conquistas para os trabalhadores.
            </strong>
          </div>

          <button type="submit" className={styles.botao} disabled={enviando}>
            {enviando ? 'Enviando...' : 'Enviar solicitação de filiação'}
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