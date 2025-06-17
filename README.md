# SauceDemo UI Testing

## Project Description
This project demonstrates UI test automation for the [SauceDemo](https://www.saucedemo.com/) website using Java, Selenium, and TestNG. SauceDemo is a mock ecommerce site designed for developers to practice UI automation skills. This project showcases software test engineering skills such as the design of a custom testing framework using the page object model (POM), cross-browser testing (planned), and continuous integration using GitHub Actions.

## Features
- UI Testing for SauceDemo using Selenium and Java
- TestNG for test management and data-driven testing
- Cross browser testing support (planned)
- Page Object Model (POM) for modular and maintainable test framework
- Parallel test execution with 4 threads
- CI/CD pipeline using GitHub Actions
- Gradle for build and dependency management

## Project Structure
- framework/base/: Base classes for page objects and tests
- framework/config/: Configuration file reader
- framework/driver/: WebDriver setup and management
- pages/: POM classes for different pages of the SauceDemo application
- tests/: Test classes for each page
- .github/workflows/: GitHub Actions CI/CD configuration
- src/test/resources/: Configuration and test suite files

## Setup Instructions
### Cloning the Repository
```
git clone https://github.com/timothygaus/SauceDemoUITesting.git
cd SauceDemoUITesting
```

### Running Tests Locally
```
./gradlew test
```
Test reports will be generated in the `build/reports/tests/test` directory.

### Running Tests via GitHub Actions
[![CI/CD Pipeline](https://github.com/timothygaus/SauceDemoUITesting/actions/workflows/ci.yml/badge.svg)](https://github.com/timothygaus/SauceDemoUITesting/actions/workflows/ci.yml)

The CI/CD pipeline automatically triggers on every push or pull request. Check [here](https://github.com/timothygaus/SauceDemoUITesting/actions) for the latest results.

## Future Enhancements
This project is an active WIP intended for me to learn software testing tools, including building a robust and scalable UI testing framework. Some of the future features I have planned are:
- Expanded test coverage (adding items to the cart, checkout process, etc)
- Optimize test runtime
- Improved reported with more detailed logs and screenshots
- Cross browser testing support

## Contact
- Author: Timothy Gause
- GitHub: [timothygaus](https://github.com/timothygaus)
