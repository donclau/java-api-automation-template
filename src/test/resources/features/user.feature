
Feature: User API

  @get
  Scenario: Get an existing user
    When I request the user with ID 1
    Then the response status code should be 200
    And the user name should be "Leanne Graham"
    And the user email should be "Sincere@april.biz"

  @post
  Scenario: Create a new user
    When I create a user with the following information
      | name  | Claudio |
      | email | claudio@test.com |
    Then the response status code should be 201
    And the response should contain the user name "Claudio"
