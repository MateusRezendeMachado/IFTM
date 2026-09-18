import { createContext, useCallback, useContext, useMemo, useState } from 'react';
import { useEventos } from './EventosContexto';

// R6: "inscricoes" era um dado que duas telas distantes (Eventos e
// Minhas Inscrições) precisavam, cada uma com sua própria cópia local -
// era exatamente esse o "estado duplicado" por trás do C1. Guardamos aqui
// só os IDS inscritos; a lista completa exibida é derivada cruzando esses
// ids com a fonte de verdade dos eventos (EventosContexto), então nunca
// existem dois objetos "evento" desatualizados em lugares diferentes.
const InscricoesContexto = createContext(undefined);

export function InscricoesProvedor({ children }) {
  const [idsInscricoes, setIdsInscricoes] = useState([]);
  const { eventos } = useEventos();

  const inscrever = useCallback((idEvento) => {
    setIdsInscricoes((atuais) => {
      if (atuais.includes(idEvento)) return atuais; // uma inscrição por evento
      return [...atuais, idEvento];
    });
  }, []);

  const cancelar = useCallback((idEvento) => {
    setIdsInscricoes((atuais) => atuais.filter((id) => id !== idEvento));
  }, []);

  const estaInscrito = useCallback(
    (idEvento) => idsInscricoes.includes(idEvento),
    [idsInscricoes]
  );

  const inscricoes = useMemo(
    () => eventos.filter((evento) => idsInscricoes.includes(evento.id)),
    [eventos, idsInscricoes]
  );

  // value memorizado: sem isso, todo re-render deste provedor criaria um
  // objeto novo e derrubaria a otimização de quem consome o contexto.
  const valor = useMemo(
    () => ({
      inscricoes,
      totalInscricoes: idsInscricoes.length,
      inscrever,
      cancelar,
      estaInscrito,
    }),
    [inscricoes, idsInscricoes.length, inscrever, cancelar, estaInscrito]
  );

  return (
    <InscricoesContexto.Provider value={valor}>
      {children}
    </InscricoesContexto.Provider>
  );
}

export function useInscricoes() {
  const contexto = useContext(InscricoesContexto);
  if (contexto === undefined) {
    throw new Error('useInscricoes precisa ser usado dentro de um InscricoesProvedor');
  }
  return contexto;
}
