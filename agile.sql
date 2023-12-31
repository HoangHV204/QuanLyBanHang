USE [master]
GO
/****** Object:  Database [QLBH]    Script Date: 16/08/2023 12:50:11 PM ******/
CREATE DATABASE [QLBH]
ALTER DATABASE [QLBH] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLBH].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLBH] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLBH] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLBH] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLBH] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLBH] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLBH] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QLBH] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLBH] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLBH] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLBH] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLBH] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLBH] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLBH] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLBH] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLBH] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QLBH] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLBH] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLBH] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLBH] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLBH] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLBH] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLBH] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLBH] SET RECOVERY FULL 
GO
ALTER DATABASE [QLBH] SET  MULTI_USER 
GO
ALTER DATABASE [QLBH] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLBH] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLBH] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLBH] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QLBH] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QLBH] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'QLBH', N'ON'
GO
ALTER DATABASE [QLBH] SET QUERY_STORE = OFF
GO
USE [QLBH]
GO
/****** Object:  Table [dbo].[CHITIETSANPHAM]    Script Date: 16/08/2023 12:50:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHITIETSANPHAM](
	[MaChiTietSanPham] [varchar](10) NOT NULL,
	[TenSP] [nvarchar](50) NULL,
	[Loai] [nvarchar](30) NULL,
	[DonGia] [int] NULL,
	[SoLuong] [int] NULL,
	[TrangThai] [nvarchar](30) NULL,
	[image] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaChiTietSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 16/08/2023 12:50:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[MaHD] [int] NOT NULL,
	[MaKH] [varchar](10) NOT NULL,
	[MaNV] [varchar](10) NOT NULL,
	[MaSP] [varchar](10) NULL,
	[ThanhTien] [int] NULL,
	[NgayTao] [date] NULL,
	[SoLuong] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 16/08/2023 12:50:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[MaKH] [varchar](10) NOT NULL,
	[HoTenKH] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](50) NULL,
	[Gioitinh] [bit] NULL,
	[SĐT] [varchar](13) NULL,
	[image] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 16/08/2023 12:50:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MaNV] [varchar](10) NOT NULL,
	[HoTenNV] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](50) NULL,
	[SĐT] [varchar](13) NULL,
	[image] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[USERS]    Script Date: 16/08/2023 12:50:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USERS](
	[username] [varchar](10) NOT NULL,
	[password] [varchar](50) NULL,
	[role] [nvarchar](15) NULL,
	[RememberPassword] [varchar](10) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP1', N'IPhone 11 Pro Max', N'new', 12000000, 2, N'Còn', N'iphone11prm.png')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP10', N'iPhone 15', N'new', 50000000, 3, N'Còn', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP11', N'sdf', N'new', 21313, 2, N'Còn', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP12', N'iguu', N'new', 5000000, 6, N'Hết', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP13', N'sdf', N'new', 30000000, 3, N'Còn', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP14', N'aaaa', N'new', 6000000, 4, N'Còn', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP15', N'sdf', N'new', 5000, 32, N'Còn', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP2', N'IPhone XS Max', N'like new', 9000000, 3, N'Còn', N'iphonexsm.png')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP3', N'IPhone 14 Pro Max', N'like new', 30000000, 0, N'Hết', N'iphone14.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP4', N'IPhone XS Max', N'like new', 10000000, 3, N'Còn', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP5', N'sdf', N'new', 2000000, 2, N'Còn', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP6', N'dsf', N'like new', 4000000, 1, N'Còn', N'hoicham.jpg')
INSERT [dbo].[CHITIETSANPHAM] ([MaChiTietSanPham], [TenSP], [Loai], [DonGia], [SoLuong], [TrangThai], [image]) VALUES (N'SP7', N'dsf', N'like new', 2300000, 2, N'Còn', N'hoicham.jpg')
GO
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (1, N'KH1', N'nv01', N'SP2', 9000000, CAST(N'2023-08-08' AS Date), 1)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (2, N'KH3', N'nv01', N'SP4', 10000000, CAST(N'2023-08-08' AS Date), 1)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (3, N'KH4', N'NV01', N'SP7', 4324324, CAST(N'2023-08-08' AS Date), 1)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (4, N'KH5', N'NV01', N'SP1', 12000000, CAST(N'2023-08-08' AS Date), 1)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (5, N'KH6', N'NV01', N'SP4', 10000000, CAST(N'2023-08-08' AS Date), 1)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (6, N'KH7', N'NV01', N'SP1', 48000000, CAST(N'2023-08-08' AS Date), 4)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (7, N'KH8', N'NV01', N'SP2', 9000000, CAST(N'2023-08-08' AS Date), 1)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (8, N'KH9', N'NV01', N'Sp2', 9000000, CAST(N'2023-08-10' AS Date), 1)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (9, N'KH12', N'NV01', N'SP2', 27000000, CAST(N'2023-08-12' AS Date), 3)
GO
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH01', N'LE BA MINH', N'Thanh Hoa', 1, N'0866738711', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH10', N'hbj', N'hhj', 1, N'0864564687', N'boy.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH11', N'hbj', N'hhj', 0, N'0864564687', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH12', N'dsf', N'sdf', 0, N'09213871221', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH13', N'sdf', N'sdf', 0, N'0234672342', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH2', N'sd', N'ds', 1, N'0746553432', N'boy.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH3', N'sd', N'ds', 1, N'0746553432', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH5', N'sdf', N'dsf', 1, N'0543534323', N'boy.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH6', N'dsf', N'sdf', 0, N'0032432432', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH7', N'sd', N'ds', 0, N'0746553432', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH8', N'dsf', N'sdf', 0, N'0887324324', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH9', N'jj', N'nb', 0, N'0867435677', N'girl.png')
GO
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'admin', N'Lê Bá Minh', 1, CAST(N'2004-02-11' AS Date), N'Thanh Hoá', N'0854783247', N'boy.png')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV01', N'Nguyễn Thu Hằng', 0, CAST(N'2004-03-12' AS Date), N'123 Phương Canh', N'0346287994', N'girl.png')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV03', N'Hoàng Văn Hoàng', 1, CAST(N'2004-02-11' AS Date), N'Hà Nội', N'0854783247', N'boy.png')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV04', N'Nguyễn Tiến Hải', 1, CAST(N'2004-02-11' AS Date), N'Thái Nguyên', N'0854783247', N'boy.png')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV05', N'Trần Hoài Nam', 1, CAST(N'2004-02-11' AS Date), N'Hà Nội', N'0854783247', N'boy.png')
GO
INSERT [dbo].[USERS] ([username], [password], [role], [RememberPassword]) VALUES (N'NV01', N'1', N'user', N'ON')
INSERT [dbo].[USERS] ([username], [password], [role], [RememberPassword]) VALUES (N'admin', N'123456', N'user', N'ON')
INSERT [dbo].[USERS] ([username], [password], [role], [RememberPassword]) VALUES (N'NV03', N'1', N'user', N'OFF')
INSERT [dbo].[USERS] ([username], [password], [role], [RememberPassword]) VALUES (N'NV04', N'123456', N'user', N'OFF')
INSERT [dbo].[USERS] ([username], [password], [role], [RememberPassword]) VALUES (N'NV05', N'123456', N'user', N'ON')
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[USERS]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
/****** Object:  StoredProcedure [dbo].[SP_AddHoaDon]    Script Date: 16/08/2023 12:50:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_AddHoaDon]
	@MaHD INT,@MaKH VARCHAR(10), @MaNV VARCHAR(10), @MaSP VARCHAR(10),
	@SoLuong INT, @ThanhTien INT
AS
BEGIN
    DECLARE @today DATE;
	SET @today = CAST(GETDATE() AS DATE)
	INSERT INTO dbo.HOADON
	(
	    MaHD,
	    MaKH,
	    MaNV,
	    MaSP,
	    ThanhTien,
	    NgayTao,
	    SoLuong
	)
	VALUES
	(   @MaHD,    -- MaHD - int
	    @MaKH,   -- MaKH - varchar(10)
	    @MaNV,   -- MaNV - varchar(10)
	    @MaSP, -- MaSP - varchar(10)
	    @ThanhTien, -- ThanhTien - int
	    @today, -- NgayTao - date
	    @SoLuong  -- SoLuong - int
	    )
END
GO
/****** Object:  StoredProcedure [dbo].[SP_AddKhachHang]    Script Date: 16/08/2023 12:50:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_AddKhachHang]
	@MaKH VARCHAR(10), @HoTen NVARCHAR(50), @DiaChi NVARCHAR(50), @GioiTinh BIT,
	@Sdt VARCHAR(20)
AS
BEGIN
    INSERT INTO dbo.KHACHHANG
    (
        MaKH,
        HoTenKH,
        DiaChi,
        Gioitinh,
        SĐT,
		image
    )
    VALUES
    (   @MaKH,   -- MaKH - varchar(10)
        @HoTen,  -- HoTenKH - nvarchar(50)
        @DiaChi, -- DiaChi - nvarchar(50)
        @GioiTinh, -- Gioitinh - bit
        @Sdt,  -- SĐT - varchar(13)
		'girl.png'
        )
END
GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateHoaDon]    Script Date: 16/08/2023 12:50:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_UpdateHoaDon]
	@MaHD INT,@TenSP NVARCHAR(50),@LoaiSP NVARCHAR(20),@SoLuong INT,
	@TenKH NVARCHAR(50),@DiaChi NVARCHAR(50),@Sdt VARCHAR(10), @ThanhTien INT
AS
BEGIN
	DECLARE @today DATE;
	SET @today = CAST(GETDATE() AS DATE)
    UPDATE dbo.HOADONCHITIET
	SET TenSP = @TenSP, Loai = @LoaiSP, SoLuong = @SoLuong, TenKH = @TenKH, DiaChi = @DiaChi, SoDT = @Sdt, ThanhTien = @ThanhTien
	WHERE MaHD = @MaHD
END
GO
USE [master]
GO
ALTER DATABASE [QLBH] SET  READ_WRITE 
GO
