-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-12-2015 a las 22:39:14
-- Versión del servidor: 5.6.20
-- Versión de PHP: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `closerme`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calllog`
--

CREATE TABLE IF NOT EXISTS `calllog` (
`registerId` int(11) NOT NULL,
  `memberName` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `numberPhone` varchar(255) NOT NULL,
  `duration` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Volcado de datos para la tabla `calllog`
--

INSERT INTO `calllog` (`registerId`, `memberName`, `date`, `numberPhone`, `duration`) VALUES
(4, 'papata', '2015-11-30', '9992875621', '1:30:55'),
(8, '0', '2015-11-30', '0', '0:0:0'),
(20, 'jajsss', '2015-11-30', '0', '0:0:0'),
(25, 'Mileena', '2015-12-02', '0', '0:0:0'),
(26, 'Reptile', '2015-12-02', '0', '0:0:0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `member`
--

CREATE TABLE IF NOT EXISTS `member` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=94 ;

--
-- Volcado de datos para la tabla `member`
--

INSERT INTO `member` (`id`, `name`, `address`, `cellphone`, `phone`) VALUES
(91, '0', '0 0 0 y 0 0', '0', '0'),
(92, '0', '0 0 0 y 0 0', '0', '0'),
(93, '1', '1 1 1 y 1 1', '1', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `membership`
--

CREATE TABLE IF NOT EXISTS `membership` (
`membershipNumber` int(11) NOT NULL,
  `membershipType` varchar(50) NOT NULL,
  `startDate` date DEFAULT NULL,
  `expireDate` date DEFAULT NULL,
  `costs` double NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=94 ;

--
-- Volcado de datos para la tabla `membership`
--

INSERT INTO `membership` (`membershipNumber`, `membershipType`, `startDate`, `expireDate`, `costs`) VALUES
(91, 'Semanal', '2015-12-02', '2015-12-09', 100),
(92, 'Semanal', '2015-12-02', '2015-12-09', 100),
(93, 'Semanal', '2015-12-02', '2015-12-09', 100);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `calllog`
--
ALTER TABLE `calllog`
 ADD PRIMARY KEY (`registerId`);

--
-- Indices de la tabla `member`
--
ALTER TABLE `member`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `membership`
--
ALTER TABLE `membership`
 ADD PRIMARY KEY (`membershipNumber`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `calllog`
--
ALTER TABLE `calllog`
MODIFY `registerId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT de la tabla `member`
--
ALTER TABLE `member`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=94;
--
-- AUTO_INCREMENT de la tabla `membership`
--
ALTER TABLE `membership`
MODIFY `membershipNumber` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=94;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
