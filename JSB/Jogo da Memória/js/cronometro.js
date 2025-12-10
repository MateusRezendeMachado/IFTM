export function iniciarCronometro(nivel, minutosInterface, segundosInterface, onTempoEsgotado) {
    let minutos, segundos;
    let idCronometro;

    // Define o tempo inicial conforme a dificuldade
    switch (nivel) {
        case "0": minutos = 5; segundos = 0; break;      // Fácil
        case "1": minutos = 5; segundos = 30; break;     // Médio
        case "2": minutos = 5; segundos = 0; break;      // Difícil
       
    }

    atualizarInterface();

    // Começa a contagem regressiva
    idCronometro = setInterval(() => {
        if (minutos === 0 && segundos === 0) {
            clearInterval(idCronometro);
            if (typeof onTempoEsgotado === "function") onTempoEsgotado();
            return;
        }

        if (segundos === 0) {
            minutos--;
            segundos = 59;
        } else {
            segundos--;
        }

        atualizarInterface();
    }, 1000);

    function atualizarInterface() {
        minutosInterface.textContent = minutos.toString().padStart(2, "0");
        segundosInterface.textContent = segundos.toString().padStart(2, "0");
    }

    return {
        parar: () => clearInterval(idCronometro)
    };
}
