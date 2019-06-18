# encoding: utf-8

Feature: CV-1517

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

  @correr
  Scenario: Validar mensaje en caso de no existir garantias por constituir con rut empresa
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "83.493.700-K"
    When ingreso a detalle de garantias empresa
    And me encuentro en el tab "Por constituir"
    Then el sistema muestra el mensaje "Sin garantías por constituir"
    And el nombre del tab indica "Por constituir [0]"
  
  Scenario: Validar campo faltante tab "Por constituir" con rut empresa
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    And ingreso a detalle de garantias empresa
    When me encuentro en el tab "Por constituir"
    Then el sistema muestra un guion en el campo faltante

  Scenario: Validar extension de columnas en el tab "Por constituir" 
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    And ingreso a detalle de garantias empresa
    And me encuentro en el tab "Por constituir"
    When hago click en la cabecera de la columnas "Tipo de Garantia"
    Then la columna se expande
    And se colapsan las otras columnas
  
  