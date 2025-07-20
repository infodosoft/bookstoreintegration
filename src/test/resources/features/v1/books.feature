@AllAPIs @books
Feature: Book API Test

  Background:
    Given the bookstore API is running

  @happy
  Scenario Outline: Retrieve a list of all books
    When User retrieves a list of <type> in store
    Then the response status code should be <responseStatusCode> from the service
    And The list of <type> is not empty

    Examples:
      | type  | responseStatusCode |
      | books | 200                |

  @happy
  Scenario Outline: Retrieve details of a specific book by its ID.
    When User retrieves a <type> details in store
    Then the response status code should be <responseStatusCode> from the service
    And The details of <type> is not empty
    Examples:
      | type  | responseStatusCode |
      | books | 200                |
  @happy
  Scenario Outline: Add a new book to the system
    When User adds a new <type> in the store
    Then the response status code should be <responseStatusCode> from the service
    And the response should contain the <type> created
    Examples:
      | type  | responseStatusCode |
      | books | 200                |

  @happy
  Scenario Outline: Update an existing book by its ID.
    When User update an existing <type> in the store
    Then the response status code should be <responseStatusCode> from the service
    And the response should contain the updated <type> in store
    Examples:
      | type  | responseStatusCode |
      | books | 200                |

  @happy
  Scenario Outline: Delete a book by its ID.
    When User deletes an existing <type> in the store
    Then the response status code should be <responseStatusCode> from the service
    And the response should not contain the updated <type> in store
    Examples:
      | type  | responseStatusCode |
      | books | 200                |
  @edge
  Scenario Outline: Create book with missing fields
    When User adds a new <type> in the store using invalid <invalidPayload> payload
    Then the response status code should be <responseStatusCode> from the service
    And the response should contain the <type> created
    Examples:
      | type  | responseStatusCode | invalidPayload |
      | books | 200                | true           |

  @edge
  Scenario Outline: Get book with invalid ID
    When User retrieves a <type> details in store using invalidId <invalidId> id
    Then the response status code should be <responseStatusCode> from the service
    Examples:
      | type  | responseStatusCode | invalidId |
      | books | 404                | 999999    |

  @edge
  Scenario Outline: Delete book with invalid ID
    When User deletes an existing <type> in the store using invalid <invalidId> id
    Then the response status code should be <responseStatusCode> from the service
    Examples:
      | type  | responseStatusCode | invalidId |
      | books | 400                | abc       |
