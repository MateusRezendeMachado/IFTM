import { createContext, useContext, useMemo, useState } from 'react';

const SessaoContexto = createContext(undefined);

export function SessaoProvedor({ children }) {
  const [usuario, setUsuario] = useState({ nome: 'Visitante', matricula: null });

  const valor = useMemo(() => ({ usuario, setUsuario }), [usuario]);

  return (
    <SessaoContexto.Provider value={valor}>
      {children}
    </SessaoContexto.Provider>
  );
}

export function useSessao() {
  const contexto = useContext(SessaoContexto);
  if (contexto === undefined) {
    throw new Error('useSessao precisa ser usado dentro de um SessaoProvedor');
  }
  return contexto;
}
