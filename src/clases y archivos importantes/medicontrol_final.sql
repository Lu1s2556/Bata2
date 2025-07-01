-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-07-2025 a las 02:33:55
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `medicontrol`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agenda`
--

CREATE TABLE `agenda` (
  `id_agenda` int(11) NOT NULL,
  `cedula` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `hora` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `agenda`
--

INSERT INTO `agenda` (`id_agenda`, `cedula`, `hora`, `fecha`) VALUES
(7, '31.702.100', '11:00 AM', '2025-06-26'),
(8, '31.702.100', '11:00 AM', '2025-07-30'),
(9, '31.731.017', '9:00 AM', '2025-07-11'),
(10, '31.731.017', '12:00 pm', '2025-07-11'),
(11, '28068018', '9:00am', '2025-07-21');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `antecedentes`
--

CREATE TABLE `antecedentes` (
  `cedula` varchar(15) NOT NULL,
  `historial` text NOT NULL,
  `enfermedades` text NOT NULL,
  `Observaciones` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `antecedentes`
--

INSERT INTO `antecedentes` (`cedula`, `historial`, `enfermedades`, `Observaciones`) VALUES
('28068018', 'dn;fanwj;fnewaivnawdfnjwna;fwnj;f', 'wfjfineineijaijeadn;sjwnf;ewaf', 'jfgaiheriuhghvjasdjkvnsdnlkwnvw');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `usuario` varchar(20) NOT NULL,
  `contraseña` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`usuario`, `contraseña`) VALUES
('doc', '123'),
('medicontrol', 'bata31@'),
('admin', '12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `cedula` varchar(15) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `grupo sanguineo` varchar(5) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `fecha de nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`cedula`, `nombre`, `apellido`, `sexo`, `grupo sanguineo`, `telefono`, `direccion`, `email`, `fecha de nacimiento`) VALUES
('28068018', 'luis', 'rojas', 'Hombre', 'A+', '04143546540', 'nvanovn;nv;nv', 'ndvonwvna;vn;', '2001-06-23'),
('31.702.100', 'junior', 'mmgvo', 'hombre', 'B', '31434124124', 'wrheofsfdsl;fjs', 'fowhfioefidsco', '2015-06-09'),
('31.731.017', 'Jose', 'Angel', 'hombre', 'B', '31434124124', 'klajdadawaw', 'sfafawfjawpdjf', '2004-07-13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recipe`
--

CREATE TABLE `recipe` (
  `id_recipe` bigint(20) NOT NULL,
  `cedula` varchar(15) NOT NULL,
  `recipe` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `recipe`
--

INSERT INTO `recipe` (`id_recipe`, `cedula`, `recipe`) VALUES
(1, '31.702.100', 'doiwaahfiuawkawbfshfoiawhfahfoa'),
(2, '28068018', '-jnvajivjibdvia\n\n-jvnawijviwvl\n\n-ijbavibviaw');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id_agenda`),
  ADD KEY `cedula` (`cedula`);

--
-- Indices de la tabla `antecedentes`
--
ALTER TABLE `antecedentes`
  ADD KEY `cedula` (`cedula`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`cedula`);

--
-- Indices de la tabla `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`id_recipe`),
  ADD KEY `cedula` (`cedula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id_agenda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `recipe`
--
ALTER TABLE `recipe`
  MODIFY `id_recipe` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `agenda_ibfk_1` FOREIGN KEY (`cedula`) REFERENCES `paciente` (`cedula`);

--
-- Filtros para la tabla `antecedentes`
--
ALTER TABLE `antecedentes`
  ADD CONSTRAINT `antecedentes_ibfk_1` FOREIGN KEY (`cedula`) REFERENCES `paciente` (`cedula`);

--
-- Filtros para la tabla `recipe`
--
ALTER TABLE `recipe`
  ADD CONSTRAINT `recipe_ibfk_1` FOREIGN KEY (`cedula`) REFERENCES `paciente` (`cedula`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
