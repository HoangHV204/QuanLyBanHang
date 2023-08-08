USE [master]
GO
/****** Object:  Database [QLBH]    Script Date: 08/08/2023 11:08:42 PM ******/
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
/****** Object:  Table [dbo].[CHITIETSANPHAM]    Script Date: 08/08/2023 11:08:43 PM ******/
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
/****** Object:  Table [dbo].[HOADON]    Script Date: 08/08/2023 11:08:43 PM ******/
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
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 08/08/2023 11:08:43 PM ******/
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
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 08/08/2023 11:08:43 PM ******/
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
/****** Object:  Table [dbo].[USERS]    Script Date: 08/08/2023 11:08:43 PM ******/
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
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[USERS]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
/****** Object:  StoredProcedure [dbo].[SP_AddHoaDon]    Script Date: 08/08/2023 11:08:43 PM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_AddKhachHang]    Script Date: 08/08/2023 11:08:43 PM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_AddSanPham]    Script Date: 08/08/2023 11:08:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_AddSanPham]
	@MaChiTietSanPham VARCHAR(10), @MaSP VARCHAR(10), 
	@TenSP NVARCHAR(50), @Loai NVARCHAR(30), @SoLuong INT, @DonGia INT, @TrangThai NVARCHAR(20)
AS
BEGIN
    INSERT INTO dbo.CHITIETSANPHAM
    (
        MaChiTietSanPham,
        MaSP,
        TenSP,
        Loai,
        DonGia,
        SoLuong,
        TrangThai,
        image
    )
    VALUES
    (   @MaChiTietSanPham,   -- MaChiTietSanPham - varchar(10)
        @MaSP, -- MaSP - varchar(10)
        @TenSP, -- TenSP - nvarchar(50)
        @Loai, -- Loai - nvarchar(30)
        @DonGia, -- DonGia - int
        @SoLuong, -- SoLuong - int
        @TrangThai, -- TrangThai - nvarchar(30)
        'hoicham.jpg'  -- image - varchar(30)
        )
END
GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateHoaDon]    Script Date: 08/08/2023 11:08:43 PM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_UpdateSanPham]    Script Date: 08/08/2023 11:08:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_UpdateSanPham]
	@MaChiTietSanPham VARCHAR(10), @MaSP VARCHAR(10), 
	@TenSP NVARCHAR(50), @Loai NVARCHAR(30), @SoLuong INT, @DonGia INT, @TrangThai NVARCHAR(20)
AS
BEGIN
    UPDATE dbo.CHITIETSANPHAM
	SET TenSP = @TenSP, Loai = @Loai,  SoLuong = @SoLuong, DonGia = @DonGia, TrangThai = @TrangThai
	WHERE MaChiTietSanPham = @MaChiTietSanPham
END
GO
USE [master]
GO
ALTER DATABASE [QLBH] SET  READ_WRITE 
GO
