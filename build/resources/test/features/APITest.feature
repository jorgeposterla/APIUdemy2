Feature: Feature example


Scenario: Testing GET to endpoint
Given I send a GET request to the https://api.github.com URI
Then I get a 200 status code


Scenario: To validate the ammount of entries in the response
    Given I send a GET request to the https://jsonplaceholder.typicode.com URI
    Then I validate there are 10 items on the /users endpoint

@API
Scenario: To validate that an element is in the response
    Given I send a GET request to the https://jsonplaceholder.typicode.com URI
    Then I validate there is a value: Bret in the response at /users endpoint


Scenario: To validate that a nested value is in the response
    Given I send a GET request to the https://jsonplaceholder.typicode.com URI
    Then I validate there is a nested value: Kattie Turnpike in the response at /users endpoint