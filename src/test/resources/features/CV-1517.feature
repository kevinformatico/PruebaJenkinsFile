# encoding: utf-8

Feature: CV-1517

  Scenario: Probar algo
    Then pruebo algo
#C1: El sistema debe permitir acceder al listado completo de todas las garantías por constituir asociadas a un cliente.
  Scenario: Validar columnas Tab "Por constituir" con rut empresa
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "76023435-4"
    When ingreso a detalle de garantias empresa
    And me encuentro en el tab "Por constituir"
    Then el sistema muestra una tabla con las siguientes columnas
      | Folio                                                 |
      | Tipo de Garantia                                      |
      | Tipo de Bien                                          |
      | Estado                                                |
      | Grado                                                 |
      | Valor comercial (Miles)                               |
      | Valor Garantia (Miles)                                |
      | Tasacion                                              |
      | Seguro                                                |


  Scenario: Validar mensaje en caso de no existir garantias por constituir con rut empresa
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "83.493.700-K"
    When ingreso a detalle de garantias empresa
    And me encuentro en el tab "Por constituir"
    Then el sistema muestra el mensaje "Sin garantías por constituir"
    And el nombre del tab indica "Por constituir [0]"

  Scenario: Validar campo faltante tab "Por constituir" con rut empresa
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "76.023.435-4"
    And ingreso a detalle de garantias empresa
    When me encuentro en el tab "Por constituir"
    Then el sistema muestra un guion en el campo faltante

  Scenario: Validar extension de columnas en el tab "Por constituir"
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "76.023.435-4"
    And ingreso a detalle de garantias empresa
    And me encuentro en el tab "Por constituir"
    When hago click en la cabecera de la columnas "Tipo de Garantía"
    Then la columna se expande
#C2: El listado de garantías estará ordenado por número de folio, pero debe permitir cambiar el orden del listado según la columna
 #seleccionada.

  Scenario: Validar ordenamiento por defecto del listado de garantias por constituir con rut empresa
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "76.023.435-4"
    And ingreso a detalle de garantias empresa
    When me encuentro en el tab "Por constituir"
    Then aparece el listado de garantias ordenado por defecto segun folio de manera ascendente

  Scenario: Reordenamiento del listado de Garantias
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "76.023.435-4"
      And ingreso a detalle de garantias empresa
      And me encuentro en el tab "Por constituir"
      When hago click en la cabecera de la columnas "Folio"
      Then se ordenara por esa columna

#C4: El sistema debe permitir la navegación del listado de garantías, a través de una paginación de registros.
  Scenario: Validar funcionalidad del paginador
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "76.023.435-4"
      And ingreso a detalle de garantias empresa
      When me encuentro en el tab "Por constituir"
      And el cliente tiene mas de 10 registros
      Then se muestra el paginador
      And tiene las siguientes opciones
      | 10 |
      | 25 |
      | 50 |

#C5: El sistema debe tener una función de filtros de búsqueda para las garantías.
  Scenario: Validar campos filtro en Tab por constituir con rut empresa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "76.023.435-4"
        And ingreso a detalle de garantias empresa
        And me encuentro en el tab "Por constituir"
        When despliego el filtro
        Then se muestra el filtro para los siguientes campos
        |Folio|
        |Tipo de Garantía|
        And los siguientes botones
        | Limpiar |
        | Buscar |


Scenario: Validar funcionalidad del boton limpiar en tab por constituir con rut empresa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "76.023.435-4"
        And ingreso a detalle de garantias empresa
        And me encuentro en el tab "Por constituir"
        And despliego el filtro
        And ingreso un valor en el filtro
        When presiono el boton "Limpiar"
        Then los filtros se limpian


Scenario: Validar funcionalidad del boton buscar en tab por constituir con rut empresa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "76.023.435-4"
        And ingreso a detalle de garantias empresa
        And me encuentro en el tab "Por constituir"
        And despliego el filtro
        And ingreso un valor en el filtro
        When presiono el boton "Buscar"
        Then se aplican los filtros

Scenario: Validar busqueda sobre el total de datos
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "76.023.435-4"
        And ingreso a detalle de garantias empresa
        And me encuentro en el tab "Por constituir"
        And despliego el filtro
        And ingreso un valor en el filtro
        When presiono el boton "Buscar"
        Then se realiza la busqueda sobre el total de datos

Scenario: Validar funcionamiento del icono lupa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "76.023.435-4"
        And ingreso a detalle de garantias empresa
        And me encuentro en el tab "Por constituir"
        When despliego el filtro
        And colapso el filtro
        Then se colapsa la seccion de filtro

Scenario: Validar funcionalidad del boton buscar en tab por constituir con rut empresa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "76.023.435-4"
        And ingreso a detalle de garantias empresa
        And me encuentro en el tab "Por constituir"
        And despliego el filtro
        When ingreso un valor en el filtro combinado
        And presiono el boton "Buscar"
        Then se aplican los filtros

 @correr
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




#rut persona tab vigentes 10.000.102-0