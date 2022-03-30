Feature: Categories test
  As a user of categories repository
  I want to crud categories
  So that I can expose categories service

  Scenario Outline: search categories
    Given we have a list of categories
    When we search categories by id "<id>"
    Then we should find categories <name>

    Examples: examples1
      | id      | name       |
      | 1       | History    |
      | 2       | Fun Facts  |
