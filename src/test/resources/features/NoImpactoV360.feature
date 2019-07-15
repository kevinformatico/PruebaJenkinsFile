Feature: No impacto V360


  Scenario: Funcionalidad icono Banco de chile para volver al escritorio ejecutivo
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    When hago click en el logo del Banco
    Then el sistema deriva a el Escritorio Ejecutivo

  Scenario: Busqueda dentro de la V360 con rut empresa
    When el usuario busca el rut "4.889.509-3" con perfil empresa
    Then se muestra el mensaje "Cargando Datos"
    And aparece la V360 empresa

  Scenario: Despliegue del icono Indicadores Economicos
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    When hago click en el icono Indicadores Economicos ubicado en el header
    Then se despliega con los siguientes tipos de monedas:
      | USD|
      | UF|
      | UTM|
    And se cierra el cuadro al hacer click en el icono

  Scenario: Despliegue del icono Indicadores Economicos
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    When hago click en el icono Indicadores Economicos ubicado en el header
    And no trae un valor
    Then aparece "Sin valor"

  Scenario: Despliegue de Notificaciones
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    When hago click en el icono de notificaciones ubicado en el header
    Then posee