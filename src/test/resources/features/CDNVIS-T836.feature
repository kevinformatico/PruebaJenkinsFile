# encoding: utf-8
Feature: 
    @TestCaseKey=CDNVIS-T836
    Scenario: Validar mensaje en caso de no existir garantias vigentes con rut persona
        
        Given el usuario "JLABORDEB" ingreso a V360
            And busco el rut "1.658.793-1"
            When ingreso a detalle de garantias persona
            And me encuentro en el tab "Vigentes"
            Then el sistema muestra el mensaje "Sin garant?as vigentes"
            And el nombre del tab indica "Vigentes [0]"