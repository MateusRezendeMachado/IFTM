package org.iftm.atividadea2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

  
    @Test
    void testeConstrutorVazio() {

        // Arrange
        Calculadora calc = new Calculadora();

        // Act
        int resultado = calc.getMemoria();

        // Assert
        assertEquals(0, resultado);
    }

    
    @Test
    void testeConstrutorCom3() {

        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        int resultado = calc.getMemoria();

        // Assert
        assertEquals(3, resultado);
    }

    
    @Test
    void testeConstrutorComMenos3() {

        // Arrange
        Calculadora calc = new Calculadora(-3);

        // Act
        int resultado = calc.getMemoria();

        // Assert
        assertEquals(-3, resultado);
    }

    
    @Test
    void testeSomarPositivo() {

        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.somar(2);

        // Assert
        assertEquals(5, calc.getMemoria());
    }

 
    @Test
    void testeSomarNegativo() {

        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.somar(-2);

        // Assert
        assertEquals(1, calc.getMemoria());
    }

 
    @Test
    void testeSubtrairPositivo() {

        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.subtrair(2);

        // Assert
        assertEquals(1, calc.getMemoria());
    }


    @Test
    void testeSubtrairNegativo() {

        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.subtrair(-2);

        // Assert
        assertEquals(5, calc.getMemoria());
    }

    @Test
    void testeMultiplicarPositivo() {

        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.multiplicar(2);

        // Assert
        assertEquals(6, calc.getMemoria());
    }

  
    @Test
    void testeMultiplicarNegativo() {

        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.multiplicar(-2);

        // Assert
        assertEquals(-6, calc.getMemoria());
    }

 
    @Test
    void testeDividirPositivo() throws Exception {

        // Arrange
        Calculadora calc = new Calculadora(6);

        // Act
        calc.dividir(2);

        // Assert
        assertEquals(3, calc.getMemoria());
    }

  
    @Test
    void testeDividirNegativo() throws Exception {

        // Arrange
        Calculadora calc = new Calculadora(6);

        // Act
        calc.dividir(-2);

        // Assert
        assertEquals(-3, calc.getMemoria());
    }

  
    @Test
    void testeDivisaoPorZero() {

        // Arrange
        Calculadora calc = new Calculadora(6);

        // Act + Assert
        assertThrows(Exception.class, () -> {
            calc.dividir(0);
        });
    }

    @Test
    void testeExponenciar1() throws Exception {

        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.exponenciar(1);

        // Assert
        assertEquals(3, calc.getMemoria());
    }

 
    @Test
    void testeExponenciar10() throws Exception {

        // Arrange
        Calculadora calc = new Calculadora(2);

        // Act
        calc.exponenciar(10);

        // Assert
        assertEquals(1024, calc.getMemoria());
    }

   
    @Test
    void testeExpoenteMaiorQue10() {

        // Arrange
        Calculadora calc = new Calculadora(2);

        // Act + Assert
        assertThrows(Exception.class, () -> {
            calc.exponenciar(20);
        });
    }

    @Test
    void testeZerarMemoria() {

        // Arrange
        Calculadora calc = new Calculadora(10);

        // Act
        calc.zerarMemoria();

        // Assert
        assertEquals(0, calc.getMemoria());
    }
}