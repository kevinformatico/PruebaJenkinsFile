#encoding:utf-8
@VTAPYME-136
Feature: Configuración Líneas de Crédito PYME
  COMO Ejecutivo de cuenta, asistente comercial, agente, jefe de plataforma
  DESEO configurar las condiciones comerciales
  PARA una LDC nueva

  Scenario: Verificar atributos para un cliente PCG que tiene al menos una cta cte Normal MN vigente sin LDC
    Given el usuario busca el rut "4.889.509-3" con perfil empresa
    When voy a contratar el siguiente producto
      | Familia | Producto | Monto a Solicitar | Spread | Tipo Plazo | cuenta asociada | Aumento programado de cupo |
      | Líneas | Línea de Crédito Privada | 30000000 | 2 | Indefinido | 003680717700 | 3 veces ingreso mensual |
    Then se agrega el producto correctamente



