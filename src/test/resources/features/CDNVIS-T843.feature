# encoding: utf-8
Feature: 
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