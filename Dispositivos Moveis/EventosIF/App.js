import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { TemaProvedor } from './src/contextos/TemaContexto';
import { SessaoProvedor } from './src/contextos/SessaoContexto';
import { EventosProvedor } from './src/contextos/EventosContexto';
import { InscricoesProvedor } from './src/contextos/InscricoesContexto';
import TelaEventos from './src/telas/TelaEventos';
import TelaDetalheEvento from './src/telas/TelaDetalheEvento';
import TelaMinhasInscricoes from './src/telas/TelaMinhasInscricoes';

const Abas = createBottomTabNavigator();
const PilhaEventos = createNativeStackNavigator();

// Correção de bug de navegação: "Detalhe" NÃO pode ser uma aba própria do
// Bottom Tab Navigator. Uma aba é alcançável tocando direto nela, sem
// passar por navigation.navigate('Detalhe', { idEvento }) - nesse caso
// route.params chega undefined e TelaDetalheEvento.js quebra ao tentar
// ler route.params.idEvento. A solução é colocar "Detalhe" dentro de um
// Stack Navigator, acessível só a partir da lista de eventos; ele nunca
// aparece como ícone de aba, então só é alcançado com params já prontos.
function FluxoEventos() {
  return (
    <PilhaEventos.Navigator>
      <PilhaEventos.Screen
        name="ListaEventos"
        component={TelaEventos}
        options={{ title: 'Eventos' }}
      />
      <PilhaEventos.Screen
        name="Detalhe"
        component={TelaDetalheEvento}
        options={{ title: 'Detalhes do evento' }}
      />
    </PilhaEventos.Navigator>
  );
}

// R7: um único AppContexto (usuario + tema + notificacoes + ultimaBusca)
// virou um provedor por preocupação. Quem só usa tema não é mais
// re-renderizado quando "usuario" muda, e vice-versa.
export default function App() {
  return (
    <TemaProvedor>
      <SessaoProvedor>
        <EventosProvedor>
          <InscricoesProvedor>
            <NavigationContainer>
              <Abas.Navigator>
                <Abas.Screen
                  name="Eventos"
                  component={FluxoEventos}
                  options={{ headerShown: false }}
                />
                <Abas.Screen name="Inscricoes" component={TelaMinhasInscricoes} />
              </Abas.Navigator>
            </NavigationContainer>
          </InscricoesProvedor>
        </EventosProvedor>
      </SessaoProvedor>
    </TemaProvedor>
  );
}
