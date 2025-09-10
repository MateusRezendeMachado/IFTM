function main() {
    let n1 = parseFloat(prompt("Digite o primeiro número:")); 
    let n2 = parseFloat(prompt("Digite o segundo número:")); 
    let operador = prompt("Selecione o operador: + - * /");

    calculadora(n1, n2, operador); 
}

function calculadora(n1, n2, operador) {
    let resultado;
    switch (operador) {
        case '+':
            resultado = n1 + n2;
            break;
        case '-':
            resultado = n1 - n2;
            break;
        case '*':
            resultado = n1 * n2;
            break;
        case '/':
            resultado = n2 !== 0 ? n1 / n2 : "Erro: divisão por zero";
            break;
        default:
            alert("DIGITE ALGO VÁLIDO, SEU ANIMAL");
            return;
    }
    alert("Resultado: " + resultado);
}

main();     
