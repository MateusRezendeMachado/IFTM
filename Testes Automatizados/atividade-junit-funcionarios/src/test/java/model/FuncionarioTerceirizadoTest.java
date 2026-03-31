package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTerceirizadoTest {

    @Test
    void deveLancarExcecaoQuandoDespesaForMaiorQue1000() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.setDespesaAdicional(1500);
        });

        assertEquals("Despesa acima do limite.", exception.getMessage());
    }

    @Test
    void deveCalcularPagamentoComBonusCorretamente() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        f.setHorasTrabalhadas(100);
        f.setValorHora(20);
        f.setDespesaAdicional(100);

        double pagamento = f.calcularPagamento();

        // 100*20 = 2000 + 110 = 2110
        assertEquals(2110, pagamento);
    }

    @Test
    void deveLancarErroQuandoPagamentoUltrapassarTeto() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        f.setHorasTrabalhadas(160);
        f.setValorHora(60); // já alto
        f.setDespesaAdicional(1000);

        Exception exception = assertThrows(IllegalArgumentException.class, f::calcularPagamento);

        assertEquals("Pagamento excede o teto.", exception.getMessage());
    }

    @Test
    void deveAceitarDespesaNoLimiteMaximo() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        assertDoesNotThrow(() -> {
            f.setDespesaAdicional(1000);
        });
    }
}