package org.example;

/**
 * MODELO de la calculadora.
 * <p>
 * Contiene las cuatro operaciones básicas: suma, resta, multiplicación
 * y división. No sabe nada de botones ni de ventanas.
 * </p>
 *
 * @author Eva Gallardo Romero
 */
public class Calculadora {

    /**
     * Suma dos números.
     *
     * @param a primer número
     * @param b segundo número
     * @return el resultado de a + b
     */
    public double sumar(double a, double b) {
        return a + b;
    }

    /**
     * Resta dos números.
     *
     * @param a primer número
     * @param b segundo número
     * @return el resultado de a - b
     */
    public double restar(double a, double b) {
        return a - b;
    }

    /**
     * Multiplica dos números.
     *
     * @param a primer número
     * @param b segundo número
     * @return el resultado de a * b
     */
    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Divide dos números.
     *
     * @param a dividendo (el número que se divide)
     * @param b divisor (el número entre el que se divide)
     * @return el resultado de a / b
     * @throws ArithmeticException si el divisor es 0
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return a / b;
    }
}