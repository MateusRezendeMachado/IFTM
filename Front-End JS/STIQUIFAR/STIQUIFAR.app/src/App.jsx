import Header from './componentes/Header/Header';
import Chamada from './componentes/Chamada/Chamada';
import Dores from './componentes/Dores/Dores';
import Solucoes from './componentes/Solucoes/Solucoes';
import Beneficios from './componentes/Beneficios/Beneficios';
import Relatos from './componentes/Relatos/Relatos';
import Faq from './componentes/Faq/Faq';
import FormularioFiliacao from './componentes/FormularioFiliacao/FormularioFiliacao';
import Footer from './componentes/Footer/Footer';
import Whatsapp from './componentes/tools/Whatsapp';
import Contato from './componentes/Contato/Contato';

function App() {
  return (
    <>
      <Header />

      <main>
        <Chamada />
        <Dores />
        <Solucoes />
        <Beneficios />
        <Relatos />
        <Faq />
        <FormularioFiliacao />
        <Contato />
      </main>

      <Footer />
      <Whatsapp />
    </>
  );
}

export default App;