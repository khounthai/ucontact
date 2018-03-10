-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 10 mars 2018 à 17:24
-- Version du serveur :  10.1.28-MariaDB
-- Version de PHP :  7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ucontact`
--

-- --------------------------------------------------------

--
-- Structure de la table `champ`
--

CREATE TABLE `champ` (
  `idchamp` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `multivaleur` tinyint(1) DEFAULT NULL,
  `iddatatype` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `champ`
--

INSERT INTO `champ` (`idchamp`, `libelle`, `multivaleur`, `iddatatype`) VALUES
(1, 'Nom', 0, 1),
(2, 'Prénom', 0, 1),
(3, 'Portable', 0, 1),
(4, 'Email', 0, 5),
(5, 'Ville', 0, 1),
(6, 'Pays', 0, 1),
(7, 'Langue', 1, 1),
(8, 'Profession', 0, 1),
(9, 'Compétence', 1, 1),
(10, 'Taille', 0, 3),
(11, 'Date de naissance', 0, 4),
(12, 'CP', 0, 1),
(13, 'Téléphone fixe', 0, 2),
(14, 'Adresse', 0, 1),
(15, 'Civilite', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

CREATE TABLE `contact` (
  `idcontact` int(11) NOT NULL,
  `dtcreation` date DEFAULT NULL,
  `favoris` tinyint(1) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `actif` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contact`
--

INSERT INTO `contact` (`idcontact`, `dtcreation`, `favoris`, `iduser`, `actif`) VALUES
(6, '2018-02-06', 0, 1, 0),
(7, '2018-02-06', 0, 1, 1),
(9, '2018-02-07', 0, 1, 1),
(12, '2018-02-08', 0, 1, 1),
(15, '2018-02-08', 0, 1, 1),
(16, '2018-03-10', 0, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `datatype`
--

CREATE TABLE `datatype` (
  `iddatatype` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `regex` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `datatype`
--

INSERT INTO `datatype` (`iddatatype`, `libelle`, `regex`) VALUES
(1, 'TEXTE', NULL),
(2, 'ENTIER', '[0-9]*'),
(3, 'DECIMAL', NULL),
(4, 'DATE', NULL),
(5, 'EMAIL', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `donnees`
--

CREATE TABLE `donnees` (
  `iddonnee` int(11) NOT NULL,
  `dtenregistrement` datetime DEFAULT NULL,
  `valeur` varchar(255) DEFAULT NULL,
  `idcontact` int(11) DEFAULT NULL,
  `idchamp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `donnees`
--

INSERT INTO `donnees` (`iddonnee`, `dtenregistrement`, `valeur`, `idcontact`, `idchamp`) VALUES
(20, '2018-02-06 00:00:00', 'John', 6, 1),
(21, '2018-02-06 00:00:00', 'Doe', 6, 2),
(22, '2018-02-06 00:00:00', 'fe', 6, 14),
(23, '2018-02-06 00:00:00', 'f', 6, 12),
(24, '2018-02-06 00:00:00', '', 6, 5),
(25, '2018-02-06 00:00:00', 'zefze', 6, 6),
(26, '2018-02-06 00:00:00', '', 6, 13),
(27, '2018-02-06 00:00:00', 'fze', 6, 3),
(28, '2018-02-06 00:00:00', '', 6, 4),
(29, '2018-02-06 00:00:00', 'aaaaaaaaa', 7, 1),
(30, '2018-02-06 00:00:00', 'bbbbbbbbbbb', 7, 2),
(31, '2018-02-06 00:00:00', '', 7, 14),
(32, '2018-02-06 00:00:00', '', 7, 12),
(33, '2018-02-06 00:00:00', 'ert', 7, 5),
(34, '2018-02-06 00:00:00', '', 7, 6),
(35, '2018-02-06 00:00:00', 'rsrserer', 7, 13),
(36, '2018-02-06 00:00:00', 't', 7, 3),
(37, '2018-02-06 00:00:00', '', 7, 4),
(47, '2018-02-07 00:00:00', 'eaz', 9, 1),
(48, '2018-02-07 00:00:00', 'aza', 9, 2),
(49, '2018-02-07 00:00:00', '', 9, 14),
(50, '2018-02-07 00:00:00', 'eaeza', 9, 12),
(51, '2018-02-07 00:00:00', '', 9, 5),
(52, '2018-02-07 00:00:00', '', 9, 6),
(53, '2018-02-07 00:00:00', '', 9, 13),
(54, '2018-02-07 00:00:00', 'daz', 9, 3),
(55, '2018-02-07 00:00:00', '', 9, 4),
(65, '2018-02-08 00:00:00', 'John', 12, 1),
(66, '2018-02-08 00:00:00', 'Doe', 12, 2),
(67, '2018-02-08 00:00:00', '1 rue de la paix', 12, 14),
(68, '2018-02-08 00:00:00', '75000', 12, 12),
(69, '2018-02-08 00:00:00', 'Paris', 12, 5),
(70, '2018-02-08 00:00:00', 'France', 12, 6),
(71, '2018-02-08 00:00:00', '01-02-03-04-05-06', 12, 13),
(72, '2018-02-08 00:00:00', '06-11-22-33-44-55', 12, 3),
(73, '2018-02-08 00:00:00', 'john@yahoo.com', 12, 4),
(75, '2018-02-08 00:00:00', 'BABA', 6, 1),
(94, '2018-02-08 00:00:00', 'sdfnb', 15, 1),
(95, '2018-02-08 00:00:00', '', 15, 2),
(96, '2018-02-08 00:00:00', 'bnvbn', 15, 14),
(97, '2018-02-08 00:00:00', '', 15, 12),
(98, '2018-02-08 00:00:00', 'vb', 15, 5),
(99, '2018-02-08 00:00:00', '', 15, 6),
(100, '2018-02-08 00:00:00', 'nvbnvbvb', 15, 13),
(101, '2018-02-08 00:00:00', '', 15, 3),
(102, '2018-02-08 00:00:00', '', 15, 4),
(103, '2018-02-08 00:00:00', 'John', 12, 1),
(112, '2018-02-08 14:39:25', 'John', 12, 1),
(113, '2018-02-08 14:39:25', 'Doe', 12, 2),
(114, '2018-02-08 14:39:25', '35 rue la gare', 12, 14),
(115, '2018-02-08 14:39:25', '51100', 12, 12),
(116, '2018-02-08 14:39:25', 'REIMS', 12, 5),
(117, '2018-02-08 14:39:25', 'France', 12, 6),
(118, '2018-02-08 14:39:26', '032601020304', 12, 13),
(119, '2018-02-08 14:39:26', '060102030405', 12, 3),
(120, '2018-02-08 14:39:26', 'john@yahoo.com', 12, 4),
(121, '2018-02-08 14:46:07', 'John', 12, 1),
(122, '2018-02-08 14:46:07', 'Doe', 12, 2),
(123, '2018-02-08 14:46:07', '35 allée de larbre', 12, 14),
(124, '2018-02-08 14:46:07', '26000', 12, 12),
(125, '2018-02-08 14:46:07', 'New York', 12, 5),
(126, '2018-02-08 14:46:07', 'USA', 12, 6),
(127, '2018-02-08 14:46:07', '000102030405', 12, 13),
(128, '2018-02-08 14:46:08', '000602030405', 12, 3),
(129, '2018-02-08 14:46:08', 'john-usa@yahoo.com', 12, 4),
(130, '2018-02-08 16:13:36', 'John', 12, 1),
(131, '2018-02-08 16:13:36', 'Doe', 12, 2),
(132, '2018-02-08 16:13:36', '15 impasse du malade', 12, 14),
(133, '2018-02-08 16:13:36', '65497', 12, 12),
(134, '2018-02-08 16:13:36', 'PEKIN', 12, 5),
(135, '2018-02-08 16:13:36', 'CHINE', 12, 6),
(136, '2018-02-08 16:13:36', '112233445566', 12, 13),
(137, '2018-02-08 16:13:36', '060709080909', 12, 3),
(138, '2018-02-08 16:13:36', 'john-chine@yahoo.com', 12, 4),
(139, '2018-02-08 16:36:07', 'John', 12, 1),
(140, '2018-02-08 16:36:07', 'Doe', 12, 2),
(141, '2018-02-08 16:36:07', '15 rue de la glace', 12, 14),
(142, '2018-02-08 16:36:07', '7000', 12, 12),
(143, '2018-02-08 16:36:07', 'LONDRE', 12, 5),
(144, '2018-02-08 16:36:07', 'ANGLETERRE', 12, 6),
(145, '2018-02-08 16:36:07', '112233445566', 12, 13),
(146, '2018-02-08 16:36:07', '060709080909', 12, 3),
(147, '2018-02-08 16:36:07', 'john-chine@yahoo.com', 12, 4),
(148, '2018-02-09 11:04:09', 'Nom15', 15, 1),
(149, '2018-02-09 11:04:09', 'Prenom15', 15, 2),
(150, '2018-02-09 11:04:09', '15 rue de PPPP', 15, 14),
(151, '2018-02-09 11:04:09', '75000', 15, 12),
(152, '2018-02-09 11:04:09', 'PARIS', 15, 5),
(153, '2018-02-09 11:04:09', 'FRANCE', 15, 6),
(154, '2018-02-09 11:04:09', '0102030405', 15, 13),
(155, '2018-02-09 11:04:09', '0607080902', 15, 3),
(156, '2018-02-09 11:04:09', 'nom15@yahoo.fr', 15, 4),
(157, '2018-02-09 11:04:39', 'Nom15', 15, 1),
(158, '2018-02-09 11:04:39', 'Prenom15', 15, 2),
(159, '2018-02-09 11:04:39', '15 rue de AAAAA', 15, 14),
(160, '2018-02-09 11:04:39', '51100', 15, 12),
(161, '2018-02-09 11:04:39', 'PARIS', 15, 5),
(162, '2018-02-09 11:04:39', 'REIMS', 15, 6),
(163, '2018-02-09 11:04:39', '0102030405', 15, 13),
(164, '2018-02-09 11:04:39', '0607080902', 15, 3),
(165, '2018-02-09 11:04:39', 'nom15@yahoo.fr', 15, 4),
(166, '2018-02-09 11:07:23', 'Nom15', 15, 1),
(167, '2018-02-09 11:07:23', 'Prenom15', 15, 2),
(168, '2018-02-09 11:07:23', '15 rue de AAAAA', 15, 14),
(169, '2018-02-09 11:07:23', '69000', 15, 12),
(170, '2018-02-09 11:07:23', 'LYON', 15, 5),
(171, '2018-02-09 11:07:23', 'FRANCE', 15, 6),
(172, '2018-02-09 11:07:24', '04102030405', 15, 13),
(173, '2018-02-09 11:07:24', '0607080902', 15, 3),
(174, '2018-02-09 11:07:24', 'nom15@lyon.fr', 15, 4),
(175, '2018-02-09 11:10:39', 'Nom15', 15, 1),
(176, '2018-02-09 11:10:39', 'Prenom15', 15, 2),
(177, '2018-02-09 11:10:39', '15 rue de AAAAA', 15, 14),
(178, '2018-02-09 11:10:39', '69000', 15, 12),
(179, '2018-02-09 11:10:39', 'LYON', 15, 5),
(180, '2018-02-09 11:10:39', 'FRANCE', 15, 6),
(181, '2018-02-09 11:10:39', '1111111111', 15, 13),
(182, '2018-02-09 11:10:39', '222222222222', 15, 3),
(183, '2018-02-09 11:10:39', 'nom15@lyon.fr', 15, 4),
(184, '2018-02-09 11:11:08', 'Nom15', 15, 1),
(185, '2018-02-09 11:11:08', 'Prenom15', 15, 2),
(186, '2018-02-09 11:11:08', '15 rue de AAAAA', 15, 14),
(187, '2018-02-09 11:11:08', '69000', 15, 12),
(188, '2018-02-09 11:11:08', 'LYON', 15, 5),
(189, '2018-02-09 11:11:08', 'FRANCE', 15, 6),
(190, '2018-02-09 11:11:09', '44444', 15, 13),
(191, '2018-02-09 11:11:09', '555555555', 15, 3),
(192, '2018-02-09 11:11:09', 'nom15@lyon.fr', 15, 4),
(193, '2018-02-09 11:18:06', 'Nom15', 15, 1),
(194, '2018-02-09 11:18:07', 'Prenom15', 15, 2),
(195, '2018-02-09 11:18:07', '15 rue de AAAAA', 15, 14),
(196, '2018-02-09 11:18:07', '69000', 15, 12),
(197, '2018-02-09 11:18:07', 'LYON', 15, 5),
(198, '2018-02-09 11:18:07', 'FRANCE', 15, 6),
(199, '2018-02-09 11:18:07', '888', 15, 13),
(200, '2018-02-09 11:18:07', '9', 15, 3),
(201, '2018-02-09 11:18:07', '9m15@lyon.fr', 15, 4),
(202, '2018-02-09 11:20:10', 'Nom15', 15, 1),
(203, '2018-02-09 11:20:10', 'Prenom15', 15, 2),
(204, '2018-02-09 11:20:10', '15 rue de AAAAA', 15, 14),
(205, '2018-02-09 11:20:10', '69000', 15, 12),
(206, '2018-02-09 11:20:10', 'LYON', 15, 5),
(207, '2018-02-09 11:20:10', 'FRANCE', 15, 6),
(208, '2018-02-09 11:20:10', '0000', 15, 13),
(209, '2018-02-09 11:20:10', '1111', 15, 3),
(210, '2018-02-09 11:20:10', 'llll5@lyon.fr', 15, 4),
(211, '2018-02-09 11:22:23', 'Nom15', 15, 1),
(212, '2018-02-09 11:22:23', 'Prenom15', 15, 2),
(213, '2018-02-09 11:22:23', '15 rue de BB', 15, 14),
(214, '2018-02-09 11:22:23', '69000', 15, 12),
(215, '2018-02-09 11:22:23', 'LYON', 15, 5),
(216, '2018-02-09 11:22:23', 'FRANCE', 15, 6),
(217, '2018-02-09 11:22:23', '323223', 15, 13),
(218, '2018-02-09 11:22:23', '33333', 15, 3),
(219, '2018-02-09 11:22:23', 'EERT5@lyon.fr', 15, 4),
(220, '2018-02-09 11:46:50', 'Nom15', 15, 1),
(221, '2018-02-09 11:46:50', 'Prenom15', 15, 2),
(222, '2018-02-09 11:46:50', '15 rue de DDD', 15, 14),
(223, '2018-02-09 11:46:50', '51100', 15, 12),
(224, '2018-02-09 11:46:50', 'TINQUEUX', 15, 5),
(225, '2018-02-09 11:46:50', 'FRANCE', 15, 6),
(226, '2018-02-09 11:46:50', '232323', 15, 13),
(227, '2018-02-09 11:46:50', '323233', 15, 3),
(228, '2018-02-09 11:46:50', 'ERRTT5@lyon.fr', 15, 4),
(229, '2018-02-09 11:59:16', 'Nom15', 15, 1),
(230, '2018-02-09 11:59:16', 'Prenom15', 15, 2),
(231, '2018-02-09 11:59:16', '25 allée des RRR', 15, 14),
(232, '2018-02-09 11:59:16', '77000', 15, 12),
(233, '2018-02-09 11:59:16', 'Disney', 15, 5),
(234, '2018-02-09 11:59:16', 'FRANCE', 15, 6),
(235, '2018-02-09 11:59:16', '010101', 15, 13),
(236, '2018-02-09 11:59:16', '020202', 15, 3),
(237, '2018-02-09 11:59:16', 'disney@lyon.fr', 15, 4),
(238, '2018-02-09 12:01:05', 'Nom15', 15, 1),
(239, '2018-02-09 12:01:05', 'Prenom15', 15, 2),
(240, '2018-02-09 12:01:05', '30 place de la paix', 15, 14),
(241, '2018-02-09 12:01:05', '69000', 15, 12),
(242, '2018-02-09 12:01:05', 'Lyon', 15, 5),
(243, '2018-02-09 12:01:05', 'FRANCE', 15, 6),
(244, '2018-02-09 12:01:05', '33333333', 15, 13),
(245, '2018-02-09 12:01:05', '9898988', 15, 3),
(246, '2018-02-09 12:01:05', 'disney@yahooo.fr', 15, 4),
(247, '2018-02-09 12:04:19', 'nom 7', 7, 1),
(248, '2018-02-09 12:04:19', 'prénom 7', 7, 2),
(249, '2018-02-09 12:04:19', '1 rue de la paix', 7, 14),
(250, '2018-02-09 12:04:19', '51100', 7, 12),
(251, '2018-02-09 12:04:19', 'REIMS', 7, 5),
(252, '2018-02-09 12:04:19', 'FRANCE', 7, 6),
(253, '2018-02-09 12:04:19', '01-02-03-04-05-06', 7, 13),
(254, '2018-02-09 12:04:19', '06-11-22-33-44-55', 7, 3),
(255, '2018-02-09 12:04:19', 'nom7@yahoo.net', 7, 4),
(256, '2018-02-09 12:05:36', 'Nom15_C', 15, 1),
(257, '2018-02-09 12:05:36', 'Prenom15_B', 15, 2),
(258, '2018-02-09 12:05:36', '30 allée des puits', 15, 14),
(259, '2018-02-09 12:05:36', '777777', 15, 12),
(260, '2018-02-09 12:05:36', 'Madrid', 15, 5),
(261, '2018-02-09 12:05:36', 'Espagne', 15, 6),
(262, '2018-02-09 12:05:36', '0505050', 15, 13),
(263, '2018-02-09 12:05:36', '0606006', 15, 3),
(264, '2018-02-09 12:05:36', 'nom15@yahoo.net', 15, 4),
(265, '2018-03-10 15:16:54', NULL, 7, 15),
(266, '2018-03-10 15:16:54', 'nom 7', 7, 1),
(267, '2018-03-10 15:16:54', 'prénom 7', 7, 2),
(268, '2018-03-10 15:16:54', '1991-01-09', 7, 11),
(269, '2018-03-10 15:16:54', '1 rue de la paix', 7, 14),
(270, '2018-03-10 15:16:54', '51100', 7, 12),
(271, '2018-03-10 15:16:54', 'REIMS', 7, 5),
(272, '2018-03-10 15:16:54', 'FRANCE', 7, 6),
(273, '2018-03-10 15:16:54', '010203040506', 7, 13),
(274, '2018-03-10 15:16:54', '06-11-22-33-44-55', 7, 3),
(275, '2018-03-10 15:16:54', 'nom7@yahoo.net', 7, 4),
(276, '2018-03-10 15:27:15', NULL, 7, 15),
(277, '2018-03-10 15:27:15', 'nom 7', 7, 1),
(278, '2018-03-10 15:27:15', 'prénom 7', 7, 2),
(279, '2018-03-10 15:27:15', '1991-01-09', 7, 11),
(280, '2018-03-10 15:27:15', '1 rue de la paix', 7, 14),
(281, '2018-03-10 15:27:15', '51100', 7, 12),
(282, '2018-03-10 15:27:16', 'REIMS', 7, 5),
(283, '2018-03-10 15:27:16', 'FRANCE', 7, 6),
(284, '2018-03-10 15:27:16', '010203040506', 7, 13),
(285, '2018-03-10 15:27:16', '06-11-22-33-44-55', 7, 3),
(286, '2018-03-10 15:27:16', 'nom7@yahoo.net', 7, 4),
(287, '2018-03-10 15:38:47', 'Mlle.', 7, 15),
(288, '2018-03-10 15:38:48', 'nom 7', 7, 1),
(289, '2018-03-10 15:38:48', 'prénom 7', 7, 2),
(290, '2018-03-10 15:38:48', '1991-01-09', 7, 11),
(291, '2018-03-10 15:38:48', '1 rue de la paix', 7, 14),
(292, '2018-03-10 15:38:48', '51100', 7, 12),
(293, '2018-03-10 15:38:48', 'REIMS', 7, 5),
(294, '2018-03-10 15:38:48', 'FRANCE', 7, 6),
(295, '2018-03-10 15:38:48', '010203040506', 7, 13),
(296, '2018-03-10 15:38:48', '06-11-22-33-44-55', 7, 3),
(297, '2018-03-10 15:38:48', 'nom7@yahoo.net', 7, 4),
(298, '2018-03-10 15:40:32', 'Mme.', 7, 15),
(299, '2018-03-10 15:40:32', 'nom 7', 7, 1),
(300, '2018-03-10 15:40:32', 'prénom 7', 7, 2),
(301, '2018-03-10 15:40:33', '1991-01-09', 7, 11),
(302, '2018-03-10 15:40:33', '1 rue de la paix', 7, 14),
(303, '2018-03-10 15:40:33', '51100', 7, 12),
(304, '2018-03-10 15:40:33', 'REIMS', 7, 5),
(305, '2018-03-10 15:40:33', 'FRANCE', 7, 6),
(306, '2018-03-10 15:40:33', '010203040506', 7, 13),
(307, '2018-03-10 15:40:33', '06-11-22-33-44-55', 7, 3),
(308, '2018-03-10 15:40:33', 'nom7@yahoo.net', 7, 4),
(375, '2018-03-10 17:02:30', 'Mme.', 7, 15),
(376, '2018-03-10 17:02:30', 'nom 7', 7, 1),
(377, '2018-03-10 17:02:30', 'prénom 7', 7, 2),
(378, '2018-03-10 17:02:30', '1991-01-09', 7, 11),
(379, '2018-03-10 17:02:30', '1 rue de la paix', 7, 14),
(380, '2018-03-10 17:02:30', '51100', 7, 12),
(381, '2018-03-10 17:02:30', 'REIMS', 7, 5),
(382, '2018-03-10 17:02:30', 'FRANCE', 7, 6),
(383, '2018-03-10 17:02:30', '010203040506', 7, 13),
(384, '2018-03-10 17:02:31', '06-11-22-33-44-55', 7, 3),
(385, '2018-03-10 17:02:31', 'nom7@yahoo.net', 7, 4),
(386, '2018-03-10 17:02:37', 'M.', 12, 15),
(387, '2018-03-10 17:02:37', 'John', 12, 1),
(388, '2018-03-10 17:02:37', 'Doe', 12, 2),
(389, '2018-03-10 17:02:37', '', 12, 11),
(390, '2018-03-10 17:02:37', '15 rue de la glace', 12, 14),
(391, '2018-03-10 17:02:37', '7000', 12, 12),
(392, '2018-03-10 17:02:37', 'LONDRE', 12, 5),
(393, '2018-03-10 17:02:37', 'ANGLETERRE', 12, 6),
(394, '2018-03-10 17:02:37', '112233445566', 12, 13),
(395, '2018-03-10 17:02:37', '060709080909', 12, 3),
(396, '2018-03-10 17:02:37', 'john-chine@yahoo.com', 12, 4),
(397, '2018-03-10 17:03:34', 'Mlle.', 9, 15),
(398, '2018-03-10 17:03:34', 'eaz', 9, 1),
(399, '2018-03-10 17:03:34', 'aza', 9, 2),
(400, '2018-03-10 17:03:34', '', 9, 11),
(401, '2018-03-10 17:03:34', '', 9, 14),
(402, '2018-03-10 17:03:34', 'eaeza', 9, 12),
(403, '2018-03-10 17:03:34', '', 9, 5),
(404, '2018-03-10 17:03:34', '', 9, 6),
(405, '2018-03-10 17:03:34', '', 9, 13),
(406, '2018-03-10 17:03:34', 'daz', 9, 3),
(407, '2018-03-10 17:03:34', '', 9, 4),
(408, '2018-03-10 17:03:41', 'Mme.', 15, 15),
(409, '2018-03-10 17:03:41', 'Nom15_C', 15, 1),
(410, '2018-03-10 17:03:41', 'Prenom15_B', 15, 2),
(411, '2018-03-10 17:03:42', '', 15, 11),
(412, '2018-03-10 17:03:42', '30 allée des puits', 15, 14),
(413, '2018-03-10 17:03:42', '777777', 15, 12),
(414, '2018-03-10 17:03:42', 'Madrid', 15, 5),
(415, '2018-03-10 17:03:42', 'Espagne', 15, 6),
(416, '2018-03-10 17:03:42', '0505050', 15, 13),
(417, '2018-03-10 17:03:42', '0606006', 15, 3),
(418, '2018-03-10 17:03:42', 'nom15@yahoo.net', 15, 4),
(419, '2018-03-10 17:19:49', 'M.', 16, 15),
(420, '2018-03-10 17:19:49', 'Dupont', 16, 1),
(421, '2018-03-10 17:19:49', 'Pierrot', 16, 2),
(422, '2018-03-10 17:19:49', '1993-02-17', 16, 11),
(423, '2018-03-10 17:19:49', '34 rue des Tombeaux', 16, 14),
(424, '2018-03-10 17:19:49', '75001', 16, 12),
(425, '2018-03-10 17:19:49', 'Paris', 16, 5),
(426, '2018-03-10 17:19:49', 'France', 16, 6),
(427, '2018-03-10 17:19:49', '010203040506', 16, 13),
(428, '2018-03-10 17:19:49', '066677889900', 16, 3),
(429, '2018-03-10 17:19:49', 'dupont.pierrot@free.com', 16, 4);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `Idgroupe` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `Idgroupeparent` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`Idgroupe`, `libelle`, `Idgroupeparent`) VALUES
(1, 'Ami', NULL),
(2, 'Profession', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `liencontactgroupe`
--

CREATE TABLE `liencontactgroupe` (
  `idcontact` int(11) NOT NULL,
  `idgroupe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lientemplatechamp`
--

CREATE TABLE `lientemplatechamp` (
  `idtemplate` int(11) NOT NULL,
  `idchamp` int(11) NOT NULL,
  `ordre` int(11) NOT NULL,
  `champactif` tinyint(4) NOT NULL DEFAULT '1',
  `accueil` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lientemplatechamp`
--

INSERT INTO `lientemplatechamp` (`idtemplate`, `idchamp`, `ordre`, `champactif`, `accueil`) VALUES
(1, 1, 1, 1, 1),
(1, 2, 2, 1, 1),
(1, 3, 8, 1, 1),
(1, 4, 9, 1, 0),
(1, 5, 5, 1, 0),
(1, 6, 6, 1, 0),
(1, 11, 3, 1, 0),
(1, 12, 4, 1, 0),
(1, 13, 7, 1, 1),
(1, 14, 3, 1, 0),
(1, 15, 0, 1, 1),
(2, 1, 0, 1, 0),
(2, 2, 0, 1, 0),
(2, 3, 0, 1, 0),
(2, 4, 0, 1, 0),
(2, 5, 0, 1, 0),
(2, 6, 0, 1, 0),
(2, 7, 0, 1, 0),
(2, 8, 0, 1, 0),
(2, 9, 0, 1, 0),
(2, 10, 0, 1, 0),
(2, 11, 0, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `preselection`
--

CREATE TABLE `preselection` (
  `idpreselect` int(11) NOT NULL,
  `Valeur` varchar(255) DEFAULT NULL,
  `idchamp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `preselection`
--

INSERT INTO `preselection` (`idpreselect`, `Valeur`, `idchamp`) VALUES
(3, 'Anglais', 7),
(4, 'Allemand', 7),
(5, 'Espagnol', 7),
(6, 'Développeur', 8),
(7, 'Technicien de maintenance', 8),
(8, 'Microsoft Office', 9),
(9, 'Plomberie', 9),
(10, 'M.', 15),
(11, 'Mme.', 15),
(12, 'Mlle.', 15);

-- --------------------------------------------------------

--
-- Structure de la table `template`
--

CREATE TABLE `template` (
  `idtemplate` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `template`
--

INSERT INTO `template` (`idtemplate`, `libelle`, `iduser`) VALUES
(1, 'Standard', NULL),
(2, 'Profesionnel', 2);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `encryptedkeypwd` binary(32) DEFAULT NULL,
  `dtcreation` date DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `encryptedkey` binary(32) DEFAULT NULL,
  `validationkey` varchar(30) DEFAULT NULL,
  `validaccount` tinyint(1) DEFAULT NULL,
  `actif` tinyint(1) NOT NULL DEFAULT '1',
  `hashed_password` binary(32) DEFAULT NULL,
  `timestamp_modif_pwd` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`iduser`, `login`, `encryptedkeypwd`, `dtcreation`, `role`, `encryptedkey`, `validationkey`, `validaccount`, `actif`, `hashed_password`, `timestamp_modif_pwd`) VALUES
(1, 'user1@hotmail.fr', 0x7573657231000000000000000000000000000000000000000000000000000000, '2017-11-20', NULL, 0xb9379b65fbbb92b11ff8ac35b626c5b95f8e8a9fb64f9948939fb51869431f02, 'lkxyjjzemqvhiqesyhfx', 1, 1, 0x289cfe05c74e2c262238f6057e2130ec6e7c1234c4229bff8aefd80438169cee, NULL),
(2, 'user2@yahoo.fr', 0x7573657232000000000000000000000000000000000000000000000000000000, '2017-11-20', NULL, 0x0000000000000000000000000000000000000000000000000000000000000000, NULL, NULL, 1, NULL, NULL),
(10, 'quentin.petit@yahoo.fr', NULL, NULL, NULL, NULL, NULL, 1, 1, 0x944bb3c8d217077fd474a8639f5312bddef75651f2eb6694e6285d9a0d6eb728, NULL),
(11, 'fdzef@yahoo.fr', NULL, '2018-03-09', NULL, NULL, 'rvkqicriaceiaomgjpym', 0, 1, 0x38f8188689cf6c7397e492abf269f3e40f6204bb77d0a7cb3fd6599c6234d9a5, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `champ`
--
ALTER TABLE `champ`
  ADD PRIMARY KEY (`idchamp`),
  ADD KEY `FK_CHAMP_id_datatype` (`iddatatype`);

--
-- Index pour la table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`idcontact`),
  ADD KEY `fk_contact_user` (`iduser`);

--
-- Index pour la table `datatype`
--
ALTER TABLE `datatype`
  ADD PRIMARY KEY (`iddatatype`);

--
-- Index pour la table `donnees`
--
ALTER TABLE `donnees`
  ADD PRIMARY KEY (`iddonnee`),
  ADD KEY `FK_Donnees_id_contact` (`idcontact`),
  ADD KEY `FK_Donnees_id_champ` (`idchamp`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`Idgroupe`),
  ADD KEY `FK_sousgroupe_ID_GROUPE_PARENT` (`Idgroupeparent`);

--
-- Index pour la table `liencontactgroupe`
--
ALTER TABLE `liencontactgroupe`
  ADD PRIMARY KEY (`idcontact`,`idgroupe`),
  ADD KEY `FK_LIENCONTACTGROUPE_ID_GROUPE` (`idgroupe`);

--
-- Index pour la table `lientemplatechamp`
--
ALTER TABLE `lientemplatechamp`
  ADD PRIMARY KEY (`idtemplate`,`idchamp`),
  ADD KEY `FK_LIENTEMPLATECHAMP_id_champ` (`idchamp`);

--
-- Index pour la table `preselection`
--
ALTER TABLE `preselection`
  ADD PRIMARY KEY (`idpreselect`),
  ADD KEY `FK_PRESELECTION_id_champ` (`idchamp`);

--
-- Index pour la table `template`
--
ALTER TABLE `template`
  ADD PRIMARY KEY (`idtemplate`),
  ADD KEY `fk_user` (`iduser`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `champ`
--
ALTER TABLE `champ`
  MODIFY `idchamp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `contact`
--
ALTER TABLE `contact`
  MODIFY `idcontact` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `datatype`
--
ALTER TABLE `datatype`
  MODIFY `iddatatype` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `donnees`
--
ALTER TABLE `donnees`
  MODIFY `iddonnee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=430;

--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `Idgroupe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `preselection`
--
ALTER TABLE `preselection`
  MODIFY `idpreselect` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `template`
--
ALTER TABLE `template`
  MODIFY `idtemplate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `champ`
--
ALTER TABLE `champ`
  ADD CONSTRAINT `FK_CHAMP_id_datatype` FOREIGN KEY (`iddatatype`) REFERENCES `datatype` (`iddatatype`);

--
-- Contraintes pour la table `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `fk_contact_user` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `donnees`
--
ALTER TABLE `donnees`
  ADD CONSTRAINT `FK_Donnees_id_champ` FOREIGN KEY (`idchamp`) REFERENCES `champ` (`idchamp`),
  ADD CONSTRAINT `FK_Donnees_id_contact` FOREIGN KEY (`idcontact`) REFERENCES `contact` (`idcontact`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `FK_sousgroupe_ID_GROUPE_PARENT` FOREIGN KEY (`Idgroupeparent`) REFERENCES `groupe` (`Idgroupe`);

--
-- Contraintes pour la table `lientemplatechamp`
--
ALTER TABLE `lientemplatechamp`
  ADD CONSTRAINT `FK_LIENTEMPLATECHAMP_id_champ` FOREIGN KEY (`idchamp`) REFERENCES `champ` (`idchamp`),
  ADD CONSTRAINT `FK_LIENTEMPLATECHAMP_id_template` FOREIGN KEY (`idtemplate`) REFERENCES `template` (`idtemplate`);

--
-- Contraintes pour la table `preselection`
--
ALTER TABLE `preselection`
  ADD CONSTRAINT `FK_PRESELECTION_id_champ` FOREIGN KEY (`idchamp`) REFERENCES `champ` (`idchamp`);

--
-- Contraintes pour la table `template`
--
ALTER TABLE `template`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
