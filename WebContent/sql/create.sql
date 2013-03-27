-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 20 Février 2013 à 09:59
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `yourfaceshark`
--

-- --------------------------------------------------------

--
-- Structure de la table `like`
--

CREATE TABLE IF NOT EXISTS `like` (
  `idLike` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPost` int(10) unsigned NOT NULL DEFAULT '0',
  `like` smallint(5) unsigned NOT NULL DEFAULT '0',
  `dislike` smallint(5) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`idLike`),
  KEY `idPost` (`idPost`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `like`
--

INSERT INTO `like` (`idLike`, `idPost`, `like`, `dislike`) VALUES
(1, 1, 2, 1),
(2, 2, 15, 0);

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `idPost` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPosteur` int(10) unsigned NOT NULL DEFAULT '0',
  `idCible` int(10) unsigned NOT NULL DEFAULT '0',
  `texte` varchar(1500) NOT NULL DEFAULT '',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` varchar(250) NOT NULL DEFAULT '',
  PRIMARY KEY (`idPost`),
  KEY `idCible` (`idCible`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `post`
--

INSERT INTO `post` (`idPost`, `idPosteur`, `idCible`, `texte`, `date`, `url`) VALUES
(1, 1, 1, '1er post trololo', '2013-02-20 09:51:56', ''),
(2, 4, 3, 'Le snow, c''est la base ! ', '2013-02-20 09:51:56', 'http://membres.multimania.fr/ecoutelecoulidusnow/snowboard-22.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL DEFAULT '',
  `prenom` varchar(30) NOT NULL DEFAULT '',
  `dateNaissance` date NOT NULL,
  `sexe` char(1) NOT NULL DEFAULT '',
  `mail` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idUser`),
  KEY `nom` (`nom`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`idUser`, `nom`, `prenom`, `dateNaissance`, `sexe`, `mail`, `password`) VALUES
(1, 'Mumpert', 'Clémence', '1990-05-21', 'f', 'c.mumpert@gmail.com', 'clemence'),
(2, 'Fontaine', 'Hugo', '1991-04-17', 'm', 'hugo.fontaine@live.fr', 'hugo'),
(3, 'Mouliac', 'Bastien', '1992-03-30', 'm', 'bmouliac@gmail.com', 'bastien');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
