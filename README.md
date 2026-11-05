# java-api-automation-template

Proyecto de pruebas de API REST automatizadas con Java, Gradle, Cucumber y REST Assured. Los escenarios están escritos en Gherkin y prueban el endpoint público de usuarios de [JSONPlaceholder](https://jsonplaceholder.typicode.com).

## Requisitos

- JDK 25. El proyecto configura Java 25 en Gradle.
- Conexión a Internet para descargar Gradle y las dependencias (en la primera ejecución) y para acceder a la API probada.
- La ejecución paralela está habilitada para hasta 2 escenarios simultáneos.

No hace falta instalar Gradle: el proyecto incluye el Gradle Wrapper.

## Ejecutar las pruebas

Desde la carpeta raíz del proyecto, en Windows:

```powershell
.\gradlew.bat test
```

En Linux o macOS:

```bash
./gradlew test
```

Gradle compila y ejecuta la suite de Cucumber. El comando termina con error si una prueba falla. Para ejecutar también las tareas de verificación del proyecto:

```powershell
.\gradlew.bat check
```

### Limpiar archivos generados

Gradle genera resultados, clases compiladas y reportes en la carpeta `build/`. Para eliminar esa carpeta antes de ejecutar las pruebas, desde la raíz del proyecto en Windows ejecuta:

```powershell
.\gradlew.bat clean
```

También puedes limpiar y ejecutar las pruebas en un solo comando:

```powershell
.\gradlew.bat clean test
```

La tarea `clean` elimina únicamente los archivos generados en `build/`; no borra el código fuente ni la configuración del proyecto.

## Reportes

El proyecto integra Allure. Después de ejecutar las pruebas, se puede generar el reporte HTML con:

```powershell
.\gradlew.bat allureReport
```

Para generar el reporte y abrirlo en un servidor local:

```powershell
.\gradlew.bat allureServe
```

Los resultados de pruebas de Gradle quedan en `build/reports/tests/test/`; los datos de Allure, en `build/allure-results/`; y el reporte HTML de Allure, en `build/reports/allure-report/`.

## Estructura del proyecto

```text
.
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
└── src/
    └── test/
        ├── java/com/automation/
        │   ├── clients/
        │   │   └── UserClient.java
        │   ├── runners/
        │   │   └── RunCucumberTest.java
        │   └── steps/
        │       └── UserSteps.java
        └── resources/
            ├── features/
            │   └── user.feature
            └── junit-platform.properties
```

- `build.gradle`: configura Java 25, dependencias, ejecución con JUnit Platform e integración con Allure.
- `settings.gradle`: define el nombre del proyecto (`java-api-automation-template`).
- `gradlew` y `gradlew.bat`: permiten ejecutar Gradle sin instalarlo globalmente.
- `src/test/resources/features/user.feature`: define los escenarios de lectura de un usuario y creación de un usuario.
- `src/test/resources/junit-platform.properties`: configura el paquete de steps y el plugin de Cucumber para Allure.
- `src/test/java/com/automation/runners/RunCucumberTest.java`: suite JUnit que descubre y ejecuta las features de Cucumber.
- `src/test/java/com/automation/steps/UserSteps.java`: implementa los pasos Gherkin, configura la URL base y valida las respuestas.
- `src/test/java/com/automation/clients/UserClient.java`: contiene las solicitudes HTTP GET y POST con REST Assured.
- `cucumber.execution.parallel.*` en `src/test/resources/junit-platform.properties`: habilita la ejecución paralela de escenarios de Cucumber con una estrategia fija de 2 hilos.
- `build/`: contiene archivos generados por Gradle, incluidos resultados y reportes; no es código fuente.

## Escenarios incluidos

La feature `user.feature` contiene:

1. **GET de usuario existente:** solicita `/users/1` y valida el estado `200`, el nombre y el correo electrónico.
2. **POST de usuario:** envía nombre y correo a `/users` y valida el estado `201`, el ID y el nombre de la respuesta.

La URL base `https://jsonplaceholder.typicode.com` se configura por solicitud en `UserClient`, evitando modificar la configuración estática global de REST Assured mientras los escenarios corren en paralelo.
