# 📚 Bookstore API Test Automation Framework

Automated API testing framework for the [Fake REST API Bookstore](https://fakerestapi.azurewebsites.net). Designed using **Java 21**, **TestNG**, **RestAssured**, **Cucumber BDD**, and enhanced with **Allure & Extent Reports**, this framework ensures clean architecture, comprehensive test coverage, and CI/CD pipeline integration via **GitHub Actions**.

---

## 🚀 Features

- ✅ **Books & Authors API** full CRUD test coverage
- ✅ **Cucumber BDD** for behavior-driven scenarios
- ✅ **TestNG** for execution and parallelism
- ✅ **Allure Reports** with GitHub Pages deployment
- ✅ **Faker-based dynamic test data**
- ✅ **Happy paths + edge cases + invalid scenarios**
- ✅ **CI/CD pipeline with Maven + GitHub Actions**
- ✅ **Java 21**, Maven, and clean SOLID structure

---

## 🛠️ Technology Stack

| Layer        | Tech Used                          |
|--------------|------------------------------------|
| Language     | Java 21                            |
| Build Tool   | Maven                              |
| Frameworks   | RestAssured, Cucumber, TestNG      |
| Reporting    | Allure, ExtentReports              |
| CI/CD        | GitHub Actions                     |
| Utilities    | JavaFaker, Log4j2                  |

---

## 📂 Folder Structure

```
bookstore-api-tests/
│
├── src/
│   ├── main/java/book/store/api/v1/
│   │   ├── config/           # Property loader
│   │   ├── utils/            # PayloadBuilder, Faker
│   └── test/java/book/store/api/v1/
│       ├── hooks/           # Hooks for reporting and lifecycle
│       ├── steps/           # Step definitions for Books & Authors
│       ├── runners/         # TestNGRunner for execution
│
├── resources/
│   ├── features/            # Feature files (Books & Authors)
│   ├── config.properties    # Base URI and environment
│   └── log4j2.xml           # Logging configuration
│
├── target/                  # Output: reports, logs, etc.
├── testng.xml               # Entry suite file
├── pom.xml                  # Maven dependencies & config
└── .github/workflows/       # GitHub Actions CI + Allure deploy
```

---

## ⚙️ Setup & Run Instructions

### 📌 Prerequisites

- Java 21 installed
- Maven installed
- Allure CLI installed:  
  `brew install allure` or [Download CLI](https://docs.qameta.io/allure/#_installing_a_commandline)

---

### ▶️ Run Tests Locally

```bash
mvn clean test
```

### 📈 View Reports

#### Allure Report

```bash
allure serve target/allure-results
```

Or generate static HTML:

```bash
allure generate target/allure-results --clean -o target/allure-report
```

## 🧪 Running Specific Suites

You can run specific `testng.xml` files:

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

Or define them via Maven profiles (`smoke`, `regression`).

---

## 🧬 CI/CD: GitHub Actions

CI workflow runs on every push to `main`.

- Executes tests using Java 21
- Generates Allure results
- Deploys Allure HTML report to GitHub Pages
- Uploads report artifact for download

📂 Workflow file: `.github/workflows/ci.yml`

---

## 🌍 Accessing Allure Report (GitHub Pages)

If Pages is enabled in your repo:

```
https://infodosoft.github.io/bookstoreintegration/
```

Ensure:
- `gh-pages` is the Pages source (set in GitHub → Settings → Pages)
- Workflow includes:
  ```yaml
  uses: peaceiris/actions-gh-pages@v3
  ```

---

## 🧪 Example Test Scenarios

- ✅ Get all books / authors
- ✅ Create new book / author (with Faker)
- ✅ Update and delete by ID
- ❌ Create book with missing fields (expect 400)
- ❌ Get with invalid ID (expect 404)
- ✅ Full request/response logging

---

## 👨‍💻 Author & Credits

- **Author:** *Clement Mukendi*
- **Date:** July 2025
- **Purpose:** API Test Automation for Bookstore (Assessment Project)

---

## ✅ To-Do / Future Enhancements

- [ ] Add Swagger schema validation
- [ ] Add test retry mechanism
- [ ] Integrate test parallelism at scenario level
- [ ] Add Docker support for isolated test execution
