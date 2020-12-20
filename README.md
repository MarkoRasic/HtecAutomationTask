# HtecAutomationTask
Automation project containing test solutions for both UI and API tests

Greetings!

You are looking at README file for project created as solution for HTEC test automation task. 

My task was to create tests including UI and API solutions.

What will you need to run it is next: 
- windows
- java version 1.8
- lombok plugin (available on plugin store)
- cucumber plugin (available on plugin store)

There are 2 different approaches to this task, one for UI and one for API tests:

UI tests are junit based in combination with SerenityBDD.
To run UI tests navigate to the file : src/test/java/htecautomation/sandboxUI/UiTestRunner.java and run userShouldBeAbleToCreateAndUpdateNumberOfUseCases() test.
After test run next command in terminal: mvn serenity:aggregate .
Link to the report will be generated. It can be open by any desired browser

API tests are written using combination of Cucumber and SerenityRest
Scenario can be found in : src/test/resources/features/htecAPIScenarios.feature
To run API tests navigate to the file : src/test/java/htecautomation/sandboxAPI/CucumberTestRunner.java and run the test
After test run next command in terminal: mvn serenity:aggregate .
Link to the report will be generated. It can be open by any desired browser
