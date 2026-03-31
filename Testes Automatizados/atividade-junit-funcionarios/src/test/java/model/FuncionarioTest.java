package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    void deveLancarExcecaoQuandoHorasForemMenoresQue20() {
        Funcionario f = new Funcionario();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.setHorasTrabalhadas(10);
        });

        assertEquals("Horas inválidas.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoHorasForemMaioresQue160() {
        Funcionario f = new Funcionario();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.setHorasTrabalhadas(200);
        });

        assertEquals("Horas inválidas.", exception.getMessage());
    }

    @Test
    void deveAceitarHorasValidas() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            f.setHorasTrabalhadas(100);
        });
    }

    @Test
    void deveLancarExcecaoQuandoValorHoraForMenorQueMinimo() {
        Funcionario f = new Funcionario();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.setValorHora(10);
        });

        assertEquals("Valor hora inválido.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoValorHoraForMaiorQueMaximo() {
        Funcionario f = new Funcionario();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.setValorHora(200);
        });

        assertEquals("Valor hora inválido.", exception.getMessage());
    }

    @Test
    void deveAceitarValorHoraValido() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            f.setValorHora(50);
        });
    }

    @Test
    void deveCalcularPagamentoCorretamente() {
        Funcionario f = new Funcionario();

        f.setHorasTrabalhadas(100);
        f.setValorHora(20);

        double pagamento = f.calcularPagamento();

        assertEquals(2000, pagamento);
    }

    @Test
    void deveLancarErroQuandoPagamentoForMenorQueSalarioMinimo() {
        Funcionario f = new Funcionario();

        f.setHorasTrabalhadas(20);
        f.setValorHora(15.18);

        Exception exception = assertThrows(IllegalArgumentException.class, f::calcularPagamento);

        assertEquals("Pagamento fora dos limites.", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoPagamentoForMaiorQueTeto() {
        Funcionario f = new Funcionario();

        f.setHorasTrabalhadas(160);
        f.setValorHora(100);

        Exception exception = assertThrows(IllegalArgumentException.class, f::calcularPagamento);

        assertEquals("Pagamento fora dos limites.", exception.getMessage());
    }

    @Test
    void deveAceitarHorasNoLimiteMinimo() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            f.setHorasTrabalhadas(20);
        });
    }

    @Test
    void deveAceitarHorasNoLimiteMaximo() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            f.setHorasTrabalhadas(160);
        });
    }

    @Test
    void deveAceitarValorHoraNoLimiteMinimo() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            f.setValorHora(15.18);
        });
    }

    @Test
    void deveAceitarValorHoraNoLimiteMaximo() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            f.setValorHora(151.80);
        });
    }
}