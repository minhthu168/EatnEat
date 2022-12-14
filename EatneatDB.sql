USE [master]
GO
/****** Object:  Database [EatneatDB]    Script Date: 8/17/2022 11:39:30 AM ******/
CREATE DATABASE [EatneatDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'EatneatDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\EatneatDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'EatneatDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\EatneatDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [EatneatDB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [EatneatDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [EatneatDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [EatneatDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [EatneatDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [EatneatDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [EatneatDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [EatneatDB] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [EatneatDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [EatneatDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [EatneatDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [EatneatDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [EatneatDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [EatneatDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [EatneatDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [EatneatDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [EatneatDB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [EatneatDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [EatneatDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [EatneatDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [EatneatDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [EatneatDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [EatneatDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [EatneatDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [EatneatDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [EatneatDB] SET  MULTI_USER 
GO
ALTER DATABASE [EatneatDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [EatneatDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [EatneatDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [EatneatDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [EatneatDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [EatneatDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [EatneatDB] SET QUERY_STORE = OFF
GO
USE [EatneatDB]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[Empid] [varchar](10) NOT NULL,
	[Empname] [varchar](80) NOT NULL,
	[phone] [varchar](15) NULL,
	[Role] [varchar](50) NULL,
	[Status] [varchar](50) NULL,
	[Email] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Empid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[Viewemployee]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[Viewemployee]
AS
SELECT        Empid, Empname, phone
FROM            dbo.Employee
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Orderid] [int] IDENTITY(1,1) NOT NULL,
	[Empid] [varchar](10) NULL,
	[quantity] [int] NOT NULL,
	[Totalamount] [int] NOT NULL,
	[orderdate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[Orderid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[Vieworders]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[Vieworders]
AS
SELECT        Orderid, Empid, quantity, Totalamount, orderdate
FROM            dbo.Orders
GO
/****** Object:  Table [dbo].[Menutype]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Menutype](
	[menutypeid] [int] IDENTITY(1,1) NOT NULL,
	[type] [varchar](50) NOT NULL,
	[typeimg] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[menutypeid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Food]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Food](
	[Foodid] [int] IDENTITY(1,1) NOT NULL,
	[Foodname] [varchar](100) NOT NULL,
	[Price] [int] NOT NULL,
	[menutypeid] [int] NULL,
	[image] [varchar](200) NULL,
	[category] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[Foodid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[Viewfood]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[Viewfood]
AS
SELECT        dbo.Food.Foodid, dbo.Food.Foodname, dbo.Food.Price, dbo.Food.menutypeid, dbo.Menutype.type, dbo.Food.image, dbo.Food.category
FROM            dbo.Food INNER JOIN
                         dbo.Menutype ON dbo.Food.menutypeid = dbo.Menutype.menutypeid
GO
/****** Object:  Table [dbo].[Orderdetail]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orderdetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Orderid] [int] NULL,
	[Foodid] [int] NULL,
	[price] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[Totalamount] [int] NOT NULL,
	[date] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[Vieworderdetail]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[Vieworderdetail]
AS
SELECT        dbo.Orderdetail.id, dbo.Orderdetail.Orderid, dbo.Orderdetail.Foodid, dbo.Food.Foodname, dbo.Food.Price, dbo.Food.image, dbo.Orderdetail.quantity, dbo.Orderdetail.Totalamount
FROM            dbo.Food INNER JOIN
                         dbo.Orderdetail ON dbo.Food.Foodid = dbo.Orderdetail.Foodid
GO
/****** Object:  Table [dbo].[Favorite]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Favorite](
	[favoriteid] [int] IDENTITY(1,1) NOT NULL,
	[foodid] [int] NOT NULL,
	[empid] [varchar](10) NOT NULL,
	[name] [varchar](100) NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[image] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[favoriteid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ingredient]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ingredient](
	[Ingredientid] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[unit] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Ingredientid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Receipe]    Script Date: 8/17/2022 11:39:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Receipe](
	[Receipeid] [int] IDENTITY(1,1) NOT NULL,
	[Foodid] [int] NULL,
	[Ingredientid] [int] NULL,
	[quantity] [decimal](15, 4) NOT NULL,
	[unit] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[Receipeid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00001', N'Nguyen Han', N'+8498365414', N'user', N'Active', N'hannguyen@g,ail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00002', N'Tran Thanh Man', N'+8436542191', N'user', N'Active', N'thanhman@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00003', N'Ngoc Anh', N'+8499232810', N'user', N'Active', N'anhngoc@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00004', N'Trung Hieu', N'+8412453888', N'user', N'Active', N'trunghieu@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00005', N'Le Trang', N'+8436722811', N'user', N'Active', N'trangle@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00008', N'Nguyen Ngoc Chau', N'+8498823561', N'user', N'Active', N'ngocchau@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00009', N'Trinh Thang Vi', N'+8494523319', N'user', N'Active', N'thanhvi@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00010', N'Hoang Trung ', N'+8439552818', N'user', N'Active', N'trunghong@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00011', N'Vo Hoang Kha', N'+8432464557', N'user', N'Active', N'hoangkha@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00012', N'Ho Trang Nhu', N'+8498765332', N'user', N'Active', N'tranhnhu@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A00013', N'Tran Hong Phuc', N'+8498623200', N'user', N'Active', N'hongphuc@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'A0007', N'Tran Anh Bao', N'+8493874129', N'user', N'Active', N'anhbao@gmail.com')
INSERT [dbo].[Employee] ([Empid], [Empname], [phone], [Role], [Status], [Email]) VALUES (N'EE001', N'Kim Dung', N'+8439888562', N'admin', N'Active', N'kimdung@gmail.com')
GO
SET IDENTITY_INSERT [dbo].[Favorite] ON 

INSERT [dbo].[Favorite] ([favoriteid], [foodid], [empid], [name], [price], [quantity], [image]) VALUES (1, 1, N'A0007', N'Sweet Fried Chicken Set', 35000, 1, N'sauce-fried-chicken.png')
SET IDENTITY_INSERT [dbo].[Favorite] OFF
GO
SET IDENTITY_INSERT [dbo].[Food] ON 

INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (1, N'Sweet Fried Chicken Set', 35000, 9, N'sauce-fried-chicken.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (2, N'Curry Chicken Rice', 35000, 9, N'mabotofu-set.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (4, N'Sweet Sour Pork Set', 35000, 7, N'sweet-sour-pork.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (5, N'Fried Chicken Set', 35000, 9, N'fried-chicken.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (6, N'Spicy Stir-Fried Shrimp Set', 35000, 12, N'stir-fried-shrimp-set.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (7, N'Stir-fried Pork Ginget Sauce Set', 35000, 7, N'stir-fried-pork-set.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (8, N'Tonkotsu Ramen', 35000, 2, N'tonkotsu-ramen.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (9, N'Shrimp Soup Tsukemen', 35000, 2, N'tsukemen.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (17, N'Sabichuong Rice Set', 35000, 7, N'sabichuong.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (18, N'Sour Soup Meal', 35000, 3, N'sour-soup.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (19, N'Vegan Spicy Noodle Soup', 35000, 3, N'spicy-miso-ramen.png', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (23, N'Steamed Carp Rice Set', 35000, 8, N'catramkho.PNG', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (31, N'Tofu in tomato sauce', 7000, 3, N'dauphusotcachua.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (33, N'Egg braised meat', 25000, 7, N'thitkhotrung.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (35, N'Hot rice with minced meat', 35000, 7, N'comnongkemthitbam.PNG', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (36, N'Fried rice with bay leaf', 35000, 7, N'comchalalot.PNG', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (37, N'Roasted pork rice', 35000, 7, N'comthitheoquay.PNG', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (38, N'Stir-fried beef with garlic', 35000, 7, N'boxaocantoi.PNG', N'set')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (39, N'Braised Tofu with Lemongrass', 10000, 3, N'tahuomsa.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (40, N'Crispy Fried Abalone Mushrooms with Passion Fruit Sauce', 20000, 3, N'nambaonguchien.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (41, N'Pumpkin soup', 10000, 3, N'canhbido.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (42, N'Sour soup', 25000, 12, N'canhchuatom.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (43, N'Stir-fried broccoli with garlic', 10000, 3, N'bongcaixaotoi.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (44, N'sauteed cabbage', 10000, 3, N'caichua.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (45, N'egg roll', 10000, 10, N'trungcuon.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (46, N'Shrimp rim', 25000, 12, N'comtomrim.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (47, N'egg oppla', 6000, 10, N'oppla.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (48, N'Sweet and sour chicken', 25000, 9, N'gaxaochuangot.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (49, N'Fried chicken', 25000, 9, N'garan.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (50, N'Steamed Carp', 25000, 8, N'catramkho.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (51, N'Rice', 10000, 13, N'rice.PNG', N'option')
INSERT [dbo].[Food] ([Foodid], [Foodname], [Price], [menutypeid], [image], [category]) VALUES (52, N'Noodle', 3000, 2, N'migoi.PNG', N'option')
SET IDENTITY_INSERT [dbo].[Food] OFF
GO
SET IDENTITY_INSERT [dbo].[Ingredient] ON 

INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (1, N'chicken', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (2, N'Pineapple', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (3, N'Tomatoes', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (4, N'Flour', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (5, N'Cornstarch', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (6, N'Rice flour', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (7, N'Potato', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (8, N'Sweet potato', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (9, N'Carrot', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (10, N'Lemongrass', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (11, N'Rice', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (12, N'Fresh vermicelli', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (13, N'Pork meat', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (14, N'Garlic', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (15, N' Shrimp', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (16, N'Purple onion', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (17, N'Cabbage', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (18, N'noodle', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (19, N'Egg', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (20, N'Ribs', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (21, N'Lemon', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (22, N'Okra', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (23, N'Mint', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (24, N'White tofu', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (25, N'Tofu', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (26, N'Carrots', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (27, N'Kimchi', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (28, N'Coconut water', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (29, N'Snakehead fish', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (30, N'Beef', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (31, N'Piper lolot', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (32, N'Roasted pork', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (33, N'Abalone Mushrooms', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (34, N'Passion Fruit', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (35, N'Crispy flour', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (36, N'Pumpkin', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (37, N'Broccoli', NULL)
INSERT [dbo].[Ingredient] ([Ingredientid], [name], [unit]) VALUES (38, N'sauteed cabbage', NULL)
SET IDENTITY_INSERT [dbo].[Ingredient] OFF
GO
SET IDENTITY_INSERT [dbo].[Menutype] ON 

INSERT [dbo].[Menutype] ([menutypeid], [type], [typeimg]) VALUES (2, N'Noodles', N'noodle.png')
INSERT [dbo].[Menutype] ([menutypeid], [type], [typeimg]) VALUES (3, N'Vegeterian', N'vegetarian.PNG')
INSERT [dbo].[Menutype] ([menutypeid], [type], [typeimg]) VALUES (7, N'Meat', N'meal.png')
INSERT [dbo].[Menutype] ([menutypeid], [type], [typeimg]) VALUES (8, N'Fish', N'fish.png')
INSERT [dbo].[Menutype] ([menutypeid], [type], [typeimg]) VALUES (9, N'Chicken', N'chicken.png')
INSERT [dbo].[Menutype] ([menutypeid], [type], [typeimg]) VALUES (10, N'Egg', N'fried-egg.png')
INSERT [dbo].[Menutype] ([menutypeid], [type], [typeimg]) VALUES (12, N'Shrimp', N'shrimp.png')
INSERT [dbo].[Menutype] ([menutypeid], [type], [typeimg]) VALUES (13, N'Rice', N'riceicon.png')
SET IDENTITY_INSERT [dbo].[Menutype] OFF
GO
SET IDENTITY_INSERT [dbo].[Orderdetail] ON 

INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (1, 1, 1, 35000, 1, 35000, CAST(N'2022-08-06T07:49:07.360' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (2, 2, 33, 25000, 1, 25000, CAST(N'2022-08-06T07:50:08.613' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (3, 2, 51, 10000, 1, 10000, CAST(N'2022-08-07T07:50:08.620' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (4, 3, 35, 35000, 1, 35000, CAST(N'2022-08-07T07:50:38.307' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (5, 4, 17, 35000, 1, 35000, CAST(N'2022-08-07T07:51:04.797' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (6, 5, 5, 35000, 1, 35000, CAST(N'2022-08-07T07:51:29.533' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (7, 6, 51, 10000, 1, 10000, CAST(N'2022-08-08T07:53:11.050' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (8, 6, 44, 10000, 1, 10000, CAST(N'2022-08-08T07:53:11.057' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (9, 6, 31, 7000, 2, 14000, CAST(N'2022-08-08T07:53:11.060' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (10, 7, 18, 35000, 1, 35000, CAST(N'2022-08-08T07:53:45.023' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (11, 8, 23, 35000, 1, 35000, CAST(N'2022-08-08T07:54:06.523' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (12, 9, 37, 35000, 1, 35000, CAST(N'2022-08-08T07:54:33.967' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (13, 10, 50, 25000, 1, 25000, CAST(N'2022-08-08T07:55:16.887' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (14, 10, 51, 10000, 1, 10000, CAST(N'2022-08-08T07:55:16.897' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (15, 11, 2, 35000, 1, 35000, CAST(N'2022-08-08T07:55:51.313' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (16, 12, 9, 35000, 1, 35000, CAST(N'2022-08-08T07:56:12.080' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (17, 13, 31, 7000, 1, 7000, CAST(N'2022-08-15T14:52:10.380' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (18, 13, 33, 25000, 1, 25000, CAST(N'2022-08-15T14:56:13.170' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (19, 14, 33, 25000, 1, 25000, CAST(N'2022-08-16T21:56:23.137' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (20, 15, 39, 10000, 1, 10000, CAST(N'2022-08-17T10:59:52.577' AS DateTime))
INSERT [dbo].[Orderdetail] ([id], [Orderid], [Foodid], [price], [quantity], [Totalamount], [date]) VALUES (21, 15, 47, 6000, 1, 6000, CAST(N'2022-08-17T10:59:52.593' AS DateTime))
SET IDENTITY_INSERT [dbo].[Orderdetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (1, N'A00001', 1, 35000, CAST(N'2022-07-06T07:49:07.317' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (2, N'A00002', 2, 35000, CAST(N'2022-08-06T07:50:08.603' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (3, N'A00003', 1, 35000, CAST(N'2022-08-07T07:50:38.290' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (4, N'A00004', 1, 35000, CAST(N'2022-08-07T07:51:04.790' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (5, N'A00005', 1, 35000, CAST(N'2022-08-07T07:51:29.530' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (6, N'A00008', 4, 34000, CAST(N'2022-08-07T07:53:11.030' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (7, N'A00009', 1, 35000, CAST(N'2022-08-08T07:53:45.010' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (8, N'A00010', 1, 35000, CAST(N'2022-08-08T07:54:06.497' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (9, N'A00011', 1, 35000, CAST(N'2022-08-08T07:54:33.947' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (10, N'A00012', 2, 35000, CAST(N'2022-08-08T07:55:16.873' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (11, N'A00013', 1, 35000, CAST(N'2022-08-08T07:55:51.307' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (12, N'A0007', 1, 35000, CAST(N'2022-08-08T07:56:12.070' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (13, N'A0007', 1, 33000, CAST(N'2022-08-15T14:52:09.380' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (14, N'A0007', 1, 25000, CAST(N'2022-08-16T21:56:22.927' AS DateTime))
INSERT [dbo].[Orders] ([Orderid], [Empid], [quantity], [Totalamount], [orderdate]) VALUES (15, N'A0007', 2, 16000, CAST(N'2022-08-17T10:59:52.550' AS DateTime))
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Receipe] ON 

INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (1, 1, 1, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (2, 1, 3, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (3, 1, 2, CAST(0.0100 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (4, 1, 4, CAST(0.0600 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (5, 1, 5, CAST(0.0200 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (6, 1, 6, CAST(0.0200 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (8, 2, 1, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (9, 2, 7, CAST(0.0050 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (10, 2, 9, CAST(0.0100 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (11, 2, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (12, 4, 13, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (13, 4, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (14, 4, 14, CAST(0.0100 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (15, 5, 1, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (16, 5, 4, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (17, 5, 5, CAST(0.0200 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (18, 5, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (19, 6, 15, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (20, 6, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (21, 6, 16, CAST(0.0100 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (22, 7, 13, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (23, 7, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (24, 7, 17, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (25, 8, 18, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (26, 8, 17, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (27, 9, 18, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (28, 9, 17, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (29, 9, 13, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (30, 8, 19, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (31, 17, 20, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (32, 17, 21, CAST(0.0200 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (33, 17, 14, CAST(0.0100 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (34, 17, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (35, 18, 15, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (36, 18, 22, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (37, 18, 23, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (38, 18, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (39, 19, 18, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (40, 19, 24, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (41, 19, 25, CAST(0.0200 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (42, 19, 26, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (43, 19, 27, CAST(0.5000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (44, 23, 29, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (45, 23, 28, CAST(0.5000 AS Decimal(15, 4)), N'liter')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (46, 23, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (47, 23, 17, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (48, 31, 25, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (49, 31, 3, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (50, 33, 13, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (51, 33, 19, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (52, 33, 28, CAST(0.2000 AS Decimal(15, 4)), N'liter')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (53, 35, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (54, 35, 13, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (55, 35, 19, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (56, 35, 17, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (57, 36, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (58, 36, 30, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (59, 36, 31, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (60, 36, 13, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (61, 36, 17, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (62, 37, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (63, 37, 32, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (64, 37, 17, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (65, 38, 30, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (66, 38, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (67, 38, 16, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (68, 38, 14, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (69, 38, 17, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (70, 38, 26, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (71, 39, 25, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (72, 39, 10, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (73, 40, 33, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (74, 40, 34, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (75, 40, 35, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (76, 41, 36, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (77, 42, 15, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (78, 42, 22, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (79, 42, 23, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (80, 43, 37, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (81, 43, 9, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (82, 43, 14, CAST(0.0100 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (83, 43, 3, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (84, 44, 38, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (85, 45, 19, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (86, 46, 15, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (87, 47, 19, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (88, 48, 1, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (89, 48, 3, CAST(0.1000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (90, 49, 1, CAST(0.3000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (91, 49, 35, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (92, 50, 29, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (93, 50, 28, CAST(0.0500 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (94, 51, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (96, 52, 18, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
INSERT [dbo].[Receipe] ([Receipeid], [Foodid], [Ingredientid], [quantity], [unit]) VALUES (97, 1, 11, CAST(0.2000 AS Decimal(15, 4)), N'kilogram')
SET IDENTITY_INSERT [dbo].[Receipe] OFF
GO
ALTER TABLE [dbo].[Food]  WITH CHECK ADD FOREIGN KEY([menutypeid])
REFERENCES [dbo].[Menutype] ([menutypeid])
GO
ALTER TABLE [dbo].[Receipe]  WITH CHECK ADD FOREIGN KEY([Foodid])
REFERENCES [dbo].[Food] ([Foodid])
GO
ALTER TABLE [dbo].[Receipe]  WITH CHECK ADD FOREIGN KEY([Ingredientid])
REFERENCES [dbo].[Ingredient] ([Ingredientid])
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Employee"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Viewemployee'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Viewemployee'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Food"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 2
         End
         Begin Table = "Menutype"
            Begin Extent = 
               Top = 6
               Left = 246
               Bottom = 119
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Viewfood'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Viewfood'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Food"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 2
         End
         Begin Table = "Orderdetail"
            Begin Extent = 
               Top = 6
               Left = 246
               Bottom = 136
               Right = 416
            End
            DisplayFlags = 280
            TopColumn = 3
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Vieworderdetail'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Vieworderdetail'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "Orders"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 208
            End
            DisplayFlags = 280
            TopColumn = 1
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Vieworders'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'Vieworders'
GO
USE [master]
GO
ALTER DATABASE [EatneatDB] SET  READ_WRITE 
GO
