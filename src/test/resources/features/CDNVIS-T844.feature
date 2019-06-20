# encoding: utf-8
Feature: 
    @TestCaseKey=CDNVIS-T844
    Scenario: Validar campos filtro en Tab vigentes con rut persona
        
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        And me encuentro en el tab "Vigentes"
        When despliego el filtro
        Then se muestra el filtro para los siguientes campos
        |Folio|
        |Tipo de Garant?a|
        And los siguientes botones
        | Limpiar |
        | Buscar |