# encoding: utf-8
Feature: 
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