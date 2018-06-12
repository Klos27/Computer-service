-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2018 at 01:49 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `computer_service`
--

-- --------------------------------------------------------

--
-- Table structure for table `contracts`
--

CREATE TABLE `contracts` (
  `id` int(11) NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `salary` float(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contracts`
--

INSERT INTO `contracts` (`id`, `date_start`, `date_end`, `salary`) VALUES
(8, '2018-06-05', '2018-06-09', 3500.00),
(9, '2018-06-04', '2018-06-11', 5500.00),
(10, '2018-06-04', '2018-06-22', 3200.00);

-- --------------------------------------------------------

--
-- Table structure for table `parts`
--

CREATE TABLE `parts` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `price` float(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `parts`
--

INSERT INTO `parts` (`id`, `name`, `price`) VALUES
(1, 'GTX 1060 6GB', 1200.99),
(2, 'Intel i5-7400', 850.00),
(3, 'AMD RYZEN 5 1600X', 982.87),
(4, 'GTX 1050 3GB', 783.54),
(5, 'GTX 1050 6GB2', 739.00),
(6, 'GTX 1080', 4555.00),
(7, 'GTX 1090 5GB', 3670.00),
(8, 'GTX 1090 12GB', 9000.00);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int(11) NOT NULL,
  `id_service_request` int(11) NOT NULL,
  `amount` float(8,2) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `creation_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`id`, `id_service_request`, `amount`, `status`, `creation_date`) VALUES
(3, 6, 1350.00, 1, '2018-05-28'),
(11, 4, 2242.43, 1, '2018-05-29'),
(16, 1, 10135.60, 1, '2018-06-11'),
(17, 8, 874.00, 1, '2018-06-11'),
(18, 12, 2439.98, 1, '2018-06-11'),
(19, 13, 226.00, 1, '2018-06-11'),
(20, 14, 119.00, 1, '2018-06-11'),
(21, 15, 874.00, 1, '2018-06-11'),
(22, 16, 1055.27, 1, '2018-06-12');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` varchar(4096) NOT NULL,
  `price` float(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`id`, `name`, `description`, `price`) VALUES
(1, 'Computer restart', 'Restart the computer', 6.00),
(2, 'Reinstall windows', 'Reinstall the operating system', 50.00),
(3, 'Replace graphic card', 'Replace graphic card for the new one', 20.00),
(4, 'Clean PC', 'Clean pc with thermal paste replacement', 101.00),
(5, 'Test PC performance', 'Test PC performance in 1 hour test', 75.34),
(6, 'Computer\'s temperature test', 'Test computer\'s temperatures in 1 hour test', 75.00),
(7, 'Reinstall MS Office', 'Reinstall MS Office program', 45.73);

-- --------------------------------------------------------

--
-- Table structure for table `service_request`
--

CREATE TABLE `service_request` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `description` varchar(4096) NOT NULL,
  `status` int(1) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_request`
--

INSERT INTO `service_request` (`id`, `id_client`, `description`, `status`, `start_date`, `end_date`) VALUES
(1, 3, 'acer\n12313\ntestowy', 4, '2018-04-17', '2018-04-17'),
(3, 4, 'ZÓŁÓŁĄŚŁĄŚŁÓ\nTESDŁŁÓĄÓX\nŹÓÓĄŁŁŁDAs', 3, '2018-05-14', '2018-05-14'),
(4, 4, 'DELL\nKNYCTV1\nMój komputer nie działa, nie wiem co mu dolega.\n\nenter x2\n\nenter x2\n\nlorem ipsum:\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse aliquam dolor at consequat scelerisque. Suspendisse fermentum ex ac arcu finibus fringilla. Integer nec elit luctus, aliquam justo quis, laoreet eros. Integer dignissim sapien non tellus cursus suscipit nec ac ante. Phasellus tortor nunc, gravida non purus a, auctor varius ligula. Fusce ultrices, leo sed rutrum cursus, sem diam egestas sapien, sed cursus augue arcu ac nisl. Aenean eget viverra magna. Curabitur eget sem posuere.', 3, '2018-05-14', '2018-05-14'),
(5, 4, 'Dell Inspiron\nZXCVBNMD24\nNie działa, ponownie.', 1, '2018-05-15', '2018-05-15'),
(6, 8, 'LENOVO\nFCVBNDGH\nMy computer does not work properly.', 4, '2018-05-15', '2018-05-15'),
(7, 4, 'DELL \nfijasifj\nNie działa', 1, '2018-05-15', '2018-05-15'),
(8, 4, 'NIU PC\nGGHFGL\nNie działa - test', 4, '2018-05-28', '2018-05-28'),
(9, 4, 'wdhj\njhdfus\ntest', 1, '2018-05-29', '2018-05-29'),
(10, 17, 'DELLOS\nKNYCTTDC2\nKomputer nie działa normalnie, leci z niego biały dym.', 3, '2018-06-06', '2018-06-06'),
(12, 4, 'DELL\nHGVDCF\nMy computer doesn\'t work! HELP ME!', 4, '2018-06-11', '2018-06-11'),
(13, 17, 'LAPTOP\nASUSOS\nNIE DZIAŁA!', 4, '2018-06-11', '2018-06-11'),
(14, 4, 'DELL\nSERIAL5\nHELP', 4, '2018-06-11', '2018-06-11'),
(15, 4, 'DELL\nSERIAL6\nHELP2', 4, '2018-06-11', '2018-06-11'),
(16, 4, 'LAST\nONETWO\nComputer crash', 4, '2018-06-12', '2018-06-12');

-- --------------------------------------------------------

--
-- Table structure for table `service_request_employee`
--

CREATE TABLE `service_request_employee` (
  `id_service_request` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_request_employee`
--

INSERT INTO `service_request_employee` (`id_service_request`, `id_employee`) VALUES
(3, 5),
(1, 5),
(6, 5),
(7, 5),
(8, 13),
(4, 5),
(9, 13),
(11, 5),
(12, 13),
(5, 13),
(13, 5),
(14, 5),
(15, 5),
(16, 5);

-- --------------------------------------------------------

--
-- Table structure for table `service_request_messages`
--

CREATE TABLE `service_request_messages` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_service_request` int(11) NOT NULL,
  `date` date NOT NULL,
  `content` varchar(4096) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_request_messages`
--

INSERT INTO `service_request_messages` (`id`, `id_user`, `id_service_request`, `date`, `content`) VALUES
(1, 4, 4, '2018-05-28', 'I jak z tym zgłoszeniem?'),
(2, 4, 4, '2018-05-28', 'Chat chyba działa ok?'),
(3, 5, 4, '2018-05-28', 'No robi się'),
(4, 5, 4, '2018-05-28', 'Spokojnie'),
(5, 4, 9, '2018-05-29', 'test'),
(6, 5, 9, '2018-05-29', 'test2'),
(7, 17, 10, '2018-06-06', 'POMOCCCYYY'),
(8, 17, 10, '2018-06-06', ':('),
(9, 5, 10, '2018-06-06', ':O'),
(10, 4, 12, '2018-06-11', 'HELP PLEASE!'),
(11, 13, 12, '2018-06-11', '<LIKE>');

-- --------------------------------------------------------

--
-- Table structure for table `service_request_parts`
--

CREATE TABLE `service_request_parts` (
  `id_service_request` int(11) NOT NULL,
  `id_part` int(11) NOT NULL,
  `part_price` float(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_request_parts`
--

INSERT INTO `service_request_parts` (`id_service_request`, `id_part`, `part_price`) VALUES
(4, 1, 999.99),
(4, 3, 1050.44),
(1, 7, 9000.00),
(1, 3, 982.87),
(8, 2, 850.00),
(9, 6, 4555.00),
(12, 1, 1200.99),
(12, 1, 1200.99),
(15, 2, 850.00),
(16, 4, 783.54);

-- --------------------------------------------------------

--
-- Table structure for table `service_request_services`
--

CREATE TABLE `service_request_services` (
  `id_service_request` int(11) NOT NULL,
  `id_service` int(11) NOT NULL,
  `service_price` float(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_request_services`
--

INSERT INTO `service_request_services` (`id_service_request`, `id_service`, `service_price`) VALUES
(4, 2, 50.00),
(4, 1, 6.00),
(4, 3, 30.00),
(4, 4, 100.00),
(4, 1, 6.00),
(1, 6, 75.00),
(1, 1, 6.00),
(1, 1, 6.00),
(1, 3, 20.00),
(1, 7, 45.73),
(8, 1, 6.00),
(8, 1, 6.00),
(8, 1, 6.00),
(8, 1, 6.00),
(9, 4, 101.00),
(9, 1, 6.00),
(9, 5, 75.34),
(9, 5, 75.34),
(9, 3, 20.00),
(12, 3, 20.00),
(12, 1, 6.00),
(12, 1, 6.00),
(12, 1, 6.00),
(13, 2, 50.00),
(13, 6, 75.00),
(13, 4, 101.00),
(14, 4, 101.00),
(14, 1, 6.00),
(14, 1, 6.00),
(14, 1, 6.00),
(15, 1, 6.00),
(15, 1, 6.00),
(15, 1, 6.00),
(15, 1, 6.00),
(16, 4, 101.00),
(16, 2, 50.00),
(16, 7, 45.73),
(16, 6, 75.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(250) NOT NULL,
  `role` int(1) NOT NULL DEFAULT '0',
  `address` varchar(512) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `password`, `email`, `role`, `address`, `phone`) VALUES
(3, 'demos123', 'demos123', '0acf684e50483d64be22d728595e9c5c565d753e625839ae3affd6a1e9d7aba1', 'demos123@123.pl', 0, NULL, NULL),
(4, 'User', 'User_Last', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'user@user.com', 0, 'Ul. Wawszawska 24, 22-222 Kraków', '222-333-455'),
(5, 'Worker', 'Worker', '87eba76e7f3164534045ba922e7770fb58bbd14ad732bbf5ba6f11cc56989e6e', 'worker@worker.com', 3, 'Adres', ''),
(6, 'admin', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin@admin.com', 1, NULL, NULL),
(7, 'foreman', 'foreman', 'c6de3c105315372cbbc427cb3a96544cb9edc0f91b557deccfe10442fad08854', 'foreman@foreman.com', 2, NULL, NULL),
(8, 'userv2', 'userv2', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'userv2@user.com', 0, 'Adres PK edu', '333-444-555'),
(13, 'worker2', 'worker2', '87eba76e7f3164534045ba922e7770fb58bbd14ad732bbf5ba6f11cc56989e6e', 'worker2@worker.com', 3, NULL, NULL),
(17, 'Klos', 'Labs', 'b8510932dad3ddf0fc34661a0caf6674e5c0d672e3930c6a736424d4df0e8016', 'wp@gmail.com', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users_contracts`
--

CREATE TABLE `users_contracts` (
  `id_user` int(11) NOT NULL,
  `id_contract` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_contracts`
--

INSERT INTO `users_contracts` (`id_user`, `id_contract`) VALUES
(5, 8),
(7, 9),
(13, 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contracts`
--
ALTER TABLE `contracts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parts`
--
ALTER TABLE `parts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_service_request` (`id_service_request`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service_request`
--
ALTER TABLE `service_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_client`);

--
-- Indexes for table `service_request_employee`
--
ALTER TABLE `service_request_employee`
  ADD KEY `id_service_request` (`id_service_request`),
  ADD KEY `id_employee` (`id_employee`);

--
-- Indexes for table `service_request_messages`
--
ALTER TABLE `service_request_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_service_request` (`id_service_request`);

--
-- Indexes for table `service_request_parts`
--
ALTER TABLE `service_request_parts`
  ADD KEY `id_service_request` (`id_service_request`),
  ADD KEY `id_part` (`id_part`);

--
-- Indexes for table `service_request_services`
--
ALTER TABLE `service_request_services`
  ADD KEY `id_service_request` (`id_service_request`),
  ADD KEY `id_service` (`id_service`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_contracts`
--
ALTER TABLE `users_contracts`
  ADD PRIMARY KEY (`id_user`,`id_contract`),
  ADD KEY `id_contract` (`id_contract`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contracts`
--
ALTER TABLE `contracts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `parts`
--
ALTER TABLE `parts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `service_request`
--
ALTER TABLE `service_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `service_request_messages`
--
ALTER TABLE `service_request_messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`id_service_request`) REFERENCES `service_request` (`id`);

--
-- Constraints for table `service_request`
--
ALTER TABLE `service_request`
  ADD CONSTRAINT `service_request_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `users` (`id`);

--
-- Constraints for table `service_request_employee`
--
ALTER TABLE `service_request_employee`
  ADD CONSTRAINT `service_request_employee_ibfk_1` FOREIGN KEY (`id_employee`) REFERENCES `users` (`id`);

--
-- Constraints for table `service_request_messages`
--
ALTER TABLE `service_request_messages`
  ADD CONSTRAINT `service_request_messages_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `service_request_messages_ibfk_2` FOREIGN KEY (`id_service_request`) REFERENCES `service_request` (`id`);

--
-- Constraints for table `service_request_parts`
--
ALTER TABLE `service_request_parts`
  ADD CONSTRAINT `service_request_parts_ibfk_1` FOREIGN KEY (`id_part`) REFERENCES `parts` (`id`),
  ADD CONSTRAINT `service_request_parts_ibfk_2` FOREIGN KEY (`id_service_request`) REFERENCES `service_request` (`id`);

--
-- Constraints for table `service_request_services`
--
ALTER TABLE `service_request_services`
  ADD CONSTRAINT `service_request_services_ibfk_1` FOREIGN KEY (`id_service`) REFERENCES `services` (`id`),
  ADD CONSTRAINT `service_request_services_ibfk_2` FOREIGN KEY (`id_service_request`) REFERENCES `service_request` (`id`);

--
-- Constraints for table `users_contracts`
--
ALTER TABLE `users_contracts`
  ADD CONSTRAINT `users_contracts_ibfk_1` FOREIGN KEY (`id_contract`) REFERENCES `contracts` (`id`),
  ADD CONSTRAINT `users_contracts_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
