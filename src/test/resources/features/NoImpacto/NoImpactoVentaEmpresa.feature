#encoding:utf-8
  @R32
  Feature: No Impacto

    #Background:
     # Given el usuario busca el rut "4.889.509-3" con perfil empresa
     # And voy a contratar una LDC
     # And contrato el producto "Línea de Crédito PYME"
@run
  Scenario:
    Given DateTime check period from "10" to "{}" is {}seconds