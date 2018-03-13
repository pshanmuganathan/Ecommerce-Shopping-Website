# WebDevelopment
# BestDeal:
The intent is to build servlet-based web application that will allow
customers to place orders online from BestDeal website. 
# Product categories:
1. Smart Phones
2. Tablets
3. Laptop
4. TV
The following is the high-level description for BestDeal website:
* The intent is to build servlet-based web application that will allow
customers to place orders online from BestDeal website
* The store has a StoreManager, Customers, and Salesmen
* The retailers sells different types of products
* The StoreManager can Add/Delete/Update products
* Salesman can create Customer accounts and can
Add/Delete/Update customers’ orders
* Every product might have accessories that could be bought
separately
* Retailer offers warranty that can be purchased by the customer
for every console
* The customer must be able to create an account online
* The customer must be able to place an order online, check the
status of the order, or cancel the order.
* The customer will pay using a credit card
* Some of the products may have retailer special-discounts
* Some of the products may have manufacturer rebates
* Customer shall be able to shop online to buy one or multiple items
in the same session from the BestDeal online retailer.
* In the same session, the customer must be able to add or remove
items from the shopping cart
* When the customer chooses to check out:
    * The customer will enter personal information (Name,
Address, Credit Card, etc.)
    * The customer will be provided with a confirmation number
and a delivery date (2 weeks after the order date) that the
customer can use to cancel an order at a later timer, though
it must be 5 business days before the delivery date.

## Databases
* MySQL
* MongoDB- For stories reviews on Each product

## DataAnalytics
* Pulled tweets posted BestBuy to match products with the website I developed to show the trending products. Used Python script to extract the feeds from twitter.(Twitter API)

## Added Functionalities
* Utilized SAX parser to parse XML document holding the products to be displyed on the first page of the Shopping website. This allows the Admin to just update the XML every now and then, and all the data for the product will be extracted from the backend and reflect on the Home Page.
* Used AJAX search utility to facilitate instant search on the products available.
