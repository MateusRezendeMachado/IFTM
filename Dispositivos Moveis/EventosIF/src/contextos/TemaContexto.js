import { createContext, useContext, useMemo, useState } from 'react';

const TemaContexto = createContext(undefined);

export function TemaProvedor({ children }) {
  const [temaEscuro, setTemaEscuro] = useState(false);

  // value memorizado: só muda quando temaEscuro muda, não a cada render
  // do provedor - essencial agora que este é o ÚNICO contexto que quem
  // só precisa do tema vai consumir.
  const valor = useMemo(() => ({ temaEscuro, setTemaEscuro }), [temaEscuro]);

  return (
    <TemaContexto.Provider value={valor}>
      {children}
    </TemaContexto.Provider>
  );
}

export function useTema() {
  const contexto = useContext(TemaContexto);
  if (contexto === undefined) {
    throw new Error('useTema precisa ser usado dentro de um TemaProvedor');
  }
  return contexto;
}
