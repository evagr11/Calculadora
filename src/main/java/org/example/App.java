package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal. Carga la VISTA (calculadora.fxml), muestra la ventana
 * y hace que la calculadora se pueda usar también con el teclado.
 *
 * @author Eva Gallardo Romero
 */
public class App extends Application {

    /**
     * Crea la ventana de la calculadora.
     * Carga el FXML, recoge su controlador y le pasa cada tecla que se pulse
     * en la ventana.
     *
     * @param stage ventana principal que crea JavaFX
     * @throws IOException si no se puede cargar el archivo FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("calculadora.fxml"));
        Parent raiz = loader.load();
        CalculadoraController controlador = loader.getController();

        Scene escena = new Scene(raiz);

        escena.setOnKeyPressed(evento -> controlador.teclaPulsada(evento));

        stage.setTitle("Calculadora");
        stage.setResizable(false);
        stage.setScene(escena);
        stage.show();
    }

    /**
     * Punto de entrada del programa. Arranca la aplicación JavaFX.
     *
     * @param args argumentos de la línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        launch();
    }
}