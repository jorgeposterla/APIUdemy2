@BraveNewCoin
Feature: Brave New Coin API scenarios: POST GetToken

    Rule: When I send a POST request to the endpoint, I receive a token that I can use further authenticated calls.

        Scenario: As a user, I can retrieve a token when making a valid POST request
            Given  I have a valid API key for the https://bravenewcoin.p.rapidapi.com URI
            When I send a POST request with a valid TokenRequestBody payload to the /oauth/token endpoint
            Then I can validate I receive a valid token in the response

        Scenario: As a user, when I use an invalid API key, I get an HTTP status code 403
            Given  I have an invalid API key for the https://bravenewcoin.p.rapidapi.com URI
            When I send a POST request with a valid body to the /oauth/token endpoint
            Then I receive an HTTP status code 403

        Scenario: As a user, when I send the POST request without "grant_type", I get a status code 400
            Given I have a valid API key for the https://bravenewcoin.p.rapidapi.com URI
            When  I send a POST request without "grant_type" in it's body
            Then I receive a HTTP status code 400