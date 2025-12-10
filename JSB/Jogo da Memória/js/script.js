import { iniciarCronometro } from "./cronometro.js";

window.addEventListener("DOMContentLoaded", function () {

    // =======================
    // ÁUDIOS
    // =======================
    const somVirar = document.getElementById("somVirar");
    const audioAmbiente = document.getElementById("audioAmbiente");
    const somVitoria = document.getElementById("somVitoria");

    const vetorBase = Array.from({ length: 27 }, (_, i) => i + 1);
    let cronometroAtivo = null;

    let primeiraCarta = null;
    let segundaCarta = null;
    let podeClicar = true;

    let pontos = 0;
    let totalPares = 0;

    const pontosSpan = document.getElementById("pontos");

    function atualizarPontos() {
        pontosSpan.textContent = pontos;
    }

    // =======================
    // VERIFICAR VITÓRIA
    // =======================
    function verificarVitoria() {
        if (pontos === totalPares) {

            // PAUSAR SOM AMBIENTE
            audioAmbiente.pause();
            audioAmbiente.currentTime = 0;

            // TOCAR SOM DE VITÓRIA
            somVitoria.currentTime = 0;
            somVitoria.play();

            const minRest = Number(document.getElementById("minutos").textContent);
            const segRest = Number(document.getElementById("segundos").textContent);

            let tempoInicialSeg = 0;
            switch (document.getElementById("nivelDif").value) {
                case "0": tempoInicialSeg = 5 * 60; break;
                case "1": tempoInicialSeg = 5 * 60 + 30; break;
                case "2": tempoInicialSeg = 5 * 60; break;
            }

            const tempoRestanteSeg = minRest * 60 + segRest;
            const tempoGasto = tempoInicialSeg - tempoRestanteSeg;

            const minutosGastos = Math.floor(tempoGasto / 60);
            const segundosGastos = tempoGasto % 60;

            if (cronometroAtivo) cronometroAtivo.parar();

            setTimeout(() => {
                alert(
                    `🎉 Parabéns!\nVocê fez ${totalPares} pares!\n` +
                    `Tempo: ${minutosGastos}min ${segundosGastos}s.`
                );
            }, 300);
        }
    }

    // =======================
    // VIRAR CARTA
    // =======================
    function revelarCarta(event) {
        if (!podeClicar) return;

        const img = event.target;

        if (img.classList.contains("revelada")) return;
        if (img.classList.contains("virada-temp")) return;

        // som de virar
        somVirar.currentTime = 0;
        somVirar.play();

        img.src = img.dataset.urlCarta;
        img.classList.add("virada-temp");

        if (primeiraCarta === null) {
            primeiraCarta = img;
        } else if (segundaCarta === null) {
            segundaCarta = img;
            podeClicar = false;

            setTimeout(() => {
                verificarPar();
            }, 700);
        }
    }

    function verificarPar() {
        if (!primeiraCarta || !segundaCarta) return;

        const c1 = primeiraCarta.dataset.urlCarta;
        const c2 = segundaCarta.dataset.urlCarta;

        if (c1 === c2) {
            primeiraCarta.classList.remove("virada-temp");
            segundaCarta.classList.remove("virada-temp");

            primeiraCarta.classList.add("revelada");
            segundaCarta.classList.add("revelada");

            pontos++;
            atualizarPontos();
            verificarVitoria();
        } else {
            primeiraCarta.src = "img/verso.png";
            segundaCarta.src = "img/verso.png";

            primeiraCarta.classList.remove("virada-temp");
            segundaCarta.classList.remove("virada-temp");
        }

        primeiraCarta = null;
        segundaCarta = null;
        podeClicar = true;
    }

    // =============================
    // GERAR PARES / EMBARALHAR
    // =============================
    function gerarPares(base, qtdPares = 4) {
        const disponiveis = [...base];
        const pares = [];

        for (let i = 0; i < qtdPares; i++) {
            const indice = Math.floor(Math.random() * disponiveis.length);
            const carta = disponiveis[indice];
            pares.push(carta, carta);
            disponiveis.splice(indice, 1);
        }
        return pares;
    }

    function embaralhar(vetor) {
        for (let i = vetor.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [vetor[i], vetor[j]] = [vetor[j], vetor[i]];
        }
        return vetor;
    }

    function mostrarCartas(vetorCartas) {
        const tabuleiro = document.getElementById("tabuleiro");
        tabuleiro.innerHTML = "";

        vetorCartas.forEach(numero => {
            const img = document.createElement("img");
            img.src = "img/verso.png";
            img.dataset.urlCarta = `img/carta${numero}.png`;
            img.classList.add("carta-img");
            img.addEventListener("click", revelarCarta);
            tabuleiro.appendChild(img);
        });
    }

    function limparTabuleiro() {
        document.getElementById("tabuleiro").innerHTML = "";
    }

    // =======================
    // INICIAR JOGO
    // =======================
    document.getElementById("btnIniciar").addEventListener("click", () => {

        // INICIAR SOM AMBIENTE APENAS NO INÍCIO DO JOGO
        audioAmbiente.volume = 0.4;
        audioAmbiente.currentTime = 0;
        audioAmbiente.play();

        const nivel = document.getElementById("nivelDif").value;
        const minutosInterface = document.getElementById("minutos");
        const segundosInterface = document.getElementById("segundos");

        if (nivel === "") {
            alert("Selecione um nível de dificuldade!");
            return;
        }

        switch (nivel) {
            case "0": totalPares = 4; break;
            case "1": totalPares = 8; break;
            case "2": totalPares = 12; break;
        }

        pontos = 0;
        atualizarPontos();

        const pares = gerarPares(vetorBase, totalPares);
        const embaralhadas = embaralhar(pares);
        mostrarCartas(embaralhadas);

        if (cronometroAtivo) cronometroAtivo.parar();

        cronometroAtivo = iniciarCronometro(
            nivel,
            minutosInterface,
            segundosInterface,
            () => {
                alert("⏰ Tempo esgotado!");
                limparTabuleiro();
            }
        );
    });

    document.getElementById("nivelDif").addEventListener("change", limparTabuleiro);
});

// ========== LOGOUT ==========
document.getElementById("logout").addEventListener("click", () => {
    localStorage.removeItem("logado");
    window.location.href = "login.html";
});
