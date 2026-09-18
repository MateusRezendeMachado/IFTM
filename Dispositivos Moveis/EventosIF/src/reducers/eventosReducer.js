// R4: redutor PURO - sem fetch, sem Date.now(), sem console.log aqui dentro.
// Antes: 4 booleans/valores independentes (eventos, carregando, erro, enviado)
// permitiam 2^4 = 16 combinações, das quais só ~5 fazem sentido (ver 5.1 no
// relatório). Agora existe um único campo `status`, então só uma situação
// pode ser verdadeira de cada vez.
export const estadoInicialEventos = {
  status: 'carregando', // 'carregando' | 'sucesso' | 'falha'
  eventos: [],
  erro: null,
};

export function eventosReducer(estado, acao) {
  switch (acao.tipo) {
    case 'CARREGANDO':
      return { status: 'carregando', eventos: [], erro: null };
    case 'SUCESSO':
      return { status: 'sucesso', eventos: acao.payload, erro: null };
    case 'FALHA':
      return { status: 'falha', eventos: [], erro: acao.payload };
    default:
      return estado;
  }
}
