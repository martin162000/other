-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Št 29.Apr 2021, 19:05
-- Verzia serveru: 10.4.13-MariaDB
-- Verzia PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `banka`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `banka`
--

CREATE TABLE `banka` (
  `ID` int(11) NOT NULL,
  `PIN` char(6) NOT NULL,
  `MENO` varchar(30) NOT NULL,
  `PRIEZVISKO` varchar(30) NOT NULL,
  `ZOSTATOK` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Sťahujem dáta pre tabuľku `banka`
--

INSERT INTO `banka` (`ID`, `PIN`, `MENO`, `PRIEZVISKO`, `ZOSTATOK`) VALUES
(1, '123456', 'Martin', 'Ziduliak', '16021'),
(2, '123457', 'Jozko', 'Mrkvicka', '1500'),
(3, '999999', 'Peter', 'Novotny', '5000'),
(4, '111111', 'Lukas', 'Zakopany', '800'),
(5, '777777', 'Pavol', 'Lovovič', '501'),
(6, '888777', 'Marek', 'Hlavaty', '100');

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `banka`
--
ALTER TABLE `banka`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `PIN` (`PIN`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `banka`
--
ALTER TABLE `banka`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
