drop table if exists StorageLines;
drop table if exists Storages;
drop table if exists OrderProducts;
drop table if exists Products;
drop table if exists Suppliers;
drop table if exists ProductTypes;
drop table if exists Invoices;
drop table if exists Orders;
drop table if exists Customers;
drop table if exists CustomerTypes;



create table Suppliers(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Address varchar(100),
    Country varchar(100),
    Phone varchar(15),
    Email varchar(30)
);

create table ProductTypes(
    Id int identity(1,1) primary key,
    TypeName varchar(100)
);

create table Storages(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Address varchar(100)
);

create table CustomerTypes(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Discount int
);

create table Customers(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Address varchar(100),
    City varchar(100),
    Phone varchar(15),
    Email varchar(30),
    CustomerTypeId int,
    foreign key (CustomerTypeId) references CustomerTypes(Id)
);

create table Orders(
    Id int identity(1,1) primary key,
    [Date] date,
    CustomerId int,
    foreign key (CustomerId) references Customers(Id),
);

CREATE TABLE Invoices(
    Id int IDENTITY(1,1) primary key,
    FOREIGN key (Id) REFERENCES Orders(Id),
    [Date] date,
    Price SMALLMONEY,
);

create table Products(
    Id int identity(1,1) primary key,
    Name varchar(100),
    Price smallmoney,
    ProductTypeId int,
    foreign key (ProductTypeId) references ProductTypes(Id),
    SupplierId int,
    foreign key (SupplierId) references Suppliers(Id)
);

create table OrderProducts(
    ProductId int,
    foreign key (ProductId) references Products(Id),
    Quantity int,
    OrderId int,
    foreign key (OrderId) references Orders(Id)
);

create table StorageLines(
    Id int identity(1,1),
    ProductId int,
    foreign key (ProductId) references Products(Id),
    Quantity int,
    StorageId int,
    foreign key (StorageId) references Storages(Id)
);
