# encoding: utf-8
@NoImpactoR29
Feature: No impacto R29 - V360


  @TestCaseKey=CDNVIS-T848
  Scenario: Validar funcionamiento despliegue filtro
    Given el usuario "JLABORDEB" ingreso a V360
    And busco el rut "10.000.102-0"
    And ingreso a detalle de garantias persona
    And me encuentro en el tab "Vigentes"
    When despliego el filtro
    And colapso el filtro
    Then se colapsa la seccion de filtro

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