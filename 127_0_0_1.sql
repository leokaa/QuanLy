-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 15, 2022 lúc 04:32 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanly1`
--
CREATE DATABASE IF NOT EXISTS `quanly1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `quanly1`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quanly_doanhthu`
--

CREATE TABLE `quanly_doanhthu` (
  `DT_ngay` date NOT NULL,
  `DT_soluongban` int(11) NOT NULL,
  `DT_soluongtra` int(11) NOT NULL,
  `LH_malh` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quanly_hangnhap`
--

CREATE TABLE `quanly_hangnhap` (
  `HN_ngay` date NOT NULL,
  `LH_malh` varchar(10) NOT NULL,
  `HN_soluong` int(15) NOT NULL,
  `HN_ghichu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quanly_khanhhang`
--

CREATE TABLE `quanly_khanhhang` (
  `KH_makh` varchar(10) NOT NULL,
  `KH_tenkh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `KH_sdt` int(15) NOT NULL,
  `KH_diachi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `KH_sothungno` int(10) NOT NULL,
  `KH_ghichu` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quanly_loaihang`
--

CREATE TABLE `quanly_loaihang` (
  `LH_malh` varchar(10) NOT NULL,
  `LH_ten` varchar(20) NOT NULL,
  `LH_dongia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `quanly_doanhthu`
--
ALTER TABLE `quanly_doanhthu`
  ADD PRIMARY KEY (`DT_ngay`,`LH_malh`),
  ADD KEY `fk_dt` (`LH_malh`);

--
-- Chỉ mục cho bảng `quanly_hangnhap`
--
ALTER TABLE `quanly_hangnhap`
  ADD PRIMARY KEY (`HN_ngay`,`LH_malh`),
  ADD KEY `malh` (`LH_malh`);

--
-- Chỉ mục cho bảng `quanly_khanhhang`
--
ALTER TABLE `quanly_khanhhang`
  ADD PRIMARY KEY (`KH_makh`);

--
-- Chỉ mục cho bảng `quanly_loaihang`
--
ALTER TABLE `quanly_loaihang`
  ADD PRIMARY KEY (`LH_malh`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `quanly_doanhthu`
--
ALTER TABLE `quanly_doanhthu`
  ADD CONSTRAINT `fk_dt` FOREIGN KEY (`LH_malh`) REFERENCES `quanly_loaihang` (`LH_malh`);

--
-- Các ràng buộc cho bảng `quanly_hangnhap`
--
ALTER TABLE `quanly_hangnhap`
  ADD CONSTRAINT `malh` FOREIGN KEY (`LH_malh`) REFERENCES `quanly_loaihang` (`LH_malh`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
