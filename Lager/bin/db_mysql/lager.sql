-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Apr 2018 um 21:47
-- Server-Version: 10.1.9-MariaDB
-- PHP-Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `lager`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(25) COLLATE latin1_german2_ci NOT NULL,
  `password` varchar(25) COLLATE latin1_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`) VALUES
(1, 'ahage', '12345'),
(2, 'usertest', '12345');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `warenausgang`
--

CREATE TABLE `warenausgang` (
  `barCode` varchar(25) COLLATE latin1_german2_ci NOT NULL,
  `ne_Bezeichnung` varchar(50) COLLATE latin1_german2_ci NOT NULL,
  `LagerOrt` varchar(15) COLLATE latin1_german2_ci NOT NULL,
  `Eingangsdatum` varchar(15) COLLATE latin1_german2_ci NOT NULL,
  `Ausgangsdatum` varchar(15) COLLATE latin1_german2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci;

--
-- Daten für Tabelle `warenausgang`
--

INSERT INTO `warenausgang` (`barCode`, `ne_Bezeichnung`, `LagerOrt`, `Eingangsdatum`, `Ausgangsdatum`) VALUES
('SP5048110', 'CXU-10', '2.A.Oben', '24.07.2017', '25.07.2017'),
('SP5048113', 'DTRU-9', '3.A.Unten', '24.07.2017', '24.07.2017'),
('SP5048114', 'CDU-9', '2.A.Unten', '24.07.2017', '24.07.2017'),
('SP5048115', 'CXU-10', '1.A.Oben', '14.06.2017', '24.07.2017'),
('SP5048116', 'CXU-10', '2.A.Mitte', '24.07.2017', '24.07.2017'),
('SP5048117', 'CXU-10', '2.A.Mitte', '23.07.2017', '24.07.2017'),
('SP5048119', '083833A', '3.A.Oben', '24.07.2017', '24.07.2017'),
('SP5048200', '083833A', '3.A.Oben', '24.07.2017', '24.07.2017');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `wareneingang`
--

CREATE TABLE `wareneingang` (
  `barCode` varchar(25) COLLATE latin1_german2_ci NOT NULL,
  `ne_Bezeichnung` varchar(50) COLLATE latin1_german2_ci NOT NULL,
  `LagerOrt` varchar(15) COLLATE latin1_german2_ci NOT NULL,
  `Eingangsdatum` varchar(15) COLLATE latin1_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci;

--
-- Daten für Tabelle `wareneingang`
--

INSERT INTO `wareneingang` (`barCode`, `ne_Bezeichnung`, `LagerOrt`, `Eingangsdatum`) VALUES
('SP5048111', 'DTRU-9', '1.A.Mitte', '24.07.2017'),
('SP5048112', '083833A', '3.A.Oben', '24.07.2017'),
('SP5048113', 'CXU-10', '2.A.Oben', '25.07.2017'),
('SP5048114', 'CXU-10', '2.A.Oben', '25.07.2017'),
('SP5048115', 'CXU-10', '3.A.Mitte', '25.07.2017'),
('SP5048118', '471810A', '2.A.Oben', '11.05.2017');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indizes für die Tabelle `warenausgang`
--
ALTER TABLE `warenausgang`
  ADD PRIMARY KEY (`barCode`);

--
-- Indizes für die Tabelle `wareneingang`
--
ALTER TABLE `wareneingang`
  ADD PRIMARY KEY (`barCode`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
