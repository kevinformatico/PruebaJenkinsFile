# encoding: utf-8
Feature: 
    @TestCaseKey=CDNVIS-T837
    Scenario: Validar campo faltante tab "Vigentes" con rut persona
        
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        When me encuentro en el tab "Vigentes"
        Then el sistema muestra un guion en el campo faltante