-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2025 at 07:36 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

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
(1, 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', 'Borrowed'),
(2, '1984', 'George Orwell', 'Dystopian', 'Borrowed'),
(3, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Classic Literature', 'Available'),
(4, 'Harry Potter and the Sorcerer\'s Stone', 'J.K. Rowling', 'Fantasy', 'Borrowed'),
(5, 'And Then There Were None', 'Agatha Christie', 'Mystery', 'Borrowed'),
(8, 'Bird', 'Max Collens', 'Story', 'Borrowed'),
(9, 'Flowers', 'John Mae', 'Fantasy', 'Borrowed'),
(10, 'Hello', 'Tweng', 'Fantasy', 'Borrowed'),
(11, 'Sumala', 'Kevin Mors', 'Horror', 'Borrowed'),
(12, 'The god', 'Smith Ong', 'Reed', 'Borrowed'),
(13, 'Happy Feet', 'Mae Pabs', 'Happy', 'Borrowed');

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
(16, 3, 3, '2025-05-22', '2025-05-22', 'Returned'),
(19, 2, 2, '2025-05-22', '2025-05-29', 'Returned'),
(23, 5, 5, '2025-05-22', NULL, 'Borrowed'),
(31, 10, 11, '2025-05-22', '2025-05-22', 'Returned'),
(32, 11, 12, '2025-05-22', NULL, 'Borrowed'),
(33, 12, 2, '2025-05-22', NULL, 'Borrowed'),
(34, 9, 10, '2025-05-22', NULL, 'Borrowed'),
(36, 10, 1, '2025-05-23', '2025-05-29', 'Returned'),
(37, 1, 9, '2025-05-29', NULL, 'Borrowed'),
(38, 1, 9, '2025-05-29', NULL, 'Borrowed'),
(39, 13, 3, '2025-05-29', '2025-05-29', 'Returned'),
(40, 3, 9, '2025-05-29', '2025-05-29', 'Returned'),
(41, 2, 9, '2025-05-29', NULL, 'Borrowed'),
(42, 13, 9, '2025-05-29', '2025-05-29', 'Returned'),
(43, 13, 9, '2025-05-29', NULL, 'Borrowed'),
(44, 10, 9, '2025-05-29', NULL, 'Borrowed');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `log_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `action` text NOT NULL,
  `date_time` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`log_id`, `user_id`, `action`, `date_time`) VALUES
(1, 16, 'User logged in successfully', '2025-05-28 23:18:52'),
(2, 16, 'Failed login attempt: incorrect password', '2025-05-28 23:21:47'),
(3, 16, 'User logged in successfully', '2025-05-28 23:21:54'),
(4, 16, 'User logged in successfully', '2025-05-28 23:25:33'),
(5, 17, 'New user registered: john', '2025-05-28 23:26:27'),
(6, 16, 'User logged in successfully', '2025-05-28 23:38:28'),
(7, 16, 'User logged in successfully', '2025-05-28 23:39:16'),
(8, 16, 'User logged in successfully', '2025-05-28 23:41:22'),
(9, 16, 'Failed login attempt: incorrect password', '2025-05-28 23:42:38'),
(10, 16, 'User logged in successfully', '2025-05-28 23:42:44'),
(11, 16, 'User logged in successfully', '2025-05-28 23:51:40'),
(12, 16, 'Deleted borrow record ID: 15 (Book ID: 1)', '2025-05-28 23:52:21'),
(13, 16, 'Deleted borrow record ID: 35 (Book ID: 13)', '2025-05-28 23:52:55'),
(14, 16, 'User logged in successfully', '2025-05-28 23:57:07'),
(15, 16, 'Deleted borrow record ID: 22 (Book ID: 1)', '2025-05-28 23:57:22'),
(16, 16, 'User logged in successfully', '2025-05-29 00:00:36'),
(17, 16, 'Added borrow record: student ID 9, book ID 1, date 2025-05-29', '2025-05-29 00:01:18'),
(18, 16, 'User logged in successfully', '2025-05-29 00:03:00'),
(19, 16, 'User logged in successfully', '2025-05-29 00:06:04'),
(20, 16, 'Added borrow record: student ID 3, book ID 13, date 2025-05-29', '2025-05-29 00:07:42'),
(22, 16, 'User logged in successfully', '2025-05-29 00:10:23'),
(23, 16, 'Failed login attempt: incorrect password', '2025-05-29 00:48:17'),
(24, 16, 'User logged in successfully', '2025-05-29 00:48:24'),
(25, 16, 'Student ID 9 borrowed book ID 2 on 2025-05-29', '2025-05-29 00:48:44'),
(26, 16, 'User logged in successfully', '2025-05-29 00:50:11'),
(27, 16, 'User logged in successfully', '2025-05-29 00:56:16'),
(28, 16, 'Student ID 9 borrowed book ID 10 on 2025-05-29', '2025-05-29 00:56:32'),
(29, 18, 'Attempted login but account is pending approval', '2025-05-29 00:59:21'),
(30, 18, 'User logged in successfully', '2025-05-29 00:59:45'),
(31, 18, 'User logged in successfully', '2025-05-29 01:10:47'),
(32, 18, 'User logged in successfully', '2025-05-29 01:13:15'),
(33, 18, 'Failed login attempt: incorrect password', '2025-05-29 01:24:00'),
(34, 18, 'User logged in successfully', '2025-05-29 01:24:07'),
(35, 16, 'Failed login attempt: incorrect password', '2025-05-29 01:25:39'),
(36, 16, 'User logged in successfully', '2025-05-29 01:25:48'),
(39, 16, 'Failed login attempt: incorrect password', '2025-05-29 01:28:43'),
(40, 16, 'User logged in successfully', '2025-05-29 01:28:49');

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
(5, 'Liza Montemayor', 'BS Information Tech', 1, 'B'),
(7, 'eric smith', 'BS Information Technology', 4, 'C'),
(8, 'Janphil Escoda', 'BS Information Technology', 1, 'B'),
(9, 'Ash Phil', 'BS Hotel Management', 4, 'E'),
(10, 'Test1', 'BS Criminilogy', 4, 'D'),
(11, 'tiyawawas', 'IT', 2, 'C'),
(12, 'Tweng', 'BIT', 3, 'E'),
(13, 'shane gwapa', 'BSRT', 2, 'A');

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
  `profile_pic` varchar(255) DEFAULT NULL,
  `security_question` varchar(255) DEFAULT NULL,
  `security_answer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `status`, `profile_pic`, `security_question`, `security_answer`) VALUES
(7, 'markj', 'cortes', 'markj', '0c4b437649a3795150ab30d3b6fcdf426d2d569177d02feb98f0759ceec124c0', 'markj@gmail.com', 'Admin', 'Approved', NULL, NULL, NULL),
(10, 'eric', 'erica', 'erica', 'd5c931212a9523fa1f58e8232d3bd5ab3d818b26b1a7643c8bc9f5864560553c', 'erica@gmail.com', 'Admin', 'aCTIVE', NULL, NULL, NULL),
(11, 'test', 'test', 'test', '048c8e6aa263b54aebde3f078df3e96c92f09f3aea39bdb11e0ce071d36dcaf0', 'test2025@gmail.com', 'Admin', 'active', NULL, 'What was the name of your first pet?', 'dolly'),
(12, 'queny', 'waskin', 'queny', 'e533af70fce8547ab846b99ff6b8d98320bbab453c70505a7398bd83770b6426', 'queny@gmail.com', 'Admin', 'active', NULL, 'What was the name of your first pet?', 'bobby'),
(13, 'shane', 'mae', 'shane ', 'd972d414d64beafcdbf9d386b74a24cc3bb79b06b35b1180b0a70b827b8f2fca', 'shanemae@gmail.com', 'Admin', 'ACTIVE', NULL, NULL, NULL),
(14, 'kkarla', 'jane', 'jane', 'a2d679bed5c2f57e3540f88caa01d559c26a0df07df451b9d099d0a84bd5cf05', 'karlajane@gmail.com', 'Admin', 'active', NULL, 'What was the name of your first pet?', 'mon'),
(16, 'John Phil', 'Esconde', 'jampil', '20d8ff2150b414bcbf716d4b1954422a9dcc1b90ac1f6f1951ddc5dc62586f34', 'john@gmail.com', 'Admin', 'Active', 'face-swap.png', 'ads', 'adad'),
(17, 'John', 'Phil', 'john', '20d8ff2150b414bcbf716d4b1954422a9dcc1b90ac1f6f1951ddc5dc62586f34', 'John@gmail.com', 'Admin', 'Pending', NULL, NULL, NULL),
(18, 'John', 'phil', 'staff', '20d8ff2150b414bcbf716d4b1954422a9dcc1b90ac1f6f1951ddc5dc62586f34', 'saff@gmail.com', 'User', 'Active', NULL, NULL, NULL);

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
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `user_id` (`user_id`);

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `borrow`
--
ALTER TABLE `borrow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrow`
--
ALTER TABLE `borrow`
  ADD CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`ID`),
  ADD CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`ID`);

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
