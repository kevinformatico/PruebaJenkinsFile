#encoding:utf-8
@NOIMPACTO-R32
  Feature: No Impacto

    #Background:
     # Given el usuario busca el rut "4.889.509-3" con perfil empresa
     # And voy a contratar una LDC
     # And contrato el producto "Línea de Crédito PYME"

  Scenario: Validar flujo para LDC Automatica Empresas con PJ
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito Automática Empresas" con los siguientes valores:
    | clave | valor |
    | Monto a Solicitar ($)| 3000000 |
    | Tipo Plazo| Fijo |
    | Plazo     |  4   |
    | Spread (%)| 0.2 |
    | N° de cuenta asociada | FIRST |
    And agrego el "9408742-2" como representante legal
    And asocio los limites
    And lo agrego a oportunidad
    And se agrega al carro correctamente
    When continuo a presentación de productos
    Then se validan los datos de configuracion en presentacion

  Scenario: Validar flujo para LDC PYME con PG
    Given el usuario busca el rut "2.896.059-K" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito PYME" con los siguientes valores:
      | clave | valor |
      | Monto a Solicitar ($)| 3000000 |
      | Tipo Plazo| Indefinido |
      | N° de cuenta asociada | FIRST |
      | Aumento programado de cupo | FIRST |
    And asocio los limites
    When lo agrego a oportunidad
    Then se agrega al carro correctamente
    When continuo a presentación de productos
    Then se validan los datos de configuracion en presentacion

  Scenario: Validar flujo para LDC Privada con PG
    Given el usuario busca el rut "16.879.879-2" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito Privada" con los siguientes valores:
      | clave | valor |
      | Monto a Solicitar ($)| 3000000 |
      | Tipo Plazo| Indefinido |
      | N° de cuenta asociada | FIRST |
      | Aumento programado de cupo | FIRST |
    And asocio los limites
    When lo agrego a oportunidad
    Then se agrega al carro correctamente
    When continuo a presentación de productos
    Then se validan los datos de configuracion en presentacion