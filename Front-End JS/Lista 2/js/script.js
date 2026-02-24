window.addEventListener("DOMContentLoaded", function () {

    const vetAges = [10, 21, 31, 40];

    // letra a
    const sumAges = vet =>
        vet.reduce((total, idade) => total + idade, 0);

    document.write(`<p>Soma das idades = ${sumAges(vetAges)}</p>`);

    // letra b
    const medAges = vet =>
        vet.reduce((total, idade) => total + idade, 0) / vet.length;

    document.write(`<p>Média das idades = ${medAges(vetAges)}</p>`);

    // letra c
    const maxAges = vet =>
        vet.reduce((max, idade) => idade > max ? idade : max);

    document.write(`<p>Maior idade = ${maxAges(vetAges)}</p>`);

    // letra d
    const oddAges = vet =>
        vet.filter(idade => idade % 2 !== 0);

    document.write(`<p>Idades ímpares = ${oddAges(vetAges)}</p>`);

    // letra e
    const maiorIdade = vet =>
        vet.every(idade => idade >= 18);

    document.write(`<p>Todos são maiores de idade? ${maiorIdade(vetAges)}</p>`);

    const valorUser = Number(prompt("Digite a idade: "));

    // letra f
    const verificaIdades = (vet, valor) =>
        vet.every(idade => idade >= valor);

    document.write(`<p>Todos são maiores que a idade digitada? ${verificaIdades(vetAges, valorUser)}</p>`);

    // letra g
    const exibeIdades = (vet, valor) =>
        vet.filter(idade => idade >= valor);

    document.write(`<p>Idades maiores ou iguais a idade digitada: ${exibeIdades(vetAges, valorUser)}</p>`);

    // letra h
    const mediaIdadesFiltradas = (vet, valor) => {
        const filtradas = vet.filter(idade => idade >= valor);
        return filtradas.length > 0
            ? (filtradas.reduce((total, idade) => total + idade, 0) / filtradas.length).toFixed(2)
            : "0.00";
    };

    document.write(`<p>Média das idades maiores ou iguais à digitada: ${mediaIdadesFiltradas(vetAges, valorUser)}</p>`);

});
