CREATE TABLE Categories
(
    CategoryID INT PRIMARY KEY,
    Name VARCHAR(255) NULL
);

CREATE TABLE Products
(
    ProductID INT PRIMARY KEY,
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
    UserID INT PRIMARY KEY,
    Name VARCHAR(255) NULL,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(255),
    PasswordHash VARCHAR(255),
    Role ENUM('CLIENT', 'ADMINISTRATOR')
);

CREATE TABLE Cart
(
    CartID INT PRIMARY KEY,
    UserID INT NOT NULL
);

CREATE TABLE CartItems
(
    CartItemID INT PRIMARY KEY,
    CartID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NULL
);

CREATE TABLE Orders
(
    OrderID INT PRIMARY KEY,
    CreatedAt datetime NULL,
    DeliveryAddress VARCHAR(255),
    ContactPhone VARCHAR(255),
    DeliveryMethod VARCHAR(255),
    Status ENUM('NEW', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELED'),
    UpdatedAt datetime NULL
);

CREATE TABLE Favorites
(
    FavoriteID INT PRIMARY KEY,
    UserID INT NOT NULL,
    ProductID INT NOT NULL
)

