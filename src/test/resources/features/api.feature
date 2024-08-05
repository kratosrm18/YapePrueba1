Feature: API Testing con Rest Assured y Cucumber

  Scenario: Crear token de acceso con metodo POST
    Given Configuro la solicitud para el endpoint auth
    When Envio el request metodo POST
    Then Recibo la respuesta con codigo 200
    And Se recibio el token

  Scenario: No crear token de acceso con metodo GET
    Given Configuro la solicitud para el endpoint auth
    When Envio el request metodo GET
    Then Recibo la respuesta con codigo 404

  Scenario: No crear token de acceso con metodo PUT
    Given Configuro la solicitud para el endpoint auth
    When Envio el request metodo PUT
    Then Recibo la respuesta con codigo 404

  Scenario: No crear token de acceso con metodo DELETE
    Given Configuro la solicitud para el endpoint auth
    When Envio el request metodo DELETE
    Then Recibo la respuesta con codigo 404

  Scenario: Obtener los IDs de booking
    Given Configuro la solicitud para el endpoint GetBookingIds
    When Envio el request metodo GET
    Then Recibo la respuesta con codigo 200
    And Recibo los IDs de booking

  Scenario: Verificar que la API está activa
    Given Configuro la solicitud para el endpoint HealthCheck
    When Envio el request metodo GET
    Then Recibo la respuesta con codigo 201

  Scenario: Verificar que la API está inactiva
    Given Configuro la solicitud para el endpoint HealthCheck
    When Envio el request metodo GET
    Then Recibo la respuesta con codigo 503
