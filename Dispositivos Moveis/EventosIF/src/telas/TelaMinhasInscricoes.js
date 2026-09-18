import { View, Text, FlatList, Button, StyleSheet } from 'react-native';
import { useInscricoes } from '../contextos/InscricoesContexto';

export default function TelaMinhasInscricoes() {
  // R6: nada de useState aqui - a lista vem inteira do InscricoesContexto,
  // então inscrever numa aba aparece na outra sem nenhum truque (resolve C1).
  const { inscricoes, cancelar } = useInscricoes();

  console.log('[render] TelaMinhasInscricoes');

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>
        Minhas inscrições ({inscricoes.length})
      </Text>
      <FlatList
        data={inscricoes}
        keyExtractor={(item) => String(item.id)}
        renderItem={({ item }) => (
          <View style={styles.linha}>
            <Text>{item.titulo}</Text>
            <Button title="Cancelar"
              onPress={() => cancelar(item.id)} />
          </View>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16 },
  titulo: { fontSize: 20, fontWeight: 'bold', marginBottom: 12 },
  linha: { flexDirection: 'row', alignItems: 'center',
    justifyContent: 'space-between', paddingVertical: 8 },
});
