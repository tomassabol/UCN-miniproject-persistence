INSERT INTO Storages(Name,Address)
VALUES('garage','a street')
INSERT INTO Storages(Name,Address)
VALUES('shed','b street')
INSERT INTO Storages(Name,Address)
VALUES('van','c street')

INSERT INTO Suppliers(Name,Address,Country,Phone,Email)
VALUES('a', 'Street 1', 'Poland1', '+11111', 'supone@gmail.com')
INSERT INTO Suppliers(Name,Address,Country,Phone,Email)
VALUES('b', 'Street 2', 'Poland2', '+22222', 'suptwo@gmail.com')
INSERT INTO Suppliers(Name,Address,Country,Phone,Email)
VALUES('c', 'Street 3', 'Poland3', '+33333', 'supthree@gmail.com')

INSERT INTO ProductTypes(TypeName)
VALUES ('Guns') 
INSERT INTO ProductTypes(TypeName)
VALUES ('Accessories') 
INSERT INTO ProductTypes(TypeName)
VALUES ('Clothing') 

INSERT INTO CustomerTypes(Name,Discount)
VALUES('typeOne', 10)
INSERT INTO CustomerTypes(Name,Discount)
VALUES('typeTwo', 20)
INSERT INTO CustomerTypes(Name,Discount)
VALUES('typeThree', 30)

INSERT INTO Customers(Name,Address,City,Phone,Email,CustomerTypeId)
VALUES('CustName1', 'CustAddress1', 'CustCity1', '+111', 'cemail1@Gmail.com',1)
INSERT INTO Customers(Name,Address,City,Phone,Email,CustomerTypeId)
VALUES('CustName2', 'CustAddress2', 'CustCity2', '+222', 'cemail2@Gmail.com',2)
INSERT INTO Customers(Name,Address,City,Phone,Email,CustomerTypeId)
VALUES('CustName3', 'CustAddress3', 'CustCity3', '+333', 'cemail3@Gmail.com',3)

INSERT INTO Orders(Date,CustomerId)
VALUES('2011-01-01','1')
INSERT INTO Orders(Date,CustomerId)
VALUES('2022-02-22','2')
INSERT INTO Orders(Date,CustomerId)
VALUES('2033-03-03','3')

INSERT INTO Invoices(date,Price)
VALUES('2011-01-01','1000')
INSERT INTO Invoices(date,Price)
VALUES('2022-02-02','2000')
INSERT INTO Invoices(date,Price)
VALUES('2033-03-03','3000')

INSERT INTO Products(Name,Price,ProductTypeId,SupplierId)
VALUES('name11', CAST('11.1' as Money), 1,1);
INSERT INTO Products(Name,Price,ProductTypeId,SupplierId)
VALUES('name2', CAST('22.2' as Money), 2,2);
INSERT INTO Products(Name,Price,ProductTypeId,SupplierId)
VALUES('name3', CAST('33.3' as Money), 3,3);

INSERT INTO StorageLines(ProductId,Quantity,StorageId)
VALUES(1,1,1);
INSERT INTO StorageLines(ProductId,Quantity,StorageId)
VALUES(2,2,2);
INSERT INTO StorageLines(ProductId,Quantity,StorageId)
VALUES(3,3,3);

INSERT INTO OrderProducts(ProductId,Quantity,OrderId)
VALUES(1,1,1)
INSERT INTO OrderProducts(ProductId,Quantity,OrderId)
VALUES(2,2,2)
INSERT INTO OrderProducts(ProductId,Quantity,OrderId)
VALUES(3,3,3)






