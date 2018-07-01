# Toy Robot Simulator

The application is a simulation of a toy robot moving on a square tabletop. Its movements 
are limited to the dimensions of 5 units x 5 units. The robot is free to move around the table, 
but should be prevented from falling from it (falling means destruction). Any 
movement that might result in the robot falling from the table 
should be prevented, but further valid movement commands must still be allowed. 

## Prerequisites

    - Java version: 1.8.0_162-b12
    - Maven version: Apache Maven 3.5.3
JDK and Maven of the above specified versions (or similar).

## Getting Started

Application Installation
Copy robot.zip into executing folder and unpack it.

Open cmd console, go to /robot folder. 
Run: '**mvn clean install**' to compile the application in \robot folder
Run: **..\robot\target>java -jar robot-1.0-SNAPSHOT.jar** to start the application  

The below text appears in the console:

    Start Robot Play.
    Your first command: >

The Robot Simulator is ready to operate. Now use the following commands.

# Robot commands

Application can read commands of the following formats:

>PLACE X,Y,F

>MOVE

>LEFT

>RIGHT

>REPORT

Command **PLACE** places the toy robot on the table in positions X,Y facing one of the four 
directions: NORTH, SOUTH, EAST or WEST.
The origin (0,0) is considered to be the SOUTH WEST corner of the table.
The first valid command to the robot is **PLACE**. Then any sequence of commands 
may be issued, in any order, including another PLACE command. 
Note: The first executed command has to be **PLACE**. If **PLACE** has not been executed, 
the application will ignore all other commands.

**MOVE** - moves the toy robot by one unit forward in the direction it is facing.

**LEFT** and **RIGHT** - rotate the robot 90 degrees in the specified direction without changing 
the position of the robot.

**REPORT** - returns to the console information about the X,Y and the robot's orientation.

**EXIT** - exits from the Robot Simulator.

##Constraints:

Any command that might lead the robot to falling from the table is ignored by the application.
This also includes the initial placement of the robot. Error message will appear if 
**PLACE** X,Y parameters are out of the range (0-4) or an incorrect format is used 
(e.g., 0,@,NORTH instead of 0,0,NORTH). See below for examples.

## How to work with the application

Use all the letters in UPPERCASE only to operate the robot.
Only **exit** can be entered in lowercase.
Use the correct  X,Y range (0-4) to place the robot.
The only directions that the robot must face are NORTH, SOUTH, EAST and WEST.
Use correct Input and Output, e.g.:
 
 a)

>PLACE 0,0,NORTH

>MOVE

>REPORT

>Output: 0,1,NORTH

 b)

>PLACE 0,0,NORTH

>LEFT

>REPORT

>Output: 0,0,WEST_

 c)
 
>PLACE 1,2,EAST

>MOVE

>MOVE

>LEFT

>MOVE

>REPORT

>Output: 3,3,NORTH

## Running the tests

To run tests only, run '**mvn test**'in \robot folder 
The JUnit tests cover the following basic functions:
- calls for the main method to check if the application can be launched
- checks if the application exits correctly
- validates commands
- checks if the direction the robot faces is correct
- checks if the **PLACE** command is correct, as well as its format
- checks if the robot correctly follows the commands
- checks if the commands that lead the robot to fall are properly ignored

## Author

I. Maltsev
30/06/2018
