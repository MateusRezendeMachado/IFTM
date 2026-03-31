package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTerceirizadoTest {

    @Test
    void deveLancarExcecaoQuandoDespesaForMaiorQue1000() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            int despesaAdicional = 1500;
            f.setDespesaAdicional(despesaAdicional);
        });

        assertEquals("Despesa acima do limite.", exception.getMessage());
    }

    @Test
    void deveCalcularPagamentoComBonusCorretamente() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        int horasTrabalhadas = 100;
        double valorHora = 20;
        int despesaAdicional = 100;

        f.setHorasTrabalhadas(horasTrabalhadas);
        f.setValorHora(valorHora);
        f.setDespesaAdicional(despesaAdicional);

        double pagamento = f.calcularPagamento();

        // 100*20 = 2000 + 110 = 2110
        assertEquals(2110, pagamento);
    }

    @Test
    void deveLancarErroQuandoPagamentoUltrapassarTeto() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        int horasTrabalhadas = 160;
        f.setHorasTrabalhadas(horasTrabalhadas);
        double valorHora = 60; // já alto
        f.setValorHora(valorHora);
        int despesaAdicional = 1000;
        f.setDespesaAdicional(despesaAdicional);

        Exception exception = assertThrows(IllegalArgumentException.class, f::calcularPagamento);

        assertEquals("Pagamento excede o teto.", exception.getMessage());
    }

    @Test
    void deveAceitarDespesaNoLimiteMaximo() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        assertDoesNotThrow(() -> {
            int despesaAdicional = 1000;
            f.setDespesaAdicional(despesaAdicional);
        });
    }
}