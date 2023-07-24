# OOP_Programming
This repository contains projects I developed with Java while learning Object-Oriented Programming for CSE 102 Computer Programming course. 
Assignments are interconnected and the requirements for the project are clearly stated as follows.

## Assignment 01
Requirements: Write a set of classes according to the following specifications:
### 1. Product
a. Attributes
i. Id: String
ii. Name: String
iii. Quantity: int
iv. Price: double
b. Methods
i. Constructor that takes the ID, name, quantity, and price as
parameters
ii. getId(): String and setId(id: String)
iii. getName(): String and setName(name: String)
iv. getPrice(): double and setPrice(price: double)
v. remaining(): int – returns the current quantity
vi. addToInventory(amount: int): int – increase count by amount
1. if amount is negative, do nothing
2. returns new quantity
vii. purchase(amount: int): double
1. decrease count by amount and return the total price
(amount X price)
2. if amount is negative or greater than quantity, do not
change count and return 0
viii. toString(): String – “Product {name} has {quantity} remaining”
ix. equals(o: Object): boolean – returns true if the passed object is
also a Product and has the same price (within 0.001)
2. FoodProduct – a child of Product
a. Attributes
i. Calories: int
ii. Dairy: boolean
iii. Eggs: boolean
iv. Peanuts: boolean
v. Gluten: boolean
b. Methods
i. Constructor that takes the ID, name, quantity, price, calories,
dairy, peanuts,eggs and gluten as parameters
ii. getCalories(): int and setCalories(calories: int)
iii. containsDairy(): boolean, containsEggs(): boolean,
containsPeanuts(): boolean, and containsGluten(): boolean
3. CleaningProduct – a child of Product
a. Attributes
i. Liquid: boolean
ii. WhereToUse: String
b. Methods
i. Constructor that takes the ID, name, quantity, price, liquid, and
whereToUse as parameters
ii. getWhereToUse () and setWhereToUse (size: String)
iii. isLiquid(): boolean
4. Customer
a. Attributes
i. Name: String
b. Methods
i. Constructor that takes the name as parameter
ii. getName(): String and setName(name: String)
iii. toString(): String – returns the name of the Customer
5. ClubCustomer – a child of Customer
a. Attributes
i. Phone: String
ii. Points: int
b. Methods
i. Constructor that takes the name and Phone as parameters and
sets points to 0
ii. getPhone(): String and setPhone(phone: String)
iii. getPoints(): int
iv. addPoints(points: int): none
1. adds the passed points to the Customer’s point total
2. if passed value is negative, does nothing
v. toString(): String – returns the name of the Customer + “ has “
{points} + “ points”
6. Store
a. Attributes
i. Name: String
ii. Website: String
b. Methods
i. Constructor that takes the name and website
1. Creates an empty list of products
ii. getName(): String and setName(name: String)
iii. getWebsite(): String and setWebsite(website: String)
iv. getInventorySize(): int – returns the number products stored
v. addProduct(product: Product, index: int)
1. Adds the passed product to the index
2. If the index is negative or greater than the size of the list,
adds the product to the end of the list
3. Returns none
vi. addProduct(product: Product)
1. Adds the passed product to the end of the list
2. Returns none
vii. getProduct(index: int): Product
1. returns the Product at the position passed
2. if the index passed is negative or greater than the index of
the last entered product, return null
viii. getProductIndex(p: Product): int
1. returns the index of the Product passed
2. if the Product does not exist, return -1
