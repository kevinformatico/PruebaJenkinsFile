#encoding:utf-8
@VTAPYME-1152
Feature: Configuración Líneas de Crédito Privada	
  COMO Ejecutivo de cuenta, asistente comercial, agente, jefe de plataforma
  DESEO configurar las condiciones comerciales
  PARA una LDC nueva

# C1: Se debe contar con los atributos necesarios para la configuración de la Línea de Crédito.

  # Verificar atributos para un cliente PCG que tiene al menos una cta cte Normal MN vigente sin LDC
  
  Background:
    Given el usuario busca el rut "14.255.888-2" con perfil empresa
    And voy a contratar una LDC
    And contrato el producto "Línea de Crédito Automática Personas"

  Scenario: Validar fallo al ingreso de monto de cupo menor al minimo segun parametros de taller
    When ingreso el monto a solicitar 200000
    And lo agrego a oportunidad
    Then se muestra el mensaje de error "Menor al monto mínimo"

  Scenario: Validar fallo al ingreso de monto de cupo mayor al maximo segun parametros de taller
    When ingreso el monto a solicitar 300000000
    And lo agrego a oportunidad
    Then se muestra el mensaje de error "Supera el monto máximo"

  Scenario: Validar valor de spread entregado por taller
    When ingreso el monto a solicitar 500000
    Then el spread debe debe ser 1.9%

  Scenario: Validar ingreso de spread con valor numerico y dos decimales
    When ingreso el monto a solicitar 500000
    And ingreso un spread de 1.99%
    And lo agrego a oportunidad
    Then no entrega mensaje de error para los campos llenados

  @run
  Scenario: Validar fallo ingreso de spread con valor 0
    When ingreso el monto a solicitar 500000
    And ingreso un spread de 0%
    And lo agrego a oportunidad
    Then se muestra el mensaje de error "Debe ser mayor a 0"

  Scenario: Validar fallo ingreso de spread con mayor a la maxima convencional
    When ingreso el monto a solicitar 500000
    And ingreso un spread de 5.99%
    And lo agrego a oportunidad
    Then se muestra el mensaje de error "Debe ser inferior a máx. convencional"

  Scenario: Validar opciones para aumento Programado de cupo
    Then el campo "Aumento programado de cupo" contiene los siguientes valores
    |3 veces ingreso mensual|
    |4 veces ingreso mensual|
    |5 veces ingreso mensual|
    |6 veces ingreso mensual|
    |No aplica|

  Scenario: Permitir seleccionar sólo el Tipo de Plazo "Indefinido"
    Then el select "Tipo Plazo" contiene solo el valor "Indefinido"

  Scenario: Validar Frecuencia entrega cartola "Mensual"
    Then se refleja frecuencia entrega cartola "Mensual"

  Scenario: Validar mensaje Spread ingresado requerirá autorización comercial
    When ingreso el monto a solicitar 1000000
    And ingreso un spread de 1%
    Then se muestra el mensaje "Spread ingresado requerirá autorización comercial"

  @todo
  Scenario: Validar campos requeridos
    When lo agrego a oportunidad
    Then aparece el mensaje "Requerido" en los siguientes campos:
      | Monto a Solicitar ($)|
      | Spread (%)|
      | Tipo Plazo|
      | N° de cuenta asociada |
      | Aumento programado de cupo |
    And aparece el mensaje de advertencia "Campos Incompletos" y el subtitulo "Verifique elementos en rojo"

    @todo
  Scenario: Validar check para seguro de desgravamen
    Then aparece desgravamen de linea de credito habilitado

