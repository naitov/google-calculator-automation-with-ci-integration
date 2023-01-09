### Overview
The aim of the testing framework is to demonstrate a good way to write and run autotests using Selenium under 
multiple browsers with detailed reporting.

### Run
The framework can be run in 3 browsers:
- Google Chrome
- Mozilla Firefox
- Safari

Suite consists of 3 different testing scopes:
- Smoke - only critical tests `surefire.suiteXmlFiles=src/test/resources/testng-smoke.xml`
- Minimal acceptance tests - critical tests + new features `surefire.suiteXmlFiles=src/test/resources/testng-minimal.xml`
Acceptance tests - pre-production testing with all tests `surefire.suiteXmlFiles=src/test/resources/testng-all.xml`

Thus, **the complete command for command line should look like this**:

`mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src/test/resources/testng-all.xml clean test`

### Reporting
In this Framework has implemented  [Allure reporting](https://docs.qameta.io/allure/).
To view report, it should be installed locally or in Jenkins. 
For console output, log4j2 used as well.

### Misc
Estimated run time of all tests - 3 Minutes.



