#encoding:utf-8

@VTAPYME-136
Feature: Configuración Líneas de Crédito PYME
  COMO Ejecutivo de cuenta, asistente comercial, agente, jefe de plataforma
  DESEO configurar las condiciones comerciales
  PARA una LDC nueva


# C1: Se debe contar con los atributos necesarios para la configuración de la Línea de Crédito

  # Verificar atributos para un cliente PCG que tiene al menos una cta cte Normal MN vigente sin LDC

  @TestCaseKey=VTAPYME-T1419
  Scenario: Validar fallo al ingreso de monto de cupo menor al minimo segun parametros de taller
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito PYME"
    When ingreso el monto a solicitar 200000
    And lo agrego a oportunidad
    Then se muestra el mensaje de error "Menor al monto mínimo"

  @TestCaseKey=VTAPYME-T1420
  Scenario: Validar fallo al ingreso de monto de cupo mayor al maximo segun parametros de taller
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito PYME"
    When ingreso el monto a solicitar 300000000
    And lo agrego a oportunidad
    Then se muestra el mensaje de error "Supera el monto máximo"

  @TestCaseKey=VTAPYME-T1421
  Scenario: Validar ingreso de spread con valor numerico y dos decimales
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito PYME"
    When ingreso el monto a solicitar 500000
    And ingreso un spread de 2,99%
    Then no entrega mensaje de error para los campos llenados

  @TestCaseKey=VTAPYME-T1422
  Scenario: Validar fallo ingreso de spread con valor negativo
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito PYME"
    When ingreso el monto a solicitar 500000
    And ingreso un spread de -1,99%
    Then se muestra el mensaje de error "Debe ser mayor a 0"

  @TestCaseKey=VTAPYME-T1423 @demo
  Scenario: Validar fallo ingreso de spread con mayor a la maxima convencional
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito PYME"
    When ingreso el monto a solicitar 500000
    And ingreso un spread de 5,99%
    Then se muestra el mensaje de error "Debe ser inferior a máx. convencional"

  @TestCaseKey=VTAPYME-T1424
  Scenario: Validar seleccion no aplica para Aumento Programado de cupo
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito PYME"
    When ingreso "No Aplica" en aumento programado de cupo
    Then permite seleccionar el valor "No Aplica"

  @TestCaseKey=VTAPYME-T1425
  Scenario: Validar Frecuencia entrega cartola "Mensual"
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito PYME"
    Then se refleja frecuencia entrega cartola "Mensual"

  @TestCaseKey=VTAPYME-T1425
  Scenario: Validar monto de cupo segun parametros de taller
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
    And continuo a presentación de productos
    And espero por 5 segundos
