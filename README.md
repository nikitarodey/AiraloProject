# Airalo Automation

This project contains UI automation tests and API Automation tests for Airalo's website using Selenium and Cucumber.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **Java JDK 8 or higher**: Make sure Java is installed and the `JAVA_HOME` environment variable is set.
- **Maven**: Ensure Maven is installed and added to your system's PATH.
- **ChromeDriver**: Download ChromeDriver from [here](https://sites.google.com/a/chromium.org/chromedriver/downloads) and make sure its path is correctly set in the `stepDefination.java` file.
- **Google Chrome Browser**: Ensure Google Chrome is installed.


## Setup and Running Tests

### 1. Clone the Repository

Clone this repository to your local machine using the following command:

```sh
git clone https://github.com/nikitarodey/AiraloProject.git
cd AiraloProject
mvn clean
mvn install
mvn test
  OR
You can also run the test using the TestRunner.java file or also you can run feature file seperately.
Maven will compile the project and execute the Cucumber tests.
