# EventosIF — refatoração de estado

Estudo de caso: "O CampusApp que esquece" (IFTM — Sistemas para Internet, Desenvolvimento para Dispositivos Móveis).

## Como rodar
```
npm install
npm start
```
(Requer Expo CLI / app Expo Go no celular, ou emulador Android/iOS configurado.)

## Sobre os dados de eventos
A API real do campus (`https://api.campus.iftm.edu.br/eventos`) não está no
ar. Para não bloquear o trabalho de gerenciamento de estado com atrito de
ambiente (rede, IP por dispositivo, versão do Expo Go), a equipe decidiu
simular a resposta do servidor com dados fixos em
`src/dados/eventosMock.js`. `EventosContexto.js` continua usando o mesmo
fluxo assíncrono (`CARREGANDO` → `SUCESSO`, via `useReducer`, com
cancelamento no unmount) que teria com uma requisição HTTP real — só a
origem dos dados deixou de ser uma chamada de rede.

## Estrutura
```
EventosIF/
├── App.js
├── package.json
├── relatorio.md              ← Etapa 5 (relatório de decisão)
├── evidencias/
│   └── etapa1-etapa2-etapa4.md   ← tabelas completas + notas da Etapa 4
└── src/
    ├── dados/
    │   └── eventosMock.js     ← eventos inseridos manualmente (simula o servidor)
    ├── componentes/
    │   └── CartaoEvento.js
    ├── contextos/
    │   ├── TemaContexto.js
    │   ├── SessaoContexto.js
    │   ├── EventosContexto.js
    │   └── InscricoesContexto.js
    ├── reducers/
    │   └── eventosReducer.js
    └── telas/
        ├── TelaEventos.js
        ├── TelaDetalheEvento.js
        └── TelaMinhasInscricoes.js
```

## Histórico da refatoração
A branch `refatoracao-estado` contém, em ordem, os 7 commits pedidos no enunciado (um por passo R1–R7), a partir de um commit-base com o código exatamente como foi entregue pela equipe:

```
git log --oneline refatoracao-estado
```

| Commit | Passo |
|---|---|
| `refactor: remove estado derivado (filtro e total)` | R1 |
| `fix: cria novo vetor ao inscrever em vez de mutar o anterior` | R2 |
| `refactor: guarda id em vez do objeto do evento` | R3 |
| `refactor: substitui quatro estados por maquina de estados com useReducer` | R4 |
| `fix: cancela requisicao ao desmontar a tela` | R5 |
| `feat: cria contexto de inscricoes com hook proprio` | R6 |
| `refactor: separa AppContexto em um contexto por preocupacao` | R7 |

## Pendência registrada (não implementada nesta etapa)
Persistência (5ª morada) de `temaEscuro` e das inscrições com `AsyncStorage` — resolveria o C7, mas foi propositalmente deixada de fora por instrução do enunciado. Ver `relatorio.md`, seção 6.
