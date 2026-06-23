// Remove tudo que não for número
export function somenteNumeros(valor) {
  return String(valor || '').replace(/\D/g, '');
}

// Formata CPF com pontos e traço, limitando a 11 dígitos
export function formatarCPF(valor) {
  const numeros = somenteNumeros(valor).slice(0, 11);

  if (numeros.length <= 3) {
    return numeros;
  }

  if (numeros.length <= 6) {
    return `${numeros.slice(0, 3)}.${numeros.slice(3)}`;
  }

  if (numeros.length <= 9) {
    return `${numeros.slice(0, 3)}.${numeros.slice(3, 6)}.${numeros.slice(6)}`;
  }

  return `${numeros.slice(0, 3)}.${numeros.slice(3, 6)}.${numeros.slice(
    6,
    9
  )}-${numeros.slice(9)}`;
}

// Formata CEP com hífen, limitando a 8 dígitos
export function formatarCEP(valor) {
  const numeros = somenteNumeros(valor).slice(0, 8);

  if (numeros.length <= 5) {
    return numeros;
  }

  return `${numeros.slice(0, 5)}-${numeros.slice(5)}`;
}

// Formata telefone com DDD, parênteses e hífen (10 ou 11 dígitos)
export function formatarTelefone(valor) {
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

// Formata valor como moeda BRL, dividindo por 100 (centavos)
export function formatarSalario(valor) {
  const numeros = somenteNumeros(valor).slice(0, 12);

  if (!numeros) {
    return '';
  }

  const valorNumerico = Number(numeros) / 100;

  return valorNumerico.toLocaleString('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  });
}

// Valida CPF: tamanho, dígitos repetidos e cálculo dos dois verificadores
export function validarCPF(cpf) {
  const cpfLimpo = somenteNumeros(cpf);

  if (cpfLimpo.length !== 11) {
    return false;
  }

  if (/^(\d)\1{10}$/.test(cpfLimpo)) {
    return false;
  }

  let soma = 0;

  for (let i = 0; i < 9; i++) {
    soma += Number(cpfLimpo[i]) * (10 - i);
  }

  let primeiroDigito = (soma * 10) % 11;

  if (primeiroDigito === 10) {
    primeiroDigito = 0;
  }

  if (primeiroDigito !== Number(cpfLimpo[9])) {
    return false;
  }

  soma = 0;

  for (let i = 0; i < 10; i++) {
    soma += Number(cpfLimpo[i]) * (11 - i);
  }

  let segundoDigito = (soma * 10) % 11;

  if (segundoDigito === 10) {
    segundoDigito = 0;
  }

  return segundoDigito === Number(cpfLimpo[10]);
}

// Valida e-mail com regex básico (contém @ e domínio)
export function validarEmail(email) {
  const emailLimpo = String(email || '').trim();

  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(emailLimpo);
}

// Valida telefone: tamanho 10 ou 11, DDD válido e não todos repetidos
export function validarTelefone(telefone) {
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

// Valida CEP: exatamente 8 dígitos
export function validarCEP(cep) {
  const numeros = somenteNumeros(cep);

  return numeros.length === 8;
}

// Valida data de nascimento: formato válido, não futura, não anterior a 1900
export function validarDataNascimento(dataNascimento) {
  if (!dataNascimento) {
    return false;
  }

  const data = new Date(`${dataNascimento}T00:00:00`);
  const hoje = new Date();
  const dataMinima = new Date('1900-01-01T00:00:00');

  if (Number.isNaN(data.getTime())) {
    return false;
  }

  if (data > hoje) {
    return false;
  }

  if (data < dataMinima) {
    return false;
  }

  return true;
}

// Valida salário: converte para número e verifica se >= 0
export function validarSalario(salario) {
  const texto = String(salario || '').trim();

  if (!texto) {
    return false;
  }

  const numeros = somenteNumeros(texto);

  if (!numeros) {
    return false;
  }

  const valor = Number(numeros) / 100;

  return !Number.isNaN(valor) && valor >= 0;
}

// Valida todos os campos do formulário de filiação, retornando mensagem de erro ou vazio
export function validarFormularioFiliacao(form) {
  if (!form.nomeCompleto.trim()) {
    return 'Informe o nome completo.';
  }

  if (!validarCPF(form.cpf)) {
    return 'Informe um CPF válido.';
  }

  if (form.rg.trim() && form.rg.trim().length < 5) {
    return 'Informe um RG válido ou deixe o campo em branco.';
  }

  if (!validarDataNascimento(form.dataNascimento)) {
    return 'Informe uma data de nascimento válida.';
  }

  if (!form.estadoCivil) {
    return 'Selecione o estado civil.';
  }

  if (!form.endereco.trim()) {
    return 'Informe o endereço completo.';
  }

  if (!form.bairro.trim()) {
    return 'Informe o bairro.';
  }

  if (!form.cidade.trim()) {
    return 'Informe a cidade.';
  }

  if (!validarCEP(form.cep)) {
    return 'Informe um CEP válido com 8 dígitos.';
  }

  if (!validarTelefone(form.telefone)) {
    return 'Informe um telefone/WhatsApp válido com DDD.';
  }

  if (!validarEmail(form.email)) {
    return 'Informe um e-mail válido.';
  }

  if (!form.empresa.trim()) {
    return 'Informe a empresa.';
  }

  if (!form.cargo.trim()) {
    return 'Informe o cargo ou função.';
  }

  if (!validarSalario(form.salario)) {
    return 'Informe o salário. Caso não possua renda, informe R$ 0,00.';
  }

  if (!form.clubeCepes) {
    return 'Informe se deseja ou não ser sócio do Clube CEPES.';
  }

  if (form.clubeCepes === 'Sim' && !form.aceitouTermosCepes) {
    return 'Para solicitar associação ao Clube CEPES, é necessário aceitar os termos.';
  }

  return '';
}

// Consulta ViaCEP para verificar se o CEP existe, retorna objeto com valido e dados ou mensagem de erro
export async function verificarCepExistente(cep) {
  const cepLimpo = somenteNumeros(cep);

  if (cepLimpo.length !== 8) {
    return {
      valido: false,
      mensagem: 'Informe um CEP válido com 8 dígitos.'
    };
  }

  try {
    const resposta = await fetch(`https://viacep.com.br/ws/${cepLimpo}/json/`);
    const dados = await resposta.json();

    if (dados.erro) {
      return {
        valido: false,
        mensagem: 'O CEP informado não foi encontrado.'
      };
    }

    return {
      valido: true,
      dados
    };
  } catch (erro) {
    return {
      valido: false,
      mensagem: 'Não foi possível validar o CEP no momento. Tente novamente.'
    };
  }
}