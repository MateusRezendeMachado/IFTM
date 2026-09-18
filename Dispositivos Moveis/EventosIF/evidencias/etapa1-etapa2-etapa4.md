# Anexo — Etapas 1, 2 e 4 (tabelas completas)

> A tabela final da Etapa 1 e a conta da Etapa 5.1 também estão em `relatorio.md`; aqui vai o preenchimento completo pedido no enunciado, incluindo as perguntas de fechamento e o diagnóstico chamado a chamado.

## Etapa 1 — Perguntas de fechamento

1. **Quem precisa de `inscricoes`?** `TelaEventos` (inscrever, mostrar contador, desabilitar botão do cartão já inscrito) e `TelaMinhasInscricoes` (listar, cancelar). `CartaoEvento` também, indiretamente, via prop `jaInscrito`.
2. **Por quanto tempo `inscricoes` precisa existir?** Enquanto o aplicativo roda (não sobrevive ao fechar, hoje) — é exatamente o que o **C7** relata.
3. **De onde vem cada um dos oito dados?**
   - `busca`: usuário digitou.
   - `eventos`: servidor mandou.
   - `eventosFiltrados`: programa calculou.
   - `inscricoes`/`idsInscricoes`: ação do usuário (tocar em "Inscrever"), guardada no cliente.
   - `totalInscricoes`: programa calculou.
   - `eventoSelecionado`/`idEvento`: usuário selecionou (é só um id passado via navegação).
   - `temaEscuro`: usuário escolheu nas preferências.
   - `usuario`: viria do servidor/login (hoje é só um valor padrão "Visitante").

## Etapa 2 — Diagnóstico chamado a chamado

| Chamado | Arquivo e trecho | Nome do erro | Consequência |
|---|---|---|---|
| **C1** | `TelaMinhasInscricoes.js` (`useState` próprio) + `TelaEventos.js` (`inscricoes` também local) | Estado duplicado / morada errada | Inscrever numa tela não aparece na outra, porque cada uma tem sua própria cópia de "inscrições". |
| **C2** | `TelaEventos.js`, `inscrever()` com `inscricoes.push(evento); setInscricoes(inscricoes)` + o `useEffect` que sincroniza `totalInscricoes` a partir de `inscricoes` | Mutação direta + estado derivado guardado | A referência do vetor não muda, então o React não re-renderiza quem depende dele; o contador só "acerta" quando outra coisa (como digitar na busca) força uma renderização. |
| **C3** | `TelaEventos.js` guardava o objeto `evento` inteiro em `eventoSelecionado`; `TelaDetalheEvento.js` recebia esse objeto via `route.params` | Estado duplicado | O objeto do detalhe é uma cópia congelada do evento no momento da navegação; se `vagas` mudar na lista, o detalhe não percebe até fechar/reabrir o app. |
| **C4** | `TelaEventos.js` — quatro `useState` independentes (`eventos`, `carregando`, `erro`, `enviado`) | Estados impossíveis | A combinação `carregando=true` + `erro` preenchido é representável mesmo sem fazer sentido; quando a API falha, `carregando` nunca vira `false`, e o spinner gira ao lado da mensagem de erro para sempre. |
| **C5** | `TelaEventos.js`, `useEffect` do fetch sem função de limpeza / sem cancelamento | Requisição sem cancelamento | Sair da tela antes da resposta chegar tenta atualizar um componente desmontado (aviso no console) e pode aplicar uma resposta de busca antiga por cima da atual. |
| **C6** | `AppContexto.js`, `value={{ usuario, setUsuario, temaEscuro, ... }}` como objeto literal, num único contexto para tudo | Contexto único + `value` sem `useMemo` | Qualquer mudança em qualquer uma das quatro variáveis recria o objeto `value` inteiro e re-renderiza todo consumidor do `AppContexto`, mesmo quem só usa `usuario`. |
| **C7** | `AppContexto.js` — `temaEscuro` e `inscricoes` moram só em estado de cliente (Context/`useState`) | Morada errada (falta a 5ª morada — persistido) | Ao reabrir o app, tudo volta ao estado inicial porque nada foi salvo em disco. Registrado como pendência (ver `relatorio.md`, seção 6) — não implementado nesta etapa por instrução do enunciado. |

## Etapa 4 — Evidências de renderização

**Importante**: os números abaixo foram *previstos a partir da leitura do código* (mecanismo de cada bug/correção), não capturados rodando o app de verdade — este ambiente de texto não executa React Native/Expo. Antes de entregar, a equipe deve rodar `npm install && npm start`, repetir os três cenários com a mesma lista fixa de eventos nas duas medições, e colar aqui as capturas reais do console. Os números assumem, como exemplo, uma lista fixa de **3 eventos visíveis** (ajustar `N` para o valor real do dataset de teste).

| Cenário | Componente | Antes (previsto) | Depois (previsto) | Por que mudou |
|---|---|---|---|---|
| Digitar 5 letras na busca | `TelaEventos` | 10 (2 por letra: 1 do `setBusca` + 1 do `useEffect` que sincroniza `eventosFiltrados`) | 5 (1 por letra: filtro calculado na renderização) | R1 remove o `useEffect` de sincronização — não há mais um segundo render por tecla. |
| Digitar 5 letras na busca | `CartaoEvento` (total, N=3) | ~30 (10 renders de `TelaEventos` × 3 cartões, já que `eventosFiltrados` trocava de referência a cada render) | ~15 (5 renders × 3 cartões) | Consequência direta da redução acima. |
| Tocar em "Inscrever" uma vez | `TelaEventos` | 1 render, mas **contador não atualiza** (bug do C2: `setInscricoes(inscricoes)` com a mesma referência não dispara re-render pelo vetor; só o `setEnviado`/`setEventoSelecionado` gera o render, com o contador ainda mostrando o valor antigo) | 1 render, contador **já correto** (estado novo com spread + setter funcional) | R2 corrige a mutação direta. |
| Alternar o tema | `TelaMinhasInscricoes` | 1 (o `value` do `AppContexto` era recriado a cada toggle, e todo consumidor — mesmo quem só lia `inscricoes` — re-renderizava) | 0 (esta tela não consome mais `TemaContexto`) | R7 separa o contexto por preocupação. |
| Alternar o tema | `CartaoEvento` (total, N=3) | 3 (cascata a partir de `TelaEventos`, que também consumia o `AppContexto` inteiro) | 0 (`CartaoEvento` está em `memo`, e suas props não mudam quando só o tema muda) | R6 (memo) + R7 (contexto separado) juntos. |

**Teste do C5** (entrar e sair da tela de Eventos cinco vezes seguidas, rapidamente):
- **Antes da R5**: espera-se ver, no console, avisos do tipo *"Can't perform a React state update on an unmounted component"* em algumas das cinco repetições (depende da velocidade de resposta da API simulada/mock).
- **Depois da R5**: nenhum aviso — a função de limpeza do `useEffect` aborta a requisição pendente com `AbortController`, e o `catch` ignora explicitamente `AbortError`.

A equipe deve substituir as previsões acima pelas capturas reais de tela do console (antes/depois) e salvar como `evidencias/console-antes.png` e `evidencias/console-depois.png`, junto com o registro escrito do teste de C5.
