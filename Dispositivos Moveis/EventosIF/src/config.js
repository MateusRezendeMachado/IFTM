// Endereço da API mock (json-server) usada nesta etapa do trabalho.
//
// Antes de rodar `npm start`, rode em OUTRO terminal:
//   npm run mock-api
// Isso sobe um servidor HTTP de verdade em http://localhost:3001,
// servindo o conteúdo de db.json em GET /eventos. O app continua
// fazendo fetch() de verdade — só o endereço mudou de uma API
// externa (fora do ar) para uma API local controlada pela equipe.
//
// Troque HOST conforme onde o app está rodando:
//   - Web (`npm run web`) ou simulador iOS -> 'localhost' funciona.
//   - Emulador Android (AVD) -> use '10.0.2.2' (endereço especial que
//     o emulador usa para enxergar o "localhost" da máquina host).
//   - Celular físico com Expo Go -> nem 'localhost' nem '10.0.2.2'
//     funcionam, porque o celular é outra máquina na rede. Troque HOST
//     pelo IP da sua máquina na rede Wi-Fi (ex.: '192.168.0.15'), que
//     aparece no terminal quando o Metro Bundler sobe.
const HOST = 'localhost';
const PORTA = 3001;

export const API_URL = `http://${HOST}:${PORTA}`;
