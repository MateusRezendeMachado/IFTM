window.addEventListener("DOMContentLoaded", function () {

    sumAges = (vet) => {
        sum = 0;
        for (i = 0; i < vet.length; i++) sum += vet[i];
        return sum;
    }
    vetAges = [10, 21, 31, 40];
    document.write(`<p>Soma das idades = ${sumAges(vetAges)}</p>`);

    const medAges = (vet) => {
        let sum = 0;
        for (i = 0; i < vet.length; i++) sum += vet[i];
        return sum / vet.length;
    }
    document.write(`<p>Média das idades = ${medAges(vetAges)}</p>`);

    const maxAges = (vet) => {
        let max = vet[0];
        for (i = 1; i < vet.length; i++) {
            if (vet[i] > max) {
                max = vet[i];
            }
        }
        return max;
    }
    document.write(`<p>Maior idade = ${maxAges(vetAges)} </p>`);

    const oddAges = (vet) => {
        let odd = [];
        for (i = 0; i < vet.length; i++) {
            if (vet[i] % 2 != 0) {
                odd.push(vet[i]);
            }
        }
        return odd;
    }
    document.write(`<p>Idades ímpares = ${oddAges(vetAges)} </p>`);

    const maiorIdade = (vet) => {
        for (i = 0; i < vet.length; i++) {
            if (vet[i] < 18) {
                return false;
            }
        }
        return true;
    }
    document.write(`<p>Todos são maiores de idade? ${maiorIdade(vetAges)} </p>`);

    const verificaIdades = (vet, valorUser) => {
        valorUser = prompt("Digite a idade: ");
        for (i = 0; i < vet.length; i++) {
            if (vet[i] < valorUser) {
                return false;
            }
        }
        return true;
    }

    document.write(`<p>Todos são maiores que a idade digitada? ${verificaIdades(vetAges)} </p>`);

    const exibeIdades = (vet, valorUser) => { 
        let exibe = []; 
        for (i = 0; i < vet.length; i++) {
            if (vet[i] >= valorUser) {
                exibe.push(vet[i]);
            }
    }
            return exibe;
}
    document.write(`<p>Idades maiores ou iguais a idade digitada: ${exibeIdades(vetAges, 20)}</p>`);
});