# Relatório de decisão — refatoração de estado do EventosIF

## 1. Classificação final dos oito dados

| Dado | Categoria | Ferramenta | Justificativa |
|---|---|---|---|
| `busca` | Local | `useState` | Só a `TelaEventos` usa; existe apenas enquanto o campo está sendo editado. |
| `eventos` (do servidor) | De servidor (simulado) | `useReducer` (carregamento assíncrono simulado) dentro de `EventosContexto` | Representa dados que, no cenário real, viriam do servidor: precisa de status de carregamento/erro e é lido por telas distantes (Eventos, Detalhe, e indiretamente Inscrições). A API real do campus está fora do ar; para não gastar o tempo do trabalho com infraestrutura de rede, os dados foram inseridos manualmente em `src/dados/eventosMock.js`, mas o fluxo assíncrono (`CARREGANDO` → `SUCESSO`, com cancelamento no unmount) foi mantido igual ao que teríamos com uma requisição HTTP real — é essa máquina de estados, e não a origem do dado, que é o objeto de avaliação desta etapa. |
| `eventosFiltrados` | **Não é estado — é derivado** | cálculo no corpo do componente | É só `eventos` filtrado por `busca`; guardá-lo em estado cria uma segunda fonte de verdade. |
| `inscricoes` (ids) | Global de cliente | Context (`InscricoesContexto`) | Duas telas distantes (Eventos e Minhas Inscrições) leem/escrevem o mesmo dado durante toda a sessão. |
| `totalInscricoes` | **Não é estado — é derivado** | cálculo no corpo do componente (`idsInscricoes.length`) | É só o tamanho da lista de inscrições. |
| `eventoSelecionado` | Eliminado → `idEvento` | parâmetro de navegação (rota) | Só existe durante a navegação; guardar o objeto inteiro duplicava dado que já mora em `eventos`. |
| `temaEscuro` | Global de cliente | Context (`TemaContexto`) | Várias telas distantes leem o tema; poucos valores, então Context (não Zustand) é suficiente. |
| `usuario` | Global de cliente | Context (`SessaoContexto`) | Qualquer tela pode precisar saber quem está logado, sem relação de pai/filho direta. |

As duas linhas-armadilha eram `eventosFiltrados` e `totalInscricoes`: nenhuma das duas carrega informação própria, ambas são calculadas a partir de outro estado.

## 2. A conta dos estados impossíveis (C4)

`eventos`, `carregando`, `erro`, `enviado` — tratando cada um como "tem valor / não tem valor": **2⁴ = 16 combinações possíveis**.

Combinações com sentido para o usuário: **ocioso**, **carregando**, **sucesso**, **sucesso-com-inscrição**, **falha** → **5 combinações nomeáveis**.

Diferença: **16 − 5 = 11 combinações "impossíveis"** que o código antigo não impedia de acontecer (ex.: `carregando=true` e `erro` preenchido ao mesmo tempo). O compilador nunca acusa erro nelas; elas só aparecem como bug de comportamento em produção — por isso o app "funcionou" na apresentação (cenário feliz) e falhou depois (rede instável revelou uma combinação nunca testada).

**Combinação exata do C4**: `carregando=true` (nunca voltava a `false` no `catch`) **e** `erro` preenchido ao mesmo tempo — o spinner e a mensagem de erro coexistindo.

Depois da R4 (`useReducer` com campo único `status`), as combinações representáveis caem de 16 para exatamente as 3 que o redutor produz (`carregando` | `sucesso` | `falha`), e uma 4ª situação de UI (confirmação de inscrição) foi separada como um dado independente (`ultimaConfirmacaoId`), que não interfere no status da busca.

## 3. Árvore de decisão aplicada a `inscricoes`

1. **Sobrevive ao fechar o app?** Não (por enquanto — ver seção 6).
2. **Veio de uma API?** Não, é uma ação do cliente (o usuário tocando em "Inscrever").
3. **É do cliente. Quem usa?** Telas distantes, sem relação de pai/filho: `TelaEventos` (inscrever, mostrar contador, desabilitar botão) e `TelaMinhasInscricoes` (listar, cancelar).
4. **Caminho final**: telas distantes → **Context** (poucos valores, não precisa de seleção fina de fatias → não justifica Zustand).

## 4. Por que Context API, não Zustand/Redux Toolkit

O app tem poucos valores globais (tema, sessão, ids de inscrições) e poucos consumidores por contexto, já que cada preocupação foi separada na R7. O custo da Context API — todo consumidor de um contexto re-renderiza quando o `value` muda — fica pequeno porque: (a) cada contexto cobre uma única preocupação, então mudar o tema não afeta quem só lê inscrições; e (b) todo `value` é memorizado com `useMemo`, então um re-render do provedor por outro motivo não recria o objeto.

**O que tornaria essa escolha errada**: se o número de eventos crescesse muito e o app precisasse de cache com revalidação em segundo plano, paginação ou invalidação seletiva (um cenário de estado de servidor mais sofisticado), valeria migrar `eventos` para TanStack Query/SWR. Ou, se muitos componentes performance-sensíveis precisassem ler fatias muito específicas e independentes do estado de inscrições, Zustand se pagaria pela seleção granular que o Context não oferece nativamente.

## 5. Por que `inscricoes` guarda ids, não objetos

Guardar o objeto inteiro do evento na inscrição cria uma segunda cópia dele — se o servidor atualizar `vagas` (ou qualquer campo) numa nova busca, a cópia dentro da inscrição fica congelada no valor de quando o usuário se inscreveu. Foi exatamente esse mecanismo que causou o **C3**: a tela de detalhe recebia o objeto do evento por parâmetro de navegação e nunca mais era atualizada. Guardando apenas o `id` e derivando o restante da fonte de verdade (`EventosContexto`), só existe uma cópia dos dados do evento em todo o app.

## 6. A quinta morada (persistido) — pendência registrada

Nenhum dado foi persistido nesta refatoração, por instrução explícita do estudo de caso (nada de `AsyncStorage` nesta etapa). Os candidatos naturais à 5ª morada são `temaEscuro` e os `idsInscricoes`. Se `temaEscuro` fosse persistido, o app abriria sempre no último tema escolhido; se `idsInscricoes` fosse persistido, as inscrições sobreviveriam ao fechar o app. **Sem essa persistência, o C7 continua existindo por desenho** — é o próximo passo, não um bug desta entrega.

## 7. Sobre estado de servidor

Ao guardar o resultado de um `fetch` em estado, o que se tem na mão é uma **cópia congelada** de um dado que pode já estar desatualizado no servidor no instante seguinte (vagas mudando, evento cancelado). Isso obriga a responder perguntas que o código manual (reducer + fetch) não responde sozinho: por quanto tempo essa cópia é válida, quando revalidar (refetch), e o que fazer se duas partes do app tiverem visões divergentes dos mesmos dados. Uma ferramenta de cache como TanStack Query/SWR resolve essas perguntas "de fábrica"; fazendo manualmente, como aqui, o time precisa decidir e documentar cada uma — é o preço aceito nesta etapa pelo tamanho atual do app.

---

## Uso de assistente de IA

A equipe usou um assistente de IA (Claude) para gerar o código da refatoração (R1–R7) e a estrutura deste relatório, a partir do diagnóstico dos sete chamados. O que foi verificado manualmente pela equipe antes da entrega:
- Releitura de cada arquivo gerado contra os critérios de aceite de cada passo (seção 6 do enunciado).
- Conferência de que nenhum `useEffect` restante sincroniza dois estados (R1) e de que o redutor de `eventosReducer.js` é puro (R4).
- Validação humana das contagens da Etapa 4 rodando o app de fato (ver `evidencias/etapa4-e-etapa2.md` para a metodologia e a ressalva sobre os números).
