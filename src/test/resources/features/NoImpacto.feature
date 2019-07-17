# encoding: utf-8
Feature: No Impacto Venta Empresa

  Scenario: Validar flujo PYME MN
    Given el usuario "MNILOS" ingreso a V360
    And busco el rut "4.889.509-3"
    And ingreso a Vista 360 empresa
    And voy a contratar un producto
    And despliego familia "Líneas"
    When voy a contratar el producto "Línea de Crédito Privada" con los siguientes valores:
    | clave | valor |
    | Monto a Solicitar| 300000000 |
    | Spread| 3 |
    | Tipo Plazo| Indefinido |
    | N° de cuenta asociada | r |
    | Aumento programado de cupo | 3 veces ingreso mensual |
    Then se agrega al carro correctamente

  Scenario: Validar flujo PYME MN
    Given el usuario busca el rut "86.881.400-4" con perfil empresa
    And voy a contratar un producto
    And despliego familia "Líneas"
    When voy a contratar el producto "Línea de Crédito Automática Empresas" con los siguientes valores:
    | clave | valor |
    | Monto a Solicitar ($)| 300000000 |
    | Spread (%)| 3 |
    | Tipo Plazo| Fijo |
    | Plazo | 3 |
    | N° de cuenta asociada | r |
    #Then se agrega al carro correctamente
    And espero por 10 segundos








  # Steps

  Scenario: Validar flujo PYME MN
    Given el usuario "MNILOS" ingreso a V360
    And busco el rut "4.889.509-3"
    And ingreso a Vista 360 "empresa"
    And voy a contratar un producto
    And despliego familia "Líneas"
    When voy a contratar el producto "Línea de Crédito Privada" con los siguientes valores:
      | clave | valor |
      | Monto a Solicitar| 300000000 |
      | Spread| 3 |
      | Tipo Plazo| Indefinido |
      | cuenta asociada| 003680717700 |
      | Aumento programado de cupo | 3 veces ingreso mensual |
    Then se agrega al carro correctamente
    #And continuo a presentación de productos
    #And agrego los siguientes datos adicionales
    #|clave|valor|
    #|clave|valor|
    #And Adjunto los siguientes documentos
    #  |clave|valor|
    #  |clave|valor|
    #Then envio a previsado