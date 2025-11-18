SENG 324 - Software Design Patterns
Term Project - Spring 2024-2025

Student Name: Batuhan Özdemir
Student ID: 210201005

Project Title: City Information and Activity Planner with Design Patterns

----------------------------------------
PROJECT DESCRIPTION
----------------------------------------

This project is a Java application that provides a graphical interface to display and interact with a list of cities. The software is designed using multiple object-oriented design patterns, as described below:

Design Patterns Used:
1. **Singleton Pattern**: Used for the CityRepository class to load cities from a JSON file and provide global access to the city list.
2. **Strategy Pattern**: Enables flexible sorting of cities by Name, Population, or Area.
3. **Iterator Pattern**: Allows filtering cities based on weather condition (Sunny, Rainy, Cloudy, Snowy) with custom iterators.
4. **Observer Pattern**: Updates temperature and weather of cities in real-time through a WeatherProvider running on a separate thread. The GUI charts respond dynamically.
5. **Decorator Pattern**: Adds activity planning capabilities (e.g., MuseumVisit, ShoppingMallVisit, ParkVisit, CityCenterVisit) to selected cities without modifying the base City class.

----------------------------------------
REQUIREMENTS
----------------------------------------

- Java 17 or above
- JavaFX (for GUI)
- GSON (for JSON parsing)
- Maven (for dependency management and building)

----------------------------------------
HOW TO RUN
----------------------------------------

1. **Using the JAR File:**
   - Open terminal or command prompt.
   - Navigate to the folder containing the JAR file.
   - Run the application using:
     ```
     java -jar DesignPatternsHW-1.0-SNAPSHOT.jar
     ```

2. **Building from Source (if needed):**
   - Navigate to the project root.
   - Run:
     ```
     mvn clean package
     ```
   - The fat JAR will be generated in the `target/` folder.

----------------------------------------
FILES INCLUDED IN THIS SUBMISSION
----------------------------------------

- `src/`                → Java source files implementing all features and patterns.
- `resources/cities.json` → Input file containing the city data.
- `pom.xml`             → Maven build configuration with dependencies.
- `DesignPatternsHW-1.0-SNAPSHOT.jar`  → Packaged fat JAR (includes dependencies).
- `UML_Diagram.pdf`     → UML class diagram showing design patterns and structure. It is made on plantuml.com 
- `README.md`          → This file.

----------------------------------------
NOTES
----------------------------------------

- Weather updates occur every 3 seconds and trigger UI chart updates.
- The GUI allows users to sort, filter, and plan activities for each city.
- All requirements from the project specification have been implemented.

