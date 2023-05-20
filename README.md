# [Simple Banking System (Java)](https://hyperskill.org/projects/93?category=2&track=9)

## Table of Contents
- [Description](#description)
- [Features](#features)
- [Usage](#usage)
- [Feedback](#feedback)
- [Contributing](#contributing)
- [Dependencies](#dependencies)
- [Contact Information](#contact-information)

## Description
This is a simple banking system project implemented in Java. The project aims to provide a basic understanding of how a banking system works, along with practical experience in working with databases and SQL. It also introduces fundamental concepts of object-oriented programming (OOP) through the use of classes.

## Features
The Simple Banking System offers the following features:

1. Account Creation: Users can create a new account, which includes generating a unique card number and PIN.
2. Account Login: Users can log into their existing accounts using their card number and PIN.
3. Balance Inquiry: Logged-in users can check their account balance.
4. Income Addition: Logged-in users can add income to their account.
5. Fund Transfer: Logged-in users can transfer funds from their account to another account.
6. Account Closure: Logged-in users can close their account.

## Usage
### To run the Simple Banking System project, follow these steps:

1. Set up the database: The project uses an SQLite database. Ensure that the SQLite JDBC driver is installed.
2. Download the project files: Obtain the project files from the provided link.
3. Compile the project: Compile the Java classes using a Java compiler.
4. Run the project: Execute the compiled Main class, providing the optional command-line argument -fileName to specify the database file name. If no file name is provided, the default file name will be used.
5. Follow the on-screen instructions: The program will display a menu with different options. Enter the corresponding number to perform the desired action.

### To create a JAR file for the Simple Banking System project, perform the following steps:
1. Compile the project: Compile the Java classes using a Java compiler.
2. Create a manifest file: Create a text file named manifest.txt and add the following content:
    ```shell
    Manifest-Version: 1.0
    Main-Class: banking.SimpleBankingSystem
    ```
3. Create the JAR file: Open a terminal or command prompt, navigate to the directory containing the compiled classes and the manifest.txt file, and execute the following command:
    ```shell
    jar cvfm SimpleBankingSystem.jar manifest.txt *.class
    ```
4. The SimpleBankingSystem.jar file will be created in the current directory.

### To run the JAR file, perform the following steps:

1. Run the JAR file: Enter the following command to execute the JAR file:
    ```shell
    java -jar Project5SimpleBankingSystem-1.0-SNAPSHOT.jar
    ```
2. Follow the on-screen instructions: The program will display a menu with different options. Enter the corresponding number to perform the desired action.

## Feedback
During this project, I learned about the functioning of a banking system and gained knowledge about SQL and databases. Understanding how the Luhn algorithm works was particularly valuable in preventing errors when entering card numbers. Additionally, I grasped basic object-oriented programming concepts, such as working with classes.

While working on the project, I encountered a few difficulties. Setting up and connecting to the database required some effort, but I was able to overcome it by studying the documentation and seeking guidance from online resources. Designing the system to handle various banking operations and ensuring data security also posed challenges, but careful planning and research helped me overcome these obstacles.

Overall, this project provided me with a practical understanding of banking systems and allowed me to develop my skills in working with databases, SQL, and object-oriented programming. It was a valuable learning experience that enhanced my knowledge and problem-solving abilities.

## Contributing
Contributions to the Simple Banking System project are welcome. If you would like to contribute, please follow these guidelines:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your modifications.
4. Test your changes to ensure they work as intended.
5. Commit your changes and push them to your forked repository.
6. Submit a pull request, describing the changes you made and their purpose.

## Dependencies
The Simple Banking System project has the following dependencies:

1. Java Development Kit (JDK): Ensure that you have a compatible JDK installed. Make sure you have JDK version 17.0 or higher installed. You can download the latest JDK from the [official Oracle website](https://www.oracle.com/java/technologies/downloads/#java17).
2. SQLite JDBC Driver: Obtain the driver from a reliable source and include it in the project's classpath. The recommended version is 3.41.2. You can download the JDBC driver from the [SQLite JDBC Driver website](https://github.com/xerial/sqlite-jdbc/releases) or include it as a Maven/Gradle dependency.

## Contact Information
If you have any questions, suggestions, or feedback regarding the Cinema Room Manager project, please contact:
- Project Developer: ```Daulet Toganbayev```
- Email: ```daulet.toganbayev@gmail.com```

Feel free to reach out with any inquiries or contributions. Your input is highly appreciated!