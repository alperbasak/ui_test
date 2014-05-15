## Locate UI Test ##

**Locate UI Test** contains functional tests for Locate web application.

### Basic Architecture ###
- **Gradle** ([http://www.gradle.org/](http://www.gradle.org/ "Gradle")) is used for project build automation. It utilizes a groovy based DSL and provides functionality similar to ant and maven combined.
- Functional tests are written in **Geb** browser automation tool ([http://www.gebish.org/](http://www.gebish.org/ "Geb")) 
- Tests are written as specifications using **Spock** test framework ([https://code.google.com/p/spock/](https://code.google.com/p/spock/ "Spock")) 
- Since spock is groovy based and Geb is using spock, tests are written in **Groovy** language ([http://groovy.codehaus.org/](http://groovy.codehaus.org/ "Groovy")) 
- **Hamcrest** and Spock Hamcrest helpers are used for BDD style matchers ([https://code.google.com/p/hamcrest/](https://code.google.com/p/hamcrest/ "Hamcrest")) 
- **spock-reports** project is used to generate customized geb / spock reports in addition to default reports ([https://github.com/renatoathaydes/spock-reports](https://github.com/renatoathaydes/spock-reports "spock-reports"))
- Tests are executed in real browsers by using **Selenium** web driver api and browser specific drivers ([http://docs.seleniumhq.org/](http://docs.seleniumhq.org/ "Selenium"))

### File And Directory Structure ###

#### Main Files ####
- **build.gradle**: Build file of project
- **gradle.properties**: System settings and configuration for Gradle (proxy settings, jvm parameters, etc.)
- **settings.gradle**: Project settings used by Gradle
- **gradlew.bat & gradlew**: graddle wrapper scripts to be used for reliable and reproducible builds

#### Main Directories ####
- **driver** : This directory contains the web driver executables used by selenium. For each driver defined in ***ext.driverPaths*** property of build.gradle, there should be a driver here
- **gradle** : gradle wrapper directory (required by gradlew command script)
- **.gradle** : gradle specific internal files (dependency caches, task artifacts, etc.)
- **.git** : git repository specific files (do not delete or edit)
- **build** : default build directory for classes, reports, artifacts, etc
- **libs** : custom jars and libraries used by application
- **src/test** : root directory for unit test files if there are any (corresponds to **test** sourceSet in build.gradle)
- **src/integrationTest** : root directory for integration test files if there are any (corresponds to **integrationTest** sourceSet in build.gradle)
- **src/functionalTest** : root directory for functional test files if there are any (corresponds to **functionalTest** sourceSet in build.gradle)

#### Organization Of Test Files ####
Functional test files are mainly organized under two groups. First group contains Page files (under **pages** package) which define details / implementation of specific pages or navigational states (**PageObject** pattern). Actual tests (under **specs** package) are written as specifications and they utilize the Page definitions written before. With this separation, pages contain mostly static layout and content definitions, while the specs contain codes specific to the behavior.

Locate loads main page once and loads sections of main page with AJAX. So, most page definitions are not actually different pages but, they represent navigational states in the same main page. It is not necessary to have one to one mapping with a spec and a page. A spec can use more than one page definition and similarly, a page can be included in different test specifications.

### Test Configuration ###
- ***src/functionalTest/resources/GebConfig.groovy*** : Geb framework environment and selenium driver configuration. 
	- There are basically two types of configurations. 
	- First configuration group is environment (driver) independent and are given at the root level of config file. 
	- As the second group, there are environment (driver) definitions and driver specific configurations. They are defined under environments section.
- ***src/functionalTest/resources/TestParams.xls*** : Main test parameter file to provide data for parameterized tests. 
	- Each parameter is defined in a new row. 
	- The first column in each row contains the parameter name. It should contain spec name (or part of it) to allow grouping of parameters.
	- If the parameter is single valued (not used as part of a data set), second column is its value. 
	- If the parameter name ends with a '[', then the parameter is multi-valued and is part of a data set. In this case, the second column contains the number of elements for the data set. The following columns should contain the elements of the data set. 
	- Empty strings should be given as "" otherwise the null and empty strings can not be distinguished.  

### Basic Usage ###
This project does not contain any IDE specific project files.
A simple code editor which understands groovy syntax (jedit, sublime text, etc.) can be used for writing tests. IDE specific plugins can be applied to build file and can be used to generate project files specific to IDEs (see [http://www.gradle.org/docs/current/userguide/eclipse_plugin.html](http://www.gradle.org/docs/current/userguide/eclipse_plugin.html "eclipse_plugin") as an example).

All operations can be done using gradle (or gradlew, if you are using wrapper) from command line.

Run ***gradlew build*** from command line. Gradle will download gradle runtime and required dependencies (if required) and run the tests defined in **build.gradle**. 

To see available gradle tasks, run ***gradlew tasks***.

