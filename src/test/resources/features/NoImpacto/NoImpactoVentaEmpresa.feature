#encoding:utf-8
  @R32
  Feature: No Impacto

    #Background:
     # Given el usuario busca el rut "4.889.509-3" con perfil empresa
     # And voy a contratar una LDC
     # And contrato el producto "Línea de Crédito PYME"
@run
  Scenario: Pruebas
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito Automática Empresas" con los siguientes valores:
    | clave | valor |
    | Tipo Plazo| Fijo |
    | Monto a Solicitar ($)| 3000000 |
    | Spread (%)| 0.2 |
    | N° de cuenta asociada | FIRST |
    | Plazo     |  4   |
    And agrego el "9408742-2" como representante legal
    And asocio los limites
    When lo agrego a oportunidad
    Then se agrega al carro correctamente