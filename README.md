# Baby Names

An application to help find baby names. Set the seed, choose a gender and the application will show you names pulled from a list of ~40,000. You can add names you like to the list and export them. 

Using the same seed, to users can have the exact same names presented to them, ensuring they'll (mostly) see the same names. Compare the name lists with a diff-checker to see which ones you have in common.


## Compile & Run Source Code

Apache Maven 3.6.3

Java 14.0.1

`mvn clean compile package shade:shade`

`java -classpath target/baby-name-1.0-SNAPSHOT.jar io.javasmithy.AppEntry`

`mvn clean compile javafx:jlink`

## About

Uses Java and JavaFX 14


