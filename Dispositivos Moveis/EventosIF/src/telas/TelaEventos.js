import { useState } from 'react';
import {
  View, Text, TextInput, FlatList, ActivityIndicator, StyleSheet,
} from 'react-native';
import { useTema } from '../contextos/TemaContexto';
import { useEventos } from '../contextos/EventosContexto';
import { useInscricoes } from '../contextos/InscricoesContexto';
import CartaoEvento from '../componentes/CartaoEvento';

export default function TelaEventos({ navigation }) {
  // R7: esta tela só precisa do tema - agora ela só se inscreve (via
  // Context) para o TemaContexto, então uma mudança em "usuario" (que
  // antes vinha junto no mesmo AppContexto) não a re-renderiza mais.
  const { temaEscuro } = useTema();
  const { status, eventos, erro } = useEventos();
  const { totalInscricoes, inscrever, estaInscrito } = useInscricoes();
  const [busca, setBusca] = useState('');
  const [ultimaConfirmacaoId, setUltimaConfirmacaoId] = useState(null);

  const eventosFiltrados = eventos.filter((ev) =>
    ev.titulo.toLowerCase().includes(busca.toLowerCase())
  );
  const eventoConfirmado = eventos.find((ev) => ev.id === ultimaConfirmacaoId);

  function aoInscrever(evento) {
    inscrever(evento.id);
    setUltimaConfirmacaoId(evento.id);
  }

  console.log('[render] TelaEventos');

  return (
    <View style={[styles.container,
      { backgroundColor: temaEscuro ? '#121212' : '#FFFFFF' }]}>
      <Text style={styles.contador}>Inscrições: {totalInscricoes}</Text>
      <TextInput
        style={styles.campo}
        value={busca}
        onChangeText={setBusca}
        placeholder="Buscar evento"
      />
      {status === 'carregando' && <ActivityIndicator size="large" />}
      {status === 'falha' && <Text style={styles.erro}>Falha: {erro}</Text>}
      {eventoConfirmado && (
        <Text style={styles.aviso}>
          Inscrição confirmada em {eventoConfirmado.titulo}
        </Text>
      )}
      {status === 'sucesso' && (
        <FlatList
          data={eventosFiltrados}
          keyExtractor={(itemLista) => String(itemLista.id)}
          renderItem={({ item }) => (
            <CartaoEvento
              evento={item}
              jaInscrito={estaInscrito(item.id)}
              aoInscrever={() => aoInscrever(item)}
              aoAbrir={() =>
                navigation.navigate('Detalhe', { idEvento: item.id })}
            />
          )}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16 },
  contador: { fontSize: 18, fontWeight: 'bold', marginBottom: 8 },
  campo: { borderWidth: 1, borderColor: '#CCCCCC', borderRadius: 8,
    padding: 10, marginBottom: 12 },
  erro: { color: '#B00020', marginBottom: 8 },
  aviso: { color: '#2E7D32', marginBottom: 8 },
});
