Feature: CV-1517

@CDNVIS-T793
  Scenario: 001_Mostrar cuadro "Resumen Garantias"de la seccion de inteligencia de la V360 Personas
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    When ingreso a detalle de garantias persona
    Then el sistema muestra una tabla con las siguientes columnas
      | Folio                                                 |
      | Tipo de Garantia                                      |
      | Tipo de Bien                                          |
      | Estado                                                |
      | Grado                                                 |
      | Valor comercial                                       |
      | Valor Garantia                                        |
      | Tasacion                                              |
      | Seguro                                                |
    #And Se pierde la conexion de internet
