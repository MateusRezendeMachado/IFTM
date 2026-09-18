import { memo } from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

// Envolvido em memo (melhoria extra, não pedida explicitamente pela R6, mas
// documentada no relatório): evita que TODOS os cartões re-renderizem sempre
// que o pai (TelaEventos) re-renderiza por um motivo que não muda as props
// deste cartão específico - ajuda também no C6 (ver R7 e Etapa 4).
function CartaoEvento({ evento, jaInscrito, aoInscrever, aoAbrir }) {
  console.log('[render] CartaoEvento', evento.id);
  return (
    <View style={styles.cartao}>
      <Text style={styles.titulo} onPress={aoAbrir}>{evento.titulo}</Text>
      <Text style={styles.local}>{evento.local} — {evento.data}</Text>
      <Button
        title={jaInscrito ? 'Inscrito' : 'Inscrever'}
        onPress={aoInscrever}
        disabled={jaInscrito}
      />
    </View>
  );
}

export default memo(CartaoEvento);

const styles = StyleSheet.create({
  cartao: { borderWidth: 1, borderColor: '#E0E0E0', borderRadius: 10,
    padding: 12, marginBottom: 10, gap: 4 },
  titulo: { fontSize: 17, fontWeight: 'bold' },
  local: { fontSize: 13, color: '#666666' },
});
