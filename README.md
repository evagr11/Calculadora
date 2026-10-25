<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:000000,50:333333,100:FF9F0A&height=200&section=header&text=Calculadora&fontSize=60&fontColor=fff&animation=fadeIn&fontAlignY=35&desc=Calculadora%20de%20escritorio%20en%20JavaFX%20%7C%20Patr%C3%B3n%20MVC&descAlignY=55&descSize=18" width="100%"/>
</div>

<div align="center">
  <a href="https://github.com/evagr11/calculadora" target="_blank">
    <img src="https://img.shields.io/badge/Ver_Código-333333?style=for-the-badge&logo=github&logoColor=white" alt="Ver Código"/>
  </a>
  <a href="https://github.com/evagr11/calculadora/tree/main/docs" target="_blank">
    <img src="https://img.shields.io/badge/Javadoc-FF9F0A?style=for-the-badge&logo=openjdk&logoColor=white" alt="Javadoc"/>
  </a>
  <img src="https://img.shields.io/badge/Estado-Terminado-A5A5A5?style=for-the-badge" alt="Estado"/>
</div>

<br>

<div align="center">
  <img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&size=22&duration=3000&pause=1000&color=FF9F0A&center=true&vCenter=true&width=600&lines=JavaFX+%2B+FXML+%2B+Maven;Modelo+%E2%86%94+Vista+%E2%86%94+Controlador;Pruebas+unitarias+con+JUnit+5;Documentado+con+Javadoc" alt="Typing SVG"/>
</div>

---

# 🧮 Sobre el Proyecto

**Calculadora** es una aplicación de escritorio hecha en **JavaFX** que realiza las cuatro operaciones básicas: suma, resta, multiplicación y división.

La interfaz está definida en **FXML** y tiene un diseño oscuro con botones redondeados. La lógica está separada siguiendo el patrón **MVC**, de modo que el modelo que hace las cuentas no sabe nada de botones ni de ventanas.

✨ **Características principales:**

- ➕ **Cuatro operaciones**: suma, resta, multiplicación y división.
- 🔗 **Operaciones encadenadas**: si ya hay una operación pendiente, se calcula al pulsar el siguiente operador (`2 + 3 + 4` da `9`).
- 🔢 **Números decimales**: el punto solo se puede añadir una vez por número.
- 🚫 **División entre cero controlada**: el modelo lanza una `ArithmeticException` y la pantalla muestra `Error`.
- 🎯 **Resultados limpios**: los enteros se muestran sin `.0` y los decimales se redondean a 8 cifras, así no aparecen cosas como `0.30000000000000004`.
- 📏 **Límite de 10 caracteres** para que el número siempre quepa en la pantalla.
- ✅ **Pruebas unitarias** del modelo con **JUnit 5**.
- 📖 **Documentación Javadoc** generada en la carpeta `docs/`.

---

# 🧠 Contexto del Proyecto

Este proyecto es una práctica de la asignatura **Desarrollo de Interfaces** del segundo curso de **DAM** (Desarrollo de Aplicaciones Multiplataforma).

El objetivo era construir una interfaz gráfica de escritorio con **JavaFX** partiendo del arquetipo **FXML de Maven** en **IntelliJ IDEA**. Además había que separar bien la vista, la lógica y el control de eventos, probar el código y documentarlo.

---

# 🛠️ Stack Tecnológico

<p align="center">
  <img src="https://img.shields.io/badge/Java_11-333333?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/JavaFX_13-FF9F0A?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/FXML-A5A5A5?style=for-the-badge&logo=xml&logoColor=black"/>
  <img src="https://img.shields.io/badge/Maven-333333?style=for-the-badge&logo=apache-maven&logoColor=white"/>
  <img src="https://img.shields.io/badge/JUnit_5-FF9F0A?style=for-the-badge&logo=junit5&logoColor=white"/>
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
</p>

<div align="center">

| Área | Tecnología |
|------|------------|
| **Lenguaje** | `Java 11` |
| **Interfaz** | `JavaFX 13` (`javafx-controls`, `javafx-fxml`) |
| **Vista** | `FXML` con estilos `-fx-*` en línea |
| **Build** | `Maven` + `javafx-maven-plugin` |
| **Pruebas** | `JUnit Jupiter 5.10` |
| **Documentación** | `Javadoc` |

</div>

---

# 🧩 Arquitectura (MVC)

<div align="center">

| Capa | Archivo | Responsabilidad |
|------|---------|-----------------|
| 🟠 **Modelo** | `Calculadora.java` | Las cuatro operaciones. Lanza una excepción al dividir entre 0 |
| ⚫ **Vista** | `calculadora.fxml` | Pantalla (`Label`) y botonera en un `GridPane` de 4 × 5 |
| ⚪ **Controlador** | `CalculadoraController.java` | Recoge los clics, guarda el estado y llama al modelo |
| ▶️ **Arranque** | `App.java` | Carga el FXML y abre una ventana de tamaño fijo |

</div>

Cada botón de operación guarda su símbolo en `userData` (`+`, `-`, `*`, `/`). Así un único método, `pulsarOperacion`, sirve para los cuatro operadores, y lo mismo pasa con `pulsarNumero` para los diez dígitos.

---

# 📂 Estructura del Proyecto

```bash
calculadora/
│
├── pom.xml
├── docs/                          # Javadoc generado
│
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── module-info.java
    │   │   └── org/example/
    │   │       ├── App.java                    # Arranque
    │   │       ├── Calculadora.java            # Modelo
    │   │       └── CalculadoraController.java  # Controlador
    │   └── resources/
    │       └── org/example/
    │           └── calculadora.fxml            # Vista
    │
    └── test/
        └── java/org/example/
            └── CalculadoraTest.java            # Pruebas JUnit
```

---

# 🖥️ Interfaz

<table align="center">
  <tr>
    <td align="center" valign="middle">
      <img width="171" height="281" alt="Implementación de la calculadora en JavaFX" src="https://github.com/user-attachments/assets/37687eab-c54b-4dc4-b978-6c0559d4b074" />
      <p><em>Implementación final de la calculadora</em></p>
    </td>
    <td valign="middle">
      <table>
        <tr>
          <th>Elemento</th>
          <th>Color</th>
        </tr>
        <tr>
          <td>Fondo</td>
          <td><img src="https://img.shields.io/badge/-%23000000-000000?style=flat-square" alt="#000000"/></td>
        </tr>
        <tr>
          <td>Números y punto</td>
          <td><img src="https://img.shields.io/badge/-%23333333-333333?style=flat-square" alt="#333333"/></td>
        </tr>
        <tr>
          <td>Operadores e igual</td>
          <td><img src="https://img.shields.io/badge/-%23FF9F0A-FF9F0A?style=flat-square" alt="#FF9F0A"/></td>
        </tr>
        <tr>
          <td>Borrar (C)</td>
          <td><img src="https://img.shields.io/badge/-%23A5A5A5-A5A5A5?style=flat-square" alt="#A5A5A5"/></td>
        </tr>
      </table>
    </td>
  </tr>
</table>

---

# 🚀 Cómo Ejecutarlo

### 📋 Requisitos

- **JDK 11** o superior
- **Maven** (o el Maven que trae IntelliJ IDEA)

### ▶️ Ejecutar la aplicación

```bash
git clone https://github.com/evagr11/calculadora.git
cd calculadora
mvn clean javafx:run
```

En **IntelliJ IDEA** también se puede lanzar desde el panel de Maven: `Plugins → javafx → javafx:run`.

### ✅ Ejecutar las pruebas

```bash
mvn test
```

| Prueba | Comprueba |
|--------|-----------|
| `testSumar` | `5 + 3 = 8` |
| `testRestar` | `5 - 3 = 2` |
| `testMultiplicar` | `5 × 3 = 15` |
| `testDividir` | `5 ÷ 2 = 2.5` |
| `testDividirEntreCeroDaError` | `5 ÷ 0` lanza `ArithmeticException` |

### 📖 Ver la documentación

Abre `docs/index.html` en el navegador para consultar el **Javadoc** de todas las clases.

---

# 📚 Lo que aprendí con este proyecto

- Crear interfaces de escritorio con **JavaFX** y **FXML**
- Conectar la vista con el controlador mediante `fx:id`, `onAction` y `@FXML`
- Aplicar el patrón **MVC** separando la lógica de la interfaz
- Maquetar con `VBox` y `GridPane` usando `columnSpan`
- Reutilizar un mismo manejador para varios botones con `userData`
- Controlar errores con **excepciones** y mostrarlos al usuario
- Evitar los problemas de precisión de los `double` al mostrar resultados
- Escribir **pruebas unitarias** con JUnit 5 y documentar con **Javadoc**

---

# 🎯 Mejoras futuras

- ⌨️ Manejar la calculadora con el teclado
- 🔙 Botón para borrar el último dígito
- ➗ Operaciones extra: porcentaje, cambio de signo (`±`) y raíz cuadrada
- 🎨 Llevar los estilos a una hoja **CSS** externa
- 🧪 Pruebas del controlador con **TestFX**

---

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:000000,50:333333,100:FF9F0A&height=100&section=footer" width="100%"/>

✨ Proyecto creado por **Eva Gallardo** ✨

</div>
