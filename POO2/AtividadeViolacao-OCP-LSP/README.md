# Correção dos princípios OCP e LSP

## OCP (Open/Closed Principle)

A violação ocorria na classe `ProcessadorPagamento`, que utilizava `if/else` para decidir qual operação executar. Dessa forma, sempre que um novo tipo de pagamento fosse criado, seria necessário alterar a classe.

Para corrigir o problema, foi criada a interface `EstrategiaPagamento` e implementações específicas para cada operação, como `PagamentoBoleto` e `Saque`. Assim, o `ProcessadorPagamento` trabalha apenas com a abstração e novas operações podem ser adicionadas sem modificar seu código.

## LSP (Liskov Substitution Principle)

A classe `ContaPoupanca` herdava de `ContaCorrente`, mas não suportava o método `pagarBoleto`, lançando uma exceção quando ele era chamado. Isso impedia que uma `ContaPoupanca` pudesse substituir corretamente uma `ContaCorrente`.

A solução foi separar as responsabilidades em duas interfaces: `Conta` e `ContaPagadora`. A `ContaCorrente` implementa `ContaPagadora`, enquanto a `ContaPoupanca` implementa apenas `Conta`. Dessa forma, cada tipo de conta possui apenas os comportamentos que realmente suporta.

## Estrutura

- `Conta`
- `ContaPagadora`
- `ContaCorrente`
- `ContaPoupanca`
- `EstrategiaPagamento`
- `PagamentoBoleto`
- `Saque`
- `ProcessadorPagamento`
- `AtividadeViolacaoOCPLSP`