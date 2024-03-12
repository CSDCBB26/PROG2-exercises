# Documentation and Discussion

## Maven 

### surefire plugin

#### How do you install it? 
In the pom.xml file, add the following code under `<plugins></plugins>`:
```xml
<!-- https://www.baeldung.com/maven-surefire-plugin -->
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <!-- By default, surefire automatically includes all test classes whose name starts with Test, or ends with Test, Tests or TestCase.-->
            </plugin>
```

#### Where do you find the XML Report

Under target/surefire-reports, you will find the XML report.

## Test Cases