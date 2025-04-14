# Qadenz

Qadenz is a robust test automation library written in Java that wraps [Selenium](https://www.selenium.dev/) for browser
automation, [TestNG](https://testng.org) for suite management, and [Hamcrest](http://hamcrest.org/JavaHamcrest/) for
validations. Using a mildly opinionated approach, Qadenz solves the common issues encountered while implementing the
wrapper layer between the tests and accompanying UI models, and the underlying frameworks (like Selenium and TestNG).

The goal of Qadenz is to take the heavy lifting out of developing a test automation project, and allow teams to focus
instead on rapid scripting of tests. This is accomplished by using an easy-to-learn API that encourages simple to read
tests without using Gherkin/BDD-based tools that add unnecessary complexity and maintenance overhead to the development
workload.

Core Features of Qadenz:

- Boilerplate test setup and configuration is built and ready to use
- Selenium element interaction functionality is wrapped in a simple-to-use API
- A unique conditions-based approach to validations
- Custom built-in HTML reporting that provides detailed results data along with integrated screenshots

Qadenz is also extensible and stands ready to support custom functionality based on the needs of the tests to be
automated. The design patterns that Qadenz provides can quickly and easily be followed for seamless integration of
team-specific features.

Read more about the Qadenz [philosophy of use](https://qadenz.dev/overview/philosophy-of-use/).

# License

The Qadenz Library is made available under
the [PolyForm Internal Use License](https://polyformproject.org/licenses/internal-use/1.0.0/) as a "Source Available"
library. Teams are welcome to use Qadenz to power their internally managed test automation projects and modify as
needed, but are prohibited from re-distributing the library or marketing/selling Qadenz (and derivative works of Qadenz)
as a product for their customers. Please see the [FAQ](https://qadenz.dev/faq/) for more detail on
licensing.

# Getting Started

Getting started with Qadenz is as simple as importing the dependency from Maven. On the `pom.xml`, add a new entry to
the `<dependencies>` section:

```xml
<dependency>
    <groupId>dev.qadenz</groupId>
    <artifactId>qadenz</artifactId>
    <version>2.1.4</version>
</dependency>
```

Documentation for the library is available at [qadenz.dev](http://qadenz.dev).

Happy automating :)

... oh btw, it's pronounced "cadence"
