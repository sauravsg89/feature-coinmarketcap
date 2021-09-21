@GUI @FUNCTIONAL 
Feature: GUI Test - Coinmarketcap
@FETASK1
Scenario: Frontend Task 1 - Verify row count
	Given user is on the webpage 
	When user selects Show rows dropdown value to 100
	Then Verify that 100 rows are displayed
@FETASK2
Scenario: Frontend Task 2 - Verify Filter
	Given user is on the webpage 
	When user clicks on Filter button
	And user Filter records by MarketCap between 1B and 10B while Price between 101 and 1000 dollars
	Then Verify that records displayed on page are correct as per the filter applied
		