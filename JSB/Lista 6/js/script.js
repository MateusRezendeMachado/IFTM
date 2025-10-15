/* =======================================================
   ETAPA 1 — CRIAR O VETOR BASE (NÚMEROS DE 1 A 27)
========================================================= */

const vetorBase = Array.from({ length: 27 }, (_, i) => i + 1);
console.log("ETAPA 1 - Vetor base criado:", vetorBase);


/* =======================================================
   ETAPA 2 — GERAR 4 PARES ALEATÓRIOS DE CARTAS
========================================================= */
function gerarPares(base, qtdPares = 4) {
    const disponiveis = [...base];
    const pares = [];

    for (let i = 0; i < qtdPares; i++) {
        const indice = Math.floor(Math.random() * disponiveis.length);
        const carta = disponiveis[indice];
        pares.push(carta, carta); // adiciona o par
        disponiveis.splice(indice, 1); // remove do vetor original
    }

    console.log("ETAPA 2 - Pares gerados:", pares);
    return pares;
}


/* =======================================================
   ETAPA 3 — EMBARALHAR AS CARTAS (ALGORITMO FISHER-YATES)
========================================================= */
function embaralhar(vetor) {
    for (let i = vetor.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [vetor[i], vetor[j]] = [vetor[j], vetor[i]];
    }
    console.log("ETAPA 3 - Vetor embaralhado:", vetor);
    return vetor;
}


/* =======================================================
   ETAPA 4 — EXIBIR AS CARTAS NA PÁGINA
========================================================= */
function mostrarCartas(vetorCartas) {
    const tabuleiro = document.getElementById("tabuleiro");
    tabuleiro.innerHTML = ""; 

    vetorCartas.forEach(numero => {
        const div = document.createElement("div");
        div.classList.add("carta");

        const img = document.createElement("img");

        img.src = `./img/carta${numero}.png`;
        img.alt = `Carta ${numero}`;

        div.appendChild(img);
        tabuleiro.appendChild(div);
    });

    console.log("ETAPA 4 - Cartas exibidas na tela");
}


/* =======================================================
   INÍCIO DO JOGO — CHAMA AS ETAPAS EM ORDEM
========================================================= */
document.getElementById("btnGerar").addEventListener("click", () => {
    const pares = gerarPares(vetorBase, 4);  
    const embaralhadas = embaralhar(pares); 
    mostrarCartas(embaralhadas);           
});
