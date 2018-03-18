-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 18 mars 2018 à 19:47
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
(1, '2018-03-10', 0, 1, 1),
(2, '2018-03-10', 0, 1, 1),
(3, '2018-03-10', 0, 1, 1);

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
(1071, '2018-03-10 17:19:49', 'M.', 1, 15),
(1072, '2018-03-10 17:19:49', 'Dupre', 1, 1),
(1073, '2018-03-10 17:19:49', 'Elsa', 1, 2),
(1074, '2018-03-10 17:19:49', '69 Rue Alasseur', 1, 14),
(1075, '2018-03-10 17:19:49', '78590', 1, 12),
(1076, '2018-03-10 17:19:49', 'Rennemoulin', 1, 5),
(1077, '2018-03-10 17:19:49', 'France', 1, 6),
(1078, '2018-03-10 17:19:49', '0205047650', 1, 13),
(1079, '2018-03-10 17:19:49', '06.68.57.49.85', 1, 3),
(1080, '2018-03-10 17:19:49', 'Dupre.Elsa@tahoo.fr', 1, 4),
(1081, '2018-03-10 17:19:49', 'Mme.', 2, 15),
(1082, '2018-03-10 17:19:49', 'Doucet', 2, 1),
(1083, '2018-03-10 17:19:49', 'Fanny', 2, 2),
(1084, '2018-03-10 17:19:49', '156 Rue de Beauce', 1, 14),
(1085, '2018-03-10 17:19:49', '83660', 2, 12),
(1086, '2018-03-10 17:19:49', 'Carnoules', 2, 5),
(1087, '2018-03-10 17:19:49', 'France', 2, 6),
(1088, '2018-03-10 17:19:49', '0453961405', 2, 13),
(1089, '2018-03-10 17:19:49', '06.24.32.54.27', 2, 3),
(1090, '2018-03-10 17:19:49', 'Doucet.Fanny@tahoo.fr', 2, 4),
(1091, '2018-03-10 17:19:49', 'Mme.', 3, 15),
(1092, '2018-03-10 17:19:49', 'Fernandes', 3, 1),
(1093, '2018-03-10 17:19:49', 'Pauline', 3, 2),
(1094, '2018-03-10 17:19:49', '18 Rue Cavallotti', 3, 14),
(1095, '2018-03-10 17:19:49', '17210', 3, 12),
(1096, '2018-03-10 17:19:49', 'Saint-Palais-de-Négrignac', 3, 5),
(1097, '2018-03-10 17:19:49', 'France', 3, 6),
(1098, '2018-03-10 17:19:49', '0078017122', 3, 13),
(1099, '2018-03-10 17:19:49', '06.62.45.54.72', 3, 3),
(1100, '2018-03-10 17:19:49', 'Fernandes.Pauline@hotmail.fr', 3, 4);

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
(2, 'user2@yahoo.fr', 0x7573657232000000000000000000000000000000000000000000000000000000, '2017-11-20', NULL, 0x0000000000000000000000000000000000000000000000000000000000000000, NULL, 1, 1, 0x33bc01fdb5d6703296e95370ca8044378c870a4ed3840db9f53c784553f9eeed, NULL);

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
  MODIFY `idcontact` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `datatype`
--
ALTER TABLE `datatype`
  MODIFY `iddatatype` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `donnees`
--
ALTER TABLE `donnees`
  MODIFY `iddonnee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1101;

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
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
