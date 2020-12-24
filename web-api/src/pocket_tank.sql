-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2020 at 02:51 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pocket_tank`
--

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

CREATE TABLE `matches` (
  `id` int(6) NOT NULL,
  `winner_id` int(6) UNSIGNED NOT NULL,
  `looser_id` int(6) UNSIGNED NOT NULL,
  `winner_score` int(6) NOT NULL,
  `looser_score` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `matches`
--

INSERT INTO `matches` (`id`, `winner_id`, `looser_id`, `winner_score`, `looser_score`) VALUES
(1, 1, 2, 100, 50);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(6) UNSIGNED NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(245) NOT NULL,
  `passwordhash` varchar(128) DEFAULT NULL,
  `score` int(11) NOT NULL DEFAULT 0,
  `imageUrl` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `passwordhash`, `score`, `imageUrl`) VALUES
(1, 'test', 'test@test.com', '81DC9BDB52D04DC20036DBD8313ED055', 100, NULL),
(2, 'testt', 'testt@test.com', 'a8f5f167f44f4964e6c998dee827110c', 0, NULL),
(3, 'hi', 'testft@test.com', 'a8f5f167f44f4964e6c998dee827110c', 0, NULL),
(4, 'testdft', 'testfyt@test.com', 'a8f5f167f44f4964e6c998dee827110c', 0, NULL),
(5, 'teasdtdft', 'tesgggt@test.com', 'a8f5f167f44f4964e6c998dee827110c', 0, NULL),
(6, 'teasuudddtdft', 'tesggsadgt@test.com', 'a8f5f167f44f4964e6c998dee827110c', 0, NULL),
(7, 'teasuudddootdft', 'tesggsaoodgt@test.com', 'a8f5f167f44f4964e6c998dee827110c', 0, NULL),
(8, 'frfrfrfr', 'tefrfrfrt@test.com', 'a8f5f167f44f4964e6c998dee827110c', 0, NULL),
(9, 'frfrfkikik', 'tefrfffft@test.com', 'a8f5f167f44f4964e6c998dee827110c', 0, NULL),
(10, 'testyoyp', 'tessst@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 0, NULL),
(11, 'testujijo', 'test@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 0, NULL),
(12, 'ahmed', 'ahmedjo@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 0, NULL),
(13, 'ahmedi', 'asdasd@test.com', '81dc9bdb52d04dc20036dbd8313ed055', 0, NULL),
(14, 'gtgtg', 'reda@test.com', '81dc9bdb52d04dc20036dbd8313ed055', 0, NULL),
(15, 'bghju', 'gtgdgfdg@gdfgdfg.com', '81dc9bdb52d04dc20036dbd8313ed055', 0, NULL),
(16, 'hjuio', 'test7@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 50, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`id`),
  ADD KEY `looser_id` (`looser_id`),
  ADD KEY `matches_ibfk_1` (`winner_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `matches`
--
ALTER TABLE `matches`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`winner_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
