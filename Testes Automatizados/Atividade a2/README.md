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
