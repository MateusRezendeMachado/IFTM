import { Routes, Route } from 'react-router-dom';

import Header from './componentes/Header/Header';
import Chamada from './componentes/Chamada/Chamada';
import Dores from './componentes/Dores/Dores';
import Solucoes from './componentes/Solucoes/Solucoes';
import Beneficios from './componentes/Beneficios/Beneficios';
import Relatos from './componentes/Relatos/Relatos';
import Faq from './componentes/Faq/Faq';
import Contato from './componentes/Contato/Contato';
import FormularioFiliacao from './componentes/FormularioFiliacao/FormularioFiliacao';
import Footer from './componentes/Footer/Footer';
import Whatsapp from './componentes/tools/Whatsapp';
import BotaoTopo from './componentes/BotaoTopo/BotaoTopo';

import Filiacao from './paginas/Filiacao/Filiacao';

function App() {
  return (
    <>
      <Header />

      <Routes>
        <Route
          path="/"
          element={
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
          }
        />

        <Route path="/filiacao" element={<Filiacao />} />
      </Routes>

      <Footer />
      <BotaoTopo />
      <Whatsapp />
    </>
  );
}

export default App;