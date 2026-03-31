package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    void deveLancarExcecaoQuandoHorasForemMenoresQue20() {
        Funcionario f = new Funcionario();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            int horasTrabalhadas = 10;
            f.setHorasTrabalhadas(horasTrabalhadas);
        });

        assertEquals("Horas inválidas.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoHorasForemMaioresQue160() {
        Funcionario f = new Funcionario();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            int horasTrabalhadas = 200;
            f.setHorasTrabalhadas(horasTrabalhadas);
        });

        assertEquals("Horas inválidas.", exception.getMessage());
    }

    @Test
    void deveAceitarHorasValidas() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            int horasTrabalhadas = 100;
            f.setHorasTrabalhadas(horasTrabalhadas);
        });
    }

    @Test
    void deveLancarExcecaoQuandoValorHoraForMenorQueMinimo() {
        Funcionario f = new Funcionario();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            double valorHora = 10;
            f.setValorHora(valorHora);
        });

        assertEquals("Valor hora inválido.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoValorHoraForMaiorQueMaximo() {
        Funcionario f = new Funcionario();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            double valorHora = 200;
            f.setValorHora(valorHora);
        });

        assertEquals("Valor hora inválido.", exception.getMessage());
    }

    @Test
    void deveAceitarValorHoraValido() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            double valorHora = 50;
            f.setValorHora(valorHora);
        });
    }

    @Test
    void deveCalcularPagamentoCorretamente() {
        Funcionario f = new Funcionario();

        int horasTrabalhadas = 100;
        double valorHora = 20;

        f.setHorasTrabalhadas(horasTrabalhadas);
        f.setValorHora(valorHora);

        double pagamento = f.calcularPagamento();

        assertEquals(2000, pagamento);
    }

    @Test
    void deveLancarErroQuandoPagamentoForMenorQueSalarioMinimo() {
        Funcionario f = new Funcionario();

        int horasTrabalhadas = 20;
        f.setHorasTrabalhadas(horasTrabalhadas);
        double valorHora = 15.18;
        f.setValorHora(valorHora);

        Exception exception = assertThrows(IllegalArgumentException.class, f::calcularPagamento);

        assertEquals("Pagamento fora dos limites.", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoPagamentoForMaiorQueTeto() {
        Funcionario f = new Funcionario();

        int horasTrabalhadas = 160;
        f.setHorasTrabalhadas(horasTrabalhadas);
        double valorHora = 100;
        f.setValorHora(valorHora);

        Exception exception = assertThrows(IllegalArgumentException.class, f::calcularPagamento);

        assertEquals("Pagamento fora dos limites.", exception.getMessage());
    }

    @Test
    void deveAceitarHorasNoLimiteMinimo() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            int horasTrabalhadas = 20;
            f.setHorasTrabalhadas(horasTrabalhadas);
        });
    }

    @Test
    void deveAceitarHorasNoLimiteMaximo() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            int horasTrabalhadas = 160;
            f.setHorasTrabalhadas(horasTrabalhadas);
        });
    }

    @Test
    void deveAceitarValorHoraNoLimiteMinimo() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            double valorHora = 15.18;
            f.setValorHora(valorHora);
        });
    }

    @Test
    void deveAceitarValorHoraNoLimiteMaximo() {
        Funcionario f = new Funcionario();

        assertDoesNotThrow(() -> {
            double valorHora = 151.80;
            f.setValorHora(valorHora);
        });
    }
}