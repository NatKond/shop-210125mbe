INSERT INTO Categories (CategoryID, Name) VALUES (1, 'Garden Tools');
INSERT INTO Categories (CategoryID, Name) VALUES (2, 'Outdoor Power Equipment');
INSERT INTO Categories (CategoryID, Name) VALUES (3, 'Watering Equipment');
INSERT INTO Categories (CategoryID, Name) VALUES (4,'Domestic Chemicals');

INSERT INTO Products (ProductID, CategoryID, DiscountPrice, Price, CreatedAt, UpdatedAt, Description, ImageURL, Name)
VALUES
    (1, 1, 15.99, 19.99, NOW(), NOW(), 'Sturdy garden trowel with wooden handle', 'https://example.com/images/garden_trowel.jpg', 'Garden Trowel'),
    (2, 1, 0.0, 39.99, NOW(), NOW(), 'Heavy-duty pruning shears for trimming bushes and small branches', 'https://example.com/images/pruning_shears.jpg', 'Pruning Shears'),
    (3, 2, 299.99, 349.99, NOW(), NOW(), 'Gas-powered lawn mower with 21-inch cutting deck', 'https://example.com/images/lawn_mower.jpg', 'Gas Lawn Mower'),
    (4, 3, 199.99, 849.99, NOW(), NOW(), 'Gas-powered lawn mower with 21-inch cutting deck', 'https://example.com/images/lawn_mower.jpg', 'New Gas Lawn Mower');

INSERT INTO Users (UserID, Name, Email, PhoneNumber, PasswordHash, Role) VALUES
(1, 'Alice Johnson', 'alice.johnson@example.com', '+1234567890', 'hashed_password_1', 'CLIENT'),
(2, 'Bob Smith', 'bob.smith@example.com', '+1987654321', 'hashed_password_2', 'ADMINISTRATOR'),
(3, 'Carol Lee', 'carol.lee@example.com', '+1122334455', 'hashed_password_3', 'CLIENT'),
(4, 'David Brown', 'david.brown@example.com', '+1222333444', 'hashed_password_4', 'CLIENT');

INSERT INTO Carts (CartId, UserId) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

INSERT INTO CartItems (CartItemId, CartId, ProductId, Quantity) VALUES
(1, 1, 3, 2),
(2, 2, 4, 1),
(3, 3, 5, 3),
(4, 4, 3, 1);

INSERT INTO Orders (OrderId, CreatedAt, DeliveryAddress, ContactPhone, DeliveryMethod, Status, UpdatedAt) VALUES
(1, '2025-05-15 10:15:00', '123 Main St, Springfield', '+1234567890', 'Standard Shipping', 'NEW', '2025-05-15 10:15:00'),
(2, '2025-05-14 09:30:00', '456 Oak Ave, Centerville', '+1987654321', 'Express Delivery', 'PROCESSING', '2025-05-14 12:00:00'),
(3, '2025-05-13 14:45:00', '789 Pine Rd, Lakeview', '+1122334455', 'Standard Shipping', 'SHIPPED', '2025-05-14 08:00:00'),
(4, '2025-05-12 08:00:00', '321 Birch Ln, Brookfield', '+1222333444', 'Courier', 'DELIVERED', '2025-05-15 16:30:00'),
(5, '2025-05-11 11:20:00', '654 Cedar St, Maplewood', '+1555666777', 'Standard Shipping', 'CANCELED', '2025-05-11 12:00:00');