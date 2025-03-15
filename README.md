# hotel_manager
Console hotel management program created in Java

#### Details:
- Maven based project
- Tested with JUnit - 94% of Code Coverage reported by SonarQube
- Fully documented with JavaDoc
- Object-Oriented Program


1. Start:
- To start the program, execute the following commands:

```shell
mvn clean package
```
```shell
java -jar main/target/main-1.0.jar
```




2. Information about the program
- The program is a console version of hotel management
- The commands that can be used are:
  - help - displays all commands
  - list - displays information about rooms and guests (whether the room is available, and in the case of a guest's
  check-in date and planned check-out date
  - prices - displays the room number along with the price per night
  - view - displays detailed information about a given room (requires the room number)
  - checkin - allows the guest to check in (requires the room number, guest data, check-in date and
  length of stay
  - checkout - allows the guest to check out of the room (requires the room number and
  displays the amount that the guest must pay for the stay)
  - save - saves the current hotel state to a file (the saved hotel state will be loaded when
  the program is restarted)
  - exit - exits the program

3. The program documentation is located in the javadoc directory
- to open it, open the site subdirectory, and then double-click on the index.html file
- the browser will start where the program documentation will be