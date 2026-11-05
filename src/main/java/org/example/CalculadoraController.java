package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * CONTROLADOR de la calculadora.
 * <p>
 * Recibe los clics de los botones definidos en el FXML y las teclas
 * pulsadas, llama al modelo ({@link Calculadora}) para hacer las cuentas
 * y muestra el resultado en la pantalla.
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

    /** Combinación de teclas del botón "C": Ctrl + C. */
    private final KeyCombination combinacionBorrar =
            new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);

    /**
     * Crea el controlador. Lo llama JavaFX al cargar el FXML.
     */
    public CalculadoraController() {
    }

    /**
     * Se ejecuta al pulsar un botón de número (0-9).
     * Saca el dígito del texto del botón y lo escribe en la pantalla.
     *
     * @param evento evento del clic, de él sacamos qué botón se pulsó
     */
    @FXML
    private void pulsarNumero(ActionEvent evento) {
        Button boton = (Button) evento.getSource();
        escribirDigito(boton.getText());
    }

    /**
     * Se ejecuta al pulsar un botón de operación (+, -, ×, ÷).
     * Saca el símbolo del atributo userData del botón y elige esa operación.
     *
     * @param evento evento del clic
     */
    @FXML
    private void pulsarOperacion(ActionEvent evento) {
        Node boton = (Node) evento.getSource();
        elegirOperacion((String) boton.getUserData());
    }

    /**
     * Se ejecuta al pulsar el botón del punto decimal (o las teclas . y ,).
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
     * Se ejecuta al pulsar el botón "=" (o las teclas Enter e =).
     * Calcula el resultado y lo muestra. Si se divide entre cero,
     * la pantalla muestra "Error". También lo llama {@link #elegirOperacion}
     * para resolver las operaciones encadenadas.
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
     * Se ejecuta al pulsar el botón "C" (o la combinación Ctrl + C).
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
     * Se ejecuta cada vez que se pulsa una tecla en la ventana
     * (lo registra {@link App} con setOnKeyPressed).
     * <p>
     * Funcionan los números del teclado normal y del numérico, las teclas
     * de operación (+, -, *, /), el punto o la coma para los decimales,
     * Enter o = para el resultado y Ctrl + C para borrar.
     * </p>
     *
     * @param evento evento del teclado, de él sacamos qué tecla se pulsó
     */
    public void teclaPulsada(KeyEvent evento) {
        KeyCode tecla = evento.getCode();

        if (combinacionBorrar.match(evento)) {
            pulsarBorrar();
            return;
        }

        if (evento.isShiftDown()) {
            if (tecla == KeyCode.DIGIT7) {
                elegirOperacion("/");
            } else if (tecla == KeyCode.PLUS) {
                elegirOperacion("*");
            } else if (tecla == KeyCode.DIGIT0) {
                pulsarIgual();
            }
            return;
        }

        switch (tecla) {
            case DIGIT0: case NUMPAD0: escribirDigito("0"); break;
            case DIGIT1: case NUMPAD1: escribirDigito("1"); break;
            case DIGIT2: case NUMPAD2: escribirDigito("2"); break;
            case DIGIT3: case NUMPAD3: escribirDigito("3"); break;
            case DIGIT4: case NUMPAD4: escribirDigito("4"); break;
            case DIGIT5: case NUMPAD5: escribirDigito("5"); break;
            case DIGIT6: case NUMPAD6: escribirDigito("6"); break;
            case DIGIT7: case NUMPAD7: escribirDigito("7"); break;
            case DIGIT8: case NUMPAD8: escribirDigito("8"); break;
            case DIGIT9: case NUMPAD9: escribirDigito("9"); break;

            case ADD: case PLUS:       elegirOperacion("+"); break;
            case SUBTRACT: case MINUS: elegirOperacion("-"); break;
            case MULTIPLY:             elegirOperacion("*"); break;
            case DIVIDE:               elegirOperacion("/"); break;

            case DECIMAL: case PERIOD: case COMMA: pulsarPunto(); break;

            case ENTER: case EQUALS:   pulsarIgual(); break;

            default: break;
        }
    }

    /**
     * Añade un dígito a la pantalla. Lo usan los botones y el teclado.
     * Como mucho se escriben 10 caracteres para que el número quepa
     * en la pantalla.
     *
     * @param digito dígito que se va a escribir ("0" a "9")
     */
    private void escribirDigito(String digito) {
        if (numeroNuevo || pantalla.getText().equals("0")) {
            pantalla.setText(digito);
            numeroNuevo = false;
        } else if (pantalla.getText().length() < 10) {
            pantalla.setText(pantalla.getText() + digito);
        }
    }

    /**
     * Elige una operación. Lo usan los botones y el teclado.
     * Si ya había una operación pendiente, primero la calcula,
     * así 2 + 3 + 4 da 9. Después guarda el número y la nueva operación.
     *
     * @param simbolo operación elegida: "+", "-", "*" o "/"
     */
    private void elegirOperacion(String simbolo) {
        if (pantalla.getText().equals("Error")) {
            return;
        }

        if (!operacion.equals("") && !numeroNuevo) {
            pulsarIgual();
            if (pantalla.getText().equals("Error")) {
                return;
            }
        }

        operacion = simbolo;
        primerNumero = Double.parseDouble(pantalla.getText());
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