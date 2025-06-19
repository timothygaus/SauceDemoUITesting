# Test Coverage Checklist
This document will be used to track the implementation of planned test cases for the SauceDemoUITesting project.

## Login Page
### UI Element Presence
- [x] Verify that the Swag Labs header is present ("Swag Labs")
- [x] Verify that the Username field is present and empty
- [x] Verify that the Password field is present and empty
- [x] Verify that the Login button is present

### Input Functionality
- [x] Verify that the user can enter text into the Username field
- [x] Verify that the user can enter text into the Password field

### Login Functionality - Valid Input
- [x] Verify login is successful with valid credentials
- [x] Verify login via mouse click on the login button
- [x] Verify login via pressing “Enter” key

### Login Functionality - Invalid Input
- [x] Verify login is blocked for invalid credentials
- [x] Verify the error message states the credentials do not match any user
- [x] Verify login is blocked for a locked out user
- [x] Verify the error message states the user is locked out

### Error Messaging & UI Feedback
- [x] Verify that the error message and error icons are not present on page load
- [x] Verify that an error message appears when login fails
- [x] Verify an error icon (“X”) appears next to the username and password fields when login fails
- [x] Verify that clicking the close button on the error message removes the error message and error icons next to input fields


## Inventory Page
### UI Element Presence
- [x] Verify that the menu button is present
- [x] Verify that the Swag Labs header is present ("Swag Labs")
- [x] Verify that the cart icon is present
- [x] Verify that the Products sub header is present ("Products")
- [x] Verify that the sorting dropdown menu is present
- [x] Verify that product cards are present with the following: Product image, product name, description, price, add to cart button

### Sorting Dropdown
- [x] Verify that all sorting options are present
- [x] Verify that when “Name (A to Z)” is selected that the product cards are sorted alphabetically
- [x] Verify that when “Name (Z to A)” is selected that the product cards are sorted reverse alphabetically
- [x] Verify that when “Price (low to high)” is selected that the product cards are sorted by ascending price
- [x] Verify that when “Price (high to low)” is selected that the product cards are sorted by descending price

### Cart Functionality
- [x] Verify that after clicking the “Add to cart” button on a product card, the “Add to cart” button is replaced with the “Remove” button
- [x] Verify that after clicking the “Add to cart” button on a product card, the cart icon is updated
- [x] Verify that after clicking the “Remove” button on a product card, the “Remove” button is replaced with the “Add to cart” button
- [x] Verify that after clicking the “Remove” button on a product card, the cart icon is updated
- [x] Verify that when the cart icon is clicked, the user is navigated to the cart page

### Product Details Navigation
- [x] Verify that each product card is populated with correct information: name, description, price, image
- [x] Verify that clicking the product name navigates the user to the product details page for that product
- [x] Verify that clicking the product image navigates the user to the product details page for that product

### Menu Interaction
- [x] Verify that clicking the menu button opens the sidebar menu
- [x] Verify that the following elements are present; All Items, About, Logout, Reset App State, Close button
- [x] Verify that after clicking the All Items option that the user remains on the Inventory page
- [x] Verify that clicking the Logout option navigates the user to the Login page
- [x] Verify that clicking the Close button closes the sidebar menu

## Product Details Page
### UI Element Presence
- [x] Verify that the menu button is present
- [x] Verify that the Swag Labs header is present
- [x] Verify that the cart icon is present
- [x] Verify that the Back to products button sub header is present
- [x] Verify that the product name is present
- [x] Verify that the product image is present
- [x] Verify that the product description is present
- [x] Verify that the product price is present
- [x] Verify that the Add to cart button is present

### Product information
- [x] Verify that the following product information is correct based on the product selection from the previous page; product name, product image, product description, product price

### Navigation functionality
- [x] Verify that clicking the Back to products button navigates the user to the Inventory page
- [x] Verify that clicking the cart button navigates the user to the cart page

### Cart functionality
- [x] Verify that after clicking the “Add to cart” button on a product card, the “Add to cart” button is replaced with the “Remove” button
- [x] Verify that after clicking the “Add to cart” button on a product card, the cart icon is updated
- [x] Verify that after clicking the “Remove” button on a product card, the “Remove” button is replaced with the “Add to cart” button
- [x] Verify that after clicking the “Remove” button on a product card, the cart icon is updated


## Cart Page
### UI Element Presence
- [x] Verify that the menu button is present
- [x] Verify that the Swag Labs header is present
- [x] Verify that the cart icon is present
- [x] Verify that the Your Cart sub header is present
- [x] Verify that the QTY label is present
- [x] Verify that the Description label is present
- [x] Verify the presence of items based on previous conditions
- [x] Verify that the following product information is present: product name, product quantity, product description, product price, remove button
- [x] Verify that the Continue Shopping button is present
- [x] Verify that the Checkout button is present

### Navigation Functionality
- [x] Verify that clicking the Continue Shopping button navigates the user to the Inventory page
- [x] Verify that clicking the Checkout button navigates the user to the Checkout Step One page
- [x] Verify that clicking any product’s name navigates the user to the product page for that product

### Product Information
- [x] Verify that, for each product present, the following are correct, based on the product’s name; product description, product price
- [x] Verify that the quantity is correct based on previous selections

### Cart Functionality
- [x] If at least one item is present, verify that the number displayed on the cart button’s badge matches the number of items displayed on the cart page
- [x] If no items are present, verify that the cart button does not have a number badge displayed
- [x] Verify that clicking the remove button on a product cart removes that product from the cart page and the number badge on the cart button is decremented by one


## Checkout Step One Page
### UI Functionality
- [ ] Verify that the menu button is present
- [ ] Verify that the Swag Labs header is present
- [ ] Verify that the cart icon is present
- [ ] Verify that the Checkout: Your Information sub header is present
- [ ] Verify that the First Name field is present
- [ ] Verify that the Last Name field is present
- [ ] Verify that the Zip/Postal Code field is present
- [ ] Verify that the Cancel button is present
- [ ] Verify that the Continue button is present

### Input Functionality
- [ ] Verify that the user can enter text into the First Name field
- [ ] Verify that the user can enter text into the Last Name field
- [ ] Verify that the user can enter text into the Zip/Postal Code field

### Navigation Functionality
- [ ] Verify that clicking the Cancel button navigates the user to the cart page
- [ ] Verify that if the continue button is clicked and the First Name field is empty, the user remains on the current page and an error is displayed stating that the First Name field is required
- [ ] Verify that if the continue button is clicked and the Last Name field is empty, the user remains on the current page and an error is displayed stating that the Last Name field is required
- [ ] Verify that if the continue button is clicked and the Zip/Postal Code field is empty, the user remains on the current page and an error is displayed stating that the Postal Code is required
- [ ] Verify that error icons appear next to all input fields when an error is displayed
- [ ] Verify that clicking the close button (x) on the error message closes the error and removes the error icons from the input fields
- [ ] Verify that clicking the Continue button when all input fields are populated with text navigates the user to the Checkout Step Two page


## Checkout Step Two Page
### UI Element Presence
- [ ] Verify that the menu button is present
- [ ] Verify that the Swag Labs header is present
- [ ] Verify that the cart icon is present
- [ ] Verify that the Checkout: Overview sub header is present
- [ ] Verify that the QTY label is present
- [ ] Verify that the Description label is present
- [ ] Verify the presence of items based on previous conditions
- [ ] Verify that the Payment Information label is present and payment information is present
- [ ] Verify that the Shipping Information label is present and shipping information is present
- [ ] Verify that the Price Total label is present, and item total, tax, and total are present
- [ ] Verify that the Cancel button is present
- [ ] Verify that the Finish button is present

### Navigation Functionality
- [ ] Verify that clicking the Cancel button navigates the user to the Inventory page
- [ ] Verify that clicking the Checkout button navigates the user to the Checkout Complete page

### Product Information
- [ ] Verify that, for each product present, the following are correct, based on the product’s name; product description, product price
- [ ] Verify that the quantity is correct based on previous selections
- [ ] Verify that the user can navigate to the product page for any item by clicking on the product’s name

### Checkout Information
- [ ] Verify that the payment information matches what was previously entered
- [ ] Verify that the shipping information matches what was previously entered
- [ ] Verify that the price, tax, and total information is correct

## Checkout Complete Page
### UI Element Presence
- [ ] Verify that the menu button is present
- [ ] Verify that the Swag Labs header is present
- [ ] Verify that the cart icon is present
- [ ] Verify that the Checkout: Complete! sub header is present
- [ ] Verify that the checkout complete image is present
- [ ] Verify that the Complete header is present
- [ ] Verify that the Complete description is present
- [ ] Verify that the Back Home button is present

### Navigation Functionality
- [ ] Verify that clicking the Back Home button navigates the user to the Inventory page

### Cart Functionality
- [ ] Verify that the cart number badge is removed from the cart button

## End-to-End Test Cases
### Recovery from failed login
- [ ] E2E test implemented
#### Preconditions:  
- User is on the login page  

#### Test Steps:
1. Verify that login fails for invalid credentials
2. Verify that clicking the close button on the error message removes the error message and all error icons next to the input fields
3. Verify that login is successful for valid credentials

#### Postconditions:
- User is on the inventory page  

### Add item to cart
- [ ] E2E test implemented
#### Preconditions: 
- User in on the inventory page
- The cart is empty

#### Test Steps:  
1. Verify that the user is able to add any item to the cart
2. Verify that the user is able to navigate to the cart page
3. Verify that previously selected item is present in the cart
4. Verify that all product information is correct

#### Postconditions:
- The cart number badge is displayed with the number “1”

### Add multiple items to cart
- [ ] E2E test implemented
#### Preconditions:
- User is on the inventory page
- The cart is empty

#### Test Steps:
1. Verify that the user is able to add any item to the cart
2. Verify that the item is present in the cart
3. Verify that the user is able add to a second item to the cart
4. Verify that the second item is in the cart
5. Verify that all product information is correct

#### Postconditions:
- The cart number badge is displayed with the number “2”

### Add and Remove item from cart
- [ ] E2E test implemented
#### Preconditions:
- User is on the inventory page

#### Test Steps:
1. Verify that the user is able to add any item to the cart
2. Verify that the user is able to navigate to the cart page
3. Verify that the user is able to remove the item from the cart
4. Verify that the user is able to navigate back to the inventory page

#### Postconditions:
- The previously selected item has an Add to cart button on its product cart
- The cart number badge is not present

### Full purchase flow
- [ ] E2E test implemented
#### Preconditions:
- User is on the login page

#### Test Steps:
1. Verify that the user is able to login with valid credentials
2. Verify that the user is able to add any number of items to the cart
3. Verify that the user is able to navigate to the cart page
4. Verify that all previously selected items are present on the cart page
5. Verify that the user is able to navigate to the Checkout Step One page
6. Verify that the user is able to navigate to the Checkout Step Two page after entering information into each input field
7. Verify that the cart item information is correct on the Checkout Step Two Page
8. Verify that Payment and Shipping information is as expected
9. Verify that the Price information is correct, assuming an 8% tax
10. Verify that the user is able to navigate to the Checkout Complete page with confirmation that the checkout was successful

#### Postconditions:
- The cart number badge is not present

### Abandoning Checkout
- [ ] E2E test implemented
#### Preconditions:
- User is on the cart page
- At least one item is present in the cart

#### Test Steps:
1. Verify that the user is able to navigate to the Checkout Step One page
2. Verify that the user is able to navigate back to the cart page by clicking the cancel button

#### Postconditions:
- The user is on the cart page
- The previously selected item(s) are still in the cart
- The cart number badge reflects the number of items in the cart

### Abandon Checkout on Step Two
- [ ] E2E test implemented
#### Preconditions:
- User is on the cart page
- At least one item is present in the cart

#### Test Steps:
1. Verify that the user is able to navigate to the Checkout Step One page
2. Verify that the user is able to navigate to the Checkout Step 2 page after entering information into each input field
3. Verify that the user is able to navigate to the Inventory page by clicking the cancel button

#### Postconditions:
- The user is on the cart page
- The previously selected items are still in the cart
- The cart number badge reflects the number of items in the cart
