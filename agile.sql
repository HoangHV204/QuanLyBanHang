USE [master]
GO
/****** Object:  Database [QLBH]    Script Date: 07/08/2023 11:05:00 PM ******/
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
/****** Object:  Table [dbo].[HOADON]    Script Date: 07/08/2023 11:05:00 PM ******/
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
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 07/08/2023 11:05:00 PM ******/
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
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 07/08/2023 11:05:00 PM ******/
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
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 07/08/2023 11:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[MaSP] [varchar](10) NOT NULL,
	[TenSP] [nvarchar](50) NULL,
	[Loai] [nvarchar](50) NULL,
	[DonViTinh] [nvarchar](10) NULL,
	[DonGia] [int] NULL,
	[TrangThai] [nvarchar](10) NULL,
	[image] [varchar](50) NULL,
	[SoLuong] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[USERS]    Script Date: 07/08/2023 11:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USERS](
	[username] [varchar](10) NOT NULL,
	[password] [varchar](50) NULL,
	[role] [nvarchar](15) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (1, N'KH2', N'nv01', N'SP2', 9000000, CAST(N'2023-08-07' AS Date), 1)
INSERT [dbo].[HOADON] ([MaHD], [MaKH], [MaNV], [MaSP], [ThanhTien], [NgayTao], [SoLuong]) VALUES (2, N'KH3', N'nv01', N'SP1', 36000000, CAST(N'2023-08-07' AS Date), 3)
GO
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH1', N'dfdsf', N'dsfd', 1, N'0123123213', N'boy.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH2', N'sdf', N'dsf', 0, N'0823423432', N'girl.png')
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTenKH], [DiaChi], [Gioitinh], [SĐT], [image]) VALUES (N'KH3', N'sdfs', N'dfs', 0, N'0786746543', N'girl.png')
GO
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV01', N'Nguyễn Thu Hằng', 0, CAST(N'2004-03-12' AS Date), N'123 Phương Canh', N'0346287994', N'girl.png')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV02', N'Lê Bá Minh', 1, CAST(N'2004-02-11' AS Date), N'Thanh Hoá', N'0854783247', N'boy.png')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV03', N'Hoàng Văn Hoàng', 1, CAST(N'2004-02-11' AS Date), N'Hà Nội', N'0854783247', N'boy.png')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV04', N'Nguyễn Tiến Hải', 1, CAST(N'2004-02-11' AS Date), N'Thái Nguyên', N'0854783247', N'boy.png')
INSERT [dbo].[NHANVIEN] ([MaNV], [HoTenNV], [GioiTinh], [NgaySinh], [DiaChi], [SĐT], [image]) VALUES (N'NV05', N'Trần Hoài Nam', 1, CAST(N'2004-02-11' AS Date), N'Hà Nội', N'0854783247', N'boy.png')
GO
INSERT [dbo].[SANPHAM] ([MaSP], [TenSP], [Loai], [DonViTinh], [DonGia], [TrangThai], [image], [SoLuong]) VALUES (N'SP1', N'IPhone 11 Pro Max', N'new', N'Chiếc', 12000000, N'Còn', N'iphone11prm.png', 2)
INSERT [dbo].[SANPHAM] ([MaSP], [TenSP], [Loai], [DonViTinh], [DonGia], [TrangThai], [image], [SoLuong]) VALUES (N'SP2', N'IPhone XS Max', N'like new', NULL, 9000000, N'Còn', N'iphonexsm.png', 3)
INSERT [dbo].[SANPHAM] ([MaSP], [TenSP], [Loai], [DonViTinh], [DonGia], [TrangThai], [image], [SoLuong]) VALUES (N'SP3', N'IPhone 14 Pro Max', N'like new', NULL, 30000000, N'Hết', N'iphone14.jpg', 0)
INSERT [dbo].[SANPHAM] ([MaSP], [TenSP], [Loai], [DonViTinh], [DonGia], [TrangThai], [image], [SoLuong]) VALUES (N'SP4', N'IPhone 11 Pro Max', N'new', NULL, 12000000, N'Còn', N'hoicham.jpg', 2)
GO
INSERT [dbo].[USERS] ([username], [password], [role]) VALUES (N'NV01', N'1', N'user')
INSERT [dbo].[USERS] ([username], [password], [role]) VALUES (N'NV02', N'1', N'admin')
INSERT [dbo].[USERS] ([username], [password], [role]) VALUES (N'NV03', N'1', N'user')
INSERT [dbo].[USERS] ([username], [password], [role]) VALUES (N'NV04', N'123456', N'user')
INSERT [dbo].[USERS] ([username], [password], [role]) VALUES (N'NV05', N'123456', N'user')
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD FOREIGN KEY([MaKH])
REFERENCES [dbo].[KHACHHANG] ([MaKH])
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD FOREIGN KEY([MaSP])
REFERENCES [dbo].[SANPHAM] ([MaSP])
GO
ALTER TABLE [dbo].[USERS]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
/****** Object:  StoredProcedure [dbo].[SP_AddHoaDon]    Script Date: 07/08/2023 11:05:01 PM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_AddKhachHang]    Script Date: 07/08/2023 11:05:01 PM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_AddSanPham]    Script Date: 07/08/2023 11:05:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_AddSanPham]
	@MaSP VARCHAR(10), @TenSP NVARCHAR(50), @Loai NVARCHAR(30), @DonGia INT, @TrangThai NVARCHAR(20),@SoLuong INT
AS
BEGIN
    INSERT INTO dbo.SANPHAM
    (
        MaSP,
        TenSP,
        Loai,
        DonViTinh,
        DonGia,
        TrangThai,
        image,
        SoLuong
    )
    VALUES
    (   @MaSP,   -- MaSP - varchar(10)
        @TenSP, -- TenSP - nvarchar(50)
        @Loai, -- Loai - nvarchar(50)
        NULL, -- DonViTinh - nvarchar(10)
        @DonGia, -- DonGia - int
        @TrangThai, -- TrangThai - nvarchar(10)
        'hoicham.jpg', -- image - varchar(50)
        @SoLuong  -- SoLuong - int
        )
END
GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateHoaDon]    Script Date: 07/08/2023 11:05:01 PM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_UpdateSanPham]    Script Date: 07/08/2023 11:05:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_UpdateSanPham]
	@MaSP VARCHAR(10), @TenSP NVARCHAR(50), @Loai NVARCHAR(30), @DonGia INT, @TrangThai NVARCHAR(20),@SoLuong INT
AS
BEGIN
    UPDATE dbo.SANPHAM
	SET TenSP = @TenSP, Loai = @Loai, DonGia = @DonGia, TrangThai = @TrangThai, SoLuong = @SoLuong
	WHERE MaSP = @MaSP
END
GO
USE [master]
GO
ALTER DATABASE [QLBH] SET  READ_WRITE 
GO
