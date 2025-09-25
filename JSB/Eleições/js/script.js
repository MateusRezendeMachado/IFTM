let candidatos = [
    { nome: "Jair Messias Bolsonaro", partido: "PL", imagem: "bolsonaro.png" },
    { nome: "Luis Inacio Lula Da Silva", partido: "PT", imagem: "lula.png" },
    { nome: "Ciro Gomes", partido: "PDT", imagem: "ciro.png" },
    { nome: "Simone Tebet", partido: "MDT", imagem: "tebet.png" }
];

document.getElementById("btnGerar").addEventListener("click", function () {
    let indices = [0, 1, 2, 3];

    let nroVotos = Math.round(Math.random() * 100);
    let vetVotos = [nroVotos, 100 - nroVotos];
    let indicesSorteados = [];

    for (let i = 1; i <= 2; i++) {
        let indCand = indices.splice(Math.floor(Math.random() * indices.length), 1)[0];
        indicesSorteados[i - 1] = indCand;

        document.getElementById(`candSorteado${i}`).innerHTML = candidatos[indCand].nome;
        document.getElementById(`partSorteado${i}`).innerHTML = candidatos[indCand].partido;
        document.getElementById(`imgSorteada${i}`).src = `img/${candidatos[indCand].imagem}`;
        document.getElementById(`votos${i}`).innerHTML = vetVotos[i - 1].toFixed(1);
    }

    if (vetVotos[0] === vetVotos[1]) {
        document.getElementById("candVencedor").innerHTML = "Empate!";
        document.getElementById("percVencedor").innerHTML = "";
    } else if (vetVotos[0] > vetVotos[1]) {
        document.getElementById("candVencedor").innerHTML = candidatos[indicesSorteados[0]].nome;
        document.getElementById("percVencedor").innerHTML = ` (${vetVotos[0].toFixed(1)}%)`;
    } else {
        document.getElementById("candVencedor").innerHTML = candidatos[indicesSorteados[1]].nome;
        document.getElementById("percVencedor").innerHTML = ` (${vetVotos[1].toFixed(1)}%)`;
    }
});
