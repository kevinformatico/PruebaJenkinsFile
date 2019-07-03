# encoding: utf-8

Feature: CV-1582


#C1: El sistema debe permitir acceder al listado completo de todas las garantías Históricas asociadas a un cliente.
  @TestCaseKey=CDNVIS-T892
Scenario: Validar columnas Tab "Históricas" con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "3.681.806-9"
    When ingreso a detalle de garantias persona
    And me encuentro en el tab "Históricas"
    Then el sistema muestra una tabla con las siguientes columnas
      | Folio                                                 |
      | Tipo de Garantía                                      |
      | Tipo de Bien                                          |
      | Estado                                                |
      | Grado                                                 |
      | Valor Comercial (Miles)                               |
      | Valor Garantía (Miles)                                |
      | Tasación                                              |
      | Seguro                                                |

  @TestCaseKey=CDNVIS-T893
Scenario: Validar mensaje en caso de no existir garantias Históricas con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "1.658.793-1"
    When ingreso a detalle de garantias persona
    And me encuentro en el tab "Históricas"
    Then el sistema muestra el mensaje "Sin garantías históricas"
    And el nombre del tab indica "Históricas [0]"

  @TestCaseKey=CDNVIS-T894
Scenario: Validar campo faltante tab "Históricas" con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "3.681.806-9"
  And ingreso a detalle de garantias persona
  When me encuentro en el tab "Históricas"
  Then el sistema muestra un guion en el campo faltante

  @TestCaseKey=CDNVIS-T895
Scenario: Validar extension de columnas en el tab "Históricas" con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "3.681.806-9"
  And ingreso a detalle de garantias persona
  And me encuentro en el tab "Históricas"
  When hago click en el cuerpo de la columna "Tipo de Bien"
  Then la columna se expande

  @TestCaseKey=CDNVIS-T896
Scenario: Validar ordenamiento por defecto del listado de garantias Históricas con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "3.681.806-9"
    And ingreso a detalle de garantias persona
    When me encuentro en el tab "Históricas"
    Then aparece el listado de garantias ordenado por defecto segun folio de manera ascendente

  @TestCaseKey=CDNVIS-T897
Scenario: Ordenamiento del listado de Garantias Históricas con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "3.681.806-9"
  And ingreso a detalle de garantias persona
  And me encuentro en el tab "Históricas"
  When hago click en la cabecera de la columna "Folio"
  Then se ordenara por esa columna

  @TestCaseKey=CDNVIS-T898
Scenario: Reordenamiento del listado de Garantias Históricas con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "3.681.806-9"
    And ingreso a detalle de garantias persona
    And me encuentro en el tab "Históricas"
    When hago click en la cabecera de la columna "Tipo de Bien"
    And hago click nuevamente en la cabecera de la columna "Tipo de Bien"
    Then se invierte el orden de esa columna

  @TestCaseKey=CDNVIS-T899
Scenario: Validar visualizacion del paginador de tab vigente con rut persona
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "3.681.806-9"
      And ingreso a detalle de garantias persona
      When me encuentro en el tab "Históricas"
      And el cliente tiene mas de 10 registros
      Then se muestra el paginador

  @TestCaseKey=CDNVIS-T900
Scenario: Validar opciones del paginador del paginador
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "3.681.806-9"
      And ingreso a detalle de garantias persona
      When me encuentro en el tab "Históricas"
      And el cliente tiene mas de 10 registros
      Then el paginador tiene las siguientes opciones
      | 10 |
      | 25 |
      | 50 |
  @TestCaseKey=CDNVIS-T901
Scenario: Validar campos filtro en Tab Históricas con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "3.681.806-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Históricas"
        When despliego el filtro
        Then se muestra el filtro para los siguientes campos
        |Folio|
        |Tipo de Garantía|
        And los siguientes botones
        | Limpiar |
        | Buscar |

  @TestCaseKey=CDNVIS-T902
Scenario: Validar funcionalidad del boton limpiar en tab Históricas con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "3.681.806-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Históricas"
        And despliego el filtro
        And ingreso el valor "Prenda Industrial" en el filtro "Tipo de Garantía"
        When presiono el boton "Limpiar"
        Then los filtros se limpian

  @TestCaseKey=CDNVIS-T903
Scenario: Validar funcionalidad del boton buscar en tab Históricas con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "3.681.806-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Históricas"
        And despliego el filtro
        And ingreso el valor "160503" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then se aplican los filtros

  @TestCaseKey=CDNVIS-T904
Scenario: Validar busqueda sobre el total de datos en tab Históricas con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "3.681.806-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Históricas"
        And despliego el filtro
        And ingreso el valor "160503" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then se realiza la busqueda sobre el total de datos

  @TestCaseKey=CDNVIS-T905
Scenario: Validar funcionamiento del icono lupa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "3.681.806-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Históricas"
        When despliego el filtro
        And colapso el filtro
        Then se colapsa la seccion de filtro

  @TestCaseKey=CDNVIS-T906
Scenario: Validar busqueda sobre el total de datos en tab Históricas con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "3.681.806-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Históricas"
        And despliego el filtro
        And ingreso el valor "113321" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then el sistema arroja el mensaje "No se encontraron resultados"

  @TestCaseKey=CDNVIS-T907 @run
Scenario: Validar funcionalidad del boton buscar en tab Históricas con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "3.681.806-9"
    And ingreso a detalle de garantias persona
    And me encuentro en el tab "Históricas"
    And despliego el filtro
    When ingreso el valor "160503" en el filtro "Folio"
    And presiono el boton "Buscar"
    Then se aplican los filtros

