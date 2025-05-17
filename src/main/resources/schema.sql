CREATE TABLE Categories
(
    CategoryID INT,
    Name VARCHAR(255) NULL
);

CREATE TABLE Products
(
    ProductID INT NOT NULL,
    Name VARCHAR(255) NULL,
    CategoryID INT NULL,
    DiscountPrice DOUBLE NULL,
    Price DOUBLE NULL,
    CreatedAt datetime NULL,
    UpdatedAt datetime NULL,
    `Description` VARCHAR(255) NULL,
    ImageURL VARCHAR(255) NULL
);

CREATE TABLE Users
(
    UserID INT NOT NULL,
    Name VARCHAR(255) NULL,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(255),
    PasswordHash VARCHAR(255),
    Role ENUM('CLIENT', 'ADMINISTRATOR')
);

CREATE TABLE Carts
(
    CartId INT NOT NULL,
    UserId INT NOT NULL
);

CREATE TABLE CartItems
(
    CartItemId INT NOT NULL,
    CartId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NULL
);

CREATE TABLE Orders
(
    OrderId INT NOT NULL,
    CreatedAt datetime NULL,
    DeliveryAddress VARCHAR(255),
    ContactPhone VARCHAR(255),
    DeliveryMethod VARCHAR(255),
    Status ENUM('NEW', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELED'),
    UpdatedAt datetime NULL
);

