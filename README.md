# CAS-Project

## Overview

This was a Java project that I completed as the coursework for my Object Oriented Programming module.

The goal was to create a CAS system for a hardware shop. The system supports a multi-user login page. Functionality as a customer and as a administrator.

It should be noted that this project uses Java Swing for the GUI. Additionally, the logic and the design for the project can be ound in the class diagram [here](ClassDiagram.jpg).

The system allows for the customer to buy his goods using paypal and credit card. The products are not deleted when the quantity reaches 0. The entry is maintained and is only available to the administrator. Additionally, the user cannot buy larger quantities than what is avaialable. Additionally, system checks the user's input if it is correct. in case it is not, the transaction cannot move forward.

Additionally, search functionalities has also been implemented to both the user and the administrator.

The project has been written in Java version 1.8.

## Running the code

In this repository there is a Jar file that contains the project, it caan be [found here](cas.jar).

it should be noted that for the jar file to work, the following files need to be in the same folder:
* ActivityLog.txt
* Stock.txt
* UserAccounts.txt

In the case that you want to run the software from the JAVA files, you should download the repository. Build the files and run the LoginGUI.java file, it can be found [here](src/gui/LoginGUI.java).

The project was completed using the Eclipse IDE.
