package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Pruebas del modelo {@link Calculadora}.
 * <p>
 * Comprueba las cuatro operaciones básicas y el caso de la división
 * entre cero.
 * </p>
 *
 * @author Eva Gallardo Romero
 */
class CalculadoraTest {

    /** Calculadora que se usa en las pruebas. */
    private Calculadora calculadora = new Calculadora();

    /** Comprueba que {@code 5 + 3} da {@code 8}. */
    @Test
    void testSumar() {
        assertEquals(8, calculadora.sumar(5, 3));
    }

    /** Comprueba que {@code 5 - 3} da {@code 2}. */
    @Test
    void testRestar() {
        assertEquals(2, calculadora.restar(5, 3));
    }

    /** Comprueba que {@code 5 * 3} da {@code 15}. */
    @Test
    void testMultiplicar() {
        assertEquals(15, calculadora.multiplicar(5, 3));
    }

    /** Comprueba que {@code 5 / 2} da {@code 2.5}. */
    @Test
    void testDividir() {
        assertEquals(2.5, calculadora.dividir(5, 2));
    }

    /** Comprueba que dividir entre 0 lanza una {@link ArithmeticException}. */
    @Test
    void testDividirEntreCeroDaError() {
        assertThrows(ArithmeticException.class, () -> calculadora.dividir(5, 0));
    }
}