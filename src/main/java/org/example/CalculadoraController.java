package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * CONTROLADOR de la calculadora.
 * <p>
 * Recibe los clics de los botones definidos en el FXML, llama al modelo
 * ({@link Calculadora}) para hacer las cuentas y muestra el resultado
 * en la pantalla.
 * </p>
 *
 * @author Eva Gallardo Romero
 */
public class CalculadoraController {

    /** Etiqueta de la pantalla (fx:id="pantalla" en el FXML). */
    @FXML
    private Label pantalla;

    /** El modelo, que hace las operaciones. */
    private Calculadora calculadora = new Calculadora();

    /** Primer número de la operación (el escrito antes del operador). */
    private double primerNumero = 0;

    /** Operación elegida: "+", "-", "*", "/" o "" si no hay ninguna. */
    private String operacion = "";

    /** Es true cuando el siguiente número que se pulse debe empezar de cero. */
    private boolean numeroNuevo = true;

    /**
     * Se ejecuta al pulsar un botón de número (0-9).
     * Añade el dígito a la pantalla. Como mucho se escriben 10 caracteres
     * para que el número quepa en la pantalla.
     *
     * @param evento evento del clic, de él sacamos qué botón se pulsó
     */
    @FXML
    private void pulsarNumero(ActionEvent evento) {
        Button boton = (Button) evento.getSource();
        String digito = boton.getText();

        if (numeroNuevo || pantalla.getText().equals("0")) {
            pantalla.setText(digito);
            numeroNuevo = false;
        } else if (pantalla.getText().length() < 10) {
            pantalla.setText(pantalla.getText() + digito);
        }
    }

    /**
     * Se ejecuta al pulsar el botón del punto decimal.
     * Solo añade el punto si el número no tiene ya uno.
     */
    @FXML
    private void pulsarPunto() {
        if (numeroNuevo) {
            pantalla.setText("0.");
            numeroNuevo = false;
        } else if (!pantalla.getText().contains(".")) {
            pantalla.setText(pantalla.getText() + ".");
        }
    }

    /**
     * Se ejecuta al pulsar un botón de operación (+, -, ×, ÷).
     * Si ya había una operación pendiente, primero la calcula,
     * así 2 + 3 + 4 da 9. Después guarda el número y la nueva operación.
     *
     * @param evento evento del clic
     */
    @FXML
    private void pulsarOperacion(ActionEvent evento) {
        if (pantalla.getText().equals("Error")) {
            return;
        }

        // Si hay una operación pendiente y ya se ha escrito el segundo número,
        // se calcula antes de guardar la nueva operación
        if (!operacion.equals("") && !numeroNuevo) {
            pulsarIgual();
            if (pantalla.getText().equals("Error")) {
                return;
            }
        }

        Node boton = (Node) evento.getSource();
        operacion = (String) boton.getUserData();
        primerNumero = Double.parseDouble(pantalla.getText());
        numeroNuevo = true;
    }

    /**
     * Se ejecuta al pulsar el botón "=".
     * Calcula el resultado y lo muestra. Si se divide entre cero,
     * la pantalla muestra "Error".
     */
    @FXML
    private void pulsarIgual() {
        if (operacion.equals("") || pantalla.getText().equals("Error")) {
            return;
        }

        double segundoNumero = Double.parseDouble(pantalla.getText());
        double resultado = 0;

        try {
            switch (operacion) {
                case "+":
                    resultado = calculadora.sumar(primerNumero, segundoNumero);
                    break;
                case "-":
                    resultado = calculadora.restar(primerNumero, segundoNumero);
                    break;
                case "*":
                    resultado = calculadora.multiplicar(primerNumero, segundoNumero);
                    break;
                case "/":
                    resultado = calculadora.dividir(primerNumero, segundoNumero);
                    break;
            }
            pantalla.setText(formatear(resultado));
        } catch (ArithmeticException e) {
            pantalla.setText("Error");
        }

        operacion = "";
        numeroNuevo = true;
    }

    /**
     * Se ejecuta al pulsar el botón "C".
     * Deja la calculadora como al principio.
     */
    @FXML
    private void pulsarBorrar() {
        pantalla.setText("0");
        primerNumero = 0;
        operacion = "";
        numeroNuevo = true;
    }

    /**
     * Convierte el resultado en el texto que se muestra en la pantalla.
     * Si es entero (8.0) se muestra sin decimales (8). Si no, se redondea
     * a 8 decimales para que no salgan números como 0.30000000000000004.
     *
     * @param resultado número que se va a mostrar
     * @return el texto para la pantalla
     */
    private String formatear(double resultado) {
        if (resultado == (long) resultado) {
            return String.valueOf((long) resultado);
        }

        resultado = Math.round(resultado * 100000000) / 100000000.0;
        return String.valueOf(resultado);
    }
}