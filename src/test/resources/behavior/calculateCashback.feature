Feature: Calculate Cashback

  Scenario:
    Given I receive a request to obtain a cashback with 2 bottles
    When make  request to cashback machine
    Then i must return this informations
    """
    {
      "cashBackValue": 0.64
    }
    """