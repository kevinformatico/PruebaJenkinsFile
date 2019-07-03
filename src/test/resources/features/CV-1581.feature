# encoding: utf-8
Feature: CV-1581


#C1: El sistema debe permitir acceder al listado completo de todas las garantías Por constituir asociadas a un cliente.
  @TestCaseKey=CDNVIS-T876
Scenario: Validar columnas Tab "Por constituir" con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "14.661.589-9"
    When ingreso a detalle de garantias persona
    And me encuentro en el tab "Por constituir"
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

  @TestCaseKey=CDNVIS-T877
Scenario: Validar mensaje en caso de no existir garantias Por constituir con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "1.658.793-1"
    When ingreso a detalle de garantias persona
    And me encuentro en el tab "Por constituir"
    Then el sistema muestra el mensaje "Sin garantías por constituir"
    And el nombre del tab indica "Por constituir [0]"

  @TestCaseKey=CDNVIS-T878
Scenario: Validar campo faltante tab "Por constituir" con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "14.661.589-9"
  And ingreso a detalle de garantias persona
  When me encuentro en el tab "Por constituir"
  Then el sistema muestra un guion en el campo faltante

  @TestCaseKey=CDNVIS-T879
Scenario: Validar extension de columnas en el tab "Por constituir" con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "14.661.589-9"
  And ingreso a detalle de garantias persona
  And me encuentro en el tab "Por constituir"
  When hago click en el cuerpo de la columna "Tipo de Bien"
  Then la columna se expande

  @TestCaseKey=CDNVIS-T880
Scenario: Validar ordenamiento por defecto del listado de garantias Por constituir con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "14.661.589-9"
    And ingreso a detalle de garantias persona
    When me encuentro en el tab "Por constituir"
    Then aparece el listado de garantias ordenado por defecto segun folio de manera ascendente

  @TestCaseKey=CDNVIS-T881
Scenario: Ordenamiento del listado de Garantias Por constituir con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "14.661.589-9"
  And ingreso a detalle de garantias persona
  And me encuentro en el tab "Por constituir"
  When hago click en la cabecera de la columna "Folio"
  Then se ordenara por esa columna

  @TestCaseKey=CDNVIS-T882
Scenario: Reordenamiento del listado de Garantias Por constituir con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "14.661.589-9"
    And ingreso a detalle de garantias persona
    And me encuentro en el tab "Por constituir"
    When hago click en la cabecera de la columna "Tipo de Bien"
    And hago click nuevamente en la cabecera de la columna "Tipo de Bien"
    Then se invierte el orden de esa columna

  @TestCaseKey=CDNVIS-T883
Scenario: Validar visualizacion del paginador de tab vigente con rut persona
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "14.661.589-9"
      And ingreso a detalle de garantias persona
      When me encuentro en el tab "Por constituir"
      And el cliente tiene mas de 10 registros
      Then se muestra el paginador

  @TestCaseKey=CDNVIS-T884
Scenario: Validar opciones del paginador del paginador
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "14.661.589-9"
      And ingreso a detalle de garantias persona
      When me encuentro en el tab "Por constituir"
      And el cliente tiene mas de 10 registros
      Then el paginador tiene las siguientes opciones
      | 10 |
      | 25 |
      | 50 |
  @TestCaseKey=CDNVIS-T885
Scenario: Validar campos filtro en Tab Por constituir con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "14.661.589-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Por constituir"
        When despliego el filtro
        Then se muestra el filtro para los siguientes campos
        |Folio|
        |Tipo de Garantía|
        And los siguientes botones
        | Limpiar |
        | Buscar |

  @TestCaseKey=CDNVIS-T886
Scenario: Validar funcionalidad del boton limpiar en tab Por constituir con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "14.661.589-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Por constituir"
        And despliego el filtro
        And ingreso el valor "Prenda Industrial" en el filtro "Tipo de Garantía"
        When presiono el boton "Limpiar"
        Then los filtros se limpian

  @TestCaseKey=CDNVIS-T887
Scenario: Validar funcionalidad del boton buscar en tab Por constituir con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "14.661.589-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Por constituir"
        And despliego el filtro
        And ingreso el valor "269163" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then se aplican los filtros

  @TestCaseKey=CDNVIS-T888
Scenario: Validar busqueda sobre el total de datos en tab Por constituir con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "14.661.589-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Por constituir"
        And despliego el filtro
        And ingreso el valor "269163" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then se realiza la busqueda sobre el total de datos

  @TestCaseKey=CDNVIS-T889
Scenario: Validar funcionamiento del icono lupa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "14.661.589-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Por constituir"
        When despliego el filtro
        And colapso el filtro
        Then se colapsa la seccion de filtro

  @TestCaseKey=CDNVIS-T890
Scenario: Validar busqueda sobre el total de datos en tab Por constituir con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "14.661.589-9"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Por constituir"
        And despliego el filtro
        And ingreso el valor "113321" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then el sistema arroja el mensaje "No se encontraron resultados"

  @TestCaseKey=CDNVIS-T891
Scenario: Validar funcionalidad del boton buscar en tab Por constituir con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "14.661.589-9"
    And ingreso a detalle de garantias persona
    And me encuentro en el tab "Por constituir"
    And despliego el filtro
    When ingreso el valor "269163" en el filtro "Folio"
    And presiono el boton "Buscar"
    Then se aplican los filtros