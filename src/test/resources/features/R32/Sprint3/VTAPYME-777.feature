#encoding:utf-8

@VTAPYME-777
Feature: Configuración Líneas de Crédito PYME
  COMO Ejecutivo de Cuenta, Asistente Comercial, Jefe de Plataforma, Agente, Ejecutivo Banca Transaccional, Asistente Comercial Banca Transaccional
  DESEO contar con los campos necesarios
  PARA configurar una Banconexión como monoproducto


# C1: Se debe contar con los atributos necesarios para la configuración de la Línea de Crédito

  # Verificar atributos para un cliente PCG que tiene al menos una cta cte Normal MN vigente sin LDC

  @TestCaseKey=VTAPYME-T1515 @run
  Scenario: Validar Cliente  que no pertenezca  a los Segmento o Bca  No se visualize Banconexion 2.0 PYME
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una Banconexión
    Then no se visualiza el producto "Banconexión 2.0 PYME"

  @TestCaseKey=VTAPYME-T1514 @todo
  Scenario: Banconexion 2.0 PYME Cliente  PJ o PG y Bca Empresas y Privada  sin cuenta corriente MN validar mensaje
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    And voy a contratar una Banconexión
    Then se muestra el mensaje "No se puede vender este producto"
    And se muestra el mensaje "Cliente no tiene cuentas corrientes vigentes para vincular"

  @TestCaseKey=VTAPYME-T1513 @todo
  Scenario: Validar  el despliege de Aside de Rep Legal  para cliente Banconexion 2.0 PYME PG Y PJ  Bca Empresas y Privada  BEC Y BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME" con los siguientes valores:
      | clave | valor |
      | Cuenta cargo comisión | FIRST |
    Then se muestra el titulo de tipo de producto
    And se muestra el cuadro de apoderado rep Legal
    And se muestra caja rut con su lupa
    And se muestra link de agregar otro
    And se muestra boton guardar
    And se muestra X para cerrar aside
    And se muestra el mensaje cuando se abandona el aside "Esta segúro que sea abandorar Representante Legal?"

  @TestCaseKey=VTAPYME-T1512 @todo
  Scenario: Validar que se pueda seleccionar una cuenta corriente  para cliente Banconexion 2.0 PYME PG Y PJ  Bca Empresas y Privada  BEC Y BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME" con los siguientes valores:
      | clave | valor |
      | Cuenta cargo comisión | FIRST |
    Then el valor queda visible

  @TestCaseKey=VTAPYME-T1511 @todo
  Scenario: Banconexion 2.0 PYME Validar Tenga la caja de Rep.Legal o Apoderado  Visible para un  Cliente  PG Y PJ  Bca Empresas y Privada  BEC Y BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME" con los siguientes valores:
      | clave | valor |
      | Cuenta cargo comisión | FIRST |
    Then el ingreso a "Rep. Legal/Apoderados" debe visualizarse

  @TestCaseKey=VTAPYME-T1510 @todo
  Scenario: Banconexion 2.0 PYME Validar Tenga la caja de Administradores Visible para un  Cliente  PG Y PJ  Bca Empresas y Privada  BEC Y BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME" con los siguientes valores:
      | clave | valor |
      | Cuenta cargo comisión | FIRST |
    Then el ingreso a "Administradores" debe visualizarse

  @TestCaseKey=VTAPYME-T1509 @todo
  Scenario: Banconexion 2.0 PYME Validar Tenga la caja de Administradores Visible para un  Cliente  PG Y PJ  Bca Empresas y Privada  BEC Y BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se debe visualizar el select "Cuenta Cargo Comisión"

  @TestCaseKey=VTAPYME-T1508 @todo
  Scenario: Banconexion 2.0 PYME Validar Select Cuenta cargo comisión para un Cliente  PJ  Bca Empresas BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se debe visualizar el select "Cuenta Cargo Comisión"

  @TestCaseKey=VTAPYME-T1507 @todo
  Scenario: Banconexion 2.0 PYME Validar Select Cuenta cargo comisión para un Cliente  PJ  Bca Privada BEC
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se debe visualizar el select "Cuenta Cargo Comisión"

  @TestCaseKey=VTAPYME-T1506 @todo
  Scenario: Banconexion 2.0 PYME Validar Select Cuenta cargo comisión para un Cliente  PJ  Bca Privada BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se debe visualizar el select "Cuenta Cargo Comisión"

  @TestCaseKey=VTAPYME-T1505 @todo
  Scenario: Banconexion 2.0 PYME Validar Select Cuenta cargo comisión para un Cliente  PG  Bca Empresas BEC
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se debe visualizar el select "Cuenta Cargo Comisión"

  @TestCaseKey=VTAPYME-T1504 @todo
  Scenario: Banconexion 2.0 PYME Validar Select Cuenta cargo comisión para un Cliente  PG  Bca Empresas BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se debe visualizar el select "Cuenta Cargo Comisión"

  @TestCaseKey=VTAPYME-T1503 @todo
  Scenario: Banconexion 2.0 PYME Validar Select Cuenta cargo comisión para un Cliente  PG  Bca Privada BEC
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se debe visualizar el select "Cuenta Cargo Comisión"

  @TestCaseKey=VTAPYME-T1502 @todo
  Scenario: Banconexion 2.0 PYME Validar Select Cuenta cargo comisión para un Cliente PG Bca Privada BCH
    Given el usuario busca el rut "96.684.990-8" con perfil empresa
    And voy a contratar una Banconexión
    And contrato el producto "Banconexión 2.0 PYME"
    Then se debe visualizar el select "Cuenta Cargo Comisión"



