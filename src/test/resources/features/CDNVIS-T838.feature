# encoding: utf-8
Feature: 
    @TestCaseKey=CDNVIS-T838
    Scenario: Validar extension de columnas en el tab "Vigentes" con rut persona
        
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "5.762.651-8"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        When hago click en el cuerpo de la columna "Tipo de Garant?a"
        Then la columna se expande