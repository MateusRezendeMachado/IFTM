// Dados inseridos manualmente para representar o retorno do servidor.
//
// Decisão de equipe: a API real do campus não está no ar, e manter uma API
// mock local (json-server) estava gerando atrito de ambiente (Expo Go,
// endereço de IP por dispositivo, etc.) sem agregar ao que é avaliado no
// trabalho. Optamos por simular a resposta do servidor com estes dados
// fixos, mantendo em EventosContexto.js o mesmo fluxo assíncrono
// (CARREGANDO -> SUCESSO/FALHA via useReducer, com cancelamento no
// unmount) que teríamos com uma requisição de verdade.
export const eventosMock = [
  {
    id: 1,
    titulo: 'Semana Nacional de Ciência e Tecnologia',
    local: 'Auditório Central',
    data: '2026-10-14',
    vagas: 40,
    descricao: 'Palestras, minicursos e mostra de projetos dos cursos técnicos e superiores do campus.',
  },
  {
    id: 2,
    titulo: 'Maratona de Programação IFTM',
    local: 'Laboratório de Redes',
    data: '2026-09-27',
    vagas: 15,
    descricao: 'Competição em equipes de até 3 pessoas, com problemas de algoritmos e estruturas de dados.',
  },
  {
    id: 3,
    titulo: 'Feira de Extensão e Iniciação Científica',
    local: 'Ginásio Poliesportivo',
    data: '2026-11-05',
    vagas: 200,
    descricao: 'Exposição aberta à comunidade com projetos de pesquisa, extensão e inovação do campus.',
  },
  {
    id: 4,
    titulo: 'Oficina de React Native para Iniciantes',
    local: 'Laboratório de Desenvolvimento Mobile',
    data: '2026-10-02',
    vagas: 25,
    descricao: 'Introdução prática à criação de aplicativos móveis multiplataforma com React Native e Expo.',
  },
  {
    id: 5,
    titulo: 'Palestra: Gerenciamento de Estado em Aplicações Modernas',
    local: 'Auditório Central',
    data: '2026-09-30',
    vagas: 60,
    descricao: 'Discussão sobre estado local, de servidor, global e persistido, com estudos de caso reais.',
  },
  {
    id: 6,
    titulo: 'Torneio Interclasses de Futsal',
    local: 'Quadra Poliesportiva',
    data: '2026-10-20',
    vagas: 80,
    descricao: 'Torneio esportivo entre turmas do campus, aberto para inscrição de times e torcedores.',
  },
];
