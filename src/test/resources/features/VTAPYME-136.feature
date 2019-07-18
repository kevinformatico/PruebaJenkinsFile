#encoding:utf-8

@VTAPYME-136
Feature: Configuración Líneas de Crédito PYME
  COMO Ejecutivo de cuenta, asistente comercial, agente, jefe de plataforma
  DESEO configurar las condiciones comerciales
  PARA una LDC nueva


# C1: Se debe contar con los atributos necesarios para la configuración de la Línea de Crédito

  @TestCaseKey=VTAPYME-T1316 @run
  Scenario: Verificar atributos para un cliente PCG que tiene al menos una cta cte Normal MN vigente sin LDC
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una LDC
    When contrato el producto "Línea de Crédito PYME" con los siguientes valores:
      | clave | valor |
      | Monto a Solicitar ($)| 3000000 |
      | Spread (%)| 3 |
      | Tipo Plazo| Indefinido |
      | N° de cuenta asociada | FIRST |
      | Aumento programado de cupo | FIRST |
    Then se agrega al carro correctamente
    And espero por 20 segundos

