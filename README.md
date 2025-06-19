# Stock Management System (SMS)

This repository contains the Java source code for a Stock Management System (SMS) developed using Eclipse IDE and object-oriented programming concepts. The primary goal of this system is to continually track and manage the stock for a shop that sells two main product types: refrigerators and TVs.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Object-Oriented Design](#object-oriented-design)
  - [Classes](#classes)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the Program](#running-the-program)
- [Usage](#usage)
- [Extensions](#extensions)
- [License](#license)

## About

This Stock Management System is designed to provide a robust solution for managing product inventory. It allows users to add new products (refrigerators and TVs), update their stock quantities, discontinue product lines, and view comprehensive product details. The system emphasizes good object-oriented design principles for maintainability and extensibility.

## Features

The program fulfills the following functional specifications:

* **Welcome Display:** Displays a welcome message, the current date and time, and the names of group members.
* **User Input:** Allows the user to input their full name, and generates a user ID based on the input.
* **Product Addition:**
    * Prompts the user to add products with a menu for selecting product types (Refrigerator or TV).
    * Allows users to enter detailed information to create new product instances.
    * Supports polymorphism for creating product objects.
* **Main Menu Operations:** After products are added, displays a menu for various stock management actions:
    * View products.
    * Add stock to existing products.
    * Deduct stock from existing products.
    * Discontinue a product line.
    * Exit the program.
* **Input Validation:** Ensures valid user selections for menu options and product quantities.
* **User Exit:** Displays the generated user ID and name upon program exit.

## Object-Oriented Design

The system is implemented using object-oriented programming concepts, adhering to principles such as Single Responsibility and Open/Closed Principle. Descriptive identifiers are used for classes, methods, variables, and constants.

### Classes

The core classes in this system are:

* **`Product` (Abstract Class)** 
    * **Purpose:** Represents a generic product.
    * **Attributes:** Product name, price, quantity in stock, item number, and product status (active/discontinued).
    * **Methods:**
        * Constructors (default and parameterized).
        * Getter and setter methods for all data fields.
        * `getTotalInventoryValue()`: Calculates product price multiplied by quantity.
        * Methods to add and deduct stock quantities.
        * `toString()`: Overridden to return product information.

* **`Refrigerator` (Subclass of `Product`)** 
    * **Purpose:** Represents a refrigerator product.
    * **Attributes:** Door design, color, and capacity.
    * **Methods:**
        * Parameterized constructor.
        * Getter and setter methods for additional attributes.
        * Method to calculate the value of refrigerator stock.
        * `toString()`: Overridden to return refrigerator-specific information.

* **`TV` (Subclass of `Product`)** 
    * **Purpose:** Represents a television product.
    * **Attributes:** Screen type, resolution, and display size.
    * **Methods:**
        * Parameterized constructor.
        * Getter and setter methods for additional attributes.
        * Method to calculate the value of TV stock.
        * `toString()`: Overridden to return TV-specific information.

* **`UserInfo`** 
    * **Purpose:** Records which user managed the stock.
    * **Attributes:** User name and user ID.
    * **Methods:**
        * `getName()`: Gets the name of the user.
        * Prompts for first name and surname, and checks for spaces.
        * Generates a user ID (e.g., "AWong" for "Ah Peng Wong").
        * Sets default user ID to "guest" if no space is found in the name.

* **`SMSGUI` and `SMSCLI` (Application Class)** 
    * **Purpose:** Controls the flow of the SMS, displays messages, and gets inputs from users.
    * **Attributes:** An array/list of `Product` objects to store product details.
    * **Static Methods:**
        * Gets the maximum number of products the user wishes to store.
        * Displays product contents for selection.
        * Displays the main system menu and validates user choice.
        * Adds stock values to identified products.
        * Deducts stock values from identified products, with quantity restrictions.
        * Allows setting a product's status to discontinued.
        * Executes appropriate methods based on menu choice using a switch-case statement.
        * Allows users to add Refrigerator or TV products, with input validation.
        * Methods for adding Refrigerator and TV products, handling input and creating objects polymorphically.
        * Displays the contents of the products array/list.
        * `main()`: The entry point of the program, fulfilling all requirements.

## Getting Started

These instructions will help you get a copy of the project running on your local machine.

### Prerequisites

* Java Development Kit (JDK)
* Java Runtime Environment (JRE)
* Eclipse IDE (Recommended)

### Running the Program

1.  **Import to Eclipse:** Import the project source code into your IDE.
2.  **Compile and Run:** Run the either the SMSCLI or SMSGUI file to start the application.

## Usage

Upon launching the application, you will be greeted with a welcome message, current date and time, and group member names.

1.  **Enter Your Name:** You will be prompted to enter your full name, which will be used to generate a user ID.
2.  **Add Products (Optional):**
    * The program will ask if you want to add products. Enter a number to proceed or `0` to exit.
    * If adding products, select between "Refrigerator" or "TV" from the menu.
    * Follow the prompts to enter details like name, item number, quantity, price, and specific attributes (e.g., door design for refrigerator, screen type for TV).
3.  **Main Menu:** After adding products (or if you chose not to), a main menu will appear:
    * **1. View products:** Displays details of all products in the system.
    * **2. Add stock:** Allows you to increase the quantity of an existing product.
    * **3. Deduct stock:** Allows you to decrease the quantity of an existing product.
    * **4. Discontinue product:** Sets a product's status to inactive.
    * **0. Exit:** Exits the program and displays your generated user ID and name.
4.  **Interaction:** Enter the corresponding number for your desired action. The system will guide you through the necessary inputs.

## Contributors

Stanley Chew Jun Xian
- Product
- Refrigerator
- TV

Tee Chi Sheng
- WashingMachine
- SMSGUI

Au Chi Le
- Add Stock function
- SMS
- UserInfo

Lim Jin Zhao
- Deduct Stock function
- SMS
- UserInfo

## License

This project was developed as an assignment for the "OOP Programming Assignment" course at Universiti Tunku Abdul Rahman.