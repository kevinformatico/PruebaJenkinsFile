# encoding: utf-8
Feature: 
    @TestCaseKey=CDNVIS-T835 @correr
    Scenario: Validar columnas Tab "Vigentes" con rut persona
        
        Given el usuario "JLABORDEB" ingreso a V360
            And busco el rut "10.000.102-0"
            When ingreso a detalle de garantias persona
            And me encuentro en el tab "Vigentes"
            Then el sistema muestra una tabla con las siguientes columnas
              | Folio                                                 |
              | Tipo de Garantia                                      |
              | Tipo de Bien                                          |
              | Estado                                                |
              | Grado                                                 |
              | Valor Comercial (Miles)                               |
              | Valor Garantia (Miles)                                |
              | Tasacion                                              |
              | Seguro                                                |