@API @FUNCTIONAL 
Feature: API Test - Coinmarketcap 
@BETASK1 
Scenario:
Backend Task 1 - Fetch IDs of bitcoin (BTC), usd tether (USDT), and Ethereum (ETH) 
	Given user has access to API 
	When user sends a GET request to cryptocurrency map call 
	Then the IDs retreived should be converted to Bolivian Boliviano 
	
@BETASK2 
Scenario: Backend Task 2 - Fetch Data from Cryptocurrency Info Call 
	Given user has access to API 
	When user sends a GET request to cryptocurrency info call 
	Then user is able to Retrieve the Ethereum ID 1027 technical documentation website 
	And user is able to verify the following Key and Value pairs 
		|Key|Value|
		|logo|https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png|
		|technical_doc|[https://github.com/ethereum/wiki/wiki/White-Paper]|
		|symbol|ETH|
		|date_added|2015-08-07T00:00:00.000Z|
		|tags|mineable|	
		
@BETASK3 
Scenario:
Backend Task 3 - Fetch First 10 currencies using Cryptocurrency Info Call
	Given user has access to API 
	When user sends a GET request to cryptocurrency info call for first ten currencies
	Then the currencies having mineable tag are printed out 