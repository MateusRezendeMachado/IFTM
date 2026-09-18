import { View, Text, StyleSheet } from 'react-native';
import { useEventos } from '../contextos/EventosContexto';

export default function TelaDetalheEvento({ route }) {
  // R3: route.params agora carrega só o id, nunca o objeto de domínio
  // inteiro. O evento é sempre buscado na fonte de verdade (EventosContexto),
  // então, se a lista de eventos for recarregada e "vagas" mudar, esta tela
  // já mostra o valor atualizado (resolve o C3).
  //
  // Validação defensiva: mesmo com "Detalhe" agora dentro de um Stack
  // Navigator (só alcançável a partir da lista, com params preenchidos),
  // continuamos tratando route.params como opcional. Isso cobre casos como
  // deep link mal formado, um botão "voltar" custom, ou uma futura tela
  // que reutilize esta rota sem passar idEvento - nenhum desses deveria
  // derrubar o app com "Cannot read property 'idEvento' of undefined".
  const idEvento = route?.params?.idEvento;
  const { eventos } = useEventos();
  const evento = idEvento != null ? eventos.find((ev) => ev.id === idEvento) : undefined;

  if (!evento) {
    return (
      <View style={styles.container}>
        <Text style={styles.texto}>
          {idEvento == null
            ? 'Nenhum evento selecionado. Volte para a lista e toque em um evento.'
            : 'Este evento não está mais disponível.'}
        </Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>{evento.titulo}</Text>
      <Text style={styles.texto}>{evento.descricao}</Text>
      <Text style={styles.texto}>Vagas restantes: {evento.vagas}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16, gap: 8 },
  titulo: { fontSize: 22, fontWeight: 'bold' },
  texto: { fontSize: 16 },
});
