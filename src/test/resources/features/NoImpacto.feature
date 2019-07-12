# encoding: utf-8
Feature: No Impacto Venta Empresa
  @run
  Scenario: Validar flujo PYME MN
    Given el usuario "MNILOS" ingreso a V360
    And busco el rut "4.889.509-3"
    And ingreso a Vista 360 "empresa"
    And voy a contratar un producto
    And despliego familia "Líneas"
    When voy a contratar el producto "Línea de Crédito Privada" con los siguientes valores:
    | clave | valor |
    | Monto a Solicitar| 300000000 |
    | Spread| 3 |
    | Tipo Plazo| Indefinido |
    | cuenta asociada| 003680717700 |
    | Aumento programado de cupo | 3 veces ingreso mensual |
    Then se agrega al carro correctamente
    #And continuo a presentación de productos
    #And agrego los siguientes datos adicionales
    #|clave|valor|
    #|clave|valor|
    #And Adjunto los siguientes documentos
    #  |clave|valor|
    #  |clave|valor|
    #Then envio a previsado
    And espero por 20 segundos











#C1: El sistema debe permitir acceder al listado completo de todas las garantías vigentes asociadas a un cliente.
@TestCaseKey=CDNVIS-T835
Scenario: Validar columnas Tab "Vigentes" con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    When ingreso a detalle de garantias persona
    And me encuentro en el tab "Vigentes"
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

  @TestCaseKey=CDNVIS-T836
Scenario: Validar mensaje en caso de no existir garantias vigentes con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "1.658.793-1"
    When ingreso a detalle de garantias persona
    And me encuentro en el tab "Vigentes"
    Then el sistema muestra el mensaje "Sin garantías vigentes"
    And el nombre del tab indica "Vigentes [0]"

  @TestCaseKey=CDNVIS-T837
Scenario: Validar campo faltante tab "Vigentes" con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "10.000.102-0"
  And ingreso a detalle de garantias persona
  When me encuentro en el tab "Vigentes"
  Then el sistema muestra un guion en el campo faltante

  @TestCaseKey=CDNVIS-T838
Scenario: Validar extension de columnas en el tab "Vigentes" con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "5.762.651-8"
  And ingreso a detalle de garantias persona
  And me encuentro en el tab "Vigentes"
  When hago click en el cuerpo de la columna "Tipo de Bien"
  Then la columna se expande

  @TestCaseKey=CDNVIS-T839
Scenario: Validar ordenamiento por defecto del listado de garantias vigentes con rut persona
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    And ingreso a detalle de garantias persona
    When me encuentro en el tab "Vigentes"
    Then aparece el listado de garantias ordenado por defecto segun folio de manera ascendente

  @TestCaseKey=CDNVIS-T840
Scenario: Ordenamiento del listado de Garantias vigentes con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "10.000.102-0"
  And ingreso a detalle de garantias persona
  And me encuentro en el tab "Vigentes"
  When hago click en la cabecera de la columna "Folio"
  Then se ordenara por esa columna

  @TestCaseKey=CDNVIS-T841
Scenario: Reordenamiento del listado de Garantias vigentes con rut persona
  Given el usuario "JLABORDEB" ingreso a V360
  And busco el rut "5.762.651-8"
  And ingreso a detalle de garantias persona
  And me encuentro en el tab "Vigentes"
  When hago click en la cabecera de la columna "Tipo de Bien"
  And hago click nuevamente en la cabecera de la columna "Tipo de Bien"
  Then se invierte el orden de esa columna

  @TestCaseKey=CDNVIS-T842
Scenario: Validar visualizacion del paginador de tab vigente con rut persona
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "5.762.651-8"
      And ingreso a detalle de garantias persona
      When me encuentro en el tab "Vigentes"
      And el cliente tiene mas de 10 registros
      Then se muestra el paginador

  @TestCaseKey=CDNVIS-T843
Scenario: Validar opciones del paginador del paginador
      Given el usuario "JLABORDEB" ingreso a V360
      And busco el rut "5.762.651-8"
      And ingreso a detalle de garantias persona
      When me encuentro en el tab "Vigentes"
      And el cliente tiene mas de 10 registros
      Then el paginador tiene las siguientes opciones
      | 10 |
      | 25 |
      | 50 |

  @TestCaseKey=CDNVIS-T844
Scenario: Validar campos filtro en Tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        When despliego el filtro
        Then se muestra el filtro para los siguientes campos
        |Folio|
        |Tipo de Garantía|
        |Estado|
        And los siguientes botones
        | Limpiar |
        | Buscar |

  @TestCaseKey=CDNVIS-T845
Scenario: Validar funcionalidad del boton limpiar en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        And ingreso el valor "Prenda Industrial" en el filtro "Tipo de Garantía"
        When presiono el boton "Limpiar"
        Then los filtros se limpian

  @TestCaseKey=CDNVIS-T846
Scenario: Validar funcionalidad del boton buscar en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        And ingreso el valor "269163" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then se aplican los filtros

  @TestCaseKey=CDNVIS-T847
Scenario: Validar busqueda sobre el total de datos en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        And ingreso el valor "269163" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then se realiza la busqueda sobre el total de datos

  @TestCaseKey=CDNVIS-T848
Scenario: Validar funcionamiento del icono lupa
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        When despliego el filtro
        And colapso el filtro
        Then se colapsa la seccion de filtro

  @TestCaseKey=CDNVIS-T849
Scenario: Validar busqueda sobre el total de datos en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        And ingreso el valor "113321" en el filtro "Folio"
        When presiono el boton "Buscar"
        Then el sistema arroja el mensaje "No se encontraron resultados"

  @TestCaseKey=CDNVIS-T850
Scenario: Validar funcionalidad del boton buscar en tab vigentes con rut persona
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        And despliego el filtro
        When ingreso el valor "269163" en el filtro "Folio"
        And presiono el boton "Buscar"
        Then se aplican los filtros