#encoding:utf-8

@VTAPYME-777
Feature: Configuración Líneas de Crédito PYME
  COMO Ejecutivo de Cuenta, Asistente Comercial, Jefe de Plataforma, Agente, Ejecutivo Banca Transaccional, Asistente Comercial Banca Transaccional
  DESEO contar con los campos necesarios
  PARA configurar una Banconexión como monoproducto


# C1: Se debe contar con los atributos necesarios para la configuración de la Línea de Crédito

  # Verificar atributos para un cliente PCG que tiene al menos una cta cte Normal MN vigente sin LDC

  @TestCaseKey=
  Scenario: Validar que aparezca ui select cargo comisión cuando selecciono el producto "Banconexión 2.0 PYME" de la familia canales remotos
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se muestra el select "Cuenta cargo comisión"


  Scenario: Validar el ingreso requerido de Rep. Legal/Apoderados para personas juridicas
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then el ingreso a "Rep. Legal/Apoderados" debe ser requerido
    And agrego el "9408742-2" como representante legal

  Scenario: Validar el ingreso opcional de Rep. Legal/Apoderados para personas con giro
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then el ingreso a "Rep. Legal/Apoderados" debe ser opcional