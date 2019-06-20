@correr
Feature: CV-1519


#C1: El sistema debe permitir acceder al listado completo de todas las garantías vigentes asociadas a un cliente.
Scenario: Validar columnas Tab "Vigentes" con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    When ingreso a detalle de garantias persona
    And me encuentro en el tab "Vigentes"
    Then el sistema muestra una tabla con las siguientes columnas
      | Folio                                                 |
      | Tipo de Garantia                                      |
      | Tipo de Bien                                          |
      | Estado                                                |
      | Grado                                                 |
      | Valor Comercial (Miles)                               |
      | Valor Garantia (Miles)                                |
      | Tasacion                                              |
      | Seguro                                                |

Scenario: Validar mensaje en caso de no existir garantias vigentes con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "1.658.793-1"
    When ingreso a detalle de garantias persona
    And me encuentro en el tab "Vigentes"
    Then el sistema muestra el mensaje "Sin garantías vigentes"
    And el nombre del tab indica "Vigentes [0]"

Scenario: Validar campo faltante tab "Vigentes" con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    And ingreso a detalle de garantias persona
    When me encuentro en el tab "Vigentes"
    Then el sistema muestra un guion en el campo faltante

Scenario: Validar extension de columnas en el tab "Vigentes" con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    And ingreso a detalle de garantias persona
    And me encuentro en el tab "Vigentes"
    When hago click en la cabecera de la columnas "Tipo de Garantía"
    Then la columna se expande

Scenario: Validar ordenamiento por defecto del listado de garantias vigentes con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    And ingreso a detalle de garantias persona
    When me encuentro en el tab "Vigentes"
    Then aparece el listado de garantias ordenado por defecto segun folio de manera ascendente

Scenario: Reordenamiento del listado de Garantias vigentes con rut persona
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "10.000.102-0"
      And ingreso a detalle de garantias persona
      And me encuentro en el tab "Vigentes"
      When hago click en la cabecera de la columnas "Folio"
      Then se ordenara por esa columna

Scenario: Reordenamiento del listado de Garantias vigentes con rut persona
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "10.000.102-0"
      And ingreso a detalle de garantias persona
      And me encuentro en el tab "Vigentes"
      When hago click en la cabecera de la columnas "Folio"
      Then se invierte el orden de esa columna

Scenario: Validar visualizacion del paginador del paginador
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "10.000.102-0"
      And ingreso a detalle de garantias persona
      When me encuentro en el tab "Vigentes"
      And el cliente tiene mas de 10 registros
      Then se muestra el paginador

Scenario: Validar opciones del paginador del paginador
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "10.000.102-0"
      And ingreso a detalle de garantias empresa
      When me encuentro en el tab "Por constituir"
      And el cliente tiene mas de 10 registros
      Then el paginador tiene las siguientes opciones
      | 10 |
      | 25 |
      | 50 |

Scenario: Validar campos filtro en Tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        When despliego el filtro
        Then se muestra el filtro para los siguientes campos
        |Folio|
        |Tipo de Garantía|
        And los siguientes botones
        | Limpiar |
        | Buscar |

Scenario: Validar funcionalidad del boton limpiar en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        And ingreso un valor en el filtro
        When presiono el boton "Limpiar"
        Then los filtros se limpian

Scenario: Validar funcionalidad del boton buscar en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        And ingreso el valor "117987" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then se aplican los filtros

Scenario: Validar busqueda sobre el total de datos en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        And ingreso el valor "117987" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then se realiza la busqueda sobre el total de datos

Scenario: Validar funcionamiento del icono lupa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        When despliego el filtro
        And colapso el filtro
        Then se colapsa la seccion de filtro

Scenario: Validar busqueda sobre el total de datos en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        And ingreso el valor "113321" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then el sistema arroja el mensaje "No se encontraron resultados"

Scenario: Validar funcionalidad del boton buscar en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        When ingreso el valor "117987" en el filtro "Folio"
        And ingreso el valor "Constituida" en el filtro "Estado"
        And presiono el boton "Buscar"
        Then se aplican los filtros