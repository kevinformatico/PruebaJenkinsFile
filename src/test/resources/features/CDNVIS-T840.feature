# encoding: utf-8
Feature: 
    @TestCaseKey=CDNVIS-T840
    Scenario: Ordenamiento del listado de Garantias vigentes con rut persona
        
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        When hago click en la cabecera de la columna "Folio"
        Then se ordenara por esa columna