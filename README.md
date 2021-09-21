# feature-coinmarketcap
GUI &amp; API Automation framework for Coinmarketcap website

-----------HOW TO RUN--------------
-----------PRE-REQUISITES--------------
1. Take checkout of code from feature/coinmarketcap branch
2. Go to pom.xml and Run a Maven(clean install) build to download all the libraries
3. ENSURE "chromedriver.exe" file compatible with your Chromedriver version should be placed/replaced in the root folder. 
The Current file will support Chrome Driver version 93.0.4577.8 
Reference URL to download Chrome Driver: https://chromedriver.chromium.org/downloads 

-----------HOW TO RUN--------------

-----------GUI + API---------------
1. Go to cucumber.framework.CucumberRunner class file
2. Enter: tags= "@FUNCTIONAL" in Line Number 15 
3. Right Click -> Run as Junit Test

-----------GUI ALL TEST CASES---------------
1. Go to cucumber.framework.CucumberRunner class file
2. Enter: tags= "@GUI" //Comment Line Number 15 and Uncomment Line Number 16
3. Right Click -> Run as Junit Test

-----------API ALL TEST CASES---------------
1. Go to cucumber.framework.CucumberRunner class file
2. Enter: tags= "@API" //Comment Line Number 15 and Uncomment Line Number 17
3. Right Click -> Run as Junit Test

-----------GUI ANY 1 TEST CASE---------------
1. Go to cucumber.framework.CucumberRunner class file
2. Enter: tags= "@FETASK1" //Comment Line Number 15 and Uncomment Line Number 19
3. Right Click -> Run as Junit Test

-----------API ANY 1 TEST CASE---------------
1. Go to cucumber.framework.CucumberRunner class file
2. Enter: tags= "@BETASK1" //Comment Line Number 15 and Uncomment Line Number 18
3. Right Click -> Run as Junit Test
