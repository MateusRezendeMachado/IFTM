import { createContext, useContext, useEffect, useReducer } from 'react';
import { eventosReducer, estadoInicialEventos } from '../reducers/eventosReducer';
import { eventosMock } from '../dados/eventosMock';

const EventosContexto = createContext(undefined);

export function EventosProvedor({ children }) {
  const [estado, despachar] = useReducer(eventosReducer, estadoInicialEventos);

  useEffect(() => {
    // A API real do campus não está no ar, e manter uma API mock local
    // (json-server) estava gerando atrito de ambiente maior do que valor
    // para o que é avaliado aqui. Decisão de equipe: simular a resposta do
    // servidor com um array fixo (src/dados/eventosMock.js), mas mantendo
    // o MESMO fluxo assíncrono que teríamos com uma requisição de verdade
    // - CARREGANDO -> SUCESSO, via useReducer (R4), com cancelamento no
    // unmount (R5) - para não perder a máquina de estados já validada.
    let cancelado = false;

    despachar({ tipo: 'CARREGANDO' });

    const temporizador = setTimeout(() => {
      if (cancelado) return;
      despachar({ tipo: 'SUCESSO', payload: eventosMock });
    }, 400);

    // Função de limpeza: roda quando o provedor desmonta OU quando o efeito
    // é refeito. Marca a resposta como cancelada e limpa o timer, evitando
    // despachar SUCESSO para um componente que já desmontou (equivalente ao
    // controlador.abort() que faríamos com fetch).
    return () => {
      cancelado = true;
      clearTimeout(temporizador);
    };
  }, []);

  return (
    <EventosContexto.Provider value={estado}>
      {children}
    </EventosContexto.Provider>
  );
}

export function useEventos() {
  const contexto = useContext(EventosContexto);
  if (contexto === undefined) {
    throw new Error('useEventos precisa ser usado dentro de um EventosProvedor');
  }
  return contexto;
}
