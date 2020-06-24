-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-06-2020 a las 02:32:27
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sd_bolsa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `company`
--

CREATE TABLE `company` (
  `RFCCom` varchar(10) NOT NULL,
  `acTot` int(11) NOT NULL,
  `acDisp` int(11) NOT NULL,
  `valActAc` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `company`
--

INSERT INTO `company` (`RFCCom`, `acTot`, `acDisp`, `valActAc`) VALUES
('12345ABCDE', 700, 500, 2500.00),
('98765ABCDE', 550, 550, 900.00),
('ABCDE12345', 600, 220, 3500.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaction`
--

CREATE TABLE `transaction` (
  `RFCUsr` varchar(10) NOT NULL,
  `RFCCom` varchar(10) NOT NULL,
  `operationDate` datetime NOT NULL,
  `operationCode` int(11) DEFAULT NULL,
  `operationPrice` double(10,2) DEFAULT NULL,
  `noAc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `transaction`
--

INSERT INTO `transaction` (`RFCUsr`, `RFCCom`, `operationDate`, `operationCode`, `operationPrice`, `noAc`) VALUES
('USER123456', '12345ABCDE', '2020-06-20 18:01:48', 1, 2500.00, 200),
('USER123456', 'ABCDE12345', '2020-06-20 17:22:27', 1, 2500.00, 50),
('USER123456', 'ABCDE12345', '2020-06-20 18:23:53', -1, 2450.00, 30),
('USER444444', 'ABCDE12345', '2020-06-20 18:23:53', 1, 3500.00, 200),
('USER678901', 'ABCDE12345', '2020-06-20 17:27:31', 1, 3600.00, 120),
('USER678901', 'ABCDE12345', '2020-06-20 17:46:48', -1, 2500.00, 50),
('USER987654', 'ABCDE12345', '2020-06-20 17:23:41', 1, 3500.00, 90);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `RFCUsr` varchar(10) NOT NULL,
  `RFCCom` varchar(10) NOT NULL,
  `noAc` int(11) DEFAULT NULL,
  `precioCompra` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`RFCUsr`, `RFCCom`, `noAc`, `precioCompra`) VALUES
('USER123456', '12345ABCDE', 200, 2500.00),
('USER123456', 'ABCDE12345', 20, 2500.00),
('USER444444', 'ABCDE12345', 200, 3500.00),
('USER678901', 'ABCDE12345', 70, 3600.00),
('USER987654', 'ABCDE12345', 90, 3500.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`RFCCom`);

--
-- Indices de la tabla `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`RFCUsr`,`RFCCom`,`operationDate`),
  ADD KEY `RFC_usr_idx` (`RFCUsr`),
  ADD KEY `RFC_com_idx` (`RFCCom`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`RFCUsr`,`RFCCom`),
  ADD KEY `RFC_com_idx` (`RFCCom`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `RFC_com_FK` FOREIGN KEY (`RFCCom`) REFERENCES `company` (`RFCCom`) ON UPDATE CASCADE,
  ADD CONSTRAINT `RFC_usr_FK` FOREIGN KEY (`RFCUsr`) REFERENCES `user` (`RFCUsr`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `RFC_com` FOREIGN KEY (`RFCCom`) REFERENCES `company` (`RFCCom`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
