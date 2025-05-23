-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2025 at 07:38 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `frecel`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `ID` int(11) NOT NULL,
  `Title` varchar(200) NOT NULL,
  `author` varchar(255) NOT NULL,
  `Category` varchar(200) NOT NULL,
  `Status` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`ID`, `Title`, `author`, `Category`, `Status`) VALUES
(1, 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', 'Available'),
(2, '1984', 'George Orwell', 'Dystopian', 'Available'),
(3, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Classic Literature', 'Available'),
(4, 'Harry Potter and the Sorcerer\'s Stone', 'J.K. Rowling', 'Fantasy', 'Available'),
(5, 'And Then There Were None', 'Agatha Christie', 'Mystery', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `borrow`
--

CREATE TABLE `borrow` (
  `id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `borrow_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrow`
--

INSERT INTO `borrow` (`id`, `book_id`, `student_id`, `borrow_date`, `return_date`, `status`) VALUES
(15, 1, 1, '2025-05-20', '2025-05-20', 'Returned');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `ID` int(11) NOT NULL,
  `Name` varchar(200) NOT NULL,
  `Program` varchar(200) NOT NULL,
  `Year` int(200) NOT NULL,
  `Section` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`ID`, `Name`, `Program`, `Year`, `Section`) VALUES
(1, 'Maria Santos', 'BS Computer Science', 1, 'A'),
(2, 'Juan Dela Cruz', 'BS Information Tech', 2, 'B'),
(3, 'Ana Lopez', 'BS Information Sys', 3, 'C'),
(4, 'Carlo Reyes', 'BS Computer Science', 4, 'A'),
(5, 'Liza Montemayor', 'BS Information Tech', 1, 'B');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(20) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(20) NOT NULL,
  `role` varchar(10) NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'Pending',
  `security_question` varchar(255) DEFAULT NULL,
  `security_answer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `status`, `security_question`, `security_answer`) VALUES
(1, 'Ash', 'Balbon', 'ash1234', '#ASh1234', 'ash@gmail.com', '', '', '', ''),
(2, 'ash', 'balb', 'admin', '1234', 'ash@gmail.com', 'User', '', '', ''),
(3, 'john', 'phil', 'jampil', '1234', 'john@Gmail.com', 'Admin', '', '', ''),
(4, 'queny', 'waskin', 'bebs', 'Waskin123!', 'quenyw@gmail.com', 'User', '', '', ''),
(7, 'markj', 'cortes', 'markj', '0c4b437649a3795150ab30d3b6fcdf426d2d569177d02feb98f0759ceec124c0', 'markj@gmail.com', 'Admin', 'Approved', NULL, NULL),
(9, 'test', 'test', 'test11', '0c4b437649a3795150ab30d3b6fcdf426d2d569177d02feb98f0759ceec124c0', 'test@gmail.com', 'Admin', 'Pending', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `borrow`
--
ALTER TABLE `borrow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `borrow`
--
ALTER TABLE `borrow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrow`
--
ALTER TABLE `borrow`
  ADD CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`ID`),
  ADD CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
