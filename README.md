# online-bookshop
This is a pet project on Java, Spring for my university course. The goal of the project is to implement a back-end system that connects to a PostgreSQL database and allows testing of API endpoints via Postman or Swagger UI

# Project Structure
**Back-end**: Spring

**UI for Endpoint Testing**: Swagger UI

**Database**: PostgreSQL

**Swagger Access**: [localhost swagger link](http://localhost:8080/swagger-ui/index.html#/)

# **Project Stage: Prototyping and planning**
Planned Features:
- Book browsing and filtering by genre, author, price
- Cart management
- Order processing and status tracking
- Reviews and ratings for books

# Bussines logic
## Short summary of the project
The Online Bookshop allows users to browse, review, and purchase books. Each book has detailed information including title, author, genre, language, price, availability, page number, average rating, and user reviews.

Registered users can create an account, log in, browse the catalog, and add books to their cart. They can place orders specifying shipping details, quantities, and receive order status updates. Users can also leave reviews with ratings for purchased books, influencing the average rating displayed.

The system manages relationships between users, books, orders, and reviews, ensuring accurate tracking of purchases, reviews, and book availability.
## Enities
- Book
  - Title
  - Author
  - Genre
  - Price
  - Language
  - Page Number
  - Availability
  - Average Rating
  - Reviews
- User
  - Id
  - Id Person
  - User Status
  - Email
  - Registration Date
- Order
  - Id Order
  - Id Cart (if multiple orders)
  - Quanity
  - Ship Address
  - Order Date
  - Order Status
  - Total Price
- Persona
  - First Name
  - Middle Name
  - Last Name
  - Address
  - Telephone Number
  - Birth Date
- Review
  - Id User
  - Id Book
  - Review Message
  - Review Rating (1-5)
 

