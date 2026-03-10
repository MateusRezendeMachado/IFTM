# Relatório de Testes – Classe Calculadora

Este documento descreve os testes realizados na classe **Calculadora** e os erros encontrados na versão original do código.

Os testes foram implementados utilizando **JUnit**, permitindo identificar falhas de implementação em diferentes métodos da classe.

---

## Teste 1

**Método testado:**  
Construtor `Calculadora()`

**Cenário testado:**  
Verificar o valor inicial da memória ao criar uma nova calculadora utilizando o construtor vazio.

**Erro encontrado:**  
No código incorreto, a memória era inicializada com o valor **1**, quando o comportamento esperado seria iniciar com **0**.  
Isso poderia gerar resultados incorretos nas operações seguintes.

---

## Teste 2

**Método testado:**  
`subtrair(int valor)`

**Cenário testado:**  
Subtrair um valor da memória da calculadora.

**Erro encontrado:**  
O método não realizava a operação de subtração corretamente. A implementação apenas atribuía o valor da memória a ela mesma:


this.memoria = this.memoria;


Dessa forma, a memória não era alterada após a execução do método.

---

## Teste 3

**Método testado:**  
`multiplicar(int valor)`

**Cenário testado:**  
Multiplicar o valor armazenado na memória por outro valor.

**Erro encontrado:**  
O método realizava uma **divisão ao invés de uma multiplicação**:


this.memoria = this.memoria / valor;


Isso fazia com que o resultado da operação fosse diferente do esperado.

---

## Teste 4

**Método testado:**  
`dividir(int valor)`

**Cenário testado:**  
Dividir o valor armazenado na memória por outro número.

**Erro encontrado:**  
O método lançava exceção quando o valor era **menor ou igual a 1**:


if (valor <= 1)


No entanto, apenas a divisão por **zero** deveria gerar exceção. Isso impedia divisões válidas, como dividir por **1**.

---

## Teste 5

**Método testado:**  
`exponenciar(int valor)`

**Cenário testado:**  
Elevar o valor armazenado na memória a uma potência informada.

**Erro encontrado:**  
O método ignorava o valor passado como parâmetro e executava o laço **sempre 9 vezes**:


for(int i = 1; i < 10; i++)


Além disso, a multiplicação era feita da memória por ela mesma repetidamente, gerando resultados incorretos.

---

## Conclusão

A utilização de **testes unitários com JUnit** permitiu identificar erros importantes na implementação da classe `Calculadora`.

Após a análise dos testes, os métodos foram corrigidos para garantir o funcionamento correto das operações matemáticas.  

O uso de testes automatizados aumenta a confiabilidade do código e facilita a identificação de pr
