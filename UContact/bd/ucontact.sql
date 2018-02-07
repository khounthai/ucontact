-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 06 fév. 2018 à 15:57
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  5.6.33

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
  `actif` tinyint(1) DEFAULT NULL,
  `iddatatype` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `champ`
--

INSERT INTO `champ` (`idchamp`, `libelle`, `multivaleur`, `actif`, `iddatatype`) VALUES
(1, 'Nom', 0, 1, 1),
(2, 'Prénom', 0, 1, 1),
(3, 'Portable', 0, 1, 1),
(4, 'Email', 0, 1, 1),
(5, 'Ville', 0, 1, 1),
(6, 'Pays', 0, 1, 1),
(7, 'Langue', 1, 1, 1),
(8, 'Profession', 0, 1, 1),
(9, 'Compétence', 1, 1, 1),
(10, 'Taille', 0, 1, 3),
(11, 'Date de naissance', 0, 1, 4),
(12, 'CP', 0, 1, 1),
(13, 'Téléphone fixe', 0, 1, 2),
(14, 'Adresse', 0, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

CREATE TABLE `contact` (
  `idcontact` int(11) NOT NULL,
  `dtcreation` date DEFAULT NULL,
  `favoris` tinyint(1) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contact`
--

INSERT INTO `contact` (`idcontact`, `dtcreation`, `favoris`, `iduser`) VALUES
(1, '2018-02-06', 0, 1),
(6, '2018-02-06', 0, 1),
(7, '2018-02-06', 0, 1),
(8, '2018-02-06', 0, 7);

-- --------------------------------------------------------

--
-- Structure de la table `datatype`
--

CREATE TABLE `datatype` (
  `iddatatype` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `datatype`
--

INSERT INTO `datatype` (`iddatatype`, `libelle`) VALUES
(1, 'TEXTE'),
(2, 'ENTIER'),
(3, 'DECIMAL'),
(4, 'DATE');

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
(38, '2018-02-06 00:00:00', 'qze', 8, 1),
(39, '2018-02-06 00:00:00', 'zqe', 8, 2),
(40, '2018-02-06 00:00:00', 'ezq', 8, 14),
(41, '2018-02-06 00:00:00', 'zee', 8, 12),
(42, '2018-02-06 00:00:00', '', 8, 5),
(43, '2018-02-06 00:00:00', 'qeqz', 8, 6),
(44, '2018-02-06 00:00:00', 'eqz', 8, 13),
(45, '2018-02-06 00:00:00', 'eqze', 8, 3),
(46, '2018-02-06 00:00:00', 'zqez', 8, 4);

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
  `ordre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lientemplatechamp`
--

INSERT INTO `lientemplatechamp` (`idtemplate`, `idchamp`, `ordre`) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 8),
(1, 4, 9),
(1, 5, 5),
(1, 6, 6),
(1, 12, 4),
(1, 13, 7),
(1, 14, 3),
(2, 1, 0),
(2, 2, 0),
(2, 3, 0),
(2, 4, 0),
(2, 5, 0),
(2, 6, 0),
(2, 7, 0),
(2, 8, 0),
(2, 9, 0),
(2, 10, 0),
(2, 11, 0);

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
(1, 'Paris', 5),
(2, 'Reims', 5),
(3, 'Anglais', 7),
(4, 'Allemand', 7),
(5, 'Espagnol', 7),
(6, 'Développeur', 8),
(7, 'Technicien de maintenance', 8),
(8, 'Microsoft Office', 9),
(9, 'Plomberie', 9);

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
  `password` varchar(50) DEFAULT NULL,
  `dtcreation` date DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `encryptedkey` binary(32) DEFAULT NULL,
  `validationkey` varchar(30) DEFAULT NULL,
  `validaccount` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`iduser`, `login`, `password`, `dtcreation`, `role`, `encryptedkey`, `validationkey`, `validaccount`) VALUES
(1, 'user1@hotmail.fr', 'user1', '2017-11-20', NULL, 0x2fcc9384143c56f2eafdcb29d6331ffffe0db17b34a5ce7001fca0d7c183c402, NULL, 0),
(2, 'user2@yahoo.fr', 'user2', '2017-11-20', NULL, 0x0000000000000000000000000000000000000000000000000000000000000000, NULL, NULL),
(7, 'khounthai.houang@viacesi.fr', 'kh1', NULL, NULL, NULL, NULL, 1);

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
  MODIFY `idchamp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `contact`
--
ALTER TABLE `contact`
  MODIFY `idcontact` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `datatype`
--
ALTER TABLE `datatype`
  MODIFY `iddatatype` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `donnees`
--
ALTER TABLE `donnees`
  MODIFY `iddonnee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `Idgroupe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `preselection`
--
ALTER TABLE `preselection`
  MODIFY `idpreselect` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `template`
--
ALTER TABLE `template`
  MODIFY `idtemplate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
