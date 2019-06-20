# encoding: utf-8
Feature: 
    @TestCaseKey=CDNVIS-T839
    Scenario: Validar ordenamiento por defecto del listado de garantias vigentes con rut persona
        
        Given el usuario "JLABORDEB" ingreso a V360
        And busco el rut "10.000.102-0"
        And ingreso a detalle de garantias persona
        When me encuentro en el tab "Vigentes"
        Then aparece el listado de garantias ordenado por defecto segun folio de manera ascendente