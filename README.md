# Cache Distribuida

Este es un proyecto que implementa una **Cache Distribuida** utilizando **Redis** para almacenar y gestionar usuarios de manera eficiente en una aplicación. 
Utilizando Spring Boot y Redis.

## Tecnologías utilizadas

- **Java 11**
- **Spring Boot**
- **Redis** (para la implementación de la cache)
- **JUnit** (para pruebas unitarias)
- **Mockito** (para pruebas con mocks)
- **Spring Data Redis**

## Características

- **Guardar** usuarios en la cache.
- **Actualizar** usuarios en la cache.
- **Eliminar** usuarios de la cache.
- **Consultar** todos los usuarios almacenados en la cache.


## Requisitos

- Java 11 o superior.
- Redis instalado y en funcionamiento.

## Instrucciones de instalación

1. Clonar el repositorio:

    ```bash
    git clone https://github.com/JuanSebastianMoralesVilla/Cache_Distribuida.git
    ```

2. Navegar al directorio del proyecto:

    ```bash
    cd Cache_Distribuida
    ```

3. Asegúrate de tener Redis en ejecución en tu máquina. Si no lo tienes, puedes instalarlo siguiendo la documentación oficial de [Redis](https://redis.io/download).

4. Compilar el proyecto:

    ```bash
    mvn clean install
    ```

5. Ejecutar la aplicación:

    ```bash
    mvn spring-boot:run
    ```
## Coleccion postman 
