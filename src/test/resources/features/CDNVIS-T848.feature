# encoding: utf-8
Feature: 
    @TestCaseKey=CDNVIS-T848
    Scenario: Validar funcionamiento del icono lupa
        
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        When despliego el filtro
        And colapso el filtro
        Then se colapsa la seccion de filtro